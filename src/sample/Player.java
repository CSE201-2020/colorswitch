package sample;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//ball
public class Player {
    Random rand = new Random();
    final private Circle ball;
    private TranslateTransition animation;
    Interpolator interpolator = new Interpolator() {
        @Override
        protected double curve(double t) {
            return -t*2 * (1 - 3*t);
        }

    };
    final private ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.web("#FAE100"),Color.web("#FF0181"),Color.web("#32DBF0"),Color.web("#900DFF")));
    Player(Color initColor,int posX, int posY) {
        ball = new Circle(posX,posY,12,initColor );

        ball.setFill(initColor);
        initAnimation();
    }
    private void initAnimation() {
        this.animation = new TranslateTransition(Duration.millis(2000), ball);

        //Setting duration of the transition
        this.animation.setByY(400f);
        this.animation.setCycleCount(1);
        //animation.setAutoReverse(true);
        this.animation.setInterpolator(interpolator);
        //animation.play();
    }


    public TranslateTransition getAnimation() {
        return animation;
    }
    public void handleJumpEvent () {
        this.animation.pause();
        initAnimation();
        this.animation.play();
    }
    public void changeColor () {
        ArrayList<Color> filtered = new ArrayList<>();
        Color ballFill = (Color)this.ball.getFill();
        colors.forEach(color -> {
            if(!ballFill.equals(color))filtered.add(color);
        });
        this.getBall().setFill(filtered.get(rand.nextInt(filtered.size())));
        System.out.println(colors);
        System.out.println("filtered"+filtered);

    }

    public Circle getBall() {
        return ball;
    }
}


