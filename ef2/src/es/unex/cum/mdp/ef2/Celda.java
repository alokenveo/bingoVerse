package es.unex.cum.mdp.ef2;

import java.util.*;

public abstract class Celda {
	protected int numero;
	protected EstadoCelda estado; //PREGUNTAR SI ES UN ENUMERADO O NO
	
	public Celda() {
		super();
	}
	public Celda(int numero) {
		super();
		this.numero = numero;
	}
	public Celda(int numero, int estado) {
		super();
		this.numero = numero;
		switch(estado) {
		case 0:
			this.estado=EstadoCelda.VACIO;
			break;
		case 1:
			this.estado=EstadoCelda.NUMERO;
			break;
		case 2:
			this.estado=EstadoCelda.CANTADO;
			break;
		default:
			break;
		}
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public EstadoCelda getEstado() {
		return estado;
	}
	public void setEstado(EstadoCelda estado) {
		this.estado = estado;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(estado, numero);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celda other = (Celda) obj;
		return estado == other.estado && numero == other.numero;
	}
	
	@Override
	public String toString() {
		return "Celda [numero=" + numero + ", estado=" + estado + "]";
	}
	
	

}
