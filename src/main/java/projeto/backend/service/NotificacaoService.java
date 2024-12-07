package projeto.backend.service;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import projeto.backend.model.Notificacoes;
import projeto.backend.model.Usuario;
import projeto.frontend.utils.UsuarioLogado;

public class NotificacaoService {
    public static void adicionarNotificacao(String mensagem) {
        Usuario usuarioLogado = UsuarioLogado.getUsuarioLogado();
        Notificacoes novaNotificacao = new Notificacoes(mensagem);
        usuarioLogado.adicionarNotificacoes(novaNotificacao);
    }
}
