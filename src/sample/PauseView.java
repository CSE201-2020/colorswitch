package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import sample.Obstacles.CircleObstacle;

import java.io.IOException;

public class PauseView {
    private GameplayChallenges gameplay;
    private Gameplay gameplay0;
    @FXML
    private SVGPath saveButton;
    public void initData (GameplayChallenges gameplay) {
        this.gameplay = gameplay;
    }
    public void initData0 (Gameplay gameplay) {
        this.gameplay0 = gameplay;
    }
    public void goToHome(MouseEvent event) throws IOException {
        System.out.println("hello");
        hidePopup();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group MainRoot = new Group();
        CircleObstacle o1 = new CircleObstacle(15,-1,10,160,110);
        CircleObstacle o2 = new CircleObstacle(15,1,10,240,110);
        o1.getAnimation().setRate(1.5);
        o1.getAnimation().setRate(1.5);
        CircleObstacle outerc = new CircleObstacle(75,1,15,200,350);
        CircleObstacle innerc = new CircleObstacle(55,-1,15,200,350);
        CircleObstacle lastc = new CircleObstacle(95,-1,15,200,350);
        outerc.getAnimation().setRate(1.1);
        innerc.getAnimation().setRate(1.3);
        Group rooto1 = o1.getRoot();
        Group rooto2 = o2.getRoot();
        Group root3 = outerc.getRoot();
        Group root4= innerc.getRoot();
        Group root5= lastc.getRoot();
        MainRoot.getChildren().addAll(rooto1,rooto2,root5,root3,root4);

        o1.getAnimation().play();
        o2.getAnimation().play();
//        outerc.getAnimation().setRate(0.5);
        outerc.getAnimation().play();
//        innerc.getAnimation().setRate(0.5);
        innerc.getAnimation().play();
        lastc.getAnimation().setRate(0.8);
        lastc.getAnimation().play();
        Group root = new Group();
        root.getChildren().setAll(pane);
        root.getChildren().add(MainRoot);

//        Stage stage=(Stage) this.gameplay.getMainScene().getWindow();
        // testing .... actually set old scene
        if (gameplay != null) {
            this.gameplay.getMainScene().setRoot(root);
        }
        if (gameplay0 != null) {
            this.gameplay0.getMainScene().setRoot(root);
        }

    }
    public void hidePopup() {
        if (gameplay != null) {
            this.gameplay.hidePopup();
        }
        if (gameplay0 != null) {
            this.gameplay0.hidePopup();
        }
    }

    public void saveGame () {
        if (gameplay != null) {
            this.gameplay.hidePopup();
            System.out.println("cant hide gameplay");
        }
        if (gameplay0 != null) {
            this.gameplay0.hidePopup();
            this.gameplay0.saveGame();
        }
    }
}
