package projeto.frontend.view.controllers;

import eu.hansolo.tilesfx.Test;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projeto.backend.controller.ControllerUsuario;
import projeto.backend.model.Usuario;
import projeto.frontend.utils.NavegacaoTela;

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
    private Label voltarParaLogin;

    @FXML
    private void botaoSalvar() {
        String nome = campoNome.getText();
        String login = campoLogin.getText();
        String email = campoEmail.getText();
        String cpf = campoCpf.getText();
        String senha = campoSenha.getText();

        System.out.println("Dados recebidos: " + nome + ", " + login + ", " + email + ", " + cpf + ", " + senha);
        if(controllerUsuario == null){
            NavegacaoTela.showErrorMessage("Erro: Controller não foi inicializado");
            return;
        }

        try{
            Usuario usuario = controllerUsuario.cadastrarUsuario(login, senha,nome,cpf,email);
            if(usuario != null){
                NavegacaoTela.showSuccessMessage("Cadastro Realizado", "Cadastro realizado com sucesso!");
                limparCampos();
            }else{
                NavegacaoTela.showErrorMessage("Erro ao cadastrar. Tente novamente");
            }
        }catch(IllegalArgumentException e){
            NavegacaoTela.showErrorMessage("Erro! Tente novamente.");
        }
    }


    @FXML
    public void voltandoLogin() {
        Stage stage = (Stage) campoNome.getScene().getWindow();
        if (stage != null) {
            NavegacaoTela.trocarTela(stage, "/fxml/TelaLogin.fxml", "Login");
        } else {
            NavegacaoTela.showErrorMessage("Erro: Não foi possível acessar a janela de login.");
        }
    }

    private void limparCampos() {
        campoNome.clear();
        campoLogin.clear();
        campoEmail.clear();
        campoCpf.clear();
        campoSenha.clear();
    }

    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }
}


