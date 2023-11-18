package es.unex.cum.mdp.ef2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unex.cum.mdp.ef2.bingo.Bingo;
import es.unex.cum.mdp.ef2.bingo.FactoriaBingo;
import es.unex.cum.mdp.ef2.bingo.Reparto;
import es.unex.cum.mdp.ef2.carton.Carton75H;
import es.unex.cum.mdp.ef2.carton.Carton75M;
import es.unex.cum.mdp.ef2.carton.Carton75V;
import es.unex.cum.mdp.ef2.carton.Carton80H;
import es.unex.cum.mdp.ef2.carton.Carton80M;
import es.unex.cum.mdp.ef2.carton.Carton80V;
import es.unex.cum.mdp.ef2.carton.Carton90H;
import es.unex.cum.mdp.ef2.carton.Carton90M;
import es.unex.cum.mdp.ef2.carton.Carton90V;
import es.unex.cum.mdp.ef2.carton.ICarton;
import es.unex.cum.mdp.ef2.celda.CeldaCarton;

public class TestBingo {

	private Bingo b1;
	// Cambiar por 75V, 75h, 75M, 80V, 80H, 80M,90V, 90H, 90M
	private String tipo = "80H"; 

	@BeforeEach
	public void setUp() throws Exception {
		///////////////////////////////
		//Sin factoria
		/*
		if (tipo.contains("75")) {
			//b1 = new Bingo75(2.0F);
		} else if (tipo.contains("80")) {
			b1 = new Bingo80(2.0F);
		} else {
			//b1 = new Bingo90(2.0F);
		}
		*/
		////////////////////////////////

		////////////////////////////////
		//Como factoria
		b1=FactoriaBingo.buildBingo(tipo, 2.0F);
		////////////////////////////////
	}

	@AfterEach
	public void tearDown() throws Exception {
		b1 = null;
	}

	/********************************************************
	 * Jugar +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 */
	public List<Integer> ListCorrecto(String tipo) {
		List<Integer> l = new LinkedList<Integer>();
		if (tipo.contains("75")) {
			// carton 75
			// X | 16 |32 |48 |69 |
			// 3 | X |39 |50 |70 |
			// 6 | 22 |X |51 |71 |
			// 7 | 25 |43 |X |73 |
			// 15| 29 |45 |58 |X |
			l.add(32);
			l.add(3);
			l.add(69);
			l.add(6);
			l.add(70);
			l.add(7);
			l.add(39);
			l.add(71);
			l.add(73);
			l.add(43);
			l.add(45);
			l.add(15);
			l.add(16);
			l.add(48);
			l.add(50);
			l.add(51);
			l.add(22);
			l.add(25);
			l.add(58);
			l.add(29);
		} else if (tipo.contains("80")) {
			// Carton 80
//			X |	11 | X  |	31 |	42 |	54 |	63 |	X  |	
//			X |	X  | 27 |	X  |	44 |	55 |	65 |	74 |	
//			5 |	15 | 28 |	X  |	47 |	X  |	X  |	78 |	
//			10|	19 | X  |	40 |	X  |	60 |	69 |	X  |

			l.add(54);
			l.add(42);
			l.add(11);
			l.add(65);
			l.add(5);
			l.add(69);
			l.add(40);
			l.add(10);
			l.add(74);

			l.add(44);
			l.add(78);
			l.add(15);
			l.add(47);
			l.add(19);

			l.add(55);
			l.add(27);
			l.add(28);
			l.add(60);
			l.add(31);
			l.add(63);
		} else if (tipo.contains("90")) {
			// Para 90

//			1 |	X |	23 |	33 |	X |	52 |	X |	75 |	X |	
//			X |	X |	24 |	38 |	X |	X  |	68|	76 |	84 |	
//			10|	19|	25 |	X  |	50|	X  |	X |	X  |	89 |	

			l.add(1);
			l.add(33);
			l.add(68);
			l.add(38);
			l.add(10);
			l.add(75);
			l.add(76);
			l.add(50);
			l.add(19);
			l.add(52);
			l.add(84);
			l.add(23);
			l.add(24);
			l.add(25);
			l.add(89);

		}

		return l;
	}

