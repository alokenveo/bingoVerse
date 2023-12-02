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

/**
 * La clase Casino representa un casino virtual con usuarios, bingos y
 * estadísticas. Permite la gestión de usuarios, bingos, cartones y estadísticas
 * de juego.
 * 
 * @author Alfredo Mituy Okenve
 * @version 1.0
 */
public class Casino {

	/**
	 * Mapa que almacena a los usuarios del casino. La clave es el nick del usuario.
	 */
	protected HashMap<String, Usuario> usuarios;
	/**
	 * Mapa que almacena los bingos del casino. La clave es la fecha del bingo.
	 */
	protected HashMap<Date, Bingo> bingos;
	/**
	 * Array que representa la estadística de los números en los bingos. El índice
	 * del array corresponde al número, y cada elemento es una instancia de
	 * Estadistica.
	 */
	protected Estadistica[] estadistica;

	// CONSTRUCTOR
	/**
	 * Constructor predeterminado de la clase Casino. Inicializa los mapas de
	 * usuarios y bingos, y crea un arreglo de estadísticas.
	 */
	public Casino() {
		this.usuarios = new HashMap<String, Usuario>();
		this.bingos = new HashMap<Date, Bingo>();
		this.estadistica = new Estadistica[80 + 1];
		for (int i = 0; i < estadistica.length; i++)
			estadistica[i] = new Estadistica(i);
	}

