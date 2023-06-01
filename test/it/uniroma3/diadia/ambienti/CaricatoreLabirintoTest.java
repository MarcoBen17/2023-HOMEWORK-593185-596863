package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

class CaricatoreLabirintoTest {

	@Test
	void testLabirintoDueStanze() throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto caricatoreLab= new CaricatoreLabirinto("testCaricatoreDueStanze.txt");
		caricatoreLab.carica();
		assertTrue(caricatoreLab.getLabirinto().getMappaStanze().size()==2);
	}
	
	@Test
	void testLabirintoStanzaBuia() throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto caricatoreLab= new CaricatoreLabirinto("testCaricatoreStanzaBuia.txt");
		caricatoreLab.carica();
		Stanza buia= new StanzaBuia(null, null);
		Class<? extends Stanza> stanza= buia.getClass();
		assertTrue(caricatoreLab.getLabirinto().getMappaStanze().get("N10").getClass().equals(stanza));
	}
	
	@Test
	void testLabirintoDueStanzeConAttrezzo() throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto caricatoreLab= new CaricatoreLabirinto("testCaricatoreDueStanze.txt");
		caricatoreLab.carica();
		assertTrue(caricatoreLab.getLabirinto().getMappaStanze().size()==2);
		assertTrue(caricatoreLab.getStanzaIniziale().hasAttrezzo("spada"));
	}
	
	@Test
	void testLabirintoDueStanzeConAttrezzoEPersonaggio() throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto caricatoreLab= new CaricatoreLabirinto("testCaricatoreDueStanze.txt");
		caricatoreLab.carica();
		assertTrue(caricatoreLab.getLabirinto().getMappaStanze().size()==2);
		assertTrue(caricatoreLab.getStanzaIniziale().hasAttrezzo("spada"));
		assertNotNull(caricatoreLab.getStanzaIniziale().getPersonaggio());
	}
}
