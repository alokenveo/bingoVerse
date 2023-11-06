package es.unex.cum.mdp.ef2;

import java.util.HashMap;

public class Carton80H implements ICarton{
	private int id;
	private Usuario user;
	private float precio;
	private float premio;
	private EstadoCarton estado;
	private HashMap<Integer,CeldaCarton> numeros;
	
	private int numeroFilas;
	private int numeroColumnas;
	private int numeroMaximo;
	private int numeroAciertosBingo;
	private int numeroAciertosLinea;
	private int numEspeciales;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public float getPremio() {
		return premio;
	}
	public void setPremio(float premio) {
		this.premio = premio;
	}
	public float getPrecio() {
		return precio;
	}
	public EstadoCarton getEstado() {
		return estado;
	}
	public Object getNumeros() {
		return numeros;
	}
	
	@Override
	public int getAciertos() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean comprobarLinea() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean comprobarLinea(int fila) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean recibirNumero(int numero) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean comprobarBingo() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean comprobarEspeciales() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean repartir() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addNumero(CeldaCarton c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addEspecial(int num) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
