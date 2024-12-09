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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projeto.backend.controller.ControllerUsuario;
import projeto.backend.model.Evento;
import projeto.backend.model.Ingresso;
import projeto.frontend.utils.Acessibilidade;
import projeto.frontend.utils.NavegacaoTela;
import projeto.frontend.utils.UsuarioLogado;

import java.io.IOException;
import java.util.List;

/**
 * Controlador da tela de compras realizadas, responsável por exibir a lista de ingressos
 * comprados pelo usuário e permitir o filtro por status do evento.
 */
public class ComprasRealizadasController {

    private ControllerUsuario controllerUsuario = new ControllerUsuario();
    private List<Ingresso> ingressos = controllerUsuario.listarComprasRealizadas(UsuarioLogado.getUsuarioLogado());

    @FXML
    public Label voltarTelaInicial;

    @FXML
    private TableView<Ingresso> tabelaCompras;

    @FXML
    private TableColumn<Ingresso, String> colunaNomeEvento;

    @FXML
    private TableColumn<Ingresso, String> colunaCategoria;

    @FXML
    private TableColumn<Ingresso, String> colunaDescricao;

    @FXML
    private TableColumn<Ingresso, String> colunaData;

    @FXML
    private TableColumn<Ingresso, String> colunaStatusEvento;

    @FXML
    private ComboBox<String> filtroStatus;

    /**
     * Inicializa a tela de compras realizadas, configura as colunas da tabela e o filtro de status.
     * Também carrega a lista de ingressos comprados pelo usuário logado.
     */
    @FXML
    public void initialize() {
        if (voltarTelaInicial != null) {
            voltarTelaInicial.setFocusTraversable(true);
            Acessibilidade.configurarEstiloFoco(voltarTelaInicial);
            voltarTelaInicial.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    voltarTela(null);
                }
            });
        }
        //Configura as colunas da tabela
        colunaNomeEvento.setCellValueFactory(new PropertyValueFactory<>("nomeEvento"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaEvento"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricaoEvento"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("dataEventoFormatado"));
        colunaStatusEvento.setCellValueFactory(new PropertyValueFactory<>("statusEvento"));

        //Adiciona os ingressos à tabela
        ObservableList<Ingresso> listaIngressos = FXCollections.observableArrayList(ingressos);
        tabelaCompras.setItems(listaIngressos);

        //Configura o filtro de status
        ObservableList<String> filtro = FXCollections.observableArrayList("Todas");
        ingressos.forEach(ingresso -> {
            if(!filtro.contains(ingresso.getStatusEvento())){
                filtro.add(ingresso.getStatusEvento());
            }
        });
        filtroStatus.setItems(filtro);
        filtroStatus.setValue("Todas");

        // Ação ao clicar duas vezes sobre um ingresso
        tabelaCompras.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Ingresso ingressoSelecionado = tabelaCompras.getSelectionModel().getSelectedItem();
                if (ingressoSelecionado != null) {
                    try {
                        abrirTelaRecibo(ingressoSelecionado);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        tabelaCompras.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Ingresso ingressoSelecionado = tabelaCompras.getSelectionModel().getSelectedItem();
                if (ingressoSelecionado != null) {
                    try {
                        abrirTelaRecibo(ingressoSelecionado);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    /**
     * Abre a tela de recibo com as informações do ingresso selecionado.
     *
     * @param ingresso O ingresso selecionado, cujos detalhes serão exibidos na tela de recibo.
     * @throws IOException Se ocorrer um erro ao carregar a tela de recibo.
     */
    private void abrirTelaRecibo(Ingresso ingresso) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaRecibo.fxml"));
        Parent root = loader.load();

        ReciboController telaReciboController = loader.getController();

        telaReciboController.exibirRecibo(ingresso);

        Stage stage = (Stage) tabelaCompras.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    /**
     * Volta para a tela inicial quando o botão de voltar é pressionado.
     *
     * @param mouseEvent O evento de clique do mouse.
     */
    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

    /**
     * Filtra os ingressos exibidos na tabela de acordo com o status selecionado.
     *
     * @param actionEvent O evento de ação gerado pelo filtro de status.
     */
    public void filtrarStatus(ActionEvent actionEvent) {
        String statusSelecionado = filtroStatus.getValue();
        if("Todas".equals(statusSelecionado)){
            tabelaCompras.setItems(FXCollections.observableArrayList(ingressos));
        }else{
            ObservableList<Ingresso> statusFiltrados = FXCollections.observableArrayList();
            for(Ingresso ingresso: ingressos){
                if(ingresso.getStatusEvento().equals(statusSelecionado)){
                    statusFiltrados.add(ingresso);
                }
                tabelaCompras.setItems(statusFiltrados);
            }
        }
    }
}
