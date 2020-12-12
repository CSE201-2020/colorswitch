package sample.animations;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import sample.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Disintegration {
    Random rand = new Random();
    Interpolator interpolator = new Interpolator() {
        @Override
        protected double curve(double t) {
            return -t*2 * (1 - 3*t);
        }

    };

    private final ParallelTransition animation;
    private final Group root = new Group();

    final private ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.web("#FAE100"),Color.web("#FF0181"),Color.web("#32DBF0"),Color.web("#900DFF")));

    public Disintegration (Player player, int N_PIECES) {
        double initial_x = player.getBall().getBoundsInParent().getCenterX();
        double initial_y = player.getBall().getBoundsInParent().getCenterY();

        this.animation = new ParallelTransition();
        for (int i =0;i<N_PIECES; i++) {
            int radius = rand.nextInt(10);

            System.out.println(initial_x+" "+initial_y);
            Circle smaller = new Circle(initial_x,initial_y, radius);
            Color color = colors.get(i%4);
            smaller.setFill(color);
            int distance = rand.nextInt(600) - 300 ;

            TranslateTransition lateralTransition = new TranslateTransition();
            lateralTransition.setNode(smaller);
            lateralTransition.setInterpolator(Interpolator.LINEAR);
            lateralTransition.setDuration(Duration.millis(5000));
            lateralTransition.setByX(distance);
            lateralTransition.setCycleCount(0);

            TranslateTransition gravity = new TranslateTransition(Duration.millis(2000), smaller);
            gravity.setByY(100f);
            gravity.setCycleCount(0);
            gravity.setInterpolator(interpolator);

            this.animation.getChildren().add(gravity);
            this.animation.getChildren().add(lateralTransition);
            this.root.getChildren().add(smaller);
        }


    }

    public ParallelTransition getAnimation() {
        return this.animation;
    }

    public Group getRoot() {
        return root;
    }
}
