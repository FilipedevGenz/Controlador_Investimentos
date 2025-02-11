module org.controladorinvestimentos.controlador_investimentos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens org.controladorinvestimentos.controlador_investimentos.GUI to javafx.fxml;

    requires javafx.base;
    requires javafx.graphics;
    exports org.controladorinvestimentos.controlador_investimentos.GUI;


}

