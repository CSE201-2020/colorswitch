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

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Controller {
    @FXML
    private AnchorPane mainRoot;
    private AnchorPane tempRoot;
    @FXML
    private ImageView startGame;

    @FXML
    private ImageView loadGame;
    @FXML
    private ImageView trophy;
    Gameplay gameplay;
    User user ;
    static ArrayList<User> copyList = new ArrayList<>();
    void initUserSpace (User user) {
        this.user = user;
    }
    @FXML
    void startGame(MouseEvent event) throws Exception {
        Node node=(Node) event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        gameplay = new Gameplay(700 , 9/(16.0), this.user);
//        GameplayChallenges gameplay = new GameplayChallenges(0,700 , 9/(16.0));
//        gameplay.init(datatable)
        System.out.println(node);
        System.out.println(gameplay);
        System.out.println(stage);

        stage.setTitle("gameplay");
        stage.setScene(gameplay.getMainScene());
        stage.show();
    }
    @FXML
    void latestGame(MouseEvent event) throws Exception {
        Node node=(Node) event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        //deserialize from this.user
        Map.Entry<Date, Gameplay.DB> entry = this.user.getGamelist().entrySet().iterator().next();
        Gameplay.DB gameInfo = entry.getValue();
        gameplay = new Gameplay(700 , 9/(16.0), this.user, gameInfo);
//        GameplayChallenges gameplay = new GameplayChallenges(0,700 , 9/(16.0));
//        gameplay.init(datatable)
        System.out.println(node);
        System.out.println(gameplay);
        System.out.println(stage);

        stage.setTitle("gameplay");
        stage.setScene(gameplay.getMainScene());
        stage.show();
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
    @FXML
    void loadChallenges(MouseEvent event) throws Exception{
       AnchorPane pane2= FXMLLoader.load(getClass().getResource("loadgame.fxml"));

        Node node=(Node) event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        Scene mainScene =new Scene(pane2, 400, 700);
        stage.setTitle("gameplay");
        stage.setScene(mainScene);
        stage.show();
    }

}
