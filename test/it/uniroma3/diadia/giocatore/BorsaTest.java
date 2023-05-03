package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	
	private Borsa borsavuota;
	private Attrezzo osso;
	private Borsa borsa;

	@BeforeEach
	public void setUp() {
		this.borsavuota = new Borsa();
		this.osso= new Attrezzo("osso", 3);
		this.borsa= new Borsa();
		this.borsa.addAttrezzo(this.osso);
	}

	@Test
	void testAddAttrezzo() {
		this.borsavuota.addAttrezzo(osso);
		assertTrue(this.borsavuota.hasAttrezzo("osso"));
	}

	@Test
	void testGetAttrezzo() {
		assertSame(this.osso, this.borsa.getAttrezzo("osso"));
	}
	@Test
	void testHasAttrezzo() {
		assertTrue(this.borsa.hasAttrezzo("osso"));
		assertFalse(this.borsavuota.hasAttrezzo("osso"));
	}

	@Test
	void testRemoveAttrezzo() {
		this.borsa.removeAttrezzo("osso");
		assertFalse(this.borsa.hasAttrezzo("osso"));
	}
	
	@Test 
	void testsortlistavuota() {
		this.borsavuota.getContenutoOrdinatoPerPeso();
		assertTrue(this.borsavuota.isEmpty());
	}
	
	@Test 
	void testsortlista() {
		this.borsa.addAttrezzo(new Attrezzo("zappa", 2));
		List<Attrezzo> lista= this.borsa.getContenutoOrdinatoPerPeso();
		List<Attrezzo> comp= new ArrayList<Attrezzo>();
		comp.add(new Attrezzo("zappa", 2));
		comp.add(this.osso);
		assertEquals(lista, comp);
	}
	
	@Test 
	void testsortlistaStezzoPeso() {
		this.borsa.addAttrezzo(new Attrezzo("appa", 3));
		List<Attrezzo> lista= this.borsa.getContenutoOrdinatoPerPeso();
		List<Attrezzo> comp= new ArrayList<Attrezzo>();
		comp.add(new Attrezzo("appa", 3));
		comp.add(this.osso);
		assertEquals(lista, comp);
	}
}
