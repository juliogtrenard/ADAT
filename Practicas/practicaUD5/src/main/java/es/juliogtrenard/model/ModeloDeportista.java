package es.juliogtrenard.model;

import java.util.Objects;

/**
 * Clase ModeloDeportista.
 */
public class ModeloDeportista {

	/** El nombre */
	private String nombre;
	
	/** Sexo. */
	private char sexo;
	
	/** Peso. */
	private float peso;
	
	/** Altura. */
	private int altura; //centimetros
	
	/**
	 * Instancia un nuevo Deportista.
	 */
	public ModeloDeportista() {}
	
	/**
	 * Instancia un nuevo deportista pasandole sus datos.
	 *
	 * @param nombre nombre
	 * @param sexo sexo
	 * @param peso peso
	 * @param altura altura
	 */
	public ModeloDeportista(String nombre, char sexo, float peso, int altura) {
		super();
		this.nombre = nombre;
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
	}

	/**
	 * Hash code.
	 *
	 * @return int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(altura, nombre, peso, sexo);
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
		ModeloDeportista other = (ModeloDeportista) obj;
		return altura == other.altura && Objects.equals(nombre, other.nombre)
				&& Float.floatToIntBits(peso) == Float.floatToIntBits(other.peso) && sexo == other.sexo;
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
	 * Establece el nuevo nombre
	 *
	 * @param nombre nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el sexo
	 *
	 * @return sexo
	 */
	public char getSexo() {
		return sexo;
	}

	/**
	 * Establece el sexo
	 *
	 * @param sexo nuevo sexo
	 */
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	/**
	 * Obtiene el peso
	 *
	 * @return peso
	 */
	public float getPeso() {
		return peso;
	}

	/**
	 * Establece el peso
	 *
	 * @param peso nuevo peso
	 */
	public void setPeso(float peso) {
		this.peso = peso;
	}

	/**
	 * Obtiene la altura
	 *
	 * @return altura
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * Establece la altura
	 *
	 * @param altura nueva altura
	 */
	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	
}
