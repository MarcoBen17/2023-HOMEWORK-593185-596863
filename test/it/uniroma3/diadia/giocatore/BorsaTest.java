package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comparatori.ComparatoreAttrezzoPerNome;

class BorsaTest {
	
	private Borsa borsavuota;
	private Attrezzo osso;
	private Borsa borsa;

	@BeforeEach
	public void setUp() {
		this.borsavuota = new Borsa();
		this.osso= new Attrezzo("osso",1 );
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
		comp.add(this.osso);
		comp.add(new Attrezzo("zappa", 2));
		assertEquals(lista, comp);
	}
	
	@Test 
	void testsortlistaStezzoPeso() {
		this.borsa.addAttrezzo(new Attrezzo("appa", 3));
		List<Attrezzo> lista= this.borsa.getContenutoOrdinatoPerPeso();
		List<Attrezzo> comp= new ArrayList<Attrezzo>();
		comp.add(this.osso);
		comp.add(new Attrezzo("appa", 3));
		assertEquals(lista, comp);
	}
	
	@Test
	void testSortPerNome(){
		Attrezzo spada = new Attrezzo("spada", 1);
		Attrezzo lanterna = new Attrezzo("lanterna", 1);
		
		this.borsa.addAttrezzo(lanterna);
		this.borsa.addAttrezzo(spada);
		
		SortedSet<Attrezzo> ins= new TreeSet<Attrezzo>(new ComparatoreAttrezzoPerNome());
		ins.addAll(this.borsa.getAttrezzi());
		
		List<Attrezzo> lins= new ArrayList<Attrezzo>();
		lins.addAll(ins);
		
		List<Attrezzo> comp= new ArrayList<Attrezzo>();
		comp.add(lanterna);
		comp.add(this.osso);
		comp.add(spada);
		assertEquals(lins, comp);
	}
	
	@Test
	void testSortSetPerPeso(){
		Attrezzo spada = new Attrezzo("spada", 2);
		Attrezzo lanterna = new Attrezzo("lanterna", 4);
		this.borsa.addAttrezzo(lanterna);
		this.borsa.addAttrezzo(spada);
		
		SortedSet<Attrezzo> ins =this.borsa.getSortedSetOrdinatoPerPeso();
		
		List<Attrezzo> lins= new ArrayList<Attrezzo>();
		lins.addAll(ins);
		
		List<Attrezzo> comp= new ArrayList<Attrezzo>();
		comp.add(this.osso);
		comp.add(spada);
		comp.add(lanterna);
		assertEquals(lins, comp);
	}
	
	@Test 
	void testMappa() {
		Attrezzo spada = new Attrezzo("spada", 1);
		Attrezzo lanterna = new Attrezzo("lanterna", 2);
		Attrezzo a = new Attrezzo("a", 2);
		this.borsa.addAttrezzo(lanterna);
		this.borsa.addAttrezzo(spada);
		this.borsa.addAttrezzo(a);
		
		Map<Integer,Set<Attrezzo>> mappa= new TreeMap<Integer, Set<Attrezzo>>();
		mappa= this.borsa.getContenutoRaggruppatoPerPeso();
		Map<Integer,Set<Attrezzo>> mappacopia= new TreeMap<>();
		
		System.out.println(mappa);
	}
	
}
