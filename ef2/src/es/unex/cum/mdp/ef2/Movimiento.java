package es.unex.cum.mdp.ef2;

import java.util.*;

public class Movimiento {
	protected String info;
	protected float monederoActual;
	protected float valor;
	protected Date fecha;
	
	
	public Movimiento() {
		super();
	}
	public Movimiento(String info, float valor, Date fecha) {
		super();
		this.info = info;
		this.valor = valor;
		this.fecha = fecha;
	}
	public Movimiento(String info, float monederoActual, float valor, Date fecha) {
		super();
		this.info = info;
		this.monederoActual = monederoActual;
		this.valor = valor;
		this.fecha = fecha;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public float getMonederoActual() {
		return monederoActual;
	}
	public void setMonederoActual(float monederoActual) {
		this.monederoActual = monederoActual;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@Override
	public int hashCode() {
		return Objects.hash(fecha, info, monederoActual, valor);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimiento other = (Movimiento) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(info, other.info)
				&& Float.floatToIntBits(monederoActual) == Float.floatToIntBits(other.monederoActual)
				&& Float.floatToIntBits(valor) == Float.floatToIntBits(other.valor);
	}
	@Override
	public String toString() {
		return "Movimiento [info=" + info + ", monederoActual=" + monederoActual + ", valor=" + valor + ", fecha="
				+ fecha + "]";
	}
}
