package de.htwg.margogo.monstermaths.levels;

import de.htwg.margogo.monstermaths.Badge;
import de.htwg.margogo.monstermaths.DataHolderInterface;
import de.htwg.margogo.monstermaths.MonsterDataHolder;
import de.htwg.margogo.monstermaths.NumberDataHolder;

/*
 * Singleton class. Dataholder for Level 2
 */
public class DataHolderLevel2 implements DataHolderInterface {

    private boolean lock;
    private int score;
    private Badge badge = Badge.Bronze; // need dafault type

    private static final int NUM_MONSTERS = 2;
    private static final int NUM_NUMBERS = 3;

    private MonsterDataHolder m1 = new MonsterDataHolder(0.0275f,-0.03f,1);
    private MonsterDataHolder m2 = new MonsterDataHolder(0.0200f,-0.01f,1);

    private NumberDataHolder n1 = new NumberDataHolder(0f,-0.015f,5);
    private NumberDataHolder n2 = new NumberDataHolder(0.02f,0.0065f,5);
    private NumberDataHolder n3 = new NumberDataHolder(0.f,0.025f,2);


    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1,m2};

    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2,n3};

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
        return "Level 2";
    }

    @Override
    public String getDescription() {
        return "Introduction to Addition";
    }

    @Override
    public Badge getBadge() {
        return badge;
    }

    @Override
    public Integer getId() {
        return 2;
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
        return 10;
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

    private static final DataHolderLevel2 holder = new DataHolderLevel2();

    public static DataHolderLevel2 getInstance() {
        return holder;
    }
}
