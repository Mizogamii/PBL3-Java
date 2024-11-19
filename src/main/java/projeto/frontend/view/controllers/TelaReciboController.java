package projeto.frontend.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projeto.backend.model.Ingresso;

import javafx.scene.control.Label;
import projeto.frontend.utils.NavegacaoTela;

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

    public void exibirRecibo(Ingresso ingresso) {
        nomeEventoLabel.setText(ingresso.getNomeEvento());
        categoriaLabel.setText(ingresso.getCategoriaEvento());
        descricaoLabel.setText(ingresso.getDescricaoEvento());
        dataLabel.setText(ingresso.getDataEvento());
        statusLabel.setText(ingresso.getStatusEvento());
    }
    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

    public void comentar(MouseEvent mouseEvent) {
        Stage stage = null;
        if (labelAbrirTelaComentar == null) {
            System.out.println("labelComprasComentar está null!");
        } else {
            stage = (Stage) labelAbrirTelaComentar.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaComentario.fxml", "Comentários");
    }
}
