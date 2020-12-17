package sample;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class User implements Serializable {
    private static final long serialVersionUID = 7368175650914175345L;

    private int totalStars; //TODO
    private String username;//TODO
//    private int gamesplayed;
//    private int gamessaves;
    private HashMap<Date , Gameplay.DB> gamelist = new HashMap<>(); //TODO

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString () {
        String result = "";
        result += "\ntotal Stars: "+ totalStars;
        result += "\nusername: "+ username;
        result += "\nsizeOfgameList: "+ gamelist.size();
//        result += "\ngamesplayed: "+ gamesplayed;
//        result += "\ngamessaves: "+ gamessaves;
        return result;
    }

    public void setTotalStars(int totalstars) {
        this.totalStars = totalstars;
    }

    public int getTotalstars() {
        return totalStars;
    }

    public String getUsername() {
        return username;
    }

    public HashMap<Date, Gameplay.DB> getGamelist() {
        return gamelist;
    }
}
