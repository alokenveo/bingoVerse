package es.unex.cum.mdp.ef2.bingo;

import java.util.*;

/**
 * Interfaz que define las operaciones basicas de una bolsa de numeros.
 */
public interface Bolsa {

	/**
	 * Agrega un numero a la bolsa.
	 *
	 * @param bola Numero a agregar.
	 */
	public void addNumero(int bola);

	/**
	 * Obtiene la bolsa de numeros.
	 *
	 * @return Bolsa de numeros.
	 */
	public Queue<Integer> getBolsa();

	/**
	 * Ordena la bolsa de numeros.
	 */
	public void ordenar();

	/**
	 * Desordena la bolsa de numeros.
	 */
	public void desordenar();

	/**
	 * Obtiene el total de bolas en la bolsa.
	 *
	 * @return Total de bolas en la bolsa.
	 */
	public int getTotalBolas();

	/**
	 * Verifica si la bolsa esta vacia.
	 *
	 * @return `true` si la bolsa esta vacia, `false` si contiene al menos una bola.
	 */
	public boolean bolsaVacia();

	/**
	 * Saca una bola de la bolsa.
	 *
	 * @return Bola extraida de la bolsa.
	 */
	public Object sacarBola();

	/**
	 * Muestra el primer elemento de la bolsa sin extraerlo.
	 *
	 * @return Primer elemento de la bolsa.
	 */
	public Object mostrarPrimero();

}
