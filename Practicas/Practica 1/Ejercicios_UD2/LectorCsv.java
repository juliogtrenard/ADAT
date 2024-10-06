package adat;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class LectorCsv {
	
    public static List<Deportista> leerDeportistas(String archivo) throws IOException {
        List<Deportista> deportistas = new ArrayList<>();
        List<String> lineas = Files.readAllLines(Paths.get(archivo));
        
        for (String linea : lineas.subList(1, lineas.size())) {
            String[] campos = linea.split(",");
            Deportista deportista = new Deportista(
                    Integer.parseInt(campos[0]),  // ID
                    campos[1],                     // Name
                    campos[2],                     // Sex
                    Integer.parseInt(campos[3]),   // Age
                    Double.parseDouble(campos[4]), // Height
                    Double.parseDouble(campos[5]), // Weight
                    campos[6],                     // Team
                    campos[7],                     // NOC
                    campos[8],                     // Games
                    Integer.parseInt(campos[9]),   // Year
                    campos[10],                    // Season
                    campos[11],                    // City
                    campos[12],                    // Sport
                    campos[13],                    // Event
                    campos[14]                     // Medal
            );
            deportistas.add(deportista);
        }
        return deportistas;
    }

    public static List<Olimpiada> generarOlimpiadas(List<Deportista> deportistas) {
        Set<Olimpiada> olimpiadas = new HashSet<>();
        for (Deportista d : deportistas) {
            Olimpiada olimpiada = new Olimpiada(d.getGames(), d.getYear(), d.getSeason(), d.getCity());
            olimpiadas.add(olimpiada);
        }
        return new ArrayList<>(olimpiadas);
    }

    public static void escribirOlimpiadasCSV(List<Olimpiada> olimpiadas, String archivo) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(archivo));
        writer.write("Games,Year,Season,City\n");
        for (Olimpiada olimpiada : olimpiadas) {
            writer.write(olimpiada.toString() + "\n");
        }
        writer.close();
    }
}
