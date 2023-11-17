package es.unex.cum.mdp.ef2;

import java.util.Iterator;
import java.util.List;

public class Bingo80 extends Bingo {

	public Bingo80() {
		super();
	}

	public Bingo80(float f) {
		super(f);
		this.b = new BolsaBingo(80);
	}

	@Override
	public Reparto jugar(Estadistica[] estadistica) {
		// TODO
		// Generamos una bolsa aleatoria
		for (int i = 1; i <= 80; i++) {
			b.addNumero(i);
		}
		b.desordenar();

		// La recaudación del Bingo será el dinero obtenido de la venta de cartones
		recaudacion = cartones.size() * 2;
		Reparto r = new Reparto(recaudacion);

		int cont = 0, numero;
		boolean bingo = false;

		while (bingo == false || b.bolsaVacia()) {
			numero = (int) b.sacarBola();
			bolasSacadas.add(numero);

			estadistica[cont] = new Estadistica(numero);

			Iterator<ICarton> it = cartones.iterator();
			boolean especial = false;
			boolean linea = false;

			while (it.hasNext()) {
				ICarton c = it.next();
				c.recibirNumero(numero);
				if (linea == false) {
					if (c.comprobarLinea()) {
						r.setNumLineas(r.getNumLineas() + 1);
						c.getUser().setNumLineas(c.getUser().getNumLineas() + 1);
						linea = true;
					}
				}
				if (especial == false) {
					if (c.comprobarEspeciales()) {
						r.setNumEspeciales(r.getNumEspeciales() + 1);
						c.getUser().setNumEspeciales(c.getUser().getNumEspeciales() + 1);
						especial = true;
					}
				}
				if (c.comprobarBingo()) {
					r.setNumBingo(r.getNumBingo() + 1);
					c.getUser().setNumBingos(c.getUser().getNumBingos() + 1);
					bingo = true;
				}
			}
			cont++;
		}
		r.setCaja((float) (recaudacion * 0.1));
		r.setRepartoEspeciales(r.getNumEspeciales() * 2);
		r.setRepartoLinea((float) (recaudacion * 0.3));
		r.setRepartoBingo((float) (recaudacion * 0.6));
		return r;
	}

	@Override
	public Reparto jugar(Estadistica[] estadistica, List<Integer> numeros) {
		// TODO Auto-generated method stub
		recaudacion = cartones.size() * 2;
		Reparto r = new Reparto(recaudacion);

		int cont = 0, numero;
		boolean bingo = false;

		while (bingo == false || numeros.isEmpty()) {
			numero = numeros.remove(0);
			bolasSacadas.add(numero);

			estadistica[cont] = new Estadistica(numero);

			Iterator<ICarton> it = cartones.iterator();
			boolean especial = false;
			boolean linea = false;

			while (it.hasNext()) {
				ICarton c = it.next();
				c.recibirNumero(numero);
				if (linea == false) {
					if (c.comprobarLinea()) {
						r.setNumLineas(r.getNumLineas() + 1);
						c.getUser().setNumLineas(c.getUser().getNumLineas() + 1);
						linea = true;
					}
				}
				if (especial == false) {
					if (c.comprobarEspeciales()) {
						r.setNumEspeciales(r.getNumEspeciales() + 1);
						c.getUser().setNumEspeciales(c.getUser().getNumEspeciales() + 1);
						especial = true;
					}
				}
				if (c.comprobarBingo()) {
					r.setNumBingo(r.getNumBingo() + 1);
					c.getUser().setNumBingos(c.getUser().getNumBingos() + 1);
					bingo = true;
				}
			}
			cont++;
		}
		r.setCaja((float) (recaudacion * 0.1));
		r.setRepartoEspeciales(r.getNumEspeciales() * 2);
		r.setRepartoLinea((float) (recaudacion * 0.3));
		r.setRepartoBingo((float) (recaudacion * 0.6));
		return r;
	}

	@Override
	public String toString() {
		return "Bingo80 [" + super.toString() + "]";
	}
}
