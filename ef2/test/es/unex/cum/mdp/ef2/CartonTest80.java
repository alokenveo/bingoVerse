package es.unex.cum.mdp.ef2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.unex.cum.mdp.ef2.carton.Carton80H;
import es.unex.cum.mdp.ef2.carton.Carton80M;
import es.unex.cum.mdp.ef2.carton.Carton80V;
import es.unex.cum.mdp.ef2.carton.EstadoCarton;
import es.unex.cum.mdp.ef2.carton.ICarton;
import es.unex.cum.mdp.ef2.celda.CeldaCarton;

public class CartonTest80 {

	private ICarton c80 = null;
	private String tipo = "80H"; // TODO 80M 80V 80H

	@Before
	public void setUp() throws Exception {
		if (tipo.equals("80M")) {
			c80 = new Carton80M();
		} else if (tipo.equals("80V")) {
			c80 = new Carton80V();
		} else if (tipo.equals("80H")) {
			c80 = new Carton80H();
		} else {
			fail();
		}

		// Carton 80
//		X |	11 | X  |	31 |	42 |	54 |	63 |	X  |	
//		X |	X  | 27 |	X  |	44 |	55 |	65 |	74 |	
//		5 |	15 | 28 |	X  |	47 |	X  |	X  |	78 |	
//		10|	19 | X  |	40 |	X  |	60 |	69 |	X  |

		c80.addNumero(new CeldaCarton(11, 1, 0, 1));
		c80.addNumero(new CeldaCarton(54, 1, 0, 5));
		c80.addNumero(new CeldaCarton(42, 1, 0, 4));
		c80.addNumero(new CeldaCarton(31, 1, 0, 3));
		c80.addNumero(new CeldaCarton(63, 1, 0, 6));

		c80.addNumero(new CeldaCarton(74, 1, 1, 7));
		c80.addNumero(new CeldaCarton(44, 1, 1, 4));
		c80.addNumero(new CeldaCarton(65, 1, 1, 6));
		c80.addNumero(new CeldaCarton(55, 1, 1, 5));
		c80.addNumero(new CeldaCarton(27, 1, 1, 2));

		c80.addNumero(new CeldaCarton(5, 1, 2, 0));
		c80.addNumero(new CeldaCarton(78, 1, 2, 7));
		c80.addNumero(new CeldaCarton(15, 1, 2, 1));
		c80.addNumero(new CeldaCarton(47, 1, 2, 4));
		c80.addNumero(new CeldaCarton(28, 1, 2, 2));

		c80.addNumero(new CeldaCarton(69, 1, 3, 6));
		c80.addNumero(new CeldaCarton(40, 1, 3, 3));
		c80.addNumero(new CeldaCarton(10, 1, 3, 0));
		c80.addNumero(new CeldaCarton(19, 1, 3, 1));
		c80.addNumero(new CeldaCarton(60, 1, 3, 5));

		c80.addEspecial(65);
		c80.addEspecial(5);
		c80.addEspecial(69);

	}

	@After
	public void tearDown() throws Exception {
		c80 = null;
	}

	@Test
	public void testCarton() {
		assertNotNull(c80);
	}

	@Test
	public void testRecibirNumero() {
		assertFalse(c80.recibirNumero(1));
		assertTrue(c80.recibirNumero(5));
	}

	@Test
	public void testComprobarNada() {
		c80.recibirNumero(32);
		assertTrue(EstadoCarton.NADA == c80.getEstado());

	}

	@Test
	public void testComprobarEspeciales() {
		assertFalse(c80.comprobarEspeciales());
		c80.recibirNumero(65);
		c80.recibirNumero(5);
		assertFalse(c80.comprobarEspeciales());
		c80.recibirNumero(69);
		assertTrue(c80.comprobarEspeciales());
		assertTrue(EstadoCarton.ESPECIAL == c80.getEstado());

	}

	@Test
	public void testComprobarEspecialLineaBingo() {
		c80.recibirNumero(11);
		c80.recibirNumero(31);
		c80.recibirNumero(42);
		c80.recibirNumero(54);
		c80.recibirNumero(63);
		c80.comprobarLinea();
		c80.recibirNumero(27);
		c80.recibirNumero(44);
		c80.recibirNumero(55);
		c80.recibirNumero(65);
		c80.recibirNumero(74);

		c80.recibirNumero(5);
		c80.recibirNumero(15);
		c80.recibirNumero(28);
		c80.recibirNumero(47);
		c80.recibirNumero(78);

		c80.recibirNumero(10);
		c80.recibirNumero(19);
		c80.recibirNumero(40);
		c80.recibirNumero(60);
		c80.recibirNumero(69);
		c80.comprobarEspeciales();
		c80.comprobarLinea();
		c80.comprobarBingo();
		assertTrue(EstadoCarton.ESPECIAL_LINEA_BINGO == c80.getEstado());

	}