	public ICarton crearCartonCorrecto(String tipo) {
		ICarton c = null;

		if (tipo.contains("75")) {
			// carton 75
			// X | 16 |32 |48 |69 |
			// 3 | X |39 |50 |70 |
			// 6 | 22 |X |51 |71 |
			// 7 | 25 |43 |X |73 |
			// 15| 29 |45 |58 |X |
			if (tipo.equals("75H")) {
				c = new Carton75H();
			} else if (tipo.equals("75V")) {
				c = new Carton75V();
			} else {
				c = new Carton75M();

			}

			c.addNumero(new CeldaCarton(32, 1, 0, 2));
			c.addNumero(new CeldaCarton(3, 1, 1, 0));
			c.addNumero(new CeldaCarton(69, 1, 0, 4));
			c.addNumero(new CeldaCarton(6, 1, 2, 0));
			c.addNumero(new CeldaCarton(70, 1, 1, 4));
			c.addNumero(new CeldaCarton(7, 1, 3, 0));
			c.addNumero(new CeldaCarton(39, 1, 1, 2));
			c.addNumero(new CeldaCarton(71, 1, 2, 4));
			c.addNumero(new CeldaCarton(73, 1, 3, 4));
			c.addNumero(new CeldaCarton(43, 1, 3, 2));
			c.addNumero(new CeldaCarton(45, 1, 4, 2));
			c.addNumero(new CeldaCarton(15, 1, 4, 0));
			c.addNumero(new CeldaCarton(16, 1, 0, 1));
			c.addNumero(new CeldaCarton(48, 1, 0, 3));
			c.addNumero(new CeldaCarton(50, 1, 1, 3));
			c.addNumero(new CeldaCarton(51, 1, 2, 3));
			c.addNumero(new CeldaCarton(22, 1, 2, 1));
			c.addNumero(new CeldaCarton(25, 1, 3, 1));
			c.addNumero(new CeldaCarton(58, 1, 4, 3));
			c.addNumero(new CeldaCarton(29, 1, 4, 1));
			c.addEspecial(32);
			c.addEspecial(3);
		} else if (tipo.contains("80")) {
			// Carton 80
//			X |	11 | X  |	31 |	42 |	54 |	63 |	X  |	
//			X |	X  | 27 |	X  |	44 |	55 |	65 |	74 |	
//			5 |	15 | 28 |	X  |	47 |	X  |	X  |	78 |	
//			10|	19 | X  |	40 |	X  |	60 |	69 |	X  |

			if (tipo.equals("80H")) {
				c = new Carton80H();
			} else if (tipo.equals("80V")) {
				c = new Carton80V();
			} else {
				c = new Carton80M();
			}
			c.addNumero(new CeldaCarton(54, 1, 0, 5));
			c.addNumero(new CeldaCarton(42, 1, 0, 4));
			c.addNumero(new CeldaCarton(11, 1, 0, 1));

			c.addNumero(new CeldaCarton(65, 1, 1, 6));
			c.addNumero(new CeldaCarton(5, 1, 2, 0));
			c.addNumero(new CeldaCarton(69, 1, 3, 6));
			c.addNumero(new CeldaCarton(40, 1, 3, 3));
			c.addNumero(new CeldaCarton(10, 1, 3, 0));

			c.addNumero(new CeldaCarton(74, 1, 1, 7));

			c.addNumero(new CeldaCarton(44, 1, 1, 4));
			c.addNumero(new CeldaCarton(78, 1, 2, 7));
			c.addNumero(new CeldaCarton(15, 1, 2, 1));
			c.addNumero(new CeldaCarton(47, 1, 2, 4));
			c.addNumero(new CeldaCarton(19, 1, 3, 1));

			c.addNumero(new CeldaCarton(55, 1, 1, 5));
			c.addNumero(new CeldaCarton(27, 1, 1, 2));
			c.addNumero(new CeldaCarton(28, 1, 2, 2));
			c.addNumero(new CeldaCarton(60, 1, 3, 5));
			c.addNumero(new CeldaCarton(31, 1, 0, 3));
			c.addNumero(new CeldaCarton(63, 1, 0, 6));

			c.addEspecial(65);
			c.addEspecial(5);
			c.addEspecial(69);
		} else if (tipo.contains("90")) {

			if (tipo.equals("90H")) {
				c = new Carton90H();
			} else if (tipo.equals("90V")) {
				c = new Carton90V();
			} else {
				c = new Carton90M();

			}

			// Para 90

//			1 |	X |	23 |	33 |	X |	52 |	X |	75 |	X |	
//			X |	X |	24 |	38 |	X |	X  |	68|	76 |	84 |	
//			10|	19|	25 |	X  |	50|	X  |	X |	X  |	89 |	

			c.addNumero(new CeldaCarton(1, 1, 0, 0));
			c.addNumero(new CeldaCarton(33, 1, 0, 3));
			c.addNumero(new CeldaCarton(68, 1, 1, 6));
			c.addNumero(new CeldaCarton(38, 1, 1, 3));
			c.addNumero(new CeldaCarton(10, 1, 2, 0));
			c.addNumero(new CeldaCarton(75, 1, 0, 7));
			c.addNumero(new CeldaCarton(76, 1, 1, 7));
			c.addNumero(new CeldaCarton(50, 1, 2, 4));
			c.addNumero(new CeldaCarton(19, 1, 2, 1));
			c.addNumero(new CeldaCarton(52, 1, 0, 5));
			c.addNumero(new CeldaCarton(84, 1, 1, 8));
			c.addNumero(new CeldaCarton(23, 1, 0, 2));
			c.addNumero(new CeldaCarton(24, 1, 1, 2));
			c.addNumero(new CeldaCarton(25, 1, 2, 2));
			c.addNumero(new CeldaCarton(89, 1, 2, 8));

			c.addEspecial(1);
			c.addEspecial(33);
			c.addEspecial(68);
			c.addEspecial(38);

		}

		return c;
	}

