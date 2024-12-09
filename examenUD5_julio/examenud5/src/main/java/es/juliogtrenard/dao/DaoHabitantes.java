package es.juliogtrenard.dao;

import com.db4o.ObjectContainer;
import es.juliogtrenard.model.ModeloHabitantes;

public class DaoHabitantes {
    public static void insertar(ModeloHabitantes habitante, ObjectContainer db) {
        db.store(habitante);
    }

    public static void modificarHabitante(ObjectContainer db, ModeloHabitantes habitante, String nuevoNombre) {
        habitante.setNombre(nuevoNombre);
        db.store(habitante);
    }

    public static void eliminar(ObjectContainer db, ModeloHabitantes habitante) {
        db.delete(habitante);
    }
}
