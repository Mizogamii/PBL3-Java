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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import projeto.backend.model.Evento;
import projeto.backend.model.Ingresso;
import projeto.backend.model.Usuario;
import projeto.frontend.utils.NavegacaoTela;

import projeto.backend.controller.ControllerUsuario;
import projeto.frontend.utils.UsuarioLogado;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

/**
 * Controlador da tela de lista de eventos com feedback. Permite visualizar os eventos
 * em que o usuário participou e acessar a tela de comentários para dar feedback.
 */
public class ListaEventosFeedBack {
    @FXML
    public Label voltarTelaInicial;

    @FXML
    private TableView<Ingresso> tabelaEventoParticipado;

    @FXML
    private TableColumn<Ingresso, String> colunaNomeEvento;

    @FXML
    private TableColumn<Ingresso, String> colunaCategoria;

    @FXML
    private TableColumn<Ingresso, String> colunaDescricao;

    @FXML
    private TableColumn<Ingresso, String> colunaData;

    @FXML
    private ComboBox<String> filtroCategoria;

    private ControllerUsuario controllerUsuario = new ControllerUsuario();

    private List<Ingresso> ingressos = controllerUsuario.listarEventosParticipados(UsuarioLogado.getUsuarioLogado());

    /**
     * Inicializa a tabela de eventos participados, associando as colunas aos dados do ingresso
     * e configurando o filtro de categoria.
     */
    @FXML
    public void initialize() {
        //Inserindo dados nas colunas da tabela
        colunaNomeEvento.setCellValueFactory(new PropertyValueFactory<>("nomeEvento"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaEvento"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricaoEvento"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("dataEvento"));

        ObservableList<Ingresso> listaIngressos = FXCollections.observableArrayList(ingressos);
        tabelaEventoParticipado.setItems(listaIngressos);

        //Ação ao clicar duas vezes no evento
        tabelaEventoParticipado.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Ingresso ingressoSelecionado = tabelaEventoParticipado.getSelectionModel().getSelectedItem();
                if (ingressoSelecionado != null) {
                    try {
                        comentar(ingressoSelecionado);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    /**
     * Abre a tela de comentários para o evento selecionado.
     *
     * @param ingresso O ingresso selecionado, representando o evento a ser comentado.
     * @throws IOException Caso ocorra um erro ao carregar a tela de comentários.
     */
    public void comentar(Ingresso ingresso) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaComentario.fxml"));
            Parent root = loader.load();

            ComentarioController telaComentarioController = loader.getController();

            telaComentarioController.setIngresso(ingresso);

            Stage stage = (Stage) tabelaEventoParticipado.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch(IOException e) {
            throw new IOException(e);
        }
    }

    /**
     * Retorna para a tela inicial quando o botão de voltar é pressionado.
     *
     * @param mouseEvent O evento de clique do mouse.
     */
    public void voltarTela(javafx.scene.input.MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }
}


