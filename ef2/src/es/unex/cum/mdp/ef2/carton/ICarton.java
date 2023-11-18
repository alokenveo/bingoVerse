package es.unex.cum.mdp.ef2.carton;

import es.unex.cum.mdp.ef2.*;
import es.unex.cum.mdp.ef2.celda.*;

/**
 * Interfaz que define el contrato para un carton en el juego de Bingo.
 */
public interface ICarton {

    /**
     * Obtiene el estado actual del carton.
     *
     * @return Estado actual del carton.
     */
    public EstadoCarton getEstado();

    /**
     * Obtiene la cantidad de aciertos en el carton.
     *
     * @return Cantidad de aciertos en el carton.
     */
    public int getAciertos();

    /**
     * Obtiene el identificador unico del carton.
     *
     * @return Identificador unico del carton.
     */
    public int getId();

    /**
     * Establece el identificador unico del carton.
     *
     * @param id Identificador unico del carton.
     */
    public void setId(int id);

    /**
     * Obtiene el usuario asociado al carton.
     *
     * @return Usuario asociado al carton.
     */
    public Usuario getUser();

    /**
     * Establece el usuario asociado al carton.
     *
     * @param user Usuario asociado al carton.
     */
    public void setUser(Usuario user);

    /**
     * Comprueba si el carton tiene una linea completa.
     *
     * @return true si el carton tiene una linea completa, false en caso contrario.
     */
    public boolean comprobarLinea();

    /**
     * Comprueba si el carton tiene una linea completa en una fila especifica.
     *
     * @param fila Fila en la que se verifica la linea.
     * @return true si el carton tiene una linea completa en la fila especificada, false en caso contrario.
     */
    public boolean comprobarLinea(int fila);

    /**
     * Recibe un numero y actualiza el estado del carton en funcion de este.
     *
     * @param numero Numero recibido.
     * @return true si el numero afecta al carton, false en caso contrario.
     */
    public boolean recibirNumero(int numero);

    /**
     * Comprueba si el carton tiene un bingo completo.
     *
     * @return true si el carton tiene un bingo completo, false en caso contrario.
     */
    public boolean comprobarBingo();

    /**
     * Comprueba si el carton tiene algun numero especial.
     *
     * @return true si el carton tiene algun numero especial, false en caso contrario.
     */
    public boolean comprobarEspeciales();

    /**
     * Reparte los numeros en el carton.
     *
     * @return true si el reparto se realiza con exito, false en caso contrario.
     */
    public boolean repartir();

    /**
     * Añade un numero a una celda del carton.
     *
     * @param c Celda en la que se añadira el numero.
     * @return true si el numero se añade con exito, false en caso contrario.
     */
    public boolean addNumero(CeldaCarton c);

    /**
     * Añade un numero especial al carton.
     *
     * @param num Numero especial a añadir.
     * @return true si el numero especial se añade con exito, false en caso contrario.
     */
    public boolean addEspecial(int num);

    /**
     * Obtiene el precio del carton.
     *
     * @return Precio del carton.
     */
    public float getPrecio();

    /**
     * Establece el precio del carton.
     *
     * @param p Precio del carton.
     */
    public void setPrecio(float p);

    /**
     * Establece el premio obtenido por el carton.
     *
     * @param valor Premio obtenido por el carton.
     */
    public void setPremio(float valor);

    /**
     * Obtiene el premio obtenido por el carton.
     *
     * @return Premio obtenido por el carton.
     */
    public float getPremio();

    /**
     * Representacion en formato de cadena del carton.
     *
     * @return Cadena que representa el carton.
     */
    public String toString();

    /**
     * Obtiene los numeros del carton.
     *
     * @return Numeros del carton.
     */
    public Object getNumeros();

    /**
     * Establece los numeros del carton.
     *
     * @param o Numeros del carton.
     */
    public void setNumeros(Object o);

}

