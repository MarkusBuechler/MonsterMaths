package de.htwg.margogo.monstermaths.levels;

import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

/*
 * Singleton class. Dataholder for Level 5
 */
public class DataHolderLevel5 extends DataHolderPrototype implements DataHolderInterface {

    private MonsterDataHolder m1 = new MonsterDataHolder(-0.005f,0.03f,2);
    private MonsterDataHolder m2 = new MonsterDataHolder(-0.025f,-0.03f,3);
    private MonsterDataHolder m3 = new MonsterDataHolder(0.025f,-0.03f,4);

    private NumberDataHolder n1 = new NumberDataHolder(0f,-0.04f,5);
    private NumberDataHolder n2 = new NumberDataHolder(0f,-0.03f,4);
    private NumberDataHolder n3 = new NumberDataHolder(0f,-0.02f,3);
    private NumberDataHolder n4 = new NumberDataHolder(0f,-0.01f,2);
    private NumberDataHolder n5 = new NumberDataHolder(0f,0f,1);
    private NumberDataHolder n6 = new NumberDataHolder(0f,0.01f,0);
    private NumberDataHolder n7 = new NumberDataHolder(0f,0.02f,1);
    private NumberDataHolder n8 = new NumberDataHolder(0f,0.03f,2);

    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1, m2, m3};
    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2,n3, n4, n5, n6, n7, n8};
    private OperatorDataHolder operatorDataHolder[] = new OperatorDataHolder[] {};

    @Override
    public Integer getId() {
        return 5;
    }

    @Override
    public Integer getExpectedResult() {
        return 15;
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
        return new BadgeCheck(3, 5);
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

    private static final DataHolderLevel5 holder = new DataHolderLevel5();

    public static DataHolderLevel5 getInstance() {
        return holder;
    }

}
