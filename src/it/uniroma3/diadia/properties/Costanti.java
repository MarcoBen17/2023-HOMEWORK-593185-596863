package it.uniroma3.diadia.properties;
import java.io.*;
import java.util.Properties;

class Costanti
{
	public static void main(String[] args) throws IOException
	{
		Properties prop = new Properties();
		prop.setProperty("cfu_iniziali", "10");
		prop.setProperty("peso_max_borsa", "20");
		prop.store(new FileWriter("diadia.properties"),
				"Configurazione del gioco DIADIA");
	}
}
