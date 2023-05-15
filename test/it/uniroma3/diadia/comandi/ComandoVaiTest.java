package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {
	
	private ComandoPosa posa;
	private String nomeStanzaIniziale= "Atrio";
	private String nomeStanzaVincente="Uscita";

	@BeforeEach
	public void setUp() {
		this.posa= new ComandoPosa();
		
	}

	@Test
	void test() {
		Labirinto bilocale = new LabirintoBuilder()
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addStanzaVincente(this.nomeStanzaVincente)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, nomeStanzaIniziale, "sud")
				.getLabirinto();
		
		DiaDia diadia= new DiaDia(bilocale, new IOSimulator(List.of("posa spada", "vai nord")));
		diadia.gioca();
		assertEquals(new Stanza(this.nomeStanzaVincente), diadia.getPartita().getStanzaCorrente());
	}

}
