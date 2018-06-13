package de.htwg.margogo.monstermaths.levels;

import java.util.ArrayList;
import java.util.List;

import de.htwg.margogo.monstermaths.Badge;
import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

/*
 * Singleton class. Dataholder for Level 10
 */
public class DataHolderLevel10 implements DataHolderInterface {

    private boolean lock;
    private int score;
    private List scoreList = new ArrayList<Integer>();
    private Badge badge = Badge.Bronze;

    private static final int NUM_MONSTERS = 6;
    private static final int NUM_NUMBERS = 4;
    private static final int NUM_OPERATORS = 2;

    private MonsterDataHolder m1 = new MonsterDataHolder(-0.003f,-0.03f,5);
    private MonsterDataHolder m2 = new MonsterDataHolder(0.003f,-0.03f,5);
    private MonsterDataHolder m3 = new MonsterDataHolder(-0.003f,-0.015f,5);
    private MonsterDataHolder m4 = new MonsterDataHolder(0.003f,-0.015f,5);
    private MonsterDataHolder m5 = new MonsterDataHolder(-0.003f,0f,5);
    private MonsterDataHolder m6 = new MonsterDataHolder(0.003f,0f,5);

    private NumberDataHolder n1 = new NumberDataHolder(-0.028f,0.045f,9);
    private NumberDataHolder n2 = new NumberDataHolder(0.028f,0.045f,9);
    private NumberDataHolder n3 = new NumberDataHolder(-0.028f,-0.045f,1);
    private NumberDataHolder n4 = new NumberDataHolder(0.028f,-0.045f,5);

    private OperatorDataHolder o1 = new OperatorDataHolder(0f, 0f, "+");
    private OperatorDataHolder o2 = new OperatorDataHolder(0f, -0.01f, "-");

    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1, m2, m3, m4, m5, m6};

    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2,n3, n4};

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
        return "Level 10";
    }

    @Override
    public String getDescription() {
        return "Mixed 2";
    }

    @Override
    public Badge getBadge() {
        return badge;
    }

    @Override
    public Integer getId() {
        return 10;
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
        return 14;
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

    private static final DataHolderLevel10 holder = new DataHolderLevel10();

    public static DataHolderLevel10 getInstance() {
        return holder;
    }

}
