package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	private String dirlocked;
	private String attrezzo;
	
	public StanzaBloccata(String nome, String direzione, String attrezzo) {
		super(nome);
		this.attrezzo= attrezzo;
		this.dirlocked= direzione;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(!this.hasAttrezzo(this.attrezzo) && this.dirlocked.equals(direzione)) {
			return this;
		}
		else return super.getStanzaAdiacente(direzione);			
	}
	
	@Override
	public String getDescrizione() {
		String nuovaDescrizione;
		nuovaDescrizione=  "Stanza Bloccata!\n"+super.getDescrizione()+"\nDirezione bloccata: "+this.dirlocked+"\nAttrezzo necessario: "+this.attrezzo;
		return nuovaDescrizione;
	}
	
}
