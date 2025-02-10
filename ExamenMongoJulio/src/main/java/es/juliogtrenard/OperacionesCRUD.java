package es.juliogtrenard;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.*;

public class OperacionesCRUD {
    private static final String DATABASE_NAME = "OndAAkin";
    private static final MongoClient mongoClient = MongoClients.create("mongodb://localhost:27018");
    private static final MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nSeleccione una operación:");
            System.out.println("1. Añadir usuario");
            System.out.println("2. Insertar imagen y anotaciones");
            System.out.println("3. Modificar categoría de anotación");
            System.out.println("4. Eliminar imagen y sus anotaciones");
            System.out.println("5. Eliminar una anotación");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1 -> aniadir();
                case 2 -> insertar();
                case 3 -> modificarAnotacion();
                case 4 -> borrarImagenAnotacion();
                case 5 -> borrarAnotacion();
                case 6 -> {
                    System.out.println("Saliendo...");
                    mongoClient.close();
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private static void aniadir() {
        MongoCollection<Document> users = database.getCollection("usuarios");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña (hasheada): ");
        String password = scanner.nextLine();
        System.out.print("Imagen de perfil (Base64): ");
        String imgPerfil = scanner.nextLine();
        Document user = new Document("nombre", nombre)
                .append("email", email)
                .append("contraseña", password)
                .append("imagen_perfil", imgPerfil);
        users.insertOne(user);
        System.out.println("Usuario añadido: " + user.toJson());
    }

    private static void insertar() {
        MongoCollection<Document> images = database.getCollection("imagenes");
        MongoCollection<Document> annotations = database.getCollection("anotaciones");

        System.out.print("ID del usuario: ");
        ObjectId userId = new ObjectId(scanner.nextLine());
        System.out.print("Imagen en Base64: ");
        String imgData = scanner.nextLine();
        Document image = new Document("imagen", imgData)
                .append("usuario_id", userId)
                .append("fecha", new Date());
        images.insertOne(image);
        ObjectId imageId = image.getObjectId("_id");
        System.out.println("Imagen añadida con ID: " + imageId);

        System.out.print("Número de anotaciones a añadir: ");
        int numAnnotations = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numAnnotations; i++) {
            System.out.println("Anotación " + (i + 1) + ":");
            System.out.print("Categoría: ");
            String categoria = scanner.nextLine();
            System.out.print("Confiabilidad (0-1): ");
            double confiabilidad = scanner.nextDouble();
            scanner.nextLine();
            Document annotation = new Document("imagen_id", imageId)
                    .append("categoria", categoria)
                    .append("confiabilidad", confiabilidad)
                    .append("usuario_id", userId)
                    .append("fecha", new Date());
            annotations.insertOne(annotation);
        }
        System.out.println("Anotaciones añadidas.");
    }

    private static void modificarAnotacion() {
        MongoCollection<Document> annotations = database.getCollection("anotaciones");
        System.out.print("ID de la anotación a modificar: ");
        ObjectId annotationId = new ObjectId(scanner.nextLine());
        System.out.print("Nueva categoría: ");
        String newCategory = scanner.nextLine();
        annotations.updateOne(Filters.eq("_id", annotationId), Updates.set("categoria", newCategory));
        System.out.println("Categoría modificada.");
    }

    private static void borrarImagenAnotacion() {
        MongoCollection<Document> images = database.getCollection("imagenes");
        MongoCollection<Document> annotations = database.getCollection("anotaciones");
        System.out.print("ID de la imagen a eliminar: ");
        ObjectId imageId = new ObjectId(scanner.nextLine());
        images.deleteOne(Filters.eq("_id", imageId));
        annotations.deleteMany(Filters.eq("imagen_id", imageId));
        System.out.println("Imagen y sus anotaciones eliminadas.");
    }

    private static void borrarAnotacion() {
        MongoCollection<Document> annotations = database.getCollection("anotaciones");
        System.out.print("ID de la anotación a eliminar: ");
        ObjectId annotationId = new ObjectId(scanner.nextLine());
        annotations.deleteOne(Filters.eq("_id", annotationId));
        System.out.println("Anotación eliminada.");
    }
}