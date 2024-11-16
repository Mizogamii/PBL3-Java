package projeto.frontend.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

public class NavegacaoTela {

    public static void trocarTela(Stage stage, String fxmlPath, String title) {
        if(stage == null){
            System.out.println("Stage nulo");
        }
        try {
            FXMLLoader loader = new FXMLLoader(NavegacaoTela.class.getResource(fxmlPath));
            Parent root = loader.load();

            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        }catch(IOException e) {
            e.printStackTrace();
            showErrorMessage("Erro ao carregar a tela: " + e.getMessage());
        }
    }

    public static void showSuccessMessage(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void showErrorMessage(String mensagem) {
        showMessage(Alert.AlertType.ERROR, "Erro", mensagem);
    }

    public static void showMessage(Alert.AlertType alertType, String titulo, String mensagem) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
