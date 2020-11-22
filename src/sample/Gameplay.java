package sample;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sample.Obstacles.CircleObstacle;
import sample.Obstacles.HorizontalLineObstacle;
import sample.Obstacles.PlusObstacle;

public class Gameplay {
    private Scene mainScene;
    Gameplay (int height, double ratio) {

        CircleObstacle obs = new CircleObstacle(60,-1,10,100,200);
        CircleObstacle obs2 = new CircleObstacle(60,1,10,250,200);
        Star star = new Star(250, 200,Color.RED,1.1);
        PlusObstacle plus0 = new PlusObstacle(60,1,10,100,200);
        HorizontalLineObstacle hor0 = new HorizontalLineObstacle(100,1,10,-400,100);
        Player pl1 = new Player(Color.HOTPINK);
        Circle ball = pl1.getBall();
        Group MainRoot = new Group();
        MainRoot.getChildren().add(obs2.getRoot());
        MainRoot.getChildren().add(obs.getRoot());
        MainRoot.getChildren().add(plus0.getRoot());
        MainRoot.getChildren().add(star.getRoot());
        MainRoot.getChildren().add(ball);
        MainRoot.getChildren().add(hor0.getRoot());

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
                System.out.println(ball.getTranslateY());
                ball.setFill(ball.getFill() == Color.LIME ? Color.HOTPINK : Color.LIME);
                pl1.handleJumpEvent();
            }
        };
        mainScene.addEventHandler(KeyEvent.KEY_PRESSED,eventHandler);

        pl1.getAnimation().play();
        obs.getAnimation().play();
        obs2.getAnimation().play();
        plus0.getAnimation().play();
        star.getAnimation().play();
    }

    public Scene getMainScene() {
        return mainScene;
    }
}
