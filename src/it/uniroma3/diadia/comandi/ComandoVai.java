package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {
	private String direzione;
	
	@Override
	public void esegui(Partita partita) {
		
		Stanza stanzaCorrente= partita.getStanzaCorrente();
		Stanza prossimaStanza= null;
		if (this.direzione== null) {
			partita.getIO().mostraMessaggio("Dove vuoi andare? \n Devi specificare una direzione");
			return;
		}
		prossimaStanza= stanzaCorrente.getStanzaAdiacente(this.direzione);
		
		if (prossimaStanza== null) {
			partita.getIO().mostraMessaggio("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione= parametro;
	}
	
	@Override
	public String getNome() {
		return "vai";
	}
	
	@Override
	public String getParametro() {
		return this.direzione;
	}
}
