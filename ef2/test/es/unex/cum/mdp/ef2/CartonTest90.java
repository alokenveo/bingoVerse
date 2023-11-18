package es.unex.cum.mdp.ef2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unex.cum.mdp.ef2.carton.Carton90H;
import es.unex.cum.mdp.ef2.carton.Carton90M;
import es.unex.cum.mdp.ef2.carton.Carton90V;
import es.unex.cum.mdp.ef2.carton.EstadoCarton;
import es.unex.cum.mdp.ef2.carton.ICarton;
import es.unex.cum.mdp.ef2.celda.CeldaCarton;

public class CartonTest90 {

	private ICarton c90 = null;
	private String tipo = "90H"; // TODO 80M 80V 80H

	@BeforeEach
	public void setUp() throws Exception {
		
		if (tipo.equals("90M")) {
			c90 = new Carton90M();
		} else if (tipo.equals("90V")) {
			c90 = new Carton90V();
		} else if (tipo.equals("90H")) {
			c90 = new Carton90H();
		} else {
			fail();
		}

		// Para 90

//		1 |	X |	23 |	33 |	X |	52 |	X |	75 |	X |	
//		X |	X |	24 |	38 |	X |	X  |	68|	76 |	84 |	
//		10|	19|	25 |	X  |	50|	X  |	X |	X  |	89 |	

		c90.addNumero(new CeldaCarton( 1, 1, 0, 0));
		c90.addNumero(new CeldaCarton(33, 1, 0, 3));
		c90.addNumero(new CeldaCarton(75, 1, 0, 7));
		c90.addNumero(new CeldaCarton(52, 1, 0, 5));
		c90.addNumero(new CeldaCarton(23, 1, 0, 2));
		
		
		c90.addNumero(new CeldaCarton(68, 1, 1, 6));
		c90.addNumero(new CeldaCarton(38, 1, 1, 3));
		c90.addNumero(new CeldaCarton(76, 1, 1, 7));
		c90.addNumero(new CeldaCarton(84, 1, 1, 8));
		c90.addNumero(new CeldaCarton(24, 1, 1, 2));
				
		c90.addNumero(new CeldaCarton(10, 1, 2, 0));
		c90.addNumero(new CeldaCarton(50, 1, 2, 4));
		c90.addNumero(new CeldaCarton(19, 1, 2, 1));
		c90.addNumero(new CeldaCarton(25, 1, 2, 2));
		c90.addNumero(new CeldaCarton(89, 1, 2, 8));

		c90.addEspecial(1);
		c90.addEspecial(33);
		c90.addEspecial(68);
		c90.addEspecial(38);

	}

	@AfterEach
	public void tearDown() throws Exception {
		c90 = null;
	}

	@Test
	public void testCarton() {
		assertNotNull(c90);
	}

	@Test
	public void testRecibirNumero() {
		assertFalse(c90.recibirNumero(5));
		assertTrue(c90.recibirNumero(1));
	}

	@Test
	public void testComprobarNada() {
		c90.recibirNumero(32);
		assertTrue(EstadoCarton.NADA == c90.getEstado());

	}

	@Test
	public void testComprobarEspeciales() {
		assertFalse(c90.comprobarEspeciales());
		c90.recibirNumero(1);
		c90.recibirNumero(33);
		assertFalse(c90.comprobarEspeciales());
		c90.recibirNumero(68);
		c90.recibirNumero(38);
		assertTrue(c90.comprobarEspeciales());
		assertTrue(EstadoCarton.ESPECIAL == c90.getEstado());

	}

	@Test
	public void testComprobarLineaBingo() {
		c90.recibirNumero(1);
		c90.recibirNumero(23);
		c90.recibirNumero(33);
		c90.recibirNumero(52);
		c90.recibirNumero(75);
		c90.comprobarLinea();
		assertFalse(c90.comprobarBingo());
		assertTrue(EstadoCarton.LINEA == c90.getEstado());
		assertFalse(EstadoCarton.BINGO == c90.getEstado());

		c90.recibirNumero(24);
		c90.recibirNumero(38);
		c90.recibirNumero(68);
		c90.recibirNumero(76);
		c90.recibirNumero(84);
		assertFalse(c90.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c90.getEstado());

		c90.recibirNumero(10);
		c90.recibirNumero(19);
		c90.recibirNumero(25);
		c90.recibirNumero(50);
		c90.recibirNumero(89);
		assertTrue(c90.comprobarBingo());
		assertTrue(EstadoCarton.LINEA_BINGO == c90.getEstado());
	}

