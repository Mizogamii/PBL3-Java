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

/**
 * Classe controladora responsável por gerenciar a exibição do recibo e o cancelamento
 * de uma compra de ingresso no sistema.
 */
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

    /**
     * Exibe as informações do recibo com base nos detalhes do ingresso fornecido.
     *
     * @param ingresso o ingresso que será exibido na tela de recibo.
     */
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
    /**
     * Retorna à tela inicial.
     *
     * @param mouseEvent evento de clique do mouse que aciona a ação.
     */
    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

    /**
     * Realiza o cancelamento da compra do ingresso atualmente exibido.
     *
     * @param actionEvent evento de ação que aciona o cancelamento.
     */
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
