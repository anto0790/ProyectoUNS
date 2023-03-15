package Excepciones;

/**
 * 
 * @author Katherina Vilca, Mariana Hernández y Antonella Diomedi.
 * Clase excepción se da cuando una carpeta es invalida.
 * 
 */
@SuppressWarnings("serial")
public class ExcepcionCarpetaInvalida extends Exception{
 
	/**
	 * Constructor de la exception ExceptionCarpetaInvalida
	 * @param s Mensaje que se recibe para inicializar el constructor de la excepción
	 */
	
	public ExcepcionCarpetaInvalida(String s)
	{super(s);}
}
