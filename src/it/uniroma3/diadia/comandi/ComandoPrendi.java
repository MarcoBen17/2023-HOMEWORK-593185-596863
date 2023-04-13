package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendi implements Comando{
	private String nomeAttrezzo;
	
	@Override
	public void esegui(Partita partita) {
		
		Borsa borsa = partita.getGiocatore().getBorsa();
		Stanza stanzaCorrente = partita.getStanzaCorrente();

		if (stanzaCorrente.hasAttrezzo(this.nomeAttrezzo) && borsa.getPeso() < borsa.getPesoMax()) {
			Attrezzo item= stanzaCorrente.getAttrezzo(this.nomeAttrezzo);
			stanzaCorrente.removeAttrezzo(item);
			borsa.addAttrezzo(item);
			partita.getIO().mostraMessaggio("attrezzo raccolto");
		}
		else if (borsa.getPeso() >= borsa.getPesoMax() && !stanzaCorrente.hasAttrezzo(this.nomeAttrezzo)) {
			partita.getIO().mostraMessaggio("Borsa piena e Attrezzo non presente nella stanza");
		}
		else if (borsa.getPeso() >= borsa.getPesoMax()) {
			partita.getIO().mostraMessaggio("Borsa piena");
		}
		else if (!stanzaCorrente.hasAttrezzo(this.nomeAttrezzo)) {
			partita.getIO().mostraMessaggio("Attrezzo non presente nella stanza");
		}
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo= parametro;
	}
	
	@Override
	public String getNome() {
		return "prendi";
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}
}
