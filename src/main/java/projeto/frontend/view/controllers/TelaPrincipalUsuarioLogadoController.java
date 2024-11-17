package projeto.frontend.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import projeto.frontend.utils.NavegacaoTela;
import projeto.frontend.utils.UsuarioLogado;

public class TelaPrincipalUsuarioLogadoController {

    @FXML
    public Label labelAbrirTelaLogin;

    @FXML
    private Label labelAbrirTelaEdicao;

    @FXML
    private Label labelAbrirTelaListar;

    @FXML
    public void abrirTelaEditar(){
        Stage stage = null;
        if (labelAbrirTelaEdicao == null) {
            System.out.println("labelAbriTelaEdicao está null!");
        } else {
            stage = (Stage) labelAbrirTelaEdicao.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaEdicao.fxml", "Editar Dados");
    }

    @FXML
    public void abrirTelaListar(){
        Stage stage = null;
        if (labelAbrirTelaListar == null) {
            System.out.println("labelAbriTelaListar está null!");
        } else {
            stage = (Stage) labelAbrirTelaEdicao.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaListarEvento.fxml", "Listar dados");
    }

    @FXML
    public void comprarIngresso(){

    }

    @FXML
    public void sairDaConta(){
        UsuarioLogado.sairConta();
        Stage stage = null;
        if (labelAbrirTelaListar == null) {
            System.out.println("labelAbriTelaPrincipal está null!");
        } else {
            stage = (Stage) labelAbrirTelaEdicao.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaPrincipal.fxml", "Tela Principal");
    }
}
