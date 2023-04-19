package it.uniroma3.diadia;

public class IOSimulator implements IO {
	
	private String[] comandiLetti;
	private int indiceProxComando;

	public IOSimulator(String...comandiLetti){
		this.comandiLetti= comandiLetti;
		this.indiceProxComando=0;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		
	}

	@Override
	public String leggiRiga() {
		return this.comandiLetti[this.indiceProxComando++];
	}

}
