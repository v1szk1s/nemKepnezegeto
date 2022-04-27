module kepnezegeto.kepnezegeto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens kepnezegeto.megjelenites to javafx.fxml;
    exports kepnezegeto.megjelenites;
}