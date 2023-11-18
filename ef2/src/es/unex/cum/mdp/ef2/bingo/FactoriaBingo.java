package es.unex.cum.mdp.ef2.bingo;

/**
 * Clase que actua como una factoria para la creacion de instancias de la clase Bingo.
 */
public class FactoriaBingo {

	private static Bingo b;
	
	/**
     * Constructor predeterminado de la clase FactoriaBingo.
     */
	public FactoriaBingo() {
		super();
	}
	
	/**
     * Asigna un identificador a la instancia de Bingo.
     *
     * @param id Identificador a asignar.
     * @return La instancia actual de FactoriaBingo.
     */
	public FactoriaBingo withId(final int id) {
		FactoriaBingo.b.setId(id);
		return this;
	}
	
	/**
     * Construye una instancia de Bingo segun el tipo especificado y el precio.
     *
     * @param tipo Tipo de Bingo a construir ("75H", "80H", "90H").
     * @param p    Precio del carton.
     * @return Instancia de la clase Bingo creada.
     */
	public static Bingo buildBingo(String tipo, float p) {
		if(tipo.equals("75H")) {
			//b=new Bingo75();
		}
		else if(tipo.equals("80H")) {
			b=new Bingo80(p);
		}
		else {
			//b=new Bingo90();
		}
		return b;
	}
}