	public ICarton crearCartonIncorrecto(String tipo) {
		ICarton c = null;

		if (tipo.contains("75")) {
			// carton 75
			// X | 16 |32 |48 |69 |
			// 3 | X |39 |50 |70 |
			// 6 | 22 |X |51 |71 |
			// 7 | 25 |43 |X |73 |
			// 15| 29 |45 |58 |X |
			if (tipo.equals("75H")) {
				c = new Carton75H();
			} else if (tipo.equals("75V")) {
				c = new Carton75V();
			} else {
				c = new Carton75M();

			}
			c.addNumero(new CeldaCarton(33, 1, 2, 0));
			c.addNumero(new CeldaCarton(4, 1, 0, 1));
			c.addNumero(new CeldaCarton(68, 1, 4, 0));
			c.addNumero(new CeldaCarton(6, 1, 0, 2));
			c.addNumero(new CeldaCarton(72, 1, 4, 1));
			c.addNumero(new CeldaCarton(8, 1, 0, 3));
			c.addNumero(new CeldaCarton(38, 1, 2, 1));
			c.addNumero(new CeldaCarton(71, 1, 4, 2));
			c.addNumero(new CeldaCarton(74, 1, 4, 3));
			c.addNumero(new CeldaCarton(42, 1, 2, 3));
			c.addNumero(new CeldaCarton(44, 1, 2, 4));
			c.addNumero(new CeldaCarton(15, 1, 0, 4));
			c.addNumero(new CeldaCarton(17, 1, 1, 0));
			c.addNumero(new CeldaCarton(49, 1, 3, 0));
			c.addNumero(new CeldaCarton(53, 1, 3, 1));
			c.addNumero(new CeldaCarton(51, 1, 3, 2));
			c.addNumero(new CeldaCarton(23, 1, 1, 2));
			c.addNumero(new CeldaCarton(25, 1, 1, 3));
			c.addNumero(new CeldaCarton(59, 1, 3, 4));
			c.addNumero(new CeldaCarton(29, 1, 1, 4));
			c.addEspecial(33);
			c.addEspecial(4);
		} else if (tipo.contains("80")) {
			// Carton 80
//			X |	11 | X  |	31 |	42 |	54 |	63 |	X  |	
//			X |	X  | 27 |	X  |	44 |	55 |	65 |	74 |	
//			5 |	15 | 28 |	X  |	47 |	X  |	X  |	78 |	
//			10|	19 | X  |	40 |	X  |	60 |	69 |	X  |

			if (tipo.equals("80H")) {
				c = new Carton80H();
			} else if (tipo.equals("80V")) {
				c = new Carton80V();
			} else {
				c = new Carton80M();
			}
			c.addNumero(new CeldaCarton(52, 1, 0, 5));
			c.addNumero(new CeldaCarton(41, 1, 0, 4));
			c.addNumero(new CeldaCarton(12, 1, 0, 1));

			c.addNumero(new CeldaCarton(66, 1, 1, 6));
			c.addNumero(new CeldaCarton(3, 1, 2, 0));
			c.addNumero(new CeldaCarton(68, 1, 3, 6));
			c.addNumero(new CeldaCarton(39, 1, 3, 3));
			c.addNumero(new CeldaCarton(14, 1, 3, 0));

			c.addNumero(new CeldaCarton(72, 1, 1, 7));

			c.addNumero(new CeldaCarton(43, 1, 1, 4));
			c.addNumero(new CeldaCarton(78, 1, 2, 7));
			c.addNumero(new CeldaCarton(15, 1, 2, 1));
			c.addNumero(new CeldaCarton(47, 1, 2, 4));
			c.addNumero(new CeldaCarton(19, 1, 3, 1));

			c.addNumero(new CeldaCarton(55, 1, 1, 5));
			c.addNumero(new CeldaCarton(27, 1, 1, 2));
			c.addNumero(new CeldaCarton(28, 1, 2, 2));
			c.addNumero(new CeldaCarton(60, 1, 3, 5));
			c.addNumero(new CeldaCarton(31, 1, 0, 3));
			c.addNumero(new CeldaCarton(63, 1, 0, 6));

			c.addEspecial(52);
			c.addEspecial(41);
			c.addEspecial(12);
		} else if (tipo.contains("90")) {

			if (tipo.equals("90H")) {
				c = new Carton90H();
			} else if (tipo.equals("90V")) {
				c = new Carton90V();
			} else {
				c = new Carton90M();

			}

			// Para 90

//			1 |	X |	23 |	33 |	X |	52 |	X |	75 |	X |	
//			X |	X |	24 |	38 |	X |	X  |	68|	76 |	84 |	
//			10|	19|	25 |	X  |	50|	X  |	X |	X  |	89 |	

			c.addNumero(new CeldaCarton(2, 1, 0, 0));
			c.addNumero(new CeldaCarton(34, 1, 0, 3));
			c.addNumero(new CeldaCarton(68, 1, 1, 6));
			c.addNumero(new CeldaCarton(39, 1, 1, 3));
			c.addNumero(new CeldaCarton(9, 1, 2, 0));
			c.addNumero(new CeldaCarton(73, 1, 0, 7));
			c.addNumero(new CeldaCarton(74, 1, 1, 7));
			c.addNumero(new CeldaCarton(49, 1, 2, 4));
			c.addNumero(new CeldaCarton(18, 1, 2, 1));
			c.addNumero(new CeldaCarton(52, 1, 0, 5));
			c.addNumero(new CeldaCarton(84, 1, 1, 8));
			c.addNumero(new CeldaCarton(23, 1, 0, 2));
			c.addNumero(new CeldaCarton(24, 1, 1, 2));
			c.addNumero(new CeldaCarton(25, 1, 2, 2));
			c.addNumero(new CeldaCarton(89, 1, 2, 8));

			c.addEspecial(2);
			c.addEspecial(34);
			c.addEspecial(68);
			c.addEspecial(39);

		}

		return c;
	}

