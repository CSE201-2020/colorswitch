package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ChallengerController {
    @FXML
    private AnchorPane mainRoot;
    private AnchorPane tempRoot;


    @FXML
    void C1 (Event event) throws IOException {
        System.out.println("here?");
        Node node=(Node) event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        System.out.println("here?");
        GameplayChallenges gameplayC = new GameplayChallenges(1, 700, 9 / (16.0));
        System.out.println("CAHAKALAJAKALAKLA");
//        GameplayChallenges gameplay = new GameplayChallenges(0,700 , 9/(16.0));
//        gameplay.init(datatable)
        System.out.println("Node"+ node);
        System.out.println("gameplay +"+ gameplayC);
        System.out.println("Stage"+stage);

        stage.setTitle("gameplayChallenges 1");
        stage.setScene(gameplayC.getMainScene());
        stage.show();
    }
    @FXML
    void C5 (Event event) throws IOException {
        System.out.println("here?");
        Node node=(Node) event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        System.out.println("here?");
        GameplayChallenges gameplayC = new GameplayChallenges(9, 700, 9 / (16.0));
        System.out.println("CAHAKALAJAKALAKLA");
//        GameplayChallenges gameplay = new GameplayChallenges(0,700 , 9/(16.0));
//        gameplay.init(datatable)
        System.out.println("Node"+ node);
        System.out.println("gameplay +"+ gameplayC);
        System.out.println("Stage"+stage);

        stage.setTitle("gameplayChallenges 1");
        stage.setScene(gameplayC.getMainScene());
        stage.show();
    }
    @FXML
    void C4 (Event event) throws IOException {
        System.out.println("here?");
        Node node=(Node) event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        System.out.println("here?");
        GameplayChallenges gameplayC = new GameplayChallenges(8, 700, 9 / (16.0));
        System.out.println("CAHAKALAJAKALAKLA");
//        GameplayChallenges gameplay = new GameplayChallenges(0,700 , 9/(16.0));
//        gameplay.init(datatable)
        System.out.println("Node"+ node);
        System.out.println("gameplay +"+ gameplayC);
        System.out.println("Stage"+stage);

        stage.setTitle("gameplayChallenges 1");
        stage.setScene(gameplayC.getMainScene());
        stage.show();
    }

    @FXML
    void C2 (Event event) throws Exception{

        Node node=(Node) event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        GameplayChallenges gameplayC = new GameplayChallenges(6, 700, 9 / (16.0));
        System.out.println("CAHAKALAJAKALAKLA");
//        GameplayChallenges gameplay = new GameplayChallenges(0,700 , 9/(16.0));
//        gameplay.init(datatable)
        System.out.println("Node"+ node);
        System.out.println("gameplay +"+ gameplayC);
        System.out.println("Stage"+stage);

        stage.setTitle("gameplayChallenges 1");
        stage.setScene(gameplayC.getMainScene());
        stage.show();
    }

    @FXML
    void C3 (Event event) throws Exception{

        Node node=(Node) event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        GameplayChallenges gameplayC = new GameplayChallenges(4, 700, 9 / (16.0));
        System.out.println("CAHAKALAJAKALAKLA");
//        GameplayChallenges gameplay = new GameplayChallenges(0,700 , 9/(16.0));
//        gameplay.init(datatable)
        System.out.println("Node"+ node);
        System.out.println("gameplay +"+ gameplayC);
        System.out.println("Stage"+stage);

        stage.setTitle("gameplayChallenges 1");
        stage.setScene(gameplayC.getMainScene());
        stage.show();
    }
}
