package de.htwg.margogo.monstermaths;

import android.util.Log;

/*
 * Singleton class. Dataholder for Level 1
 */
public class DataHolderLevel1 implements DataHolderInterface {

    public boolean lock;
    public int time;
    public String name;

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public boolean getLock() {
        return lock;
    }

    public void setTime(int time) {
        this.time = time;
        Log.i("level1!", "setTime: called");
    }

    public int getTime() {
        return time;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    private static final DataHolderLevel1 holder = new DataHolderLevel1();

    public static DataHolderLevel1 getInstance() {
        return holder;
    }

}
