package it.uniroma3.diadia.giocatore;


public class Giocatore {

	private Borsa borsa;
	private int cfu;

	public Giocatore() {
		this.borsa= new Borsa();
		this.cfu= 0;
	}
	
	
	public Borsa getBorsa() {
		return borsa;
	}


	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}


	public int getCfu() {
		return cfu;
	}


	public void setCfu(int cfu) {
		this.cfu = cfu;
	}



}
