package de.htwg.margogo.monstermaths;

import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

public interface DataHolderInterface {

    boolean getLock();

    Integer getScore();

    String getName();

    String getDescription();

    Badge getBadge();

    Integer getId();

    Integer getNumMonsters();

    Integer getNumNumbers();

    Integer getNumOperators();

    Integer getExpectedResult();

    MonsterDataHolder[] getMonsterDataHolderList();

    NumberDataHolder[] getNumberDataHolderList();

    OperatorDataHolder[] getOperatorDataHolderList();

    void setLock(boolean lock);

    void setScore(int time);

    void setBadge(Badge badge);

}
