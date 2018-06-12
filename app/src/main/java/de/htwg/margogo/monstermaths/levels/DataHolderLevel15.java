package de.htwg.margogo.monstermaths.levels;

import de.htwg.margogo.monstermaths.Badge;
import de.htwg.margogo.monstermaths.DataHolderInterface;
import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

/*
 * Singleton class. Dataholder for Level 14
 */
public class DataHolderLevel15 implements DataHolderInterface {

    private boolean lock;
    private int score;
    private Badge badge = Badge.Bronze; // need dafault type

    private static final int NUM_MONSTERS = 4;
    private static final int NUM_NUMBERS = 9;
    private static final int NUM_OPERATORS = 2;

    private MonsterDataHolder m1 = new MonsterDataHolder(0.0275f,-0.03f,1);
    private MonsterDataHolder m2 = new MonsterDataHolder(0.0200f,-0.01f,1);
    private MonsterDataHolder m3 = new MonsterDataHolder(0.0200f,0.01f,1);
    private MonsterDataHolder m4 = new MonsterDataHolder(0.0275f,0.03f,1);

    private NumberDataHolder n1 = new NumberDataHolder(-0.02f,-0.020f,9);
    private NumberDataHolder n2 = new NumberDataHolder(0f,-0.020f,8);
    private NumberDataHolder n3 = new NumberDataHolder(0.02f,-0.020f,7);

    private NumberDataHolder n4 = new NumberDataHolder(-0.02f,0f,6);
    private NumberDataHolder n5 = new NumberDataHolder(0f,0f,1);
    private NumberDataHolder n6 = new NumberDataHolder(0.02f,0f,4);

    private NumberDataHolder n7 = new NumberDataHolder(-0.02f,0.020f,3);
    private NumberDataHolder n8 = new NumberDataHolder(0f,0.020f,2);
    private NumberDataHolder n9 = new NumberDataHolder(0.02f,0.020f,1);

    private OperatorDataHolder o1 = new OperatorDataHolder(0.01f, -0.045f, "*");
    private OperatorDataHolder o2 = new OperatorDataHolder(-0.01f, -0.045f, "-");

    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1,m2, m3, m4};

    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2,n3,n4, n5, n6, n7, n8, n9};

    private OperatorDataHolder operatorDataHolder[] = new OperatorDataHolder[] {o1, o2};

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public boolean getLock() {
        return lock;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public String getName() {
        return "Level 15";
    }

    @Override
    public String getDescription() {
        return "Mixed 3";
    }

    @Override
    public Badge getBadge() {
        return badge;
    }

    @Override
    public Integer getId() {
        return 15;
    }

    @Override
    public Integer getNumMonsters() {
        return NUM_MONSTERS;
    }

    @Override
    public Integer getNumNumbers() {
        return NUM_NUMBERS;
    }

    @Override
    public Integer getNumOperators() {
        return NUM_OPERATORS;
    }

    @Override
    public Integer getExpectedResult() {
        return 53;
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
    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    private static final DataHolderLevel15 holder = new DataHolderLevel15();

    public static DataHolderLevel15 getInstance() {
        return holder;
    }
}
