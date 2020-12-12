package sample.Obstacles;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;
import sample.Obstacle;
import sample.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class CircleThingy extends Obstacle {
    final private Group root = new Group();
    final private ParallelTransition animation ;
    final private ArrayList<Circle> circleArrayList = new ArrayList<>();

    final private Color colors [] = {Color.web("#FAE100"),Color.web("#FF0181"),Color.web("#32DBF0"),Color.web("#900DFF")};

    public CircleThingy (int radius, int direction, int posX, int posY, int type) {

        int N_per_SIDE = 5;
        int SIDE = 3;
        ArrayList<Double> points = new ArrayList<>();
        switch (type) {
            case 0:
                // Triangle
                double upY = posY + (Math.pow(3,-0.5)*15* radius);
                double downY = posY - (Math.pow(3,-0.5)*7.5* radius);
                points.addAll(
                    Arrays.asList(
                        posX - (7.5*radius), downY,
                        posX + (7.5*radius), downY,
                        (double)posX, upY)
                    );
                break;
            case 1:
                // inverted triangle
                double InvertedupY = posY + (Math.pow(3,-0.5)*7.5* radius);
                double InverteddownY = posY - (Math.pow(3,-0.5)*15* radius);

                points.addAll(
                    Arrays.asList(
                        posX - (7.5*radius), InvertedupY,
                        posX + (7.5*radius), InvertedupY,
                        (double)posX, InverteddownY
                    )
                );
                break;
            case 2:
                // square
                SIDE = 4;
                points.addAll(
                    Arrays.asList(
                        posX + (7.5*radius), posY - (7.5*radius),
                        posX - (7.5*radius), posY - (7.5*radius),
                        posX - (7.5*radius), posY + (7.5*radius),
                        posX + (7.5*radius), posY + (7.5*radius)
                    )
                );
                break;
            case 3:
                // infinity-shaped
                break;
        }

        if (direction<0) Collections.reverse(points);
        Polygon path = new Polygon();
        path.getPoints().addAll(points);

        int N_CIRCLE = SIDE * N_per_SIDE;

        this.animation = new ParallelTransition();
        int mod = N_CIRCLE / 4;
        for (int i =0 ; i < N_CIRCLE ; i++) {
            Circle unitCircle = new Circle(posX, posY, radius);
            unitCircle.setFill(colors[i/mod]);

            PathTransition anim = new PathTransition();
            anim.setDuration(Duration.millis(5000));
            anim.setDelay(Duration.millis(i*5000.0/(N_CIRCLE)));
            anim.setNode(unitCircle);
            anim.setPath(path);
            anim.setInterpolator(Interpolator.LINEAR);
//        animation.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            anim.setCycleCount(1000);

            this.animation.getChildren().add(anim);
            root.getChildren().add(unitCircle);
            circleArrayList.add(unitCircle);
        }


    }


    @Override
    public Group getRoot() {
        return this.root;
    }

    @Override
    public int checkCollision(Player player) {
        Circle ball = player.getBall();
        for (Circle arc : this.circleArrayList){
            Shape intersected = Shape.intersect(arc,ball);
            if (!arc.getFill().equals(ball.getFill())) {
//                System.out.print(arc.getStroke()+" "+ball.getFill());
                if (intersected.getBoundsInLocal().getWidth() != -1) {
                    System.out.print("Different Color");
                    player.getAnimation().pause();
                    this.animation.pause();
                }
            }
        }
        return 0;
    }

    @Override
    public Transition getAnimation() {
        return this.animation;
    }
}
