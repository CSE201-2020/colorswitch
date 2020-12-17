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
        int currentStars = this.gameplay0.curscore;
        this.gameplay0.user.setHighest(Math.max(currentStars, this.gameplay0.user.getHighest()));
        this.gameplay0.user.setTotalStars(this.gameplay0.user.getTotalstars()+currentStars);
        this.gameplay0.saveGame();

        Group root = new Group();
        hidePopup();
        // need to make the main page user specific
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "sample.fxml"
                )
        );
        AnchorPane pane= loader.load();
        Controller controller = loader.getController();

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

        controller.initUserSpace(this.gameplay0.user);

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
        this.gameplay0.hidePopup();

    }
    public void retry() {
        if (gameplay != null) {
            this.gameplay.hidePopup();

        }
        if (gameplay0 != null) {
            int currentStars = this.gameplay0.curscore;
            this.gameplay0.user.setHighest(Math.max(currentStars, this.gameplay0.user.getHighest()));
            this.gameplay0.user.setTotalStars(this.gameplay0.user.getTotalstars()+currentStars);
            this.gameplay0.hidePopup();
            this.gameplay0.saveGame();
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
    public void hidePopup() {
        if (gameplay != null) {
            this.gameplay.hidePopup();
        }
        if (gameplay0 != null) {
            this.gameplay0.hidePopup();
        }
    }

}
