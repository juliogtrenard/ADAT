package adat;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class OlimpiadasSAXParser {
    public static void listarOlimpiadas(String xmlFile) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            OlimpiadaHandler handler = new OlimpiadaHandler();
            parser.parse(xmlFile, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class OlimpiadaHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("olimpiada")) {
                String year = attributes.getValue("year");
                System.out.println("Olimpiada - Año: " + year);
            }
        }
    }
}
