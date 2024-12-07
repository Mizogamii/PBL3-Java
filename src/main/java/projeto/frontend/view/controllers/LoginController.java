package projeto.frontend.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projeto.backend.controller.ControllerUsuario;
import projeto.backend.model.Usuario;
import projeto.frontend.utils.NavegacaoTela;
import projeto.frontend.utils.UsuarioLogado;

public class LoginController {

    private ControllerUsuario controllerUsuario = new ControllerUsuario();

    @FXML
    private TextField campoLogin;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private Label labelAbrirTelaCadastro;

    @FXML
    private void initialize(){
        campoLogin.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                KeyCodeCombination fecharPorTeclado = new KeyCodeCombination(KeyCode.W, KeyCodeCombination.CONTROL_DOWN);
                newScene.setOnKeyPressed(event -> {
                    if(fecharPorTeclado.match(event)){
                        fecharTela();
                    }
                });
            }
        });
    }

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
            UsuarioLogado.login(usuario);
            abrirTelaPrincipal(usuario);
            System.out.println(usuario.getNome());
            controllerUsuario.notificarEventoProximo(usuario);
            controllerUsuario.verificarNotificacoes(usuario);
            //controllerUsuario.atualizarRecibo(usuario);


        }catch (IllegalArgumentException e){
            NavegacaoTela.showErrorMessage(e.getMessage());
        }

    }

    private void abrirTelaPrincipal(Usuario usuario) {
        Stage stage = (Stage) campoLogin.getScene().getWindow();

        NavegacaoTela.trocarTela(stage, "/fxml/TelaLogada.fxml", "Tela Principal");
    }


    @FXML
    public void abrirTelaCadastro() {
        System.out.println("Label: " + labelAbrirTelaCadastro);
        Stage stage = null;
        if (labelAbrirTelaCadastro == null) {
            System.out.println("labelAbriTelaCadastro está null!");
        } else {
            stage = (Stage) labelAbrirTelaCadastro.getScene().getWindow();
            System.out.println("Teste login");
        }

        NavegacaoTela.trocarTela(stage, "/fxml/TelaCadastro.fxml", "Cadastro");
    }

    private void fecharTela(){
        Stage stage = (Stage) campoLogin.getScene().getWindow();
        stage.close();
    }

    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

}

