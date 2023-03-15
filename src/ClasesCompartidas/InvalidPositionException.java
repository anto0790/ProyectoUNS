package ClasesCompartidas;

/**
 * 
 * @author Katherina Vilca y Mariana Hernández.
 * Clase de la excepción de Lista cuando una posicion no es válida. Extiende a Exception
 * 
 */
@SuppressWarnings("serial")
public class InvalidPositionException extends Exception {
	/**
	 * 
	 * @param s Mensaje que se recibe para inicializar el constructor de la excepción
	 * 
	 */
	public InvalidPositionException(String s)
	{super(s);}
}
