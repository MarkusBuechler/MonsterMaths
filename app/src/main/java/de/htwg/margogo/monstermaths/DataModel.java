package de.htwg.margogo.monstermaths;

import de.htwg.margogo.monstermaths.levels.BadgeCheck;

public class DataModel {

    private String name;
    protected Integer id;
    private Boolean locked;
    private String description;
    private int personal_highscore;
    private BadgeCheck badgeCheck;


    protected DataModel(String name, Integer id, Boolean locked, String description, Integer personal_highscore, BadgeCheck badgeCheck) {
        this.name = name;
        this.id = id;
        this.locked = locked;
        this.description = description;
        this.personal_highscore = personal_highscore;
        this.badgeCheck = badgeCheck;
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

    public BadgeCheck getBadgeCheck() {
        return badgeCheck;
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

}