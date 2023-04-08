package it.uniroma3.diadia.comando;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {
	private String direzione;
	
	@Override
	public void esegui(Partita partita) {
		
		Stanza stanzaCorrente= partita.getStanzaCorrente();
		Stanza prossimaStanza= null;
		if (this.direzione== null) {
			partita.getIOConsole().mostraMessaggio("Dove vuoi andare? \n Devi specificare una direzione");
			return;
		}
		prossimaStanza= stanzaCorrente.getStanzaAdiacente(this.direzione);
		
		if (prossimaStanza== null) {
			partita.getIOConsole().mostraMessaggio("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		partita.getIOConsole().mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione= parametro;
	}
}
