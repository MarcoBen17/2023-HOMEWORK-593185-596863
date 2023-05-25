package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		String personaggio;
		if (partita.getStanzaCorrente().getPersonaggio()!= null) personaggio=partita.getStanzaCorrente().getPersonaggio().toString();
		else personaggio="Nessun personaggio presente nella stanza";
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione()+
				"\nCFU rimasti:"+ partita.getGiocatore().getCfu()+ "\nPersonaggi nella stanza: "+ personaggio);
		return;
	}

	
}
