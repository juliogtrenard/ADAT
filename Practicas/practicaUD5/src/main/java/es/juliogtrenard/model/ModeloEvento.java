package es.juliogtrenard.model;

import java.util.Objects;

/**
 * Clase ModeloEvento.
 */
public class ModeloEvento {

	/** Nombre. */
	private String nombre;
	
	/** Olimpiada. */
	private ModeloOlimpiada olimpiada;
	
	/** Deporte. */
	private ModeloDeporte deporte;
	
	/**
	 * Crea un nuevo ModeloEvento.
	 */
	public ModeloEvento() {}
	
	/**
	 * Crea un nuevo ModeloEvento con sus datos.
	 *
	 * @param nombre nombre
	 * @param olimpiada olimpiada
	 * @param deporte deporte
	 */
	public ModeloEvento(String nombre, ModeloOlimpiada olimpiada, ModeloDeporte deporte) {
		this.nombre = nombre;
		this.olimpiada = olimpiada;
		this.deporte = deporte;
	}
	
	/**
	 * Hash code.
	 *
	 * @return int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(deporte, nombre, olimpiada);
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
		ModeloEvento other = (ModeloEvento) obj;
		return Objects.equals(deporte, other.deporte) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(olimpiada, other.olimpiada);
	}

	/**
	 * Obtiene el nombre.
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
	 * Obtiene la olimpiada.
	 *
	 * @return olimpiada
	 */
	public ModeloOlimpiada getOlimpiada() {
		return olimpiada;
	}
	
	/**
	 * SEstablece la olimpiada.
	 *
	 * @param olimpiada nueva olimpiada
	 */
	public void setOlimpiada(ModeloOlimpiada olimpiada) {
		this.olimpiada = olimpiada;
	}
	
	/**
	 * Obtiene el deporte.
	 *
	 * @return el deporte
	 */
	public ModeloDeporte getDeporte() {
		return deporte;
	}
	
	/**
	 * Establece el deporte.
	 *
	 * @param deporte el nuevo deporte
	 */
	public void setDeporte(ModeloDeporte deporte) {
		this.deporte = deporte;
	}

}
