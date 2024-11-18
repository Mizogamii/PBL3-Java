package projeto.frontend.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
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
}
