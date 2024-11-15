package view;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class TelaLogin {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    // Este método é chamado quando o botão "Entrar" é clicado
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Lógica para enviar os dados para o backend e validar o login
        if (isValidLogin(username, password)) {
            // Redireciona para a próxima tela ou exibe mensagem de sucesso
        } else {
            // Exibe mensagem de erro
        }
    }

    private boolean isValidLogin(String username, String password) {
        // Aqui você chama o backend para validar o login
        // Retorne true se for válido, false se não for
        return true; // apenas um exemplo
    }
}
