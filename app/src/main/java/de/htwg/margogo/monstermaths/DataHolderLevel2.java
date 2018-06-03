package de.htwg.margogo.monstermaths;

import android.util.Log;

/*
 * Singleton class. Dataholder for Level 2
 */
public class DataHolderLevel2 implements DataHolderInterface {

    private boolean lock;
    private int score;
    private String name = "Level 2";
    private String descrption = "Introduction to Addition";
    private Badge badge = Badge.Bronze; // need dafault type
    private int id = 2;

    static final int NUM_MONSTERS = 1;
    static final int NUM_NUMBERS = 2;

    MonsterDataHolder m1 = new MonsterDataHolder(0.0275f,-0.03f,1);

    NumberDataHolder n1 = new NumberDataHolder(0f,-0.015f,1);
    NumberDataHolder n2 = new NumberDataHolder(0.02f,0.0065f,2);


    public MonsterDataHolder monsterDataHolder[] = new MonsterDataHolder[] {m1};;

    public NumberDataHolder numberDataHolder[] = new NumberDataHolder[] {n1,n2};

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public boolean getLock() {
        return lock;
    }

    public void setScore(int score) {
        this.score = score;
        Log.i("level2!", "setScore: called");
    }

    public int getScore() {
        return score;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return descrption;
    }

    @Override
    public Badge getBadge() {
        return badge;
    }

    @Override
    public Integer getId() {
        return id;
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