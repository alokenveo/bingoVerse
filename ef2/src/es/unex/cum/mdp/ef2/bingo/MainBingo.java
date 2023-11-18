package es.unex.cum.mdp.ef2.bingo;

import es.unex.cum.mdp.ef2.Estadistica;
import es.unex.cum.mdp.ef2.Usuario;

/**
 * Clase principal que contiene el metodo main para ejecutar el juego de Bingo.
 */
public class MainBingo {
	
	/**
     * Metodo principal para ejecutar el juego de Bingo.
     * @param args Argumentos de la linea de comandos (no utilizados en este caso).
     */
	public static void main(String[] args) {
		String tipo="80H";
		long startTime = System.currentTimeMillis();
		Estadistica[] estadistica= new Estadistica[90+1];
		for (int i=0;i<estadistica.length;i++)
			estadistica[i]=new Estadistica(i);
		Usuario u = new Usuario("l", "luis", "luis", 1000000);
		Usuario u1 = new Usuario("l", "luis2", "luis2", 1000000);
		Usuario u2 = new Usuario("l", "luis3", "luis3", 1000000);
		Bingo b=null;
		//Sin la factoria hecha
		/*
		if (tipo.contains("75")) {
			//b = new Bingo75(2.0F); //Precio del cartón
		}else if (tipo.contains("80")) {
			b = new Bingo80(2.0F); //Precio del cartón
		}else {
			//b = new Bingo90(2.0F); //Precio del cartón
		}
		*/
		
		//Con la factoría hecha. 
		b=FactoriaBingo.buildBingo(tipo, 2.0F); //Dos euros el cartón

		for (int i = 0; i < 1; i++) {
				b.crearCarton(tipo, u);
				b.crearCarton(tipo, u1);
				b.crearCarton(tipo, u2);
		}
		b.jugar(estadistica);
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
		
	}
}