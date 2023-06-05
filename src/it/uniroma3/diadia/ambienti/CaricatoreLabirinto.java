package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.internal.Checks;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Mago;

public class CaricatoreLabirinto {
	
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	private static final String MAGO_MARKER = "Mago:";

	private static final String STREGA_MARKER = "Strega:";

	private static final String CANE_MARKER = "Cane:";

	private static final String STANZEBUIE_MARKER = "Stanze_Buie:";

	private static final String STANZEMAGICHE_MARKER = "Stanze_Magiche:";

	private static final String STANZEBLOCCATE_MARKER = "Stanze_Bloccate:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;
	
	private LabirintoBuilder labirinto;

	


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {		
		this.reader = new LineNumberReader(new FileReader(nomeFile));
		this.labirinto= new LabirintoBuilder();
		
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto.getLabirinto();
	}
	

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzaMagica();
			this.leggiEcreaStanzaBloccata();
			this.leggiECollocaMago();
			this.leggiEcollocaStrega();
			this.leggiECollocaCane();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();		
			this.leggiInizialeEvincente();
			
			
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}


	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			this.labirinto.addStanza(nomeStanza);
			Stanza stanza = new Stanza(nomeStanza);
			
		}
		
	}
	

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		try (Scanner scannerDiParole = scanner) {
			scanner.useDelimiter(",");
			while(scanner.hasNext()) {
				result.add(scannerDiParole.next());
			}			
		}
		return result;
		
		
	}
	


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.labirinto.setStanzaIniziale(this.labirinto.getMappaStanze().get(nomeStanzaIniziale));
		this.labirinto.setStanzaVincente(this.labirinto.getMappaStanze().get(nomeStanzaVincente));
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
				
				
			}			
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			Stanza corrente=this.labirinto.getMappaStanze().get(nomeStanza);
			this.labirinto.setStanzaCorrente(corrente);
			peso = Integer.parseInt(pesoAttrezzo);
			this.labirinto.addAttrezzo(nomeAttrezzo, peso);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
		
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.labirinto.getMappaStanze().containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
			for(String specificaUscita: this.separaStringheAlleVirgole(specificheUscite)) {
				String stanzaPartenza=null;
				String dir=null;
				String stanzaDestinazione=null;
			try (Scanner scannerDiLinea = new Scanner(specificaUscita)) {			
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				stanzaDestinazione = scannerDiLinea.next();								
		} 
			this.impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
			}
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		this.labirinto.addAdiacenza(stanzaDa, nomeA, dir);
		
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.labirinto.getStanzaIniziale();
	}

	public Stanza getStanzaVincente() {
		return this.labirinto.getStanzaVincente();
	}
	private void leggiECollocaMago() throws FormatoFileNonValidoException {
		String specificheMago= this.leggiRigaCheCominciaPer(MAGO_MARKER);
		for(String specifica: this.separaStringheAlleVirgole(specificheMago)) {
			String nome=null;
			String nomeAttrezzo=null;
			String peso=null;
			String nomeStanza=null;			
			try(Scanner scannerdiLinea= new Scanner(specifica)){
				check(scannerdiLinea.hasNext(), msgTerminazionePrecoce("il nome del mago "));
				nome=scannerdiLinea.next();
				check(scannerdiLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo"));
				nomeAttrezzo=scannerdiLinea.next();
				check(scannerdiLinea.hasNext(), msgTerminazionePrecoce("il peso dell'attrezzo"));
				peso=scannerdiLinea.next();
				check(scannerdiLinea.hasNext(), msgTerminazionePrecoce("il nome della stanza"));
				nomeStanza=scannerdiLinea.next();
			}
			this.aggiungiMago(nome, nomeAttrezzo, peso, nomeStanza);
		}		
	}

	private void aggiungiMago(String nome, String nomeAttrezzo, String peso, String nomeStanza) {
		Stanza corrente= this.labirinto.getMappaStanze().get(nomeStanza);
		this.labirinto.setStanzaCorrente(corrente);
		this.labirinto.addMago(nome, "Abracadabra", nomeAttrezzo,  Integer.parseInt(peso) );
	}
	private void leggiEcollocaStrega() throws FormatoFileNonValidoException {
		String specificheStrega= this.leggiRigaCheCominciaPer(STREGA_MARKER);
		for(String specifica: this.separaStringheAlleVirgole(specificheStrega)) {
			String nome=null;			
			String nomeStanza=null;
			try(Scanner scannerdiLinea= new Scanner(specifica)){
				check(scannerdiLinea.hasNext(), msgTerminazionePrecoce("il nome della strega "));
				nome=scannerdiLinea.next();
				check(scannerdiLinea.hasNext(), msgTerminazionePrecoce("il nome della stanza"));
				nomeStanza=scannerdiLinea.next();				
			}
			this.aggiungiStrega(nome, nomeStanza);			
		}
		
	}

	private void aggiungiStrega(String nome, String nomeStanza) {
		Stanza corrente= this.labirinto.getMappaStanze().get(nomeStanza);
		this.labirinto.setStanzaCorrente(corrente);
		this.labirinto.addStrega(nome, " Non ti conviene giocare con me!");		
	}

	private void leggiECollocaCane() throws FormatoFileNonValidoException {
		String specificheStrega= this.leggiRigaCheCominciaPer(CANE_MARKER);
		for(String specifica: this.separaStringheAlleVirgole(specificheStrega)) {
			String nome=null;			
			String nomeAttrezzo=null;
			String pesoAttrezzo=null;
			String nomeStanza=null;
			try(Scanner scannerdiLinea= new Scanner(specifica)){
				check(scannerdiLinea.hasNext(), msgTerminazionePrecoce("il nome del cane "));
				nome=scannerdiLinea.next();
				check(scannerdiLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo"));
				nomeAttrezzo=scannerdiLinea.next();
				check(scannerdiLinea.hasNext(), msgTerminazionePrecoce("il peso dell'attrezzo"));
				pesoAttrezzo=scannerdiLinea.next();
				check(scannerdiLinea.hasNext(), msgTerminazionePrecoce("il nome della stanza"));
				nomeStanza=scannerdiLinea.next();
			}
			this.aggiungiCane(nome, nomeAttrezzo, pesoAttrezzo, nomeStanza);			
		}		
	}

	private void aggiungiCane(String nome, String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) {
		Stanza corrente= this.labirinto.getMappaStanze().get(nomeStanza);
		this.labirinto.setStanzaCorrente(corrente);
		this.labirinto.addCane(nome, "Baubau", nomeAttrezzo, Integer.parseInt(pesoAttrezzo));
		}
	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZEBUIE_MARKER);
		for(String specificheStanza : separaStringheAlleVirgole(nomiStanze)) {
			String nomeStanza=null;
			String nomeAttrezzo=null;
			try(Scanner scannerdiLinea= new Scanner(specificheStanza)){
				check(scannerdiLinea.hasNext(), msgTerminazionePrecoce("il nome della stanza "));
				nomeStanza=scannerdiLinea.next();
				check(scannerdiLinea.hasNext(), msgTerminazionePrecoce("il nome dell'attrezzo "));
				nomeAttrezzo=scannerdiLinea.next();				
			}
			this.creaStanzaBuia(nomeStanza, nomeAttrezzo);
		}
	}

	private void creaStanzaBuia(String nomeStanza,String nomeAttrezzo) {
		this.labirinto.addStanzaBuia(nomeStanza,nomeAttrezzo);	
	}
	private void leggiECreaStanzaMagica() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZEMAGICHE_MARKER);
		for(String specificheStanza : separaStringheAlleVirgole(nomiStanze)) {
			String nomeStanza=null;
			String soglia=null;
			try(Scanner scannerdiLinea= new Scanner(specificheStanza)){
				check(scannerdiLinea.hasNext(), msgTerminazionePrecoce("il nome della stanza "));
				nomeStanza=scannerdiLinea.next();
				check(scannerdiLinea.hasNext(), msgTerminazionePrecoce("la soglia"));
				soglia=scannerdiLinea.next();
			}
			this.creaStanzaMagica(nomeStanza, soglia);
		}
		
	}

	private void creaStanzaMagica(String nomeStanza, String soglia) {
		if(soglia!=null) {
			this.labirinto.addStanzaMagica(nomeStanza,Integer.parseInt(soglia));
		}
		else {
			this.labirinto.addStanzaMagica(nomeStanza);
		}
		
	}
	private void leggiEcreaStanzaBloccata() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZEBLOCCATE_MARKER);
		for(String specificheStanza : separaStringheAlleVirgole(nomiStanze)) {
			String nomeStanza=null;
			String dir=null;
			String nomeAttrezzo=null;
			try(Scanner scannerdiLinea= new Scanner(specificheStanza)){
				check(scannerdiLinea.hasNext(), msgTerminazionePrecoce("il nome della stanza "));
				nomeStanza=scannerdiLinea.next();
				check(scannerdiLinea.hasNext(), msgTerminazionePrecoce("la direzione "));
				dir=scannerdiLinea.next();
				check(scannerdiLinea.hasNext(), msgTerminazionePrecoce("il nome del'attrezzo "));
				nomeAttrezzo=scannerdiLinea.next();
			}
			this.labirinto.addStanzaBloccata(nomeStanza, dir, nomeAttrezzo);	
		}
	}
	
	
	
}


