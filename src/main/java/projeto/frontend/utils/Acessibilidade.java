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
package projeto.frontend.utils;

import javafx.scene.control.Label;

public class Acessibilidade {

    /**
     * Configura o estilo de foco para o componente Label.
     *
     * @param label O label no qual o foco será configurado.
     */
    public static void configurarEstiloFoco(Label label) {
        String estiloOriginal = label.getStyle();

        label.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {

                label.setStyle(estiloOriginal + "-fx-border-color: blue; -fx-border-width: 2px; -fx-border-radius: 5px;");
            } else {
                label.setStyle(estiloOriginal);
            }
        });
    }
}
