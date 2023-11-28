package es.unex.cum.mdp.ef2;


import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unex.cum.mdp.ef2.bingo.BolsaBingo;

class BolsaBingoTest {

	private BolsaBingo b,b1;
	private int tam=80;
	
	@BeforeEach
	void setUp() throws Exception {
		b=new BolsaBingo();
		b1=new BolsaBingo(tam);
	}

	@AfterEach
	void tearDown() throws Exception {
		b=null;
		b1=null;
	}

	@Test
	void testBolsaBingo() {
		assertNotNull(b);		
	}

	//Crear la cola, debe añadir tantos número como tamaño
	@Test
	void testBolsaBingoInt() {
		assertNotNull(b1);
		assertTrue(tam==b1.getTotalBolas());
	}

	@Test
	void testGetBolsa() {
		assertNotNull(b.getBolsa());
		assertNotNull(b1.getBolsa());
	}

	@Test
	void testAddNumero() {
		//b es una bolsa bingo sin numero
		//b1 es una bolsa bingo con 75 numeros
		int numero=90;
		b.addNumero(numero);
		assertTrue(b.getTotalBolas()==1);
		//Buscar el número en la bolsa b
		b1.addNumero(numero);
		assertTrue(b1.getTotalBolas()==(tam+1));
		//Buscar el número en la bolsa b1
		Iterator<Integer>it=b1.getBolsa().iterator();
		boolean enc=false;
		while (it.hasNext()&& !enc) {
			Integer a=it.next();
			if (numero==a) {
				enc=true;
			}
		}
		assertTrue(enc);		
	}

	@Test
	void testDesordenar() {
		
		b1.desordenar(); //b.shuffle();
		//COmprobar que la cola esta desordenada
		//Comprobar que la cola no esta ordenada
		List<Integer> l=(List)b1.getBolsa();
		boolean desordenado=false;
		for (int i=0;i<l.size()-1;i++) {
			Integer actual=l.get(i);
			Integer sig=l.get(i+1);
			if (actual>sig) {
				desordenado=true;
			}
		}
		assertTrue(desordenado);
	}

	@Test
	void testGetTotalBolas() {
		assertTrue(tam==b1.getTotalBolas());
	}

	@Test
	void testSacarBola() {
		
		//b que esta vacia
		assertNull(b.sacarBola());
		//b1 que esta llena --> 75 números
		b1.desordenar();
		assertNotNull(b1.sacarBola());
		assertTrue(b1.getTotalBolas()==(tam-1));		
	}

	@Test
	void testBolsaVacia() {
		assertTrue(b.bolsaVacia());
		assertFalse(b1.bolsaVacia());
	}

	@Test
	void testMostrarPrimero() {
		assertNull(b.mostrarPrimero());
		assertNotNull(b1.mostrarPrimero());
		assertTrue(1==(Integer)b1.mostrarPrimero());
		assertTrue(tam==b1.getTotalBolas());
	}

}
