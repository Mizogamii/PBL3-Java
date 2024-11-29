package projeto.frontend.view.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projeto.backend.model.Ingresso;

import javafx.scene.control.Label;
import projeto.backend.model.Recibo;
import projeto.frontend.utils.NavegacaoTela;

import java.io.IOException;

public class TelaReciboController {
    @FXML
    private Label nomeEventoLabel;

    @FXML
    private Label categoriaLabel;

    @FXML
    private Label descricaoLabel;

    @FXML
    private Label dataLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label labelAbrirTelaComentar;

    private Recibo recibo;

    public void exibirRecibo(Ingresso ingresso) {
        Platform.runLater(() -> {
            nomeEventoLabel.setText(ingresso.getNomeEvento());
            categoriaLabel.setText(ingresso.getCategoriaEvento());
            descricaoLabel.setText(ingresso.getDescricaoEvento());
            dataLabel.setText(ingresso.getDataEventoFormatado());
            statusLabel.setText(ingresso.getStatusEvento());
        });
    }
    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

    public void comentar(MouseEvent mouseEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaComentario.fxml"));
        Parent root = loader.load();

        TelaComentarioController telaComentarioController = loader.getController();

        telaComentarioController.exibirNomeEvento(recibo);

        Stage stage = (Stage) labelAbrirTelaComentar.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
