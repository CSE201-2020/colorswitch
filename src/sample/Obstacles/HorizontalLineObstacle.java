package sample.Obstacles;


import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Obstacle;
import sample.Player;

public class HorizontalLineObstacle extends Obstacle {
    final private Group root;
    final private TranslateTransition animation;
    final private Color colors [] = {Color.web("#FAE100"),Color.web("#FF0181"),Color.web("#32DBF0"),Color.web("#900DFF")};
    public HorizontalLineObstacle(int size, int direction, int thickness, int posX, int posY) {
        root = new Group();

        for (int i = 0 ;i <10; ++i) {
            Line line = new Line();
            line.setStrokeWidth(thickness);
            line.setStartX(posX+size* i);
            line.setStartY(posY);
            line.setStroke(colors[i%4]);
            line.setEndX(posX + size*(i+1));
            line.setEndY(posY);
            root.getChildren().add(line);
//        tt.setAutoReverse(true);


        }
        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), root);
        tt.setByX(400f);
        tt.setInterpolator(Interpolator.LINEAR);
        tt.setCycleCount(100);
        tt.play();

        animation  = new TranslateTransition() ;

    }

    public Group getRoot() {
        return root;
    }

    public TranslateTransition getAnimation() {
        return animation;
    }

    public int checkCollision(Player player) {
//        System.out.println(this.root.getBoundsInParent());
        for (Node obPart: this.root.getChildren()) {
            if (player.getBall().intersects(obPart.getBoundsInParent())) {

            }
        }
        return 0;
    }
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        // TODO Auto-generated method stub
//
//
//        //Configuring Group and Scene
////
////        Scene scene = new Scene(root,600,400,Color.BLACK);
////        primaryStage.setScene(scene);
////        primaryStage.setTitle("Rotate Transition example");
////        primaryStage.show();
//
//    }
}
