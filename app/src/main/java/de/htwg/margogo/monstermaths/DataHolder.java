package de.htwg.margogo.monstermaths;

public class DataHolder {

    public boolean lock;

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public boolean getLock() {
        return lock;
    }

    private static final DataHolder holder = new DataHolder();

    public static DataHolder getInstace() {
        return holder;
    }
}
