package sample;

import javafx.geometry.Bounds;
import javafx.scene.Node;

public abstract class GameElement {
    private Node element;
    public boolean checkCollision (Node element2){
        return (element2.getBoundsInParent()) . intersects(element.getBoundsInParent());
    }
    //add abstract functions here ...
    //1) getColor
    //2) maybe checkCollision also.

}
