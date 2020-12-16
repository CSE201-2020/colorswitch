package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.media.Media;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sample.Obstacles.CircleObstacle;

import java.io.*;
import java.util.Scanner;

public class  Main extends Application {
    private static User currentd= new User();
    private static User copy;
    final private int height = 700 ;
    final private double ratio = (4/7.0) ;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Media media = new Media(getClass().getResource("/resources/maintheme.mp3").toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
//        player.play();

        AnchorPane root = FXMLLoader.load(getClass().getResource("login.fxml"));
        System.out.println("Enter Username : ");
        Scanner sc= new Scanner(System.in);
//        String s=sc.next();
        currentd.setUsername("Bhavesh");
        currentd.setTotalstars(0);
        serialize();
        deserialize();
        System.out.println("Copy Stars are : " + copy.getTotalstars());


        CircleObstacle o1 = new CircleObstacle(15,-1,5,150,62);
        CircleObstacle o2 = new CircleObstacle(15,1,5,230,62);
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
    public static void serialize() throws IOException {
        ObjectOutputStream out=null;
        try {
            out = new ObjectOutputStream (new FileOutputStream("UserL.txt"));
            out.writeObject(currentd);
            System.out.println(currentd.getTotalstars());
        }
        catch (Exception e){
            System.out.println("Exception");
        }
        finally {
            out.close();
            System.out.println("Saved!");
        }
    }
    public static void deserialize() throws ClassNotFoundException, FileNotFoundException, IOException{
        ObjectInputStream in = null;
        try {
            in=new ObjectInputStream (new FileInputStream("UserL.txt"));
            copy=(User) in.readObject();
            in.close();
            System.out.println("copied");
        }
        catch (FileNotFoundException e){
            copy=new User();
            System.out.println("here");
        }
        catch (NullPointerException e) {
            copy=new User();
            System.out.println("This user does not exist in the database");
        }
    }
}