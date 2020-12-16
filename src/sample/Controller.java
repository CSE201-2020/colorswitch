package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
        Node node=(Node) event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        Gameplay gameplay = new Gameplay(700 , 9/(16.0));
//        GameplayChallenges gameplay = new GameplayChallenges(0,700 , 9/(16.0));
//        gameplay.init(datatable)
        System.out.println(node);
        System.out.println(gameplay);
        System.out.println(stage);

        stage.setTitle("gameplay");
        stage.setScene(gameplay.getMainScene());
        stage.show();
        //        VBox layout1= new VBox(20);
//
//        Label gameplay = new Label("Gameplay here");
//        layout1.getChildren().add(gameplay);
//
//        mainRoot.getChildren().setAll(layout1);
    }
    @FXML
    void loadGame(MouseEvent event) throws Exception{
       AnchorPane pane2= FXMLLoader.load(getClass().getResource("loadgame.fxml"));

        Node node=(Node) event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        Scene mainScene =new Scene(pane2, 400, 700);
        stage.setTitle("gameplay");
        stage.setScene(mainScene);
        stage.show();
    } 

}
