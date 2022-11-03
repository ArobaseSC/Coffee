module org.arobase.coffeejfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.arobase.coffeejfx to javafx.fxml;
    exports org.arobase.coffeejfx;
}