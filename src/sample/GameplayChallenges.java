package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.animations.Disintegration;
import sample.animations.StarCollected;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GameplayChallenges implements Serializable {
    Random rand = new Random();
    final int presetLength = 7;
    int center = 200;

    boolean disentegrated = false;
    Player pl;
    private Scene mainScene;

    Queue<GameElement> obstacles= new LinkedList<>();
    Group ObstaclesRoot;
    Label score;

    int curscore=0;

    private Popup popup;
    public int initiateChallenge(int challenge, int posY) {

        ObstacleFactory.OB_dist N = ObstacleFactory.CreateRandomObstacle(challenge, posY,curscore);

        ArrayList<GameElement> NEW = N.getObstacleList();
        int NEW_LENGTH = NEW.size();
        for (GameElement obx: NEW) {
            obstacles.add(obx);
            ObstaclesRoot.getChildren().add(obx.getRoot());
            obx.getAnimation().play();
        }
        FinishLine fin = new FinishLine(200);// change posY according to obstacles
        ObstaclesRoot.getChildren().add(fin.getRoot());
        obstacles.add(fin);
        return NEW_LENGTH;
    }

//    void handleCollisions(Player  pla) {
//        GameElement toBeRemoved = null;  // remove star and colorchanger
//        for (GameElement node : obstacles ) {
//            int status = node.checkCollision(pl);
//
//            if (status < 0) {
//                if (!disentegrated) {
////                    System.out.print("circle ");
//                    Disintegration dis = new Disintegration(pl, 15);
//                    dis.getAnimation().play();
//                    ObstaclesRoot.getChildren().add(dis.getRoot());
//                    disentegrated = true;
//                    System.out.println("detected");
//                }
//            } else if (status == 2) {
//                System.out.println("STAR STAR");
//                StarCollected col = new StarCollected(pl, 5);
//
//                ObstaclesRoot.getChildren().add(col.getRoot());
//                col.getAnimation().play();
//
//                ObstaclesRoot.getChildren().remove(node.getRoot());
//                toBeRemoved = node;
//
//                curscore++;
//                String s = "" + curscore;
//                score.setText(s);
//            } else if (status == 3) {
//            }
//        }
//        if (toBeRemoved!=null) obstacles.remove(toBeRemoved);
//    }

    GameplayChallenges (int challengeNo ,int height, double ratio) {
        ObstaclesRoot = new Group();
        initiateChallenge(challengeNo, 500);

        pl = initiatePlayer();
        ObstaclesRoot.getChildren().add(pl.getBall());

        Group MainRoot = new Group();

        score = new Label("0");
        score.setFont(Font.font("Bookshelf Symbol 7",40));
        score.setTextFill(Paint.valueOf("#fff9f9"));
        score.setTranslateY(5);

        MainRoot.getChildren().add(makePauseButton(80,-250));
        MainRoot.getChildren().add(makeStarCount(50,25));
        MainRoot.getChildren().add(score);

        MainRoot.getChildren().add(ObstaclesRoot);


        double speed = (1200)/(6000.0);
        float dist = 1200f;
        TranslateTransition tt = new TranslateTransition(Duration.millis(dist/speed), ObstaclesRoot);
        tt.setByY(dist);
        tt.setCycleCount(1);
        tt.setInterpolator(Interpolator.LINEAR);

        mainScene =new Scene(MainRoot, height*ratio, height);
        mainScene.addEventHandler(KeyEvent.KEY_PRESSED,eventHandler);

        Timeline gameLoop = new Timeline(
            new KeyFrame(Duration.millis(16),  // could be improved for performance
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        double yPos =  pl.getBall().getTranslateY()+ ObstaclesRoot.getTranslateY();
                        //postion of ball from top of screen .
                        if (yPos < -425) tt.play();
                        else tt.pause();

                        handleCollisions(pl);
                    }
                }
            )
        );

        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
        mainScene.setFill(Color.web("272727"));
    }
    void handleCollisions(Player  pl) {

        GameElement toBeRemoved = null;  // remove star and colorchanger
//        System.out.println(obstacles.size()+" "+ObstaclesRoot.getChildren().size());
        for (GameElement node : obstacles ) {
            // detecting collision goes here.
            int status = node.checkCollision(pl);
//            int status  = 3;
            if (status < 0) {
                if (!disentegrated) {
                    //                    System.out.print("circle ");
                    Disintegration dis =  new Disintegration(pl, 10);
                    dis.getAnimation().play();
                    if(node.getClass().getName().equals("Sample.FinishLine")){
                        Label l2= new Label("Challenge Completed");
                    }
                    else{
                        Label l2= new Label("You lost");
                    }
                    ObstaclesRoot.getChildren().add(dis.getRoot());
                    disentegrated = true;
                    System.out.println("detected");
//                    initiateDeathSequence () ;
                    toBeRemoved = node;
                    ObstaclesRoot.getChildren().remove(node.getRoot());
                }
            }
            else if (status == 1) {
                System.out.println("STAR STAR");
                StarCollected col =  new StarCollected(pl, 5);
                col.getAnimation().play();

                curscore++;
                String s= "" +curscore;
                score.setText(s);

                ObstaclesRoot.getChildren().add(col.getRoot());
                ObstaclesRoot.getChildren().remove(node.getRoot());


                toBeRemoved = node;
            }
            else if (status == 2) {
                System.out.println("COLOR CHANGER");
                pl.changeColor();
                ObstaclesRoot.getChildren().remove(node.getRoot());
                toBeRemoved = node;
            }

        }
        if (toBeRemoved!=null) obstacles.remove(toBeRemoved);
    }

    Node makePauseButton(int x, int y) {
        String svg = "m282.824 0c-155.947 0-282.824 126.877-282.824 282.824s126.877 282.824 282.824 282.824 282.824-126.877 282.824-282.824-126.877-282.824-282.824-282.824zm-35.353 388.883h-70.706v-212.118h70.706zm141.412 0h-70.706v-212.118h70.706z";
        SVGPath pause = new SVGPath();
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource(
                "PauseView.fxml"
            )
        );

        pause.setCursor(Cursor.OPEN_HAND);
        AnchorPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PauseView controller = loader.getController();
        controller.initData(this);

        popup = new Popup();
        if (pane != null )popup.getContent().add(pane);

        EventHandler<MouseEvent> event =
                new EventHandler<MouseEvent>() {

                    public void handle(MouseEvent e)
                    {
                        Node node=(Node) e.getSource();
                        Stage stage=(Stage) node.getScene().getWindow();
                        if (!popup.isShowing()) {
                            popup.show(stage);
                            ColorAdjust colorAdjust2 = new ColorAdjust();
                            colorAdjust2.setBrightness(-0.6);
                            mainScene.getRoot().setEffect(colorAdjust2);
                        }

                    }
                };

        pause.setOnMouseClicked(event);
        pause.setContent(svg);
        pause.setFill(Color.LIGHTGRAY);
        double ratio = 0.06;
        pause.setScaleY(ratio);
        pause.setScaleX(ratio);
        pause.setTranslateX(x);
        pause.setTranslateY(y);
        return pause;
    }

    Node makeStarCount(int x,int y) {
        Star StarImage = new Star(x,y,Color.LIGHTGRAY,1.1);
        double ratio = 0.06;
        StarImage.setScaleStar(1.5,1.5);
        return StarImage.getRoot();
    }

    EventHandler<KeyEvent> eventHandler;

    Player initiatePlayer () {
        Player pl1 = new Player(Color.web("#FF0181"),center,600);
        Circle ball = pl1.getBall();
        eventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
//                ball.setFill(ball.getFill() == Color.LIME ? Color.HOTPINK : Color.LIME);
                pl1.handleJumpEvent();
            }
        };
//        pl1.getAnimation().play();
        return pl1;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public void hidePopup() {

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0);
        mainScene.getRoot().setEffect(colorAdjust);

        this.popup.hide();
    }

    public Popup getPopup() {
        return popup;
    }
}
