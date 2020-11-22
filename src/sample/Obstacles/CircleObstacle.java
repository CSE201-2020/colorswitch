package sample.Obstacles;


import javafx.animation.Interpolator;
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
import sample.Obstacle;

public class CircleObstacle extends Obstacle {
    final private Group root;
    final private RotateTransition animation;
    final private Color colors [] = {Color.LIMEGREEN,Color.HOTPINK,Color.BLUE,Color.RED};
    public CircleObstacle(int radius, int direction, int thickness, int posX, int posY) {
        // Creating Circle
        root = new Group();
        int deg = 0;

        for (int i = 0 ;i < 4; ++i) {
            Arc rect = new Arc(posX,posY,radius,radius,90*i,90);

            rect.setStroke(colors[i]);
            rect.setFill(Color.WHITE);
            rect.setStrokeWidth(thickness);
            root.getChildren().add(rect);

        }

        //Instantiating RotateTransition class
        animation = new RotateTransition();

        //Setting Axis of rotation
        animation.setAxis(Rotate.Z_AXIS);

        // setting the angle of rotation
        animation.setByAngle(direction>0? 360 :-360);

        //setting cycle count of the rotation
        animation.setCycleCount(450);

        //Setting duration of the transition
        animation.setDuration(Duration.millis(2000));
        animation.setInterpolator(Interpolator.LINEAR);

        //the transition will be auto reversed by setting this to true
        //rotate.setAutoReverse(true);

        //setting Rectangle as the node onto which the
// transition will be applied
        animation.setNode(root);
        //playing the transition

    }

    public Group getRoot() {
        return root;
    }

    public RotateTransition getAnimation() {
        return animation;
    }

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        // TODO Auto-generated method stub
//
//
//        //Configuring Group and Scene
////
////        Scene scene = new Scene(root,600,400,Color.BLACK);
////        primaryStage.setScene(scene);
////        primaryStage.setTitle("Rotate Transition example");
////        primaryStage.show();
//
//    }
}
