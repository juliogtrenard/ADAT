package adat;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import faciles.Consola;

public class FicherosBinarios {

    public static void main(String[] args) throws Exception {
        boolean salir = false;

        String ruta = "olimpiadas.dat";

        while (!salir) {
            System.out.println("\nMenú:");
            System.out.println("1. Crear fichero serializable de olimpiadas");
            System.out.println("2. Añadir edición olímpica");
            System.out.println("3. Buscar olimpiadas por sede");
            System.out.println("4. Eliminar edición olímpica");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            int opcion = Consola.leeInt();

            switch (opcion) {
                case 1:
                    crearFicheroBinarioDeOlimpiadas("olimpiadas.xml", ruta);
                    break;
                case 2:
                    añadirEdicionOlimpica(ruta);
                    break;
                case 3:
                    buscarOlimpiadasPorSede(ruta);
                    break;
                case 4:
                    eliminarOlimpiada(ruta);
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void crearFicheroBinarioDeOlimpiadas(String xmlFilePath, String binFilePath) throws Exception {
        File xmlFile = new File(xmlFilePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        List<Olimpiada> olimpiadas = new ArrayList<>();

        NodeList nList = doc.getElementsByTagName("olimpiada");
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String games = element.getElementsByTagName("juegos").item(0).getTextContent();
                int year = Integer.parseInt(element.getAttribute("year"));
                String season = element.getElementsByTagName("temporada").item(0).getTextContent();
                String city = element.getElementsByTagName("ciudad").item(0).getTextContent();

                Olimpiada olimpiada = new Olimpiada(games, year, season, city);
                olimpiadas.add(olimpiada);
            }
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(binFilePath))) {
            out.writeObject(olimpiadas);
            System.out.println("Fichero binario creado correctamente.");
        }
    }

    public static void añadirEdicionOlimpica(String binFilePath) throws Exception {
        List<Olimpiada> olimpiadas = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(binFilePath))) {
            olimpiadas = (List<Olimpiada>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el fichero. Se creará un nuevo archivo.");
        }

        System.out.print("Ingrese el nombre de los Juegos Olímpicos: ");
        String games = Consola.leeString();
        
        System.out.print("Ingrese el año: ");
        int year = Consola.leeInt();
        
        System.out.print("Ingrese la temporada (Summer/Winter): ");
        String season = Consola.leeString();
        
        System.out.print("Ingrese la ciudad: ");
        String city = Consola.leeString();

        Olimpiada nuevaOlimpiada = new Olimpiada(games, year, season, city);
        olimpiadas.add(nuevaOlimpiada);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(binFilePath))) {
            out.writeObject(olimpiadas);
            System.out.println("Nueva edición olímpica añadida correctamente.");
        }
    }

    public static void buscarOlimpiadasPorSede(String binFilePath) throws Exception {
        List<Olimpiada> olimpiadas = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(binFilePath))) {
            olimpiadas = (List<Olimpiada>) in.readObject();
        }

        System.out.print("Ingrese la ciudad a buscar: ");
        String ciudadBusqueda = Consola.leeString();

        boolean encontrado = false;
        for (Olimpiada olimpiada : olimpiadas) {
            if (olimpiada.getCity().toLowerCase().contains(ciudadBusqueda.toLowerCase())) {
                System.out.println(olimpiada);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron olimpiadas para esa sede.");
        }
    }

    public static void eliminarOlimpiada(String binFilePath) throws Exception {
        List<Olimpiada> olimpiadas = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(binFilePath))) {
            olimpiadas = (List<Olimpiada>) in.readObject();
        }

        System.out.print("Ingrese el año de la olimpiada a eliminar: ");
        int year = Consola.leeInt();
        
        System.out.print("Ingrese la temporada (Summer/Winter): ");
        String season = Consola.leeString();

        boolean eliminado = false;
        Iterator<Olimpiada> iterator = olimpiadas.iterator();
        while (iterator.hasNext()) {
            Olimpiada olimpiada = iterator.next();
            if (olimpiada.getYear() == year && olimpiada.getSeason().equalsIgnoreCase(season)) {
                iterator.remove();
                eliminado = true;
                break;
            }
        }

        if (eliminado) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(binFilePath))) {
                out.writeObject(olimpiadas);
                System.out.println("Edición olímpica eliminada correctamente.");
            }
        } else {
            System.out.println("No se encontró la edición olímpica para el año y temporada proporcionados.");
        }
    }
}
