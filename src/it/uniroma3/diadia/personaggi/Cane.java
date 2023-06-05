package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Cane extends AbstractPersonaggio {
	private Attrezzo attrezzoDaRegalare;
	private static final String CIBO_PREFERITO = "osso";
	public Cane(String nome, String descr, Attrezzo attrezzo) {
		super(nome, descr);	
		this.attrezzoDaRegalare=attrezzo;
	}
	

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		String msg="Oh no, il cane ti ha morso! Controlla i tuo CFU. Magari prova a regalargli un osso,forse gli farà piacere...";
		return msg;
	}
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(this.attrezzoDaRegalare==null) {
			return "Il cane non ha più niente da darti!";
		}
		else if(attrezzo.getNome().equals(CIBO_PREFERITO)) {
			
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			return "Guarda a terra!";
		}
		else {
			return "Non lo vuole!";
		}
	}
	
	public Attrezzo getAttrezzoDaRegalare() {
		return this.attrezzoDaRegalare;
	}
	
	
	public void setAttrezzoDaRegalare(Attrezzo attrezzoDaRegalare) {
		this.attrezzoDaRegalare = attrezzoDaRegalare;
	}

}
