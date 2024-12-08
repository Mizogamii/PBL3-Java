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
package projeto.frontend.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.frontend.utils.NavegacaoTela;
import projeto.frontend.utils.UsuarioLogado;

/**
 * Classe principal, responsável por iniciar a interface gráfica e carregar a tela inicial.
 */
public class Main extends Application {
    /**
     * Inicia a aplicação, verificando se o usuário está logado e carregando a tela correspondente.
     *
     * @param primaryStage o estágio principal da aplicação.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            //Estágio inicial
            NavegacaoTela.setPrimaryStage(primaryStage);

            //Escolha de tela inicial(logado ou não)
            String telaInicial = null;
            if(UsuarioLogado.isLogado()){
                telaInicial = "/fxml/TelaLogada.fxml"; //Carrega tela de usuário logada
            }else{
                telaInicial = "/fxml/TelaPrincipal.fxml"; //Carrega tela principal sem usuário logado
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
    /**
     * Método principal que inicia a aplicação.
     *
     * @param args argumentos passados para a aplicação (não utilizados aqui).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
