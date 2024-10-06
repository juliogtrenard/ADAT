package adat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import faciles.Consola;

public class GestionarFicheros {
	
    public static void main(String[] args) {
    	GestionarFicheros gestor = new GestionarFicheros();
        gestor.start();
    }

    public void start() {
        int opcion;
        do {
            // Mostrar menú
            System.out.println("Seleccione una opción:");
            System.out.println("1. Crear un directorio");
            System.out.println("2. Listar un directorio");
            System.out.println("3. Copiar un archivo");
            System.out.println("4. Mover un archivo");
            System.out.println("5. Eliminar un archivo/directorio");
            System.out.println("6. Salir");
            
            opcion = Consola.leeInt();

            switch (opcion) {
                case 1:
                    crearDirectorio();
                    break;
                case 2:
                    listarDirectorio();
                    break;
                case 3:
                    copiarArchivo();
                    break;
                case 4:
                    moverArchivo();
                    break;
                case 5:
                    eliminarArchivoDirectorio();
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 6);
    }

    private void crearDirectorio() {
        System.out.print("Ingrese la ruta donde se creará el nuevo directorio: ");
        String ruta = Consola.leeString();
        
        System.out.print("Ingrese el nombre del nuevo directorio: ");
        String nuevaRuta = Consola.leeString();

        File nuevoDir = new File(ruta, nuevaRuta);
        
        if(nuevoDir.mkdirs()) {
            System.out.println("Directorio creado con éxito: " + nuevoDir.getAbsolutePath());
        } else {
            System.out.println("No se pudo crear el directorio.");
        }
    }

    private void listarDirectorio() {
        System.out.print("Ingrese la ruta del directorio a listar: ");
        String ruta = Consola.leeString();
        
        File dir = new File(ruta);

        if(dir.isDirectory()) {
            File[] archivos = dir.listFiles();
            
            if(archivos != null) {
                for(File archivo : archivos) {
                    if(archivo.isDirectory()) {
                        System.out.println("DIRECTORIO: " + archivo.getName());
                    } else {
                        System.out.println("ARCHIVO: " + archivo.getName());
                    }
                }
            } else {
                System.out.println("El directorio está vacío.");
            }
        } else {
            System.out.println("No es un directorio válido.");
        }
    }

    private void copiarArchivo() {
        System.out.print("Ingrese la ruta del archivo a copiar: ");
        String rutaFuente = Consola.leeString();
        
        System.out.print("Ingrese la ruta del directorio de destino: ");
        String rutaDestino = Consola.leeString();

        Path fuente = Path.of(rutaFuente);
        Path destino = Path.of(rutaDestino, fuente.getFileName().toString());

        try {
            Files.copy(fuente, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado con éxito a: " + destino);
        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
        }
    }

    private void moverArchivo() {
        System.out.print("Ingrese la ruta del archivo a mover: ");
        String rutaFuente = Consola.leeString();
        
        System.out.print("Ingrese la ruta del directorio de destino: ");
        String rutaDestino = Consola.leeString();

        Path fuente = Path.of(rutaFuente);
        Path destino = Path.of(rutaDestino, fuente.getFileName().toString());

        try {
            Files.move(fuente, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo movido con éxito a: " + destino);
        } catch (IOException e) {
            System.out.println("Error al mover el archivo: " + e.getMessage());
        }
    }

    private void eliminarArchivoDirectorio() {
        System.out.print("Ingrese la ruta del archivo o directorio a eliminar: ");
        String ruta = Consola.leeString();
        
        File fichero = new File(ruta);

        if (fichero.exists()) {
            if (fichero.isDirectory()) {
                String[] archivos = fichero.list();
                if (archivos != null && archivos.length == 0) {
                    if (fichero.delete()) {
                        System.out.println("Directorio eliminado con éxito.");
                    } else {
                        System.out.println("No se pudo eliminar el directorio.");
                    }
                } else {
                    System.out.println("El directorio no está vacío y no se puede eliminar.");
                }
            } else {
                if (fichero.delete()) {
                    System.out.println("Archivo eliminado con éxito.");
                } else {
                    System.out.println("No se pudo eliminar el archivo.");
                }
            }
        } else {
            System.out.println("La ruta ingresada no existe.");
        }
    }
}
