package projeto.frontend.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import projeto.backend.controller.ControllerUsuario;
import projeto.backend.model.Usuario;
import projeto.frontend.utils.NavegacaoTela;
import projeto.frontend.utils.UsuarioLogado;

public class EdicaoController {
    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoLogin;

    @FXML
    private TextField campoEmail;

    @FXML
    private PasswordField campoSenha;

    private ControllerUsuario controllerUsuario = new ControllerUsuario();

    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }


    @FXML
    private void botaoAlterarDados() {
        String newNome = campoNome.getText();
        String newLogin = campoLogin.getText();
        String newEmail = campoEmail.getText();
        String newSenha = campoSenha.getText();

        Usuario usuario = UsuarioLogado.getUsuarioLogado();

        if (newNome.isEmpty() && newLogin.isEmpty() && newEmail.isEmpty() && newSenha.isEmpty()) {
            NavegacaoTela.showErrorMessage("ERRO! Preencha pelo menos uma das lacunas.");
            return;
        }

        if (controllerUsuario == null) {
            NavegacaoTela.showErrorMessage("ERRO! Controller não foi inicializado.");
            return;
        }
        try {
            if (!newNome.isEmpty()) {
                controllerUsuario.editarNome(usuario, newNome);
            }
            if (!newLogin.isEmpty()) {
                controllerUsuario.editarLogin(usuario, newLogin);
            }
            if (!newEmail.isEmpty()) {
                controllerUsuario.editarEmail(usuario, newEmail);
            }
            if (!newSenha.isEmpty()) {
                controllerUsuario.editarSenha(usuario, newSenha);
            }
            NavegacaoTela.showSuccessMessage("Dados Editados", "Dados editados com sucesso!");

        } catch (IllegalArgumentException e) {
            NavegacaoTela.showErrorMessage("Erro! " + e.getMessage());
        }
    }
}
