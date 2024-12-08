package projeto.frontend.view.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projeto.backend.controller.ControllerCompra;
import projeto.backend.model.Ingresso;

import javafx.scene.control.Label;
import projeto.backend.model.Notificacoes;
import projeto.backend.model.Pagamento;
import projeto.backend.model.Recibo;
import projeto.frontend.utils.NavegacaoTela;
import projeto.frontend.utils.UsuarioLogado;

import java.io.IOException;

public class ReciboController {

    private ControllerCompra controllerCompra = new ControllerCompra();

    @FXML
    private Label nomeEventoLabel;

    @FXML
    private Label categoriaLabel;

    @FXML
    private Label descricaoLabel;

    @FXML
    private Label dataLabel;

    @FXML
    private Label statusLabel;

    private Recibo recibo;
    private Ingresso ingressoSelecionado;


    public void exibirRecibo(Ingresso ingresso) {
        Platform.runLater(() -> {
            nomeEventoLabel.setText(ingresso.getNomeEvento());
            categoriaLabel.setText(ingresso.getCategoriaEvento());
            descricaoLabel.setText(ingresso.getDescricaoEvento());
            dataLabel.setText(ingresso.getDataEventoFormatado());
            statusLabel.setText(ingresso.getStatusEvento());
        });
        ingressoSelecionado = ingresso;
    }
    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }


    public void botaoRealizarCancelamento(ActionEvent actionEvent) {;
        try {
            if (ingressoSelecionado == null) {
                NavegacaoTela.showErrorMessage("Ingresso inválido para cancelamento.");
                return;
            }

            boolean sucesso = controllerCompra.cancelarCompra(UsuarioLogado.getUsuarioLogado(), ingressoSelecionado);

            if (sucesso) {
                NavegacaoTela.showSuccessMessage("Cancelamento", "Cancelamento realizado com sucesso!");

                Notificacoes notificacoes = new Notificacoes("Cancelamento realizado para o evento" + ingressoSelecionado.getNomeEvento());
                UsuarioLogado.getUsuarioLogado().adicionarNotificacoes(notificacoes);

                NavegacaoTela.voltarTelaInicial();
            } else {
                NavegacaoTela.showErrorMessage("Não foi possível realizar o cancelamento. Tente novamente.");
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            NavegacaoTela.showErrorMessage("Não foi possível realizar o cancelamento.");
        }
    }
}
