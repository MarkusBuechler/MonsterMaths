package de.htwg.margogo.monstermaths.levels;

public class BadgeCheck {

    int gold;
    int silver;
    int bronze;


    public BadgeCheck (int gold, int silver, int bronze) {
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
    }

    // 1 is blueMonster
    public int getGold() {
        return gold;
    }

    public float getSilver() {
        return silver;
    }

    public float getBronze() {
        return bronze;
    }
}
