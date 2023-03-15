
package ClasesCompartidas;

/**
 * 
 * @author Katherina Vilca y Mariana Hernández.
 * Clase de la excepción de Lista cuando se excede sus limites que extiende a Exception
 * 
 */
@SuppressWarnings("serial")
public class BoundaryViolationException extends Exception{
 
	/**
	 * 
	 * @param s Mensaje que se recibe para inicializar el constructor de la excepción
	 * 
	 */
	
	public BoundaryViolationException(String s)
	{super(s);}
}
