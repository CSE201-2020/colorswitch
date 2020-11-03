package sample;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PathTransitionEx extends Application {

    @Override
    public void start(Stage stage) {

        initUI(stage);
    }

    private void initUI(Stage stage) {

        var root = new Pane();

        var path = new Path();
        path.getElements().add(new MoveTo(20, 120));
        path.getElements().add(new CubicCurveTo(180, 60, 250, 340, 420, 240));

        var circle = new Circle(20, 120, 10);
        circle.setFill(Color.CADETBLUE);

        var ptr = new PathTransition();

        ptr.setDuration(Duration.seconds(6));
        ptr.setDelay(Duration.seconds(2));
        ptr.setPath(path);
        ptr.setNode(circle);
        ptr.setCycleCount(2);
        ptr.setAutoReverse(true);
        ptr.play();

        root.getChildren().addAll(path, circle);

        var scene = new Scene(root, 450, 300);

        stage.setTitle("PathTransition");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}