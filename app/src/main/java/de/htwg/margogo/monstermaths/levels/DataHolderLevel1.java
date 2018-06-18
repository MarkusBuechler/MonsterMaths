package de.htwg.margogo.monstermaths.levels;

import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

/*
 * Singleton class. Dataholder for Level 1
 */
public class DataHolderLevel1 extends DataHolderPrototype implements DataHolderInterface  {

    private MonsterDataHolder m1 = new MonsterDataHolder(0.0275f,-0.03f,1);

    private NumberDataHolder n1 = new NumberDataHolder(0f,-0.015f,1);
    private NumberDataHolder n2 = new NumberDataHolder(0.02f,0.0065f,1);

    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1};
    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2};
    private OperatorDataHolder operatorDataHolder[] = new OperatorDataHolder[] {};

    @Override
    public Integer getId() {
        return 1;
    }

    @Override
    public Integer getExpectedResult() {
        return 2;
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
        return new BadgeCheck(3, 5,9);
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

    private static final DataHolderLevel1 holder = new DataHolderLevel1();

    public static DataHolderLevel1 getInstance() {
        return holder;
    }

}
