package es.unex.cum.mdp.ef2.bingo;

import java.util.*;;

/**
 * Clase que implementa la interfaz Bolsa y representa una bolsa de Bingo
 */
public class BolsaBingo implements Bolsa{
	private Queue<Integer> bolsa;
	private int numMaxBolas;
	
	/**
	 * Constructor por defecto que crea la bolsa de numeros del Bingo
	 */
	public BolsaBingo() {
		super();
		this.bolsa=new LinkedList<>();
		for(int i=1;i<=80;i++) {
			addNumero(i);
		}
	}
	/**
     * Constructor que inicializa la bolsa con un numero maximo de bolas.
     *
     * @param numMaxBolas Numero maximo de bolas en la bolsa.
     */
	public BolsaBingo(int numMaxBolas) {
		this.numMaxBolas = numMaxBolas;
		this.bolsa=new LinkedList<>();
		for(int i=1;i<=numMaxBolas;i++) {
			addNumero(i);
		}
		
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
		Collections.sort((LinkedList)bolsa);
		
	}
	@Override
	public void desordenar() {
		Collections.shuffle((LinkedList)bolsa);
		
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
