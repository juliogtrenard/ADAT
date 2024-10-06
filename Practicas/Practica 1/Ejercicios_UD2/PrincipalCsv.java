package adat;

import java.util.*;
import faciles.Consola;

public class PrincipalCsv {

    public static void main(String[] args) throws Exception {
        List<Deportista> deportistas = LectorCsv.leerDeportistas("athlete_events.csv");
        boolean salir = false;

        while (!salir) {
            System.out.println("\nMenú:");
            System.out.println("1. Generar fichero CSV de olimpiadas");
            System.out.println("2. Buscar deportista");
            System.out.println("3. Buscar deportistas por deporte y olimpiada");
            System.out.println("4. Añadir deportista");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            int opcion = Consola.leeInt();

            switch (opcion) {
                case 1:
                    List<Olimpiada> olimpiadas = LectorCsv.generarOlimpiadas(deportistas);
                    LectorCsv.escribirOlimpiadasCSV(olimpiadas, "olimpiadas.csv");
                    System.out.println("Archivo olimpiadas.csv generado.");
                    break;
                case 2:
                    System.out.print("Introduce el nombre del deportista: ");
                    String nombre = Consola.leeString();
                    buscarDeportista(deportistas, nombre);
                    break;
                case 3:
                    System.out.print("Introduce el deporte: ");
                    String deporte = Consola.leeString();
                    
                    System.out.print("Introduce el año: ");
                    int año = Consola.leeInt();
                    
                    System.out.print("Introduce la temporada (Summer/Winter): ");
                    String temporada = Consola.leeString();
                    
                    buscarPorDeporteYOlimpiada(deportistas, deporte, año, temporada);
                    break;
                case 4:
                    añadirDeportista(deportistas);
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void buscarDeportista(List<Deportista> deportistas, String nombre) {
        boolean encontrado = false;
        for (Deportista d : deportistas) {
            if (d.getName().toLowerCase().contains(nombre.toLowerCase())) {
                System.out.println(d);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró a ningún deportista con ese nombre.");
        }
    }

    public static void buscarPorDeporteYOlimpiada(List<Deportista> deportistas, String deporte, int año, String temporada) {
        boolean encontrado = false;
        System.out.println("Detalles de la edición Olímpica:");
        for (Deportista d : deportistas) {
            if (d.getSport().equalsIgnoreCase(deporte) && d.getYear() == año && d.getSeason().equalsIgnoreCase(temporada)) {
                if (!encontrado) {
                    System.out.println("Juegos: " + d.getGames() + " | Ciudad: " + d.getCity());
                    System.out.println("Deporte: " + deporte);
                    System.out.println("Deportistas:");
                    encontrado = true;
                }
                System.out.println(d.getName() + " | Evento: " + d.getEvent() + " | Medalla: " + d.getMedal());
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron deportistas para este deporte y olimpiada.");
        }
    }

    public static void añadirDeportista(List<Deportista> deportistas) {
        System.out.println("Añadir deportista: ");
        
        System.out.print("ID: ");
        int id = Consola.leeInt();
        
        System.out.print("Nombre: ");
        String nombre = Consola.leeString();
        
        System.out.print("Sexo: ");
        String sexo = Consola.leeString();
        
        System.out.print("Edad: ");
        int edad = Consola.leeInt();
        
        System.out.print("Altura: ");
        double altura = Consola.leeDouble();
        
        System.out.print("Peso: ");
        double peso = Consola.leeDouble();
        
        System.out.print("Equipo: ");
        String equipo = Consola.leeString();
        
        System.out.print("NOC: ");
        String noc = Consola.leeString();
        
        System.out.print("Juegos: ");
        String juegos = Consola.leeString();
        
        System.out.print("Año: ");
        int año = Consola.leeInt();
        
        System.out.print("Temporada: ");
        String temporada = Consola.leeString();
        
        System.out.print("Ciudad: ");
        String ciudad = Consola.leeString();
        
        System.out.print("Deporte: ");
        String deporte = Consola.leeString();
        
        System.out.print("Evento: ");
        String evento = Consola.leeString();
        
        System.out.print("Medalla (si tiene): ");
        String medalla = Consola.leeString();

        Deportista nuevoDeportista = new Deportista(id, nombre, sexo, edad, altura, peso, equipo, noc, juegos, 
                                                     año, temporada, ciudad, deporte, evento, medalla);
        deportistas.add(nuevoDeportista);
        System.out.println("Deportista añadido.");
    }
}
