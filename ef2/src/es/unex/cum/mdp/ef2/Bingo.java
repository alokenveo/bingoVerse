package es.unex.cum.mdp.ef2;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public abstract class Bingo {
	protected int id;
	protected Date fecha;
	protected float precioCarton; 
	protected float recaudacion;
	protected boolean jugado = false;
	protected HashSet<ICarton> cartones;
	protected ArrayList<Integer> bolasSacadas;
	protected BolsaBingo b;
	protected Reparto reparto;

	public Bingo() {
		super();
	}
	public Bingo(float f) {
		super();
		this.precioCarton=f;
		this.fecha=new Date();
		this.cartones=new HashSet<>();
		this.bolasSacadas=new ArrayList<Integer>();
		this.reparto=new Reparto();
	}
	public float getPrecioCarton() {
		return precioCarton;
	}
	public void setPrecioCarton(float precioCarton) {
		this.precioCarton = precioCarton;
	}
	public int getRecaudacion() {
		return (int) recaudacion;
	}
	public ICarton crearCarton(String tipo, Usuario u) {
		CartonBuilder builder = new CartonBuilder(tipo);
		ICarton carton = builder.withUser(u).build();
		u.addCarton(carton);
		add(carton);
		return carton;
	}

	public boolean add(ICarton c) {
		if (c == null) {
			return false;
		}
		return cartones.add(c);
	}
	public abstract Reparto jugar(Estadistica[] estadistica);
	public abstract Reparto jugar(Estadistica[] estadistica,List<Integer> numeros);
	@Override
	public String toString() {
		return "Bingo [id=" + id + ", fecha=" + fecha + ", precioCarton=" + precioCarton + ", recaudacion="
				+ recaudacion + "]";
	}

}
