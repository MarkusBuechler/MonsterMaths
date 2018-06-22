package de.htwg.margogo.monstermaths.levels;

import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

public class DataHolderPrototype implements DataHolderInterface {

    private boolean lock;

    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {};

    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {};

    private OperatorDataHolder operatorDataHolder[] = new OperatorDataHolder[] {};

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public boolean getLock() {
        return lock;
    }

    @Override
    public Integer getId() {
        return 0;
    }

    @Override
    public Integer getNumMonsters() {
        return monsterDataHolder.length;
    }

    @Override
    public Integer getNumNumbers() {
        return numberDataHolder.length;
    }

    @Override
    public Integer getNumOperators() {
        return operatorDataHolder.length;
    }

    @Override
    public Integer getExpectedResult() {
        return 0;
    }

    @Override
    public MonsterDataHolder[] getMonsterDataHolderList() {
        return monsterDataHolder;
    }

    @Override
    public NumberDataHolder[] getNumberDataHolderList() {
        return numberDataHolder;
    }

    @Override
    public OperatorDataHolder[] getOperatorDataHolderList() {
        return operatorDataHolder;
    }

    @Override
    public BadgeCheck getBadgeCheck() {
        return new BadgeCheck(0,0);
    }

}
