package sample;

import javafx.animation.Transition;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;

public abstract class GameElement {
    private Node element;
    public boolean checkCollision (Node element2){
        return (element2.getBoundsInParent()) . intersects(element.getBoundsInParent());
    }
    abstract public Group getRoot();
    abstract public Transition getAnimation();
    //add abstract functions here ...
    //1) getColor
    //2) maybe checkCollision also.

}
