package es.unex.cum.mdp.ef2.bingo;

import java.util.*;;

/**
 * Clase que implementa la interfaz Bolsa y representa una bolsa de Bingo
 */
public class BolsaBingo implements Bolsa{
	private Queue<Integer> bolsa;
	private int numMaxBolas;
	
	/**
     * Constructor que inicializa la bolsa con un numero maximo de bolas.
     *
     * @param numMaxBolas Numero maximo de bolas en la bolsa.
     */
	public BolsaBingo(int numMaxBolas) {
		this.numMaxBolas = numMaxBolas;
		this.bolsa=new LinkedList<>();
	}
	
	/**
     * Agrega un numero a la bolsa si aun no se alcanzo el numero maximo de bolas.
     *
     * @param bola Numero a agregar.
     */
	@Override
	public void addNumero(int bola) {
		if (bolsa.size() < numMaxBolas) {
            bolsa.add(bola);
        }
		
	}
	@Override
	public Queue<Integer> getBolsa() {
		return bolsa;
	}
	
	/**
     * Representacion en cadena de texto del objeto BolsaBingo.
     *
     * @return Cadena de texto que representa el objeto.
     */
	@Override
	public String toString() {
		return "BolsaBingo [bolsa=" + bolsa + ", numMaxBolas=" + numMaxBolas + "]";
	}
	@Override
	public void ordenar() {
		LinkedList<Integer> listaOrdenada = new LinkedList<>(bolsa);
        listaOrdenada.sort(Integer::compareTo);
        bolsa.clear();
        bolsa.addAll(listaOrdenada);
		
	}
	@Override
	public void desordenar() {
		LinkedList<Integer> listaDesordenada = new LinkedList<>(bolsa);
        Collections.shuffle(listaDesordenada);
        bolsa.clear();
        bolsa.addAll(listaDesordenada);
		
	}
	@Override
	public int getTotalBolas() {
		return bolsa.size();
	}
	@Override
	public boolean bolsaVacia() {
		return bolsa.isEmpty();
	}
	@Override
	public Object sacarBola() {
		return bolsa.poll();
	}
	@Override
	public Object mostrarPrimero() {
		return bolsa.peek();
	}

}
