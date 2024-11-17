module projeto {
    requires com.google.gson;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;


    requires java.desktop;

    exports projeto.frontend.view;
    exports projeto.backend.model;
    exports projeto.backend.controller;
    opens projeto.frontend.view.controllers to javafx.fxml, eu.hansolo.toolbox;
    //opens projeto.frontend.view.controllers to javafx.fxml;
    opens projeto.backend.model to com.google.gson;

}
