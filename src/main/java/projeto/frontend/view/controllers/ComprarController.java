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
import projeto.backend.controller.ControllerEvento;
import projeto.backend.model.Evento;
import projeto.frontend.utils.Acessibilidade;
import projeto.frontend.utils.NavegacaoTela;

import java.io.IOException;
import java.util.List;

/**
 * Controlador responsável pela interface de compra de ingressos.
 * Exibe eventos disponíveis, permite filtrar por categoria e acessar a tela de pagamento ao selecionar um evento.
 */
public class ComprarController {

    /** Controlador responsável pela lógica relacionada aos eventos. */
    private ControllerEvento controllerEvento = new ControllerEvento();

    @FXML
    public Label voltarTelaInicial;

    @FXML
    private TableView<Evento> tabelaEventos;

    @FXML
    private TableColumn<Evento, String> colunaNomeEvento;

    @FXML
    private TableColumn<Evento, String> colunaCategoria;

    @FXML
    private TableColumn<Evento, String> colunaDescricao;

    @FXML
    private TableColumn<Evento, String> colunaData;

    @FXML
    private ComboBox<String> filtroCategoria;

    @FXML
    private TableColumn<Evento, Integer> colunaQuantidadeAssentosDisponiveis;

    @FXML
    public TableColumn<Evento, Double> colunaPreco;

    /** Lista de eventos disponíveis. */
    private final List<Evento> eventos = controllerEvento.listarEventosDisponiveis();

    /**
     * Inicializa os componentes da interface e popula a tabela de eventos e o filtro de categorias.
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
        colunaNomeEvento.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("dataFormatada"));
        colunaQuantidadeAssentosDisponiveis.setCellValueFactory(new PropertyValueFactory<>("quantidadeAssentosDisponiveis"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        ObservableList<Evento> listaEventos = FXCollections.observableArrayList(eventos);
        tabelaEventos.setItems(listaEventos);

        ObservableList<String> categorias = FXCollections.observableArrayList();
        eventos.forEach(evento -> {
            if (!categorias.contains(evento.getCategoria())) {
                categorias.add(evento.getCategoria());
            }
        });
        filtroCategoria.setItems(categorias);
        filtroCategoria.getItems().add(0, "Todas");
        filtroCategoria.getSelectionModel().select(0);

        tabelaEventos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Evento eventoSelecionado = tabelaEventos.getSelectionModel().getSelectedItem();
                if (eventoSelecionado != null) {
                    abrirTelaPagamento(eventoSelecionado);
                }
            }
        });

        tabelaEventos.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Evento eventoSelecionado = tabelaEventos.getSelectionModel().getSelectedItem();
                if (eventoSelecionado != null) {
                    abrirTelaPagamento(eventoSelecionado);
                }
            }
        });
    }

    /**
     * Abre a tela de pagamento para o evento selecionado.
     *
     * @param evento O evento selecionado para compra.
     */
    private void abrirTelaPagamento(Evento evento){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaPagamento.fxml"));
            Parent root = loader.load();

            PagamentoController telaPagamentoController = loader.getController();

            telaPagamentoController.setEvento(evento);

            Stage stage = (Stage) tabelaEventos.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            NavegacaoTela.showErrorMessage("Erro ao abrir a tela de pagamento");
        }
    }

    /**
     * Retorna à tela inicial do sistema.
     *
     * @param mouseEvent Evento de clique do mouse.
     */
    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

    /**
     * Filtra os eventos exibidos na tabela com base na categoria selecionada.
     *
     * @param actionEvent Evento de ação do filtro de categoria.
     */
    public void filtrarCategoria(ActionEvent actionEvent) {
        String categoriaSelecionada = filtroCategoria.getValue();

        if ("Todas".equals(categoriaSelecionada)) {
            tabelaEventos.setItems(FXCollections.observableArrayList(eventos));
        } else {
            ObservableList<Evento> eventosFiltrados = FXCollections.observableArrayList();
            for (Evento evento : eventos) {
                if (evento.getCategoria().equals(categoriaSelecionada)) {
                    eventosFiltrados.add(evento);
                }
            }
            tabelaEventos.setItems(eventosFiltrados);
        }
    }

}

