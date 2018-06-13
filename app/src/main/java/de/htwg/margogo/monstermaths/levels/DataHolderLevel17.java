package de.htwg.margogo.monstermaths.levels;

import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

/*
 * Singleton class. Dataholder for Level 17
 * Fastest solution should be -> 9 -> 8 -> 7 -> / -> 8 = 3
 */
public class DataHolderLevel17 extends DataHolderPrototype implements DataHolderInterface {

    private MonsterDataHolder m1 = new MonsterDataHolder(-0.025f,-0.03f,5);
    private MonsterDataHolder m2 = new MonsterDataHolder(-0.025f,-0.03f,3);
    private MonsterDataHolder m3 = new MonsterDataHolder(0.025f,-0.03f,4);
    private MonsterDataHolder m4 = new MonsterDataHolder(0.025f,-0.03f,5);

    private NumberDataHolder n1 = new NumberDataHolder(-0.02f,-0.03f,9);
    private NumberDataHolder n2 = new NumberDataHolder(0.02f,-0.03f,8);
    private NumberDataHolder n3 = new NumberDataHolder(-0.02f,0.03f,7);
    private NumberDataHolder n4 = new NumberDataHolder(0.02f,0.03f,8);

    private OperatorDataHolder o1 = new OperatorDataHolder(0f, -0.01f, "/");

    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1,m2, m3, m4};
    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2, n3, n4};
    private OperatorDataHolder operatorDataHolder[] = new OperatorDataHolder[] {o1};

    @Override
    public String getName() {
        return "Level 17";
    }

    @Override
    public String getDescription() {
        return "Mixed 4";
    }

    @Override
    public Integer getId() {
        return 17;
    }

    @Override
    public Integer getExpectedResult() {
        return 3;
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
        return new BadgeCheck(10, 15,20);
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

    private static final DataHolderLevel17 holder = new DataHolderLevel17();

    public static DataHolderLevel17 getInstance() {
        return holder;
    }
}
