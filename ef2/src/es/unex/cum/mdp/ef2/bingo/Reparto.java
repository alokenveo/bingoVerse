package es.unex.cum.mdp.ef2.bingo;

/**
 * Clase que representa el reparto de premios en un juego de Bingo.
 */
public class Reparto {
	private int numLineas;
	private int numBingo;
	private int numEspeciales;
	private float repartoEspeciales;
	private float repartoLinea;
	private float repartoBingo;
	private float caja;
	private float recaudacion;

	/**
	 * Constructor por defecto de un objeto Reparto
	 */
	public Reparto() {
		super();
	}

	/**
     * Construye un objeto Reparto con una recaudacion inicial.
     * @param recaudacion2 La recaudacion inicial del juego de Bingo.
     */
	public Reparto(float recaudacion2) {
		this.recaudacion=recaudacion2;
	}

	/**
	 * Obtiene el numero de lineas ganadoras en el juego de Bingo.
	 * @return El numero de lineas ganadoras.
	 */
	public int getNumLineas() {
		return numLineas;
	}

	/**
	 * Establece el numero de lineas ganadoras en el juego de Bingo.
	 * @param numLineas El nuevo numero de lineas ganadoras a establecer.
	 */
	public void setNumLineas(int numLineas) {
		this.numLineas = numLineas;
	}

	/**
	 * Obtiene el numero de bingos ganadores en el juego de Bingo.
	 * @return El numero de bingos ganadores.
	 */
	public int getNumBingo() {
		return numBingo;
	}

	/**
	 * Establece el numero de bingos ganadores en el juego de Bingo.
	 * @param numBingo El nuevo numero de bingos ganadores a establecer.
	 */
	public void setNumBingo(int numBingo) {
		this.numBingo = numBingo;
	}

	/**
	 * Obtiene el numero de jugadas especiales ganadoras en el juego de Bingo.
	 * @return El numero de jugadas especiales ganadoras.
	 */
	public int getNumEspeciales() {
		return numEspeciales;
	}

	/**
	 * Establece el numero de jugadas especiales ganadoras en el juego de Bingo.
	 * @param numEspeciales El nuevo numero de jugadas especiales ganadoras a establecer.
	 */
	public void setNumEspeciales(int numEspeciales) {
		this.numEspeciales = numEspeciales;
	}

	/**
	 * Obtiene la cantidad destinada al reparto de premios especiales en el juego de Bingo.
	 * @return La cantidad destinada al reparto de premios especiales.
	 */
	public float getRepartoEspeciales() {
		return repartoEspeciales;
	}

	/**
	 * Establece la cantidad destinada al reparto de premios especiales en el juego de Bingo.
	 * @param repartoEspeciales La nueva cantidad destinada al reparto de premios especiales a establecer.
	 */
	public void setRepartoEspeciales(float repartoEspeciales) {
		this.repartoEspeciales = repartoEspeciales;
		recaudacion-=repartoEspeciales;
	}

	/**
	 * Obtiene la cantidad destinada al reparto de premios por lineas en el juego de Bingo.
	 * @return La cantidad destinada al reparto de premios por lineas.
	 */
	public float getRepartoLinea() {
		return repartoLinea;
	}

	/**
	 * Establece la cantidad destinada al reparto de premios por lineas en el juego de Bingo.
	 * @param repartoLinea La nueva cantidad destinada al reparto de premios por lineas a establecer.
	 */
	public void setRepartoLinea(float repartoLinea) {
		this.repartoLinea = repartoLinea;
	}

	/**
	 * Obtiene la cantidad destinada al reparto de premios por bingos en el juego de Bingo.
	 * @return La cantidad destinada al reparto de premios por bingos.
	 */
	public float getRepartoBingo() {
		return repartoBingo;
	}

	/**
	 * Establece la cantidad destinada al reparto de premios por bingos en el juego de Bingo.
	 * @param repartoBingo La nueva cantidad destinada al reparto de premios por bingos a establecer.
	 */
	public void setRepartoBingo(float repartoBingo) {
		this.repartoBingo = repartoBingo;
	}

	/**
	 * Obtiene la cantidad de dinero acumulada en la caja del juego de Bingo.
	 * @return La cantidad de dinero en la caja.
	 */
	public float getCaja() {
		return caja;
	}

	/**
	 * Establece la cantidad de dinero en la caja del juego de Bingo.
	 * @param caja La nueva cantidad de dinero en la caja a establecer.
	 */
	public void setCaja(float caja) {
		this.caja = caja;
		recaudacion-=caja;
	}

	/**
	 * Obtiene la cantidad total de recaudacion en el juego de Bingo.
	 * @return La cantidad total de recaudacion.
	 */
	public float getRecaudacion() {
		return recaudacion;
	}

	/**
	 * Establece la cantidad total de recaudacion en el juego de Bingo.
	 * @param recaudacion La nueva cantidad total de recaudacion a establecer.
	 */
	public void setRecaudacion(float recaudacion) {
		this.recaudacion = recaudacion;
	}

	/**
     * Devuelve una cadena de texto que representa el estado actual del objeto Reparto.
     * @return Representacion en cadena de texto del objeto Reparto.
     */
	@Override
	public String toString() {
		return "Reparto [numLineas=" + numLineas + ", numBingo=" + numBingo + ", numEspeciales=" + numEspeciales
				+ ", repartoEspeciales=" + repartoEspeciales + ", repartoLinea=" + repartoLinea + ", repartoBingo="
				+ repartoBingo + ", caja=" + caja + ", recaudacion=" + recaudacion + "]";
	}

}
