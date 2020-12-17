package sample.animations;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
            int radius = rand.nextInt(5) + 2;

            System.out.println("Disintegration "+"X "+initial_x+" "+initial_y);
            Circle smaller = new Circle(initial_x,initial_y, radius);
            Color color = colors.get(i%4);
            smaller.setFill(color);

            TranslateTransition gravity = new TranslateTransition(Duration.millis(2000), smaller);
            gravity.setByY(rand.nextInt(500) + 400);
            gravity.setCycleCount(0);
            gravity.setInterpolator(interpolator);

            this.animation.getChildren().add(gravity);
            this.root.getChildren().add(smaller);

            final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
                int deltaX = (rand.nextInt(10) + 1) * (rand.nextBoolean()?1:-1);

                @Override
                public void handle(final ActionEvent t) {
                    smaller.setLayoutX(smaller.getLayoutX() + deltaX);
                    System.out.println("Disintegration"+smaller.getLayoutX());
//                    final Bounds bounds = canvas.getBoundsInLocal();
                    final boolean atRightBorder = smaller.getLayoutX() >= (200);
                    final boolean atLeftBorder = smaller.getLayoutX() <= (-200);
                    final boolean down = smaller.getBoundsInParent().getCenterY() >= (700);
                    if (down) {
                        animation.stop();
                    }
                    if (atRightBorder || atLeftBorder) {
                        deltaX *= -1;
                    }
                }
            }));

            loop.setCycleCount(20000);
            this.animation.getChildren().add(loop);

        }



    }

    public ParallelTransition getAnimation() {
        return this.animation;
    }

    public Group getRoot() {
        return root;
    }
}
