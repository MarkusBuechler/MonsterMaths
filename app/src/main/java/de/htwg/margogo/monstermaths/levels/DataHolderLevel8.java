package de.htwg.margogo.monstermaths.levels;

import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

/*
 * Singleton class. Dataholder for Level 8
 */
public class DataHolderLevel8 extends DataHolderPrototype implements DataHolderInterface {

    private MonsterDataHolder m1 = new MonsterDataHolder(-0.003f,-0.03f,5);
    private MonsterDataHolder m2 = new MonsterDataHolder(-0.025f,-0.03f,3);
    private MonsterDataHolder m3 = new MonsterDataHolder(0.025f,-0.03f,4);
    private MonsterDataHolder m4 = new MonsterDataHolder(0.003f,-0.03f,5);


    private NumberDataHolder n1 = new NumberDataHolder(0f,-0.03f,9);
    private NumberDataHolder n2 = new NumberDataHolder(0f,-0.02f,8);
    private NumberDataHolder n3 = new NumberDataHolder(0f,-0.01f,7);
    private NumberDataHolder n4 = new NumberDataHolder(0f,0f,6);
    private NumberDataHolder n5 = new NumberDataHolder(0f,0.01f,5);
    private NumberDataHolder n6 = new NumberDataHolder(0f,0.02f,4);

    private OperatorDataHolder o1 = new OperatorDataHolder(0f, -0.04f, "-");

    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1, m2, m3, m4};
    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2,n3, n4, n5 ,n6};
    private OperatorDataHolder operatorDataHolder[] = new OperatorDataHolder[] {o1};

    @Override
    public String getName() {
        return "Level 8";
    }

    @Override
    public String getDescription() {
        return "Subtraction 2";
    }

    @Override
    public Integer getId() {
        return 8;
    }

    @Override
    public Integer getExpectedResult() {
        return -39;
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
        return new BadgeCheck(4, 8,12);
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

    private static final DataHolderLevel8 holder = new DataHolderLevel8();

    public static DataHolderLevel8 getInstance() {
        return holder;
    }

}
