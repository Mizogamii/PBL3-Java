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

import java.io.IOException;

public class TelaLoginController {

    private ControllerUsuario controllerUsuario;

    public void setControllerUsuario(ControllerUsuario controllerUsuario){
        this.controllerUsuario = controllerUsuario;
    }

    @FXML
    private TextField campoLogin;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private Label labelAbriTelaCadastro;

    @FXML
    private void handleLogin() {
        String login = campoLogin.getText();
        String senha = campoSenha.getText();
        try{
            Usuario usuario = controllerUsuario.fazerLogin(login, senha);
            abrirTelaPrincipal(usuario);
        }catch (IllegalArgumentException e){
            showErrorMessage(e.getMessage());
        }

    }

    private void abrirTelaPrincipal(Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/TelaPrincipal.fxml")));
            Parent root = loader.load();

            Stage telaPrincipal = new Stage();
            telaPrincipal.setTitle("Tela principal");
            telaPrincipal.setScene(new Scene(root));
            telaPrincipal.show();
        }catch (IOException e){
            e.printStackTrace();
            showErrorMessage("Erro! Tente novamente.");
        }
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro de Login");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    public void abrirTelaCadastro(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaCadastro.fxml"));
            TelaCadastroController controller = new TelaCadastroController();
            loader.setController(controller);
            controller.setControllerUsuario(controllerUsuario);

            Parent root = loader.load();
            Stage novaTela = new Stage();
            novaTela.setTitle("Cadastro");
            novaTela.setScene(new Scene(root));
            novaTela.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
