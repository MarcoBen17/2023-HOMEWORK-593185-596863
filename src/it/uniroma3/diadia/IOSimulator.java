package it.uniroma3.diadia;

public class IOSimulator implements IO {

	private String[] comandiLetti;
	private String[] comandiStampati;
	private int indiceProxComando;
	private int indiceProxStampa;

	public IOSimulator(String[]comandistampa, String...comandiLetti){
		this.comandiLetti= comandiLetti;
		this.indiceProxComando=0;
		this.indiceProxStampa=0;
		this.comandiStampati= comandistampa;
	}
	
	public IOSimulator(String...comandiletti) {
		this(null, comandiletti);
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		if (this.comandiStampati[this.indiceProxStampa].equals(messaggio)) {
			System.out.println(messaggio+ "    --corretto comportamento del programma");
		}
		else System.out.println(messaggio+ "   --comportamento anomalo del programma");
		this.indiceProxStampa++;
	}

	@Override
	public String leggiRiga() {
		
		return this.comandiLetti[this.indiceProxComando++];
	}

}
