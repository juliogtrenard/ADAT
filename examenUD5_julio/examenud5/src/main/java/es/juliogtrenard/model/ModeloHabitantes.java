package es.juliogtrenard.model;

public class ModeloHabitantes {
    private int identificador;
    private String nombre;
    private int edad;
    private boolean esElegido;

    public ModeloHabitantes() {
    }

    public ModeloHabitantes(int identificador, String nombre, int edad, boolean esElegido) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.edad = edad;
        this.esElegido = esElegido;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isEsElegido() {
        return esElegido;
    }

    public void setEsElegido(boolean esElegido) {
        this.esElegido = esElegido;
    }

    @Override
    public String toString() {
        return "ModeloHabitantes{" +
                "identificador=" + identificador +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}
