package projeto.frontend.view.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import projeto.backend.controller.ControllerUsuario;
import projeto.backend.model.Evento;
import projeto.backend.model.Ingresso;
import projeto.frontend.utils.NavegacaoTela;

import java.io.IOException;
import java.util.List;

public class TelaComprasRealizadasController {

    private ControllerUsuario controllerUsuario = new ControllerUsuario();
    private final List<Ingresso> ingressos = controllerUsuario.listarComprasRealizadas();

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
    public void initialize() {
        colunaNomeEvento.setCellValueFactory(new PropertyValueFactory<>("nomeEvento"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaEvento"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricaoEvento"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("dataEvento"));
        colunaStatusEvento.setCellValueFactory(new PropertyValueFactory<>("statusEvento"));
        ObservableList<Ingresso> listaIngressos = FXCollections.observableArrayList(ingressos);
        tabelaCompras.setItems(listaIngressos);

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

    }


    private void abrirTelaRecibo(Ingresso ingresso) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaRecibo.fxml"));
        Parent root = loader.load();

        TelaReciboController telaReciboController = loader.getController();

        telaReciboController.exibirRecibo(ingresso);

        Stage stage = (Stage) tabelaCompras.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }
}
