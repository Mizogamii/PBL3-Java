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
package projeto.frontend.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import projeto.frontend.view.controllers.PrincipalUsuarioLogadoController;

import java.io.IOException;

/**
 * Classe utilitária responsável por gerenciar a navegação entre as telas da aplicação.
 */
public class NavegacaoTela {

    /** Instância principal do Stage para controle das telas. */
    public static Stage primaryStage;
    /**
     * Define o stage principal da aplicação.
     *
     * @param stage Stage principal a ser definido.
     */
    public static void setPrimaryStage(Stage stage){
        primaryStage = stage;
    }

    /**
     * Realiza a troca de tela.
     *
     * @param stage Stage onde a nova tela será carregada.
     * @param fxmlPath Caminho do arquivo FXML da nova tela.
     * @param title Título da nova tela.
     */
    public static void trocarTela(Stage stage, String fxmlPath, String title) {
        if (stage == null) {
            System.out.println("Stage nulo");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(NavegacaoTela.class.getResource(fxmlPath));
            Parent root = loader.load();

            if (fxmlPath.equals("/fxml/TelaLogada.fxml")) {
                PrincipalUsuarioLogadoController controller = loader.getController();
                controller.atualizarNotificacoes();
            }

            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorMessage("Erro ao carregar a tela: " + e.getMessage());
        }
    }

    /**
     * Exibe uma mensagem de sucesso.
     *
     * @param titulo Título da mensagem.
     * @param mensagem Conteúdo da mensagem de sucesso.
     */
    public static void showSuccessMessage(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
     * Exibe uma mensagem de erro.
     *
     * @param mensagem Conteúdo da mensagem de erro.
     */
    public static void showErrorMessage(String mensagem) {
        showMessage(Alert.AlertType.ERROR, "Erro", mensagem);
    }

    /**
     * Exibe uma mensagem genérica.
     *
     * @param alertType Tipo de alerta (INFORMATION, ERROR, WARNING, etc.).
     * @param titulo Título da mensagem.
     * @param mensagem Conteúdo da mensagem.
     */
    public static void showMessage(Alert.AlertType alertType, String titulo, String mensagem) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
     * Volta para a tela inicial da aplicação.
     * Se o usuário estiver logado, carrega a tela logada, caso contrário, carrega a tela principal.
     */
    public static void voltarTelaInicial(){
        String telaInicial = null;
        if(primaryStage != null){
            UsuarioLogado.isLogado();
            if(UsuarioLogado.isLogado()){
                telaInicial = "/fxml/TelaLogada.fxml";
            }else{
                telaInicial = "/fxml/TelaPrincipal.fxml";
            }
            trocarTela(primaryStage, telaInicial, "Tela Principal");
        }else{
            showErrorMessage("ERRO! Não há tela principal definida.");
        }
    }

    /**
     * Fecha a tela atual utilizando comandos pelo teclado (ainda não implementado).
     *
     * @param scene Cena onde o evento de fechamento será tratado.
     */
    public static void fecharTelaPorTeclado(Scene scene){

    }
}
