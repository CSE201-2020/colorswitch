package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sample.Obstacles.CircleObstacle;

public class Main extends Application {
    final private int height = 700 ;
    final private double ratio = (9/16.0) ;


    @Override
    public void start(Stage primaryStage) throws Exception{
        CircleObstacle obs = new CircleObstacle(60,-1,10,100,200);
        CircleObstacle obs1 = new CircleObstacle(60,-1,10,100,200);
        CircleObstacle obs2 = new CircleObstacle(60,1,10,250,200);
        CircleObstacle obs3 = new CircleObstacle(60,1,10,250,200);
        Group root = obs.getRoot();
        Group root2 = obs2.getRoot();

        Group MainRoot = new Group();
        MainRoot.getChildren().add(root2);
        MainRoot.getChildren().add(root);

        Scene mainScene =new Scene(MainRoot, height*ratio, height);
        PerspectiveCamera camera = new PerspectiveCamera();
//        camera.setTranslateZ(-1000);
//        camera.setNearClip(0.1);
//        camera.setRotate(45);
//        camera.setFarClip(2000);
//        camera.setFieldOfView(45);
//        System.out.println(root.getBoundsInParent());
        mainScene.setCamera(camera);
//        camera.setTranslateY(camera.getTranslateY0);
        primaryStage.setTitle("color");
        primaryStage.setScene(mainScene);

        primaryStage.show();
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
    }


    public static void main(String[] args) {
        launch(args);
    }
}
