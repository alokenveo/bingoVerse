package es.unex.cum.mdp.ef2.bingo;

/**
 * Clase que actua como una factoria para la creacion de instancias de la clase
 * Bingo.
 */
public class FactoriaBingo {
	public static Bingo buildBingo(String tipo, float p) {
		Bingo b = null;
		if (tipo.equals("75H")) {
			// b=new Bingo75();
		} else if (tipo.equals("80H")) {
			b = new Bingo80(p);
		} else {
			// b=new Bingo90();
		}
		return b;
	}
}
