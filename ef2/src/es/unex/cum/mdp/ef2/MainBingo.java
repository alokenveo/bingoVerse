package es.unex.cum.mdp.ef2;

import es.unex.cum.mdp.ef2.Bingo;
import es.unex.cum.mdp.ef2.Bingo75;
import es.unex.cum.mdp.ef2.Estadistica;
import es.unex.cum.mdp.ef2.Usuario;

public class MainBingo {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Estadistica[] estadistica= new Estadistica[90+1];
		for (int i=0;i<estadistica.length;i++)
			estadistica[i]=new Estadistica(i);
		Usuario u = new Usuario("l", "luis", "luis", 1000000);
		Bingo b = new Bingo75();
		for (int i = 0; i < 999; i++) {
				b.crearCarton("75M", u);
		}
			b.jugar(estadistica);
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
		
	}
}
