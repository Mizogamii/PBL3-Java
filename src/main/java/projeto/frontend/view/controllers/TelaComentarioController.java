package projeto.frontend.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import projeto.frontend.utils.NavegacaoTela;

public class TelaComentarioController {

    @FXML
    private Label nomeEvento;


    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }
}
