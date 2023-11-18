package es.unex.cum.mdp.ef2.bingo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import es.unex.cum.mdp.ef2.Estadistica;
import es.unex.cum.mdp.ef2.Usuario;
import es.unex.cum.mdp.ef2.carton.CartonBuilder;
import es.unex.cum.mdp.ef2.carton.ICarton;

/**
 * Clase abstracta que define la estructura general de un juego de Bingo.
 */
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

	/**
     * Constructor predeterminado de la clase Bingo.
     */
	public Bingo() {
		super();
	}
	
	/**
     * Constructor que inicializa el precio del cartón y otras propiedades.
     *
     * @param f Precio del cartón.
     */
	public Bingo(float f) {
		super();
		this.precioCarton=f;
		this.fecha=new Date();
		this.cartones=new HashSet<>();
		this.bolasSacadas=new ArrayList<Integer>();
		this.reparto=new Reparto();
	}
	
	/**
     * Obtiene el identificador del juego de Bingo.
     *
     * @return Identificador del Bingo.
     */
	public int getId() {
		return id;
	}
	/**
     * Establece el identificador del juego de Bingo.
     *
     * @param id Nuevo identificador del Bingo.
     */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
     * Obtiene el precio del cartón para este juego de Bingo.
     *
     * @return Precio del cartón.
     */
	public float getPrecioCarton() {
		return precioCarton;
	}
	
	/**
     * Establece el precio del cartón para este juego de Bingo.
     *
     * @param precioCarton Nuevo precio del cartón.
     */
	public void setPrecioCarton(float precioCarton) {
		this.precioCarton = precioCarton;
	}
	
	/**
     * Obtiene la recaudación total del juego de Bingo.
     *
     * @return Recaudación total.
     */
	public int getRecaudacion() {
		return (int) recaudacion;
	}
	
	/**
     * Crea un nuevo cartón para un usuario y lo agrega al juego.
     *
     * @param tipo Tipo de cartón a crear.
     * @param u    Usuario al que se asignará el cartón.
     * @return El cartón creado.
     */
	public ICarton crearCarton(String tipo, Usuario u) {
		CartonBuilder builder = new CartonBuilder(tipo);
		ICarton carton = builder.withUser(u).build();
		carton.setPrecio(precioCarton);
		u.addCarton(carton);
		add(carton);
		return carton;
	}

	/**
     * Agrega un cartón al juego.
     *
     * @param c Cartón a agregar.
     * @return `true` si se agrega con éxito, `false` si el cartón es nulo.
     */
	public boolean add(ICarton c) {
		if (c == null) {
			return false;
		}
		return cartones.add(c);
	}
	
	/**
     * Método abstracto que define el proceso de juego del Bingo.
     *
     * @param estadistica Arreglo de estadísticas para el juego.
     * @return Resultados del juego en un objeto de tipo Reparto.
     */
	public abstract Reparto jugar(Estadistica[] estadistica);
	
	/**
     * Método abstracto que define el proceso de juego del Bingo con números específicos.
     *
     * @param estadistica Arreglo de estadísticas para el juego.
     * @param numeros     Lista de números específicos para el juego.
     * @return Resultados del juego en un objeto de tipo Reparto.
     */
	public abstract Reparto jugar(Estadistica[] estadistica,List<Integer> numeros);
	
	@Override
	/**
     * Representación en cadena de texto del objeto Bingo.
     *
     * @return Cadena de texto que representa el objeto.
     */
	public String toString() {
		return "Bingo [id=" + id + ", fecha=" + fecha + ", precioCarton=" + precioCarton + ", recaudacion="
				+ recaudacion + "]";
	}

}
