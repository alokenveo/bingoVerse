package es.unex.cum.mdp.ef2;

import java.math.BigDecimal;
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
		// Generamos una bolsa aleatoria
		for (int i = 1; i <= 80; i++) {
			b.addNumero(i);
		}
		b.desordenar();

		// La recaudación del Bingo será el dinero obtenido de la venta de cartones
		recaudacion = cartones.size() * 2;
		Reparto r = new Reparto(recaudacion);

		int numero;
		boolean bingo = false;
		boolean especial = false;
		boolean linea = false;

		while (bingo == false || b.bolsaVacia()) {
			numero = (int) b.sacarBola();
			bolasSacadas.add(numero);

			estadistica[numero] = new Estadistica(numero);
			estadistica[numero].setNumSacado(estadistica[numero].getNumSacado() + 1);

			Iterator<ICarton> it = cartones.iterator();

			while (it.hasNext()) {
				ICarton c = it.next();
				if (c.recibirNumero(numero)) {
					if (linea == false) {
						if (c.comprobarLinea()) {
							r.setNumLineas(r.getNumLineas() + 1);
							c.getUser().setNumLineas(c.getUser().getNumLineas() + 1);
							estadistica[numero].setNumLinea(estadistica[numero].getNumLinea() + 1);
							linea = true;
						}
					}
					if (especial == false) {
						if (c.comprobarEspeciales()) {
							r.setNumEspeciales(r.getNumEspeciales() + 1);
							c.getUser().setNumEspeciales(c.getUser().getNumEspeciales() + 1);
							estadistica[numero].setNumEspecial(estadistica[numero].getNumEspecial() + 1);
							especial = true;
						}
					}
					if (bingo == false) {
						if (c.comprobarBingo()) {
							r.setNumBingo(r.getNumBingo() + 1);
							c.getUser().setNumBingos(c.getUser().getNumBingos() + 1);
							estadistica[numero].setNumBingo(estadistica[numero].getNumBingo() + 1);
							bingo = true;
						}
					}
				}
			}
		}
		
		//FIN DEL JUEGO Y REPARTO DE PREMIOS
		
		r.setCaja((float) (recaudacion * 0.1));
		r.setRepartoEspeciales(r.getNumEspeciales() * 2);
		r.setRepartoLinea(round((float) (r.getRecaudacion() * 0.3), 2));
		r.setRepartoBingo(round((float) (r.getRecaudacion() - r.getRepartoLinea()), 2));

		for (ICarton c : cartones) {
			float premio=0.0f;
			c.getUser().addCartonHistorico(c);
			switch(c.getEstado()) {
			case BINGO:
				premio=r.getRepartoBingo();
				c.getUser().setMonedero(c.getUser().getMonedero()+premio);
				c.setPremio(premio);
				break;
			case LINEA:
				premio=r.getRepartoLinea();
				c.getUser().setMonedero(c.getUser().getMonedero()+premio);
				c.setPremio(premio);
				break;
			case LINEA_BINGO:
				premio=r.getRepartoBingo()+r.getRepartoLinea();
				c.getUser().setMonedero(c.getUser().getMonedero()+premio);
				c.setPremio(premio);
				break;
			case ESPECIAL:
				premio=r.getRepartoEspeciales();
				c.getUser().setMonedero(c.getUser().getMonedero()+premio);
				c.setPremio(premio);
				break;
			case ESPECIAL_LINEA:
				premio=r.getRepartoEspeciales()+r.getRepartoLinea();
				c.getUser().setMonedero(c.getUser().getMonedero()+premio);
				c.setPremio(premio);
				break;
			case ESPECIAL_BINGO:
				premio=r.getRepartoBingo()+r.getRepartoEspeciales();
				c.getUser().setMonedero(c.getUser().getMonedero()+premio);
				c.setPremio(premio);
				break;
			case ESPECIAL_LINEA_BINGO:
				premio=r.getRepartoBingo()+r.getRepartoEspeciales()+r.getRepartoLinea();
				c.getUser().setMonedero(c.getUser().getMonedero()+premio);
				c.setPremio(premio);
				break;
			default:
				break;
			}
		}
		
		return r;
	}

	@Override
	public Reparto jugar(Estadistica[] estadistica, List<Integer> numeros) {
		// TODO Auto-generated method stub
		recaudacion = cartones.size() * 2;
		Reparto r = new Reparto(recaudacion);

		int numero;
		boolean bingo = false;
		boolean especial = false;
		boolean linea = false;

		while (bingo == false || !numeros.isEmpty()) {
			numero = numeros.remove(0);
			bolasSacadas.add(numero);

			estadistica[numero] = new Estadistica(numero);
			estadistica[numero].setNumSacado(estadistica[numero].getNumSacado() + 1);

			Iterator<ICarton> it = cartones.iterator();

			while (it.hasNext()) {
				ICarton c = it.next();
				if (c.recibirNumero(numero)) {
					if (linea == false) {
						if (c.comprobarLinea()) {
							r.setNumLineas(r.getNumLineas() + 1);
							c.getUser().setNumLineas(c.getUser().getNumLineas() + 1);
							estadistica[numero].setNumLinea(estadistica[numero].getNumLinea() + 1);
							linea = true;
						}
					}
					if (especial == false) {
						if (c.comprobarEspeciales()) {
							r.setNumEspeciales(r.getNumEspeciales() + 1);
							c.getUser().setNumEspeciales(c.getUser().getNumEspeciales() + 1);
							estadistica[numero].setNumEspecial(estadistica[numero].getNumEspecial() + 1);
							especial = true;
						}
					}
					if (bingo == false) {
						if (c.comprobarBingo()) {
							r.setNumBingo(r.getNumBingo() + 1);
							c.getUser().setNumBingos(c.getUser().getNumBingos() + 1);
							estadistica[numero].setNumBingo(estadistica[numero].getNumBingo() + 1);
							bingo = true;
						}
					}
				}
			}
		}
		
		//FIN DEL JUEGO Y REPARTO DE PREMIOS
		
		r.setCaja((float) (recaudacion * 0.1));
		r.setRepartoEspeciales(r.getNumEspeciales() * 2);
		r.setRepartoLinea(round((float) (r.getRecaudacion() * 0.3), 2));
		r.setRepartoBingo(round((float) (r.getRecaudacion() - r.getRepartoLinea()), 2));

		for (ICarton c : cartones) {
			float premio=0.0f;
			c.getUser().addCartonHistorico(c);
			switch(c.getEstado()) {
			case BINGO:
				premio=r.getRepartoBingo();
				c.getUser().setMonedero(c.getUser().getMonedero()+premio);
				c.setPremio(premio);
				break;
			case LINEA:
				premio=r.getRepartoLinea();
				c.getUser().setMonedero(c.getUser().getMonedero()+premio);
				c.setPremio(premio);
				break;
			case LINEA_BINGO:
				premio=r.getRepartoBingo()+r.getRepartoLinea();
				c.getUser().setMonedero(c.getUser().getMonedero()+premio);
				c.setPremio(premio);
				break;
			case ESPECIAL:
				premio=r.getRepartoEspeciales();
				c.getUser().setMonedero(c.getUser().getMonedero()+premio);
				c.setPremio(premio);
				break;
			case ESPECIAL_LINEA:
				premio=r.getRepartoEspeciales()+r.getRepartoLinea();
				c.getUser().setMonedero(c.getUser().getMonedero()+premio);
				c.setPremio(premio);
				break;
			case ESPECIAL_BINGO:
				premio=r.getRepartoBingo()+r.getRepartoEspeciales();
				c.getUser().setMonedero(c.getUser().getMonedero()+premio);
				c.setPremio(premio);
				break;
			case ESPECIAL_LINEA_BINGO:
				premio=r.getRepartoBingo()+r.getRepartoEspeciales()+r.getRepartoLinea();
				c.getUser().setMonedero(c.getUser().getMonedero()+premio);
				c.setPremio(premio);
				break;
			default:
				break;
			}
		}
		
		return r;
	}

	@Override
	public String toString() {
		return "Bingo80 [" + super.toString() + "]";
	}
	private float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}
}
