package es.juliogtrenard.model;

import java.util.Objects;

/**
 * Clase ModeloEquipo.
 */
public class ModeloEquipo {

	/** Nombre. */
	private String nombre;
	
	/** Iniciales. */
	private String iniciales;
	
	/**
	 * Crea un nuevo Equipo.
	 */
	public ModeloEquipo() {}
	
	/**
	 * Crea un nuevo Equipo con sus datos.
	 *
	 * @param nombre nombre
	 * @param iniciales iniciales
	 */
	public ModeloEquipo(String nombre, String iniciales) {
		this.nombre = nombre;
		this.iniciales = iniciales;
	}

	/**
	 * Hash code.
	 *
	 * @return int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(iniciales, nombre);
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
		ModeloEquipo other = (ModeloEquipo) obj;
		return Objects.equals(iniciales, other.iniciales) && Objects.equals(nombre, other.nombre);
	}

	/**
	 * Obitene el nombre.
	 *
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre.
	 *
	 * @param nombre nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene las iniciales.
	 *
	 * @return iniciales
	 */
	public String getIniciales() {
		return iniciales;
	}

	/**
	 * Establece las iniciales.
	 *
	 * @param iniciales las nuevas iniciales
	 */
	public void setIniciales(String iniciales) {
		this.iniciales = iniciales;
	}
	
}
