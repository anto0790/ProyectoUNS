package ClasesCompartidas;

/**
 * 
 * @author Katherina Vilca y Mariana Hern�ndez.
 * Clase de la excepci�n de Lista cuando una posicion no es v�lida. Extiende a Exception
 * 
 */
@SuppressWarnings("serial")
public class InvalidPositionException extends Exception {
	/**
	 * 
	 * @param s Mensaje que se recibe para inicializar el constructor de la excepci�n
	 * 
	 */
	public InvalidPositionException(String s)
	{super(s);}
}
