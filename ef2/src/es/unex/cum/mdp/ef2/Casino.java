package es.unex.cum.mdp.ef2;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import es.unex.cum.mdp.ef2.bingo.Bingo;
import es.unex.cum.mdp.ef2.carton.ICarton;

public class Casino {

	protected HashMap<String, Usuario> usuarios;
	protected HashMap<Integer, Bingo> bingos;
	protected Estadistica[] estadistica;

	// CONSTRUCTOR
	public Casino() {
		// TODO Hacer el constructor
	}

	public Usuario login(String nick, String password) {
		// TODO
		return null;
	}

	public boolean register(String nick, String name, String password, float monedero) {
		// TODO
		return false;
	}

	public boolean addMonedero(String nick, String password, float moneder) {
		// TODO
		return false;
	}

	public Bingo crearBingo(String tipo, Date d, float bote) {
		// TODO
		return null;
	}

	public ICarton adherirseBingo(String nick, String password, String tipo) {
		// TODO
		return null;
	}

	public boolean jugar(Date d) {
		// TODO
		return false;
	}

	public boolean jugar(Date d, List<Integer> combinacion) {
		// TODO
		return false;
	}

	public Bingo consultarBingo(Date d) {
		// TODO
		return null;
	}

	public Integer[] verRanking() {
		// TODO
		return null;
	}

	public void verEstadistica() {
	}

}
