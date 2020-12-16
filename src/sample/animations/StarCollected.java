package sample.animations;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sample.Player;
import sample.Star;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class StarCollected {
    Random rand = new Random();

    private final ParallelTransition animation;
    private final Group root = new Group();

    public StarCollected (Player player, int N_PIECES) {
        double initial_x = player.getBall().getBoundsInParent().getCenterX();
        double initial_y = player.getBall().getBoundsInParent().getCenterY();

        this.animation = new ParallelTransition();
        for (int i =0;i<N_PIECES; i++) {

            SVGPath starSVG = new SVGPath();
            starSVG.setContent("m510.652344 185.902344c-3.351563-10.367188-12.546875-17.730469-23.425782-18.710938l-147.773437-13.417968-58.433594-136.769532c-4.308593-10.023437-14.121093-16.511718-25.023437-16.511718s-20.714844 6.488281-25.023438 16.535156l-58.433594 136.746094-147.796874 13.417968c-10.859376 1.003906-20.03125 8.34375-23.402344 18.710938-3.371094 10.367187-.257813 21.738281 7.957031 28.90625l111.699219 97.960937-32.9375 145.089844c-2.410156 10.667969 1.730468 21.695313 10.582031 28.09375 4.757813 3.4375 10.324219 5.1875 15.9375 5.1875 4.839844 0 9.640625-1.304687 13.949219-3.882813l127.46875-76.183593 127.421875 76.183593c9.324219 5.609376 21.078125 5.097657 29.910156-1.304687 8.855469-6.417969 12.992187-17.449219 10.582031-28.09375l-32.9375-145.089844 111.699219-97.941406c8.214844-7.1875 11.351563-18.539063 7.980469-28.925781zm0 0");

            starSVG.setScaleX(0.03);
            starSVG.setScaleY(0.03);

            int distX = rand.nextInt(40) - 100;
            starSVG.setTranslateX(distX);
            starSVG.setFill(Color.AZURE);
            double opacity = (rand.nextInt(50) + 50.0 )/(100);
            starSVG.setOpacity(opacity);


            TranslateTransition lateralTransition = new TranslateTransition();
            lateralTransition.setNode(starSVG);
            lateralTransition.setInterpolator(Interpolator.LINEAR);
            lateralTransition.setDuration(Duration.millis(3000));
            lateralTransition.setByY(-2);
            lateralTransition.setCycleCount(0);

            RotateTransition rot = new RotateTransition(Duration.millis(2000), starSVG);
            rot.setByAngle(45);
            rot.setCycleCount(0);

            this.animation.getChildren().add(rot);
            this.animation.getChildren().add(lateralTransition);
            this.root.getChildren().add(starSVG);
        }
        Text t = new Text(initial_x -50, initial_y - 50, "+1");
        t.setFont(new Font(20));
        this.root.getChildren().add(t);


    }

    public ParallelTransition getAnimation() {
        return this.animation;
    }

    public Group getRoot() {
        return root;
    }
}