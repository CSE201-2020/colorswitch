package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;
import java.util.Scanner;

public class  Main extends Application {
    private static User currentd= new User();
    private static User copy;

    private static ArrayList<User> userArrayList = new ArrayList<>();
    private static ArrayList<User> copyList;
    final private int height = 700 ;
    final private double ratio = (4/7.0) ;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Media media = new Media(getClass().getResource("/resources/maintheme.mp3").toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.play();

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "login.fxml"
                )
        );
        AnchorPane root= loader.load();
        MainController controller = loader.getController();

        // get data from db to copyList and then copy it to userArrayList
        deserializeList("users_main");
        copyList.forEach(user -> {
            userArrayList.add(user);
        });

        ObservableList<User> items = FXCollections.observableArrayList (copyList);
        controller.initialize(items);

        CircleObstacle o1 = new CircleObstacle(15,-1,5,150,62);
        CircleObstacle o2 = new CircleObstacle(15,1,5,230,62);
        o1.getAnimation().setRate(1.5);
        o2.getAnimation().setRate(1.5);
        Group rooto1 = o1.getRoot();
        Group rooto2 = o2.getRoot();
        Group MainRoot = new Group();
        MainRoot.getChildren().addAll(rooto1,rooto2);
        o1.getAnimation().play();
        o2.getAnimation().play();

        root.getChildren().add(MainRoot);
        primaryStage.setTitle("Game");
        primaryStage.setScene(new Scene(root, height*ratio, height));
        primaryStage.show();
        System.out.println(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void serializeList(String username) throws IOException {
        ObjectOutputStream out=null;

        try {
            File myObj = new File("db/"+username+".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            out = new ObjectOutputStream (new FileOutputStream("db/"+username+".txt"));
            System.out.println(userArrayList);
            out.writeObject(userArrayList);
        }
        catch (Exception e){
            System.out.println("Exception "+e);
        }
        finally {
            out.close();
            System.out.println("Saved!");
        }
    }

    public static void deserializeList(String username) throws ClassNotFoundException, FileNotFoundException, IOException{
        ObjectInputStream in = null;
        try {
            in=new ObjectInputStream (new FileInputStream("db/"+username+".txt"));
            copyList = (ArrayList<User>) in.readObject();
            in.close();
            System.out.println("copied"+copyList);
        }
        catch (FileNotFoundException e){
            copyList = new ArrayList<User>();
            System.out.println("here");
        }
        catch (NullPointerException e) {
            copyList = new ArrayList<User>();
            System.out.println("This user does not exist in the database");
        }
    }

    public static ArrayList<User> getUserArrayList() {
        return userArrayList;
    }
}