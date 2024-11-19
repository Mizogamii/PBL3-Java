package projeto.frontend.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import projeto.backend.model.Recibo;
import projeto.frontend.utils.NavegacaoTela;

public class TelaComentarioController {

    @FXML
    private Label nomeEvento;


    public void exibirNomeEvento(Recibo recibo){
        nomeEvento.setText(recibo.getNomeDoEvento());
    }

    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }
}
