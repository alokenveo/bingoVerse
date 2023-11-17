/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.unex.cum.mdp.ef2;

public interface ICarton {

	public EstadoCarton getEstado();
	public int getAciertos();
	public int getId();
	public void setId(int id);
	public Usuario getUser();
	public void setUser(Usuario user);
	public boolean comprobarLinea();
	public boolean comprobarLinea(int fila);
	public boolean recibirNumero(int numero);
	public boolean comprobarBingo();
	public boolean comprobarEspeciales();
	public boolean repartir();
	public boolean addNumero(CeldaCarton c);
	public boolean addEspecial(int num);
	public float getPrecio();
	public void setPrecio(float p);
	public void setPremio(float valor);
	public float getPremio();
	public String toString();
	public Object getNumeros();
	public void setNumeros(Object o);	
	

}

