package de.htwg.margogo.monstermaths;

import android.util.Log;

/*
 * Singleton class. Dataholder for Level 1
 */
public class DataHolderLevel1 implements DataHolderInterface {

    private boolean lock;
    private int score;
    private String name = "Level 1";
    private String description = "Basic Introduction to the game concept";
    private Badge badge = Badge.Bronze;
    private int id = 1;

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public boolean getLock() {
        return lock;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Badge getBadge() {
        return badge;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    private static final DataHolderLevel1 holder = new DataHolderLevel1();

    public static DataHolderLevel1 getInstance() {
        return holder;
    }

}
