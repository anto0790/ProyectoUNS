package Excepciones;

/**
 * 
 * @author Katherina Vilca, Mariana Hern�ndez y Antonella Diomedi.
 * Clase excepci�n se da cuando una ruta es invalida.
 * 
 */
@SuppressWarnings("serial")
public class ExcepcionRutaInvalida extends Exception{
 
	/**
	 * Constructor de la exception ExceptionRutaInvalida
	 * @param s Mensaje que se recibe para inicializar el constructor de la excepci�n
	 */
	
	public ExcepcionRutaInvalida(String s)
	{super(s);}
}