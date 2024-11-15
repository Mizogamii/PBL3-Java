package view;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaLogin {

    @FXML
    private TextField campoLogin;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private Label labelAbriTelaCadastro;

    @FXML
    private void handleLogin() {
        String login = campoLogin.getText();
        String senha = campoSenha.getText();

        if (isValidLogin(login, senha)) {
            // Lógica de navegação para a próxima tela
            System.out.println("Login bem-sucedido!");

            // Por exemplo, carregar a tela de eventos
        } else {
            showErrorMessage("Nome de usuário ou senha inválidos.");
        }
    }

    private boolean isValidLogin(String username, String password) {
        return "admin".equals(username) && "senha123".equals(password); // Lógica de validação
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro de Login");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
