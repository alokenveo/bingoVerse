package es.unex.cum.mdp.ef2.bingo;

/**
 * Clase que representa una fábrica de objetos Bingo.
 */
public class FactoriaBingo {
	/**
     * Construye un objeto Bingo basado en el tipo especificado y el precio del cartón.
     *
     * @param tipo Tipo de bingo a construir ("75H", "80H", u otro).
     * @param p    Precio del cartón.
     * @return Objeto Bingo construido.
     */
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
