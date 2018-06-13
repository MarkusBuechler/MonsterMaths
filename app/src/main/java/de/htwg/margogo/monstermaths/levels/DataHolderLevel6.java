package de.htwg.margogo.monstermaths.levels;

import java.util.ArrayList;
import java.util.List;

import de.htwg.margogo.monstermaths.Badge;
import de.htwg.margogo.monstermaths.types.MonsterDataHolder;
import de.htwg.margogo.monstermaths.types.NumberDataHolder;
import de.htwg.margogo.monstermaths.types.OperatorDataHolder;

/*
 * Singleton class. Dataholder for Level 6
 */
public class DataHolderLevel6 extends DataHolderPrototype implements DataHolderInterface {

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

    @Override
    public String getName() {
        return "Level 6";
    }

    @Override
    public String getDescription() {
        return "Introduction to Subtraction";
    }

    @Override
    public Integer getId() {
        return 6;
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
    public Integer getNumMonsters() {
        return monsterDataHolder.length;
    }

    @Override
    public Integer getNumNumbers() {
        return numberDataHolder.length;
    }

    @Override
    public Integer getNumOperators() {
        return operatorDataHolder.length;
    }

    private static final DataHolderLevel6 holder = new DataHolderLevel6();

    public static DataHolderLevel6 getInstance() {
        return holder;
    }

}
