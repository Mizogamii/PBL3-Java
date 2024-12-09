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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import projeto.backend.controller.ControllerEvento;
import projeto.backend.model.Evento;
import projeto.frontend.utils.NavegacaoTela;

import java.util.List;

/**
 * Controller responsável pela interface principal do usuário no sistema.
 * Gerencia a interação com a tabela de eventos e a navegação para outras telas.
 */
public class PrincipalUsuarioController {
    private ControllerEvento controllerEvento = new ControllerEvento();

    @FXML
    public Label labelAbrirTelaLogin;

    @FXML
    private Label labelAbrirTelaListar;

    @FXML
    private Label labelAbrirTelaCadastro;

    @FXML
    private Label abrirTelaLogin;

    @FXML
    private TableView<Evento> tabelaEventos;

    @FXML
    private TableColumn<Evento, String> colunaNomeEvento;

    @FXML
    private TableColumn<Evento, String> colunaCategoria;

    @FXML
    private TableColumn<Evento, String> colunaData;

    private final List<Evento> eventos = controllerEvento.listarEventosDisponiveis();

    /**
     * Inicializa os componentes da interface e configura os dados da tabela de eventos.
     */
    @FXML
    public void initialize() {
        colunaNomeEvento.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("dataFormatada"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        ObservableList<Evento> listaEventos = FXCollections.observableArrayList(eventos);

        tabelaEventos.setItems(listaEventos);

        tabelaEventos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Evento eventoSelecionado = tabelaEventos.getSelectionModel().getSelectedItem();
                if (eventoSelecionado != null) {
                    abrirDetalhesEventos(eventoSelecionado);
                }
            }
        });

        tabelaEventos.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Evento eventoSelecionado = tabelaEventos.getSelectionModel().getSelectedItem();
                if (eventoSelecionado != null) {
                    abrirDetalhesEventos(eventoSelecionado);
                }
            }
        });
    }

    /**
     * Abre a tela de listagem de eventos disponíveis.
     */
    @FXML
    public void abrirTelaListar(){
        Stage stage = null;
        if (labelAbrirTelaListar != null) {
            stage = (Stage) labelAbrirTelaListar.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaListarEvento.fxml", "Listar dados");
    }

    /**
     * Abre a tela de cadastro de eventos.
     */
    @FXML
    public void abrirTelaCadastro() {
        Stage stage = null;
        if (labelAbrirTelaCadastro != null) {
            stage = (Stage) labelAbrirTelaCadastro.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaCadastro.fxml", "Cadastro");
    }

    /**
     * Abre a tela de login do sistema.
     *
     * @param mouseEvent Evento do mouse que acionou a ação.
     */
    @FXML
    public void abrirTelaLogin(javafx.scene.input.MouseEvent mouseEvent) {
        Stage stage = null;
        if (labelAbrirTelaLogin != null) {
            stage = (Stage) labelAbrirTelaLogin.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaLogin.fxml", "Login");
    }

    /**
     * Exibe os detalhes de um evento selecionado.
     *
     * @param evento Evento cuja descrição será exibida.
     */
    public void abrirDetalhesEventos(Evento evento){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detalhes do Evento");
        alert.setHeaderText("Notificação");

        String detalhes = "Evento: " + evento.getNome() +
                "\nDescrição: " + evento.getDescricao() +
                "\nData do evento: " + evento.getDataFormatada() +
                "\nCategoria: " + evento.getCategoria() +
                "\nPreço: R$" + evento.getPreco();

        alert.setContentText(detalhes);
        alert.showAndWait();
    }

}
