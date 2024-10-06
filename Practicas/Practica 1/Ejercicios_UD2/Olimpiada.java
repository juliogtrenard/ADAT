package adat;

import java.io.Serializable;

public class Olimpiada implements Serializable {
    private String games;
    private int year;
    private String season;
    private String city;

    public Olimpiada(String games, int year, String season, String city) {
        this.games = games;
        this.year = year;
        this.season = season;
        this.city = city;
    }

    @Override
    public String toString() {
        return games + "," + year + "," + season + "," + city;
    }
    
    public String getGames() {
        return games;
    }

    public int getYear() {
        return year;
    }

    public String getSeason() {
        return season;
    }

    public String getCity() {
        return city;
    }
}
