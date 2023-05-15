package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

public class IOSimulator implements IO {

	private List<String> comandiLetti;
	private List<String> comandiStampati;
	private int indiceProxComando;

	public IOSimulator(List<String> comandiLetti){
		this.comandiLetti= comandiLetti;
		this.comandiStampati= new ArrayList<String>();
		this.indiceProxComando=0;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		System.out.println(messaggio);
		this.comandiStampati.add(messaggio);
	}

	@Override
	public String leggiRiga() {
		return this.comandiLetti.get(this.indiceProxComando++);
	}

	public List<String> getComandiStampati() {
		return this.comandiStampati;
	}
	
}
