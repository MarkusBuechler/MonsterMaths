package de.htwg.margogo.monstermaths.levels;

import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

/*
 * Singleton class. Dataholder for Level 18
 * Fastest solution should be -> 8 -> 8 -> - -> 1 = 15
 */
public class DataHolderLevel18 extends DataHolderPrototype implements DataHolderInterface {

    private MonsterDataHolder m1 = new MonsterDataHolder(-0.003f,-0.03f,5);
    private MonsterDataHolder m2 = new MonsterDataHolder(0.003f,-0.03f,5);
    private MonsterDataHolder m3 = new MonsterDataHolder(-0.003f,-0.015f,5);
    private MonsterDataHolder m4 = new MonsterDataHolder(0.003f,-0.015f,5);
    private MonsterDataHolder m5 = new MonsterDataHolder(-0.003f,0f,5);
    private MonsterDataHolder m6 = new MonsterDataHolder(0.003f,0f,5);

    private NumberDataHolder n1 = new NumberDataHolder(-0.028f,0.045f,8);
    private NumberDataHolder n2 = new NumberDataHolder(0.028f,0.045f,8);
    private NumberDataHolder n3 = new NumberDataHolder(-0.028f,-0.045f,4);
    private NumberDataHolder n4 = new NumberDataHolder(0.028f,-0.045f,1);

    private OperatorDataHolder o1 = new OperatorDataHolder(0f, 0f, "*");
    private OperatorDataHolder o2 = new OperatorDataHolder(0f, -0.02f, "/");
    private OperatorDataHolder o3 = new OperatorDataHolder(0f, 0.02f, "-");

    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1, m2, m3, m4, m5, m6};
    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2,n3, n4};
    private OperatorDataHolder operatorDataHolder[] = new OperatorDataHolder[] {o1, o2, o3};

    @Override
    public Integer getId() {
        return 18;
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
        return new BadgeCheck(6, 12);
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

    private static final DataHolderLevel18 holder = new DataHolderLevel18();

    public static DataHolderLevel18 getInstance() {
        return holder;
    }

}
