package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.io.LineNumberReader;
import java.io.StringReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CaricatoreLabirintoTest {

	private CaricatoreLabirinto caricatoreLab;
	private String specificheLab;
	private LineNumberReader reader;

	@BeforeEach
	void setUp() throws Exception {
		this.caricatoreLab= new CaricatoreLabirinto();
		this.reader= new LineNumberReader(new StringReader(this.specificheLab));
	}

	@Test
	void testLabirintoVuoto() {
		fail("Not yet implemented");
	}

}
