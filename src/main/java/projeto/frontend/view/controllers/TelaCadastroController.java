package projeto.frontend.view.controllers;

import eu.hansolo.tilesfx.Test;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaCadastroController {
    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoLogin;

    @FXML
    private TextField campoEmail;

    @FXML
    private TextField campoCpf;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private void botaoSalvar() {
        String nome = campoNome.getText();
        String login = campoLogin.getText();
        String email = campoEmail.getText();
        String cpf = campoCpf.getText();
        String senha = campoSenha.getText();

    }
}
