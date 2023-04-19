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
	void testNessunComando() {
		
	}
}
