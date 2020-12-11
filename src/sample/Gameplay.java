package sample;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;
import sample.Obstacles.CircleObstacle;
import sample.Obstacles.HorizontalLineObstacle;
import sample.Obstacles.PlusObstacle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Gameplay implements Serializable {
    ObstacleFactory Factory ;
    Random rand = new Random();
    final int presetLength = 5;
    int currentPos = -1200 ;
    Player pl;
    private Scene mainScene;
    Queue<GameElement> obstacles= new LinkedList<>();
    Group ObstaclesRoot;


    void addNewObstacles(int posY) {
        int preset = rand.nextInt(presetLength);
//        preset = 4;
        ArrayList<Obstacle> NEW = ObstacleFactory.CreateRandomObstacle(preset, posY);
        int NEW_LENGTH = NEW.size();
        // gotta delete NEW_LENGTH from queue to save memory.
        for (int i =0;i< NEW_LENGTH;i++)obstacles.poll();
        for (Obstacle obx: NEW) {
            obstacles.add(obx);
            ObstaclesRoot.getChildren().add(obx.getRoot());
            obx.getAnimation().play();
        }
    }

    void handleCollisions(Player  pl) {

        // death , adding stars and  colour changing.
        for (GameElement node : obstacles ){
            // detecting collision goes here.
            if (node.getRoot().intersects(pl.getBall().getBoundsInParent())) {
                if (node.getClass().getName().equals("sample.Obstacles"))System.out.print("circle ");
                if (node.getClass().getName().equals("sample.Star"))System.out.print("Star ");
                if (node.getClass().getName().equals("sample.Obstacles.PlusObstacle"))System.out.print("PO ");
                if (node.getClass().getName().equals("sample.Obstacles.HorizontalLineObstacle"))System.out.print("HL ");
                System.out.println("detected");
            }

        }
    }

    Node makePauseButton(int x, int y) {
        String svg = "m282.824 0c-155.947 0-282.824 126.877-282.824 282.824s126.877 282.824 282.824 282.824 282.824-126.877 282.824-282.824-126.877-282.824-282.824-282.824zm-35.353 388.883h-70.706v-212.118h70.706zm141.412 0h-70.706v-212.118h70.706z";
        SVGPath pause = new SVGPath();

        pause.setContent(svg);
        pause.setFill(Color.GREEN);
        double ratio = 0.06;
        pause.setScaleY(ratio);
        pause.setScaleX(ratio);
        pause.setTranslateX(x);
        pause.setTranslateY(y);
        return pause;
    }

    Node makeStarCount(int x,int y) {
        Star StarImage = new Star(x,y,Color.GREEN,1.1);
        return StarImage.getRoot();
    }

    EventHandler<KeyEvent> eventHandler;

    Group initiateTestObstacles () {
        CircleObstacle obs = new CircleObstacle(60,-1,10,center,200);
        CircleObstacle obs2 = new CircleObstacle(60,1,10,center,-100);
        Star star = new Star(center, 200,Color.RED,1.1);
        PlusObstacle plus0 = new PlusObstacle(60,1,10,center + 60,-400);
        PlusObstacle plus1 = new PlusObstacle(60,1,10,center - 60 ,-700);
        HorizontalLineObstacle hor0 = new HorizontalLineObstacle(100,1,10,-400,-1000);

        obs.getAnimation().play();
        obs2.getAnimation().play();
        plus0.getAnimation().play();
        plus1.getAnimation().play();
        star.getAnimation().play();

        obstacles.add(obs);
        obstacles.add(obs2);
        obstacles.add(star);
        obstacles.add(plus0);
        obstacles.add(plus1);
        obstacles.add(hor0);


        Group ObstaclesRoot = new Group();
        ObstaclesRoot.getChildren().add(obs2.getRoot());
        ObstaclesRoot.getChildren().add(obs.getRoot());
        ObstaclesRoot.getChildren().add(plus0.getRoot());
        ObstaclesRoot.getChildren().add(star.getRoot());
        ObstaclesRoot.getChildren().add(hor0.getRoot());
        ObstaclesRoot.getChildren().add(plus1.getRoot());
        return ObstaclesRoot;
    }

    Player initiatePlayer () {
        Player pl1 = new Player(Color.HOTPINK,center,600);
        Circle ball = pl1.getBall();
        eventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
//                ball.setFill(ball.getFill() == Color.LIME ? Color.HOTPINK : Color.LIME);
                pl1.handleJumpEvent();
            }
        };
        pl1.getAnimation().play();
        return pl1;
    }

    int center = 200;

    Gameplay (int height, double ratio) {
        ObstaclesRoot = initiateTestObstacles();
         pl = initiatePlayer();

        ObstaclesRoot.getChildren().add(pl.getBall());
        Group MainRoot = new Group();

        MainRoot.getChildren().add(makePauseButton(-230,-240));
        MainRoot.getChildren().add(makeStarCount(350,40));
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
                                if (yPos < -425) tt.play();
                                else tt.pause();

                                if (pl.getBall().getTranslateY() < currentPos) addNewObstacles(currentPos -= 200);

                                handleCollisions(pl);
                            }
                        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
        mainScene.setFill(Color.web("272727"));
    }

    public Scene getMainScene() {
        return mainScene;
    }
}
