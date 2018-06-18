package de.htwg.margogo.monstermaths.levels;

import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

/*
 * Singleton class. Dataholder for Level 14
 * Fastest solution should be -> 1 -> * -> 7 -> 5 -> 6 = 210
 */
public class DataHolderLevel14 extends DataHolderPrototype implements DataHolderInterface {

    private MonsterDataHolder m1 = new MonsterDataHolder(0.0275f,-0.03f,1);
    private MonsterDataHolder m2 = new MonsterDataHolder(0.0200f,-0.01f,1);
    private MonsterDataHolder m3 = new MonsterDataHolder(0.0200f,0.01f,1);
    private MonsterDataHolder m4 = new MonsterDataHolder(0.0275f,0.03f,1);

    private NumberDataHolder n1 = new NumberDataHolder(-0.02f,-0.020f,9);
    private NumberDataHolder n2 = new NumberDataHolder(0f,-0.020f,8);
    private NumberDataHolder n3 = new NumberDataHolder(0.02f,-0.020f,7);
    private NumberDataHolder n4 = new NumberDataHolder(-0.02f,0f,6);
    private NumberDataHolder n5 = new NumberDataHolder(0f,0f,5);
    private NumberDataHolder n6 = new NumberDataHolder(0.02f,0f,4);
    private NumberDataHolder n7 = new NumberDataHolder(-0.02f,0.020f,3);
    private NumberDataHolder n8 = new NumberDataHolder(0f,0.020f,2);
    private NumberDataHolder n9 = new NumberDataHolder(0.02f,0.020f,1);

    private OperatorDataHolder o1 = new OperatorDataHolder(0.01f, -0.045f, "*");

    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1,m2, m3, m4};
    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2,n3,n4, n5, n6, n7, n8, n9};
    private OperatorDataHolder operatorDataHolder[] = new OperatorDataHolder[] {o1};

    @Override
    public Integer getId() {
        return 14;
    }

    @Override
    public Integer getExpectedResult() {
        return 210;
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
        return new BadgeCheck(11, 16,22);
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

    private static final DataHolderLevel14 holder = new DataHolderLevel14();

    public static DataHolderLevel14 getInstance() {
        return holder;
    }
}
