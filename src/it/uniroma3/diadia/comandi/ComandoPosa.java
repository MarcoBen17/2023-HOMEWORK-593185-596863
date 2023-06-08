package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa extends AbstractComando{
	private String nomeAttrezzo;
	
	@Override
	public void esegui(Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();
		Stanza stanzaCorrente = partita.getStanzaCorrente();

		if (borsa.hasAttrezzo(this.nomeAttrezzo) && stanzaCorrente.getAttrezzi().size()< Stanza.getNumeroMassimoAttrezzi()) {
			Attrezzo item= borsa.removeAttrezzo(this.nomeAttrezzo);
			stanzaCorrente.addAttrezzo(item);
			partita.getIO().mostraMessaggio("attrezzo posato");
		}
		else if (!borsa.hasAttrezzo(this.nomeAttrezzo) && stanzaCorrente.getAttrezzi().size()>= Stanza.getNumeroMassimoAttrezzi())
			partita.getIO().mostraMessaggio("Attrezzo non presente in borsa e stanza piena");

		else if (!borsa.hasAttrezzo(this.nomeAttrezzo))
			partita.getIO().mostraMessaggio("Attrezzo non presente in borsa");

		else if (stanzaCorrente.getAttrezzi().size() >= Stanza.getNumeroMassimoAttrezzi())
			partita.getIO().mostraMessaggio("stanza piena");
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo= parametro;
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}
}
