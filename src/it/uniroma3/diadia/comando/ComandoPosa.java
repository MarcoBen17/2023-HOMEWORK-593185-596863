package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa implements Comando{
	private String nomeAttrezzo;
	
	@Override
	public void esegui(Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();
		Stanza stanzaCorrente = partita.getStanzaCorrente();

		if (borsa.hasAttrezzo(this.nomeAttrezzo) && stanzaCorrente.getNumeroAttrezzi()< Stanza.getNumeroMassimoAttrezzi()) {
			Attrezzo item= borsa.removeAttrezzo(this.nomeAttrezzo);
			stanzaCorrente.addAttrezzo(item);
			partita.getIOConsole().mostraMessaggio("attrezzo posato");
		}
		else if (!borsa.hasAttrezzo(this.nomeAttrezzo) && stanzaCorrente.getNumeroAttrezzi()>= Stanza.getNumeroMassimoAttrezzi())
			partita.getIOConsole().mostraMessaggio("Attrezzo non presente in borsa e stanza piena");

		else if (!borsa.hasAttrezzo(this.nomeAttrezzo))
			partita.getIOConsole().mostraMessaggio("Attrezzo non presente in borsa");

		else if (stanzaCorrente.getNumeroAttrezzi() >= Stanza.getNumeroMassimoAttrezzi())
			partita.getIOConsole().mostraMessaggio("stanza piena");
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo= parametro;
	}
}
