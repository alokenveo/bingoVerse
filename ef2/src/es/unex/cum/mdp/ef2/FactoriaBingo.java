package es.unex.cum.mdp.ef2;

public class FactoriaBingo {

	private static Bingo b;
	public FactoriaBingo() {
		super();
	}
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
