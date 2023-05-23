package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {

/**
* esecuzione del comando
*/
public abstract void esegui(Partita partita);

/**
* set parametro del comando
*/
public void setParametro(String parametro) { return; }

public String getParametro() {return null;}

}

