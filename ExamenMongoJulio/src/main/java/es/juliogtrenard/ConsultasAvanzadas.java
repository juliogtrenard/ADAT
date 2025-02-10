package es.juliogtrenard;

import com.mongodb.client.*;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.Arrays;

public class ConsultasAvanzadas {
    private static final String DATABASE_NAME = "OndAAkin";
    private static final MongoClient mongoClient = MongoClients.create("mongodb://localhost:27018");
    private static final MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);

    public static void obtenerImagenesConAnotacion(String userName) {
        MongoCollection<Document> users = database.getCollection("usuarios");
        MongoCollection<Document> images = database.getCollection("imagenes");
        MongoCollection<Document> annotations = database.getCollection("anotaciones");

        Document user = users.find(Filters.eq("nombre", userName)).first();
        if (user == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        ObjectId userId = user.getObjectId("_id");

        for (Document image : images.find(Filters.eq("usuario_id", userId))) {
            System.out.println("Imagen ID: " + image.getObjectId("_id"));
            for (Document annotation : annotations.find(Filters.eq("imagen_id", image.getObjectId("_id")))) {
                System.out.println("  - Anotación: " + annotation.toJson());
            }
        }
    }

    public static void imagenesPorUsuario() {
        MongoCollection<Document> images = database.getCollection("imagenes");
        MongoCollection<Document> users = database.getCollection("usuarios");

        AggregateIterable<Document> results = images.aggregate(Arrays.asList(
                Aggregates.group("$usuario_id", Accumulators.sum("total", 1))
        ));

        for (Document doc : results) {
            ObjectId userId = doc.getObjectId("_id");
            Document user = users.find(Filters.eq("_id", userId)).first();
            String userName = (user != null) ? user.getString("nombre") : "Desconocido";
            System.out.println(userName + " ha subido " + doc.getInteger("total") + " imágenes.");
        }
    }

    public static void top4Categorias() {
        MongoCollection<Document> annotations = database.getCollection("anotaciones");
        long totalAnnotations = annotations.countDocuments();

        AggregateIterable<Document> results = annotations.aggregate(Arrays.asList(
                Aggregates.group("$categoria", Accumulators.sum("count", 1)),
                Aggregates.sort(new Document("count", -1)),
                Aggregates.limit(4)
        ));

        for (Document doc : results) {
            String category = doc.getString("_id");
            int count = doc.getInteger("count");
            double percentage = (count / (double) totalAnnotations) * 100;
            System.out.printf("%s: %d anotaciones (%.2f%%)\n", category, count, percentage);
        }
    }

    public static void anotacionesAltaConfiabilidad() {
        MongoCollection<Document> annotations = database.getCollection("anotaciones");

        for (Document doc : annotations.find(Filters.and(
                Filters.gt("confiabilidad", 0.8),
                Filters.eq("tipo", "automática")
        ))) {
            System.out.println(doc.toJson());
        }
    }

    public static void main(String[] args) {
        System.out.println("Imágenes con anotaciones de Juan Pérez:");
        obtenerImagenesConAnotacion("Juan Pérez");

        System.out.println("\nCantidad de imágenes por usuario:");
        imagenesPorUsuario();

        System.out.println("\nLas 4 categorías más anotadas:");
        top4Categorias();

        System.out.println("\nAnotaciones automáticas con confiabilidad > 80%:");
        anotacionesAltaConfiabilidad();
    }
}
