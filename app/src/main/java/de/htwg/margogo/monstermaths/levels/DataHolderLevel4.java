package de.htwg.margogo.monstermaths.levels;

import de.htwg.margogo.monstermaths.Badge;
import de.htwg.margogo.monstermaths.DataHolderInterface;
import de.htwg.margogo.monstermaths.MonsterDataHolder;
import de.htwg.margogo.monstermaths.NumberDataHolder;

/*
 * Singleton class. Dataholder for Level 4
 */
public class DataHolderLevel4 implements DataHolderInterface {

    private boolean lock;
    private int score;
    private Badge badge = Badge.Bronze;

    private static final int NUM_MONSTERS = 3;
    private static final int NUM_NUMBERS = 4;

    private MonsterDataHolder m1 = new MonsterDataHolder(-0.005f,0.03f,2);
    private MonsterDataHolder m2 = new MonsterDataHolder(-0.025f,-0.03f,3);
    private MonsterDataHolder m3 = new MonsterDataHolder(0.025f,-0.03f,4);

    private NumberDataHolder n1 = new NumberDataHolder(0f,0f,9);
    private NumberDataHolder n2 = new NumberDataHolder(0.025f,-0.025f,8);
    private NumberDataHolder n3 = new NumberDataHolder(0.f,0.02f,7);
    private NumberDataHolder n4 = new NumberDataHolder(-0.025f,-0.025f,8);

    private MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1, m2, m3};

    private NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2,n3, n4};

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
        return "Level 4";
    }

    @Override
    public String getDescription() {
        return "Addition 2";
    }

    @Override
    public Badge getBadge() {
        return badge;
    }

    @Override
    public Integer getId() {
        return 4;
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
        return 24;
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

    private static final DataHolderLevel4 holder = new DataHolderLevel4();

    public static DataHolderLevel4 getInstance() {
        return holder;
    }

}
