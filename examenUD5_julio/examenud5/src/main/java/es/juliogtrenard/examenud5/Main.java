package es.juliogtrenard.examenud5;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.db4o.ObjectContainer;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import es.juliogtrenard.db.DB;
import es.juliogtrenard.model.ModeloDistritos;
import es.juliogtrenard.model.ModeloHabitantes;
import es.juliogtrenard.model.ModeloJuegos;

public class Main {
    public static void main(String[] args) {
        ObjectContainer db = new DB().getConnection();

        try {
            cargarDatos(db);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cargarDatos(ObjectContainer db) throws IOException {
        List<ModeloDistritos> distritos = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("/home/dm2/Escritorio/examenUD5_julio/examenud5/src/main/resources/csv/distritos.csv"))) {
            String[] nextLine;
            nextLine = reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                ModeloDistritos distrito = new ModeloDistritos(
                        Integer.parseInt(nextLine[0]),
                        nextLine[1]
                );
                distritos.add(distrito);
            }
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        for (ModeloDistritos distrito : distritos) {            
            db.store(distrito);
        }

        List<ModeloHabitantes> habitantes = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("/home/dm2/Escritorio/examenUD5_julio/examenud5/src/main/resources/csv/habitantes.csv"))) {
            String[] nextLine;
            nextLine = reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                ModeloHabitantes habitante = new ModeloHabitantes(
                    Integer.parseInt(nextLine[0]),
                        nextLine[1],
                        Integer.parseInt(nextLine[2]),
                        Boolean.parseBoolean(nextLine[3])
                );
                habitantes.add(habitante);
            }
        } catch (CsvValidationException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
        
        for (ModeloHabitantes habitante : habitantes) {
            db.store(habitante);
        }

        List<ModeloJuegos> juegos = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("/home/dm2/Escritorio/examenUD5_julio/examenud5/src/main/resources/csv/juegos.csv"))) {
            String[] nextLine;
            nextLine = reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                ModeloJuegos juego = new ModeloJuegos(
                    Integer.parseInt(nextLine[0]),
                    Integer.parseInt(nextLine[1])
                );
                juegos.add(juego);
            }
        } catch (CsvValidationException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
        
        for (ModeloJuegos juego : juegos) {
            db.store(juego);
        }
    }
}