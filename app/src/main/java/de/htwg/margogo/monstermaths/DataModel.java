package de.htwg.margogo.monstermaths;


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
    String type;
    String version_number;
    String feature;

    public DataModel(String name, String type, String version_number, String feature ) {
        this.name=name;
        this.type=type;
        this.version_number=version_number;
        this.feature=feature;

    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getVersion_number() {
        return version_number;
    }

    public String getFeature() {
        return feature;
    }

}