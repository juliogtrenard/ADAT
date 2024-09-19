package ejercicios_julio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VisualizarContenido {
	public static void main(String[] args) {
        String nombre = args[0];

        try (BufferedReader br = new BufferedReader(new FileReader(nombre))) {
            String linea = br.readLine();
            
            while (linea != null) {
                System.out.println(linea);
                
                linea = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error de E/S de datos: " + e.getMessage());
        }
    }
}
