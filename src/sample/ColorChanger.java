package sample;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;

import java.util.ArrayList;
import java.util.Arrays;

public class ColorChanger extends GameElement{
    final private Group root;
    final private ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.web("#FAE100"),Color.web("#FF0181"),Color.web("#32DBF0"),Color.web("#900DFF")));

    public ColorChanger(int posX, int posY) {
        this.root = new Group();
        for (int i = 0 ;i < 4; ++i) {
            Arc rect = new Arc(posX,posY,15,15,90*i,90);
            rect.setType(ArcType.ROUND);
            rect.setStrokeLineCap(StrokeLineCap.BUTT);
//            rect.setStroke(colors.get(i));
            rect.setFill(colors.get(i));
            rect.setStrokeWidth(0);
            root.getChildren().add(rect);
        }
    }

    @Override
    public Group getRoot() {
        return root;
    }

    @Override
    public Transition getAnimation() {
        return new TranslateTransition();
    }

    public int checkCollision(Player player) {
        if (this.root.intersects(player.getBall().getBoundsInParent())) return 2;
        return 0;
    }
}