	@Test
	public void testJugarBingo() {

		Usuario u1 = new Usuario("luis1", "luis1", "luis", 100);
		Usuario u2 = new Usuario("luis2", "luis2", "luis2", 100);
		Usuario u3 = new Usuario("luis3", "luis3", "luis3", 100);

		for (int i = 0; i < 5; i++) {
			b1.crearCarton(tipo, u1);
			b1.crearCarton(tipo, u2);
			b1.crearCarton(tipo, u3);
		}
		ICarton c1 = b1.crearCarton(tipo, u1);
		ICarton ganado = crearCartonCorrecto(tipo);
		c1.setNumeros(ganado.getNumeros());

		// Antes de jugar comprueba el número de cartones
		// Comprobar la lista de un jugador (10 cartones)
		assertTrue(u1.getCartones().size() == 6);
		assertTrue(u2.getCartones().size() == 5);
		assertTrue(u3.getCartones().size() == 5);

		// Se juega el sorteo
		List<Integer> l = ListCorrecto(tipo);
		Estadistica[] estadistica = new Estadistica[90 + 1];
		for (int i = 0; i < estadistica.length; i++)
			estadistica[i] = new Estadistica(i);
		Reparto r = b1.jugar(estadistica, l);
		assertTrue(b1.getRecaudacion() == 32);

		assertTrue(u1.getHistorico().size() == 7); // 5 cartones + 1 Carton ganador +1 mov de ganar
		assertTrue(u2.getHistorico().size() == 5); // 5 cartones
		assertTrue(u3.getHistorico().size() == 5); // 5 cartones

		assertTrue(u2.getMonedero() == (100 - 10)); // 100 - 5*2 cartones
		assertTrue(u3.getMonedero() == (100 - 10)); // 100 - 5*2 cartones
		
		// Con reparto
		assertTrue(r.getCaja() == 3.2F);
		assertTrue(r.getNumBingo() == 1);
		assertTrue(r.getNumLineas() == 1);
		assertTrue(r.getNumEspeciales() == 1);
		assertTrue(r.getRepartoBingo() == 18.76F);
		assertTrue(r.getRepartoLinea() == 8.04F);
		assertTrue(r.getRepartoEspeciales() == 2);

		float total = (100 - 12 + 2 + 18.76F + 8.04F);
		assertTrue(u1.getMonedero() == total);

		// Compruebo las estadísticas.
		if (tipo.contains("75")) {
			// Especial con el 38
			assertTrue(estadistica[3].getNumEspecial() == 1);
			// Línea con el 23
			assertTrue(estadistica[48].getNumLinea() == 1);
			// Bingo con el 89
			assertTrue(estadistica[29].getNumBingo() == 1);
		} else if (tipo.contains("80")) {
			// Especial con el 38
			assertTrue(estadistica[69].getNumEspecial() == 1);
			// Línea con el 23
			assertTrue(estadistica[27].getNumLinea() == 1);
			// Bingo con el 89
			assertTrue(estadistica[63].getNumBingo() == 1);
		} else {
			// Especial con el 38
			assertTrue(estadistica[38].getNumEspecial() == 1);
			// Línea con el 23
			assertTrue(estadistica[23].getNumLinea() == 1);
			// Bingo con el 89
			assertTrue(estadistica[89].getNumBingo() == 1);
		}
		// Compruebo que los números cantados están a 1
		Iterator it = l.iterator();
		while (it.hasNext()) {
			Integer aux = (Integer) it.next();
			assertTrue(estadistica[aux.intValue()].getNumSacado() == 1);
		}

	}
	
	
	@Test
	public void testJugarAleatorio() {

		Usuario u1 = new Usuario("luis1", "luis1", "luis", 100);
		Usuario u2 = new Usuario("luis2", "luis2", "luis2", 100);
		Usuario u3 = new Usuario("luis3", "luis3", "luis3", 100);

		for (int i = 0; i < 5; i++) {
			b1.crearCarton(tipo, u1);
			b1.crearCarton(tipo, u2);
			b1.crearCarton(tipo, u3);
		}

		// Antes de jugar comprueba el número de cartones
		// Comprobar la lista de un jugador (5 cartones)
		assertTrue(u1.getCartones().size() == 5);
		assertTrue(u2.getCartones().size() == 5);
		assertTrue(u3.getCartones().size() == 5);

		// Se juega el sorteo
		Estadistica[] estadistica = new Estadistica[90 + 1];
		for (int i = 0; i < estadistica.length; i++)
			estadistica[i] = new Estadistica(i);
		Reparto r = b1.jugar(estadistica);
		assertTrue(b1.getRecaudacion() == 30);

		//TODO: Como se juega aleatorio puede que no funcione 
		
		// Con reparto 
		assertTrue(r.getCaja() == 3F);
		assertTrue(r.getNumBingo() == 1);
		assertTrue(r.getNumLineas() == 1);
		assertTrue(r.getNumEspeciales() == 1);
		assertTrue(r.getRepartoBingo() == 17.5F);
		assertTrue(r.getRepartoLinea() == 7.5F);
		assertTrue(r.getRepartoEspeciales() == 2);

	}

	private float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}
}
