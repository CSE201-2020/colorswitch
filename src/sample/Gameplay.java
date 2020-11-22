package sample;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sample.Obstacles.CircleObstacle;
import sample.Obstacles.PlusObstacle;

public class Gameplay {
    private Scene mainScene;
    Gameplay (int height, double ratio) {

        CircleObstacle obs = new CircleObstacle(60,-1,10,100,200);
        CircleObstacle obs1 = new CircleObstacle(60,-1,10,100,200);
        CircleObstacle obs2 = new CircleObstacle(60,1,10,250,200);
        CircleObstacle obs3 = new CircleObstacle(60,1,10,250,200);
        PlusObstacle plus0 = new PlusObstacle(60,1,10,100,200);
        Group root = obs.getRoot();
        Group root2 = obs2.getRoot();

        Group MainRoot = new Group();
        MainRoot.getChildren().add(root2);
        MainRoot.getChildren().add(root);
        MainRoot.getChildren().add(plus0.getRoot());

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
        Player pl1 = new Player(Color.HOTPINK);
        Circle ball = pl1.getBall();

        EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                System.out.println(ball.getTranslateY());
                ball.setFill(ball.getFill() == Color.LIME ? Color.HOTPINK : Color.LIME);
                pl1.handleJumpEvent();
            }
        };
        MainRoot.getChildren().add(ball);
        mainScene.addEventHandler(KeyEvent.KEY_PRESSED,eventHandler);
        pl1.getAnimation().play();

        obs.getAnimation().play();
        obs2.getAnimation().play();
        plus0.getAnimation().play();
    }

    public Scene getMainScene() {
        return mainScene;
    }
}
