package de.htwg.margogo.monstermaths.levels;

import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

/*
 * Singleton class. Dataholder for Level 2
 */
public class DataHolderLevel2 extends DataHolderPrototype implements DataHolderInterface {

    private MonsterDataHolder m1 = new MonsterDataHolder(0.0275f,-0.03f,1);
    private MonsterDataHolder m2 = new MonsterDataHolder(0.0200f,-0.01f,1);

    private NumberDataHolder n1 = new NumberDataHolder(0f,-0.015f,5);
    private NumberDataHolder n2 = new NumberDataHolder(0.02f,0.0065f,5);
    private NumberDataHolder n3 = new NumberDataHolder(0.f,0.025f,2);


    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1,m2};
    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2,n3};
    private OperatorDataHolder operatorDataHolder[] = new OperatorDataHolder[] {};

    @Override
    public String getName() {
        return "Level 2";
    }

    @Override
    public String getDescription() {
        return "Introduction to Addition";
    }

    @Override
    public Integer getId() {
        return 2;
    }

    @Override
    public Integer getExpectedResult() {
        return 10;
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
    public BadgeCheck getBadgeCheck() {
        return new BadgeCheck(3, 5,9);
    }

    private static final DataHolderLevel2 holder = new DataHolderLevel2();

    public static DataHolderLevel2 getInstance() {
        return holder;
    }
}
