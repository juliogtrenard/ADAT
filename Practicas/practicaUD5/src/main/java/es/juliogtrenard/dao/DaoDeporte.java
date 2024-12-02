package es.juliogtrenard.dao;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import es.juliogtrenard.model.ModeloDeporte;

/**
 * Clase para controlar las operaciones de DaoDeporte.
 */
public class DaoDeporte {

	/**
	 * Insertar.
	 *
	 * @param dep the dep
	 * @param db the db
	 */
	public static void insertar(ModeloDeporte dep, ObjectContainer db) {
		db.store(dep);
	}
	
	/**
	 * Conseguir por nombre.
	 *
	 * @param nombre the nombre
	 * @param db the db
	 * @return the modelo deporte
	 */
	public static ModeloDeporte conseguirPorNombre(String nombre, ObjectContainer db) {
		ModeloDeporte dep=new ModeloDeporte();
		dep.setNombre(nombre);
		ObjectSet<ModeloDeporte> set=db.queryByExample(dep);
		return set.hasNext() ? set.next() : null;
	}
	
}
