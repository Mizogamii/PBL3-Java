package projeto.frontend.view.controllers;

import eu.hansolo.tilesfx.Test;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import projeto.backend.controller.ControllerUsuario;
import projeto.backend.model.Usuario;

public class TelaCadastroController {

    private ControllerUsuario controllerUsuario = new ControllerUsuario();

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

        System.out.println("Dados recebidos: " + nome + ", " + login + ", " + email + ", " + cpf + ", " + senha);

        if(controllerUsuario == null){
            showErrorMessage("Erro: Controller não foi inicializado");
            return;
        }

        try{
            Usuario usuario = controllerUsuario.cadastrarUsuario(login, senha,nome,cpf,email);
            if(usuario != null){
                showSuccessMessage("Cadastro realizado com sucesso!");
                limparCampos();
            }else{
                showErrorMessage("Erro ao cadastrar. Tente novamente");
            }
        }catch(IllegalArgumentException e){
            showErrorMessage("Erro! Tente novamente.");
        }
    }

    public void initialize(){
        System.out.println("Iniciando nova tela");
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro ao realizar cadastro");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro Realizado");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void limparCampos() {
        campoNome.clear();
        campoLogin.clear();
        campoEmail.clear();
        campoCpf.clear();
        campoSenha.clear();
    }
}


