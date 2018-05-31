package de.htwg.margogo.monstermaths;

import android.util.Log;

/*
 * Singleton class. Dataholder for Level 2
 */
public class DataHolderLevel2 implements DataHolderInterface {

    private boolean lock;
    private int score;
    private String name = "Level 2";
    private String descrption = "Introduction to Addition";
    private Badge badge = Badge.Bronze; // need dafault type
    private int id = 2;

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public boolean getLock() {
        return lock;
    }

    public void setScore(int score) {
        this.score = score;
        Log.i("level2!", "setScore: called");
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
        return descrption;
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

    private static final DataHolderLevel2 holder = new DataHolderLevel2();

    public static DataHolderLevel2 getInstance() {
        return holder;
    }
}
