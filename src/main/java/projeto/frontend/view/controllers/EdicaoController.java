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
import javafx.scene.input.MouseEvent;
import projeto.backend.controller.ControllerUsuario;
import projeto.backend.model.Usuario;
import projeto.frontend.utils.NavegacaoTela;
import projeto.frontend.utils.UsuarioLogado;
import projeto.frontend.utils.Acessibilidade;
import javafx.scene.input.KeyCode;

/**
 * Controlador da tela de edição de dados do usuário, permitindo que o usuário altere
 * seu nome, login, email e senha.
 */
public class EdicaoController {
    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoLogin;

    @FXML
    private TextField campoEmail;

    @FXML
    private PasswordField campoSenha;

    @FXML
    public Label voltarTelaInicial;

    private ControllerUsuario controllerUsuario = new ControllerUsuario();

    /**
     * Volta para a tela inicial quando o botão de voltar é pressionado.
     *
     * @param mouseEvent O evento de clique do mouse.
     */
    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

    @FXML
    public void initialize() {
        if (voltarTelaInicial != null) {
            voltarTelaInicial.setFocusTraversable(true);
            Acessibilidade.configurarEstiloFoco(voltarTelaInicial);
            voltarTelaInicial.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    voltarTela(null);
                }
            });
        }
    }

    /**
     * Realiza a alteração dos dados do usuário (nome, login, email, senha) com base
     * nos valores preenchidos nos campos de texto. Se os campos estiverem vazios,
     * exibe uma mensagem de erro.
     *
     * @throws IllegalArgumentException Se algum dado inválido for fornecido.
     */
    @FXML
    private void botaoAlterarDados() {
        //Armazena o que o usuário digitou
        String newNome = campoNome.getText();
        String newLogin = campoLogin.getText();
        String newEmail = campoEmail.getText();
        String newSenha = campoSenha.getText();

        //Salva o usuário logado
        Usuario usuario = UsuarioLogado.getUsuarioLogado();

        if (newNome.isEmpty() && newLogin.isEmpty() && newEmail.isEmpty() && newSenha.isEmpty()) {
            NavegacaoTela.showErrorMessage("ERRO! Preencha pelo menos uma das lacunas.");
            return;
        }

        if (controllerUsuario == null) {
            NavegacaoTela.showErrorMessage("ERRO! Controller não foi inicializado.");
            return;
        }
        //Edição de dados do usuário
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
