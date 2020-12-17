package sample;

import javafx.scene.Group;
import sample.GameElement;

import java.io.Serializable;

public class DB implements Serializable {
    private static final long serialVersionUID = 7368175650914175345L;
    private int PosY;
    private double Rotate;
    DB() {
    }
    void updateValues(GameElement element) {
        this.PosY = (int)element.getRoot().getBoundsInParent().getCenterY();
        this.Rotate = element.getRoot().getRotate();
    }
    public void initializeObstacle(Group ObstaclesRoot) {
        System.out.println("DB obstacle Created: "+this);
    }
    @Override
    public String toString () {
        String result = "";
        result += "\ncurrentPos: "+ PosY;
        result += "\nRotate: "+ Rotate;
        return result;
    }

    public double getRotate() {
        return Rotate;
    }

    public int getPosY() {
        return PosY;
    }

}