	@Test
	public void testComprobarEspecialLineaBingo() {
		c90.recibirNumero(1);
		c90.recibirNumero(23);
		c90.recibirNumero(33);
		c90.recibirNumero(52);
		c90.recibirNumero(75);
		c90.comprobarLinea();

		c90.recibirNumero(24);
		c90.recibirNumero(38);
		c90.recibirNumero(68);
		c90.recibirNumero(76);
		c90.recibirNumero(84);

		c90.recibirNumero(10);
		c90.recibirNumero(19);
		c90.recibirNumero(25);
		c90.recibirNumero(50);
		c90.recibirNumero(89);
		c90.comprobarEspeciales();
		c90.comprobarBingo();
		assertTrue(EstadoCarton.ESPECIAL_LINEA_BINGO == c90.getEstado());
	}

	@Test
	public void testComprobarEspecialBingo() {
		c90.recibirNumero(1);
		c90.recibirNumero(23);
		c90.recibirNumero(33);
		c90.recibirNumero(52);
		c90.recibirNumero(75);

		c90.recibirNumero(24);
		c90.recibirNumero(38);
		c90.recibirNumero(68);
		c90.recibirNumero(76);
		c90.recibirNumero(84);

		c90.recibirNumero(10);
		c90.recibirNumero(19);
		c90.recibirNumero(25);
		c90.recibirNumero(50);
		c90.recibirNumero(89);
		c90.comprobarEspeciales();
		c90.comprobarBingo();
		assertTrue(EstadoCarton.ESPECIAL_BINGO == c90.getEstado());

	}

	@Test
	public void testComprobarBingo() {
		c90.recibirNumero(1);
		c90.recibirNumero(23);
		c90.recibirNumero(33);
		c90.recibirNumero(52);
		c90.recibirNumero(75);
//		c90.comprobarLinea();
		assertFalse(c90.comprobarBingo());
//		assertTrue(EstadoCarton.LINEA == c90.getEstado());
		assertFalse(EstadoCarton.BINGO == c90.getEstado());

		c90.recibirNumero(24);
		c90.recibirNumero(38);
		c90.recibirNumero(68);
		c90.recibirNumero(76);
		c90.recibirNumero(84);
		assertFalse(c90.comprobarBingo());
		assertFalse(EstadoCarton.BINGO == c90.getEstado());

		c90.recibirNumero(10);
		c90.recibirNumero(19);
		c90.recibirNumero(25);
		c90.recibirNumero(50);
		c90.recibirNumero(89);
		assertTrue(c90.comprobarBingo());
		assertTrue(EstadoCarton.BINGO == c90.getEstado());

	}

	@Test
	public void testComprobarLinea1() {
		assertFalse(c90.comprobarLinea());
		c90.recibirNumero(1);
		c90.recibirNumero(23);
		assertFalse(c90.comprobarLinea());
		c90.recibirNumero(33);
		assertFalse(c90.comprobarLinea());
		c90.recibirNumero(52);
		c90.recibirNumero(75);
		assertTrue(c90.comprobarLinea());
		assertTrue(EstadoCarton.LINEA == c90.getEstado());
	}

	@Test
	public void testComprobarEspecialLinea() {
		c90.recibirNumero(1);
		c90.recibirNumero(33);
		c90.recibirNumero(68);
		c90.recibirNumero(38);
		c90.comprobarEspeciales();

		c90.recibirNumero(23);
		c90.recibirNumero(52);
		c90.recibirNumero(75);
		c90.comprobarLinea();
		assertTrue(EstadoCarton.ESPECIAL_LINEA == c90.getEstado());
	}

	@Test
	public void testComprobarLinea2() {
		assertFalse(c90.comprobarLinea());
		c90.recibirNumero(24);
		c90.recibirNumero(38);
		assertFalse(c90.comprobarLinea());
		c90.recibirNumero(68);
		assertFalse(c90.comprobarLinea());
		c90.recibirNumero(76);
		c90.recibirNumero(84);
		assertTrue(c90.comprobarLinea());
		assertTrue(EstadoCarton.LINEA == c90.getEstado());
	}

	@Test
	public void testComprobarLinea3() {
		assertFalse(c90.comprobarLinea());
		c90.recibirNumero(10);
		c90.recibirNumero(19);
		assertFalse(c90.comprobarLinea());
		c90.recibirNumero(25);
		assertFalse(c90.comprobarLinea());
		c90.recibirNumero(50);
		c90.recibirNumero(89);
		assertTrue(c90.comprobarLinea());
		assertTrue(EstadoCarton.LINEA == c90.getEstado());
	}

