package Excepciones;

/**
 * 
 * @author Katherina Vilca, Mariana Hern�ndez y Antonella Diomedi.
 * Clase excepci�n se da cuando una carpeta es invalida.
 * 
 */
@SuppressWarnings("serial")
public class ExcepcionCarpetaInvalida extends Exception{
 
	/**
	 * Constructor de la exception ExceptionCarpetaInvalida
	 * @param s Mensaje que se recibe para inicializar el constructor de la excepci�n
	 */
	
	public ExcepcionCarpetaInvalida(String s)
	{super(s);}
}
