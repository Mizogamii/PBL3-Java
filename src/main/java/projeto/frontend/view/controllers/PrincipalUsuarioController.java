package projeto.frontend.view.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import projeto.backend.controller.ControllerEvento;
import projeto.backend.model.Evento;
import projeto.frontend.utils.NavegacaoTela;

import java.util.List;

public class PrincipalUsuarioController {
    private ControllerEvento controllerEvento = new ControllerEvento();

    @FXML
    public Label labelAbrirTelaLogin;

    @FXML
    private Label labelAbrirTelaListar;

    @FXML
    private Label labelAbrirTelaCadastro;

    @FXML
    private Label abrirTelaLogin;

    @FXML
    private TableView<Evento> tabelaEventos;

    @FXML
    private TableColumn<Evento, String> colunaNomeEvento;

    @FXML
    private TableColumn<Evento, String> colunaCategoria;

    @FXML
    private TableColumn<Evento, String> colunaData;

    private final List<Evento> eventos = controllerEvento.listarEventosDisponiveis();

    @FXML
    public void initialize() {
       colunaNomeEvento.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));

        ObservableList<Evento> listaEventos = FXCollections.observableArrayList(eventos);

        tabelaEventos.setItems(listaEventos);

        tabelaEventos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Evento eventoSelecionado = tabelaEventos.getSelectionModel().getSelectedItem();
                if (eventoSelecionado != null) {
                    abrirDetalhesEventos(eventoSelecionado);
                }
            }
        });
    }

    @FXML
    public void abrirTelaListar(){
        Stage stage = null;
        if (labelAbrirTelaListar == null) {
            System.out.println("labelAbriTelaListar está null!");
        } else {
            stage = (Stage) labelAbrirTelaListar.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaListarEvento.fxml", "Listar dados");
    }

    @FXML
    public void abrirTelaCadastro() {
        System.out.println("Label: " + labelAbrirTelaCadastro);
        Stage stage = null;
        if (labelAbrirTelaCadastro == null) {
            System.out.println("labelAbriTelaCadastro está null!");
        } else {
            stage = (Stage) labelAbrirTelaCadastro.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaCadastro.fxml", "Cadastro");
    }

    @FXML
    public void abrirTelaLogin(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println("Label: " + labelAbrirTelaLogin);
        Stage stage = null;
        if (labelAbrirTelaLogin == null) {
            System.out.println("labelAbriTelaCadastro está null!");
        } else {
            stage = (Stage) labelAbrirTelaLogin.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaLogin.fxml", "Login");
    }
    public void abrirDetalhesEventos(Evento evento){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detalhes do Evento");
        alert.setHeaderText("Notificação");

        String detalhes = "Evento: " + evento.getNome() +
                "\nDescrição: " + evento.getDescricao() +
                "\nData do evento: " + evento.getDataFormatada() +
                "\nCategoria: " + evento.getCategoria() +
                "\nPreço: R$" + evento.getPreco();

        alert.setContentText(detalhes);
        alert.showAndWait();
    }

}
