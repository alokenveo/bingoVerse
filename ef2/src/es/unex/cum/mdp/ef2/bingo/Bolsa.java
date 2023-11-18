package es.unex.cum.mdp.ef2.bingo;

import java.util.*;

public interface Bolsa {
	public void addNumero(int bola);
	public Queue getBolsa();
	public void ordenar();
	public void desordenar();
	public int getTotalBolas();
	public boolean bolsaVacia();
	public Object sacarBola();
	public Object mostrarPrimero();

}
