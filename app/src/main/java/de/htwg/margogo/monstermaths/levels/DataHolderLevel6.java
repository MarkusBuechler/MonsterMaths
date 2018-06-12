package de.htwg.margogo.monstermaths.levels;

import de.htwg.margogo.monstermaths.Badge;
import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

/*
 * Singleton class. Dataholder for Level 6
 */
public class DataHolderLevel6 implements DataHolderInterface {

    private boolean lock;
    private int score;
    private Badge badge = Badge.Bronze;

    private static final int NUM_MONSTERS = 3;
    private static final int NUM_NUMBERS = 3;
    private static final int NUM_OPERATORS = 1;

    private MonsterDataHolder m1 = new MonsterDataHolder(-0.005f,0.03f,2);
    private MonsterDataHolder m2 = new MonsterDataHolder(-0.025f,-0.03f,3);
    private MonsterDataHolder m3 = new MonsterDataHolder(0.025f,-0.03f,4);

    private NumberDataHolder n1 = new NumberDataHolder(0f,-0.03f,9);
    private NumberDataHolder n2 = new NumberDataHolder(0f,0f,3);
    private NumberDataHolder n3 = new NumberDataHolder(0f,0.02f,3);

    private OperatorDataHolder o1 = new OperatorDataHolder(0f, -0.01f, "-");

    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1, m2, m3};

    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2,n3};

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
        return "Level 6";
    }

    @Override
    public String getDescription() {
        return "Introduction to Subtraction";
    }

    @Override
    public Badge getBadge() {
        return badge;
    }

    @Override
    public Integer getId() {
        return 6;
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

    private static final DataHolderLevel6 holder = new DataHolderLevel6();

    public static DataHolderLevel6 getInstance() {
        return holder;
    }

}
