package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import sample.GameElement;
import sample.Obstacles.CircleObstacle;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class DB implements Serializable {
    private static final long serialVersionUID = 7368175650914175345L;
    private int PosY;
    private double Rotate;
    private Class className;
    private ArrayList<Object> args;
    DB() {
    }
    void updateValues(GameElement element) {
        this.PosY = (int)element.getRoot().getBoundsInParent().getCenterY();
        this.Rotate = element.getRoot().getRotate();
        this.className = element.getClass();
        this.args = new ArrayList<>(element.getArgs());
    }
    public void initializeObstacle(Group ObstaclesRoot, Queue<GameElement> obstacles) {
        System.out.println("DB obstacle Created: "+this);
        String name = this.className.getName();
        try {
            Class[] cArg = new Class[args.size()] ;
            Arrays.fill(cArg, int.class);
            if (name.contains("sample.Obstacles")){
                Obstacle obs =  (Obstacle) this.className.getDeclaredConstructor(cArg).newInstance(args.get(0),args.get(1),args.get(2),args.get(3),args.get(4));
                ObstaclesRoot.getChildren().add(obs.getRoot());
                obs.getAnimation().play();
                obstacles.add(obs);
            }
            else if (name.equals("sample.ColorChanger")) {
                ColorChanger obs = (ColorChanger) this.className.getDeclaredConstructor(cArg).newInstance(args.get(0),args.get(1));
                ObstaclesRoot.getChildren().add(obs.getRoot());
                obs.getAnimation().play();
                obstacles.add(obs);
            }
            else if (name.equals("sample.Star")) {
                cArg = new Class[args.size() + 1] ;
                cArg[0] = int.class;cArg[1] = int.class;cArg[2] = Color.class;cArg[3] = int.class;
                Star obs = (Star) this.className.getDeclaredConstructor(cArg).newInstance(args.get(0),args.get(1), Color.AZURE ,args.get(2));
                ObstaclesRoot.getChildren().add(obs.getRoot());
                obs.getAnimation().play();
                obstacles.add(obs);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString () {
        String result = "";
        result += "\ncurrentPos: "+ PosY;
        result += "\nRotate: "+ Rotate;
        result += "\nClass: "+ this.className.getName();
        return result;
    }

    public double getRotate() {
        return Rotate;
    }

    public int getPosY() {
        return PosY;
    }

}
