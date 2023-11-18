package es.unex.cum.mdp.ef2;

import java.util.*;

public class Usuario {
	protected String nick;
	protected String nombre;
	protected String password;
	protected float monedero;
	protected int numBingos;
	protected int numLineas;
	protected int numEspeciales;
	protected List<ICarton> cartones;
	protected Queue<ICarton> cartonesHist;

	protected List<Movimiento> historico;

	public Usuario() {
		super();
		this.cartones=new ArrayList<>();
		this.cartonesHist=new LinkedList<>();
		this.historico=new ArrayList<>();
	}

	public Usuario(String nick, String nombre, String password, int i) {
		super();
		this.nick = nick;
		this.nombre = nombre;
		this.password = password;
		this.monedero=i;
		this.cartones=new ArrayList<>();
		this.cartonesHist=new LinkedList<>();
		this.historico=new ArrayList<>();
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getMonedero() {
		return monedero;
	}

	public void setMonedero(float add) {
		this.monedero = add;
	}

	public int getNumBingos() {
		return numBingos;
	}

	public void setNumBingos(int numBingos) {
		this.numBingos = numBingos;
	}

	public int getNumLineas() {
		return numLineas;
	}

	public void setNumLineas(int numLineas) {
		this.numLineas = numLineas;
	}

	public int getNumEspeciales() {
		return numEspeciales;
	}

	public void setNumEspeciales(int numEspeciales) {
		this.numEspeciales = numEspeciales;
	}

	public List<ICarton> getCartones() {
		return cartones;
	}

	public void setCartones(List<ICarton> cartones) {
		this.cartones = cartones;
	}

	public Queue<ICarton> getCartonesHist() {
		return cartonesHist;
	}

	public void setCartonesHist(Queue<ICarton> cartonesHist) {
		this.cartonesHist = cartonesHist;
	}

	public List<Movimiento> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Movimiento> historico) {
		this.historico = historico;
	}

	@Override
	public String toString() {
		return "Usuario [nick=" + nick + ", nombre=" + nombre + ", password=" + password + ", monedero=" + monedero
				+ ", numBingos=" + numBingos + "]";
	}

	public boolean addCarton(ICarton c) {
		if (c == null|| monedero<c.getPrecio()) {
			return false;
		}
		monedero-=c.getPrecio();
		c.setId(cartones.size()+1);
		addMovimiento(new Movimiento("Comprar carton",c.getPrecio(),monedero));
		return cartones.add(c);
	}

	public boolean addCartonHistorico(ICarton c) {
		if (c == null) {
			return false;
		}
		c.setId(cartonesHist.size()+1);
		return cartonesHist.add(c);
	}

	public boolean addMovimiento(Movimiento m) {
		if (m == null) {
			return false;
		}
		return historico.add(m);
	}

}
