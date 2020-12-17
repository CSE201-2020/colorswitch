package sample.Obstacles;


import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Obstacle;
import sample.Player;
import sample.animations.Disintegration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class CircleObstacle extends Obstacle {
    final private Group root;
    final private RotateTransition animation;
    final private ArrayList<Arc> arcArrayList = new ArrayList<>();
    final private ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.web("#FAE100"),Color.web("#FF0181"),Color.web("#32DBF0"),Color.web("#900DFF")));
    public CircleObstacle(int radius, int direction, int thickness, int posX, int posY) {
        // Creating Circle
        root = new Group();
        int deg = 0;
        for (int i = 0 ;i < 4; ++i) {
            Arc rect = new Arc(posX,posY,radius,radius,90*i,90);
            rect.setType(ArcType.OPEN);
            rect.setStrokeLineCap(StrokeLineCap.BUTT);
            rect.setStroke(colors.get(i));
            rect.setFill(Color.TRANSPARENT);
            rect.setStrokeWidth(thickness);
            root.getChildren().add(rect);
            this.arcArrayList.add(rect);


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

    public CircleObstacle(int radius, int direction, int thickness, int posX, int posY, boolean partOf2) {
        // Creating Circle
        root = new Group();
        int deg = 0;

        if (partOf2) {
            Collections.reverse(colors);
            Collections.rotate(colors,2);
        }
        for (int i = 0 ;i < 4; ++i) {
            Arc rect = new Arc(posX,posY,radius,radius,90*i,90);
            rect.setType(ArcType.OPEN);
            rect.setStrokeLineCap(StrokeLineCap.BUTT);
            rect.setStroke(colors.get(i));
            rect.setFill(Color.TRANSPARENT);
            rect.setStrokeWidth(thickness);
            root.getChildren().add(rect);
            this.arcArrayList.add(rect);
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
    public int checkCollision(Player player) {
        Circle ball = player.getBall();
        for (Arc arc : this.arcArrayList){
            Shape intersected = Shape.intersect(arc,ball);
            if (!arc.getStroke().equals(ball.getFill())) {
                if (intersected.getBoundsInLocal().getWidth() != -1) {
                    player.getAnimation().pause();
                    this.animation.pause();

                    return -1;
                }
            }
        }
        return 0;
    }
    public int checkCollision(Player player, boolean doublecircle) {
        Circle ball = player.getBall();
        for (Arc arc : this.arcArrayList){
            Shape intersected = Shape.intersect(arc,ball);
            if (!arc.getStroke().equals(ball.getFill())) {
                if (intersected.getBoundsInLocal().getWidth() != -1) {
                    player.getAnimation().pause();
//                    this.animation.pause();

                    return -1;
                }
            }
        }
        return 0;
    }

}
