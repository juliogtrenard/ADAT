package adat;

import java.util.ArrayList;
import java.util.List;

public class Deportista {
    private int id;
    private String name;
    private String sex;
    private int age;
    private double height;
    private double weight;
    private String team;
    private String noc;
    private String games;
    private int year;
    private String season;
    private String city;
    private String sport;
    private String event;
    private String medal;
    private List<Participacion> participaciones;

    public Deportista(int id, String name, String sex, int age, double height, double weight, String team, 
                      String noc, String games, int year, String season, String city, 
                      String sport, String event, String medal) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.team = team;
        this.noc = noc;
        this.games = games;
        this.year = year;
        this.season = season;
        this.city = city;
        this.sport = sport;
        this.event = event;
        this.medal = medal;
        this.participaciones = new ArrayList<>();
    }
    
    public List<Participacion> getParticipaciones() {
        return participaciones;
    }

    public void addParticipacion(Participacion participacion) {
        this.participaciones.add(participacion);
    }

    @Override
    public String toString() {
        return id + "," + name + "," + sex + "," + age + "," + height + "," + weight + "," + team + "," + noc + ","
                + games + "," + year + "," + season + "," + city + "," + sport + "," + event + "," + medal;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getNoc() {
		return noc;
	}

	public void setNoc(String noc) {
		this.noc = noc;
	}

	public String getGames() {
		return games;
	}

	public void setGames(String games) {
		this.games = games;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getMedal() {
		return medal;
	}

	public void setMedal(String medal) {
		this.medal = medal;
	}
}
