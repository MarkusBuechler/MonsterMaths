package de.htwg.margogo.monstermaths;

public class NumberDataHolder {

    float xPos;
    float yPos;
    int value;


    NumberDataHolder(float xPos, float yPos, int value) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}