package projeto.frontend.view.controllers;

import eu.hansolo.toolbox.observables.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import projeto.backend.controller.ControllerEvento;
import projeto.backend.model.Evento;

import java.util.List;

public class TelaListarEventos {
    private ControllerEvento controllerEvento = new ControllerEvento();

    @FXML
    private TableView<Evento> tabelaEventos;

    @FXML
    private TableColumn<Evento, String> colunaNome;

    @FXML
    private TableColumn<Evento, String> colunaCategoria;

    @FXML
    private TableColumn<Evento, String> colunaDescricao;

    @FXML
    private TableColumn<Evento, String> colunaData;

    @FXML
    private TableColumn<Evento, String> colunaQuantidade;


    @FXML
    public void initialize() {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
    }

    @FXML
    private void abrirTelaListar(){
    }

    @FXML
    private void abrirTelaListar() {
        // Obtém a lista de eventos do back-end
        List<Evento> eventos = controllerEvento.listarEventosDisponiveis();

        // Adiciona os eventos à tabela
        ObservableList<Evento> eventosObservable = FXCollections.observableArrayList(eventos);
        tabelaEventos.setItems(eventosObservable);
    }

}
