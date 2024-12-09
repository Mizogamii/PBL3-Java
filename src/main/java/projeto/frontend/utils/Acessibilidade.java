package projeto.frontend.utils;

import javafx.scene.control.Label;

public class Acessibilidade {

    /**
     * Configura o estilo de foco para o componente Label.
     *
     * @param label O label no qual o foco será configurado.
     */
    public static void configurarEstiloFoco(Label label) {
        String estiloOriginal = label.getStyle(); // Pega o estilo original da label

        label.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Mantém o estilo original e adiciona o estilo de foco
                label.setStyle(estiloOriginal + "-fx-border-color: blue; -fx-border-width: 2px; -fx-border-radius: 5px;");
            } else {
                // Volta ao estilo original sem o foco
                label.setStyle(estiloOriginal);
            }
        });
    }
}
