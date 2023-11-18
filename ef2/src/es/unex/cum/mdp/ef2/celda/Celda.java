package es.unex.cum.mdp.ef2.celda;

import java.util.*;

/**
 * Clase abstracta que representa una celda generica en un contexto de juego.
 */
public abstract class Celda {
	/**
     * Numero asociado a la celda.
     */
	protected int numero;
	
	/**
     * Estado actual de la celda (VACIO, NUMERO, CANTADO).
     */
	protected EstadoCelda estado; //PREGUNTAR SI ES UN ENUMERADO O NO
	
	/**
     * Constructor por defecto de la celda.
     */
	public Celda() {
		super();
	}
	
	/**
     * Constructor que establece el numero asociado a la celda.
     *
     * @param numero Numero de la celda.
     */
	public Celda(int numero) {
		super();
		this.numero = numero;
	}
	
	 /**
     * Constructor que establece el numero y el estado de la celda.
     *
     * @param numero Numero de la celda.
     * @param estado Estado de la celda (VACIO, NUMERO, CANTADO).
     */
	public Celda(int numero, int estado) {
		super();
		this.numero = numero;
		switch(estado) {
		case 0:
			this.estado=EstadoCelda.VACIO;
			break;
		case 1:
			this.estado=EstadoCelda.NUMERO;
			break;
		case 2:
			this.estado=EstadoCelda.CANTADO;
			break;
		default:
			break;
		}
	}
	
	/**
     * Obtiene el numero asociado a la celda.
     *
     * @return Numero de la celda.
     */
	public int getNumero() {
		return numero;
	}
	
	/**
     * Establece el numero asociado a la celda.
     *
     * @param numero Numero de la celda.
     */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	/**
     * Obtiene el estado actual de la celda.
     *
     * @return Estado de la celda.
     */
	public EstadoCelda getEstado() {
		return estado;
	}
	
	/**
     * Establece el estado de la celda.
     *
     * @param estado Estado de la celda.
     */
	public void setEstado(EstadoCelda estado) {
		this.estado = estado;
	}
	
	/**
     * Calcula el codigo hash de la celda.
     *
     * @return Codigo hash.
     */
	@Override
	public int hashCode() {
		return Objects.hash(estado, numero);
	}
	
	/**
     * Compara la celda con otro objeto para determinar si son iguales.
     *
     * @param obj Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celda other = (Celda) obj;
		return estado == other.estado && numero == other.numero;
	}
	
	/**
     * Representacion en formato de cadena de la celda.
     *
     * @return Cadena que representa la celda.
     */
	@Override
	public String toString() {
		return "Celda [numero=" + numero + ", estado=" + estado + "]";
	}
}
