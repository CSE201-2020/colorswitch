package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class  Main extends Application {
    final private int height = 700 ;
    final private double ratio = (9/16.0) ;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Game");
        primaryStage.setScene(new Scene(root, height*ratio, height));
        primaryStage.show();
        System.out.println(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}