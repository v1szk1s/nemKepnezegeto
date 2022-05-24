package kepnezegeto;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import kepnezegeto.kepKezelo.Kep;
import kepnezegeto.kepKezelo.Kepkezelo;

import kepnezegeto.tranformaciok.*;

import kepnezegeto.filterek.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.imageio.*;
import javax.swing.RootPaneContainer;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;


public class Main extends Application {
    private Stage window;
    private static BorderPane root;
    private static HBox preview;
    private static Kepkezelo kepkezelo = new Kepkezelo();
    private static int imageIndex = -1;
    private static ImageView iv = new ImageView();
    private Scene scene;
    private MenuBar menuBar;
    private Menu fileMenu, transzMenu, filterMenu;
    private static MenuItem openFileMenuItem, saveMenuItem, undoMenuItem, openFilesMenuItem;
    private Button right, left;
    private FileChooser fileChooser;


    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/kepnezegeto/view/kepnezegeto.fxml"));
        init(stage);
        root.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2 && kepkezelo.getKepek().size() == 0){
                openFiles();
            }
        });;


    }

    public static void setImageIndex(int index){
        imageIndex = index;
        refresh();
    }
    public static void refresh(){
        iv.setImage(kepkezelo.getKep(imageIndex));
        root.setCenter(iv);
        root.setRight(null);
        preview.getChildren().clear();
        undoMenuItem.setDisable(kepkezelo.getKepek().get(imageIndex).getHistory().size() == 0);

        int i = Math.max(0, imageIndex-3);
        for(int j = 0; j < 6; j++, i++){
            //ath.min(i+2, kepkezelo.getPreviewek().size())
            if(i == kepkezelo.getPreviewek().size()){
                break;
            }

            ImageView v = kepkezelo.getPreviewek().get(i);
            if(i != imageIndex){
                v.setOpacity(0.6);

            }else{
                v.setOpacity(1);
            }

            preview.getChildren().add(v);
            HBox.setMargin(v, new Insets(0, 20, 0, 20));
            
        }
    }

    public void init(Stage window){
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG/PNG/GIF fájlok (*.png, *.jpg, *.gif)", "*.jpeg", "*.JPEG", "*.jpg", "*.JPG", "*.png", "*.PNG", "*.GIF", "*.gif"));

        iv.setPreserveRatio(true);
        iv.setFitWidth(1000);
        iv.setFitHeight(540);

        ArrayList<Transzformacio> transzformaciok = new ArrayList<>(); // itt lehetne megcsinalni azt, hogy kesobb be lehessen importalni
        transzformaciok.add(new Forgatas());
        transzformaciok.add(new Tukrozes());
        transzformaciok.add(new Atmeretezes());
        transzformaciok.add(new Kimetszes());

        ArrayList<Filter> filterek = new ArrayList<>();
        filterek.add(new Grayscale());
        filterek.add(new Negativ());
        

        root = new BorderPane();

        menuBar = new MenuBar();
        fileMenu = new Menu("Fájl");
        openFileMenuItem = new MenuItem("Kép megnyitása");
        openFilesMenuItem = new MenuItem("Képek megnyitása");
        saveMenuItem = new MenuItem("Mentés");
        undoMenuItem = new MenuItem("Visszavonás");
        undoMenuItem.setDisable(true);
        fileMenu.getItems().addAll(openFileMenuItem, openFilesMenuItem, saveMenuItem);

        transzMenu = new Menu("Szerkeszt");
        for(var t:transzformaciok){
            transzMenu.getItems().add(new MenuItem(t.getNev()));
        }
        transzMenu.getItems().add(undoMenuItem);

        filterMenu = new Menu("Filterek");
        for(var t:filterek){
            filterMenu.getItems().add(new MenuItem(t.getNev()));
        }
        //ImageView forgatKep = new ImageView(".");
        ImageView forgatKep = new ImageView("file:src/main/java/kepnezegeto/images/forgat.png");
        forgatKep.setFitHeight(22);
        forgatKep.setFitWidth(22);
 

        menuBar.getMenus().addAll(fileMenu, transzMenu, filterMenu);
        root.setTop(menuBar);

        Text text = new Text("Kép megnyitásához duplán kattints valahova...");
        root.setCenter(text);



        preview = new HBox();
        preview.setMinHeight(150);
        BorderPane.setMargin(preview, new Insets(10, 10, 10, 10));


        Scene scene = new Scene(root, 1200, 750);
        scene.getStylesheets().add("file:src/main/java/kepnezegeto/style/style.css");
        window.setTitle("A Festő Titkai");
        window.setResizable(false);
        window.setScene(scene);
        window.show();

        openFileMenuItem.setOnAction(e -> openFile());
        openFilesMenuItem.setOnAction(e -> openFiles());      
        saveMenuItem.setOnAction(e -> saveFile());
        undoMenuItem.setOnAction(e -> undoAction());
        // xd.setOnAction(e -> {
        //     System.out.println("xd");
        //     kepkezelo.set(imageIndex, transzformaciok.get(0).transzformal(kepkezelo.getKep(imageIndex)));
        //     iv.setImage(kepkezelo.getKep(imageIndex));
        //     //root.setCenter(iv);
        //     refresh();
        // });
        //style
        //root.setStyle("-fx-background-color: BEIGE;");

        for (var item:transzMenu.getItems()){
            for(var transz:transzformaciok){
                if(item.getText().equals(transz.getNev())){
                    item.setOnAction(e -> {


                        if(kepkezelo.getKepek().size() > 0){

                            //kepkezelo.set(imageIndex, transz.transzformal(kepkezelo.getKepek().get(imageIndex)));
                            //iv.setImage(kepkezelo.getKep(imageIndex));
                            //root.setCenter(iv);
                            refresh();

                            root.setRight(transz.getUI(kepkezelo.getKepek().get(imageIndex), ((ev) -> {

                                if(kepkezelo.getKepek().get(imageIndex).getHistory().size() != 0) {
                                    undoMenuItem.setDisable(false);
                                }
                                root.getChildren().remove(((Button)ev.getSource()).getParent());
                                iv.setImage(kepkezelo.getKep(imageIndex));

                            })));

                        }
                        //root.setBottom(preview);
                    });
                }
            }
        }

        for (var item:filterMenu.getItems()){
            for(var filter:filterek){
                if(item.getText().equals(filter.getNev())){
                    item.setOnAction(e -> {
                        if(kepkezelo.getKepek().size() > 0){

                            undoMenuItem.setDisable(false);

                            kepkezelo.set(imageIndex, filter.filter(kepkezelo.getKep(imageIndex)));
                            iv.setImage(kepkezelo.getKep(imageIndex));

                            refresh();
                            
                            root.setBottom(preview);
                        }
                      
                    });
                }
            }
        }

    }

    private void undoAction() {

        kepkezelo.getKepek().get(imageIndex).restore();

        //System.out.println(kepkezelo.getKepek().get(imageIndex).getHistory().size());
        iv.setImage(kepkezelo.getKep(imageIndex));
        if(kepkezelo.getKepek().get(imageIndex).getHistory().size() <= 0){
            undoMenuItem.setDisable(true);
        }
    }

    public void openFile(){
        fileChooser.setTitle("Képek megnyitása");
        File kep = fileChooser.showOpenDialog(window);

        if(kep != null){
            //System.out.println(kep.toURI());
            kepkezelo.add(new Image(kep.toURI().toString()));
            imageIndex++;
            iv.setImage(kepkezelo.getKep(imageIndex));
            root.setCenter(iv);
            root.setBottom(preview);
            refresh();

        }
    }

    public void openFiles(){
        fileChooser.setTitle("Képek megnyitása");
        List<File> kepek = fileChooser.showOpenMultipleDialog(window);

        if(kepek != null){
            for(var v:kepek){
                kepkezelo.add(new Image(v.toURI().toString()));
                imageIndex++;
            }
            iv.setImage(kepkezelo.getKep(imageIndex));
            root.setCenter(iv);
            root.setBottom(preview);
            refresh();

        }
    }

    public void saveFile(){
        if (kepkezelo.getKepek().size() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nincs kép!");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }
        
        fileChooser.setTitle("Mentés");
        File outputFile = fileChooser.showSaveDialog(window);

        //BufferedImage bImage = SwingFXUtils.fromFXImage(iv.getImage(), null);
        if(outputFile != null){
            //try {
                //ImageIO.write(bImage, "png", ujFile);
            //}catch (IOException e){}
            kepkezelo.save(imageIndex, outputFile);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}