	@Test
	public void testComprobarLineaBingo() {
		c80.recibirNumero(11);
		c80.recibirNumero(31);
		c80.recibirNumero(42);
		c80.recibirNumero(54);
		c80.recibirNumero(63);
		c80.comprobarLinea();
		assertFalse(c80.comprobarBingo());
		assertTrue(EstadoCarton.LINEA == c80.getEstado());
		assertFalse(EstadoCarton.BINGO == c80.getEstado());

		c80.recibirNumero(27);
		c80.recibirNumero(44);
		c80.recibirNumero(55);
		c80.recibirNumero(65);
		c80.recibirNumero(74);
		assertFalse(c80.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c80.getEstado());

		c80.recibirNumero(5);
		c80.recibirNumero(15);
		c80.recibirNumero(28);
		c80.recibirNumero(47);
		c80.recibirNumero(78);
		assertFalse(c80.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c80.getEstado());

		c80.recibirNumero(10);
		c80.recibirNumero(19);
		c80.recibirNumero(40);
		c80.recibirNumero(60);
		c80.recibirNumero(69);
		assertTrue(c80.comprobarBingo());
		assertTrue(EstadoCarton.LINEA_BINGO == c80.getEstado());

	}

	@Test
	public void testComprobarBingo() {
		c80.recibirNumero(11);
		c80.recibirNumero(31);
		c80.recibirNumero(42);
		c80.recibirNumero(54);
		c80.recibirNumero(63);
		// c80.comprobarLinea();
		assertFalse(c80.comprobarBingo());
		// assertTrue(EstadoCarton.LINEA == c80.getEstado());
		assertFalse(EstadoCarton.BINGO == c80.getEstado());

		c80.recibirNumero(27);
		c80.recibirNumero(44);
		c80.recibirNumero(55);
		c80.recibirNumero(65);
		c80.recibirNumero(74);
		assertFalse(c80.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c80.getEstado());

		c80.recibirNumero(5);
		c80.recibirNumero(15);
		c80.recibirNumero(28);
		c80.recibirNumero(47);
		c80.recibirNumero(78);
		assertFalse(c80.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c80.getEstado());

		c80.recibirNumero(10);
		c80.recibirNumero(19);
		c80.recibirNumero(40);
		c80.recibirNumero(60);
		c80.recibirNumero(69);
		assertTrue(c80.comprobarBingo());
		assertTrue(EstadoCarton.BINGO == c80.getEstado());

	}

	@Test
	public void testComprobarEspecialBingo() {
		c80.recibirNumero(11);
		c80.recibirNumero(31);
		c80.recibirNumero(42);
		c80.recibirNumero(54);
		c80.recibirNumero(63);
		// c80.comprobarLinea();
		assertFalse(c80.comprobarBingo());
		// assertTrue(EstadoCarton.LINEA == c80.getEstado());
		assertFalse(EstadoCarton.BINGO == c80.getEstado());

		c80.recibirNumero(27);
		c80.recibirNumero(44);
		c80.recibirNumero(55);
		c80.recibirNumero(65);
		c80.recibirNumero(74);
		assertFalse(c80.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c80.getEstado());

		c80.recibirNumero(5);
		c80.recibirNumero(15);
		c80.recibirNumero(28);
		c80.recibirNumero(47);
		c80.recibirNumero(78);
		assertFalse(c80.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c80.getEstado());

		c80.recibirNumero(10);
		c80.recibirNumero(19);
		c80.recibirNumero(40);
		c80.recibirNumero(60);
		c80.recibirNumero(69);
		c80.comprobarEspeciales();
		assertTrue(c80.comprobarBingo());
		assertTrue(EstadoCarton.ESPECIAL_BINGO == c80.getEstado());

	}

	@Test
	public void testComprobarEspecialLinea() {
		c80.recibirNumero(65);
		c80.recibirNumero(5);
		c80.recibirNumero(69);
		c80.comprobarEspeciales();
		c80.recibirNumero(11);
		c80.recibirNumero(31);
		c80.recibirNumero(42);
		c80.recibirNumero(54);
		c80.recibirNumero(63);
		c80.comprobarLinea();
		assertTrue(EstadoCarton.ESPECIAL_LINEA == c80.getEstado());

	}

