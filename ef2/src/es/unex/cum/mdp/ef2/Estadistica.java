package es.unex.cum.mdp.ef2;

public class Estadistica {
	private int numero;
	private int numSacado;
	private int numEspecial;
	private int numLinea;
	private int numBingo;

	public Estadistica(int n) {
		super();
		this.numero = n;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getNumSacado() {
		return numSacado;
	}

	public void setNumSacado(int numSacado) {
		this.numSacado = numSacado;
	}

	public int getNumEspecial() {
		return numEspecial;
	}

	public void setNumEspecial(int numEspecial) {
		this.numEspecial = numEspecial;
	}

	public int getNumLinea() {
		return numLinea;
	}

	public void setNumLinea(int numLinea) {
		this.numLinea = numLinea;
	}

	public int getNumBingo() {
		return numBingo;
	}

	public void setNumBingo(int numBingo) {
		this.numBingo = numBingo;
	}

	@Override
	public String toString() {
		return "Estadistica [numero=" + numero + ", numSacado=" + numSacado + ", numEspecial=" + numEspecial
				+ ", numLinea=" + numLinea + ", numBingo=" + numBingo + "]";
	}

}
