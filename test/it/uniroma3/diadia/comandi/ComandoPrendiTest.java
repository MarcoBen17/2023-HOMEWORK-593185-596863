package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;


class ComandoPrendiTest {
	private Partita partita;
	private Attrezzo attrezzo;
	private String nomeStanzaIniziale= "Atrio";
	private String nomeStanzaVincente= "Uscita";
	private AbstractComando prendi;

	@BeforeEach
	public void setUp() {
		this.partita= new Partita(new IOConsole(new Scanner(System.in)));
		this.attrezzo= new Attrezzo("spada", 1);
		this.prendi= new ComandoPrendi();
		this.partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
	}
	
	
	@Test
	void testEseguiStanzaVuota() {
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}

	@Test
	void testEsegui() {
		this.prendi.setParametro("spada");
		this.prendi.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}
	
	@Test 
	void testComandoPrendiBilocale() {
		Labirinto lab= new Labirinto.LabirintoBuilder()
				.addStanzaIniziale(this.nomeStanzaIniziale).addAttrezzo("spada", 1).addAttrezzo("lancia", 10)
				.addStanzaVincente(this.nomeStanzaVincente)
				.addAdiacenza(this.nomeStanzaIniziale, this.nomeStanzaVincente, "nord")
				.addAdiacenza(this.nomeStanzaVincente, this.nomeStanzaIniziale, "sud")
				.getLabirinto();
		DiaDia diadia= new DiaDia(lab,new IOSimulator(List.of("prendi spada", "prendi lancia", "vai nord")));
		diadia.gioca();
		assertTrue(diadia.getPartita().getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertFalse(diadia.getPartita().getGiocatore().getBorsa().hasAttrezzo("lancia"));
	}
}
