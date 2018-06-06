package de.htwg.margogo.monstermaths;

/*
 * Singleton class. Dataholder for Level 1
 */
public class DataHolderLevel1 implements DataHolderInterface {

    private boolean lock;
    private int score;
    private Badge badge = Badge.Bronze;

    private static final int NUM_MONSTERS = 1;
    private static final int NUM_NUMBERS = 2;

    private MonsterDataHolder m1 = new MonsterDataHolder(0.0275f,-0.03f,1);

    private NumberDataHolder n1 = new NumberDataHolder(0f,-0.015f,1);
    private NumberDataHolder n2 = new NumberDataHolder(0.02f,0.0065f,1);

    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1};

    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2};

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
        return "Level 1";
    }

    @Override
    public String getDescription() {
        return "Basic Introduction to the game concept";
    }

    @Override
    public Badge getBadge() {
        return badge;
    }

    @Override
    public Integer getId() {
        return 1;
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
    public Integer getExpectedResult() {
        return 2;
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
    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    private static final DataHolderLevel1 holder = new DataHolderLevel1();

    public static DataHolderLevel1 getInstance() {
        return holder;
    }

}
