package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

	private final static String VERSO_MORSO= "AAARRGG... \nIl cane ti ha morso, la prossima volta che iteragisci con lui prova "
			+ "a donargli un osso magari in qualche modo ti ricompenserà...";

	private final static String VERSO= "BAUU<3...\nIl cane apprezza il dono che gli hai fatto e ti ricompensa con un attrezzo"
			+ ", sono sicuro che ti sarà molto utile!!";

	private final static String CIBO_PREFERITO= "osso";

	private Attrezzo attrezzo;


	public Cane(String nome, String descrizione, Attrezzo attrezzo) {
		super(nome, descrizione);
		this.attrezzo= attrezzo;
	}


	@Override
	public String agisci(Partita partita) {
		int cfu=partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(cfu-1);
		return VERSO_MORSO;
	}


	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo.getNome().equals(CIBO_PREFERITO)) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			return VERSO;
		}
		else return this.agisci(partita);
	}

}
