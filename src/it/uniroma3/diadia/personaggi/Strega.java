package it.uniroma3.diadia.personaggi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comparatori.ComparatoreStanzaPerNumAttrezzi;

public class Strega extends AbstractPersonaggio {

	private static final String MALEDIZIONE="INGRATO! Come osi chiedere il mio aiuto senza prima avermi "
			+ "salutato... Questa la pagherai cara...";
	
	private static final String MAGIA="Vedo che sei una persona educata, per questa volta ti aiuterò, "
			+ "ma non prenderci l'abitudine...";
	
	private static final String RISATA="AHAHAHAHAHAHA cosa speravi di ottenere? AHAHHAAH";
	
	public Strega(String nome, String descriz) {
		super(nome, descriz);
	}
	
	@Override
	public String agisci(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		ComparatoreStanzaPerNumAttrezzi comparatore= new ComparatoreStanzaPerNumAttrezzi();
		
		Collection<Stanza> collezioneStanzeAdiacenti = partita.getStanzaCorrente().getMapStanzeAdiacenti().values();
		ArrayList<Stanza> listaStanze = new ArrayList<Stanza>(collezioneStanzeAdiacenti);
		
		Collections.sort(listaStanze,comparatore);
		
		int numeroStanze= listaStanze.size();
		
		if(personaggio.haSalutato()) {
			partita.setStanzaCorrente(listaStanze.get(numeroStanze-1));
			return MAGIA;
		}
		else {
			partita.setStanzaCorrente(listaStanze.get(0));
			return MALEDIZIONE;
		}
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return RISATA;
	}

}
