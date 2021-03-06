package de.htwg.margogo.monstermaths.levels;

import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

/*
 * Singleton class. Dataholder for Level 20
 * Fastest solution should be -> 7 -> * -> 3 -> - -> 6 -> / -> 3 = 5
 */
public class DataHolderLevel20 extends DataHolderPrototype implements DataHolderInterface {

    private MonsterDataHolder m1 = new MonsterDataHolder(-0.005f,0.03f,2);
    private MonsterDataHolder m2 = new MonsterDataHolder(-0.025f,-0.03f,3);
    private MonsterDataHolder m3 = new MonsterDataHolder(0.025f,-0.03f,4);
    private MonsterDataHolder m4 = new MonsterDataHolder(-0.005f,-0.03f,2);
    private MonsterDataHolder m5 = new MonsterDataHolder(-0.025f,-0.01f,3);
    private MonsterDataHolder m6 = new MonsterDataHolder(0.025f,-0.01f,4);

    private NumberDataHolder n1 = new NumberDataHolder(0f,0.01f,3);
    private NumberDataHolder n2 = new NumberDataHolder(0f,-0.01f,7);
    private NumberDataHolder n3 = new NumberDataHolder(0f,0.02f,6);
    private NumberDataHolder n4 = new NumberDataHolder(0f,-0.02f,3);

    private OperatorDataHolder o1 = new OperatorDataHolder(0f, 0f, "*");
    private OperatorDataHolder o2 = new OperatorDataHolder(0.02f, 0f, "/");
    private OperatorDataHolder o3 = new OperatorDataHolder(-0.02f, 0f, "-");

    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1, m2, m3, m4, m5, m6};
    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2,n3, n4};
    private OperatorDataHolder operatorDataHolder[] = new OperatorDataHolder[] {o1, o2, o3};

    @Override
    public Integer getId() {
        return 20;
    }

    @Override
    public Integer getExpectedResult() {
        return 5;
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
        return new BadgeCheck(14, 20);
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

    private static final DataHolderLevel20 holder = new DataHolderLevel20();

    public static DataHolderLevel20 getInstance() {
        return holder;
    }

}
