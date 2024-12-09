/*******************************************************************************************
 Autor: Sayumi Mizogami Santana
 Componente Curricular: EXA 863 - MI Programação
 Concluido em: 08/12/2024
 Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 *******************************************************************************************/
package projeto.frontend.view.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import projeto.backend.controller.ControllerUsuario;
import projeto.backend.model.Evento;
import projeto.backend.model.Ingresso;
import projeto.backend.model.Notificacoes;
import projeto.frontend.utils.Acessibilidade;
import projeto.frontend.utils.NavegacaoTela;
import projeto.backend.model.Pagamento;
import projeto.backend.controller.ControllerCompra;
import projeto.frontend.utils.UsuarioLogado;

import java.text.SimpleDateFormat;

/**
 * Controlador responsável pela tela de pagamento, permitindo ao usuário selecionar
 * um método de pagamento, realizar a compra de ingressos e gerenciar notificações.
 */
public class PagamentoController {
    /**
     * Controlador para gerenciar a compra de ingressos.
     */
    private ControllerCompra controllerCompra = new ControllerCompra();

    /**
     * Controlador para gerenciar informações do usuário.
     */
    private ControllerUsuario controllerUsuario = new ControllerUsuario();

    /**
     * Controlador principal do usuário logado, gerencia informações exibidas ao usuário.
     */
    private PrincipalUsuarioLogadoController principalUsuarioLogadoController = new PrincipalUsuarioLogadoController();

    @FXML
    private Label nomeEventoLabel;

    @FXML
    private Label dataLabel;

    @FXML
    private Label categoriaLabel;

    @FXML
    private Label precoLabel;

    @FXML
    private Label precoLabel1;

    @FXML
    private ComboBox<String> metodoPagamento;

    @FXML
    public Label voltarTelaInicial;

    /**
     * Informações do evento associado à compra.
     */
    private Evento eventoInfo;

    /**
     * Define o evento atual e atualiza os campos visuais com suas informações.
     *
     * @param eventoInfo o evento cujas informações serão exibidas.
     */
    public void setEvento(Evento eventoInfo) {
        this.eventoInfo = eventoInfo;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Platform.runLater(() -> {
            nomeEventoLabel.setText(eventoInfo.getNome());
            dataLabel.setText(formatter.format(eventoInfo.getData()));
            categoriaLabel.setText(eventoInfo.getCategoria());
            precoLabel.setText(String.format("%.2f", eventoInfo.getPreco()));
            precoLabel1.setText(String.format("R$%.2f", eventoInfo.getPreco()));

        });
    }

    /**
     * Inicializa a tela configurando as opções de métodos de pagamento disponíveis.
     */
    @FXML
    public void initialize(){
        if (voltarTelaInicial != null) {
            voltarTelaInicial.setFocusTraversable(true);
            Acessibilidade.configurarEstiloFoco(voltarTelaInicial);
            voltarTelaInicial.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    voltarTela(null);
                }
            });
        }
        ObservableList<String> opcoes = FXCollections.observableArrayList(
                Pagamento.TipoPagamento.BOLETO.name(),
                Pagamento.TipoPagamento.CREDITO.name(),
                Pagamento.TipoPagamento.DEBITO.name(),
                Pagamento.TipoPagamento.PIX.name()
        );
        metodoPagamento.setItems(opcoes);
    }

    /**
     * Volta para a tela inicial ao clicar em um botão ou ícone de navegação.
     *
     * @param mouseEvent o evento de clique do mouse.
     */
    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

    /**
     * Realiza o pagamento selecionado pelo usuário e processa a compra do ingresso.
     * Adiciona uma notificação ao usuário e retorna à tela inicial em caso de sucesso.
     *
     * @param actionEvent o evento de clique do botão de pagamento.
     */
    public void botaoRealizarPagamento(ActionEvent actionEvent) {
        try{
            String metodoSelecionado = metodoPagamento.getValue();

            if(metodoSelecionado == null || metodoSelecionado.isEmpty()){
                NavegacaoTela.showErrorMessage("Erro! Selecione uma forma de pagamento.");
                return;
            }

            Pagamento.TipoPagamento tipoPagamento = Pagamento.TipoPagamento.valueOf(metodoSelecionado);
            Ingresso ingresso = controllerCompra.fazerIngresso(UsuarioLogado.getUsuarioLogado(), eventoInfo.getNome(), tipoPagamento, UsuarioLogado.getUsuarioLogado().getLogin());
            NavegacaoTela.showSuccessMessage("Pagamento", "Pagamento realizado com sucesso!");

            Notificacoes notificacoes = new Notificacoes("Compra realizada para o evento: " + eventoInfo.getNome());
            UsuarioLogado.getUsuarioLogado().adicionarNotificacoes(notificacoes);

            NavegacaoTela.voltarTelaInicial();

        }catch (IllegalArgumentException | NullPointerException e){
            NavegacaoTela.showErrorMessage("Erro! Selecione uma forma de pagamento.");
        }
    }

}
