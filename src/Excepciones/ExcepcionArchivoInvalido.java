package Excepciones;

/**
 * 
 * @author Katherina Vilca, Mariana Hern�ndez y Antonella Diomedi.
 * Clase excepci�n se da cuando una archivo es invalido.
 * 
 */
@SuppressWarnings("serial")
public class ExcepcionArchivoInvalido extends Exception{
 
	/**
	 * Constructor de la exception ExceptionArchivoInvalido
	 * @param s Mensaje que se recibe para inicializar el constructor de la excepci�n
	 */
	
	public ExcepcionArchivoInvalido(String s)
	{super(s);}
}