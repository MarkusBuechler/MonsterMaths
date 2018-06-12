package de.htwg.margogo.monstermaths;

import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;

public interface DataHolderInterface {

    boolean getLock();

    Integer getScore();

    String getName();

    String getDescription();

    Badge getBadge();

    Integer getId();

    Integer getNumMonsters();

    Integer getNumNumbers();

    Integer getExpectedResult();

    MonsterDataHolder[] getMonsterDataHolderList();

    NumberDataHolder[] getNumberDataHolderList();

    void setLock(boolean lock);

    void setScore(int time);

    void setBadge(Badge badge);

}
