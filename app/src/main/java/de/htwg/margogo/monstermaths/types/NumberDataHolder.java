package de.htwg.margogo.monstermaths.types;

public class NumberDataHolder {

    float xPos;
    float yPos;
    int value;


    public NumberDataHolder(float xPos, float yPos, int value) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public float getXPos() {
        return xPos;
    }

    public float getYPos() {
        return yPos;
    }

}