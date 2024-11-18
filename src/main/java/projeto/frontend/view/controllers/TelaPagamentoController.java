package projeto.frontend.view.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import projeto.backend.controller.ControllerUsuario;
import projeto.backend.model.Evento;
import projeto.backend.model.Usuario;
import projeto.frontend.utils.NavegacaoTela;
import projeto.backend.model.Pagamento;
import projeto.backend.controller.ControllerCompra;
import projeto.backend.controller.ControllerUsuario;
import projeto.backend.controller.ControllerEvento;

public class TelaPagamentoController {

    private ControllerCompra controllerCompra = new ControllerCompra();

    @FXML
    private Label nomeEventoLabel;
    public void setNomeEvento(String nomeEvento) {
        nomeEventoLabel.setText("Evento: " + nomeEvento);
    }

    @FXML
    private ComboBox<String> metodoPagamento;


    @FXML
    public void initialize(){
        ObservableList<String> opcoes = FXCollections.observableArrayList(
                Pagamento.TipoPagamento.BOLETO.name(),
                Pagamento.TipoPagamento.CRÉDITO.name(),
                Pagamento.TipoPagamento.DÉBITO.name(),
                Pagamento.TipoPagamento.PIX.name()
        );
        metodoPagamento.setItems(opcoes);
    }

    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

    public void botaoRealizarPagamento(ActionEvent actionEvent) {
        try{
            String metodoSelecionado = metodoPagamento.getValue();

            Pagamento.TipoPagamento tipoPagamento = Pagamento.TipoPagamento.valueOf(metodoSelecionado);

            controllerCompra.fazerIngresso(usuario, evento.getNome(), tipoPagamento, usuario.getLogin());

        }catch (IllegalArgumentException | NullPointerException e){
            NavegacaoTela.showErrorMessage("Erro! Selecione uma forma de pagamento.");
        }
    }
}