	/**
	 * Obtiene el mapa de usuarios del casino.
	 *
	 * @return Mapa de usuarios.
	 */
	public HashMap<String, Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * Establece el mapa de usuarios del casino.
	 *
	 * @param usuarios Nuevo mapa de usuarios.
	 */
	public void setUsuarios(HashMap<String, Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * Obtiene el mapa de bingos del casino.
	 *
	 * @return Mapa de bingos.
	 */
	public HashMap<Date, Bingo> getBingos() {
		return bingos;
	}

	/**
	 * Establece el mapa de bingos del casino.
	 *
	 * @param bingos Nuevo mapa de bingos.
	 */
	public void setBingos(HashMap<Date, Bingo> bingos) {
		this.bingos = bingos;
	}

	/**
	 * Obtiene el array de estadísticas del casino.
	 *
	 * @return Array de estadísticas.
	 */
	public Estadistica[] getEstadistica() {
		return estadistica;
	}

	/**
	 * Establece el array de estadísticas del casino.
	 *
	 * @param estadistica Nuevo array de estadísticas.
	 */
	public void setEstadistica(Estadistica[] estadistica) {
		this.estadistica = estadistica;
	}

	/**
	 * Realiza el proceso de inicio de sesión de un usuario en el casino.
	 *
	 * @param nick     Nickname del usuario.
	 * @param password Contraseña del usuario.
	 * @return Instancia del usuario autenticado.
	 * @throws UsuarioNoAutenticado Excepción lanzada si no se puede autenticar al
	 *                              usuario.
	 */
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

	/**
	 * Registra a un nuevo usuario en el casino.
	 *
	 * @param nick     Nickname del nuevo usuario.
	 * @param name     Nombre del nuevo usuario.
	 * @param password Contraseña del nuevo usuario.
	 * @param monedero Monto inicial en el monedero del nuevo usuario.
	 * @return `true` si el registro es exitoso, `false` en caso contrario.
	 */
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

	/**
	 * Añade monedero a un usuario existente en el casino.
	 *
	 * @param nick     Nickname del usuario.
	 * @param password Contraseña del usuario.
	 * @param moneder Monto a añadir al monedero del usuario.
	 * @return `true` si la operación es exitosa, `false` en caso contrario.
	 */
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

	/**
	 * Crea un nuevo juego de Bingo en el casino.
	 *
	 * @param tipo   Tipo de Bingo a crear ("75H", "80H", "90H").
	 * @param fecha  Fecha de inicio del juego.
	 * @param f      Monto inicial del bote del juego.
	 * @param precio Precio del cartón del juego.
	 * @return Instancia del nuevo juego de Bingo creado.
	 */
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

	/**
	 * Crea un nuevo juego de Bingo en el casino.
	 *
	 * @param tipo   Tipo de Bingo a crear ("75H", "80H", "90H").
	 * @param d      Fecha de inicio del juego.
	 * @param precio Precio del cartón del juego.
	 * @return Instancia del nuevo juego de Bingo creado.
	 */
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

	/**
	 * Permite a un usuario unirse a un cartón de Bingo en una fecha específica.
	 *
	 * @param nick     Nickname del usuario.
	 * @param password Contraseña del usuario.
	 * @param f        Fecha del juego de Bingo al que unirse.
	 * @param tipo     Tipo de cartón de Bingo.
	 * @return Instancia del cartón de Bingo al que se unió el usuario.
	 */
	public ICarton adherirseCarton(String nick, String password, Date f, String tipo) {
		if (nick == null || password == null || f == null || tipo == null) {
			return null;
		}
		Bingo b = bingos.get(f);
		if (b == null) {
			return null;
		}
		Usuario u;
		try {
			u = login(nick, password);
		} catch (UsuarioNoAutenticado e) {
			return null;
		}
		ICarton c = b.crearCarton(tipo, u);
		return c;
	}

	/**
	 * Permite a un usuario unirse a un cartón de Bingo.
	 *
	 * @param nick     Nickname del usuario.
	 * @param password Contraseña del usuario.
	 * @param tipo     Tipo de cartón de Bingo.
	 * @return Instancia del cartón de Bingo al que se unió el usuario.
	 */
	public ICarton adherirseCarton(String nick, String password, String tipo) {
	    if (nick == null || password == null || tipo == null) {
	        return null;
	    }
	    Usuario u;
	    try {
	        u = login(nick, password);
	    } catch (UsuarioNoAutenticado e) {
	        return null;
	    }
	    for (Bingo b : bingos.values()) {
	        if (b != null) {
	            ICarton c = b.crearCarton(tipo, u);
	            if (c != null) {
	                return c;
	            }
	        }
	    }
	    return null;
	}


	/**
	 * Inicia el juego de Bingo en una fecha específica.
	 *
	 * @param d Fecha del juego de Bingo a iniciar.
	 * @return `true` si el juego se inicia con éxito, `false` en caso contrario.
	 */
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

	/**
	 * Inicia el juego de Bingo en una fecha específica con una combinación de
	 * números.
	 *
	 * @param d           Fecha del juego de Bingo a iniciar.
	 * @param combinacion Combinación de números para el juego.
	 * @return `true` si el juego se inicia con éxito, `false` en caso contrario.
	 */
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
		b.jugar(estadistica, combinacion);
		b.setJugado(true);

		return true;
	}

	/**
	 * Consulta la información de un juego de Bingo en una fecha específica.
	 *
	 * @param d Fecha del juego de Bingo a consultar.
	 * @return Instancia del juego de Bingo consultado.
	 */
	public Bingo consultarBingo(Date d) {
		if (d == null) {
			return null;
		}
		Bingo b = null;
		b = bingos.get(d);
		return b;
	}

	/**
	 * Obtiene el ranking de usuarios en el casino.
	 *
	 * @return Lista de usuarios ordenada por número de bingos, líneas y especiales.
	 */
	public List<Usuario> verRanking() {
		List<Usuario> listaUsuarios = new ArrayList<>(usuarios.values());
		
		// Ordenar la lista de usuarios utilizando un comparador compuesto
		Collections.sort(listaUsuarios);
		return listaUsuarios;
	}

	// MÉTODOS PRIVATES
	/**
	 * Verifica si un usuario ya existe en el casino.
	 *
	 * @param u Usuario a verificar.
	 * @return `true` si el usuario ya existe, `false` en caso contrario.
	 */
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
