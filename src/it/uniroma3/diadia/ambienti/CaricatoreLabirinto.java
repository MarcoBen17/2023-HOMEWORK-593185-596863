package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;


public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             
	private static final String BLOCCATE_MARKER = "Stanze_Bloccate:";             
	private static final String MAGICHE_MARKER = "Stanze_Magiche:";             
	private static final String BUIE_MARKER = "Stanze_Buie:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/*prefisso della riga contenente le specifiche dei personaggi*/
	private static final String MAGO_MARKER = "Mago:";
	private static final String STREGA_MARKER = "Strega:";
	private static final String CANE_MARKER = "Cane:";
	
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

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECollocaStanzaBuia();
			this.leggiECollocaStanzaBloccata();
			this.leggiECollocaStanzaMagica();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiECollocaMago();
			this.leggiECollocaStrega();
			this.leggiECollocaCane();
			this.leggiEImpostaUscite();
			
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
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		try (Scanner scanner = new Scanner(string)) {
			scanner.useDelimiter(",");
			while(scanner.hasNext()) {
				result.add(scanner.next());
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
		
		this.labirinto.setStanzaVincente(this.labirinto.getMappaStanze().get(nomeStanzaVincente));
		this.labirinto.setStanzaIniziale(this.labirinto.getMappaStanze().get(nomeStanzaIniziale));
		
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : this.separaStringheAlleVirgole(specificheAttrezzi)) {
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
			peso = Integer.parseInt(pesoAttrezzo);
			check(this.isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.labirinto.setStanzaCorrente(this.labirinto.getMappaStanze().get(nomeStanza));
			this.labirinto.addAttrezzo(nomeAttrezzo, peso);
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

		for(String specificaUscita : this.separaStringheAlleVirgole(specificheUscite)) {
			String stanzaPartenza=null;
			String dir=null;
			String stanzaDestinazione= null;
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
		check(this.isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(this.isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
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
	
	private void leggiECollocaStanzaMagica() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(MAGICHE_MARKER);
		for(String specificaStanza : separaStringheAlleVirgole(nomiStanze)) {
			String nome= null;
			String soglia= null;
			
			try (Scanner scannerDiLinea = new Scanner(specificaStanza)) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza "));
				nome = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la soglia per attivare l'effetto"));
				soglia = scannerDiLinea.next();
			}
			this.labirinto.addStanzaMagica(nome, Integer.parseInt(soglia));
		}
	}
	
	private void leggiECollocaStanzaBuia() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(BUIE_MARKER);
		for(String specificaStanza : separaStringheAlleVirgole(nomiStanze)) {
			String nome= null;
			String nomeAttrezzo= null;
			try (Scanner scannerDiLinea = new Scanner(specificaStanza)) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza "));
				nome = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo per rivelare la descrizione"));
				nomeAttrezzo = scannerDiLinea.next();
			}
			this.labirinto.addStanzaBuia(nome, nomeAttrezzo);
		}
	}
	
	private void leggiECollocaStanzaBloccata() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(BLOCCATE_MARKER);
		for(String specificaStanza : separaStringheAlleVirgole(nomiStanze)) {
			String nome= null;
			String dir= null;
			String nomeAttrezzo= null;
			try (Scanner scannerDiLinea = new Scanner(specificaStanza)) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza "));
				nome = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione bloccata"));
				dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo"));
				nomeAttrezzo = scannerDiLinea.next();
			}
			this.labirinto.addStanzaBloccata(nome, dir, nomeAttrezzo);
		}
	}
	
	private void leggiECollocaMago() throws FormatoFileNonValidoException {
		String specifichePersonaggi = this.leggiRigaCheCominciaPer(MAGO_MARKER);

		for(String specificaPersonaggio : this.separaStringheAlleVirgole(specifichePersonaggi)) {
			String nome=null;
			String nomeAttrezzo=null;
			String pesoAttrezzo= null;
			String nomeStanza= null;
			try (Scanner scannerDiLinea = new Scanner(specificaPersonaggio)) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome del personaggio "));
				nome = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo"));
				nomeAttrezzo = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza"));
				nomeStanza= scannerDiLinea.next();
			}
			this.aggiungiMago(nome, nomeAttrezzo, pesoAttrezzo, nomeStanza);
		} 
	}
	
	private void leggiECollocaStrega() throws FormatoFileNonValidoException {
		String specifichePersonaggi = this.leggiRigaCheCominciaPer(STREGA_MARKER);
		
		for(String specificaPersonaggio : this.separaStringheAlleVirgole(specifichePersonaggi)) {
			String nome=null;
			String nomeStanza= null;
			try (Scanner scannerDiLinea = new Scanner(specificaPersonaggio)) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome del personaggio "));
				nome = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza"));
				nomeStanza= scannerDiLinea.next();
			}
			this.aggiungiStrega(nome, nomeStanza);
		} 
	}
	
	private void leggiECollocaCane() throws FormatoFileNonValidoException {
		String specifichePersonaggi = this.leggiRigaCheCominciaPer(CANE_MARKER);

		for(String specificaPersonaggio : this.separaStringheAlleVirgole(specifichePersonaggi)) {
			String nome=null;
			String nomeAttrezzo=null;
			String pesoAttrezzo= null;
			String nomeStanza= null;
			try (Scanner scannerDiLinea = new Scanner(specificaPersonaggio)) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome del personaggio "));
				nome = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo"));
				nomeAttrezzo = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza"));
				nomeStanza= scannerDiLinea.next();
			}
			this.aggiungiCane(nome, nomeAttrezzo, pesoAttrezzo, nomeStanza);
		} 
	}
	
	private void aggiungiMago(String nome, String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		check(this.isStanzaValida(nomeStanza),"Stanza non valida");
		peso = Integer.parseInt(pesoAttrezzo);
		this.labirinto.setStanzaCorrente(this.labirinto.getMappaStanze().get(nomeStanza));
		this.labirinto.addMago(nome, "AbraCadabra!", nomeAttrezzo, peso);
	}
	
	private void aggiungiStrega(String nome, String nomeStanza) throws FormatoFileNonValidoException {
		check(this.isStanzaValida(nomeStanza),"Stanza non valida");
		this.labirinto.setStanzaCorrente(this.labirinto.getMappaStanze().get(nomeStanza));
		this.labirinto.addStrega(nome, "EHEHEHEH sono malvagia!!");
	}
	
	private void aggiungiCane(String nome, String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		check(this.isStanzaValida(nomeStanza),"Stanza non valida");
		peso = Integer.parseInt(pesoAttrezzo);
		this.labirinto.setStanzaCorrente(this.labirinto.getMappaStanze().get(nomeStanza));
		this.labirinto.addCane(nome, "BAUBAU!", nomeAttrezzo, peso);
	}

}
