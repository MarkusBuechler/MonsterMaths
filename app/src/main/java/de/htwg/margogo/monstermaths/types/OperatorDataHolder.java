package de.htwg.margogo.monstermaths.types;

public class OperatorDataHolder {

    float xPos;
    float yPos;
    String operation;


    public OperatorDataHolder(float xPos, float yPos, String operation) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.operation = operation;
    }

    // 1 is blueMonster
    public String getOperation() {
        return operation;
    }

    public float getXPos() {
        return xPos;
    }

    public float getYPos() {
        return yPos;
    }

}