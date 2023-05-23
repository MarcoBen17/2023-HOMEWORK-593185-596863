package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {


	private static final String MESSAGGIO_SCUSE= "Sono spiacente, ma non ho più nulla...";

	private static final String MESSAGGIO_DONO= "Ecco a te, sono certo che ne farai buon uso! \n Se non sai cosa ti ho "
			+ "donato perchè non provi a guardare tra gli attrezzi presenti in questa stanza (;.";

	private Attrezzo attrezzo;
	
	public Mago(String nome, String descrizione, Attrezzo attrezzo) {
		super(nome, descrizione);
		this.attrezzo= attrezzo;
	}


	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzo==null) {
			msg=MESSAGGIO_SCUSE;
		}
		else {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo=null;
			msg=MESSAGGIO_DONO;
		}
		return msg;
	}

}
