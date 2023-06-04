package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	@SuppressWarnings("deprecation")
	@Override
	public AbstractComando costruisciComando(String istruzione){

		@SuppressWarnings("resource")
		Scanner scannerDiParole = new Scanner(istruzione); // es. ‘vai sud’
		String nomeComando = null; // es. ‘vai’
		String parametro = null; // es. ‘sud’
		AbstractComando comando = null;

		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();//prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();//seconda parola: eventuale parametro

		try {
			StringBuilder nomeclasse= new StringBuilder();
			nomeclasse.append("it.uniroma3.diadia.comandi.Comando");
			nomeclasse.append(Character.toUpperCase(nomeComando.charAt(0)));
			nomeclasse.append(nomeComando.substring(1));
			comando= (AbstractComando)Class.forName(nomeclasse.toString()).newInstance();
			comando.setParametro(parametro);
		} catch (Exception e){
			comando= new ComandoNonValido();
		}
		return comando;
	}

}
