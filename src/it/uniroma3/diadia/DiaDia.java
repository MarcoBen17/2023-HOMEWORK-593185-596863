package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.giocatore.*;

import java.util.Scanner;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole IOConsole;

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;   //prende una righa e ci fa una scansione (esempio comando "vai sud")

		System.out.println(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(System.in);		
		do		
			istruzione = scannerDiLinee.nextLine();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		String nomeComando = comandoDaEseguire.getNome();
		if (nomeComando== null) {
			System.out.println("Comando sconosciuto");
		}
		else if (nomeComando.equals("fine")) {
			this.fine(); 
			return true;
		}
		else if (nomeComando.equals("vai"))
			this.vai(comandoDaEseguire.getParametro());

		else if (nomeComando.equals("aiuto"))
			this.aiuto();

		else if (nomeComando.equals("prendi") && comandoDaEseguire.getParametro()!= null)
			this.prendi(comandoDaEseguire.getParametro());
		
		else if (nomeComando.equals("posa") && comandoDaEseguire.getParametro()!= null)
			this.posa(comandoDaEseguire.getParametro());
		
		else
			System.out.println("Comando sconosciuto");
		if (this.partita.vinta()) {
			System.out.println("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		System.out.println();
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			System.out.println("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			System.out.println("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			Giocatore giocatore = this.partita.getGiocatore();
			int cfu = giocatore.getCfu();
			giocatore.setCfu(cfu--);
		}
		System.out.println(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		System.out.println("Grazie di aver giocato!");  // si desidera smettere
	}

	public void prendi(String nomeAttrezzo) {
		
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();

		if (stanzaCorrente.hasAttrezzo(nomeAttrezzo) && borsa.getPeso() < borsa.getPesoMax()) {
			Attrezzo item= stanzaCorrente.getAttrezzo(nomeAttrezzo);
			stanzaCorrente.removeAttrezzo(item);
			borsa.addAttrezzo(item);
			System.out.println("attrezzo raccolto");
		}
		else if (borsa.getPeso() >= borsa.getPesoMax() && !stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
			System.out.println("Borsa piena e Attrezzo non presente nella stanza");
		}
		else if (borsa.getPeso() >= borsa.getPesoMax()) {
			System.out.println("Borsa piena");
		}
		else if (!stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
			System.out.println("Attrezzo non presente nella stanza");
		}
	}

	public void posa(String nomeAttrezzo) {
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();

		if (borsa.hasAttrezzo(nomeAttrezzo) && stanzaCorrente.getNumeroAttrezzi()< Stanza.getNumeroMassimoAttrezzi()) {
			Attrezzo item= borsa.removeAttrezzo(nomeAttrezzo);
			stanzaCorrente.addAttrezzo(item);
			System.out.println("attrezzo posato");
		}
		else if (!borsa.hasAttrezzo(nomeAttrezzo) && stanzaCorrente.getNumeroAttrezzi()>= Stanza.getNumeroMassimoAttrezzi())
			System.out.println("Attrezzo non presente in borsa e stanza piena");

		else if (!borsa.hasAttrezzo(nomeAttrezzo))
			System.out.println("Attrezzo non presente in borsa");

		else if (stanzaCorrente.getNumeroAttrezzi() >= Stanza.getNumeroMassimoAttrezzi())
			System.out.println("stanza piena");
	}

	
	
	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}