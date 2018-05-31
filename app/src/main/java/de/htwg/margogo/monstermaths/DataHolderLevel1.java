package de.htwg.margogo.monstermaths;

/*
 * Singleton class. Dataholder for Level 1
 */
public class DataHolderLevel1 {

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
    }

    public int getTime() {
        return time;
    }

    private static final DataHolderLevel1 holder = new DataHolderLevel1();

    public static DataHolderLevel1 getInstace() {
        return holder;
    }
}
