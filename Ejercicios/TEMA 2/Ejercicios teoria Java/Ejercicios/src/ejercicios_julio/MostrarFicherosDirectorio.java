package ejercicios_julio;

import java.io.File;

public class MostrarFicherosDirectorio {
	public static void main(String[] args) {
        String nombre = args[0];

        File directorio = new File(nombre);

        if(!directorio.exists() || !directorio.isDirectory()) {
            System.out.println("ERROR. No existe o no es un directorio.");
            return;
        }

        String[] ficheros = directorio.list();

        if(ficheros != null && ficheros.length > 0) {
            System.out.println("Archivos en el directorio " + nombre + ":");
            for(String fichero : ficheros) {
                System.out.println(fichero);
            }
        } else {
            System.out.println("El directorio está vacío.");
        }
    }
}
