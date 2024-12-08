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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projeto.backend.controller.ControllerEvento;
import projeto.backend.model.Evento;
import projeto.frontend.utils.NavegacaoTela;

import java.io.IOException;
import java.util.List;

/**
 * Controlador responsável pela tela de listagem de eventos.
 * Permite que o usuário visualize todos os eventos cadastrados,
 * filtre por categoria e acesse mais detalhes de um evento específico.
 */
public class ListarEventosController {

    private ControllerEvento controllerEvento = new ControllerEvento();

    @FXML
    public Label voltarTelaInicial;

    @FXML
    private TableView<Evento> tabelaEventos;

    @FXML
    private TableColumn<Evento, String> colunaNomeEvento;

    @FXML
    private TableColumn<Evento, String> colunaCategoria;

    @FXML
    private TableColumn<Evento, String> colunaDescricao;

    @FXML
    private TableColumn<Evento, String> colunaData;

    @FXML
    private ComboBox<String> filtroCategoria;

    @FXML
    private TableColumn<Evento, Integer> colunaQuantidadeAssentosDisponiveis;

    @FXML
    public TableColumn<Evento, Double> colunaPreco;

    private final List<Evento> eventos = controllerEvento.listarTodosOsEventos();

    /**
     * Inicializa a tabela de eventos, preenchendo as colunas com as informações dos eventos
     * e configurando o filtro de categoria. Também define a ação para abrir a tela de detalhes
     * de um evento ao ser clicado.
     */
    @FXML
    public void initialize() {
        //Inserindo dados nas colunas da tabela
        colunaNomeEvento.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaQuantidadeAssentosDisponiveis.setCellValueFactory(new PropertyValueFactory<>("quantidadeAssentosDisponiveis"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        // Preenche as categorias no ComboBox
        ObservableList<Evento> listaEventos = FXCollections.observableArrayList(eventos);
        tabelaEventos.setItems(listaEventos);

        ObservableList<String> categorias = FXCollections.observableArrayList();
        eventos.forEach(evento -> {
            if (!categorias.contains(evento.getCategoria())) {
                categorias.add(evento.getCategoria());
            }
        });
        filtroCategoria.setItems(categorias);
        filtroCategoria.getItems().add(0, "Todas");
        filtroCategoria.getSelectionModel().select(0);

        //Clicar duas vezes no evento para mostrar detalhes do evento
        tabelaEventos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Evento eventoSelecionado = tabelaEventos.getSelectionModel().getSelectedItem();
                if (eventoSelecionado != null) {
                    try {
                        abrirTelaEvento(eventoSelecionado);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    /**
     * Volta para a tela inicial quando o botão de voltar é pressionado.
     *
     * @param mouseEvent O evento de clique do mouse.
     */
    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

    /**
     * Filtra os eventos por categoria selecionada no ComboBox.
     *
     * @param actionEvent O evento de ação gerado pelo ComboBox de filtro.
     */
    public void filtrarCategoria(ActionEvent actionEvent) {
        String categoriaSelecionada = filtroCategoria.getValue();

        if ("Todas".equals(categoriaSelecionada)) {
            tabelaEventos.setItems(FXCollections.observableArrayList(eventos));
        } else {
            ObservableList<Evento> eventosFiltrados = FXCollections.observableArrayList();
            for (Evento evento : eventos) {
                if (evento.getCategoria().equals(categoriaSelecionada)) {
                    eventosFiltrados.add(evento);
                }
            }
            tabelaEventos.setItems(eventosFiltrados);
        }
    }

    /**
     * Abre a tela de detalhes de um evento selecionado.
     *
     * @param evento O evento para o qual os detalhes devem ser carregados.
     * @throws IOException Caso ocorra um erro ao carregar a tela de detalhes.
     */
    public void abrirTelaEvento(Evento evento) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaEventoIndividual.fxml"));
        Parent root = loader.load();

        TelaEventoController telaEvento = loader.getController();

        telaEvento.initialize(evento);

        Stage stage = (Stage) tabelaEventos.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

}
