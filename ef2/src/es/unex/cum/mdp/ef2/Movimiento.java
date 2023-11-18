package es.unex.cum.mdp.ef2;

import java.util.*;

/**
 * Clase que representa un movimiento financiero realizado por un usuario.
 */
public class Movimiento {
	protected String info;
	protected float monederoActual;
	protected float valor;
	protected Date fecha;
	
	/**
     * Constructor por defecto de la clase Movimiento.
     */
	public Movimiento() {
		super();
	}
	
	/**
     * Constructor de la clase Movimiento con informacion, valor y fecha.
     * @param info Informacion asociada al movimiento.
     * @param valor Valor del movimiento.
     * @param fecha Fecha en la que se realiza el movimiento.
     */
	public Movimiento(String info, float valor, Date fecha) {
		super();
		this.info = info;
		this.valor = valor;
		this.fecha = fecha;
	}
	
	/**
     * Constructor de la clase Movimiento con informacion, valor, monedero actual y fecha.
     * @param info Informacion asociada al movimiento.
     * @param valor Valor del movimiento.
     * @param monederoActual Monedero actual del usuario.
     * @param fecha Fecha en la que se realiza el movimiento.
     */
	public Movimiento(String info, float valor, float monederoActual, Date fecha) {
		super();
		this.info = info;
		this.monederoActual = monederoActual;
		this.valor = valor;
		this.fecha = fecha;
	}
	
	/**
     * Constructor de la clase Movimiento con informacion, precio y monedero actual.
     * La fecha se establece como la fecha actual.
     * @param string Informacion asociada al movimiento.
     * @param precio Precio del movimiento.
     * @param monederoActual Monedero actual del usuario.
     */
	public Movimiento(String string, float precio,float monederoActual) {
		this.info=string;
		this.monederoActual=monederoActual;
		this.valor=precio;
		fecha=new Date();
		
	}
	
	/**
     * Obtiene la informacion asociada al movimiento.
     * @return Informacion asociada al movimiento.
     */

	public String getInfo() {
		return info;
	}
	
	/**
     * Establece la informacion asociada al movimiento.
     * @param info Informacion a establecer.
     */
	public void setInfo(String info) {
		this.info = info;
	}
	
	/**
     * Obtiene el monto actual del monedero del usuario.
     * @return Monto actual del monedero.
     */
	public float getMonederoActual() {
		return monederoActual;
	}
	
	/**
     * Establece el monto actual del monedero del usuario.
     * @param monederoActual Monto a establecer.
     */
	public void setMonederoActual(float monederoActual) {
		this.monederoActual = monederoActual;
	}
	
	/**
     * Obtiene el valor del movimiento.
     * @return Valor del movimiento.
     */
	public float getValor() {
		return valor;
	}
	
	/**
     * Establece el valor del movimiento.
     * @param valor Valor a establecer.
     */
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	/**
     * Obtiene la fecha en que se realizo el movimiento.
     * @return Fecha del movimiento.
     */
	public Date getFecha() {
		return fecha;
	}
	
	/**
     * Establece la fecha en que se realizo el movimiento.
     * @param fecha Fecha a establecer.
     */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	/**
     * Calcula y devuelve el codigo hash del objeto.
     * @return Codigo hash del objeto.
     */
	@Override
	public int hashCode() {
		return Objects.hash(fecha, info, monederoActual, valor);
	}
	
	 /**
     * Compara este objeto con otro para verificar si son iguales.
     * @param obj Objeto a comparar.
     * @return true si los objetos son iguales, false de lo contrario.
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimiento other = (Movimiento) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(info, other.info)
				&& Float.floatToIntBits(monederoActual) == Float.floatToIntBits(other.monederoActual)
				&& Float.floatToIntBits(valor) == Float.floatToIntBits(other.valor);
	}
	
	/**
     * Devuelve una representacion en cadena de la instancia de Movimiento.
     * @return Representacion en cadena de la instancia.
     */
	@Override
	public String toString() {
		return "Movimiento [info=" + info + ", monederoActual=" + monederoActual + ", valor=" + valor + ", fecha="
				+ fecha + "]";
	}
}
