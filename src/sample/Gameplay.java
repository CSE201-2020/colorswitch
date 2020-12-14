package sample;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.util.Duration;
import sample.Obstacles.CircleObstacle;
import sample.Obstacles.HorizontalLineObstacle;
import sample.Obstacles.PlusObstacle;
import sample.Obstacles.CircleThingy;
import sample.animations.Disintegration;
import sample.animations.StarCollected;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Gameplay implements Serializable {
    ObstacleFactory Factory ;
    Random rand = new Random();
    final int presetLength = 7;
    int currentPos = -1600 ;
    boolean disentegrated = false;
    Player pl;
    private Scene mainScene;
    Queue<GameElement> obstacles= new LinkedList<>();
    Group ObstaclesRoot;
    int center = 200;
    Label score;
    int curscore=0;

    Gameplay (int height, double ratio) {
        ObstaclesRoot = initiateTestObstacles();
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
                                if(yPos > -10) pl.getAnimation().pause();
                                if (pl.getBall().getTranslateY() < currentPos) addNewObstacles(currentPos -= 200);

                                handleCollisions(pl);
                            }
                        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
        mainScene.setFill(Color.web("272727"));
    }
    int addNewObstacles(int posY) {
        int preset = rand.nextInt(presetLength);
//        preset = 4;
        ArrayList<Obstacle> NEW = ObstacleFactory.CreateRandomObstacle(preset, posY);
        int NEW_LENGTH = NEW.size();
        // gotta delete NEW_LENGTH from queue to save memory.
        for (int i =0;i< NEW_LENGTH;i++) {
            GameElement toRemove = obstacles.poll();
            ObstaclesRoot.getChildren().remove(toRemove.getRoot());
        }
        //check queue size ::
        //System.out.println(obstacles.size()+" "+" "+ObstaclesRoot.getChildren().size());
        for (Obstacle obx: NEW) {
            obstacles.add(obx);
            ObstaclesRoot.getChildren().add(obx.getRoot());
            obx.getAnimation().play();
        }
        return NEW_LENGTH;
    }
    int pink = Color.web("ff0181").hashCode();
    int grey = Color.web("272727").hashCode();
    void handleCollisions(Player  pl) {
//        Bounds boundsInScreen = pl.getBall().localToScreen(pl.getBall().getBoundsInLocal());
//        Color top =  robot.getPixelColor(boundsInScreen.getCenterX(),boundsInScreen.getMinY() - 3 );
//        Color bottom =  robot.getPixelColor(boundsInScreen.getCenterX(),boundsInScreen.getMaxY()+ 3);

//        if  (top.hashCode() != pink|| top.hashCode()!= grey )System.out.println("not OK");
//        System.out.println(pl.+ " " +pl.getBall().getTranslateY()+ ObstaclesRoot.getTranslateY());
        // death , adding stars and  colour changing.

        GameElement toBeRemoved = null;  // remove star and colorchanger
        for (GameElement node : obstacles ){
            // detecting collision goes here.
//  ----  saksham 
//             int status = node.checkCollision(pl);

//             if (status < 0) {
//                 if (!disentegrated) {
// //                    System.out.print("circle ");
//                     Disintegration dis =  new Disintegration(pl, 10);
//                     dis.getAnimation().play();
//                     ObstaclesRoot.getChildren().add(dis.getRoot());
//                     disentegrated = true;
//                     System.out.println("detected");
//                 }
//             }
//             else if (status == 2) {
//                 System.out.println("STAR STAR");
//                 StarCollected col =  new StarCollected(pl, 5);
//                 col.getAnimation().play();
//                 ObstaclesRoot.getChildren().add(col.getRoot());
//                 ObstaclesRoot.getChildren().remove(node.getRoot());
//                 toBeRemoved = node;
//             }
//             else if (status == 3) {
//  ----  saksham
                //change color
            if (node.getRoot().intersects(pl.getBall().getBoundsInParent())) {
                if (node.getClass().getName().equals("sample.Obstacles"))System.out.print("circle ");
                if (node.getClass().getName().equals("sample.Star")){
                    System.out.print("Star ");
                    ObstaclesRoot.getChildren().remove(node.getRoot());
                    obstacles.remove(node);
                    curscore++;
                    String s= "" +curscore;
                    score.setText(s);
                }
                if (node.getClass().getName().equals("sample.Obstacles.PlusObstacle"))System.out.print("PO ");
                if (node.getClass().getName().equals("sample.Obstacles.HorizontalLineObstacle"))System.out.print("HL ");
                System.out.println("detected");
            }

        }
        if (toBeRemoved!=null) obstacles.remove(toBeRemoved);
    }

    Node makePauseButton(int x, int y) {
        String svg = "m282.824 0c-155.947 0-282.824 126.877-282.824 282.824s126.877 282.824 282.824 282.824 282.824-126.877 282.824-282.824-126.877-282.824-282.824-282.824zm-35.353 388.883h-70.706v-212.118h70.706zm141.412 0h-70.706v-212.118h70.706z";
        SVGPath pause = new SVGPath();

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

    Group initiateTestObstacles () {
        CircleObstacle obs = new CircleObstacle(100,-1,24,center,200);
        CircleObstacle obs2 = new CircleObstacle(100,1,24,center,-200);
        Star star = new Star(center, 200,Color.RED,1.1);
        PlusObstacle plus0 = new PlusObstacle(60,1,10,center + 60,-600);
        PlusObstacle plus1 = new PlusObstacle(120,1,20,center - 120 ,-1000);
        HorizontalLineObstacle hor0 = new HorizontalLineObstacle(100,1,10,-400,-1200);
        CircleThingy test = new CircleThingy(15,1,center,-1600,0);
        //This is the star for collection not score.
        ColorChanger tary= new ColorChanger(center,-220);

        obs.getAnimation().play();
        obs2.getAnimation().play();
        plus0.getAnimation().play();
        plus1.getAnimation().play();
        star.getAnimation().play();
        test.getAnimation().play();

        obstacles.add(obs);
        obstacles.add(obs2);
        obstacles.add(star);
        obstacles.add(plus0);
        obstacles.add(plus1);
        obstacles.add(hor0);

        obstacles.add(test);
        obstacles.add(tary);


        Group ObstaclesRoot = new Group();
        ObstaclesRoot.getChildren().add(obs2.getRoot());
        ObstaclesRoot.getChildren().add(obs.getRoot());
        ObstaclesRoot.getChildren().add(plus0.getRoot());
        ObstaclesRoot.getChildren().add(star.getRoot());
        ObstaclesRoot.getChildren().add(hor0.getRoot());
        ObstaclesRoot.getChildren().add(plus1.getRoot());
        ObstaclesRoot.getChildren().add(test.getRoot());
        ObstaclesRoot.getChildren().add(tary.getRoot());

        return ObstaclesRoot;
    }

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
}
