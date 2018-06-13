package de.htwg.margogo.monstermaths.levels;

import java.util.ArrayList;
import java.util.List;

import de.htwg.margogo.monstermaths.Badge;
import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

/*
 * Singleton class. Dataholder for Level 9
 */
public class DataHolderLevel9 implements DataHolderInterface {

    private boolean lock;
    private int score;
    private List scoreList = new ArrayList<Integer>();
    private Badge badge = Badge.Bronze;

    private static final int NUM_MONSTERS = 6;
    private static final int NUM_NUMBERS = 3;
    private static final int NUM_OPERATORS = 2;

    private MonsterDataHolder m1 = new MonsterDataHolder(-0.003f,-0.03f,5);
    private MonsterDataHolder m2 = new MonsterDataHolder(0.003f,-0.03f,5);
    private MonsterDataHolder m3 = new MonsterDataHolder(-0.003f,-0.015f,5);
    private MonsterDataHolder m4 = new MonsterDataHolder(0.003f,-0.015f,5);
    private MonsterDataHolder m5 = new MonsterDataHolder(-0.003f,0f,5);
    private MonsterDataHolder m6 = new MonsterDataHolder(0.003f,0f,5);

    private NumberDataHolder n1 = new NumberDataHolder(0f,-0.03f,6);
    private NumberDataHolder n2 = new NumberDataHolder(0f,-0.01f,2);
    private NumberDataHolder n3 = new NumberDataHolder(0f,0.02f,1);

    private OperatorDataHolder o1 = new OperatorDataHolder(0f, -0.02f, "-");
    private OperatorDataHolder o2 = new OperatorDataHolder(0f, 0.01f, "+");

    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1, m2, m3, m4, m5, m6};

    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2,n3};

    private OperatorDataHolder operatorDataHolder[] = new OperatorDataHolder[] {o1, o2};

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
        return "Level 9";
    }

    @Override
    public String getDescription() {
        return "Mixed 1";
    }

    @Override
    public Badge getBadge() {
        return badge;
    }

    @Override
    public Integer getId() {
        return 9;
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
        return 7;
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

    private static final DataHolderLevel9 holder = new DataHolderLevel9();

    public static DataHolderLevel9 getInstance() {
        return holder;
    }

}
