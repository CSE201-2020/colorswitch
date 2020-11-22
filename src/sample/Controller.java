package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private AnchorPane mainRoot;
    private AnchorPane tempRoot;
    @FXML
    private ImageView startGame;

    @FXML
    private ImageView loadGame;
    @FXML
    void startGame(MouseEvent event) throws Exception {
//        AnchorPane pane= FXMLLoader.load(getClass().getResource("Almanac.fxml"));
        Node node=(Node) event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        Gameplay gameplay = new Gameplay(700 , 9/(16.0));
        System.out.println(node);
        System.out.println(gameplay);
        System.out.println(stage);
        stage.setTitle("gameplay");
        stage.setScene(gameplay.getMainScene());
        stage.show();
        //        VBox layout1= new VBox(20);
//
//        Label gameplay = new Label("Gameplay here");
//        layout1.getChildren().add(gameplay);
//
//        mainRoot.getChildren().setAll(layout1);
    }
    @FXML
    void loadGame(MouseEvent event) throws Exception{
        VBox layout1= new VBox(20);

        Label gameplay = new Label("Load Game heafjaksldfjal;sjfl;asdjf;kasdlf;lkasdfl;asdkfl;asdjfl;asdkf;lasdjf;lasdkre");
        layout1.getChildren().add(gameplay);

        mainRoot.getChildren().setAll(layout1);
    }

}
