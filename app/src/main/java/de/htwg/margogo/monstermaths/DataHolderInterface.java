package de.htwg.margogo.monstermaths;

public interface DataHolderInterface {

    boolean getLock();

    int getScore();

    String getName();

    String getDescription();

    Badge getBadge();

    Integer getId();

    Integer getNumMonsters();

    Integer getNumNumbers();

    MonsterDataHolder[] getMonsterDataHolderList();

    NumberDataHolder[] getNumberDataHolderList();

    void setLock(boolean lock);

    void setScore(int time);

    void setBadge(Badge badge);

}
