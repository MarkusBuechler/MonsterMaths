package de.htwg.margogo.monstermaths;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Highscore.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HighscoreDao highscoreDao();
}

