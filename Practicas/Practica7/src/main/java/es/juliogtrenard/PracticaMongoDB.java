package es.juliogtrenard;

import com.mongodb.client.*;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class PracticaMongoDB {
    private static MongoCollection<Document> collection;

    public static void main(String[] args) {
        // Me conecto desde el Docker en lugar de usar la url del cluster porque no me aparece el sample_airbnb
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("sample_airbnb");
        collection = database.getCollection("listingsAndReviews");

        // Crear un alojamiento
        String listingId = crearAlojamiento();

        // Leer alojamiento
        leerAlojamientos("Rio De Janeiro", 50.0, 500.0);

        // Actualizar el precio del alojamiento
        actualizarPrecio(listingId, 80.0);

        // Eliminar el alojamiento actualizado
        eliminarAlojamiento(listingId);

        // Cerrar la conexión
        mongoClient.close();
    }

    // Crear
    public static String crearAlojamiento() {
        Document alojamiento = new Document("listing_id", "10093234")
                .append("name", "Kora Green City")
                .append("price", 120.00)
                .append("city", "Vitoria")
                .append("bedrooms", 5)
                .append("bathrooms", 2)
                .append("amenities", List.of("TV", "Wifi", "Cocina"))
                .append("description", "Fantástico sitio con las mejores vistas.")
                .append("host", new Document("host_id", "12213457").append("host_name", "Carlos Garcia"))
                .append("address", new Document("street", "Vitoria Gasteiz").append("country", "España"))
                .append("availability", new Document("availability_30", 28).append("availability_60", 47));

        collection.insertOne(alojamiento);

        System.out.println("--- Crear (Insertar) ---");
        System.out.println("Nuevo alojamiento insertado: " + alojamiento.toJson());
        return alojamiento.getString("listing_id");
    }

    // Leer
    public static void leerAlojamientos(String ciudad, double precioMin, double precioMax) {
        List<Document> alojamientos = collection.find(new Document("address.market", ciudad)
                        .append("price", new Document("$gte", precioMin).append("$lte", precioMax)))
                .into(new ArrayList<>());

        System.out.println("--- Leer (Consultar) ---");
        for (Document alojamiento : alojamientos) {
            System.out.println(alojamiento.toJson());
        }
    }

    // Actualizar
    public static void actualizarPrecio(String listingId, double nuevoPrecio) {
        Document filtro = new Document("listing_id", listingId);
        Document actualizacion = new Document("$set", new Document("price", nuevoPrecio));

        collection.updateOne(filtro, actualizacion);

        System.out.println("--- Actualizar ---");
        System.out.println("Alojamiento actualizado: " + filtro.toJson());
    }

    // Eliminar
    public static void eliminarAlojamiento(String listingId) {
        Document filtro = new Document("listing_id", listingId);
        collection.deleteOne(filtro);

        System.out.println("--- Eliminar ---");
        System.out.println("Alojamiento eliminado");
    }
}
