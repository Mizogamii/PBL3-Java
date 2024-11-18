package projeto.frontend.view.controllers;

import javafx.application.Platform;
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
import projeto.frontend.utils.UsuarioLogado;

import java.lang.management.ClassLoadingMXBean;

public class TelaPagamentoController {

    private ControllerCompra controllerCompra = new ControllerCompra();

    @FXML
    private Label nomeEventoLabel;

    @FXML
    private Label dataLabel;

    @FXML
    private Label categoriaLabel;

    @FXML
    private Label precoLabel;


    private Evento eventoInfo;

    public void setEvento(Evento eventoInfo) {
        this.eventoInfo = eventoInfo;
        Platform.runLater(() -> {
            nomeEventoLabel.setText(eventoInfo.getNome());
            dataLabel.setText(String.format("", eventoInfo.getData()));
            categoriaLabel.setText(eventoInfo.getCategoria());
            precoLabel.setText(String.format("%.2f", eventoInfo.getPreco()));
        });
    }


    @FXML
    private ComboBox<String> metodoPagamento;


    @FXML
    public void initialize(){
        ObservableList<String> opcoes = FXCollections.observableArrayList(
                Pagamento.TipoPagamento.BOLETO.name(),
                Pagamento.TipoPagamento.CREDITO.name(),
                Pagamento.TipoPagamento.DEBITO.name(),
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
            System.out.println(metodoSelecionado);

            if(metodoSelecionado == null || metodoSelecionado.isEmpty()){
                NavegacaoTela.showErrorMessage("Erro! Selecione uma forma de pagamento.");
                return;
            }

            System.out.println("Convertendo para enum...");

            Pagamento.TipoPagamento tipoPagamento = Pagamento.TipoPagamento.valueOf(metodoSelecionado);
            System.out.println("Método convertido para enum: " + tipoPagamento);

            controllerCompra.fazerIngresso(UsuarioLogado.getUsuarioLogado(), eventoInfo.getNome(), tipoPagamento, UsuarioLogado.getUsuarioLogado().getLogin());

        }catch (IllegalArgumentException | NullPointerException e){
            NavegacaoTela.showErrorMessage("Erro! Selecione uma forma de pagamento.");
        }
    }

    public void botaoTeste(ActionEvent actionEvent) {
        String metodoSelecionado = metodoPagamento.getValue();
        if (metodoSelecionado == null) {
            System.out.println("Método não selecionado.");
        } else {
            System.out.println("Método selecionado: " + metodoSelecionado);
        }
    }
}
