package sample.Obstacles;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import sample.Obstacle;
import sample.Player;

import java.util.ArrayList;

public class PlusObstacle extends Obstacle {
    final private Group root;
    final private RotateTransition animation;
    final private ArrayList<Line> lineArrayList = new ArrayList<>();
    final private Color colors [] = {Color.web("#FAE100"),Color.web("#FF0181"),Color.web("#32DBF0"),Color.web("#900DFF")};
    public PlusObstacle(int radius, int direction, int thickness, int posX, int posY) {
        root = new Group();

        for (int i = 0 ;i < 4; ++i) {
            Line line = new Line();
            line.setStrokeWidth(thickness);
            line.setStartX(posX);
            line.setStartY(posY);
            line.setStroke(colors[i]);
            line.setEndX(i%2==0 ? (i == 0? posX + radius: posX -radius ): posX);
            line.setEndY(i%2==0 ? posY : (i == 1? posY - radius : posY + radius) );
            root.getChildren().add(line);

            this.lineArrayList.add(line);

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

    }

    public RotateTransition getAnimation() {
        return animation;
    }

    public Group getRoot() {
        return root;
    }

    public int checkCollision(Player player) {
        Circle ball = player.getBall();
        for (Line arc : this.lineArrayList){
            Shape intersected = Shape.intersect(arc,ball);
            if (!arc.getStroke().equals(ball.getFill())) {
                System.out.print(arc.getStroke()+" "+ball.getFill());
                if (intersected.getBoundsInLocal().getWidth() != -1) {
                    System.out.print("Different Color");
                    player.getAnimation().pause();
                    this.animation.pause();
                }
            }
        }
        return 0;
    }
}

