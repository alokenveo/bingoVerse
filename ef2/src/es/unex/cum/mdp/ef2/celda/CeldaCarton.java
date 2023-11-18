package es.unex.cum.mdp.ef2.celda;

/**
 * Clase que representa una celda especifica en un carton de juego.
 */
public class CeldaCarton extends Celda {
	/**
	 * Fila de la celda en el carton.
	 */
	private int fila;
	
	/**
	 * Columna de la celda en el carton.
	 */
	private int columna;
	
	/**
	 * Indica si la celda es especial.
	 */
	private boolean especial;
	
	/**
	 * Ruta de la imagen asociada a la celda.
	 */
	private String RutaImagen;
	
	/**
	 * Ruta de la imagen en reverso asociada a la celda.
	 */
	private String RutaReverso;

	/**
     * Constructor que establece el numero y el estado de la celda en el carton.
     *
     * @param numero Numero de la celda.
     * @param estado Estado de la celda (VACIO, NUMERO, CANTADO).
     */
	public CeldaCarton(int numero, int estado) {
		super(numero, estado);
	}

	/**
     * Constructor que establece el numero, estado, fila y columna de la celda en el carton.
     *
     * @param numero  Numero de la celda.
     * @param estado  Estado de la celda (VACIO, NUMERO, CANTADO).
     * @param fila    Fila de la celda en el carton.
     * @param columna Columna de la celda en el carton.
     */
	public CeldaCarton(int numero, int estado, int fila, int columna) {
		super(numero, estado);
		this.fila = fila;
		this.columna = columna;
	}

	/**
     * Obtiene la fila de la celda en el carton.
     *
     * @return Fila de la celda.
     */

	public int getFila() {
		return fila;
	}

	/**
	* Establece la fila de la celda en el carton.
    *
    * @param fila Fila de la celda
    */
	public void setFila(int fila) {
		this.fila = fila;
	}

	/**
     * Obtiene la columna de la celda en el carton.
     *
     * @return Columna de la celda.
     */
	public int getColumna() {
		return columna;
	}

	/**
	* Establece la columna de la celda en el carton.
    *
    * @param columna Columna de la celda
    */
	public void setColumna(int columna) {
		this.columna = columna;
	}

	/**
	 * Indica si la celda es especial en el carton.
	 *
	 * @return true si la celda es especial, false en caso contrario.
	 */
	public boolean isEspecial() {
	    return especial;
	}

	/**
	 * Establece si la celda es especial en el carton.
	 *
	 * @param especial true si la celda es especial, false en caso contrario.
	 */
	public void setEspecial(boolean especial) {
	    this.especial = especial;
	}

	/**
	 * Obtiene la ruta de la imagen asociada a la celda en el carton.
	 *
	 * @return Ruta de la imagen.
	 */
	public String getRutaImagen() {
	    return RutaImagen;
	}

	/**
	 * Establece la ruta de la imagen asociada a la celda en el carton.
	 *
	 * @param rutaImagen Ruta de la imagen.
	 */
	public void setRutaImagen(String rutaImagen) {
	    RutaImagen = rutaImagen;
	}

	/**
	 * Obtiene la ruta de la imagen en reverso asociada a la celda en el carton.
	 *
	 * @return Ruta de la imagen en reverso.
	 */
	public String getRutaReverso() {
	    return RutaReverso;
	}

	/**
	 * Establece la ruta de la imagen en reverso asociada a la celda en el carton.
	 *
	 * @param rutaReverso Ruta de la imagen en reverso.
	 */
	public void setRutaReverso(String rutaReverso) {
	    RutaReverso = rutaReverso;
	}

	/**
	 * Representacion en formato de cadena de la celda en el carton.
	 *
	 * @return Cadena que representa la celda en el carton.
	 */
	@Override
	public String toString() {
		return "CeldaCarton [fila=" + fila + ", columna=" + columna + ", especial=" + especial + "]";
	}
}
