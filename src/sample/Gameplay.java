package sample;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Obstacles.CircleObstacle;
import sample.Obstacles.HorizontalLineObstacle;
import sample.Obstacles.PlusObstacle;
import sample.Obstacles.CircleThingy;
import sample.animations.Disintegration;
import sample.animations.StarCollected;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class Gameplay {

    final int presetLength = 9;
    public static class DB implements Serializable {
        private static final long serialVersionUID = 7368175650914175345L;

      double ballPos = 0 ;
      int currentPos = -1200 ;
      double cameraPos = 0;
      transient Player pl; // TODO // divide into posy, color
        Queue<sample.DB> obstac= new LinkedList<>(); // TODO // divide into posY, rotation
        int curscore=0;  // TODO
        DB() {
        }
        void updateValues(Queue<sample.DB> obstacles, int cur, double ballPos,double cameraPos, int currentPos) {
            this.ballPos = ballPos;
            this.cameraPos = cameraPos;
            this.currentPos = currentPos;
            this.obstac = new LinkedList<>(obstacles);
            this.curscore = cur;
            System.out.println("savingsavingsavingsavingsavingsavingsavingsavingsavingsavingsaving"+obstacles);
        }
        void initializeObstacles(Group ObstaclesRoot, Queue <GameElement> obstacles) {
            if (this.obstac == null) return;
            for (sample.DB obx: this.obstac) {
                obx.initializeObstacle(ObstaclesRoot, obstacles);
            }
        }
        @Override
        public String toString () {
            String result = "";
            result += "\nballPos: "+ ballPos;
            result += "\nObstaclePos: "+ cameraPos ;
            result += "\nobstacles: "+ obstac;
            result += "\ncurrScore: "+ curscore;
//        result += "\ngamesplayed: "+ gamesplayed;
//        result += "\ngamessaves: "+ gamessaves;
            return result;
        }

    }
    DB gameInfo = new DB();


    final private transient Random rand = new Random();
    int currentPos = -600 ; // TODO

    boolean disentegrated = false;
    Player pl; // TODO
    Popup popup;
    private Scene mainScene;
    Queue<GameElement> obstacles= new LinkedList<>(); // TODO
    Group ObstaclesRoot;
    transient int center = 200;
    Label score;
    int curscore=0;  // TODO
    transient User user;
    AnchorPane panePause = null;
    AnchorPane paneDeath = null;


    Gameplay (int height, double ratio, User user) {
        this.user = user;
        System.out.println(user);

        ObstaclesRoot = initiateTestObstacles();
        pl = initiatePlayer();

        ObstaclesRoot.getChildren().add(pl.getBall());
        Group MainRoot = new Group();
        score = new Label("0");
        score.setFont(Font.font("Bookshelf Symbol 7",40));
        score.setTextFill(Paint.valueOf("#fff9f9"));
        score.setTranslateY(5);
        MainRoot.getChildren().add(makePauseButton(80,-250));
        MainRoot.getChildren().add(makeStarCount(58,25));
        MainRoot.getChildren().add(score);

        MainRoot.getChildren().add(ObstaclesRoot);


        double speed = (1200)/(6000.0);
        float dist = 1200f;
        TranslateTransition tt = new TranslateTransition(Duration.millis(dist/speed), ObstaclesRoot);
        tt.setByY(dist);
        tt.setCycleCount(1);
        tt.setInterpolator(Interpolator.LINEAR);

        mainScene =new Scene(MainRoot, height*ratio, height);

        mainScene.addEventHandler(KeyEvent.KEY_PRESSED,eventHandler);

        Timeline gameLoop = new Timeline(
                new KeyFrame(Duration.millis(16),  // could be improved for performance

                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                double yPos =  pl.getBall().getTranslateY()+ ObstaclesRoot.getTranslateY();
                                //postion of ball from top of screen .
                                if (yPos < -425) tt.play();
                                else tt.pause();
                                if(yPos > -10) pl.getAnimation().pause();
                                if (pl.getBall().getTranslateY() < currentPos) currentPos = addNewObstacles(currentPos);

                                handleCollisions(pl);
                            }
                        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
        mainScene.setFill(Color.web("272727"));
    }

    Gameplay (int height, double ratio, User user, DB gameInfo) {
        this.user = user;
        System.out.println(user);
        System.out.println("Game Info found !:"+ gameInfo);

        ObstaclesRoot = new Group();
        gameInfo.initializeObstacles(ObstaclesRoot, this.obstacles);
        pl = initiatePlayer();
        pl.getBall().setTranslateY(gameInfo.ballPos);

        ObstaclesRoot.getChildren().add(pl.getBall());
        ObstaclesRoot.setTranslateY(gameInfo.cameraPos);
        Group MainRoot = new Group();

        this.currentPos = gameInfo.currentPos;

        this.curscore = gameInfo.curscore;
        score = new Label(""+gameInfo.curscore);
        score.setFont(Font.font("Bookshelf Symbol 7",40));
        score.setTextFill(Paint.valueOf("#fff9f9"));
        score.setTranslateY(5);

        MainRoot.getChildren().add(makePauseButton(80,-250));
        MainRoot.getChildren().add(makeStarCount(60,25));
        MainRoot.getChildren().add(score);
        MainRoot.getChildren().add(ObstaclesRoot);


        double speed = (1200)/(6000.0);
        float dist = 1200f;
        TranslateTransition tt = new TranslateTransition(Duration.millis(dist/speed), ObstaclesRoot);
        tt.setByY(dist);
        tt.setCycleCount(1);
        tt.setInterpolator(Interpolator.LINEAR);

        mainScene =new Scene(MainRoot, height*ratio, height);

        mainScene.addEventHandler(KeyEvent.KEY_PRESSED,eventHandler);

        Timeline gameLoop = new Timeline(
                new KeyFrame(Duration.millis(16),  // could be improved for performance

                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                double yPos =  pl.getBall().getTranslateY()+ ObstaclesRoot.getTranslateY();
                                //postion of ball from top of screen .
                                if (yPos < -425) tt.play();
                                else tt.pause();
                                if(yPos > -10) pl.getAnimation().pause();
                                if (pl.getBall().getTranslateY() < currentPos) currentPos = addNewObstacles(currentPos);

                                handleCollisions(pl);
                            }
                        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
        mainScene.setFill(Color.web("272727"));
    }

    int addNewObstacles(int posY) {
        int preset = rand.nextInt(2 )+2;
//        preset = 3 ;
        ObstacleFactory.OB_dist N = ObstacleFactory.CreateRandomObstacle(preset, posY,curscore);
        ArrayList<GameElement> NEW = N.getObstacleList();
        System.out.println("----------------"+ N.getObstacleList()+""+N.getDist());
        int NEW_LENGTH = NEW.size();
        // gotta delete NEW_LENGTH from queue to save memory.
//        for (int i =0;i< NEW_LENGTH;i++) {
//            GameElement toRemove = obstacles.poll();
//            if (toRemove != null) ObstaclesRoot.getChildren().remove(toRemove.getRoot());
//        }
        //check queue size ::
        //System.out.println(obstacles.size()+" "+" "+ObstaclesRoot.getChildren().size());
        for (GameElement obx: NEW) {
            obstacles.add(obx);
            ObstaclesRoot.getChildren().add(obx.getRoot());
            obx.getAnimation().play();
        }
        posY=N.getDist();
        ColorChanger newcolorchanger = new ColorChanger(center,posY+75);
        int newcolorchance=rand.nextInt(3);
        if(newcolorchance==2){
            obstacles.add(newcolorchanger);
            ObstaclesRoot.getChildren().add(newcolorchanger.getRoot());
        }
        pl.getBall().toFront();

//        Star newstar=new Star(center, posY+215,Color.AZURE,1.1);
//        double tempH = Math.pow(3,-0.5)*(23.5)* 12 / 2;
//        if(preset==2 || preset== 3 )
//            newstar.setposY(posY+215+tempH);
//        if(preset==7)
//            newstar.setposY(posY-20);
//        newstar.getAnimation().play();
//        obstacles.add(newstar);
//        ObstaclesRoot.getChildren().add(newstar.getRoot());
        return N.getDist();
    }

    void initiateDeathSequence () {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "DeathView.fxml"
                )
        );
        try {
            paneDeath = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DeathView controller = loader.getController();
        controller.initData0(this);

        popup = new Popup();
        if (paneDeath != null ) {
            System.out.println(popup.getContent().setAll(paneDeath));
        }
        Stage stage=(Stage) this.getMainScene().getWindow();
        if (!popup.isShowing()) {
            pauseEverything();
            popup.show(stage);
            ColorAdjust colorAdjust2 = new ColorAdjust();
            colorAdjust2.setBrightness(-0.6);
            mainScene.getRoot().setEffect(colorAdjust2);
        }


    }

    void handleCollisions(Player  pl) {

        GameElement toBeRemoved = null;  // remove star and colorchanger
        System.out.println(obstacles.size()+" "+ObstaclesRoot.getChildren().size());
        for (GameElement node : obstacles ) {
            // detecting collision goes here.
            int status = node.checkCollision(pl);
//            int status  = 3;
            if (status < 0) {
                 if (!disentegrated) {
 //                    System.out.print("circle ");
                     Disintegration dis =  new Disintegration(pl, 10);
                     dis.getAnimation().play();
                     ObstaclesRoot.getChildren().add(dis.getRoot());
                     disentegrated = true;
                     System.out.println("detected");
                     initiateDeathSequence () ;
                     toBeRemoved = node;
                     ObstaclesRoot.getChildren().remove(node.getRoot());
                 }
            }
            else if (status == 1) {
                System.out.println("STAR STAR");
                StarCollected col =  new StarCollected(pl, 5);
                col.getAnimation().play();

                curscore++;
                String s= "" +curscore;
                score.setText(s);

                ObstaclesRoot.getChildren().add(col.getRoot());
                ObstaclesRoot.getChildren().remove(node.getRoot());
                toBeRemoved = node;
            }
            else if (status == 2) {
                System.out.println("COLOR CHANGER");
                pl.changeColor();
                ObstaclesRoot.getChildren().remove(node.getRoot());
                toBeRemoved = node;
            }

        }
        if (toBeRemoved!=null) obstacles.remove(toBeRemoved);
    }

    Node makePauseButton(int x, int y) {
        String svg = "m282.824 0c-155.947 0-282.824 126.877-282.824 282.824s126.877 282.824 282.824 282.824 282.824-126.877 282.824-282.824-126.877-282.824-282.824-282.824zm-35.353 388.883h-70.706v-212.118h70.706zm141.412 0h-70.706v-212.118h70.706z";
        SVGPath pause = new SVGPath();
        pause.setPickOnBounds(true);
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "PauseView.fxml"
                )
        );

        pause.setCursor(Cursor.OPEN_HAND);
        try {
            panePause = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PauseView controller = loader.getController();
        controller.initData0(this);

        EventHandler<MouseEvent> event =
                new EventHandler<MouseEvent>() {

                    public void handle(MouseEvent e)
                    {
                        Node node=(Node) e.getSource();
                        Stage stage=(Stage) node.getScene().getWindow();
                        popup = new Popup();
                        try {
                            panePause = loader.load();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        if (panePause != null ) {
                            popup.getContent().setAll(panePause);
                        }
                        if (!popup.isShowing()) {
                            pauseEverything();
                            ColorAdjust colorAdjust2 = new ColorAdjust();
                            colorAdjust2.setBrightness(-0.6);
                            mainScene.getRoot().setEffect(colorAdjust2);
                            popup.show(stage);
                        }

                    }
                };

        pause.setOnMouseClicked(event);
        pause.setContent(svg);
        pause.setFill(Color.AZURE);
        double ratio = 0.06;
        pause.setScaleY(ratio);
        pause.setScaleX(ratio);
        pause.setTranslateX(x);
        pause.setTranslateY(y);
        return pause;
    }

    Node makeStarCount(int x,int y) {
        Star StarImage = new Star(x,y,Color.AZURE,1.1);
        double ratio = 0.06;
        StarImage.setScaleStar(1.35,1.35);
        return StarImage.getRoot();
    }

    EventHandler<KeyEvent> eventHandler;

    Group initiateTestObstacles () {
        CircleObstacle obs = new CircleObstacle(100,-1,24,center,200);
        CircleObstacle obs2 = new CircleObstacle(100,1,24,center,-200);
        Star star1 = new Star(center, 200,Color.AZURE,1.1);
        Star star2 = new Star(center, 0,Color.AZURE,1.1);
        Star star3 = new Star(center, -200,Color.AZURE,1.1);
       // PlusObstacle plus0 = new PlusObstacle(60,1,15,center + 60,-600);
        //PlusObstacle plus1 = new PlusObstacle(100,1,20,center - 80 ,-1000);
        HorizontalLineObstacle hor0 = new HorizontalLineObstacle(100,1,18,-400,-500);
       // CircleThingy test = new CircleThingy(15,1,center,-1600,0);
        //This is the star for collection not score.
        ColorChanger tary= new ColorChanger(center,-400);

        obs.getAnimation().play();
        obs2.getAnimation().play();
       // plus0.getAnimation().play();
       // plus1.getAnimation().play();
        star1.getAnimation().play();
        star2.getAnimation().play();
        star3.getAnimation().play();
       // test.getAnimation().play();
        obs.getAnimation().setRate(0.7);
        obs2.getAnimation().setRate(0.7);
        obstacles.add(obs);
        obstacles.add(obs2);
        obstacles.add(star1);
        obstacles.add(star2);
        obstacles.add(star3);
      //  obstacles.add(plus0);
      //  obstacles.add(plus1);
        obstacles.add(hor0);

        //obstacles.add(test);
        obstacles.add(tary);


        Group ObstaclesRoot = new Group();
        ObstaclesRoot.getChildren().add(obs2.getRoot());
        ObstaclesRoot.getChildren().add(obs.getRoot());
        //ObstaclesRoot.getChildren().add(plus0.getRoot());
        ObstaclesRoot.getChildren().add(star1.getRoot());
        ObstaclesRoot.getChildren().add(star2.getRoot());
        ObstaclesRoot.getChildren().add(star3.getRoot());
        ObstaclesRoot.getChildren().add(hor0.getRoot());
       // ObstaclesRoot.getChildren().add(plus1.getRoot());
       // ObstaclesRoot.getChildren().add(test.getRoot());
        ObstaclesRoot.getChildren().add(tary.getRoot());

        return ObstaclesRoot;
    }

    Player initiatePlayer () {
        Player pl1 = new Player(Color.web("#FF0181"),center,600);
        Circle ball = pl1.getBall();
        eventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
//                ball.setFill(ball.getFill() == Color.LIME ? Color.HOTPINK : Color.LIME);
                pl1.handleJumpEvent();
            }
        };
//        pl1.getAnimation().play();
        return pl1;
    }

    boolean pauseEverything () {
        obstacles.forEach(gameElement -> {
            gameElement.getAnimation().pause();
        });
        pl.getAnimation().pause();
        return true;
    }

    boolean playEverything () {
        obstacles.forEach(gameElement -> {
            gameElement.getAnimation().play();
        });
        pl.getAnimation().play();
        return true;
    }

    public Popup getPopup() {
        return popup;
    }

    public void hidePopup() {

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0);
        mainScene.getRoot().setEffect(colorAdjust);

        this.popup.hide();
        playEverything();
    }

    public void saveGame() {
        Queue<sample.DB> q = new LinkedList<>();
        this.obstacles.forEach(gameElement -> {
            gameElement.obsInfo.updateValues(gameElement);
            q.add(gameElement.obsInfo);
        });
        this.gameInfo.updateValues(q,this.curscore,this.pl.getBall().getTranslateY(),this.ObstaclesRoot.getTranslateY(), currentPos);
        this.user.getGamelist().put(new Date(), this.gameInfo);
        try {
            Main.serializeList("users_main");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Scene getMainScene() {
        return mainScene;
    }
}
