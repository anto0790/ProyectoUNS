package TDALista;

/**
 * 
 * Clase de la excepci�n de Lista vacia que extiende a Exception
 * @author Katherina Vilca y Mariana Hern�ndez.
 */
@SuppressWarnings("serial")
public class EmptyListException extends Exception{
	/**
	 * 
	 * @param s Mensaje que se recibe para inicializar el constructor de la excepci�n
	 * 
	 */
	public EmptyListException (String s)
	{super(s);}

}
