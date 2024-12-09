package es.juliogtrenard.model;

import java.util.ArrayList;

public class ModeloDistritos {
    private int identificador;
    private String nombre;
    private ArrayList<ModeloHabitantes> habitantes = new ArrayList<>();

    public ModeloDistritos(int identificador, String nombre) {
        this.identificador = identificador;
        this.nombre = nombre;
    }

    public ModeloDistritos(int identificador, String nombre, ArrayList<ModeloHabitantes> habitantes) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.habitantes = habitantes;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<ModeloHabitantes> getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(ArrayList<ModeloHabitantes> habitantes) {
        this.habitantes = habitantes;
    }

    @Override
    public String toString() {
        return "ModeloDistritos{" +
                "identificador=" + identificador +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
