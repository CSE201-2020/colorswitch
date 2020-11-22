package sample.Obstacles;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class PlusObstacle {
    final private Group root;
    final private RotateTransition animation;
    final private Color colors [] = {Color.LIMEGREEN,Color.HOTPINK,Color.BLUE,Color.RED};
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

    public RotateTransition getAnimation() {
        return animation;
    }

    public Group getRoot() {
        return root;
    }
}

