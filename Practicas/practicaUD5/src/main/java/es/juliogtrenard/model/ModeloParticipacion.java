package es.juliogtrenard.model;

import java.util.Objects;

/**
 * Clase ModeloParticipacion.
 */
public class ModeloParticipacion {

	/** Deportista. */
	private ModeloDeportista deportista;
	
	/** Evento. */
	private ModeloEvento evento;
	
	/** Equipo. */
	private ModeloEquipo equipo;
	
	/** Edad. */
	private int edad;
	
	/** Medalla. */
	private String medalla;

	/**
	 * Crea un objeto ModeloParticipacion.
	 */
	public ModeloParticipacion() {}
	
	/**
	 * Crea un objeto ModeloParticipacion con sus datos.
	 *
	 * @param deportista deportista
	 * @param evento evento
	 * @param equipo equipo
	 * @param edad edad
	 * @param medalla medalla
	 */
	public ModeloParticipacion(ModeloDeportista deportista, ModeloEvento evento, ModeloEquipo equipo, int edad,
			String medalla) {
		super();
		this.deportista = deportista;
		this.evento = evento;
		this.equipo = equipo;
		this.edad = edad;
		this.medalla = medalla;
	}
	
	/**
	 * Hash code.
	 *
	 * @return int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(deportista, edad, equipo, evento, medalla);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
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
		ModeloParticipacion other = (ModeloParticipacion) obj;
		return Objects.equals(deportista, other.deportista) && edad == other.edad
				&& Objects.equals(equipo, other.equipo) && Objects.equals(evento, other.evento)
				&& Objects.equals(medalla, other.medalla);
	}

	/**
	 * Gets the deportista.
	 *
	 * @return deportista
	 */
	public ModeloDeportista getDeportista() {
		return deportista;
	}

	/**
	 * Sets the deportista.
	 *
	 * @param deportista el nuevo deportista
	 */
	public void setDeportista(ModeloDeportista deportista) {
		this.deportista = deportista;
	}

	/**
	 * Gets the evento.
	 *
	 * @return evento
	 */
	public ModeloEvento getEvento() {
		return evento;
	}

	/**
	 * Sets the evento.
	 *
	 * @param evento el nuevo evento
	 */
	public void setEvento(ModeloEvento evento) {
		this.evento = evento;
	}

	/**
	 * Gets the equipo.
	 *
	 * @return equipo
	 */
	public ModeloEquipo getEquipo() {
		return equipo;
	}

	/**
	 * Sets the equipo.
	 *
	 * @param equipo el nuevo equipo
	 */
	public void setEquipo(ModeloEquipo equipo) {
		this.equipo = equipo;
	}

	/**
	 * Gets the edad.
	 *
	 * @return edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Sets the edad.
	 *
	 * @param edad la nueva edad
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * Gets the medalla.
	 *
	 * @return medalla
	 */
	public String getMedalla() {
		return medalla;
	}

	/**
	 * Sets the medalla.
	 *
	 * @param medalla la nueva medalla
	 */
	public void setMedalla(String medalla) {
		this.medalla = medalla;
	}
	
}