	@Test
	public void testRepartirAdHoc() {
		int[] contFilas = new int[3];
		int[] contColumnas = new int[9];
		int contVacias = 0;
		if (tipo.equals("90M")) {
			CeldaCarton[][] c = (CeldaCarton[][]) c90.getNumeros();
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 9; j++) {
					// Que el numero esta en la columna correcta
					if (c[i][j] != null) {
						if (c[i][j].getNumero() != 0) {// Cuento las filas con numeros

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
			for (int i = 0; i < 3; i++) {
				// Si las filas no tiene 5 números --> Fallo
				if (contFilas[i] != 5)
					fail();
				// Si hay columnas sin ningún número --> fallo
				if (contColumnas[i] == 0)
					fail();
			}
		} else if (tipo.equals("90V")) {
			CeldaCarton[] c = (CeldaCarton[]) c90.getNumeros();
			for (int i = 1; i < 91; i++) {
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
			for (int i = 0; i < 3; i++) {
				// Si las filas no tiene 5 números --> Fallo
				if (contFilas[i] != 5)
					fail();
				// Si hay columnas sin ningún número --> fallo
				if (contColumnas[i] == 0)
					fail();
			}

		} else if (tipo.equals("90H")) {
			HashMap<Integer, CeldaCarton> c = (HashMap<Integer, CeldaCarton>) c90.getNumeros();
			for (Integer clave : c.keySet()) {
				CeldaCarton ca = c.get(clave);
				if (ca.getNumero() != 0) {// Cuento las filas con numeros
					contFilas[ca.getFila()]++; // Cuento numero que hay en filas
					contColumnas[ca.getColumna()]++; // Cuento numero que hay en columnas
					// Que el numero esta en la columna correcta
					if ((ca.getColumna() * 10 > ca.getNumero()) || ((ca.getColumna() + 1) * 10 < ca.getNumero())) {
						fail();
					}
				}

			}
			// Compruebo que hay 4 números en cada fila y en cada columna
			for (int i = 0; i < 3; i++) {
				// Si las filas no tiene 5 números --> Fallo
				if (contFilas[i] != 5)
					fail();
				// Si hay columnas sin ningún número --> fallo
				if (contColumnas[i] == 0)
					fail();
			}
		}

	}
	
	
	@Test
	public void testRepartirAleatorio() {
		int[] contFilas = new int[3];
		int[] contColumnas = new int[9];
		int contVacias = 0;
		if (tipo.equals("90M")) {
			c90=new Carton90M();
			c90.repartir();
			CeldaCarton[][] c = (CeldaCarton[][]) c90.getNumeros();
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 9; j++) {
					// Que el numero esta en la columna correcta
					if (c[i][j] != null) {
						if (c[i][j].getNumero() != 0) {// Cuento las filas con numeros
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
			for (int i = 0; i < 3; i++) {
				// Si las filas no tiene 5 números --> Fallo
				if (contFilas[i] != 5)
					fail();
				// Si hay columnas sin ningún número --> fallo
				if (contColumnas[i] == 0)
					fail();
			}
		} else if (tipo.equals("90V")) {
			c90=new Carton90V();
			c90.repartir();
			CeldaCarton[] c = (CeldaCarton[]) c90.getNumeros();
			for (int i = 1; i < 91; i++) {
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
			for (int i = 0; i < 3; i++) {
				// Si las filas no tiene 5 números --> Fallo
				if (contFilas[i] != 5)
					fail();
				// Si hay columnas sin ningún número --> fallo
				if (contColumnas[i] == 0)
					fail();
			}

		} else if (tipo.equals("90H")) {
			c90=new Carton90H();
			c90.repartir();
			HashMap<Integer, CeldaCarton> c = (HashMap<Integer, CeldaCarton>) c90.getNumeros();
			for (Integer clave : c.keySet()) {
				CeldaCarton ca = c.get(clave);
				if (ca.getNumero() != 0) {// Cuento las filas con numeros
					contFilas[ca.getFila()]++; // Cuento numero que hay en filas
					contColumnas[ca.getColumna()]++; // Cuento numero que hay en columnas
					// Que el numero esta en la columna correcta
					if ((ca.getColumna() * 10 > ca.getNumero()) || ((ca.getColumna() + 1) * 10 < ca.getNumero())) {
						fail();
					}
				}

			}
			// Compruebo que hay 4 números en cada fila y en cada columna
			for (int i = 0; i < 3; i++) {
				// Si las filas no tiene 5 números --> Fallo
				if (contFilas[i] != 5)
					fail();
				// Si hay columnas sin ningún número --> fallo
				if (contColumnas[i] == 0)
					fail();
			}
		}

	}

}
