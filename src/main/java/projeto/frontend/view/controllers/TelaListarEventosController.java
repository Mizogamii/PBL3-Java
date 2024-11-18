package projeto.frontend.view.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import projeto.backend.controller.ControllerEvento;
import projeto.backend.model.Evento;
import projeto.frontend.utils.NavegacaoTela;

import java.util.List;

public class TelaListarEventosController {

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

    private final List<Evento> eventos = controllerEvento.listarEventosDisponiveis();

    @FXML
    public void initialize() {
        colunaNomeEvento.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaQuantidadeAssentosDisponiveis.setCellValueFactory(new PropertyValueFactory<>("quantidadeAssentosDisponiveis"));
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
