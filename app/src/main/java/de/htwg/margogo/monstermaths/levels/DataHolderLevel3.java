package de.htwg.margogo.monstermaths.levels;

import de.htwg.margogo.monstermaths.R;
import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

/*
 * Singleton class. Dataholder for Level 3
 */
public class DataHolderLevel3 extends DataHolderPrototype implements DataHolderInterface {

    private MonsterDataHolder m1 = new MonsterDataHolder(0.0275f,-0.03f,1);
    private MonsterDataHolder m2 = new MonsterDataHolder(0.0200f,-0.01f,1);
    private MonsterDataHolder m3 = new MonsterDataHolder(0.0235f,0.01f,1);
    private MonsterDataHolder m4 = new MonsterDataHolder(0.0235f,0.03f,1);

    private NumberDataHolder n1 = new NumberDataHolder(0f,-0.020f,3);
    private NumberDataHolder n2 = new NumberDataHolder(0.020f,-0.020f,3);
    private NumberDataHolder n3 = new NumberDataHolder(-0.020f,-0.020f,3);
    private NumberDataHolder n4 = new NumberDataHolder(0.02f,0.020f,4);
    private NumberDataHolder n5 = new NumberDataHolder(-0.02f,0.020f,4);
    private NumberDataHolder n6 = new NumberDataHolder(0.02f,0f,1);
    private NumberDataHolder n7 = new NumberDataHolder(-0.02f,0f,1);

    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1,m2, m3, m4};
    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2,n3,n4, n5, n6, n7};
    private OperatorDataHolder operatorDataHolder[] = new OperatorDataHolder[] {};

    @Override
    public Integer getId() {
        return 3;
    }

    @Override
    public Integer getExpectedResult() {
        return 17;
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
        return new BadgeCheck(6, 10,15);
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

    private static final DataHolderLevel3 holder = new DataHolderLevel3();

    public static DataHolderLevel3 getInstance() {
        return holder;
    }
}
