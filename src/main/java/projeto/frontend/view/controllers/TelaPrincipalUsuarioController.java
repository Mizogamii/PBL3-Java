package projeto.frontend.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import projeto.frontend.utils.NavegacaoTela;

public class TelaPrincipalUsuarioController {

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
}
