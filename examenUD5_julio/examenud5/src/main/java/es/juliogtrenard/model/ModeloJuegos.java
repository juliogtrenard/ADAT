package es.juliogtrenard.model;

import java.util.ArrayList;

public class ModeloJuegos {
    private int identificador;
    private int anio;
    private ArrayList<ModeloTributos> tributos = new ArrayList<>();
    private ModeloTributos ganador;

    public ModeloJuegos(int identificador, int anio) {
        this.identificador = identificador;
        this.anio = anio;
    }

    public ModeloJuegos(int anio, ArrayList<ModeloTributos> tributos, ModeloTributos ganador) {
        this.anio = anio;
        this.tributos = tributos;
        this.ganador = ganador;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public ArrayList<ModeloTributos> getTributos() {
        return tributos;
    }

    public void setTributos(ArrayList<ModeloTributos> tributos) {
        this.tributos = tributos;
    }

    public ModeloTributos getGanador() {
        return ganador;
    }

    public void setGanador(ModeloTributos ganador) {
        this.ganador = ganador;
    }

    @Override
    public String toString() {
        return "ModeloJuegos{" +
                "anio=" + anio +
                ", tributos=" + tributos +
                ", ganador=" + ganador +
                '}';
    }
}
