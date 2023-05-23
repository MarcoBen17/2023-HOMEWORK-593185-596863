package it.uniroma3.diadia.personaggi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	private Boolean teletrasportato;

	public Strega(String nome, String descr) {
		super(nome, descr);
		this.teletrasportato=false;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String agisci(Partita partita) {
		String msg="Oh no sei stato teletrasportato, ma chissà dove...";
		Stanza daMandare=null;
		int contatore=0;
		if(!this.haSalutato()) {
			Map<Stanza, Set<Attrezzo>> mapStanzeAdiacenti_ConAttrezzi = partita.getStanzaCorrente().getMapStanzeAdiacenti_ConAttrezzi();
			for(Stanza adiacente: mapStanzeAdiacenti_ConAttrezzi.keySet()) {
				if(mapStanzeAdiacenti_ConAttrezzi.get(adiacente).size()>contatore) {
					contatore=mapStanzeAdiacenti_ConAttrezzi.get(adiacente).size();
					daMandare=adiacente;
				}
			}
			
		}
		else {
			Map<Stanza, Set<Attrezzo>> mapStanzeAdiacenti_ConAttrezzi = partita.getStanzaCorrente().getMapStanzeAdiacenti_ConAttrezzi();
			List<String> direzioni=new ArrayList<>();
			direzioni.addAll(partita.getStanzaCorrente().getDirezioni());
			contatore=partita.getStanzaCorrente().getStanzaAdiacente(direzioni.get(0)).getAttrezzi().size();
			for(Stanza adiacente: mapStanzeAdiacenti_ConAttrezzi.keySet()) {
				if(mapStanzeAdiacenti_ConAttrezzi.get(adiacente).size()<contatore) {
					contatore=mapStanzeAdiacenti_ConAttrezzi.get(adiacente).size();
					daMandare=adiacente;
				}
			}
					
				
		}
		if(daMandare!=null) partita.setStanzaCorrente(daMandare);
		else {
			msg="Sei stato fortunato, non ci sono state adiacenti";
		}
		
		return msg;
	}
}
