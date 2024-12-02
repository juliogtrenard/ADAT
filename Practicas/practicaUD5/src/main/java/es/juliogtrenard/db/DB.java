package es.juliogtrenard.db;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/**
 * Clase para manejar la base de datos.
 */
public class DB {

    /**
     * La base de datos
     */
    private static ObjectContainer db = null;

    /**
     * consigue la conexion y si es nula la crea.
     *
     * @return La conexión
     */
    public static ObjectContainer getConnection() {
        if (db == null) {
            try {
                // Configurar y abrir conexión a la base de datos db4o
                db = Db4oEmbedded.openFile("database.db4o");
                System.out.println("Conexión a db4o creada");
            } catch (Exception e) {
                throw new RuntimeException("Error al conectar con db4o: " + e.getMessage());
            }
        }
        return db;
    }

    /**
     * Cerrar la conexion.
     */
    public static void closeConnection() {
        if (db != null) {
            db.close();
            System.out.println("Conexión a db4o cerrada");
        }
    }

}
