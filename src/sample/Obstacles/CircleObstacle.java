package sample.Obstacles;


import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CircleObstacle extends Application{

    final private Color colors [] = {Color.LIMEGREEN,Color.HOTPINK,Color.BLUE,Color.RED};

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

        // Creating Circle
        Group root = new Group();
        int deg = 0;

        for (int i = 0 ;i < 4; ++i) {
            Arc rect = new Arc(200,200,100,100,90*i,90);

            rect.setStroke(colors[i]);
            rect.setStrokeWidth(10);
            root.getChildren().add(rect);

        }

        //Instantiating RotateTransition class
        RotateTransition rotate = new RotateTransition();

        //Setting Axis of rotation
        rotate.setAxis(Rotate.Z_AXIS);

        // setting the angle of rotation
        rotate.setByAngle(360);

        //setting cycle count of the rotation
        rotate.setCycleCount(500);

        //Setting duration of the transition
        rotate.setDuration(Duration.millis(1000));

        //the transition will be auto reversed by setting this to true
        //rotate.setAutoReverse(true);

        //setting Rectangle as the node onto which the
// transition will be applied
        rotate.setNode(root);

        //playing the transition
        rotate.play();

        //Configuring Group and Scene

        Scene scene = new Scene(root,600,400,Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rotate Transition example");
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }

}
