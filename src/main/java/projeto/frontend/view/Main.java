package projeto.frontend.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.frontend.utils.NavegacaoTela;
import projeto.frontend.utils.UsuarioLogado;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            NavegacaoTela.setPrimaryStage(primaryStage);
            String telaInicial = null;
            if(UsuarioLogado.isLogado()){
                telaInicial = "/fxml/TelaLogada.fxml";
            }else{
                telaInicial = "/fxml/TelaPrincipal.fxml";
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(telaInicial));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Tela Principal");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
