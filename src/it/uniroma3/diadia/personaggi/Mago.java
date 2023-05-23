package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {	
	
	private Attrezzo attrezzoDaregalare;
	
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto nella stanza" +
			"per il tuo borsone!";
	
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	
	

	public Mago(String nome, String descr, Attrezzo att) {
		super(nome, descr);	
		this.attrezzoDaregalare=att;
	}
	
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		if(this.attrezzoDaregalare!=null) {
			partita.getStanzaCorrente().addAttrezzo(attrezzoDaregalare);
			this.attrezzoDaregalare=null;
			msg=MESSAGGIO_DONO;
		}
		else {
			msg=MESSAGGIO_SCUSE;
		}
		return msg;
	}

}
