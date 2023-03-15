package Excepciones;

/**
 * 
 * @author Katherina Vilca, Mariana Hernández y Antonella Diomedi.
 * Clase excepción se da cuando una ruta es invalida.
 * 
 */
@SuppressWarnings("serial")
public class ExcepcionRutaInvalida extends Exception{
 
	/**
	 * Constructor de la exception ExceptionRutaInvalida
	 * @param s Mensaje que se recibe para inicializar el constructor de la excepción
	 */
	
	public ExcepcionRutaInvalida(String s)
	{super(s);}
}