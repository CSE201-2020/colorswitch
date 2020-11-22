package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Controller {
    @FXML
    private AnchorPane mainRoot;
    private AnchorPane tempRoot;
    @FXML
    private ImageView startGame;

    @FXML
    private ImageView loadGame;
    @FXML
    void startGame(MouseEvent event) throws Exception {
//        AnchorPane pane= FXMLLoader.load(getClass().getResource("Almanac.fxml"));
//        mainRoot.getChildren().setAll(pane);
        VBox layout1= new VBox(20);

        Label gameplay = new Label("Gameplay here");
        layout1.getChildren().add(gameplay);

        mainRoot.getChildren().setAll(layout1);
    }
    @FXML
    void loadGame(MouseEvent event) throws Exception{
        VBox layout1= new VBox(20);

        Label gameplay = new Label("Load Game here");
        layout1.getChildren().add(gameplay);

        mainRoot.getChildren().setAll(layout1);
    }

}
