package de.htwg.margogo.monstermaths.levels;

import java.util.List;

import de.htwg.margogo.monstermaths.Badge;
import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

public interface DataHolderInterface {

    boolean getLock();

    void setLock(boolean lock);

    String getName();

    String getDescription();

    Badge getBadge();

    void setBadge(Badge badge);

    Integer getId();

    Integer getNumMonsters();

    Integer getNumNumbers();

    Integer getNumOperators();

    Integer getExpectedResult();

    MonsterDataHolder[] getMonsterDataHolderList();

    NumberDataHolder[] getNumberDataHolderList();

    OperatorDataHolder[] getOperatorDataHolderList();

}
