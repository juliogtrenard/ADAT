package adat;

import java.util.*;
import faciles.Consola;

public class PrincipalXML {

    public static void main(String[] args) throws Exception {
        boolean salir = false;

        while (!salir) {
            System.out.println("\nMenú:");
            System.out.println("1. Crear fichero XML de olimpiadas");
            System.out.println("2. Crear fichero XML de deportistas");
            System.out.println("3. Listado de olimpiadas");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            int opcion = Consola.leeInt();

            switch (opcion) {
                case 1:
                    List<Olimpiada> olimpiadas = new ArrayList<>();
                    olimpiadas.add(new Olimpiada("Summer Olympics", 2020, "Summer", "Tokyo"));
                    olimpiadas.add(new Olimpiada("Winter Olympics", 2018, "Winter", "Pyeongchang"));
                    CrearXML.crearFicheroXMLOlimpiadas(olimpiadas, "olimpiadas.xml");
                    System.out.println("Fichero olimpiadas.xml creado.");
                    break;
                case 2:
                    List<Deportista> deportistas = new ArrayList<>();
                    Deportista deportista1 = new Deportista(
                            1,                      // id
                            "Michael Phelps",       // name
                            "Male",                 // sex
                            23,                     // age
                            1.93,                   // height
                            88,                     // weight
                            "USA",                  // team
                            "USA",                  // noc
                            "Beijing 2008",         // games
                            2008,                   // year
                            "Summer",               // season
                            "Beijing",              // city
                            "Swimming",             // sport
                            "100m freestyle",       // event
                            "Gold"                  // medal
                        );
                    deportista1.addParticipacion(new Participacion("Swimming", 23, "USA", "USA", "Beijing 2008", "100m freestyle", "Gold"));
                    deportistas.add(deportista1);
                    CrearXML.crearFicheroXMLDeportistas(deportistas, "deportistas.xml");
                    System.out.println("Fichero deportistas.xml creado.");
                    break;
                case 3:
                    OlimpiadasSAXParser.listarOlimpiadas("olimpiadas.xml");
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
