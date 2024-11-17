package projeto.frontend.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import projeto.backend.controller.ControllerUsuario;
import projeto.backend.model.Usuario;
import projeto.frontend.utils.NavegacaoTela;

public class TelaEdicaoController {
   private ControllerUsuario controllerUsuario = new ControllerUsuario();

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoLogin;

    @FXML
    private TextField campoEmail;

    @FXML
    private PasswordField campoSenha;

    public void voltarTela(MouseEvent mouseEvent) {
     NavegacaoTela.voltarTelaInicial();
    }

    /*@FXML
    private void botaoAlterarDados() {
        String nome = campoNome.getText();
        String login = campoLogin.getText();
        String email = campoEmail.getText();
        String senha = campoSenha.getText();

        System.out.println("Dados recebidos: " + nome + ", " + login + ", " + email + ", " + senha);
        if(controllerUsuario == null){
            NavegacaoTela.showErrorMessage("Erro: Controller não foi inicializado");
            return;
        }

        try{
            Usuario usuario = controllerUsuario.editarEmail(login, senha,email);
            if(usuario != null){
                NavegacaoTela.showSuccessMessage("Edição Realizado", "Edição realizado com sucesso!");
            }else{
                NavegacaoTela.showErrorMessage("Erro ao editar. Tente novamente");
            }
        }catch(IllegalArgumentException e){
            NavegacaoTela.showErrorMessage("Erro! Tente novamente.");
        }
    }*/
}
