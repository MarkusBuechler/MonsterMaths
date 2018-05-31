package de.htwg.margogo.monstermaths;

import android.util.Log;
import android.widget.Toast;

/*
 * Singleton class. Dataholder for Level 2
 */
public class DataHolderLevel2 implements DataHolderInterface {

    public boolean lock;
    public int time;

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public boolean getLock() {
        return lock;
    }

    public void setTime(int time) {
        this.time = time;
        Log.i("level2!", "setTime: called");
    }

    public int getTime() {
        return time;
    }

    private static final DataHolderLevel2 holder = new DataHolderLevel2();

    public static DataHolderLevel2 getInstance() {
        return holder;
    }
}
