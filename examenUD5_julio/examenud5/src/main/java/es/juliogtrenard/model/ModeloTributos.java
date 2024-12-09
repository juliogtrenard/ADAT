package es.juliogtrenard.model;

public class ModeloTributos {
    private ModeloHabitantes habitante;
    private int puntaje;
    private String habilidades;

    public ModeloTributos() {}

    public ModeloTributos(ModeloHabitantes habitante, int puntaje, String habilidades) {
        this.habitante = habitante;
        this.puntaje = puntaje;
        this.habilidades = habilidades;
    }

    public ModeloHabitantes getHabitante() {
        return habitante;
    }

    public void setHabitante(ModeloHabitantes habitante) {
        this.habitante = habitante;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    @Override
    public String toString() {
        return "ModeloTributos{" +
                "habitante=" + habitante +
                ", puntaje=" + puntaje +
                ", habilidades='" + habilidades + '\'' +
                '}';
    }

    @Override
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModeloTributos other = (ModeloTributos) obj;
        if (habitante == null) {
            if (other.habitante != null)
                return false;
        } else if (!habitante.equals(other.habitante))
            return false;
        return true;
    }
}
