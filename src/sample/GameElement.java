package sample;

import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public abstract class GameElement  {
    DB obsInfo = new DB();

    private Node element;
    abstract public Group getRoot();
    abstract public Transition getAnimation();
    // 0:sameColor, -1:different color, 1:star, 2:colorChanger
    abstract public int checkCollision(Player player);
    //add abstract functions here ...
    //1) getColor
    //2) maybe checkCollision also.

}
