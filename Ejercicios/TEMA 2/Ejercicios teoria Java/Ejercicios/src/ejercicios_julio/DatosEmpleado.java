package ejercicios_julio;

import java.util.HashMap;
import java.util.Map;

public class DatosEmpleado {
	public static void main(String[] args) {
        String idEmpleado = args[0];

        Map<String, String> empleados = new HashMap<>();
        empleados.put("01", "Empleado 1 / Ventas /  Salario: 1500");
        empleados.put("02", "Empleado 2 / Marketing / Salario: 2000");
        empleados.put("03", "Empleado 3 / Contabilidad / Salario: 1200");
        empleados.put("04", "Empleado 4 / CEO / Salario: 4200");
        empleados.put("05", "Empleado 5 / Mantenimiento / Salario: 1200");

        String datosEmpleado = empleados.get(idEmpleado);

        if (datosEmpleado != null) {
            System.out.println("Datos del empleado: " + datosEmpleado);
        } else {
            System.out.println("El empleado no existe.");
        }
    }
}
