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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projeto.backend.controller.ControllerEvento;
import projeto.backend.model.Comentario;
import projeto.backend.model.Evento;
import projeto.backend.model.Notificacoes;
import projeto.backend.model.Usuario;
import projeto.frontend.utils.Acessibilidade;
import projeto.frontend.utils.NavegacaoTela;
import projeto.frontend.utils.UsuarioLogado;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.util.List;

/**
 * Controlador responsável pela tela principal do usuário logado no sistema.
 * Gerencia notificações, eventos e navegação entre telas do sistema.
 */
public class PrincipalUsuarioLogadoController {
    private ControllerEvento controllerEvento = new ControllerEvento();


    @FXML
    private Label labelAbrirTelaEdicao;

    @FXML
    private Label labelAbrirTelaListar;

    @FXML
    private Label labelAvaliarEvento;

    @FXML
    private Label labelComprarIngresso;

    @FXML
    private Label compras;

    @FXML
    private ListView<Notificacoes> areaTexto;

    @FXML
    private TableView<Evento> tabelaEventos;

    @FXML
    private TableColumn<Evento, String> colunaNomeEvento;

    @FXML
    private TableColumn<Evento, String> colunaData;

    private final List<Evento> eventos = controllerEvento.listarEventosDisponiveis();

    /**
     * Inicializa os componentes da interface.
     * Configura as tabelas de eventos e as notificações.
     */
    @FXML
    public void initialize() {

        labelAbrirTelaEdicao.setFocusTraversable(true);
        labelAbrirTelaListar.setFocusTraversable(true);
        labelComprarIngresso.setFocusTraversable(true);
        compras.setFocusTraversable(true);
        labelAvaliarEvento.setFocusTraversable(true);


        Acessibilidade.configurarEstiloFoco(labelAbrirTelaEdicao);
        Acessibilidade.configurarEstiloFoco(labelAbrirTelaListar);
        Acessibilidade.configurarEstiloFoco(labelComprarIngresso);
        Acessibilidade.configurarEstiloFoco(compras);
        Acessibilidade.configurarEstiloFoco(labelAvaliarEvento);

        // Configurar ações para Enter
        labelAbrirTelaEdicao.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                abrirTelaEditar();
            }
        });

        labelAbrirTelaListar.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                abrirTelaListar();
            }
        });

        labelComprarIngresso.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) abrirTelaComprar(null);
        });

        compras.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) abrirTelaComprar(null);
        });

        labelAvaliarEvento.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    fazerFeedBack(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        if (areaTexto != null) {
            ObservableList<Notificacoes> notificacoesObservable = FXCollections.observableArrayList(UsuarioLogado.getUsuarioLogado().getNotificacoes());
            areaTexto.setItems(notificacoesObservable);

            if (notificacoesObservable.isEmpty()) {
                areaTexto.setPlaceholder(new Label("Não há notificações a serem exibidas."));
            } else {
                areaTexto.setCellFactory(listView -> new ListCell<Notificacoes>() {
                    @Override
                    protected void updateItem(Notificacoes notificacao, boolean empty) {
                        super.updateItem(notificacao, empty);
                        if (empty || notificacao == null) {
                            setText(null);
                        } else {
                            setText(notificacao.getMensagemNotificada());
                        }
                    }
                });

                areaTexto.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2) {
                        Notificacoes notificacaoSelecionada = areaTexto.getSelectionModel().getSelectedItem();
                        if (notificacaoSelecionada != null) {
                            abrirTelaDetalhesNotificacao(notificacaoSelecionada);
                        }
                    }
                });

                areaTexto.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        Notificacoes notificacaoSelecionada = areaTexto.getSelectionModel().getSelectedItem();
                        if (notificacaoSelecionada != null) {
                            abrirTelaDetalhesNotificacao(notificacaoSelecionada);
                        }
                    }
                });
            }
        }
        colunaNomeEvento.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("dataFormatada"));

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

        // Ação para pressionar a tecla Enter na TableView
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
     * Atualiza a lista de notificações exibida no ListView.
     */
    public void atualizarNotificacoes() {
        ObservableList<Notificacoes> notificacoesObservable = FXCollections.observableArrayList(UsuarioLogado.getUsuarioLogado().getNotificacoes());
        areaTexto.setItems(notificacoesObservable);
        areaTexto.refresh();
    }

    /**
     * Abre a tela de edição de dados do usuário.
     */
    @FXML
    public void abrirTelaEditar(){
        Stage stage = null;
        if (labelAbrirTelaEdicao != null) {
            stage = (Stage) labelAbrirTelaEdicao.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaEdicao.fxml", "Editar Dados");
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
     * Realiza o logout do usuário e retorna à tela principal.
     */
    @FXML
    public void sairDaConta(){
        UsuarioLogado.sairConta();
        Stage stage = null;
        if (labelAbrirTelaListar != null) {
            stage = (Stage) labelAbrirTelaEdicao.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaPrincipal.fxml", "Tela Principal");
    }

    /**
     * Abre a tela para compra de ingressos.
     *
     * @param mouseEvent Evento do mouse que acionou a navegação.
     */
    public void abrirTelaComprar(MouseEvent mouseEvent) {
        Stage stage = null;
        if (labelAbrirTelaListar != null) {
            stage = (Stage) labelAbrirTelaEdicao.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaComprar.fxml", "Comprar Ingresso");
    }

    /**
     * Retorna à tela inicial do sistema.
     *
     * @param mouseEvent Evento do mouse que acionou a navegação.
     */
    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

    /**
     * Abre a tela que lista as compras realizadas pelo usuário.
     *
     * @param mouseEvent Evento do mouse que acionou a navegação.
     */
    public void comprasFeitas(MouseEvent mouseEvent) {
        Stage stage = null;
        if (labelAbrirTelaListar != null) {
            stage = (Stage) labelAbrirTelaEdicao.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaComprasFeitas.fxml", "Compras Realizadas");
    }

    /**
     * Abre a tela para realizar avaliações de eventos.
     *
     * @param mouseEvent Evento do mouse que acionou a navegação.
     * @throws IOException Se ocorrer um erro ao carregar a tela.
     */
    public void fazerFeedBack(MouseEvent mouseEvent) throws IOException {
        Stage stage = null;
        if (labelAvaliarEvento != null) {
            stage = (Stage) labelAvaliarEvento.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaListaEventosFeedBack.fxml", "Compras Realizadas");
    }

    /**
     * Exibe os detalhes de uma notificação selecionada.
     *
     * @param notificacoes A notificação a ser detalhada.
     */
    public void abrirTelaDetalhesNotificacao(Notificacoes notificacoes){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detalhes do Evento");
        alert.setHeaderText("Notificação");
        alert.setContentText(notificacoes.getMensagemNotificada());
        alert.showAndWait();
    }

    /**
     * Exibe os detalhes de um evento selecionado.
     *
     * @param evento O evento a ser detalhado.
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
