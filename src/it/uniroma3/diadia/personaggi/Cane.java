package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;

public class Cane extends AbstractPersonaggio {

	public Cane(String nome, String descr) {
		super(nome, descr);		
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		String msg="Oh no, il cane ti ha morso! Controlla i tuo CFU.";
		return msg;
	}

}
