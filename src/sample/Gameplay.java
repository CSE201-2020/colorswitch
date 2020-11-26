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
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;
import sample.Obstacles.CircleObstacle;
import sample.Obstacles.HorizontalLineObstacle;
import sample.Obstacles.PlusObstacle;

import java.util.ArrayList;

public class Gameplay {
    private Scene mainScene;
    final private ArrayList<Obstacle> ObstacleList = new ArrayList<Obstacle>();

    void addNewObstacles() {
        Obstacle random = new CircleObstacle(60,-1,10,100,200);
        ObstacleList.add(random);
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

    Node makeStarCount() {
        Star StarImage = new Star(350,40,Color.GREEN,1.1);
        return StarImage.getRoot();
    }

    EventHandler<KeyEvent> eventHandler;

    Group initiateTestObstacles () {
        CircleObstacle obs = new CircleObstacle(60,-1,10,center,200);
        CircleObstacle obs2 = new CircleObstacle(60,1,10,center,-100);
        Star star = new Star(center, 200,Color.RED,1.1);
        PlusObstacle plus0 = new PlusObstacle(60,1,10,center,-400);
        PlusObstacle plus1 = new PlusObstacle(60,1,10,center,-700);
        HorizontalLineObstacle hor0 = new HorizontalLineObstacle(100,1,10,-400,-1000);

        obs.getAnimation().play();
        obs2.getAnimation().play();
        plus0.getAnimation().play();
        plus1.getAnimation().play();
        star.getAnimation().play();



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
                ball.setFill(ball.getFill() == Color.LIME ? Color.HOTPINK : Color.LIME);
                pl1.handleJumpEvent();
            }
        };
        pl1.getAnimation().play();
        return pl1;
    }

    int center = 200;

    Gameplay (int height, double ratio) {
        Group ObstaclesRoot = initiateTestObstacles();
        Player pl = initiatePlayer();

        ObstaclesRoot.getChildren().add(pl.getBall());
        Group MainRoot = new Group();

        MainRoot.getChildren().add(makePauseButton(-230,-240));
        MainRoot.getChildren().add(makeStarCount());
        MainRoot.getChildren().add(ObstaclesRoot);
      
        TranslateTransition tt = new TranslateTransition(Duration.millis(5000), ObstaclesRoot);
        tt.setByY(1200f);
        tt.setCycleCount(1);
        tt.setInterpolator(Interpolator.LINEAR);
        tt.pause();

        mainScene =new Scene(MainRoot, height*ratio, height);
        PerspectiveCamera camera = new PerspectiveCamera();

        mainScene.setCamera(camera);

        mainScene.addEventHandler(KeyEvent.KEY_PRESSED,eventHandler);

        Timeline gameLoop = new Timeline(
                new KeyFrame(Duration.millis(41),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                double yPos =  pl.getBall().getTranslateY()+ ObstaclesRoot.getTranslateY();
                                //System.out.println(yPos);
                                if (yPos < -350) tt.play();
                                else tt.pause();

                                for (Node node : ObstaclesRoot.getChildren() ){
                                    if (node == pl.getBall()) continue;
                                    // detecting collision goes here.
                                }
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
