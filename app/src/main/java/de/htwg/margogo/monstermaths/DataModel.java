package de.htwg.margogo.monstermaths;

/*
 * TODO: Badge : Bronze, Silver, Gold, Platin if Player is in below certain limits in the highscore list.
 */
public class DataModel {

    private String name;
    protected Integer id;
    private Boolean locked;
    private String description;
    private int personal_highscore;
    private Badge badge;


    protected DataModel(String name, Integer id, Boolean locked, String description, Integer personal_highscore, Badge badge) {
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

    public void setBadge(Badge badge) {
        this.badge = badge;
    }


}