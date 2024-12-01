package projeto.frontend.view.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import projeto.backend.model.Evento;
import projeto.backend.model.Ingresso;
import projeto.backend.model.Usuario;
import projeto.frontend.utils.NavegacaoTela;

import projeto.backend.controller.ControllerUsuario;
import projeto.frontend.utils.UsuarioLogado;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class ListaEventosFeedBack {
    @FXML
    public Label voltarTelaInicial;

    @FXML
    private TableView<Ingresso> tabelaEventoParticipado;

    @FXML
    private TableColumn<Ingresso, String> colunaNomeEvento;

    @FXML
    private TableColumn<Ingresso, String> colunaCategoria;

    @FXML
    private TableColumn<Ingresso, String> colunaDescricao;

    @FXML
    private TableColumn<Ingresso, String> colunaData;

    @FXML
    private ComboBox<String> filtroCategoria;

    private ControllerUsuario controllerUsuario = new ControllerUsuario();

    private List<Ingresso> ingressos = controllerUsuario.listarEventosParticipados(UsuarioLogado.getUsuarioLogado());

    @FXML
    public void initialize() {
        colunaNomeEvento.setCellValueFactory(new PropertyValueFactory<>("nomeEvento"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaEvento"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricaoEvento"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("dataEvento"));

        ObservableList<Ingresso> listaIngressos = FXCollections.observableArrayList(ingressos);

        tabelaEventoParticipado.setItems(listaIngressos);

        tabelaEventoParticipado.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Ingresso ingressoSelecionado = tabelaEventoParticipado.getSelectionModel().getSelectedItem();
                if (ingressoSelecionado != null) {
                    try {
                        comentar(ingressoSelecionado);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void comentar(Ingresso ingresso) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaComentario.fxml"));
            Parent root = loader.load();

            ComentarioController telaComentarioController = loader.getController();

            telaComentarioController.exibirNomeEvento(ingresso);

            Stage stage = (Stage) tabelaEventoParticipado.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch(IOException e) {
            throw new IOException(e);
        }
    }

    public void voltarTela(javafx.scene.input.MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }
}


