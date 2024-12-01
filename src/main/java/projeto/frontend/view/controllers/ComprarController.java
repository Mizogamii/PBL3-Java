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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projeto.backend.controller.ControllerEvento;
import projeto.backend.model.Evento;
import projeto.frontend.utils.NavegacaoTela;

import java.io.IOException;
import java.util.List;

public class ComprarController {
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

    private final List<Evento> eventos = controllerEvento.listarEventosDisponiveis();

    @FXML
    public void initialize() {
        colunaNomeEvento.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
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
    }

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

    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

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

