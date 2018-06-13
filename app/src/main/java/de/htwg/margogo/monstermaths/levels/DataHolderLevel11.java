package de.htwg.margogo.monstermaths.levels;

import java.util.ArrayList;
import java.util.List;

import de.htwg.margogo.monstermaths.Badge;
import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

/*
 * Singleton class. Dataholder for Level 11
 */
public class DataHolderLevel11 implements DataHolderInterface {

    private boolean lock;
    private int score;
    private List scoreList = new ArrayList<Integer>();
    private Badge badge = Badge.Bronze;

    private static final int NUM_MONSTERS = 3;
    private static final int NUM_NUMBERS = 4;
    private static final int NUM_OPERATORS = 1;

    private MonsterDataHolder m1 = new MonsterDataHolder(-0.005f,0.03f,2);
    private MonsterDataHolder m2 = new MonsterDataHolder(-0.025f,-0.03f,3);
    private MonsterDataHolder m3 = new MonsterDataHolder(0.025f,-0.03f,4);

    private NumberDataHolder n1 = new NumberDataHolder(0f,-0.02f,1);
    private NumberDataHolder n2 = new NumberDataHolder(0.025f,-0.025f,3);
    private NumberDataHolder n3 = new NumberDataHolder(0.f,0.02f,3);
    private NumberDataHolder n4 = new NumberDataHolder(-0.025f,-0.025f,3);

    private OperatorDataHolder o1 = new OperatorDataHolder(0f, 0f, "*");

    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1, m2, m3};

    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2,n3, n4};

    private OperatorDataHolder operatorDataHolder[] = new OperatorDataHolder[] {o1};

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    @Override
    public List getScoreList() {
        return scoreList;
    }

    public boolean getLock() {
        return lock;
    }

    public void insertScore(int score) {
        scoreList.add(score);
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public String getName() {
        return "Level 11";
    }

    @Override
    public String getDescription() {
        return "Introduction to Multiplication";
    }

    @Override
    public Badge getBadge() {
        return badge;
    }

    @Override
    public Integer getId() {
        return 11;
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
        return 27;
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

    private static final DataHolderLevel11 holder = new DataHolderLevel11();

    public static DataHolderLevel11 getInstance() {
        return holder;
    }

}
