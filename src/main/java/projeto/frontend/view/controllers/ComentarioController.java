package projeto.frontend.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import projeto.backend.model.Ingresso;
import projeto.backend.model.Recibo;
import projeto.frontend.utils.NavegacaoTela;
import projeto.frontend.utils.UsuarioLogado;

public class ComentarioController {

    @FXML
    private Label nomeEvento;

    public void exibirNomeEvento(Ingresso ingresso){
        nomeEvento.setText(ingresso.getNomeEvento());
    }

    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }
}
