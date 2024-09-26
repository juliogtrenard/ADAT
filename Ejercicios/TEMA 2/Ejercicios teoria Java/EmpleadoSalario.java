package ejercicios_julio;

import java.util.HashMap;

public class EmpleadoSalario {
	public static void main(String[] args) {
        String idEmpleado = args[0];
        double importe = Double.parseDouble(args[1]);

        HashMap<String, Empleado> empleados = new HashMap<>();
        empleados.put("01", new Empleado("Empleado 1", 1500.00));
        empleados.put("02", new Empleado("Empleado 2", 2000.00));
        empleados.put("03", new Empleado("Empleado 3", 1200.00));
        empleados.put("04", new Empleado("Empleado 4", 4200.00));
        empleados.put("05", new Empleado("Empleado 5", 1200.00));

        Empleado empleado = empleados.get(idEmpleado);

        if (empleado != null) {
            double salarioAntiguo = empleado.getSalario();
            empleado.sumarImporte(importe);
            double nuevoSalario = empleado.getSalario();

            System.out.println("Apellido: " + empleado.getApellido() + '\n' + 
            		"Salario antiguo: " + salarioAntiguo + '\n' + "Nuevo salario: " 
            		+ nuevoSalario);
        } else {
            System.out.println("El empleado no existe.");
        }
    }
}
