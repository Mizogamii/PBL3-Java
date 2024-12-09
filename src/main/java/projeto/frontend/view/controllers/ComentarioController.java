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

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import projeto.backend.controller.ControllerEvento;
import projeto.backend.model.Ingresso;
import projeto.backend.model.Recibo;
import projeto.frontend.utils.Acessibilidade;
import projeto.frontend.utils.NavegacaoTela;
import projeto.frontend.utils.UsuarioLogado;

import java.lang.module.Configuration;

/**
 * Controlador responsável pela interface de comentários no evento.
 * Permite ao usuário logado realizar comentários sobre um evento que participou.
 */
public class ComentarioController {

    /** Instância do controlador responsável pela lógica relacionada a eventos. */
    private ControllerEvento controllerEvento = new ControllerEvento();

    @FXML
    private Label nomeEvento;

    @FXML
    private Button mandarComentario;

    @FXML
    private TextArea areaComentar;

    @FXML
    private Label voltarTelaInicial;

    private Ingresso ingresso;

    @FXML
    public void initialize() {
        if (voltarTelaInicial != null) {
            voltarTelaInicial.setFocusTraversable(true);
            Acessibilidade.configurarEstiloFoco(voltarTelaInicial);
            voltarTelaInicial.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    voltarTela(null);
                }
            });
        }
    }

    /**
     * Define o ingresso relacionado ao comentário e atualiza o nome do evento na interface.
     *
     * @param ingresso o ingresso associado ao evento.
     */
    public void setIngresso(Ingresso ingresso){
        this.ingresso = ingresso;
        nomeEvento.setText(ingresso.getNomeEvento());
    }

    /**
     * Retorna à tela inicial do sistema ao clicar no botão ou área correspondente.
     *
     * @param mouseEvent Evento de clique do mouse.
     */
    public void voltarTela(MouseEvent mouseEvent) {
        NavegacaoTela.voltarTelaInicial();
    }

    /**
     * Envia o comentário realizado pelo usuário.
     * O método valida o texto, registra o comentário no evento e exibe mensagens de sucesso ou erro.
     *
     * @param mouseEvent Evento de clique do mouse.
     */
    public void enviarComentario(MouseEvent mouseEvent) {
        try{
            String comentarioTexto = areaComentar.getText().trim();
            controllerEvento.fazerComentario(UsuarioLogado.getUsuarioLogado(), ingresso.getEvento(), ingresso.getNomeEvento(), comentarioTexto);
            if(!comentarioTexto.isEmpty()){
                NavegacaoTela.showSuccessMessage("Comentário","Comentário realizado com sucesso!");
                NavegacaoTela.voltarTelaInicial();
            }
        }catch (Exception e){
            NavegacaoTela.showErrorMessage("ERRO! Digite um comentário!");
        }
    }
}
