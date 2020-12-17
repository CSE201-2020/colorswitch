package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Obstacles.CircleObstacle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GameplayLoader {
    @FXML
    private AnchorPane mainRoot;
    @FXML
    private ListView<String> time;
    @FXML
    private ListView<Integer> score;

    private User user ;
    private Gameplay.DB current = new Gameplay.DB();
    private Gameplay gameplay;
    private Stage stage;

    public void initUser (User user , Stage stage){
        this.user = user ;
        this.stage = stage;

        HashMap<String, Gameplay.DB> names = new HashMap<>();
        ArrayList<String> namesList = new ArrayList<>();
        ArrayList<Integer> scores = new ArrayList<>();
        this.user.getGamelist().forEach((date,db) -> {
            System.out.println(date.toString());
            names.put(date.toString(),db);
            namesList.add(date.toString());
            scores.add(db.curscore);
        });
        System.out.println(score);
        score.setItems(FXCollections.observableArrayList(scores));
        time.setItems(FXCollections.observableArrayList(namesList));
        time.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> ov,
                                        String old_val, String new_val) {
                        System.out.println("you have chosen: "+new_val);
                        // deserialize and make copy of User
                        //TODO
                        current = names.get(new_val);
                        //deserialize from this.user
                        gameplay = new Gameplay(700 , 9/(16.0), user, current);

                        System.out.println(gameplay);
                        stage.setScene(gameplay.getMainScene());
                        stage.show();
                    }
                });
    }

    public void goToHome(MouseEvent event) throws IOException {

        Group root = new Group();
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

        controller.initUserSpace(this.user);

        root.getChildren().setAll(pane);
        root.getChildren().add(MainRoot);

        Scene new_sc = new Scene(root, 400.0,700.0);
//        Stage stage=(Stage) this.gameplay.getMainScene().getWindow();
        // testing .... actually set old scene
        this.stage.setScene(new_sc);

    }

}
