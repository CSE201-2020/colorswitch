package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sample.Obstacles.CircleObstacle;

public class  Main extends Application {
    final private int height = 700 ;
    final private double ratio = (4/7.0) ;


    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root = FXMLLoader.load(getClass().getResource("login.fxml"));
        CircleObstacle o1 = new CircleObstacle(15,-1,10,150,62);
        CircleObstacle o2 = new CircleObstacle(15,1,10,230,62);
        Group rooto1 = o1.getRoot();
        Group rooto2 = o2.getRoot();
        Group MainRoot = new Group();
        MainRoot.getChildren().addAll(rooto1,rooto2);
        o1.getAnimation().play();
        o2.getAnimation().play();
//        root.getChildren().setAll(root);
        root.getChildren().add(MainRoot);
        primaryStage.setTitle("Game");
        primaryStage.setScene(new Scene(root, height*ratio, height));
        primaryStage.show();
        System.out.println(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}