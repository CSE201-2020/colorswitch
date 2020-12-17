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
    static int d = 150;

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

    static double tempH = Math.pow(3,-0.5)*(23.5)* 12 / 2;
    static double temp2H = 7.5 * 12.0 ;
    static public OB_dist CreateRandomObstacle (int preset, int PosY) {

        ArrayList<Obstacle> OBS = new ArrayList<Obstacle>();

        switch (preset) {
            case 0:
                PosY-=65;
//                CircleObstacle c0 = new CircleObstacle(60, -1,10, center, PosY);
//                int CircleHeight = (int)c0.getRoot().getBoundsInParent().getHeight();
//                PosY-=(d + 130);
                CircleObstacle c1 = new CircleObstacle(60, 1,20, center, PosY);
//                OBS.add(c0);
                OBS.add(c1);
                PosY-=(65 + d);
                break;
            case 1:
                PosY -= 65;
                OBS.add(new PlusObstacle(60,1,10,center + 60, PosY));
                PosY -= (65 + d);
                System.out.println("small plus");
                break;
            case 2:
                PosY -= tempH;
                OBS.add(new CircleThingy(12,1,center,PosY,0));
                System.out.println("Circle Thingy 0");
                PosY -= (tempH + d+10);
                break;
            case 3:
                PosY -= tempH;
                OBS.add(new CircleThingy(12,1,center,PosY,1));
                System.out.println("Circle Thingy 1");
                PosY -= (tempH + d+10);
                break;
            case 4:
                PosY -= temp2H;
                OBS.add(new CircleThingy(12,1,center,PosY,2));
                System.out.println("Circle Thingy 2");
                PosY -= (temp2H + d);
                break;
            case 5:
                PosY -= 65;
                OBS.add(new DoubleCircleObstacle(60,1,10,center,PosY));
                PosY -= (65 + d);
                System.out.println("Double Circle");
                break;
            case 6:
                PosY-=65;
                OBS.add(new PlusObstacle(100,1,20,center + 100 ,PosY));
                System.out.println("Big plus");
                PosY-=(65 +d);
                break;
            case 7:
                PosY -= 10;
                HorizontalLineObstacle hor0 = new HorizontalLineObstacle(100,1,18,-400,PosY);
                System.out.println("hori");
                PosY -= (10+10);
                break;
            case 8:
                PosY -= tempH;
                OBS.add(new CircleThingy(15,0 ,center,PosY,2));
                OBS.add(new CircleThingy(12,1,center,PosY,1));
                System.out.println("Triangle inside square");
                PosY -= (tempH + d);
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
