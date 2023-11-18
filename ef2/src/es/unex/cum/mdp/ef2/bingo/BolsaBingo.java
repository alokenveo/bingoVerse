package es.unex.cum.mdp.ef2.bingo;

import java.util.*;;

public class BolsaBingo implements Bolsa{
	private Queue<Integer> bolsa;
	private int numMaxBolas;
	
	
	public BolsaBingo(int numMaxBolas) {
		this.numMaxBolas = numMaxBolas;
		this.bolsa=new LinkedList<>();
	}
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
