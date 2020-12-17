package sample.Obstacles;

        import javafx.animation.Interpolator;
        import javafx.animation.ParallelTransition;
        import javafx.animation.RotateTransition;
        import javafx.scene.Group;
        import javafx.scene.Node;
        import javafx.scene.paint.Color;
        import javafx.scene.shape.Arc;
        import javafx.scene.transform.Rotate;
        import javafx.util.Duration;
        import sample.Obstacle;
        import sample.Player;

public class DoublePlusObstacle extends Obstacle {
    int center = 200;

    final private Group root;
    private ParallelTransition animation;
    private PlusObstacle left;
    final private Color colors [] = {Color.web("#FAE100"),Color.web("#FF0181"),Color.web("#32DBF0"),Color.web("#900DFF")};
    public DoublePlusObstacle(int radius, int direction, int thickness, int posX, int posY) {
        // Creating Circle
        root = new Group();
        left = new PlusObstacle(radius, direction, thickness,center + radius + thickness -4, posY);
        PlusObstacle right = new PlusObstacle(radius, -direction, thickness,center - radius - thickness+4, posY,true);
        root.getChildren().add(left.getRoot());
        root.getChildren().add(right.getRoot());

        animation =
                new ParallelTransition(left.getAnimation(), right.getAnimation());
//        animation.play();
    }

    public Group getRoot() {
        return root;
    }

    public ParallelTransition getAnimation() {
        return animation;
    }

    public int checkCollision(Player player) {
        if(left.checkCollision(player,true)==-1){
            this.animation.pause();
            return -1;
        }
        else
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