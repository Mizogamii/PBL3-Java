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
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import projeto.backend.model.Comentario;
import projeto.backend.model.Evento;
import projeto.backend.model.Ingresso;
import projeto.frontend.utils.NavegacaoTela;

import java.io.IOException;

/**
 * Classe controladora responsável por exibir os detalhes de um evento e seus comentários associados.
 */
public class TelaEventoController {
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

    @FXML
    private ListView<Comentario> areaTexto;

    /**
     * Inicializa a tela de evento com as informações do evento e seus comentários.
     *
     * @param evento o evento cujas informações serão exibidas na tela.
     */
    public void initialize(Evento evento) {
        nomeEventoLabel.setText(evento.getNome());
        categoriaLabel.setText(evento.getCategoria());
        descricaoLabel.setText(evento.getDescricao());
        dataLabel.setText(evento.getDataFormatada());
        statusLabel.setText(evento.getStatusEventoFormatado());

        if(evento.getComentarios() != null && !evento.getComentarios().isEmpty()){
            areaTexto.getItems().addAll(evento.getComentarios());
        }else{
            areaTexto.setPlaceholder(new Label("Não há comentários a serem exibidos."));
        }
        areaTexto.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Comentario comentario, boolean empty) {
                super.updateItem(comentario, empty);
                if (empty || comentario == null) {
                    setText(null);
                } else {
                    setText(comentario.getLogin() + ": " + comentario.getComent());
                }
            }
        });

        // Ação ao clicar duas vezes sobre um evento
        areaTexto.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Comentario comentario = areaTexto.getSelectionModel().getSelectedItem();
                if (comentario != null) {
                    abrirDetalhesComentario(comentario);
                }
            }
        });

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
     * Exibe os detalhes de um evento selecionado.
     *
     * @param comentario Comentario cuja descrição será exibida.
     */
    public void abrirDetalhesComentario(Comentario comentario){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detalhes do comentário ");
        alert.setHeaderText("Comentário");

        String detalhes = "Usuário:  " + comentario.getLogin()  + "\nEvento: " + comentario.getNomeEvento() + "\nComentário: " + comentario.getComent();

        alert.setContentText(detalhes);
        alert.showAndWait();
    }
}
