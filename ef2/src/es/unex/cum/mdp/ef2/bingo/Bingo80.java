package es.unex.cum.mdp.ef2.bingo;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import es.unex.cum.mdp.ef2.Estadistica;
import es.unex.cum.mdp.ef2.Movimiento;
import es.unex.cum.mdp.ef2.carton.ICarton;

/**
 * Clase que implementa un juego de Bingo con numeros del 1 al 80.
 */
public class Bingo80 extends Bingo {

	/**
	 * Constructor predeterminado de la clase Bingo80.
	 */
	public Bingo80() {
		super();
	}

	/**
	 * Constructor que inicializa el precio del carton y la bolsa de Bingo.
	 *
	 * @param f Precio del carton.
	 */
	public Bingo80(float f) {
		super(f);
		this.b = new BolsaBingo(80);
	}

	/**
	 * Implementacion del proceso de juego del Bingo80.
	 *
	 * @param estadistica Arreglo de estadisticas para el juego.
	 * @return Resultados del juego en un objeto de tipo Reparto.
	 */
	@Override
	public Reparto jugar(Estadistica[] estadistica) {
		// Generamos una bolsa aleatoria
		b.desordenar();

		// La recaudacion del Bingo sera el dinero obtenido de la venta de cartones
		recaudacion = cartones.size() * precioCarton;
		recaudacion = recaudacion + boteInicial;
		Reparto r = new Reparto(recaudacion);

		int numero;
		boolean bingoC = false, bingoB = false;
		boolean especialC = false, especialB = false;
		boolean lineaC = false, lineaB = false;

		while (bingoB == false || b.bolsaVacia()) {
			numero = (int) b.sacarBola();
			bolasSacadas.add(numero);

			// estadistica[numero] = new Estadistica(numero);
			estadistica[numero].setNumSacado(estadistica[numero].getNumSacado() + 1);

			Iterator<ICarton> it = cartones.iterator();

			while (it.hasNext()) {
				ICarton c = it.next();
				if (c.recibirNumero(numero)) {

					// Verificar si se ha conseguido especial
					if (!especialB && c.comprobarEspeciales()) {
						r.setNumEspeciales(r.getNumEspeciales() + 1);
						c.getUser().setNumEspeciales(c.getUser().getNumEspeciales() + 1);
						especialC = true;
					}

					// Verificar si se ha conseguido línea
					if (!lineaB && c.comprobarLinea()) {
						r.setNumLineas(r.getNumLineas() + 1);
						c.getUser().setNumLineas(c.getUser().getNumLineas() + 1);
						lineaC = true;
					}

					// Verificar si se ha conseguido bingo
					if (!bingoB && c.comprobarBingo()) {
						r.setNumBingo(r.getNumBingo() + 1);
						c.getUser().setNumBingos(c.getUser().getNumBingos() + 1);
						bingoC = true;
					}
				}
			}
			if (especialC == true) {
				estadistica[numero].setNumEspecial(estadistica[numero].getNumEspecial() + 1);
				especialB = true;
			}
			if (lineaC == true) {
				estadistica[numero].setNumLinea(estadistica[numero].getNumLinea() + 1);
				lineaB = true;
			}
			if (bingoC == true) {
				estadistica[numero].setNumBingo(estadistica[numero].getNumBingo() + 1);
				bingoB = true;
			}

		}

		// FIN DEL JUEGO Y REPARTO DE PREMIOS
		r.setCaja((float) (recaudacion * 0.1));
		r.setRepartoEspeciales(r.getNumEspeciales() * 2);
		r.setRepartoLinea(round((float) (r.getRecaudacion() * 0.3), 2));
		r.setRepartoBingo(round((float) (r.getRecaudacion() - r.getRepartoLinea()), 2));

		for (ICarton c : cartones) {
			float premio = 0.0f;
			float premioInd=0.0f;
			c.getUser().addCartonHistorico(c);
			switch (c.getEstado()) {
			case BINGO:
				premio = r.getRepartoBingo();
				premioInd=round(premio/r.getNumBingo(),2);
				premioInd=round(premioInd,2);
				c.getUser().addMovimiento(
						new Movimiento("Carton premiado", premioInd, c.getUser().getMonedero(), this.fecha));
				c.getUser().setMonedero(c.getUser().getMonedero() + premioInd);
				c.setPremio(premioInd);
				break;
			case LINEA:
				premio = r.getRepartoLinea();
				premioInd=round(premio/r.getNumLineas(),2);
				premioInd=round(premioInd,2);
				c.getUser().addMovimiento(
						new Movimiento("Carton premiado", premioInd, c.getUser().getMonedero(), this.fecha));
				c.getUser().setMonedero(c.getUser().getMonedero() + premioInd);
				c.setPremio(premioInd);
				break;
			case LINEA_BINGO:
				premio = round((r.getRepartoBingo()/r.getNumBingo()),2) + round((r.getRepartoLinea()/r.getNumLineas()),2);
				premio=round(premio,2);
				c.getUser().addMovimiento(
						new Movimiento("Carton premiado", premio, c.getUser().getMonedero(), this.fecha));
				c.getUser().setMonedero(c.getUser().getMonedero() + premio);
				c.setPremio(premio);
				break;
			case ESPECIAL:
				premio = r.getRepartoEspeciales();
				premioInd=round(premio/r.getNumEspeciales(),2);
				premioInd=round(premioInd,2);
				c.getUser().addMovimiento(
						new Movimiento("Carton premiado", premioInd, c.getUser().getMonedero(), this.fecha));
				c.getUser().setMonedero(c.getUser().getMonedero() + premioInd);
				c.setPremio(premioInd);
				break;
			case ESPECIAL_LINEA:
				premio = round((r.getRepartoEspeciales()/r.getNumEspeciales()),2) + round((r.getRepartoLinea()/r.getNumLineas()),2);
				premio=round(premio,2);
				c.getUser().addMovimiento(
						new Movimiento("Carton premiado", premio, c.getUser().getMonedero(), this.fecha));
				c.getUser().setMonedero(c.getUser().getMonedero() + premio);
				c.setPremio(premio);
				break;
			case ESPECIAL_BINGO:
				premio = round((r.getRepartoBingo()/r.getNumBingo()),2) + round((r.getRepartoEspeciales()/r.getNumEspeciales()),2);
				premio=round(premio,2);
				c.getUser().addMovimiento(
						new Movimiento("Carton premiado", premio, c.getUser().getMonedero(), this.fecha));
				c.getUser().setMonedero(c.getUser().getMonedero() + premio);
				c.setPremio(premio);
				break;
			case ESPECIAL_LINEA_BINGO:
				premio = round((r.getRepartoBingo()/r.getNumBingo()),2) + round((r.getRepartoEspeciales()/r.getNumEspeciales()),2) + round((r.getRepartoLinea()/r.getNumLineas()),2);
				premio=round(premio,2);
				c.getUser().addMovimiento(
						new Movimiento("Carton premiado", premio, c.getUser().getMonedero(), this.fecha));
				c.getUser().setMonedero(c.getUser().getMonedero() + premio);
				c.setPremio(premio);
				break;
			default:
				break;
			}
		}

		reparto=r;
		return r;
	}

