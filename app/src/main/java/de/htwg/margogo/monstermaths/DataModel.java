package de.htwg.margogo.monstermaths;


import java.util.List;

/**
 * Class representing a level.
 * What should every level have ?
 * - String: Name
 * - ID : int
 * - Boolean: Locked
 * - String Description maybe
 * - ? : Personal high score -> need to be saved somewhere
 * - ? : High score List -> List of other scores from different players
 * - ? : Badge : Bronze, Silver, Gold, Platin if Player is in top 30, 20, 10 or top 5% in the highscore list.
 * TODO: think
 */
public class DataModel {

    String name;
    Integer id;
    Boolean locked;
    String description;
    int personal_highscore;
    List<Double> highscore_list;
    Badge badge;


    public DataModel(String name, Integer id, Boolean locked, String description, Integer personal_highscore, Badge badge) {
        this.name = name;
        this.id = id;
        this.locked = locked;
        this.description = description;
        this.personal_highscore = personal_highscore;
        this.badge = badge;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getLocked() {
        return locked;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPersonal_highscore() {
        return personal_highscore;
    }

    public List<Double> getHighscore_list() {
        return highscore_list;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public void setPersonal_highscore(Integer personal_highscore) {
        this.personal_highscore = personal_highscore;
    }

    public void setHighscore_list(List<Double> highscore_list) {
        this.highscore_list = highscore_list;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }


}