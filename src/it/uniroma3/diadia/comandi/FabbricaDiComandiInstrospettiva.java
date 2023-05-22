package it.uniroma3.diadia.comandi;

import java.util.Scanner;


public class FabbricaDiComandiInstrospettiva implements FabbricaDiComandi{

	@SuppressWarnings("deprecation")
	@Override
	public Comando costruisciComando(String istruzione) {

		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		
		StringBuilder stringa= new StringBuilder();
		stringa.append("it.uniroma3.diadia.comandi.Comando");	

		try {
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando

		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); // seconda parola: eventuale parametro
		
		stringa.append(nomeComando.toUpperCase().charAt(0));
		stringa.append(nomeComando.substring(1));
		comando=(Comando)Class.forName(stringa.toString()).newInstance();
		comando.setParametro(parametro);	}
		catch (Exception e) {
			comando=new ComandoNonValido();
		}
		
		
		return comando;
	}
}

