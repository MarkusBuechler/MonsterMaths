package de.htwg.margogo.monstermaths.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Highscore implements Serializable {

    public Highscore(int level, int value) {
        this.level = level;
        this.value = value;
    }

    @PrimaryKey(autoGenerate = true)
    private int hid;

    @ColumnInfo(name = "level")
    private int level;

    @ColumnInfo(name = "value")
    private int value;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }


}