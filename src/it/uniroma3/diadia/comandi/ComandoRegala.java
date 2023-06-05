package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {
	private String nomeAttrezzo;
	
	@Override
	public void esegui(Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		AbstractPersonaggio personaggio= partita.getStanzaCorrente().getPersonaggio();
		if(borsa.hasAttrezzo(nomeAttrezzo)){
			partita.getIO().mostraMessaggio(personaggio.riceviRegalo(borsa.removeAttrezzo(nomeAttrezzo), partita));
		}
		else if(this.nomeAttrezzo==null) {
			partita.getIO().mostraMessaggio("Specificare cosa si vuol regalare!");
		}
		else {
			partita.getIO().mostraMessaggio("Attrezzo non presente nella borsa!");
		}
		return;
		
	}
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo= parametro;
	}
	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
