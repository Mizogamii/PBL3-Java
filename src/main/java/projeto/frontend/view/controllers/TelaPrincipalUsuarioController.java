package projeto.frontend.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projeto.frontend.utils.NavegacaoTela;
import projeto.frontend.utils.UsuarioLogado;

import java.io.IOException;

public class TelaPrincipalUsuarioController {

    @FXML
    public Label labelAbrirTelaLogin;

    @FXML
    private Label labelAbrirTelaListar;

    @FXML
    private Label labelAbrirTelaCadastro;

    @FXML
    private Label abrirTelaLogin;

   /* @FXML
    public void initialize() {
        if (abrirTelaLogin != null) {
            System.out.println("A variável 'abrirTelaLogin' foi inicializada com sucesso!");
        } else {
            System.out.println("Erro: o objeto 'abrirTelaLogin' não foi injetado.");
        }
    }*/

    @FXML
    public void abrirTelaListar(){
        Stage stage = null;
        if (labelAbrirTelaListar == null) {
            System.out.println("labelAbriTelaListar está null!");
        } else {
            stage = (Stage) labelAbrirTelaListar.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaListarEvento.fxml", "Listar dados");
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

    @FXML
    public void abrirTelaLogin(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println("Label: " + labelAbrirTelaLogin);
        Stage stage = null;
        if (labelAbrirTelaLogin == null) {
            System.out.println("labelAbriTelaCadastro está null!");
        } else {
            stage = (Stage) labelAbrirTelaLogin.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaLogin.fxml", "Login");
    }


}
