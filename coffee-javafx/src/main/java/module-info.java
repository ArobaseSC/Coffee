module org.arobase.coffeejavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens org.arobase.coffeejavafx to javafx.fxml;
    exports org.arobase.coffeejavafx;
}