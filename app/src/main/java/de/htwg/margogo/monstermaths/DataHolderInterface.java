package de.htwg.margogo.monstermaths;

public interface DataHolderInterface {

    boolean getLock();

    int getScore();

    String getName();

    String getDescription();

    Badge getBadge();

    Integer getId();

    void setLock(boolean lock);

    void setScore(int time);

    void setBadge(Badge badge);

}
