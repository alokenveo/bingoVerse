package es.unex.cum.mdp.ef2;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import es.unex.cum.mdp.ef2.carton.ICarton;

/**
 * Clase que representa a un usuario en un juego de Bingo.
 */
public class Usuario implements Comparable {
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

	/**
     * Constructor predeterminado de Usuario.
     * Inicializa las colecciones y atributos del usuario.
     */
	public Usuario() {
		super();
		this.cartones=new ArrayList<>();
		this.cartonesHist=new LinkedList<>();
		this.historico=new ArrayList<>();
	}
	
	public Usuario(String nick, String nombre, String password) {
		super();
		this.nick = nick;
		this.nombre = nombre;
		this.password = password;
		this.cartones=new ArrayList<>();
		this.cartonesHist=new LinkedList<>();
		this.historico=new ArrayList<>();
	}

	/**
     * Constructor de Usuario con parametros.
     * Inicializa un usuario con informacion especifica.
     *
     * @param nick     Nombre de usuario.
     * @param nombre   Nombre del usuario.
     * @param password Contraseña del usuario.
     * @param i        Monto inicial en el monedero del usuario.
     */
	public Usuario(String nick, String nombre, String password, float i) {
		super();
		this.nick = nick;
		this.nombre = nombre;
		this.password = password;
		this.monedero=i;
		this.cartones=new ArrayList<>();
		this.cartonesHist=new LinkedList<>();
		this.historico=new ArrayList<>();
	}

	/**
     * Obtiene el nombre de usuario.
     *
     * @return Nombre de usuario.
     */
	public String getNick() {
		return nick;
	}

	/**
     * Establece el nombre de usuario.
     *
     * @param nick Nombre de usuario a establecer.
     */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
     * Obtiene el nombre del usuario.
     *
     * @return Nombre del usuario.
     */
	public String getNombre() {
		return nombre;
	}

	/**
     * Establece el nombre del usuario.
     *
     * @param nombre Nombre del usuario a establecer.
     */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
     * Obtiene la contraseña del usuario.
     *
     * @return Contraseña del usuario.
     */
	public String getPassword() {
		return password;
	}

	/**
     * Establece la contraseña del usuario.
     *
     * @param password Contraseña a establecer.
     */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
     * Obtiene el monto en el monedero del usuario.
     *
     * @return Monto en el monedero del usuario.
     */
	public float getMonedero() {
		return monedero;
	}

	/**
     * Establece el monto en el monedero del usuario.
     *
     * @param add Monto a establecer en el monedero.
     */
	public void setMonedero(float add) {
		this.monedero = add;
	}

	/**
     * Obtiene la cantidad de bingos ganados por el usuario.
     *
     * @return Cantidad de bingos ganados.
     */
	public int getNumBingos() {
		return numBingos;
	}

	/**
     * Establece la cantidad de bingos ganados por el usuario.
     *
     * @param numBingos Cantidad de bingos a establecer.
     */

	public void setNumBingos(int numBingos) {
		this.numBingos = numBingos;
	}

	/**
     * Obtiene la cantidad de lineas ganadas por el usuario.
     *
     * @return Cantidad de lineas ganadas.
     */
	public int getNumLineas() {
		return numLineas;
	}

	/**
     * Establece la cantidad de lineas ganadas por el usuario.
     *
     * @param numLineas Cantidad de lineas a establecer.
     */
	public void setNumLineas(int numLineas) {
		this.numLineas = numLineas;
	}

	/**
     * Obtiene la cantidad de especiales ganados por el usuario.
     *
     * @return Cantidad de especiales ganados.
     */
	public int getNumEspeciales() {
		return numEspeciales;
	}

	/**
     * Establece la cantidad de especiales ganados por el usuario.
     *
     * @param numEspeciales Cantidad de especiales a establecer.
     */
	public void setNumEspeciales(int numEspeciales) {
		this.numEspeciales = numEspeciales;
	}

	/**
     * Obtiene la lista de cartones del usuario.
     *
     * @return Lista de cartones del usuario.
     */
	public List<ICarton> getCartones() {
		return cartones;
	}

	/**
     * Establece la lista de cartones del usuario.
     *
     * @param cartones Lista de cartones a establecer.
     */

	public void setCartones(List<ICarton> cartones) {
		this.cartones = cartones;
	}

	/**
     * Obtiene la cola de cartones historicos del usuario.
     *
     * @return Cola de cartones historicos del usuario.
     */
    public Queue<ICarton> getCartonesHist() {
        return cartonesHist;
    }

    /**
     * Establece la cola de cartones historicos del usuario.
     *
     * @param cartonesHist Cola de cartones historicos a establecer.
     */
    public void setCartonesHist(Queue<ICarton> cartonesHist) {
        this.cartonesHist = cartonesHist;
    }

    /**
     * Obtiene el historial de movimientos del usuario.
     *
     * @return Historial de movimientos del usuario.
     */
    public List<Movimiento> getHistorico() {
        return historico;
    }

    /**
     * Establece el historial de movimientos del usuario.
     *
     * @param historico Historial de movimientos a establecer.
     */
    public void setHistorico(List<Movimiento> historico) {
        this.historico = historico;
    }

    /**
     * Representacion en cadena del objeto Usuario.
     *
     * @return Cadena que representa al objeto Usuario.
     */
    @Override
    public String toString() {
        return "Usuario [nick=" + nick + ", nombre=" + nombre + ", password=" + password + ", monedero=" + monedero
                + ", numBingos=" + numBingos + "]";
    }

	/**
     * Añade un carton al usuario y realiza el correspondiente movimiento financiero.
     * @param c Carton a agregar.
     * @return true si se agrega con exito, false de lo contrario.
     */
	public boolean addCarton(ICarton c) {
		if (c == null|| monedero<c.getPrecio()||monedero==0.0F) {
			return false;
		}
		c.setId(cartones.size()+1);
		addMovimiento(new Movimiento("Comprar carton",c.getPrecio(),monedero,new Date()));
		monedero-=c.getPrecio();
		return cartones.add(c);
	}

	/**
     * Añade un carton historico al usuario.
     * @param c Carton historico a agregar.
     * @return true si se agrega con exito, false de lo contrario.
     */
	public boolean addCartonHistorico(ICarton c) {
		if (c == null) {
			return false;
		}
		c.setId(cartonesHist.size()+1);
		return cartonesHist.add(c);
	}

	/**
     * Añade un movimiento financiero al historial del usuario.
     * @param m Movimiento financiero a agregar.
     * @return true si se agrega con exito, false de lo contrario.
     */
	public boolean addMovimiento(Movimiento m) {
		if (m == null) {
			return false;
		}
		return historico.add(m);
	}

	@Override
	public int compareTo(Object aux) {
		Usuario o=(Usuario)aux;
		if(this.getNumBingos()<o.getNumBingos())
			return 5;
		else if(this.getNumBingos()>o.getNumBingos())
			return -1;
		else {
			//comprobar linea
			if(this.getNumLineas()<o.getNumLineas())
				return 5;
			else if(this.getNumLineas()>o.getNumLineas())
				return -1;
			else {
				if(this.getNumEspeciales()<o.getNumEspeciales())
					return 5;
				else if(this.getNumEspeciales()>o.getNumEspeciales())
					return -1;
				else return 0;
			}
		}
	}

}
