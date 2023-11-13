package es.unex.cum.mdp.ef2;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class Bingo {
	protected int id;
	protected Date fecha;
	protected float recaudacion;
	protected boolean jugado=false;
	protected HashSet<ICarton> cartones;
	protected ArrayList<Integer> bolasSacadas;
	protected BolsaBingo b;
	protected Reparto reparto;

}
