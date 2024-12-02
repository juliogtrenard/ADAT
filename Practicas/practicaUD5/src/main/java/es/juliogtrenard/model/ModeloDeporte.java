package es.juliogtrenard.model;

import java.util.Objects;

/**
 * Clase Deporte.
 */
public class ModeloDeporte {

	/** El nombre del deporte */
	private String nombre;
	
	/**
	 * Instancia un nuevo Deporte.
	 */
	public ModeloDeporte() {}
	
	/**
	 * Instancia un nuevo Deporte pasandole un nombre.
	 *
	 * @param nombre the nombre
	 */
	public ModeloDeporte(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Hash code.
	 *
	 * @return int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	/**
	 * Equals.
	 *
	 * @param obj obj
	 * @return true, si es igual
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModeloDeporte other = (ModeloDeporte) obj;
		return nombre == other.nombre;
	}

	/**
	 * Obtiene el nombre
	 *
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre
	 *
	 * @param nombre el nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
