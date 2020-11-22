package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.Printer;
import javafx.scene.Group;
import javafx.scene.layout.*;
import sample.Obstacles.CircleObstacle;

import java.io.IOException;

public class MainController {
    @FXML
    private AnchorPane mainRoot;
public void loadmenu() throws IOException {
    System.out.println("hello");
    AnchorPane pane= FXMLLoader.load(getClass().getResource("sample.fxml"));
    Group MainRoot = new Group();
    CircleObstacle o1 = new CircleObstacle(15,-1,10,160,110);
    CircleObstacle o2 = new CircleObstacle(15,1,10,240,110);
    CircleObstacle outerc = new CircleObstacle(70,1,10,200,350);
    CircleObstacle innerc = new CircleObstacle(50,-1,10,200,350);
    CircleObstacle lastc = new CircleObstacle(90,-1,10,200,350);
    Group rooto1 = o1.getRoot();
    Group rooto2 = o2.getRoot();
    Group root3 = outerc.getRoot();
    Group root4= innerc.getRoot();
    Group root5= lastc.getRoot();
    MainRoot.getChildren().addAll(rooto1,rooto2,root5,root3,root4);

    o1.getAnimation().play();
    o2.getAnimation().play();
    outerc.getAnimation().play();
    innerc.getAnimation().play();
    lastc.getAnimation().play();



    mainRoot.getChildren().setAll(pane);
    mainRoot.getChildren().add(MainRoot);
}
}
