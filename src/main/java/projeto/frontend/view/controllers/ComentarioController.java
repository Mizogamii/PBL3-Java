package projeto.frontend.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import projeto.backend.controller.ControllerEvento;
import projeto.backend.model.Ingresso;
import projeto.backend.model.Recibo;
import projeto.frontend.utils.NavegacaoTela;
import projeto.frontend.utils.UsuarioLogado;

import java.lang.module.Configuration;

public class ComentarioController {

    private ControllerEvento controllerEvento = new ControllerEvento();

    @FXML
    private Label nomeEvento;

    @FXML
    private Button mandarComentario;

    @FXML
    private TextArea areaComentar;

    private Ingresso ingresso;

    public void setIngresso(Ingresso ingresso){
        this.ingresso = ingresso;
        nomeEvento.setText(ingresso.getNomeEvento());
    }

    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

    public void enviarComentario(MouseEvent mouseEvent) {
        try{
            String comentarioTexto = areaComentar.getText().trim();
            controllerEvento.fazerComentario(UsuarioLogado.getUsuarioLogado(), ingresso.getEvento(), ingresso.getNomeEvento(), comentarioTexto);
            if(!comentarioTexto.isEmpty()){
                NavegacaoTela.showSuccessMessage("Comentário","Comentário realizado com sucesso!");
                NavegacaoTela.voltarTelaInicial();
            }
        }catch (Exception e){
            NavegacaoTela.showErrorMessage("ERRO! Digite um comentário!");
        }
    }
}
