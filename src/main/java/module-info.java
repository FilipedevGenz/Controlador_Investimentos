module org.controladorinvestimentos.controladorInvestimentos {
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

    opens org.controladorinvestimentos.controladorInvestimentos.GUI to javafx.fxml;

    requires javafx.base;
    requires javafx.graphics;
    requires com.google.gson;
    requires okhttp3;
    requires static lombok;
    requires java.desktop;
    requires org.jfree.jfreechart;
    requires jfreechart.fx;


}

