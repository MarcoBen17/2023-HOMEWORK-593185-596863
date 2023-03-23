package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.giocatore.*;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	static final private int CFU_INIZIALI = 20;

	private Stanza stanzaCorrente;
	private Labirinto labirinto;
	private boolean finita;
	private int cfu;
	private Giocatore giocatore;

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	public Partita(){
		this.labirinto= new Labirinto();
		this.stanzaCorrente= labirinto.getEntrata();
		this.finita = false;
		this.cfu = CFU_INIZIALI;
		this.giocatore= new Giocatore();
	}
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.stanzaCorrente == this.labirinto.getUscita();
	}

	public Stanza getStanzaCorrente() {
		return stanzaCorrente;
	}
	public void setStanzaCorrente(Stanza stanzacorrente) {
		this.stanzaCorrente = stanzacorrente;
	}
	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (cfu == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	
}
