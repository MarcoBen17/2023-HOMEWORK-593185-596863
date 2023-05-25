package it.uniroma3.diadia.comparatori;

import java.util.Comparator;

import it.uniroma3.diadia.ambienti.Stanza;

public class ComparatoreStanzaPerNumAttrezzi implements Comparator<Stanza>{

	@Override
	public int compare(Stanza o1, Stanza o2) {
		return o1.getAttrezzi().size()-o2.getAttrezzi().size();
	}

}
