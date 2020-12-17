package sample.Obstacles;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;
import sample.Obstacle;
import sample.Player;

import java.util.*;

public class CircleThingy extends Obstacle {
    final private Group root = new Group();
    final private ParallelTransition animation ;
    final private ArrayList<Circle> circleArrayList = new ArrayList<>();
    private Timer timer ;
    final private Color colors [] = {Color.web("#FF0181"),Color.web("#32DBF0"),Color.web("#FAE100"),Color.web("#900DFF")};
    class delayTask extends TimerTask {
        @Override
        public void run() {
            animation.setRate(1);
        }
    }
    public void taskMaker (int seconds) {
        timer = new Timer();
        timer.schedule(new delayTask(), seconds * 1000);
    }
    public CircleThingy (int radius, int direction, int posX, int posY, int type) {

        int N_per_SIDE = 5;
        int SIDE = 4;
        ArrayList<Double> points = new ArrayList<>();
        SVGPath svg = new SVGPath();
        switch (type) {
            case 0:
                // Triangle
                double upY = posY + (Math.pow(3,-0.5)*15* radius);
                double downY = posY - (Math.pow(3,-0.5)*7.5* radius);
                points.addAll(
                    Arrays.asList(
                        posX - (7.5*radius), downY,
                        posX + (7.5*radius), downY,
                        (double)posX, upY)
                    );
                break;
            case 1:
                // inverted triangle
                double InvertedupY = posY + (Math.pow(3,-0.5)*7.5* radius);
                double InverteddownY = posY - (Math.pow(3,-0.5)*15* radius);

                points.addAll(
                    Arrays.asList(
                        posX - (7.5*radius), InvertedupY,
                        posX + (7.5*radius), InvertedupY,
                        (double)posX, InverteddownY
                    )
                );
                break;
            case 2:
                // square
                SIDE = 4;
                points.addAll(
                    Arrays.asList(
                        posX + (7.5*radius), posY - (7.5*radius),
                        posX - (7.5*radius), posY - (7.5*radius),
                        posX - (7.5*radius), posY + (7.5*radius),
                        posX + (7.5*radius), posY + (7.5*radius)
                    )
                );
                break;
            case 3:
                // infinity-shaped
                SIDE = 4;
                svg.setFill(Color.TRANSPARENT);
//                svg.setStrokeWidth(1.0);
//                svg.setStroke(Color.BLACK);
                svg.setContent("M 787.49,150 C 787.49,203.36 755.56,247.27 712.27,269.5 S 622.17,290.34 582.67,279.16 508.78,246.56 480,223.91 424.93,174.93 400,150 348.85,98.79 320,76.09 256.91,32.03 217.33,20.84 130.62,8.48 87.73,30.5 12.51,96.64 12.51,150 44.44,247.27 87.73,269.5 177.83,290.34 217.33,279.16 291.22,246.56 320,223.91 375.07,174.93 400,150 451.15,98.79 480,76.09 543.09,32.03 582.67,20.84 669.38,8.48 712.27,30.5 787.49,96.64 787.49,150 z");
                System.out.println(svg.getBoundsInLocal());
                svg.setTranslateX(-200);
                svg.setScaleX(0.3);
                svg.setScaleY(0.3);
                svg.setTranslateY(-1000);
                break;
        }


        if (direction<0) Collections.reverse(points);
        Polygon path = new Polygon();
        path.getPoints().addAll(points);

        int N_CIRCLE = SIDE * N_per_SIDE;

        this.animation = new ParallelTransition();
        int mod = N_CIRCLE / 4;
        for (int i =0 ; i < N_CIRCLE ; i++) {
            Circle unitCircle = new Circle(posX, posY, radius);
            unitCircle.setFill(colors[(i/mod)%4]);

            PathTransition anim = new PathTransition();
            anim.setDuration(Duration.millis(5000));
            anim.setDelay(Duration.millis(i*5000.0/(N_CIRCLE)));

            anim.setNode(unitCircle);
            anim.setPath(type==3?svg:path);
            anim.setInterpolator(Interpolator.LINEAR);
//        animation.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            anim.setCycleCount(Animation.INDEFINITE);
            this.animation.getChildren().add(anim);
            root.getChildren().add(unitCircle);
            circleArrayList.add(unitCircle);
        }
        this.animation.setRate(5);
        taskMaker(1);
        this.animation.play();

    }


    @Override
    public Group getRoot() {
        return this.root;
    }

    @Override
    public int checkCollision(Player player) {
        Circle ball = player.getBall();
        for (Circle arc : this.circleArrayList){
            Shape intersected = Shape.intersect(arc,ball);
            if (!arc.getFill().equals(ball.getFill())) {
                System.out.print(arc.getStroke()+" "+ball.getFill());
                if (intersected.getBoundsInLocal().getWidth() != -1) {
//                    player.getAnimation().pause();
//                    this.animation.pause();
                    return -1;
                }
            }
        }
        return 0;
    }

    @Override
    public Transition getAnimation() {
        return this.animation;
    }
}
