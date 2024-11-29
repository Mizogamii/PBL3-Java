package projeto.frontend.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import projeto.backend.model.Recibo;
import projeto.frontend.utils.NavegacaoTela;
import projeto.frontend.utils.UsuarioLogado;

public class TelaComentarioController {

    @FXML
    private Label nomeEvento;

    @FXML
    private Label nomeUsuarioLogado;

    public void exibirNomeEvento(Recibo recibo){
        nomeEvento.setText(recibo.getNomeDoEvento());
        nomeUsuarioLogado.setText(UsuarioLogado.getUsuarioLogado().getNome());
    }

    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }
}
