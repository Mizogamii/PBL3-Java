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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projeto.backend.controller.ControllerUsuario;
import projeto.backend.model.Usuario;
import projeto.frontend.utils.Acessibilidade;
import projeto.frontend.utils.NavegacaoTela;

/**
 * Controlador responsável pelo gerenciamento das ações na tela de cadastro de usuário.
 * Permite cadastrar novos usuários, retornar à tela de login e limpar os campos de entrada.
 */
public class CadastroController {

    /** Controlador responsável pela lógica de usuários. */
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
    private Label voltarTelaInicial;


    @FXML
    public void initialize() {
        if (voltarParaLogin != null) {
            voltarParaLogin.setFocusTraversable(true);
            Acessibilidade.configurarEstiloFoco(voltarParaLogin);
            voltarParaLogin.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    voltandoLogin();
                }
            });
        }

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
     * Método chamado ao pressionar o botão de salvar.
     * Recebe os dados do formulário, tenta cadastrar o usuário e exibe mensagens de sucesso ou erro.
     */
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

    /**
     * Retorna à tela de login ao pressionar o botão correspondente.
     */
    @FXML
    public void voltandoLogin() {
        Stage stage = (Stage) campoNome.getScene().getWindow();
        if (stage != null) {
            NavegacaoTela.trocarTela(stage, "/fxml/TelaLogin.fxml", "Login");
        } else {
            NavegacaoTela.showErrorMessage("Erro: Não foi possível acessar a janela de login.");
        }
    }
    /**
     * Limpa os campos de entrada de texto na tela de cadastro.
     */
    private void limparCampos() {
        campoNome.clear();
        campoLogin.clear();
        campoEmail.clear();
        campoCpf.clear();
        campoSenha.clear();
    }

    /**
     * Retorna à tela inicial ao clicar no elemento correspondente.
     *
     * @param mouseEvent Evento de clique do mouse.
     */
    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }
}


