package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class GameplayLoader {

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

}
