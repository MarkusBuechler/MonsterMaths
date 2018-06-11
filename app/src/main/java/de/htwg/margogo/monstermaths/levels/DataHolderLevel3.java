package de.htwg.margogo.monstermaths.levels;

import de.htwg.margogo.monstermaths.Badge;
import de.htwg.margogo.monstermaths.DataHolderInterface;
import de.htwg.margogo.monstermaths.MonsterDataHolder;
import de.htwg.margogo.monstermaths.NumberDataHolder;

/*
 * Singleton class. Dataholder for Level 3
 */
public class DataHolderLevel3 implements DataHolderInterface {

    private boolean lock;
    private int score;
    private Badge badge = Badge.Bronze; // need dafault type

    private static final int NUM_MONSTERS = 4;
    private static final int NUM_NUMBERS = 7;

    private MonsterDataHolder m1 = new MonsterDataHolder(0.0275f,-0.03f,1);
    private MonsterDataHolder m2 = new MonsterDataHolder(0.0200f,-0.01f,1);
    private MonsterDataHolder m3 = new MonsterDataHolder(0.0235f,0.01f,1);
    private MonsterDataHolder m4 = new MonsterDataHolder(0.0235f,0.03f,1);

    private NumberDataHolder n1 = new NumberDataHolder(0f,-0.020f,3);
    private NumberDataHolder n2 = new NumberDataHolder(0.020f,-0.020f,3);
    private NumberDataHolder n3 = new NumberDataHolder(-0.020f,-0.020f,3);
    private NumberDataHolder n4 = new NumberDataHolder(0.02f,0.020f,4);
    private NumberDataHolder n5 = new NumberDataHolder(-0.02f,0.020f,4);
    private NumberDataHolder n6 = new NumberDataHolder(0.02f,0f,1);
    private NumberDataHolder n7 = new NumberDataHolder(-0.02f,0f,1);


    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1,m2, m3, m4};

    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2,n3,n4, n5, n6, n7};

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
        return "Level 3";
    }

    @Override
    public String getDescription() {
        return "Addition 1";
    }

    @Override
    public Badge getBadge() {
        return badge;
    }

    @Override
    public Integer getId() {
        return 3;
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
        return 17;
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

    private static final DataHolderLevel3 holder = new DataHolderLevel3();

    public static DataHolderLevel3 getInstance() {
        return holder;
    }
}