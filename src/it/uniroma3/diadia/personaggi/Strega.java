package it.uniroma3.diadia.personaggi;

import java.util.ArrayList;
import java.util.Comparator;
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
		class ComparatoreStanzePerAttrezzi implements Comparator<Stanza>{

			@Override
			public int compare(Stanza o1, Stanza o2) {
				return o1.getAttrezzi().size()-o2.getAttrezzi().size();
			}
			
		}
		List<Stanza> listaStanze = new ArrayList<>(partita.getStanzaCorrente().getMapStanzeAdiacenti().values());
		listaStanze.sort(new ComparatoreStanzePerAttrezzi());
		if(!this.haSalutato()) {
			daMandare=listaStanze.get(0);						
		}
		else {
			daMandare=listaStanze.get(listaStanze.size()-1);				
		}
		if(daMandare!=null) partita.setStanzaCorrente(daMandare);
		else {
			msg="Sei stato fortunato, non ci sono state adiacenti";
		}		
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "Stolto avventuriero speravi di farmi addolcire forse? hihihihhihi";
	}
}
