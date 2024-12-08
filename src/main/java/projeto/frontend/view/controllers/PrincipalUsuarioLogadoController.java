package projeto.frontend.view.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
import projeto.frontend.utils.NavegacaoTela;
import projeto.frontend.utils.UsuarioLogado;

import java.io.IOException;
import java.util.List;

public class PrincipalUsuarioLogadoController {
    private ControllerEvento controllerEvento = new ControllerEvento();

    @FXML
    private Label labelAbrirTelaEdicao;

    @FXML
    private Label labelAbrirTelaListar;

    @FXML
    private Label labelAvaliarEvento;

    @FXML
    private ListView<Notificacoes> areaTexto;

    @FXML
    private TableView<Evento> tabelaEventos;

    @FXML
    private TableColumn<Evento, String> colunaNomeEvento;

    @FXML
    private TableColumn<Evento, String> colunaData;

    private final List<Evento> eventos = controllerEvento.listarEventosDisponiveis();

    @FXML
    public void initialize() {
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
            }
        }
        colunaNomeEvento.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));

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
    }

    public void atualizarNotificacoes() {
        ObservableList<Notificacoes> notificacoesObservable = FXCollections.observableArrayList(UsuarioLogado.getUsuarioLogado().getNotificacoes());
        areaTexto.setItems(notificacoesObservable);
        areaTexto.refresh();
    }

    @FXML
    public void abrirTelaEditar(){
        Stage stage = null;
        if (labelAbrirTelaEdicao == null) {
            System.out.println("labelAbriTelaEdicao está null!");
        } else {
            stage = (Stage) labelAbrirTelaEdicao.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaEdicao.fxml", "Editar Dados");
    }

    @FXML
    public void abrirTelaListar(){
        Stage stage = null;
        if (labelAbrirTelaListar == null) {
            System.out.println("labelAbriTelaListar está null!");
        } else {
            stage = (Stage) labelAbrirTelaListar.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaListarEvento.fxml", "Listar dados");
    }

    @FXML
    public void sairDaConta(){
        UsuarioLogado.sairConta();
        Stage stage = null;
        if (labelAbrirTelaListar == null) {
            System.out.println("labelAbriTelaPrincipal está null!");
        } else {
            stage = (Stage) labelAbrirTelaEdicao.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaPrincipal.fxml", "Tela Principal");
    }

    public void abrirTelaComprar(MouseEvent mouseEvent) {
        Stage stage = null;
        if (labelAbrirTelaListar == null) {
            System.out.println("labelAbriTelaComprar está null!");
        } else {
            stage = (Stage) labelAbrirTelaEdicao.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaComprar.fxml", "Comprar Ingresso");
    }

    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

    public void comprasFeitas(MouseEvent mouseEvent) {
        Stage stage = null;
        if (labelAbrirTelaListar == null) {
            System.out.println("labelComprasFeitas está null!");
        } else {
            stage = (Stage) labelAbrirTelaEdicao.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaComprasFeitas.fxml", "Compras Realizadas");
    }

    public void fazerFeedBack(MouseEvent mouseEvent) throws IOException {
        Stage stage = null;
        if (labelAvaliarEvento == null) {
            System.out.println("labelFeedBack está null!");
        } else {
            stage = (Stage) labelAvaliarEvento.getScene().getWindow();
        }
        NavegacaoTela.trocarTela(stage, "/fxml/TelaListaEventosFeedBack.fxml", "Compras Realizadas");
    }



    public void abrirTelaDetalhesNotificacao(Notificacoes notificacoes){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detalhes do Evento");
        alert.setHeaderText("Notificação");
        alert.setContentText(notificacoes.getMensagemNotificada());
        alert.showAndWait();
    }

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
