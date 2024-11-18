package projeto.frontend.view.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import projeto.frontend.utils.NavegacaoTela;
import projeto.backend.model.Pagamento;

public class TelaPagamentoController {

    @FXML
    private Label nomeEventoLabel;
    public void setNomeEvento(String nomeEvento) {
        nomeEventoLabel.setText("Evento: " + nomeEvento);
    }

    @FXML
    private ComboBox<String> metodoPagamento;


    @FXML
    public void initialize(){
        String[] metodos = Pagamento.getMetodosPagamento();
        ObservableList<String> opcoes = FXCollections.observableArrayList(metodos);
        metodoPagamento.setItems(opcoes);
    }

    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }


}
