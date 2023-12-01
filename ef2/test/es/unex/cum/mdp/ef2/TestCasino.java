package es.unex.cum.mdp.ef2;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unex.cum.mdp.ef2.bingo.Bingo;
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
import es.unex.cum.mdp.ef2.carton.CartonBuilder;
import es.unex.cum.mdp.ef2.carton.ICarton;
import es.unex.cum.mdp.ef2.celda.CeldaCarton;

/*
	Bateria de test para Casino
	
	Importante: 
		Cambiar en la bateria Carton80H por el Carton que se tenga
		Cmabiar en el atributo tipo por el que se tenga
*/

public class TestCasino {
	private Casino c;
	// Cambiar por 75V, 75h, 75M, 80V, 80H, 80M,90V, 90H, 90M
	private String tipo = "80H"; // 75M 80H 90V
	private float precio = 3.0F;
	private float saldo = 200.0F;

	@BeforeEach
	public void setUp() throws Exception {
		// Se realiza la instancia de onlae y se registran 10 usuarios para las
		// distintas pruebas
		c = new Casino();
		for (int i = 0; i < 10; i++) {
			c.register(String.valueOf(i), String.valueOf(i), String.valueOf(i), saldo);
		}

	}

	@AfterEach
	public void tearDown() throws Exception {
		c = null;
	}

	@Test
	public void testLogin() {
		c.register("50", "Luis", "50", saldo);
		try {
			assertEquals(c.login("50", "50").getNombre(), "Luis");
		} catch (UsuarioNoAutenticado e1) {
			fail();
		}
		try {
			c.login("40", "50");
			fail();
		} catch (UsuarioNoAutenticado e) {

		}
		try {
			c.login("50", "40");
			fail();
		} catch (UsuarioNoAutenticado e) {
		}
	}

	@Test
	public void testRegister() {
		assertTrue(c.register("50", "Luis", "50", saldo));
		assertFalse(c.register("50", "Luis", "50", saldo));
		assertTrue(c.register("51", "Luis", "51", saldo));
	}

	@Test
	public void testAddMonedero() {
		c.register("50", "Luis", "50", saldo); // Se registra
		float monedero = 0;
		;
		try {
			monedero = c.login("50", "50").getMonedero() + saldo;
		} catch (UsuarioNoAutenticado e) {
			fail();
		}
		assertTrue(c.addMonedero("50", "50", saldo)); // añado saldo mas
		assertFalse(c.addMonedero("30", "50", saldo)); // no existe usuario
		assertFalse(c.addMonedero("50", "30", saldo)); // password mal
		Usuario u = null;
		try {
			u = c.login("50", "50");
		} catch (UsuarioNoAutenticado e) {
			fail();

		} // recupero su monedero
		assertTrue(monedero == u.getMonedero()); // compruebo
		assertTrue(u.getHistorico().size() == 1); // Hay un movimiento en el historico

	}

