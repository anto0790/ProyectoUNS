
package ClasesCompartidas;

/**
 * 
 * @author Katherina Vilca y Mariana Hern�ndez.
 * Clase de la excepci�n de Lista cuando se excede sus limites que extiende a Exception
 * 
 */
@SuppressWarnings("serial")
public class BoundaryViolationException extends Exception{
 
	/**
	 * 
	 * @param s Mensaje que se recibe para inicializar el constructor de la excepci�n
	 * 
	 */
	
	public BoundaryViolationException(String s)
	{super(s);}
}
