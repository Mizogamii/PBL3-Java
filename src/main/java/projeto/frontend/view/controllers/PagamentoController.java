package projeto.frontend.view.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import projeto.backend.model.Evento;
import projeto.backend.model.Ingresso;
import projeto.frontend.utils.NavegacaoTela;
import projeto.backend.model.Pagamento;
import projeto.backend.controller.ControllerCompra;
import projeto.frontend.utils.UsuarioLogado;

import java.text.SimpleDateFormat;

public class PagamentoController {

    private ControllerCompra controllerCompra = new ControllerCompra();

    @FXML
    private Label nomeEventoLabel;

    @FXML
    private Label dataLabel;

    @FXML
    private Label categoriaLabel;

    @FXML
    private Label precoLabel;

    @FXML
    private Label precoLabel1;


    private Evento eventoInfo;

    public void setEvento(Evento eventoInfo) {
        this.eventoInfo = eventoInfo;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Platform.runLater(() -> {
            nomeEventoLabel.setText(eventoInfo.getNome());
            dataLabel.setText(formatter.format(eventoInfo.getData()));
            categoriaLabel.setText(eventoInfo.getCategoria());
            precoLabel.setText(String.format("%.2f", eventoInfo.getPreco()));
            precoLabel1.setText(String.format("R$%.2f", eventoInfo.getPreco()));

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

            Pagamento.TipoPagamento tipoPagamento = Pagamento.TipoPagamento.valueOf(metodoSelecionado);
            Ingresso retorno = controllerCompra.fazerIngresso(UsuarioLogado.getUsuarioLogado(), eventoInfo.getNome(), tipoPagamento, UsuarioLogado.getUsuarioLogado().getLogin());
            NavegacaoTela.showSuccessMessage("Pagamento", "Pagamento realizado com sucesso!");
            NavegacaoTela.voltarTelaInicial();
        }catch (IllegalArgumentException | NullPointerException e){
            NavegacaoTela.showErrorMessage("Erro! Selecione uma forma de pagamento.");
        }
    }
}
