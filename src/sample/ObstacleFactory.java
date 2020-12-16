package sample;

import javafx.geometry.Pos;
import sample.Obstacles.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
public class ObstacleFactory {
    /*
        Presets :

        0: CircleObstacle(60,-1,10,center,200);
        1: PlusObstacle(60,1,10,center + 60,-400);
        2: PlusObstacle(60,1,10,center - 60 ,-700);
        3: HorizontalLineObstacle(100,1,10,-400,-1000);
        4: DoubleCircleObstacle(60,1,10,center,PosY);
     */
    //returning arraylist to allow some presets to be a combination , like to consecutive opposite circleObstacles.
    static int center = 200 ;
    static int d = 200;

    public static class OB_dist {
        final private ArrayList<Obstacle> obstacleList;
        final private int dist;
        OB_dist (ArrayList<Obstacle> obs, int dis) {
            obstacleList = obs;
            dist = dis;
        }

        public ArrayList<Obstacle> getObstacleList() {
            return obstacleList;
        }

        public int getDist() {
            return dist;
        }
    }
    static public OB_dist CreateRandomObstacle (int preset, int PosY) {

        ArrayList<Obstacle> OBS = new ArrayList<Obstacle>();

        switch (preset) {
            case 0:
                PosY-=65;
                CircleObstacle c0 = new CircleObstacle(60, -1,10, center, PosY);
                int CircleHeight = (int)c0.getRoot().getBoundsInParent().getHeight();
                PosY-=(d + 130);
                CircleObstacle c1 = new CircleObstacle(60, 1,10, center, PosY);
                OBS.add(c0);
                OBS.add(c1);
                PosY-=(65 + d);
                System.out.println("                                         1 -1"+CircleHeight);
                break;
            case 1:
                PosY -= 65;
                OBS.add(new PlusObstacle(60,1,10,center + 60, PosY));
                System.out.println("small plus");
                break;
            case 2:
                OBS.add(new CircleThingy(10,1,center,PosY,0));
                System.out.println("Circle Thingy 0");
                break;
            case 3:
                OBS.add(new CircleThingy(15,1,center,PosY,2));
                System.out.println("Circle Thingy 2");
                break;
            case 4:
                OBS.add(new CircleThingy(15,1,center,PosY,1));
                System.out.println("Circle Thingy 1");
                break;
            case 5:
                OBS.add(new DoubleCircleObstacle(60,1,10,center,PosY));
                System.out.println("Double Circle");
                break;
            case 6:
                OBS.add(new PlusObstacle(120,1,10,center + 120 ,PosY));
                System.out.println("Big plus");
                break;
        }
        return new OB_dist(OBS, PosY);
    }

    static public ArrayList<Obstacle> CreateChallenge (int challenge, int PosY) {
        ArrayList<Obstacle> OBS = new ArrayList<Obstacle>();
        switch (challenge) {
            case 0:
                OBS.add(new CircleObstacle(120, -1,10, center, PosY));
                OBS.add(new CircleObstacle(120, 1,10, center, PosY - 400));
                System.out.println(" 1 -1");
                break;
            case 1:
                OBS.add(new PlusObstacle(60,1,10,center + 60, PosY));
                System.out.println("small plus");
                break;
            case 2:
                OBS.add(new CircleThingy(10,1,center,PosY,0));
                System.out.println("Circle Thingy 0");
                break;
            case 3:
                OBS.add(new CircleThingy(15,1,center,PosY,2));
                System.out.println("Circle Thingy 2");
                break;
            case 4:
                OBS.add(new CircleThingy(15,1,center,PosY,1));
                System.out.println("Circle Thingy 1");
                break;
            case 5:
                OBS.add(new DoubleCircleObstacle(60,1,10,center,PosY));
                System.out.println("Double Circle");
                break;
            case 6:
                OBS.add(new PlusObstacle(120,1,10,center + 120 ,PosY));
                System.out.println("Big plus");
                break;
        }
        return OBS;
    }
}