	@Test
	public void TestcrearBingo() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.NOVEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 20);
		Date fecha = cal.getTime();

		// TODO: Cambiar por el sorteo
		// Se crea el sorteo
		Bingo s = c.crearBingo(tipo, fecha, 10000.00F, precio);
		assertNotNull(s);

		// Comprobaciones
		assertEquals(s.getFecha(), fecha);

		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.NOVEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 27);
		fecha = cal.getTime();

		// Se crea el sorteo
		Bingo s1 = c.crearBingo(tipo, fecha, 10000.00F, precio);
		assertNotNull(s);
		Bingo s2 = c.crearBingo(tipo, fecha, 10000.00F, precio);
		assertNull(s2);

		// Comprobaciones
		assertEquals(s1.getFecha(), fecha);

	}

	@Test
	public void testAdherirseBingo() {
		// se crea el bingo
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.NOVEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 20);
		Date fecha = cal.getTime();

		Bingo s = c.crearBingo(tipo, fecha, 1000.00F, precio);

		// Se crea el usuario con 100€ de monedero
		c.register("50", "Luis", "50", saldo);
		c.register("51", "Juan", "51", 0); // SIn dinero
		c.register("52", "Juan", "52", saldo); // SIn dinero

		// Intento jugar con el usuario sin dinero
		assertNull(c.adherirseCarton("51", "51", fecha, tipo));

		// Se juega al bingo creado
		ICarton b = c.adherirseCarton("50", "50", fecha, tipo);
		assertNotNull(b);
		assertNull(c.adherirseCarton("30", "50", fecha, tipo));
		assertNull(c.adherirseCarton("50", "30", fecha, tipo));

		// Otro carton en otro jugador
		ICarton b2 = c.adherirseCarton("52", "52", fecha, tipo);
		assertNotNull(b);

		// Comprobaciones a nivel de usuario
		Usuario u = null;
		try {
			u = c.login("50", "50");
		} catch (UsuarioNoAutenticado e) {
			fail();
		}
		assertTrue(u.getMonedero() == (saldo - b.getPrecio())); // Se ha descontado de su monedero
		assertTrue(u.getHistorico().size() == 1);
		Usuario u2 = null;
		try {
			u2 = c.login("52", "52");
		} catch (UsuarioNoAutenticado e) {
			fail();
		}
		assertTrue(u2.getMonedero() == (saldo - b.getPrecio())); // Se ha descontado de su monedero
		assertTrue(u2.getHistorico().size() == 1);

		// Comprobaciones con el sorteo
		assertFalse(s.isJugado());
		assertTrue(s.getRecaudacion() == 1000 + (2 * b.getPrecio())); // 1000 bote+dos boletos
		assertTrue(s.getCartones().size() == 2);
		assertTrue(s.getFecha().equals(fecha));

		// Comprobaciones con el carton
		CartonBuilder cb = new CartonBuilder(tipo);
		assertEquals(b.getUser().getNick(), u.getNick());
		assertEquals(b2.getUser().getNick(), u2.getNick());
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

			// Especial
			l.add(3);
			l.add(32);
			// Linea
			l.add(16);
			l.add(48);
			l.add(69);
			// Bingo
			l.add(6);
			l.add(70);
			l.add(7);
			l.add(39);
			l.add(71);
			l.add(73);
			l.add(43);
			l.add(45);
			l.add(15);
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

			// Especial
			l.add(65);
			l.add(5);
			l.add(69);

			// Linea
			l.add(54);
			l.add(42);
			l.add(11);
			l.add(31);
			l.add(63);
			// Bingo
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

		} else if (tipo.contains("90")) {
			// Para 90

//			1 |	X |	23 |	33 |	X |	52 |	X |	75 |	X |	
//			X |	X |	24 |	38 |	X |	X  |	68|	76 |	84 |	
//			10|	19|	25 |	X  |	50|	X  |	X |	X  |	89 |	
			// Especial
			l.add(1);
			l.add(68);
			l.add(38);
			l.add(33);
			// Linea
			l.add(23);
			l.add(52);
			l.add(75);
			// Bingo
			l.add(10);
			l.add(76);
			l.add(50);
			l.add(19);
			l.add(84);
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

			c.addNumero(new CeldaCarton(65, 1, 1, 6));
			c.addNumero(new CeldaCarton(5, 1, 2, 0));
			c.addNumero(new CeldaCarton(69, 1, 3, 6));

			c.addNumero(new CeldaCarton(54, 1, 0, 5));
			c.addNumero(new CeldaCarton(42, 1, 0, 4));
			c.addNumero(new CeldaCarton(11, 1, 0, 1));
			c.addNumero(new CeldaCarton(31, 1, 0, 3));
			c.addNumero(new CeldaCarton(63, 1, 0, 6));

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

	public ICarton crearCartonCorrectoSinBingo(String tipo) {
		ICarton c = null;

		if (tipo.contains("75")) {
			// carton 75
			// X | 16 |32 |48 |69 |
			// 3 | X |39 |50 |70 |
			// 6 | 22 |X |51 |71 |
			// 7 | 25 |43 |X |73 |
			// 15| 28 |45 |58 |X |
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
			c.addNumero(new CeldaCarton(28, 1, 4, 1));
			c.addEspecial(32);
			c.addEspecial(3);
		} else if (tipo.contains("80")) {
			// Carton 80
//			X |	11 | X  |	31 |	42 |	54 |	63 |	X  |	
//			X |	X  | 27 |	X  |	44 |	55 |	65 |	74 |	
//			5 |	15 | 28 |	X  |	47 |	X  |	X  |	78 |	
//			10|	18 | X  |	40 |	X  |	60 |	69 |	X  |

			if (tipo.equals("80H")) {
				c = new Carton80H();
			} else if (tipo.equals("80V")) {
				c = new Carton80V();
			} else {
				c = new Carton80M();
			}
			c.addNumero(new CeldaCarton(65, 1, 1, 6));
			c.addNumero(new CeldaCarton(5, 1, 2, 0));
			c.addNumero(new CeldaCarton(69, 1, 3, 6));

			c.addNumero(new CeldaCarton(54, 1, 0, 5));
			c.addNumero(new CeldaCarton(42, 1, 0, 4));
			c.addNumero(new CeldaCarton(11, 1, 0, 1));
			c.addNumero(new CeldaCarton(31, 1, 0, 3));
			c.addNumero(new CeldaCarton(63, 1, 0, 6));

			c.addNumero(new CeldaCarton(40, 1, 3, 3));
			c.addNumero(new CeldaCarton(10, 1, 3, 0));
			c.addNumero(new CeldaCarton(74, 1, 1, 7));
			c.addNumero(new CeldaCarton(44, 1, 1, 4));
			c.addNumero(new CeldaCarton(78, 1, 2, 7));
			c.addNumero(new CeldaCarton(15, 1, 2, 1));
			c.addNumero(new CeldaCarton(47, 1, 2, 4));
			c.addNumero(new CeldaCarton(18, 1, 3, 1));
			c.addNumero(new CeldaCarton(55, 1, 1, 5));
			c.addNumero(new CeldaCarton(27, 1, 1, 2));
			c.addNumero(new CeldaCarton(28, 1, 2, 2));
			c.addNumero(new CeldaCarton(60, 1, 3, 5));
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
//			10|	19|	25 |	X  |	50|	X  |	X |	X  |	88 |	

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
			c.addNumero(new CeldaCarton(88, 1, 2, 8));

			c.addEspecial(1);
			c.addEspecial(33);
			c.addEspecial(68);
			c.addEspecial(38);

		}

		return c;
	}

	public ICarton crearCartonCorrectoSinLinea(String tipo) {
		ICarton c = null;

		if (tipo.contains("75")) {
			// carton 75
			// X | 16 |32 |48 |68 |
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
			c.addNumero(new CeldaCarton(68, 1, 0, 4));
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
//			X |	11 | X  |	31 |	42 |	54 |	62 |	X  |	
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
			c.addNumero(new CeldaCarton(62, 1, 0, 6));

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

//			1 |	X |	23 |	33 |	X |	52 |	X |	74 |	X |	
//			X |	X |	24 |	38 |	X |	X  |	68|	76 |	84 |	
//			10|	19|	25 |	X  |	50|	X  |	X |	X  |	89 |	

			c.addNumero(new CeldaCarton(1, 1, 0, 0));
			c.addNumero(new CeldaCarton(33, 1, 0, 3));
			c.addNumero(new CeldaCarton(68, 1, 1, 6));
			c.addNumero(new CeldaCarton(38, 1, 1, 3));
			c.addNumero(new CeldaCarton(10, 1, 2, 0));
			c.addNumero(new CeldaCarton(74, 1, 0, 7));
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

			c.addEspecial(1);
			c.addEspecial(33);
			c.addEspecial(68);
			c.addEspecial(38);

		}

		return c;
	}

	@Test
	public void testJugarBingo() {
		// se crea el sorteo
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.NOVEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 20);
		Date fecha = cal.getTime();

		// TODO: Cambiar por el sorteo
		Bingo s = c.crearBingo(tipo, fecha, 0.00F, precio);

		// Se crea 10 usuarios que van a ganar
		for (int i = 11; i <= 21; i++) {
			c.register(String.valueOf(i), String.valueOf(i), String.valueOf(i), saldo);
		}

		// Se logean con 10 usuarios y cada uno juega 10 cartones que no ganan
		for (int i = 11; i < 21; i++) {
			for (int j = 0; j < 10; j++) {
				ICarton perdido = crearCartonIncorrecto(tipo);
				ICarton b = c.adherirseCarton(String.valueOf(i), String.valueOf(i), fecha, tipo);
				b.setNumeros(perdido.getNumeros());
			}

		}

		// Creo el cartón correcto
		ICarton ganado = crearCartonCorrecto(tipo);
		// Se lo asigno al usuario 11 el cartón correcto
		ICarton b = c.adherirseCarton("11", "11", fecha, tipo);
		b.setNumeros(ganado.getNumeros());

		// Antes de jugar comprueba el número de cartones
		// Comprobar la lista de un jugador (10 cartones)
		Usuario u = null;
		for (int i = 12; i < 21; i++) {
			u = c.getUsuarios().get(String.valueOf(i));
			assertTrue(u.getCartones().size() == 10);
		}

		u = c.getUsuarios().get("11"); // 10 + 1 del carton ganador
		assertTrue(u.getCartones().size() == 11);

		// Se juega el sorteo
		List<Integer> l = ListCorrecto(tipo);
		assertFalse(s.isJugado());
		assertTrue(c.jugar(fecha, l));
		assertFalse(c.jugar(fecha, l)); // Volvemos a intentar jugarlos
		assertTrue(s.getRecaudacion() == 101 * b.getPrecio()); // 10 jugador + 10 cartones +1

		// Compruebo los movimiento del jugador con 10 cartones y con 11
		for (int i = 12; i < 21; i++) {
			u = c.getUsuarios().get(String.valueOf(i));
			assertTrue(u.getHistorico().size() == 10); // 10 cartones
		}

		u = c.getUsuarios().get("11"); // 10 + 1 del carton ganador
		assertTrue(u.getHistorico().size() == 12); // 10 cartones + 1 Carton ganador +1 mov de ganar

		// Compruebo el dinero del jugador con 10 cartones y con 11

		for (int i = 12; i < 21; i++) {
			u = c.getUsuarios().get(String.valueOf(i));
			assertTrue(u.getMonedero() == (saldo - 10 * b.getPrecio())); // 100 - 10 cartones
		}

		// Compruebo las estadísticas.
		Estadistica[] e = c.getEstadistica();
		if (tipo.contains("75")) {
			// Especial con el 38
			assertTrue(e[32].getNumEspecial() == 1);
			// Línea con el 23
			assertTrue(e[69].getNumLinea() == 1);
			// Bingo con el 89
			assertTrue(e[29].getNumBingo() == 1);
		} else if (tipo.contains("80")) {
			// Especial con el 38
			assertTrue(e[69].getNumEspecial() == 1);
			// Línea con el 23
			assertTrue(e[63].getNumLinea() == 1);
			// Bingo con el 89
			assertTrue(e[60].getNumBingo() == 1);
		} else {
			// Especial con el 38
			assertTrue(e[33].getNumEspecial() == 1);
			// Línea con el 23
			assertTrue(e[75].getNumLinea() == 1);
			// Bingo con el 89
			assertTrue(e[89].getNumBingo() == 1);
		}
		// Compruebo que los números cantados están a 1
		Iterator it = l.iterator();
		while (it.hasNext()) {
			Integer aux = (Integer) it.next();
			assertTrue(e[aux.intValue()].getNumSacado() == 1);
		}
		
		

		//////////////////////////
		// Comprobación del reparto. Punto Adicional
		//////////////////////////

		Reparto r = c.getBingos().get(fecha).getReparto();
		// Carton ganador
		u = c.getUsuarios().get("11"); // 10 + 1 del carton ganador
		float total = (saldo - 11 * b.getPrecio() + 2 + r.getRepartoBingo() + r.getRepartoLinea());
		assertTrue(u.getMonedero() == round(total, 2));

		// Compruebo el reparto
		String d = String.valueOf(fecha.getYear()) + String.valueOf(fecha.getMonth()) + String.valueOf(fecha.getDay());
		assertNotNull(r);
		// La caja el 10%
		assertTrue(r.getCaja() == round((s.getRecaudacion()) / 10, 2));
		assertTrue(r.getNumBingo() == 1);
		assertTrue(r.getNumLineas() == 1);
		assertTrue(r.getNumEspeciales() == 1);
		// Especiales 2€ por especial. Solo uno
		assertTrue(r.getRepartoEspeciales() == 2);
		assertTrue(r.getRepartoBingo() == round((s.getRecaudacion() - r.getCaja() - 2) * 0.7F, 2));
		assertTrue(r.getRepartoLinea() == round((s.getRecaudacion() - r.getCaja() - 2) * 0.3F, 2));

	}

	// Tres premiados con todo
	@Test
	public void testJugarBingo2() {
		// se crea el sorteo
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.NOVEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 20);
		Date fecha = cal.getTime();

		// TODO: Cambiar por el sorteo
		Bingo s = c.crearBingo(tipo, fecha, 0.00F, precio);

		// Se crea 10 usuarios que van a ganar
		for (int i = 11; i <= 21; i++) {
			c.register(String.valueOf(i), String.valueOf(i), String.valueOf(i), 100);
		}

		// Se logean con 10 usuarios y cada uno juega 10 cartones que no ganan
		for (int i = 11; i < 21; i++) {
			for (int j = 0; j < 10; j++) {
				ICarton perdido = crearCartonIncorrecto(tipo);
				ICarton b = c.adherirseCarton(String.valueOf(i), String.valueOf(i), fecha, tipo);
				b.setNumeros(perdido.getNumeros());
			}

		}

		// Creo el cartón correcto
		ICarton ganado = crearCartonCorrecto(tipo);
		// Se lo asigno al usuario 11 el cartón correcto
		ICarton b = c.adherirseCarton("11", "11", fecha, tipo);
		b.setNumeros(ganado.getNumeros());

		ganado = crearCartonCorrecto(tipo);
		b = c.adherirseCarton("12", "12", fecha, tipo);
		b.setNumeros(ganado.getNumeros());

		ganado = crearCartonCorrecto(tipo);
		b = c.adherirseCarton("13", "13", fecha, tipo);
		b.setNumeros(ganado.getNumeros());

		// Antes de jugar comprueba el número de cartones
		// Comprobar la lista de un jugador (10 cartones)
		Usuario u = null;
		for (int i = 11; i < 14; i++) {
			u = c.getUsuarios().get(String.valueOf(i));
			assertTrue(u.getCartones().size() == 11);
		}

		for (int i = 14; i < 21; i++) {
			u = c.getUsuarios().get(String.valueOf(i));
			assertTrue(u.getCartones().size() == 10);
		}

		// Se juega el sorteo
		List<Integer> l = ListCorrecto(tipo);
		assertFalse(s.isJugado());
		assertTrue(c.jugar(fecha, l));
		assertFalse(c.jugar(fecha, l)); // Volvemos a intentar jugarlos
		assertTrue(s.getRecaudacion() == 100 * b.getPrecio() + 3 * b.getPrecio()); // 10 jugador + 10 cartones +1

		// Compruebo los movimiento del jugador con 11 cartones
		for (int i = 11; i < 14; i++) {
			u = c.getUsuarios().get(String.valueOf(i));
			assertTrue(u.getHistorico().size() == 12); // 10 cartones + 1 Carton ganador +1 mov de ganar
		}
		// Compruebo los movimiento del jugador con 10 cartones
		for (int i = 14; i < 21; i++) {
			u = c.getUsuarios().get(String.valueOf(i));
			assertTrue(u.getHistorico().size() == 10); // 10 cartones
		}

		// Compruebo las estadísticas. Cambiar
		Estadistica[] e = c.getEstadistica();
		if (tipo.contains("75")) {
			// Especial con el 38
			assertTrue(e[32].getNumEspecial() == 1);
			// Línea con el 23
			assertTrue(e[69].getNumLinea() == 1);
			// Bingo con el 89
			assertTrue(e[29].getNumBingo() == 1);
		} else if (tipo.contains("80")) {
			// Especial con el 38
			assertTrue(e[69].getNumEspecial() == 1);
			// Línea con el 23
			assertTrue(e[63].getNumLinea() == 1);
			// Bingo con el 89
			assertTrue(e[60].getNumBingo() == 1);
		} else {
			// Especial con el 38
			assertTrue(e[33].getNumEspecial() == 1);
			// Línea con el 23
			assertTrue(e[75].getNumLinea() == 1);
			// Bingo con el 89
			assertTrue(e[89].getNumBingo() == 1);
		}
		// Compruebo que los números cantados están a 1
		Iterator it = l.iterator();
		while (it.hasNext()) {
			Integer aux = (Integer) it.next();
			assertTrue(e[aux.intValue()].getNumSacado() == 1);
		}
		
		
		

		//////////////////////////
		// Comprobación del reparto. Punto Adicional
		//////////////////////////

		// Compruebo el reparto
		String d = String.valueOf(fecha.getYear()) + String.valueOf(fecha.getMonth()) + String.valueOf(fecha.getDay());
		Reparto r = c.getBingos().get(fecha).getReparto();
		assertNotNull(r);
		assertTrue(r.getCaja() == round((s.getRecaudacion()) / 10, 2));
		assertTrue(r.getNumBingo() == 3);
		assertTrue(r.getNumLineas() == 3);
		assertTrue(r.getNumEspeciales() == 3);
		// Especiales 2€ por especial. Solo uno
		assertTrue(r.getRepartoEspeciales() == r.getNumEspeciales() * 2);
		assertTrue(r.getRepartoBingo() == round(((s.getRecaudacion() - r.getCaja() - r.getRepartoEspeciales()) * 0.7F),
				2));
		assertTrue(r.getRepartoLinea() == round(((s.getRecaudacion() - r.getCaja() - r.getRepartoEspeciales()) * 0.3F),
				2));

		// Compruebo el dinero del jugador con 11 cartones: ganadores
		for (int i = 11; i < 14; i++) {
			u = c.getUsuarios().get(String.valueOf(i));
			float total = round(100 - 11 * b.getPrecio() // Numero de cartones x precio
					+ 2 // premio especial
					+ r.getRepartoBingo() / r.getNumBingo() // Premio de bingo
					+ r.getRepartoLinea() / r.getNumLineas() // premio de linea
					, 2); // 100 cartones -10 C - 1
			assertTrue(u.getMonedero() == total);
		}
		// Compruebo el dinero del jugador con 10 cartones
		for (int i = 14; i < 21; i++) {
			u = c.getUsuarios().get(String.valueOf(i));
			assertTrue(u.getMonedero() == (100 - 10 * b.getPrecio())); // 100 - 10 cartones * precio
		}

	}

	@Test
	public void ranking() {
///////////////////////////////
		// Jugamos con un ganador
		// se crea el sorteo
///////////////////////////////
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2023);
		cal.set(Calendar.MONTH, Calendar.NOVEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 20);
		Date fecha = cal.getTime();

		// TODO: Cambiar por el sorteo
		Bingo s = c.crearBingo(tipo, fecha, 0.00F, precio);

		// Se crea 10 usuarios que van a ganar
		for (int i = 11; i <= 21; i++) {
			c.register(String.valueOf(i), String.valueOf(i), String.valueOf(i), saldo);
		}

		// Se logean con 10 usuarios y cada uno juega 10 cartones que no ganan
		for (int i = 11; i < 21; i++) {
			for (int j = 0; j < 10; j++) {
				ICarton perdido = crearCartonIncorrecto(tipo);
				ICarton b = c.adherirseCarton(String.valueOf(i), String.valueOf(i), fecha, tipo);
				b.setNumeros(perdido.getNumeros());
			}

		}

		// Creo el cartón correcto
		ICarton ganado = crearCartonCorrecto(tipo);
		// Se lo asigno al usuario 11 el cartón correcto
		ICarton b = c.adherirseCarton("11", "11", fecha, tipo);
		b.setNumeros(ganado.getNumeros());

		// Usuario 14 con correcto
		ICarton ganado2 = crearCartonCorrecto(tipo);
		b = c.adherirseCarton("14", "14", fecha, tipo);
		b.setNumeros(ganado2.getNumeros());

		// Se juega el sorteo
		List<Integer> l = ListCorrecto(tipo);
		c.jugar(fecha, l);

		///////////////////////////////
		// Jugamos con tres ganadores
		//////////////////////////////
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.NOVEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		fecha = cal.getTime();

		// TODO: Cambiar por el sorteo
		s = c.crearBingo(tipo, fecha, 0.00F, precio);

		// Se logean con 10 usuarios y cada uno juega 10 cartones que no ganan
		for (int i = 11; i < 21; i++) {
			for (int j = 0; j < 10; j++) {
				ICarton perdido = crearCartonIncorrecto(tipo);
				b = c.adherirseCarton(String.valueOf(i), String.valueOf(i), fecha, tipo);
				b.setNumeros(perdido.getNumeros());
			}

		}

		// Creo el cartón correcto
		ganado = crearCartonCorrecto(tipo);
		// Se lo asigno al usuario 11 el cartón correcto
		b = c.adherirseCarton("11", "11", fecha, tipo);
		b.setNumeros(ganado.getNumeros());

		ganado = crearCartonCorrectoSinBingo(tipo);
		b = c.adherirseCarton("12", "12", fecha, tipo);
		b.setNumeros(ganado.getNumeros());

		ganado = crearCartonCorrectoSinLinea(tipo);
		b = c.adherirseCarton("13", "13", fecha, tipo);
		b.setNumeros(ganado.getNumeros());

		// Se juega el sorteo
		l = ListCorrecto(tipo);
		c.jugar(fecha, l);

		// Devuelva una lista de usuario donde se ha realizado .sort por bingos, líneas
		// y especiales.
		List<Usuario> list = c.verRanking();
		// User 11 2 Bingo 2 Linea y 2 especiales
		assertEquals(list.get(0).getNick(), "11");
		// User 14 1 Bingo 1 linea y 1 especiales
		assertEquals(list.get(1).getNick(), "14");
		// User 12 1 linea y 1 especiales
		assertEquals(list.get(2).getNick(), "12");
		// User 13 1 especiales
		assertEquals(list.get(3).getNick(), "13");

	}

	private float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}

}
