package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
        int center = 200;
    Gameplay (int height, double ratio) {

        CircleObstacle obs = new CircleObstacle(60,-1,10,center,200);
        CircleObstacle obs2 = new CircleObstacle(60,1,10,center,-100);
        Star star = new Star(center, 200,Color.RED,1.1);
        PlusObstacle plus0 = new PlusObstacle(60,1,10,center,-400);
        PlusObstacle plus1 = new PlusObstacle(60,1,10,center,-700);
        HorizontalLineObstacle hor0 = new HorizontalLineObstacle(100,1,10,-400,-1000);
        Player pl1 = new Player(Color.HOTPINK,center,600);
        Circle ball = pl1.getBall();

        Group MainRoot = new Group();
        Group ObstaclesRoot = new Group();
        ObstaclesRoot.getChildren().add(obs2.getRoot());
        ObstaclesRoot.getChildren().add(obs.getRoot());
        ObstaclesRoot.getChildren().add(plus0.getRoot());
        ObstaclesRoot.getChildren().add(star.getRoot());
        ObstaclesRoot.getChildren().add(hor0.getRoot());
        ObstaclesRoot.getChildren().add(plus1.getRoot());

        MainRoot.getChildren().add(ball);
        MainRoot.getChildren().add(ObstaclesRoot);

        TranslateTransition tt = new TranslateTransition(Duration.millis(10000), MainRoot);
        tt.setByY(1200f);
        tt.setCycleCount(1);

//        tt.setAutoReverse(true);
        //tt.play();

        mainScene =new Scene(MainRoot, height*ratio, height);
        PerspectiveCamera camera = new PerspectiveCamera();
//        camera.setTranslateZ(-1000);
//        camera.setNearClip(0.1);
//        camera.setRotate(45);
//        camera.setFarClip(2000);
//        camera.setFieldOfView(45);
//        System.out.println(root.getBoundsInParent());
        mainScene.setCamera(camera);
//        camera.setTranslateY(camera.getTranslateY0);


        EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                ball.setFill(ball.getFill() == Color.LIME ? Color.HOTPINK : Color.LIME);
                pl1.handleJumpEvent();
            }
        };
        mainScene.addEventHandler(KeyEvent.KEY_PRESSED,eventHandler);

        Timeline fiveSecondsWonder = new Timeline(
                new KeyFrame(Duration.millis(200),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                double yPos =  ball.getTranslateY()+ MainRoot.getTranslateY();
                                System.out.println(yPos);
                                if (yPos < -300) tt.play();
                                else tt.pause();
                            }
                        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();

        pl1.getAnimation().play();
        obs.getAnimation().play();
        obs2.getAnimation().play();
        plus0.getAnimation().play();
        plus1.getAnimation().play();
        star.getAnimation().play();
    }

    public Scene getMainScene() {
        return mainScene;
    }
}
