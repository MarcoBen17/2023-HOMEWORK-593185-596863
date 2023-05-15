package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {

	private String nomeStanzaIniziale= "Atrio";
	private String nomeStanzaVincente= "Uscita";

	
	@Test
	void testEseguiBilocale() {
		Labirinto lab= new LabirintoBuilder()
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addStanzaVincente(this.nomeStanzaVincente)
				.addAdiacenza(this.nomeStanzaIniziale, this.nomeStanzaVincente, "nord")
				.addAdiacenza(this.nomeStanzaVincente, this.nomeStanzaIniziale, "sud")
				.getLabirinto();
		DiaDia diadia= new DiaDia(lab,new IOSimulator(List.of("vai nord")));
		diadia.gioca();
		assertEquals(new Stanza(this.nomeStanzaVincente),diadia.getPartita().getStanzaCorrente());
	}
	
	
	
}
