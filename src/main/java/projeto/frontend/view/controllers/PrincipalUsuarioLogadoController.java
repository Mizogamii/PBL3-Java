package projeto.frontend.view.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projeto.frontend.utils.NavegacaoTela;
import projeto.frontend.utils.UsuarioLogado;

import java.io.IOException;
import java.util.List;

public class PrincipalUsuarioLogadoController {

    @FXML
    private Label labelAbrirTelaEdicao;

    @FXML
    private Label labelAbrirTelaListar;

    @FXML
    private Label labelAvaliarEvento;

    @FXML
    private VBox notificacoesBox;

    @FXML
    public void initialize() {
        List<String> notificacoesIniciais = List.of("Bem-vindo ao sistema!", "Novo evento disponível.");
        carregarNotificacoes(notificacoesIniciais);
    }

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

    public void abrirTelaComprar(MouseEvent mouseEvent) {
        Stage stage = null;
        if (labelAbrirTelaListar == null) {
            System.out.println("labelAbriTelaComprar está null!");
        } else {
            stage = (Stage) labelAbrirTelaEdicao.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaComprar.fxml", "Comprar Ingresso");
    }

    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

    public void comprasFeitas(MouseEvent mouseEvent) {
        Stage stage = null;
        if (labelAbrirTelaListar == null) {
            System.out.println("labelComprasFeitas está null!");
        } else {
            stage = (Stage) labelAbrirTelaEdicao.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaComprasFeitas.fxml", "Compras Realizadas");
    }

    public void fazerFeedBack(MouseEvent mouseEvent) throws IOException {
        Stage stage = null;
        if (labelAvaliarEvento == null) {
            System.out.println("labelFeedBack está null!");
        } else {
            stage = (Stage) labelAvaliarEvento.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaListaEventosFeedBack.fxml", "Compras Realizadas");
    }

    public void carregarNotificacoes(List<String> notificacoes){
        for (String mensagem : notificacoes) {
            Label notificacaoLabel = new Label(mensagem);
            notificacaoLabel.setStyle("-fx-padding: 10; -fx-font-size: 14; -fx-background-color: #f0f0f0; -fx-border-color: #ccc; -fx-border-radius: 5; -fx-background-radius: 5;");
            notificacaoLabel.setMaxWidth(Double.MAX_VALUE);
            notificacoesBox.getChildren().add(notificacaoLabel);
            ScrollPane scrollPane = (ScrollPane) notificacoesBox.getParent();
            scrollPane.setVvalue(1.0);
        }
    }
}
