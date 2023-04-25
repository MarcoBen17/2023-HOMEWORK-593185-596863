package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IOSimulatorTest {

	@Test
	void testUnComando() {
		assertEquals("fine", new IOSimulator("fine").leggiRiga());
	}

	@Test
	void testDueComandi() {
		IOSimulator IOSimulator = new IOSimulator("fine", "vai nord");
		assertEquals("fine",IOSimulator.leggiRiga());
		assertEquals("vai nord", IOSimulator.leggiRiga());
	}
	
	@Test
	void testPartitaSimulata() {
		DiaDia diadia;
		String messaggioDiBenvenuto = ""+
				"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
				"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
				"I locali sono popolati da strani personaggi, " +
				"alcuni amici, altri... chissa!\n"+
				"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
				"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
				"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
				"Per conoscere le istruzioni usa il comando 'aiuto'.";
		
		String[] stampe= {messaggioDiBenvenuto ,"Aula N10", "attrezzo raccolto"};
		
		IOSimulator iosimulator = new IOSimulator(stampe,"vai sud", "prendi lanterna");
		diadia= new DiaDia(iosimulator);
		diadia.gioca();
	}
}
