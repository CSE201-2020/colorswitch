package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Obstacles.CircleObstacle;

import java.io.IOException;

public class DeathView {
    private GameplayChallenges gameplay;
    private Gameplay gameplay0;
    @FXML
    private SVGPath useStarsButton;
    @FXML
    private SVGPath goBack;
    @FXML
    private SVGPath retryButton;
    @FXML
    private Text Label0;
    @FXML
    private Text Label1;
    @FXML
    private SVGPath label2;
    public void initData (GameplayChallenges gameplay) {
        this.gameplay = gameplay;
    }
    public void initData0 (Gameplay gameplay) {
        this.gameplay0 = gameplay;
        int stars = gameplay.curscore;
        if (stars < 5) {
            Label0.setVisible(false);
            Label1.setVisible(false);
            label2.setVisible(false);
            useStarsButton.setVisible(false);
        }
    }
    public void goToHome(MouseEvent event) throws IOException {
        System.out.println("hello");
        AnchorPane pane= FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group MainRoot = new Group();
        CircleObstacle o1 = new CircleObstacle(15,-1,10,160,110);
        CircleObstacle o2 = new CircleObstacle(15,1,10,240,110);
        CircleObstacle outerc = new CircleObstacle(70,1,10,200,350);
        CircleObstacle innerc = new CircleObstacle(50,-1,10,200,350);
        CircleObstacle lastc = new CircleObstacle(90,-1,10,200,350);
        Group rooto1 = o1.getRoot();
        Group rooto2 = o2.getRoot();
        Group root3 = outerc.getRoot();
        Group root4= innerc.getRoot();
        Group root5= lastc.getRoot();
        MainRoot.getChildren().addAll(rooto1,rooto2,root5,root3,root4);

        o1.getAnimation().play();
        o2.getAnimation().play();
        outerc.getAnimation().play();
        innerc.getAnimation().play();
        lastc.getAnimation().play();
        Group root = new Group();
        root.getChildren().setAll(pane);
        root.getChildren().add(MainRoot);

        Stage stage=(Stage) this.gameplay.getMainScene().getWindow();
        // testing .... actually set old scene
        if (gameplay != null) {
            this.gameplay.getMainScene().setRoot(root);
        }
        if (gameplay0 != null) {
            this.gameplay0.getMainScene().setRoot(root);
        }

    }
    public void retry() {
        if (gameplay != null) {
            this.gameplay.hidePopup();

        }
        if (gameplay0 != null) {
            this.gameplay0.hidePopup();
            Stage stage = (Stage) this.gameplay0.getMainScene().getWindow();
            Gameplay new_game = new Gameplay(700 , 9/(16.0), this.gameplay0.user);
//        GameplayChallenges gameplay = new GameplayChallenges(0,700 , 9/(16.0));
//        gameplay.init(datatable)
            System.out.println(stage);

            stage.setTitle("gameplay");
            stage.setScene(new_game.getMainScene());
            stage.show();
        }
    }

    public void useStars () {
        if (gameplay0 != null) {
            this.gameplay0.curscore -= 5;
            this.gameplay0.hidePopup();
            this.gameplay0.disentegrated = false;
        }
    }
}
