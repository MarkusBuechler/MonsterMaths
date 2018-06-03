package de.htwg.margogo.monstermaths;

public class NumberDataHolder {

    float xPos;
    float yPos;
    int value;


    public NumberDataHolder(float xPos, float yPos, int value) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.value = value;
    }

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public int getValue() {
        return value;
    }

}