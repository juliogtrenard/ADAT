package es.juliogtrenard.dao;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import es.juliogtrenard.model.ModeloDistritos;

public class DaoDistritos {
    public static void insertar(ModeloDistritos distrito, ObjectContainer db) {
		db.store(distrito);
	}

    public static void eliminar(ObjectContainer db, ModeloDistritos distrito) {
		db.delete(distrito);
	}

    public static void todosDistritos(ObjectContainer db) {
        Query query = db.query();
        query.constrain(ModeloDistritos.class);
        ObjectSet<ModeloDistritos> resultados = query.execute();

        while (resultados.hasNext()) {
            ModeloDistritos distrito = resultados.next();
            System.out.println(distrito.toString());
        }
    }
}
