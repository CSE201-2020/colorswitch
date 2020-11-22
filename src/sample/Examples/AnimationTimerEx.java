package sample.Examples;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AnimationTimerEx extends Application {

    private double opacity = 1;
    private Label lbl;

    @Override
    public void start(Stage stage) {

        initUI(stage);
    }

    private void initUI(Stage stage) {

        var root = new StackPane();

        lbl = new Label("JavaFX");
        lbl.setFont(Font.font(48));
        root.getChildren().add(lbl);

        AnimationTimer timer = new MyTimer();
        timer.start();

        var scene = new Scene(root, 300, 250);

        stage.setTitle("AnimationTimer");
        stage.setScene(scene);
        stage.show();
    }

    private class MyTimer extends AnimationTimer {

        @Override
        public void handle(long now) {

            doHandle();
        }

        private void doHandle() {

            opacity -= 0.01;
            lbl.opacityProperty().set(opacity);

            if (opacity <= 0) {
                opacity =1;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}