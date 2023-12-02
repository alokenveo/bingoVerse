package es.unex.cum.mdp.ef2.bingo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

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
	protected float boteInicial;

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
     * Obtiene la fecha del bingo.
     *
     * @return Fecha del bingo.
     */
	public Date getFecha() {
		return fecha;
	}
	
	/**
     * Establece la fecha del bingo.
     *
     * @param d Fecha a establecer.
     */
	public void setFecha(Date d) {
		this.fecha=d;
	}
	
	/**
     * Establece la recaudación del bingo.
     *
     * @param r Recaudación a establecer.
     */

	public void setRecaudacion(float r) {
		this.recaudacion=r;
	}
	
	/**
     * Obtiene los cartones asociados al bingo.
     *
     * @return Conjunto de cartones asociados al bingo.
     */
	public HashSet<ICarton> getCartones(){
		return cartones;
	}
	
	/**
     * Verifica si el bingo ha sido jugado.
     *
     * @return true si el bingo ha sido jugado, false de lo contrario.
     */
	public boolean isJugado() {
		return jugado;
	}

	/**
     * Establece el estado de jugado del bingo.
     *
     * @param jugado Estado a establecer.
     */
	public void setJugado(boolean jugado) {
		this.jugado = jugado;
	}

	/**
     * Obtiene la recaudación total del juego de Bingo.
     *
     * @return Recaudación total.
     */
	public float getRecaudacion() {
		return recaudacion;
	}
	
	/**
     * Obtiene la lista de bolas sacadas durante el bingo.
     *
     * @return Lista de bolas sacadas durante el bingo.
     */
	public ArrayList<Integer> getBolasSacadas() {
		return bolasSacadas;
	}

	/**
     * Establece la lista de bolas sacadas durante el bingo.
     *
     * @param bolasSacadas Lista de bolas a establecer.
     */
	public void setBolasSacadas(ArrayList<Integer> bolasSacadas) {
		this.bolasSacadas = bolasSacadas;
	}

	/**
     * Obtiene la bolsa de bolas asociada al bingo.
     *
     * @return Bolsa de bolas asociada al bingo.
     */
	public BolsaBingo getB() {
		return b;
	}

	/**
     * Establece la bolsa de bolas asociada al bingo.
     *
     * @param b Bolsa de bolas a establecer.
     */
	public void setB(BolsaBingo b) {
		this.b = b;
	}

	/**
     * Obtiene el reparto asociado al bingo.
     *
     * @return Reparto asociado al bingo.
     */
	public Reparto getReparto() {
		return reparto;
	}

	/**
     * Establece el reparto asociado al bingo.
     *
     * @param reparto Reparto a establecer.
     */
	public void setReparto(Reparto reparto) {
		this.reparto = reparto;
	}

	/**
     * Obtiene el bote inicial del bingo.
     *
     * @return Bote inicial del bingo.
     */
	public float getBoteInicial() {
		return boteInicial;
	}

	/**
     * Establece el bote inicial del bingo.
     *
     * @param boteInicial Bote inicial a establecer.
     */
	public void setBoteInicial(float boteInicial) {
		this.boteInicial = boteInicial;
	}

	/**
     * Establece los cartones asociados al bingo.
     *
     * @param cartones Conjunto de cartones a establecer.
     */
	public void setCartones(HashSet<ICarton> cartones) {
		this.cartones = cartones;
	}

	/**
     * Crea un nuevo cartón para un usuario y lo agrega al juego.
     *
     * @param tipo Tipo de cartón a crear.
     * @param u    Usuario al que se asignará el cartón.
     * @return El cartón creado.
     */
	public ICarton crearCarton(String tipo, Usuario u) {
		if (u.getMonedero()<this.precioCarton)
			return null;
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
	
	//-----------------------
	//MUCHA DUDA
	//-----------------------
	public boolean add(ICarton c) {
		if (c == null) {
			return false;
		}
		recaudacion=boteInicial+2*c.getPrecio(); //Preguntar por qué por 2
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
	
	/**
     * Calcula el valor hash del bingo basado en la fecha, el precio del cartón y la recaudación.
     *
     * @return Valor hash del bingo.
     */
	@Override
	public int hashCode() {
		return Objects.hash(fecha, precioCarton, recaudacion);
	}

	/**
     * Compara el bingo con otro objeto para determinar si son iguales.
     *
     * @param obj Objeto a comparar.
     * @return true si los bingos son iguales, false de lo contrario.
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bingo other = (Bingo) obj;
		return Objects.equals(fecha, other.fecha)
				&& Float.floatToIntBits(precioCarton) == Float.floatToIntBits(other.precioCarton)
				&& Float.floatToIntBits(recaudacion) == Float.floatToIntBits(other.recaudacion);
	}

}
