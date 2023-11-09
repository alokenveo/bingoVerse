package es.unex.cum.mdp.ef2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.unex.cum.mdp.ef2.carton.Carton75H;
import es.unex.cum.mdp.ef2.carton.Carton75M;
import es.unex.cum.mdp.ef2.carton.Carton75V;
import es.unex.cum.mdp.ef2.carton.CeldaCarton;
import es.unex.cum.mdp.ef2.carton.EstadoCarton;
import es.unex.cum.mdp.ef2.carton.ICarton;

public class CartonTest75 {
	private ICarton c75 = null;
	private String tipo = "75M"; //TODO 75M 75V 75H

	@Before
	public void setUp() throws Exception {
		if (tipo.equals("75M")) {
			c75 = new Carton75M();
		}else if (tipo.equals("75V")) {
			c75 = new Carton75V();
		}else if (tipo.equals("75H")) {
			c75 = new Carton75H();
		}else {
			fail();
		}

		// carton 75
		// X | 16 |32 |48 |69 |
		// 3 | X |39 |50 |70 |
		// 6 | 22 |X |51 |71 |
		// 7 | 25 |43 |X |73 |
		// 15| 29 |45 |58 |X |
		c75.addNumero(new CeldaCarton(16, 1, 0, 1));
		c75.addNumero(new CeldaCarton(32, 1, 0, 2));
		c75.addNumero(new CeldaCarton(48, 1, 0, 3));
		c75.addNumero(new CeldaCarton(69, 1, 0, 4));
		c75.addNumero(new CeldaCarton(3, 1, 1, 0));
		c75.addNumero(new CeldaCarton(39, 1, 1, 2));
		c75.addNumero(new CeldaCarton(50, 1, 1, 3));
		c75.addNumero(new CeldaCarton(70, 1, 1, 4));
		c75.addNumero(new CeldaCarton(6, 1, 2, 0));
		c75.addNumero(new CeldaCarton(22, 1, 2, 1));
		c75.addNumero(new CeldaCarton(51, 1, 2, 3));
		c75.addNumero(new CeldaCarton(71, 1, 2, 4));
		c75.addNumero(new CeldaCarton(7, 1, 3, 0));
		c75.addNumero(new CeldaCarton(25, 1, 3, 1));
		c75.addNumero(new CeldaCarton(43, 1, 3, 2));
		c75.addNumero(new CeldaCarton(73, 1, 3, 4));
		c75.addNumero(new CeldaCarton(15, 1, 4, 0));
		c75.addNumero(new CeldaCarton(29, 1, 4, 1));
		c75.addNumero(new CeldaCarton(45, 1, 4, 2));
		c75.addNumero(new CeldaCarton(58, 1, 4, 3));
		c75.addEspecial(32);
		c75.addEspecial(3);

	}

	@After
	public void tearDown() throws Exception {
		c75 = null;
	}

	@Test
	public void testCarton() {
		assertNotNull(c75);
	}

	@Test
	public void testRecibirNumero() {
		assertFalse(c75.recibirNumero(1));
		assertTrue(c75.recibirNumero(32));
	}

	@Test
	public void testComprobarNada() {
		c75.recibirNumero(32);
		assertTrue(EstadoCarton.NADA == c75.getEstado());

	}

	@Test
	public void testComprobarEspeciales() {
		assertFalse(c75.comprobarEspeciales());
		c75.recibirNumero(32);
		c75.recibirNumero(3);
		assertTrue(c75.comprobarEspeciales());
		assertTrue(EstadoCarton.ESPECIAL == c75.getEstado());

	}

	@Test
	public void testComprobarLineaBingo() {
		// X | 16 |32 |48 |69 |
		// 3 | X |39 |50 |70 |
		// 6 | 22 |X |51 |71 |
		// 7 | 25 |43 |X |73 |
		// 15| 29 |45 |58 |X |

		c75.recibirNumero(16);
		c75.recibirNumero(32);
		c75.recibirNumero(48);
		c75.recibirNumero(69);
		c75.comprobarLinea();
		assertFalse(c75.comprobarBingo());
		assertTrue(EstadoCarton.LINEA == c75.getEstado());
		assertFalse(EstadoCarton.BINGO == c75.getEstado());

		c75.recibirNumero(3);
		c75.recibirNumero(39);
		c75.recibirNumero(50);
		c75.recibirNumero(70);
		assertFalse(c75.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c75.getEstado());

		c75.recibirNumero(6);
		c75.recibirNumero(22);
		c75.recibirNumero(51);
		c75.recibirNumero(71);
		assertFalse(c75.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c75.getEstado());

		c75.recibirNumero(7);
		c75.recibirNumero(25);
		c75.recibirNumero(43);
		c75.recibirNumero(73);
		assertFalse(c75.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c75.getEstado());

		c75.recibirNumero(15);
		c75.recibirNumero(29);
		c75.recibirNumero(45);
		c75.recibirNumero(58);
		assertTrue(c75.comprobarBingo());
		assertTrue(EstadoCarton.LINEA_BINGO == c75.getEstado());

	}

