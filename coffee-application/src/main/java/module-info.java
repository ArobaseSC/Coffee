module org.arobase.coffeeapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens org.arobase.coffeeapplication to javafx.fxml;
    exports org.arobase.coffeeapplication;
}