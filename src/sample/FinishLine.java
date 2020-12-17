package sample;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

import java.util.ArrayList;

public class FinishLine extends GameElement{
    private Group root = new Group();
    private Transition animation;

    FinishLine (int posY) {
        int x = 0;

        boolean color = true;

        int height = 10;
        int width = height;
        for (int i =0;i < 400 / width;i++) {
            for (int j =0 ;j < 5;j++) {

                System.out.println("made rects");
                Rectangle r = new Rectangle();
                r.setX(x);
                r.setY(posY - (j*width));
                r.setWidth(width);
                r.setHeight(height);

                r.setFill(color ? Color.WHITE:Color.BLACK);

                color = !color;
                this.root.getChildren().add(r);
            }
            x += width;
        }
    }

    @Override
    public Group getRoot() {
        return root;
    }

    @Override
    public Transition getAnimation() {
        return animation;
    }

    @Override
    public int checkCollision(Player player) {
        return 0;
    }

    @Override
    public ArrayList<Object> getArgs() {
        return null;
    }
}