	@Test
	public void testComprobarLinea1() {
		assertFalse(c80.comprobarLinea());
		c80.recibirNumero(11);
		c80.recibirNumero(31);
		assertFalse(c80.comprobarLinea());
		c80.recibirNumero(42);
		assertFalse(c80.comprobarLinea());
		c80.recibirNumero(54);
		c80.recibirNumero(63);
		assertTrue(c80.comprobarLinea());
		assertTrue(EstadoCarton.LINEA == c80.getEstado());

	}

	@Test
	public void testComprobarLinea2() {
		assertFalse(c80.comprobarLinea());
		c80.recibirNumero(27);
		c80.recibirNumero(44);
		assertFalse(c80.comprobarLinea());
		c80.recibirNumero(55);
		assertFalse(c80.comprobarLinea());
		c80.recibirNumero(65);
		c80.recibirNumero(74);
		assertTrue(c80.comprobarLinea());
		assertTrue(EstadoCarton.LINEA == c80.getEstado());
	}

	@Test
	public void testComprobarLinea3() {
		assertFalse(c80.comprobarLinea());
		c80.recibirNumero(5);
		c80.recibirNumero(15);
		assertFalse(c80.comprobarLinea());
		c80.recibirNumero(28);
		assertFalse(c80.comprobarLinea());
		c80.recibirNumero(47);
		c80.recibirNumero(78);
		assertTrue(c80.comprobarLinea());
		assertTrue(EstadoCarton.LINEA == c80.getEstado());
	}

	@Test
	public void testComprobarLinea4() {
		assertFalse(c80.comprobarLinea());
		c80.recibirNumero(10);
		c80.recibirNumero(19);
		assertFalse(c80.comprobarLinea());
		c80.recibirNumero(40);
		assertFalse(c80.comprobarLinea());
		c80.recibirNumero(60);
		c80.recibirNumero(69);
		assertTrue(c80.comprobarLinea());
		assertTrue(EstadoCarton.LINEA == c80.getEstado());
	}

