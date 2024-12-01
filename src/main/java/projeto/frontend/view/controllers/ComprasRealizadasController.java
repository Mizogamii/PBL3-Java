package projeto.frontend.view.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projeto.backend.controller.ControllerUsuario;
import projeto.backend.model.Ingresso;
import projeto.frontend.utils.NavegacaoTela;
import projeto.frontend.utils.UsuarioLogado;

import java.io.IOException;
import java.util.List;

public class ComprasRealizadasController {

    private ControllerUsuario controllerUsuario = new ControllerUsuario();
    private List<Ingresso> ingressos = controllerUsuario.listarComprasRealizadas(UsuarioLogado.getUsuarioLogado());

    @FXML
    public Label voltarTelaInicial;

    @FXML
    private TableView<Ingresso> tabelaCompras;

    @FXML
    private TableColumn<Ingresso, String> colunaNomeEvento;

    @FXML
    private TableColumn<Ingresso, String> colunaCategoria;

    @FXML
    private TableColumn<Ingresso, String> colunaDescricao;

    @FXML
    private TableColumn<Ingresso, String> colunaData;

    @FXML
    private TableColumn<Ingresso, String> colunaStatusEvento;

    @FXML
    private ComboBox<String> filtroStatus;

    @FXML
    public void initialize() {
        colunaNomeEvento.setCellValueFactory(new PropertyValueFactory<>("nomeEvento"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaEvento"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricaoEvento"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("dataEvento"));
        colunaStatusEvento.setCellValueFactory(new PropertyValueFactory<>("statusEvento"));

        ObservableList<Ingresso> listaIngressos = FXCollections.observableArrayList(ingressos);

        tabelaCompras.setItems(listaIngressos);

        ObservableList<String> filtro = FXCollections.observableArrayList("Todas");
        ingressos.forEach(ingresso -> {
            if(!filtro.contains(ingresso.getStatusEvento())){
                filtro.add(ingresso.getStatusEvento());
            }
        });
        filtroStatus.setItems(filtro);
        filtroStatus.setValue("Todas");

        tabelaCompras.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Ingresso ingressoSelecionado = tabelaCompras.getSelectionModel().getSelectedItem();
                if (ingressoSelecionado != null) {
                    try {
                        abrirTelaRecibo(ingressoSelecionado);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }


    private void abrirTelaRecibo(Ingresso ingresso) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaRecibo.fxml"));
        Parent root = loader.load();

        ReciboController telaReciboController = loader.getController();

        telaReciboController.exibirRecibo(ingresso);

        Stage stage = (Stage) tabelaCompras.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

    public void filtrarStatus(ActionEvent actionEvent) {
        String statusSelecionado = filtroStatus.getValue();
        if("Todas".equals(statusSelecionado)){
            tabelaCompras.setItems(FXCollections.observableArrayList(ingressos));
        }else{
            ObservableList<Ingresso> statusFiltrados = FXCollections.observableArrayList();
            for(Ingresso ingresso: ingressos){
                if(ingresso.getStatusEvento().equals(statusSelecionado)){
                    statusFiltrados.add(ingresso);
                }
                tabelaCompras.setItems(statusFiltrados);
            }
        }
    }
}
