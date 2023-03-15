package TDALista;

/**
 * 
 * Clase de la excepción de Lista vacia que extiende a Exception
 * @author Katherina Vilca y Mariana Hernández.
 */
@SuppressWarnings("serial")
public class EmptyListException extends Exception{
	/**
	 * 
	 * @param s Mensaje que se recibe para inicializar el constructor de la excepción
	 * 
	 */
	public EmptyListException (String s)
	{super(s);}

}
