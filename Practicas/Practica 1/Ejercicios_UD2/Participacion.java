package adat;

public class Participacion {
    private String sport;
    private int age;
    private String noc;
    private String team;
    private String games;
    private String event;
    private String medal;

    public Participacion(String sport, int age, String noc, String team, String games, String event, String medal) {
        this.sport = sport;
        this.age = age;
        this.noc = noc;
        this.team = team;
        this.games = games;
        this.event = event;
        this.medal = medal;
    }

    public String getSport() {
        return sport;
    }

    public int getAge() {
        return age;
    }

    public String getNoc() {
        return noc;
    }

    public String getTeam() {
        return team;
    }

    public String getGames() {
        return games;
    }

    public String getEvent() {
        return event;
    }

    public String getMedal() {
        return medal;
    }
}
