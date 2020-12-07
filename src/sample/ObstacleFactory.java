package sample;

import javafx.geometry.Pos;
import sample.Obstacles.CircleObstacle;
import sample.Obstacles.DoubleCircleObstacle;
import sample.Obstacles.HorizontalLineObstacle;
import sample.Obstacles.PlusObstacle;

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

    static public ArrayList<Obstacle> CreateRandomObstacle (int preset, int PosY) {

        ArrayList<Obstacle> OBS = new ArrayList<Obstacle>();
        switch (preset) {
            case 0:
                OBS.add(new CircleObstacle(60, -1,10, center, PosY));
                break;
            case 1:
                OBS.add(new PlusObstacle(60,1,10,center + 60, PosY));
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                OBS.add(new DoubleCircleObstacle(60,1,10,center,PosY));
                break;
            case 5:
                break;
        }
        return OBS;
    }
}
