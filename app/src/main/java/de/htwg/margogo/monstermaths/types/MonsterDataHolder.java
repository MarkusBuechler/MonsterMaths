package de.htwg.margogo.monstermaths.types;

public class MonsterDataHolder {

    float xPos;
    float yPos;
    int typ;


    public MonsterDataHolder(float xPos, float yPos, int typ) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.typ = typ;
    }

    // 1 is blueMonster
    public int getTyp() {
        return typ;
    }

    public float getXPos() {
        return xPos;
    }

    public float getYPos() {
        return yPos;
    }

}