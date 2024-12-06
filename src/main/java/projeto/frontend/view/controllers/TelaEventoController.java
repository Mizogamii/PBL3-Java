package projeto.frontend.view.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import projeto.backend.model.Comentario;
import projeto.backend.model.Evento;
import projeto.frontend.utils.NavegacaoTela;

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

    }

    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

}
