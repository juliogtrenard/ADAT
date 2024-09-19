package ejercicios_julio;

public class Empleado {
	private String apellido;
    private double salario;

    public Empleado(String apellido, double salario) {
        this.apellido = apellido;
        this.salario = salario;
    }

    public String getApellido() {
        return apellido;
    }

    public double getSalario() {
        return salario;
    }

    public void sumarImporte(double importe) {
        this.salario += importe;
    }
}
