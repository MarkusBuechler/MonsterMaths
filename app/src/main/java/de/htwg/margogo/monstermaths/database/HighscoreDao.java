package de.htwg.margogo.monstermaths.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface HighscoreDao {
    @Query("SELECT * FROM Highscore WHERE level = :x")
    List<Highscore> getScoresLevelX(int x);

    @Query("SELECT value FROM Highscore WHERE level = :x order by value asc limit 1")
    int getHighscoreLevelX(int x);

    @Insert
    void insertHighscore(Highscore highscore);

    @Delete
    void delete(Highscore highscore);
}