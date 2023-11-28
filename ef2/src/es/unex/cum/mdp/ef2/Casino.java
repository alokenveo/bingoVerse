package es.unex.cum.mdp.ef2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import es.unex.cum.mdp.ef2.bingo.Bingo;
import es.unex.cum.mdp.ef2.bingo.FactoriaBingo;
import es.unex.cum.mdp.ef2.carton.CartonBuilder;
import es.unex.cum.mdp.ef2.carton.ICarton;

public class Casino {

	protected HashMap<String, Usuario> usuarios;
	protected HashMap<Date, Bingo> bingos;
	protected Estadistica[] estadistica;

	// CONSTRUCTOR
	public Casino() {
		this.usuarios = new HashMap<String, Usuario>();
		this.bingos = new HashMap<Date, Bingo>();
		this.estadistica = new Estadistica[80+1];
		for (int i=0;i<estadistica.length;i++)
			estadistica[i]=new Estadistica(i);
	}

	public HashMap<String, Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(HashMap<String, Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public HashMap<Date, Bingo> getBingos() {
		return bingos;
	}

	public void setBingos(HashMap<Date, Bingo> bingos) {
		this.bingos = bingos;
	}

	public Estadistica[] getEstadistica() {
		return estadistica;
	}

	public void setEstadistica(Estadistica[] estadistica) {
		this.estadistica = estadistica;
	}

	public Usuario login(String nick, String password) throws UsuarioNoAutenticado {
		if (nick == null || password == null) {
			throw new UsuarioNoAutenticado();
		}

		Usuario u = usuarios.get(nick);
		if (u == null || !u.getPassword().equals(password)) {
			throw new UsuarioNoAutenticado();
		}
		return u;
	}

	public boolean register(String nick, String name, String password, float monedero) {
		if (nick == null || name == null || password == null) {
			return false;
		}
		if (existeUsuario(new Usuario(nick, name, password))) {
			return false;
		}
		usuarios.put(nick, new Usuario(nick, name, password, monedero));
		return true;
	}

	public boolean addMonedero(String nick, String password, float moneder) {
		if (nick == null || password == null) {
			return false;
		}
		try {
			login(nick, password).setMonedero(login(nick, password).getMonedero() + moneder);
			login(nick, password)
					.addMovimiento(new Movimiento("Añadir a monedero", moneder, login(nick, password).getMonedero()));
		} catch (UsuarioNoAutenticado e) {
			return false;
		}
		return true;
	}

	public Bingo crearBingo(String tipo, Date fecha, float f, float precio) {
		if (tipo == null || fecha == null) {
			return null;
		}
		Bingo b = FactoriaBingo.buildBingo(tipo, precio);
		b.setId(bingos.size() + 1);
		b.setBoteInicial(f);
		b.setFecha(fecha);
		for (Bingo existente : bingos.values()) {
			if (existente.equals(b)) {
				return null;
			}
		}
		bingos.put(fecha, b);
		return b;
	}

	public Bingo crearBingo(String tipo, Date d, float precio) {
		if (tipo == null || d == null) {
			return null;
		}
		Bingo b = FactoriaBingo.buildBingo(tipo, precio);
		b.setId(bingos.size() + 1);
		b.setFecha(d);
		for (Bingo existente : bingos.values()) {
			if (existente.equals(b)) {
				return null;
			}
		}
		bingos.put(b.getFecha(), b);
		return b;
	}

	public ICarton adherirseCarton(String nick, String password, Date f, String tipo) {
		// TODO
		if (nick == null || password == null || f == null || tipo == null) {
			return null;
		}
		Bingo b=bingos.get(f);
		if(b==null) {
			return null;
		}
		Usuario u;
		try {
			u = login(nick, password);
		} catch (UsuarioNoAutenticado e) {
			return null;
		}
		/*CartonBuilder cart = new CartonBuilder(tipo);
		ICarton c = cart.withUser(u).build();
		c.setPrecio(b.getPrecioCarton());
		if (!u.addCarton(c)) {
			return null;
		}
		b.add(c);*/
		ICarton c=b.crearCarton(tipo, u);
		return c;
	}

	public ICarton adherirseCarton(String nick, String password, String tipo) {
		// TODO
		if (nick == null || password == null || tipo == null) {
			return null;
		}
		Usuario u;
		try {
			u = login(nick, password);
		} catch (UsuarioNoAutenticado e) {
			return null;
		}
		CartonBuilder cart = new CartonBuilder(tipo);
		ICarton c = cart.withUser(u).build();
		if (!u.addCarton(c)) {
			return null;
		}
		for (Bingo b : bingos.values()) {
			b.add(c);
		}
		return c;
	}

	public boolean jugar(Date d) {
		// TODO
		Bingo b = null;

		// Seleccionamos el bingo a jugar dependiendo de la fecha y que jugado sea false
		for (Bingo bing : bingos.values()) {
			if (bing.getFecha().equals(d)) {
				if (bing.isJugado() == false) {
					b = bing;
				}
			}
		}
		if (b == null) {
			return false;
		}
		b.jugar(estadistica);
		b.setJugado(true);

		return true;
	}

	public boolean jugar(Date d, List<Integer> combinacion) {
		// TODO
		Bingo b = null;

		// Seleccionamos el bingo a jugar dependiendo de la fecha y que jugado sea false
		for (Bingo bing : bingos.values()) {
			if (bing.getFecha().equals(d)) {
				if (bing.isJugado() == false) {
					b = bing;
				}
			}
		}
		if (b == null) {
			return false;
		}
		b.jugar(estadistica,combinacion);
		b.setJugado(true);

		return true;
	}

	public Bingo consultarBingo(Date d) {
		// TODO
		return null;
	}

	public List<Usuario> verRanking() {
		// TODO
		 List<Usuario> listaUsuarios = new ArrayList<>(usuarios.values());

	        // Ordenar la lista de usuarios utilizando un comparador compuesto
	        Collections.sort(listaUsuarios, Comparator
	                .comparingInt(Usuario::getNumBingos).reversed()
	                .thenComparingInt(Usuario::getNumLineas).reversed()
	                .thenComparingInt(Usuario::getNumEspeciales).reversed());

	        return listaUsuarios;
	}

	public void verEstadistica() {
	}

	// MÉTODOS PRIVATES
	private boolean existeUsuario(Usuario u) {
		if (u == null || usuarios == null) {
			return false; // Usuario nulo o HashMap no inicializado
		}

		// Recorremos los valores del HashMap (usuarios) para verificar si existe el
		// usuario
		for (Usuario usuarioExistente : usuarios.values()) {
			if (usuarioExistente.getNick().equals(u.getNick())) {
				return true; // Usuario encontrado
			}
		}
		return false; // Usuario no encontrado
	}

}
