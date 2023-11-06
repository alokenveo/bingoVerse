package es.unex.cum.mdp.ef2;

public class CeldaCarton extends Celda {
	private int fila;
	private int columna;
	private boolean especial;
	private String RutaImagen;
	private String RutaReverso;
	
	
	
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		this.fila = fila;
	}
	public int getColumna() {
		return columna;
	}
	public void setColumna(int columna) {
		this.columna = columna;
	}
	public boolean isEspecial() {
		return especial;
	}
	public void setEspecial(boolean especial) {
		this.especial = especial;
	}
	public String getRutaImagen() {
		return RutaImagen;
	}
	public void setRutaImagen(String rutaImagen) {
		RutaImagen = rutaImagen;
	}
	public String getRutaReverso() {
		return RutaReverso;
	}
	public void setRutaReverso(String rutaReverso) {
		RutaReverso = rutaReverso;
	}
	
	
	

}
