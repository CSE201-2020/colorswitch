package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private int totalstars;
    private String username;
    private int gamesplayed;
    private int gamessaves;
    private ArrayList<Gameplay> gamelist = new ArrayList<>();
    public void setUsername(String username) {
        this.username = username;
    }

    public void setTotalstars(int totalstars) {
        this.totalstars = totalstars;
    }

    public int getTotalstars() {
        return totalstars;
    }
}
