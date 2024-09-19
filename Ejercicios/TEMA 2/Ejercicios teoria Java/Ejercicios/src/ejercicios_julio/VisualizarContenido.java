package ejercicios_julio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VisualizarContenido {
	public static void main(String[] args) {
        String nombre = args[0];

        try (BufferedReader br = new BufferedReader(new FileReader(nombre))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error de E/S de datos: " + e.getMessage());
        }
    }
}
