package projeto.frontend.view.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto.backend.controller.ControllerUsuario;
import projeto.backend.model.Usuario;
import projeto.frontend.utils.NavegacaoTela;

import java.io.IOException;

public class TelaLoginController {

    private ControllerUsuario controllerUsuario = new ControllerUsuario();

    @FXML
    private TextField campoLogin;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private Label labelAbrirTelaCadastro;

    @FXML
    private void abrirTelaLogin() {
        String login = campoLogin.getText();
        String senha = campoSenha.getText();

        if(controllerUsuario == null){
            NavegacaoTela.showErrorMessage("Erro: Controller não foi inicializado");
            return;
        }

        try{
            Usuario usuario = controllerUsuario.fazerLogin(login, senha);
            abrirTelaPrincipal(usuario);
        }catch (IllegalArgumentException e){
            NavegacaoTela.showErrorMessage(e.getMessage());
        }
    }

    private void abrirTelaPrincipal(Usuario usuario) {
        Stage stage = (Stage) campoLogin.getScene().getWindow();
        NavegacaoTela.trocarTela(stage, "/fxml/TelaPrincipal.fxml", "Tela Principal");
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
}
