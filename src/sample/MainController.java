package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

import java.io.IOException;

public class MainController {
    @FXML
    private AnchorPane mainRoot;
public void loadmenu() throws IOException {
    System.out.println("hello");
    AnchorPane pane= FXMLLoader.load(getClass().getResource("sample.fxml"));
    mainRoot.getChildren().setAll(pane);
}
}
