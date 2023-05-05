package it.uniroma3.diadia.giocatore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.comparatori.ComparatoreAttrezzoPerNome;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private List<Attrezzo> attrezzi;
	private int pesoMax;


	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}


	public Borsa(int pesoMax) {

		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>(); // speriamo bastino...
	}

	public List<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}


	public boolean addAttrezzo(Attrezzo attrezzo) {

		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.add(attrezzo);
		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (Attrezzo item : this.attrezzi)
			if (item.getNome().equals(nomeAttrezzo))
				a = item;
		return a;
	}

	public int getPeso() {
		int peso = 0;
		for (Attrezzo a: this.attrezzi)
			peso += a.getPeso();
		return peso;
	}

	public boolean isEmpty() {
		return this.attrezzi.size() == 0;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Iterator<Attrezzo> iteratore= this.attrezzi.iterator();
		while(iteratore.hasNext()) {
			a= iteratore.next();
			if (a.getNome().equals(nomeAttrezzo)) {
				iteratore.remove();
				return a;
			}
		}
		return null;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo a: this.attrezzi)
				s.append(a.toString()+"\n");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> lista= new ArrayList<Attrezzo>();
		lista.addAll(this.attrezzi);
		Collections.sort(lista);
		return lista;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		Comparator<Attrezzo> comparatore= new ComparatoreAttrezzoPerNome();
		SortedSet<Attrezzo> ins= new TreeSet<Attrezzo>(comparatore);
		ins.addAll(this.attrezzi);
		return ins;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> ins = new TreeSet<Attrezzo>(this.attrezzi);
		return ins;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		
		Map<Integer,Set<Attrezzo>> mappa= new TreeMap<Integer,Set<Attrezzo>>();
		
		for (Attrezzo item: this.attrezzi) {
			int chiave = item.getPeso();
			if (mappa.containsKey(chiave)) {
				mappa.get(chiave).add(item);
			}
			else {
				HashSet<Attrezzo> ins = new HashSet<Attrezzo>();
				ins.add(item);
				mappa.put(chiave,ins);
			}
		}
		return mappa;
	}
	
	
	
}