	@Test
	public void testComprobarEspecialLineaBingo() {

		c75.recibirNumero(16);
		c75.recibirNumero(32);
		c75.recibirNumero(48);
		c75.recibirNumero(69);
		c75.comprobarLinea();
		assertFalse(c75.comprobarBingo());
		assertTrue(EstadoCarton.LINEA == c75.getEstado());
		assertFalse(EstadoCarton.BINGO == c75.getEstado());

		c75.recibirNumero(3);
		c75.comprobarEspeciales();
		c75.recibirNumero(39);
		c75.recibirNumero(50);
		c75.recibirNumero(70);
		assertFalse(c75.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c75.getEstado());

		c75.recibirNumero(6);
		c75.recibirNumero(22);
		c75.recibirNumero(51);
		c75.recibirNumero(71);
		assertFalse(c75.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c75.getEstado());

		c75.recibirNumero(7);
		c75.recibirNumero(25);
		c75.recibirNumero(43);
		c75.recibirNumero(73);
		assertFalse(c75.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c75.getEstado());

		c75.recibirNumero(15);
		c75.recibirNumero(29);
		c75.recibirNumero(45);
		c75.recibirNumero(58);
		assertTrue(c75.comprobarBingo());
		assertTrue(EstadoCarton.ESPECIAL_LINEA_BINGO == c75.getEstado());

	}

	@Test
	public void testComprobarBingo() {

		c75.recibirNumero(16);
		c75.recibirNumero(32);
		c75.recibirNumero(48);
		c75.recibirNumero(69);
		// c75.comprobarLinea(); //no se comprueba y por tanto no se canta
		assertFalse(c75.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c75.getEstado());

		c75.recibirNumero(3);
		c75.recibirNumero(39);
		c75.recibirNumero(50);
		c75.recibirNumero(70);
		assertFalse(c75.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c75.getEstado());

		c75.recibirNumero(6);
		c75.recibirNumero(22);
		c75.recibirNumero(51);
		c75.recibirNumero(71);
		assertFalse(c75.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c75.getEstado());

		c75.recibirNumero(7);
		c75.recibirNumero(25);
		c75.recibirNumero(43);
		c75.recibirNumero(73);
		assertFalse(c75.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c75.getEstado());

		c75.recibirNumero(15);
		c75.recibirNumero(29);
		c75.recibirNumero(45);
		c75.recibirNumero(58);
		assertTrue(c75.comprobarBingo());
		assertTrue(EstadoCarton.BINGO == c75.getEstado());

	}

	@Test
	public void testComprobarEspecialBingo() {

		c75.recibirNumero(16);
		c75.recibirNumero(32);
		c75.recibirNumero(48);
		c75.recibirNumero(69);
		// c75.comprobarLinea(); //no se comprueba y por tanto no se canta
		c75.recibirNumero(3);
		c75.comprobarEspeciales();
		c75.recibirNumero(39);
		c75.recibirNumero(50);
		c75.recibirNumero(70);
		assertFalse(c75.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c75.getEstado());

		c75.recibirNumero(6);
		c75.recibirNumero(22);
		c75.recibirNumero(51);
		c75.recibirNumero(71);
		assertFalse(c75.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c75.getEstado());

		c75.recibirNumero(7);
		c75.recibirNumero(25);
		c75.recibirNumero(43);
		c75.recibirNumero(73);
		assertFalse(c75.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c75.getEstado());

		c75.recibirNumero(15);
		c75.recibirNumero(29);
		c75.recibirNumero(45);
		c75.recibirNumero(58);
		assertTrue(c75.comprobarBingo());
		assertTrue(EstadoCarton.ESPECIAL_BINGO == c75.getEstado());

	}

	@Test
	public void testComprobarEspecialLinea() {
		c75.recibirNumero(3);
		c75.recibirNumero(16);
		c75.recibirNumero(32);
		c75.comprobarEspeciales();
		c75.recibirNumero(48);
		c75.recibirNumero(69);
		c75.comprobarLinea();
		assertTrue(EstadoCarton.ESPECIAL_LINEA == c75.getEstado());

	}

