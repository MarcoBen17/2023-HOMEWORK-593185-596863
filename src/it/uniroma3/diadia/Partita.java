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

	private Stanza stanzaCorrente;
	private Labirinto labirinto;
	private boolean finita;
	private Giocatore giocatore;
	private IOConsole ioconsole;

	public IOConsole getIOConsole() {
		return ioconsole;
	}

	public void setIOConsole(IOConsole ioconsole) {
		this.ioconsole = ioconsole;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	public Partita(IOConsole ioconsole){
		this.ioconsole= ioconsole;
		this.labirinto= new Labirinto();
		this.stanzaCorrente= labirinto.getEntrata();
		this.finita = false;
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
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public boolean giocatoreIsVivo() {
		if (this.giocatore.getCfu() > 0) return true;
		return false;
	}
}
