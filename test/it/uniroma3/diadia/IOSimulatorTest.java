package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class IOSimulatorTest {
	
	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	private String nomeStanzaIniziale= "Atrio";
	private String nomeStanzaVincente= "Uscita";

	@Test
	void testUnComando() {
		assertEquals("fine", new IOSimulator(new ArrayList<String>(List.of("fine"))).leggiRiga());
	}

	@Test
	void testDueComandi() {
		IOSimulator IOSimulator = new IOSimulator(new ArrayList<String>(List.of("fine", "vai nord")));
		assertEquals("fine",IOSimulator.leggiRiga());
		assertEquals("vai nord", IOSimulator.leggiRiga());
	}
	
	@Test
	void testPiccolaPartitaSimulata() {
		DiaDia diadia;
		List<String> stampe= new ArrayList<String>(List.of(MESSAGGIO_BENVENUTO ,"Aula N10", "attrezzo raccolto", "attrezzo posato", "Grazie di aver giocato!"));
		IOSimulator iosimulator = new IOSimulator(new ArrayList<String>(List.of("vai sud", "prendi lanterna", "posa lanterna", "fine")));
		diadia= new DiaDia(iosimulator);
		diadia.gioca();
		assertEquals(stampe, iosimulator.getComandiStampati());
		
	}
	
	@Test
	void testPartitaConLabirintoSimulatoStanzaBloccata() {
		DiaDia diadia;
		Labirinto lab= new LabirintoBuilder()
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("chiave", 1)
				.addStanzaBloccata("stanza bloccata", "nord", "chiave")
				.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", "nord")
				.addAdiacenza("stanza bloccata", nomeStanzaIniziale, "sud")
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("stanza bloccata", nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, "stanza bloccata", "sud")
				.getLabirinto();
		List<String> stampe= new ArrayList<String>(List.of(MESSAGGIO_BENVENUTO ,"stanza bloccata", "stanza bloccata", "Atrio" , "attrezzo raccolto" ,"stanza bloccata" ,"attrezzo posato" ,"Uscita" ,"Hai vinto!"));
		IOSimulator iosimulator = new IOSimulator(new ArrayList<String>(List.of("vai nord", "vai nord", "vai sud", "prendi chiave", "vai nord" ,"posa chiave" ,"vai nord")));
		diadia= new DiaDia(lab,iosimulator);
		diadia.gioca();
		assertEquals(stampe, iosimulator.getComandiStampati());
	}
	
}
