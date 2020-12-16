package sample;

import javafx.animation.ScaleTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class Star extends GameElement{
    final private Group root;
    final private ScaleTransition animation;
    final private int offset = 10;
    private SVGPath star = new SVGPath();
    int inactivityCycleCount = 450;

    Star(int posX, int posY, Color color, double initialScale) {
        root = new Group();
        //svg for star


        star.setContent("M12 .587l3.668 7.568 8.332 1.151-6.064 5.828 1.48 8.279-7.416-3.967-7.417 3.967 1.481-8.279-6.064-5.828 8.332-1.151z");
        star.setFill(color);
        star.setTranslateX(posX-offset);
        star.setTranslateY(posY-offset);
//        star.setScaleX(initialScale);
//        star.setScaleY(initialScale);
        root.getChildren().add(star);


        animation = new ScaleTransition(Duration.millis(1000), star);
        animation.setByX(0.4f);
        animation.setByY(0.4f);
        animation.setCycleCount(inactivityCycleCount);
        animation.setAutoReverse(true);

    }

    public Group getRoot() {
        return root;
    }
    public void setScaleStar(double x, double y){
        star.setScaleX(x);
        star.setScaleY(y);
    }

    public ScaleTransition getAnimation() {
        return animation;
    }

    public int checkCollision(Player player) {
        if (this.root.intersects(player.getBall().getBoundsInParent())) return 1;
        return 0;
    }
}
