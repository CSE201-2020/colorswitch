package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.Printer;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.Obstacles.CircleObstacle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainController {
    @FXML
    private AnchorPane mainRoot;
    @FXML
    private ListView<String> listOfUsers;
    @FXML
    private TextField textField;
    private boolean createdNew = true;
    User current ;

    public void initialize(ObservableList<User> items ) {
        HashMap<String, User> names = new HashMap<>();
        items.forEach(user -> {
            names.put(user.getUsername(),user);
        });
        listOfUsers.setItems(FXCollections.observableArrayList(names.keySet()));
        listOfUsers.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> ov,
                                    String old_val, String new_val) {
                    System.out.println("you have chosen: "+new_val);
                    // deserialize and make copy of User
                    //TODO
                    current = names.get(new_val);
                    try {
                        createdNew = false;
                        loadmenu();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
    }

    public User makeNewUser (String username) throws IOException {
        User current = new User();
        current.setUsername(username);
        current.setTotalStars(0);

        Main.getUserArrayList().add(current);
        Main.serializeList("users_main");
        System.out.println("SERIALIZED with new user: "+username+" New USER LENGTH: "+Main.getUserArrayList().size());
        return current;
    }
    public void loadmenu() throws IOException {
        System.out.print("hello ");
        if (createdNew) {
            current = makeNewUser(textField.getText());
            System.out.println(textField.getText());
        }

        // need to make the main page user specific
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "sample.fxml"
                )
        );
        AnchorPane pane= loader.load();
        Controller controller = loader.getController();

        Group MainRoot = new Group();
        CircleObstacle o1 = new CircleObstacle(15,-1,10,160,110);
        CircleObstacle o2 = new CircleObstacle(15,1,10,240,110);
        o1.getAnimation().setRate(1.5);
        o1.getAnimation().setRate(1.5);
        CircleObstacle outerc = new CircleObstacle(75,1,15,200,350);
        CircleObstacle innerc = new CircleObstacle(55,-1,15,200,350);
        CircleObstacle lastc = new CircleObstacle(95,-1,15,200,350);
        outerc.getAnimation().setRate(1.1);
        innerc.getAnimation().setRate(1.3);
        Group rooto1 = o1.getRoot();
        Group rooto2 = o2.getRoot();
        Group root3 = outerc.getRoot();
        Group root4= innerc.getRoot();
        Group root5= lastc.getRoot();
        MainRoot.getChildren().addAll(rooto1,rooto2,root5,root3,root4);

        o1.getAnimation().play();
        o2.getAnimation().play();
//        outerc.getAnimation().setRate(0.5);
        outerc.getAnimation().play();
//        innerc.getAnimation().setRate(0.5);
        innerc.getAnimation().play();
        lastc.getAnimation().setRate(0.8);
        lastc.getAnimation().play();

        controller.initUserSpace(current);

        mainRoot.getChildren().setAll(pane);
        mainRoot.getChildren().add(MainRoot);
    }
}