	@Test
	public void testComprobarLinea1() {
		assertFalse(c75.comprobarLinea());
		c75.recibirNumero(16);
		c75.recibirNumero(32);
		assertFalse(c75.comprobarLinea());
		c75.recibirNumero(48);
		assertFalse(c75.comprobarLinea());
		c75.recibirNumero(69);
		assertTrue(c75.comprobarLinea());
		assertTrue(EstadoCarton.LINEA == c75.getEstado());

	}

	@Test
	public void testComprobarLinea2() {
		assertFalse(c75.comprobarLinea());
		c75.recibirNumero(3);
		c75.recibirNumero(39);
		assertFalse(c75.comprobarLinea());
		c75.recibirNumero(50);
		assertFalse(c75.comprobarLinea());
		c75.recibirNumero(70);
		assertTrue(c75.comprobarLinea());
		assertTrue(EstadoCarton.LINEA == c75.getEstado());
	}

	@Test
	public void testComprobarLinea3() {
		assertFalse(c75.comprobarLinea());
		c75.recibirNumero(6);
		c75.recibirNumero(22);
		assertFalse(c75.comprobarLinea());
		c75.recibirNumero(51);
		assertFalse(c75.comprobarLinea());
		c75.recibirNumero(71);
		assertTrue(c75.comprobarLinea());
		assertTrue(EstadoCarton.LINEA == c75.getEstado());
	}

	@Test
	public void testComprobarLinea4() {
		assertFalse(c75.comprobarLinea());
		c75.recibirNumero(7);
		c75.recibirNumero(25);
		assertFalse(c75.comprobarLinea());
		c75.recibirNumero(43);
		assertFalse(c75.comprobarLinea());
		c75.recibirNumero(73);
		assertTrue(c75.comprobarLinea());
		assertTrue(EstadoCarton.LINEA == c75.getEstado());
	}

	@Test
	public void testComprobarLinea5() {
		assertFalse(c75.comprobarLinea());
		c75.recibirNumero(15);
		c75.recibirNumero(29);
		assertFalse(c75.comprobarLinea());
		c75.recibirNumero(45);
		assertFalse(c75.comprobarLinea());
		c75.recibirNumero(58);
		assertTrue(c75.comprobarLinea());
		assertTrue(EstadoCarton.LINEA == c75.getEstado());
	}

