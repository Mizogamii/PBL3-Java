/*******************************************************************************************
 Autor: Sayumi Mizogami Santana
 Componente Curricular: EXA 863 - MI Programação
 Concluido em: 08/12/2024
 Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 *******************************************************************************************/
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
import projeto.frontend.utils.Acessibilidade;
import projeto.frontend.utils.NavegacaoTela;
import projeto.frontend.utils.UsuarioLogado;

/**
 * Controlador responsável por gerenciar a tela de login.
 * Ele permite que o usuário insira suas credenciais, efetue login,
 * acesse a tela principal, ou navegue para a tela de cadastro.
 */
public class LoginController {
    /**
     * Instância do controlador de usuários responsável pela lógica de autenticação.
     */
    private ControllerUsuario controllerUsuario = new ControllerUsuario();

    @FXML
    private TextField campoLogin;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private Label labelAbrirTelaCadastro;

    @FXML
    private Label voltarTelaInicial;

    /**
     * Inicializa o controlador configurando atalhos e eventos necessários na cena.
     */
    @FXML
    private void initialize(){
        if (voltarTelaInicial != null) {
            voltarTelaInicial.setFocusTraversable(true);
            Acessibilidade.configurarEstiloFoco(voltarTelaInicial);
            voltarTelaInicial.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    voltarTela(null);
                }
            });
        }
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

    /**
     * Realiza a autenticação do usuário e navega para a tela principal, caso o login seja bem-sucedido.
     * Exibe uma mensagem de erro caso as credenciais sejam inválidas.
     */
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
            controllerUsuario.notificarEventoProximo(usuario);
        }catch (IllegalArgumentException e){
            NavegacaoTela.showErrorMessage(e.getMessage());
        }

    }

    /**
     * Navega para a tela principal após o login bem-sucedido.
     *
     * @param usuario o usuário autenticado que acessará a tela principal.
     */
    private void abrirTelaPrincipal(Usuario usuario) {
        Stage stage = (Stage) campoLogin.getScene().getWindow();

        NavegacaoTela.trocarTela(stage, "/fxml/TelaLogada.fxml", "Tela Principal");
    }

    /**
     * Navega para a tela de cadastro de usuários.
     */
    @FXML
    public void abrirTelaCadastro() {
        Stage stage = null;
        if (labelAbrirTelaCadastro != null) {
            stage = (Stage) labelAbrirTelaCadastro.getScene().getWindow();
        }

        NavegacaoTela.trocarTela(stage, "/fxml/TelaCadastro.fxml", "Cadastro");
    }

    /**
     * Fecha a tela de login.
     */
    private void fecharTela(){
        Stage stage = (Stage) campoLogin.getScene().getWindow();
        stage.close();
    }

    /**
     * Retorna para a tela inicial ao clicar no botão ou label correspondente.
     *
     * @param mouseEvent o evento de clique do mouse.
     */
    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

}

