package sample.Obstacles;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import sample.Obstacle;
import sample.Player;

import java.util.ArrayList;

public class DoubleCircleObstacle extends Obstacle {
    int center = 200;

    final private Group root;
    final private ParallelTransition animation;
    final private Color colors [] = {Color.web("#FAE100"),Color.web("#FF0181"),Color.web("#32DBF0"),Color.web("#900DFF")};

    ArrayList<Object> args = new ArrayList<>();
    CircleObstacle left;
    public DoubleCircleObstacle(int radius, int direction, int thickness, int posX, int posY) {
        args.add(radius);args.add(direction);args.add(thickness);args.add(posX);args.add(posY);
        // Creating Circle
        root = new Group();
        left = new CircleObstacle(radius, direction, thickness,center + radius + thickness, posY,true);
        CircleObstacle right = new CircleObstacle(radius, -direction, thickness,center - radius - thickness, posY, false);
        root.getChildren().add(left.getRoot());
        root.getChildren().add(right.getRoot());

        animation =
                new ParallelTransition(left.getAnimation(), right.getAnimation());
//        animation.play();
    }

    public Group getRoot() {
        return root;
    }

    public ParallelTransition getAnimation() {
        return animation;
    }

    public int checkCollision(Player player) {
        return left.checkCollision(player );
    }

    @Override
    public ArrayList<Object> getArgs() {
        return args;
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