	@Test
	public void testRepartirAdHoc() {
		int[] contFilas = new int[5];
		int[] contColumnas = new int[5];
		int contVacias = 0;
		if (tipo.equals("75M")) {
			CeldaCarton[][] c = (CeldaCarton[][]) c75.getNumeros();
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (c[i][j].getFila() == c[i][j].getColumna() && c[i][j].getNumero() != 0) // no puede haber numero
																								// en i y j
						fail();
					//Que el numero esta en la columna correcta
					if (c[i][j].getNumero()!=0 && (c[i][j].getColumna()*15>c[i][j].getNumero()) || ((c[i][j].getColumna()+1)*15<c[i][j].getNumero()))
						fail();
					
					if (i != j && c[i][j].getNumero() != 0) {// Cuento las filas con numeros

						contFilas[c[i][j].getFila()]++; // Cuento numero que hay en filas
						contColumnas[c[i][j].getColumna()]++; // Cuento numero que hay en columnas
					}
				}
			}
			// Compruebo que hay 4 números en cada fila y en cada columna
			for (int i = 0; i < 5; i++) {
				if (contFilas[i] != 4)
					fail();
				if (contColumnas[i] != 4)
					fail();
			}
		} else if (tipo.equals("75V")) {
			CeldaCarton[] c = (CeldaCarton[]) c75.getNumeros();
			for (int i = 1; i <= 75; i++) {
				if (c[i] != null) {
					// no puede haber numero en i y j
					if (c[i].getFila() == c[i].getColumna() && c[i].getNumero() != 0)
						fail("");
					//Que el numero esta en la columna correcta
					if ( (c[i].getColumna()*15>c[i].getNumero()) || ((c[i].getColumna()+1)*15<c[i].getNumero()))
						fail();

					
					if (c[i].getNumero() != 0) {// Cuento las filas con numeros

						contFilas[c[i].getFila()]++; // Cuento numero que hay en filas
						contColumnas[c[i].getColumna()]++; // Cuento numero que hay en columnas
					}
				}
			}
			// Compruebo que hay 4 números en cada fila y en cada columna
			for (int i = 0; i < 5; i++) {
				if (contFilas[i] != 4)
					fail();
				if (contColumnas[i] != 4)
					fail();
			}

		} else if (tipo.equals("75H")) {
			HashMap<Integer, CeldaCarton> c = (HashMap<Integer, CeldaCarton>) c75.getNumeros();
			for (Integer clave : c.keySet()) {
				CeldaCarton ca = c.get(clave);
				// no puede haber numero en i y j
				if (ca.getFila() == ca.getColumna() && ca.getNumero() != 0)
					fail("");
				//Que el numero esta en la columna correcta
				if ( (ca.getColumna()*15>ca.getNumero()) || ((ca.getColumna()+1)*15<ca.getNumero()))
					fail();

				if (ca.getNumero() != 0) {// Cuento las filas con numeros
					contFilas[ca.getFila()]++; // Cuento numero que hay en filas
					contColumnas[ca.getColumna()]++; // Cuento numero que hay en columnas
				}

			}
			// Compruebo que hay 4 números en cada fila y en cada columna
			for (int i = 0; i < 5; i++) {
				if (contFilas[i] != 4)
					fail();
				if (contColumnas[i] != 4)
					fail();
			}
		}

	}
	
	@Test
	public void testRepartirAleatorio() {
		int[] contFilas = new int[5];
		int[] contColumnas = new int[5];
		int contVacias = 0;
		if (tipo.equals("75M")) {
			c75=new Carton75M();
			c75.repartir();
			CeldaCarton[][] c = (CeldaCarton[][]) c75.getNumeros();
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (c[i][j].getFila() == c[i][j].getColumna() && c[i][j].getNumero() != 0) // no puede haber numero
																								// en i y j
						fail();
					//Que el numero esta en la columna correcta
					if (c[i][j].getNumero()!=0 && (c[i][j].getColumna()*15>c[i][j].getNumero()) || ((c[i][j].getColumna()+1)*15<c[i][j].getNumero()))
						fail();
					
					if (i != j && c[i][j].getNumero() != 0) {// Cuento las filas con numeros

						contFilas[c[i][j].getFila()]++; // Cuento numero que hay en filas
						contColumnas[c[i][j].getColumna()]++; // Cuento numero que hay en columnas
					}
				}
			}
			// Compruebo que hay 4 números en cada fila y en cada columna
			for (int i = 0; i < 5; i++) {
				if (contFilas[i] != 4)
					fail();
				if (contColumnas[i] != 4)
					fail();
			}
		} else if (tipo.equals("75V")) {
			c75=new Carton75V();
			c75.repartir();
			CeldaCarton[] c = (CeldaCarton[]) c75.getNumeros();
			for (int i = 1; i <= 75; i++) {
				if (c[i] != null) {
					// no puede haber numero en i y j
					if (c[i].getFila() == c[i].getColumna() && c[i].getNumero() != 0)
						fail("");
					//Que el numero esta en la columna correcta
					if ( (c[i].getColumna()*15>c[i].getNumero()) || ((c[i].getColumna()+1)*15<c[i].getNumero()))
						fail();

					
					if (c[i].getNumero() != 0) {// Cuento las filas con numeros

						contFilas[c[i].getFila()]++; // Cuento numero que hay en filas
						contColumnas[c[i].getColumna()]++; // Cuento numero que hay en columnas
					}
				}
			}
			// Compruebo que hay 4 números en cada fila y en cada columna
			for (int i = 0; i < 5; i++) {
				if (contFilas[i] != 4)
					fail();
				if (contColumnas[i] != 4)
					fail();
			}

		} else if (tipo.equals("75H")) {
			c75=new Carton75H();
			c75.repartir();
			HashMap<Integer, CeldaCarton> c = (HashMap<Integer, CeldaCarton>) c75.getNumeros();
			for (Integer clave : c.keySet()) {
				CeldaCarton ca = c.get(clave);
				// no puede haber numero en i y j
				if (ca.getFila() == ca.getColumna() && ca.getNumero() != 0)
					fail("");
				//Que el numero esta en la columna correcta
				if ( (ca.getColumna()*15>ca.getNumero()) || ((ca.getColumna()+1)*15<ca.getNumero()))
					fail();

				if (ca.getNumero() != 0) {// Cuento las filas con numeros
					contFilas[ca.getFila()]++; // Cuento numero que hay en filas
					contColumnas[ca.getColumna()]++; // Cuento numero que hay en columnas
				}

			}
			// Compruebo que hay 4 números en cada fila y en cada columna
			for (int i = 0; i < 5; i++) {
				if (contFilas[i] != 4)
					fail();
				if (contColumnas[i] != 4)
					fail();
			}
		}

	}

}
