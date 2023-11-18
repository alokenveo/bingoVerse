package es.unex.cum.mdp.ef2;

public class Reparto {
	private int numLineas;
	private int numBingo;
	private int numEspeciales;
	private float repartoEspeciales;
	private float repartoLinea;
	private float repartoBingo;
	private float caja;
	private float recaudacion;

	public Reparto() {
		super();
	}

	public Reparto(float recaudacion2) {
		this.recaudacion=recaudacion2;
	}

	public int getNumLineas() {
		return numLineas;
	}

	public void setNumLineas(int numLineas) {
		this.numLineas = numLineas;
	}

	public int getNumBingo() {
		return numBingo;
	}

	public void setNumBingo(int numBingo) {
		this.numBingo = numBingo;
	}

	public int getNumEspeciales() {
		return numEspeciales;
	}

	public void setNumEspeciales(int numEspeciales) {
		this.numEspeciales = numEspeciales;
	}

	public float getRepartoEspeciales() {
		return repartoEspeciales;
	}

	public void setRepartoEspeciales(float repartoEspeciales) {
		this.repartoEspeciales = repartoEspeciales;
		recaudacion-=repartoEspeciales;
	}

	public float getRepartoLinea() {
		return repartoLinea;
	}

	public void setRepartoLinea(float repartoLinea) {
		this.repartoLinea = repartoLinea;
	}

	public float getRepartoBingo() {
		return repartoBingo;
	}

	public void setRepartoBingo(float repartoBingo) {
		this.repartoBingo = repartoBingo;
	}

	public float getCaja() {
		return caja;
	}

	public void setCaja(float caja) {
		this.caja = caja;
		recaudacion-=caja;
	}

	public float getRecaudacion() {
		return recaudacion;
	}

	public void setRecaudacion(float recaudacion) {
		this.recaudacion = recaudacion;
	}

	@Override
	public String toString() {
		return "Reparto [numLineas=" + numLineas + ", numBingo=" + numBingo + ", numEspeciales=" + numEspeciales
				+ ", repartoEspeciales=" + repartoEspeciales + ", repartoLinea=" + repartoLinea + ", repartoBingo="
				+ repartoBingo + ", caja=" + caja + ", recaudacion=" + recaudacion + "]";
	}

}
