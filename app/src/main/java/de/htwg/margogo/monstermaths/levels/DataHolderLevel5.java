package de.htwg.margogo.monstermaths.levels;

import de.htwg.margogo.monstermaths.Badge;
import de.htwg.margogo.monstermaths.DataHolderInterface;
import de.htwg.margogo.monstermaths.MonsterDataHolder;
import de.htwg.margogo.monstermaths.NumberDataHolder;

/*
 * Singleton class. Dataholder for Level 4
 */
public class DataHolderLevel5 implements DataHolderInterface {

    private boolean lock;
    private int score;
    private Badge badge = Badge.Bronze;

    private static final int NUM_MONSTERS = 3;
    private static final int NUM_NUMBERS = 8;

    private MonsterDataHolder m1 = new MonsterDataHolder(-0.005f,0.03f,2);
    private MonsterDataHolder m2 = new MonsterDataHolder(-0.025f,-0.03f,3);
    private MonsterDataHolder m3 = new MonsterDataHolder(0.025f,-0.03f,4);

    private NumberDataHolder n1 = new NumberDataHolder(0f,-0.04f,5);
    private NumberDataHolder n2 = new NumberDataHolder(0f,-0.03f,4);
    private NumberDataHolder n3 = new NumberDataHolder(0f,-0.02f,3);
    private NumberDataHolder n4 = new NumberDataHolder(0f,-0.01f,2);
    private NumberDataHolder n5 = new NumberDataHolder(0f,0f,1);
    private NumberDataHolder n6 = new NumberDataHolder(0f,0.01f,0);
    private NumberDataHolder n7 = new NumberDataHolder(0f,0.02f,1);
    private NumberDataHolder n8 = new NumberDataHolder(0f,0.03f,2);

    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1, m2, m3};

    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2,n3, n4, n5, n6, n7, n8};

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
        return "Level 5";
    }

    @Override
    public String getDescription() {
        return "Addition 3";
    }

    @Override
    public Badge getBadge() {
        return badge;
    }

    @Override
    public Integer getId() {
        return 5;
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
        return 15;
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

    private static final DataHolderLevel5 holder = new DataHolderLevel5();

    public static DataHolderLevel5 getInstance() {
        return holder;
    }

}
