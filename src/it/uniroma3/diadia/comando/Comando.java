package it.uniroma3.diadia.comando;
import it.uniroma3.diadia.Partita;

public interface Comando {

/**
* esecuzione del comando
*/
public void esegui(Partita partita);

/**
* set parametro del comando
*/
public void setParametro(String parametro);

}