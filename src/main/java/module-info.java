module org.controladorinvestimentos.demo1 {
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

    opens org.controladorinvestimentos.demo1 to javafx.fxml;
    exports org.controladorinvestimentos.demo1;
    opens org.controladorinvestimentos.demo1.GUI to javafx.graphics;

    requires javafx.base;
    requires javafx.graphics;
    exports org.controladorinvestimentos.controlador_investimentos.GUI to javafx.graphics;
}

