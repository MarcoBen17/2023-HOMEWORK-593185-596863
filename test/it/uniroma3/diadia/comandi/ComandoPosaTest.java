package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {
	
	private Partita partita;
	private Attrezzo attrezzo;
	private Comando posa;
	private String nomeStanzaIniziale= "Atrio";
	private String nomeStanzaVincente= "Uscita";

	@BeforeEach
	public void setUp() {
		this.attrezzo= new Attrezzo("spada", 1);
		this.posa= new ComandoPosa();
	}

	@Test
	void testEseguiStanzaVuota() {
		this.partita= new Partita(new IOConsole());
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}

	@Test
	void testEsegui() {
		this.partita= new Partita(new IOConsole());
		this.partita.getGiocatore().getBorsa().addAttrezzo(this.attrezzo);
		this.posa.setParametro("spada");
		this.posa.esegui(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}
	
	@Test 
	void testComandoPosaBilocale() {
		Labirinto lab= new LabirintoBuilder()
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addStanzaVincente(this.nomeStanzaVincente)
				.addAdiacenza(this.nomeStanzaIniziale, this.nomeStanzaVincente, "nord")
				.addAdiacenza(this.nomeStanzaVincente, this.nomeStanzaIniziale, "sud")
				.getLabirinto();
		DiaDia diadia= new DiaDia(lab,new IOSimulator(List.of("posa spada", "vai nord")));
		diadia.getPartita().getGiocatore().getBorsa().addAttrezzo(this.attrezzo);
		diadia.gioca();
		assertTrue(lab.getStanza(this.nomeStanzaIniziale).hasAttrezzo("spada"));
	}
}
