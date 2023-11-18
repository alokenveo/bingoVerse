package es.unex.cum.mdp.ef2;

/**
 * Clase que representa las estadisticas de un numero en un juego de Bingo.
 */
public class Estadistica {
	private int numero;
	private int numSacado;
	private int numEspecial;
	private int numLinea;
	private int numBingo;

	/**
     * Constructor de la clase Estadistica.
     * @param n Numero asociado a las estadisticas.
     */
	public Estadistica(int n) {
		super();
		this.numero = n;
	}

	/**
     * Obtiene el numero asociado a la estadistica.
     * @return Numero asociado a la estadistica.
     */
	public int getNumero() {
		return numero;
	}

	/**
     * Establece el numero asociado a la estadistica.
     * @param numero Numero a establecer.
     */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
     * Obtiene la cantidad de veces que el numero fue sacado.
     * @return Cantidad de veces que el numero fue sacado.
     */
	public int getNumSacado() {
		return numSacado;
	}

	/**
     * Establece la cantidad de veces que el numero fue sacado.
     * @param numSacado Cantidad de veces a establecer.
     */
	public void setNumSacado(int numSacado) {
		this.numSacado = numSacado;
	}

	/**
     * Obtiene la cantidad de veces que el numero fue especial.
     * @return Cantidad de veces que el numero fue especial.
     */
	public int getNumEspecial() {
		return numEspecial;
	}

	/**
     * Establece la cantidad de veces que el numero fue especial.
     * @param numEspecial Cantidad de veces a establecer.
     */
	public void setNumEspecial(int numEspecial) {
		this.numEspecial = numEspecial;
	}

	/**
     * Obtiene la cantidad de veces que el numero fue parte de una linea ganadora.
     * @return Cantidad de veces que el numero fue parte de una linea ganadora.
     */
	public int getNumLinea() {
		return numLinea;
	}

	/**
     * Establece la cantidad de veces que el numero fue parte de una linea ganadora.
     * @param numLinea Cantidad de veces a establecer.
     */
	public void setNumLinea(int numLinea) {
		this.numLinea = numLinea;
	}

	/**
     * Obtiene la cantidad de veces que el numero fue parte de un bingo ganador.
     * @return Cantidad de veces que el numero fue parte de un bingo ganador.
     */
	public int getNumBingo() {
		return numBingo;
	}

	/**
     * Establece la cantidad de veces que el numero fue parte de un bingo ganador.
     * @param numBingo Cantidad de veces a establecer.
     */
	public void setNumBingo(int numBingo) {
		this.numBingo = numBingo;
	}

	/**
     * Devuelve una representacion en cadena de la instancia de Estadistica.
     * @return Representacion en cadena de la instancia.
     */
	@Override
	public String toString() {
		return "Estadistica [numero=" + numero + ", numSacado=" + numSacado + ", numEspecial=" + numEspecial
				+ ", numLinea=" + numLinea + ", numBingo=" + numBingo + "]";
	}

}
