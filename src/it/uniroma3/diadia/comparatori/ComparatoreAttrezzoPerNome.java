package it.uniroma3.diadia.comparatori;
import java.util.Comparator;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComparatoreAttrezzoPerNome implements Comparator<Attrezzo> {
	
	@Override
	public int compare(Attrezzo a, Attrezzo b) {
		return a.getNome().compareTo(b.getNome());
	}
}
