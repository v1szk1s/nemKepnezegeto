package kepnezegeto;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kepnezegeto.filterek.Grayscale;
import kepnezegeto.kepek.Jpg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    private Stage window;
    BorderPane root;
    private ObservableList<Image> kepek = FXCollections.<Image>observableArrayList();
    private ImageView imageView;
    private Text text = new Text("text");


    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/kepnezegeto/view/kepnezegeto.fxml"));
        init(stage);

        kepek.addListener(new ListChangeListener<Image>() {
            @Override
            public void onChanged(Change<? extends Image> change) {
                text.setText(kepek.size()+ "");
                if(kepek.size() > 0){
                    imageView = new ImageView(kepek.get(kepek.size()-1));
                    root.setCenter(imageView);
                    text.setText(kepek.get(kepek.size()-1).getUrl());
                }
            }
        });

    }

    public void init(Stage window){
        root = new BorderPane();
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("Fájl");
        MenuItem openMenu = new MenuItem("Megnyitás");
        fileMenu.getItems().add(openMenu);
        menuBar.getMenus().add(fileMenu);
        root.setTop(menuBar);
        StringBuilder builder = new StringBuilder();
        root.setCenter(text);
        Scene scene = new Scene(root, 800, 600);
        window.setTitle("Képnézegető");
        window.setResizable(false);
        window.setScene(scene);
        window.show();
        openMenu.setOnAction(e -> openFile());

    }

    public void openFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Képek megnyitása");
        File kep = fileChooser.showOpenDialog(window);
        if(kep != null){
            kepek.add(new Image(kep.toURI().toString(), 600, 0, true, false));
        }

    }

    public static void main(String[] args) {
        launch();
    }
}