package adat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.util.List;

public class CrearXML {
    public static void crearFicheroXMLOlimpiadas(List<Olimpiada> olimpiadas, String ruta) throws Exception {
        olimpiadas.sort((o1, o2) -> {
            if (o1.getYear() != o2.getYear()) return Integer.compare(o1.getYear(), o2.getYear());
            return o1.getSeason().equals("Winter") ? -1 : 1;
        });

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        Element rootElement = doc.createElement("olimpiadas");
        doc.appendChild(rootElement);

        for (Olimpiada olimpiada : olimpiadas) {
            Element olimpiadaElement = doc.createElement("olimpiada");
            olimpiadaElement.setAttribute("year", String.valueOf(olimpiada.getYear()));

            Element juegos = doc.createElement("juegos");
            juegos.appendChild(doc.createTextNode(olimpiada.getGames()));
            olimpiadaElement.appendChild(juegos);

            Element temporada = doc.createElement("temporada");
            temporada.appendChild(doc.createTextNode(olimpiada.getSeason()));
            olimpiadaElement.appendChild(temporada);

            Element ciudad = doc.createElement("ciudad");
            ciudad.appendChild(doc.createTextNode(olimpiada.getCity()));
            olimpiadaElement.appendChild(ciudad);

            rootElement.appendChild(olimpiadaElement);
        }

        File file = new File(ruta);
        javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
        javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
        javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(doc);
        javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(file);
        transformer.transform(source, result);
    }
    
    public static void crearFicheroXMLDeportistas(List<Deportista> deportistas, String ruta) throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        Element rootElement = doc.createElement("deportistas");
        doc.appendChild(rootElement);

        for (Deportista deportista : deportistas) {
            Element deportistaElement = doc.createElement("deportista");
            deportistaElement.setAttribute("id", String.valueOf(deportista.getId()));

            Element nombre = doc.createElement("nombre");
            nombre.appendChild(doc.createTextNode(deportista.getName()));
            deportistaElement.appendChild(nombre);

            Element sexo = doc.createElement("sexo");
            sexo.appendChild(doc.createTextNode(deportista.getSex()));
            deportistaElement.appendChild(sexo);

            Element altura = doc.createElement("altura");
            altura.appendChild(doc.createTextNode(String.valueOf(deportista.getHeight())));
            deportistaElement.appendChild(altura);

            Element peso = doc.createElement("peso");
            peso.appendChild(doc.createTextNode(String.valueOf(deportista.getWeight())));
            deportistaElement.appendChild(peso);

            Element participaciones = doc.createElement("participaciones");
            for (Participacion participacion : deportista.getParticipaciones()) {
                Element deporte = doc.createElement("deporte");
                deporte.setAttribute("nombre", participacion.getSport());

                Element participacionElement = doc.createElement("participacion");
                participacionElement.setAttribute("edad", String.valueOf(participacion.getAge()));

                Element equipo = doc.createElement("equipo");
                equipo.setAttribute("abbr", participacion.getNoc());
                equipo.appendChild(doc.createTextNode(participacion.getTeam()));
                participacionElement.appendChild(equipo);

                Element juegos = doc.createElement("juegos");
                juegos.appendChild(doc.createTextNode(participacion.getGames()));
                participacionElement.appendChild(juegos);

                Element evento = doc.createElement("evento");
                evento.appendChild(doc.createTextNode(participacion.getEvent()));
                participacionElement.appendChild(evento);

                Element medalla = doc.createElement("medalla");
                medalla.appendChild(doc.createTextNode(participacion.getMedal()));
                participacionElement.appendChild(medalla);

                deporte.appendChild(participacionElement);
                participaciones.appendChild(deporte);
            }
            deportistaElement.appendChild(participaciones);

            rootElement.appendChild(deportistaElement);
        }

        File file = new File(ruta);
        javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
        javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
        javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(doc);
        javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(file);
        transformer.transform(source, result);
    }
}
