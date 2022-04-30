module kepnezegeto.kepnezegeto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens kepnezegeto;
    opens kepnezegeto.controllers to javafx.fxml;

}