	/**
	 * Implementacion del proceso de juego del Bingo80 con numeros especificos.
	 *
	 * @param estadistica Arreglo de estadisticas para el juego.
	 * @param numeros     Lista de numeros especificos para el juego.
	 * @return Resultados del juego en un objeto de tipo Reparto.
	 */
	@Override
	public Reparto jugar(Estadistica[] estadistica, List<Integer> numeros) {
		recaudacion = cartones.size() * precioCarton;
		recaudacion = recaudacion + boteInicial;
		Reparto r = new Reparto(recaudacion);

		int numero;
		boolean bingoC = false, bingoB = false;
		boolean especialC = false, especialB = false;
		boolean lineaC = false, lineaB = false;

		while (bingoB == false || !numeros.isEmpty()) {
			numero = numeros.remove(0);
			bolasSacadas.add(numero);

			// estadistica[numero] = new Estadistica(numero);
			estadistica[numero].setNumSacado(estadistica[numero].getNumSacado() + 1);

			Iterator<ICarton> it = cartones.iterator();

			while (it.hasNext()) {
				ICarton c = it.next();
				if (c.recibirNumero(numero)) {

					// Verificar si se ha conseguido especial
					if (!especialB && c.comprobarEspeciales()) {
						r.setNumEspeciales(r.getNumEspeciales() + 1);
						c.getUser().setNumEspeciales(c.getUser().getNumEspeciales() + 1);
						especialC = true;
					}

					// Verificar si se ha conseguido línea
					if (!lineaB && c.comprobarLinea()) {
						r.setNumLineas(r.getNumLineas() + 1);
						c.getUser().setNumLineas(c.getUser().getNumLineas() + 1);
						lineaC = true;
					}

					// Verificar si se ha conseguido bingo
					if (!bingoB && c.comprobarBingo()) {
						r.setNumBingo(r.getNumBingo() + 1);
						c.getUser().setNumBingos(c.getUser().getNumBingos() + 1);
						bingoC = true;
					}
				}
			}
			if (especialC == true) {
				estadistica[numero].setNumEspecial(estadistica[numero].getNumEspecial() + 1);
				especialB = true;
			}
			if (lineaC == true) {
				estadistica[numero].setNumLinea(estadistica[numero].getNumLinea() + 1);
				lineaB = true;
			}
			if (bingoC == true) {
				estadistica[numero].setNumBingo(estadistica[numero].getNumBingo() + 1);
				bingoB = true;
			}

		}

		// FIN DEL JUEGO Y REPARTO DE PREMIOS
		r.setCaja((float) (recaudacion * 0.1));
		r.setRepartoEspeciales(r.getNumEspeciales() * 2);
		r.setRepartoLinea(round((float) (r.getRecaudacion() * 0.3), 2));
		r.setRepartoBingo(round((float) (r.getRecaudacion() - r.getRepartoLinea()), 2));

		for (ICarton c : cartones) {
			float premio = 0.0f;
			float premioInd=0.0f;
			c.getUser().addCartonHistorico(c);
			switch (c.getEstado()) {
			case BINGO:
				premio = r.getRepartoBingo();
				premioInd=round(premio/r.getNumBingo(),2);
				premioInd=round(premioInd,2);
				c.getUser().addMovimiento(
						new Movimiento("Carton premiado", premioInd, c.getUser().getMonedero(), this.fecha));
				c.getUser().setMonedero(c.getUser().getMonedero() + premioInd);
				c.setPremio(premioInd);
				break;
			case LINEA:
				premio = r.getRepartoLinea();
				premioInd=round(premio/r.getNumLineas(),2);
				premioInd=round(premioInd,2);
				c.getUser().addMovimiento(
						new Movimiento("Carton premiado", premioInd, c.getUser().getMonedero(), this.fecha));
				c.getUser().setMonedero(c.getUser().getMonedero() + premioInd);
				c.setPremio(premioInd);
				break;
			case LINEA_BINGO:
				premio = round((r.getRepartoBingo()/r.getNumBingo()),2) + round((r.getRepartoLinea()/r.getNumLineas()),2);
				premio=round(premio,2);
				c.getUser().addMovimiento(
						new Movimiento("Carton premiado", premio, c.getUser().getMonedero(), this.fecha));
				c.getUser().setMonedero(c.getUser().getMonedero() + premio);
				c.setPremio(premio);
				break;
			case ESPECIAL:
				premio = r.getRepartoEspeciales();
				premioInd=round(premio/r.getNumEspeciales(),2);
				premioInd=round(premioInd,2);
				c.getUser().addMovimiento(
						new Movimiento("Carton premiado", premioInd, c.getUser().getMonedero(), this.fecha));
				c.getUser().setMonedero(c.getUser().getMonedero() + premioInd);
				c.setPremio(premioInd);
				break;
			case ESPECIAL_LINEA:
				premio = round((r.getRepartoEspeciales()/r.getNumEspeciales()),2) + round((r.getRepartoLinea()/r.getNumLineas()),2);
				premio=round(premio,2);
				c.getUser().addMovimiento(
						new Movimiento("Carton premiado", premio, c.getUser().getMonedero(), this.fecha));
				c.getUser().setMonedero(c.getUser().getMonedero() + premio);
				c.setPremio(premio);
				break;
			case ESPECIAL_BINGO:
				premio = round((r.getRepartoBingo()/r.getNumBingo()),2) + round((r.getRepartoEspeciales()/r.getNumEspeciales()),2);
				premio=round(premio,2);
				c.getUser().addMovimiento(
						new Movimiento("Carton premiado", premio, c.getUser().getMonedero(), this.fecha));
				c.getUser().setMonedero(c.getUser().getMonedero() + premio);
				c.setPremio(premio);
				break;
			case ESPECIAL_LINEA_BINGO:
				premio = round((r.getRepartoBingo()/r.getNumBingo()),2) + round((r.getRepartoEspeciales()/r.getNumEspeciales()),2) + round((r.getRepartoLinea()/r.getNumLineas()),2);
				premio=round(premio,2);
				c.getUser().addMovimiento(
						new Movimiento("Carton premiado", premio, c.getUser().getMonedero(), this.fecha));
				c.getUser().setMonedero(c.getUser().getMonedero() + premio);
				c.setPremio(premio);
				break;
			default:
				break;
			}
		}
		reparto=r;
		return r;
	}

	/**
	 * Representacion en cadena de texto del objeto Bingo80.
	 *
	 * @return Cadena de texto que representa el objeto.
	 */
	@Override
	public String toString() {
		return "Bingo80 [" + super.toString() + "]";
	}

	/**
	 * Redondea un numero decimal a la cantidad de decimales especificada.
	 *
	 * @param d            Numero decimal a redondear.
	 * @param decimalPlace Cantidad de decimales a mantener.
	 * @return Numero decimal redondeado.
	 */
	@SuppressWarnings("deprecation")
	private float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}
}
