package de.htwg.margogo.monstermaths.levels;

import de.htwg.margogo.monstermaths.Badge;
import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

/*
 * Singleton class. Dataholder for Level 17
 */
public class DataHolderLevel17 implements DataHolderInterface {

    private boolean lock;
    private int score;
    private Badge badge = Badge.Bronze; // need dafault type

    private static final int NUM_MONSTERS = 4;
    private static final int NUM_NUMBERS = 4;
    private static final int NUM_OPERATORS = 1;

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
        return "Level 17";
    }

    @Override
    public String getDescription() {
        return "Mixed 4";
    }

    @Override
    public Badge getBadge() {
        return badge;
    }

    @Override
    public Integer getId() {
        return 17;
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
    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    private static final DataHolderLevel17 holder = new DataHolderLevel17();

    public static DataHolderLevel17 getInstance() {
        return holder;
    }
}
