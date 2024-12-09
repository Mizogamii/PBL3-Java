package projeto.frontend.utils;

import javafx.scene.control.Label;

/**
 * Classe utilitária para configurar o estilo de foco nos componentes.
 * Facilita a reutilização de código relacionado ao estilo de foco.
 */
public class Acessibilidade {

    /**
     * Configura o estilo de foco para o componente Label.
     *
     * @param label O label no qual o foco será configurado.
     */
    public static void configurarEstiloFoco(Label label) {
        label.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                label.setStyle("-fx-border-color: blue; -fx-border-width: 2px; -fx-border-radius: 5px;");
            } else {
                label.setStyle("");
            }
        });
    }
}
