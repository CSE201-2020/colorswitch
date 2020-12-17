package sample;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;
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
    static int d = 170;

    public static class OB_dist {
        final private ArrayList<GameElement> obstacleList;
        final private int dist;
        OB_dist (ArrayList<GameElement> obs, int dis) {
            obstacleList = obs;
            dist = dis;
        }

        public ArrayList<GameElement> getObstacleList() {
            return obstacleList;
        }

        public int getDist() {
            return dist;
        }
    }

    static double tempH = Math.pow(3,-0.5)*(23.5)* 12 / 2;
    static double temp2H = 7.5 * 12.0 ;
    static double obrate=1;
    static public OB_dist CreateRandomObstacle (int preset, int PosY , int curscore) {
        ArrayList<GameElement> OBS = new ArrayList<GameElement>();
        if(curscore<5){
            if(preset>=8){
                preset-=3;
            }
        }
        int scoreintens=curscore/10;
        if(scoreintens>1){
            obrate=1+scoreintens*0.1;
            if(scoreintens>5){
                obrate=1.5;
                d-=30;
            }
        }
        switch (preset) {
            case 0:
                PosY-=80;
//                CircleObstacle c0 = new CircleObstacle(60, -1,10, center, PosY);
//                int CircleHeight = (int)c0.getRoot().getBoundsInParent().getHeight();
//                PosY-=(d + 130);
                CircleObstacle c1 = new CircleObstacle(80, 1,20, center, PosY);
                c1.getAnimation().setRate(obrate);
                Star newstar=new Star(center, PosY, Color.AZURE,1.1);
                OBS.add(newstar);
//                OBS.add(c0);
                OBS.add(c1);
                PosY-=(80 + d);
                break;
            case 1:
                PosY -= 65;
                PlusObstacle c1p1=new PlusObstacle(60,1,10,center + 60, PosY);
                c1p1.getAnimation().setRate(obrate);
                OBS.add(c1p1);
                newstar=new Star(center, PosY-50, Color.AZURE,1.1);
                OBS.add(newstar);
                PosY -= (65 + d);
                System.out.println("small plus");
                break;
            case 2:
                PosY -= tempH +25;
                OBS.add(new CircleThingy(12,1,center,PosY,0));
                newstar=new Star(center, PosY, Color.AZURE,1.1);
                OBS.add(newstar);
                System.out.println("Circle Thingy 0");
                PosY -= (tempH + d+10);
                break;
            case 3:
                PosY -= tempH;
                OBS.add(new CircleThingy(12,1,center,PosY,1));
                newstar=new Star(center, PosY, Color.AZURE,1.1);
                OBS.add(newstar);
                System.out.println("Circle Thingy 1");
                PosY -= (tempH + d+10);
                break;
            case 4:
                PosY -= temp2H;
                OBS.add(new CircleThingy(12,1,center,PosY,2));
                newstar=new Star(center, PosY, Color.AZURE,1.1);
                OBS.add(newstar);
                System.out.println("Circle Thingy 2");
                PosY -= (temp2H + d);
                break;
            case 5:
                PosY -= 65;
                DoubleCircleObstacle c5d1=new DoubleCircleObstacle(60,1,10,center,PosY);
                c5d1.getAnimation().setRate(obrate);
                OBS.add(c5d1);
                newstar=new Star(center, PosY-50, Color.AZURE,1.1);
                OBS.add(newstar);
                PosY -= (65 + d);
                System.out.println("Double Circle");
                break;
            case 6:
                PosY-=65;
                PlusObstacle c6p1=new PlusObstacle(100,1,20,center + 100 ,PosY);
                c6p1.getAnimation().setRate(obrate);
                OBS.add(c6p1);
                newstar=new Star(center, PosY-50, Color.AZURE,1.1);
                OBS.add(newstar);
                System.out.println("Big plus");
                PosY-=(65 +d);
                break;
            case 7:
                PosY -= 80;
                HorizontalLineObstacle hor0 = new HorizontalLineObstacle(100,1,18,-400,PosY);
                hor0.getAnimation().setRate(obrate);
                newstar=new Star(center, PosY-40, Color.AZURE,1.1);
                OBS.add(hor0);
                OBS.add(newstar);
                System.out.println("hori");
                PosY -= (d);
                break;
            case 8:
                PosY -= temp2H+100;
                CircleThingy dd= new CircleThingy(15,0 ,center,PosY,2);
                dd.getAnimation().setRate(8);
                OBS.add(dd);
                CircleObstacle ch1=  new CircleObstacle(80,0,14,center,PosY);
                ch1.getAnimation().setRate(0.9);

//                OBS.add(new CircleThingy(10,1,center,PosY,1));
                newstar=new Star(center, PosY, Color.AZURE,1.1);
                OBS.add(ch1);
                OBS.add(newstar);
                System.out.println("Triangle inside square");
                PosY -= (temp2H +50+ d);
                break;
            case 9:
                PosY-=120;
//                CircleObstacle c0 = new CircleObstacle(60, -1,10, center, PosY);
//                int CircleHeight = (int)c0.getRoot().getBoundsInParent().getHeight();
//                PosY-=(d + 130);
                CircleObstacle c3 = new CircleObstacle(110, 1,20, center, PosY);
                CircleObstacle c2=  new CircleObstacle(80,0,14,center,PosY,true);
                c2.getAnimation().setRate(0.8);
                c3.getAnimation().setRate(0.8);
                newstar=new Star(center, PosY, Color.AZURE,1.1);
                OBS.add(newstar);
//                OBS.add(c0);
                OBS.add(c3);
                OBS.add(c2);
                PosY-=(120 + d);
                break;

            case 10:
                PosY -= 65;
                DoublePlusObstacle c10d1=new DoublePlusObstacle(60,1,20,center,PosY);
                c10d1.getAnimation().setRate(obrate);
                OBS.add(c10d1);
                newstar=new Star(center, PosY-50, Color.AZURE,1.1);
                OBS.add(newstar);
                PosY -= (65 + d);
                System.out.println("Double Circle");
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
