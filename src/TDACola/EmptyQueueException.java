package TDACola;

/** 
 * Clase de la excepcion EmptyQueueExcepcion que extiende a Exception
 * @author Katherina Vilca y Mariana Hernandez.
 *
 */

@SuppressWarnings("serial")
public class EmptyQueueException extends Exception {
	
	/**
	 * 
	 * @param s Mensaje que se recibe para inicializar el construsctor de la excepción
	 * 
	 */
	public EmptyQueueException (String s)
	{super(s);}
}