	@Test
	public void testRepartirAdHoc() {
		int[] contFilas = new int[4];
		int[] contColumnas = new int[8];
		int contVacias = 0;
		if (tipo.equals("80M")) {
			CeldaCarton[][] c = (CeldaCarton[][]) c80.getNumeros();
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 8; j++) {
					// Que el numero esta en la columna correcta
					if (c[i][j] != null) {
						if (c[i][j].getNumero() != 0) {// Cuento las filas con numero
							contFilas[c[i][j].getFila()]++; // Cuento numero que hay en filas
							contColumnas[c[i][j].getColumna()]++; // Cuento numero que hay en columnas
							if ((c[i][j].getColumna() * 10 > c[i][j].getNumero())
									|| ((c[i][j].getColumna() + 1) * 10 < c[i][j].getNumero()))
								fail();

						}
					}
				}
			}
			// Compruebo que hay 4 números en cada fila y en cada columna
			for (int i = 0; i < 4; i++) {
				// Si las filas no tiene 5 números --> Fallo
				if (contFilas[i] != 5)
					fail();
			}
			// Si hay columnas sin ningún número --> fallo
			for (int i = 0; i < 8; i++) {
				if (contColumnas[i] == 0)
					fail();
			}
		} else if (tipo.equals("80V")) {
			CeldaCarton[] c = (CeldaCarton[]) c80.getNumeros();
			for (int i = 1; i < 81; i++) {
				if (c[i] != null) {
					// no puede haber numero en i y j
					if (c[i].getNumero() != 0) {// Cuento las filas con numeros
						contFilas[c[i].getFila()]++; // Cuento numero que hay en filas
						contColumnas[c[i].getColumna()]++; // Cuento numero que hay en columnas
						// Que el numero esta en la columna correcta
						if ((c[i].getColumna() * 10 > c[i].getNumero())
								|| ((c[i].getColumna() + 1) * 10 < c[i].getNumero())) {
							fail();
						}

					}
				}
			}
			// Compruebo que hay 4 números en cada fila y en cada columna
			for (int i = 0; i < 4; i++) {
				// Si las filas no tiene 5 números --> Fallo
				if (contFilas[i] != 5)
					fail();
			}
			// Si hay columnas sin ningún número --> fallo
			for (int i = 0; i < 8; i++) {
				if (contColumnas[i] == 0)
					fail();
			}

		} else if (tipo.equals("80H")) {
			HashMap<Integer, CeldaCarton> c = (HashMap<Integer, CeldaCarton>) c80.getNumeros();
			for (Integer clave : c.keySet()) {
				CeldaCarton ca = c.get(clave);
				// Que el numero esta en la columna correcta
				if (ca.getNumero() != 0) {// Cuento las filas con numeros
					contFilas[ca.getFila()]++; // Cuento numero que hay en filas
					contColumnas[ca.getColumna()]++; // Cuento numero que hay en columnas
					if ((ca.getColumna() * 10 > ca.getNumero()) || ((ca.getColumna() + 1) * 10 < ca.getNumero())) {
						fail();
					}

				}

			}
			// Compruebo que hay 4 números en cada fila y en cada columna
			for (int i = 0; i < 4; i++) {
				// Si las filas no tiene 5 números --> Fallo
				if (contFilas[i] != 5)
					fail();
			}
			// Si hay columnas sin ningún número --> fallo
			for (int i = 0; i < 8; i++) {
				if (contColumnas[i] == 0)
					fail();
			}
		}

	}

	@Test
	public void testRepartirAleatorio() {
		int[] contFilas = new int[4];
		int[] contColumnas = new int[8];
		int contVacias = 0;
		if (tipo.equals("80M")) {
			c80 = new Carton80M();
			c80.repartir();
			CeldaCarton[][] c = (CeldaCarton[][]) c80.getNumeros();
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 8; j++) {
					if (c[i][j] != null) {
						if (c[i][j].getNumero() != 0) {// Cuento las filas con numeros
							contFilas[c[i][j].getFila()]++; // Cuento numero que hay en filas
							contColumnas[c[i][j].getColumna()]++; // Cuento numero que hay en columnas
							// Que el numero esta en la columna correcta
							if ((c[i][j].getColumna() * 10 > c[i][j].getNumero())
									|| ((c[i][j].getColumna() + 1) * 10 < c[i][j].getNumero())) {
								fail();
							}

						}
					}
				}
			}
			// Compruebo que hay 4 números en cada fila y en cada columna
			for (int i = 0; i < 4; i++) {
				// Si las filas no tiene 5 números --> Fallo
				if (contFilas[i] != 5)
					fail();
			}
			// Si hay columnas sin ningún número --> fallo
			for (int i = 0; i < 8; i++) {
				if (contColumnas[i] == 0)
					fail();
			}
		} else if (tipo.equals("80V")) {
			c80 = new Carton80V();
			c80.repartir();
			CeldaCarton[] c = (CeldaCarton[]) c80.getNumeros();
			for (int i = 1; i < 81; i++) {
				if (c[i] != null) {
					// no puede haber numero en i y j

					if (c[i].getNumero() != 0) {// Cuento las filas con numeros
						contFilas[c[i].getFila()]++; // Cuento numero que hay en filas
						contColumnas[c[i].getColumna()]++; // Cuento numero que hay en columnas
						// Que el numero esta en la columna correcta
						if ((c[i].getColumna() * 10 > c[i].getNumero())
								|| ((c[i].getColumna() + 1) * 10 < c[i].getNumero())) {
							fail();
						}

					}
				}
			}
			// Compruebo que hay 4 números en cada fila y en cada columna
			for (int i = 0; i < 4; i++) {
				// Si las filas no tiene 5 números --> Fallo
				if (contFilas[i] != 5)
					fail();
			}
			
			// Si hay columnas sin ningún número --> fallo
			for (int i = 0; i < 8; i++) {
				if (contColumnas[i] == 0)
					fail();
			}
		} else if (tipo.equals("80H")) {
			c80 = new Carton80H();
			c80.repartir();
			HashMap<Integer, CeldaCarton> c = (HashMap<Integer, CeldaCarton>) c80.getNumeros();
			for (Integer clave : c.keySet()) {
				CeldaCarton ca = c.get(clave);
				// Que el numero esta en la columna correcta

				if (ca.getNumero() != 0) {// Cuento las filas con numeros
					contFilas[ca.getFila()]++; // Cuento numero que hay en filas
					contColumnas[ca.getColumna()]++; // Cuento numero que hay en columnas
					if ((ca.getColumna() * 10 > ca.getNumero()) || ((ca.getColumna() + 1) * 10 < ca.getNumero())) {
						fail();
					}
				}

			}
			// Compruebo que hay 4 números en cada fila y en cada columna
			for (int i = 0; i < 4; i++) {
				// Si las filas no tiene 5 números --> Fallo
				if (contFilas[i] != 5)
					fail();
			}
			// Si hay columnas sin ningún número --> fallo
			for (int i = 0; i < 8; i++) {
				if (contColumnas[i] == 0)
					fail();
			}
		}

	}

}
