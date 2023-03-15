package Excepciones;

/**
 * 
 * @author Katherina Vilca, Mariana Hernández y Antonella Diomedi.
 * Clase excepción se da cuando una archivo es invalido.
 * 
 */
@SuppressWarnings("serial")
public class ExcepcionArchivoInvalido extends Exception{
 
	/**
	 * Constructor de la exception ExceptionArchivoInvalido
	 * @param s Mensaje que se recibe para inicializar el constructor de la excepción
	 */
	
	public ExcepcionArchivoInvalido(String s)
	{super(s);}
}