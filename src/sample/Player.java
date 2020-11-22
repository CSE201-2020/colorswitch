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

//ball
public class Player {
    final private Circle ball;
    private TranslateTransition animation;
    Interpolator interpolator = new Interpolator() {
        @Override
        protected double curve(double t) {
            return -t*4 * (1 - 2*t);
        }

    };

    Player(Color initColor) {
        ball = new Circle(100,800,12,initColor );

        ball.setFill(initColor);
        initAnimation();
    }
    private void initAnimation() {
        this.animation = new TranslateTransition(Duration.millis(2000), ball);

        //Setting duration of the transition
        this.animation.setByY(200f);
        this.animation.setCycleCount(4);
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
    public Circle getBall() {
        return ball;
    }
}


