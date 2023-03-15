package TDADiccionario;

/**
 * Retorna en caso de una entrada que no existe.
 * @author Antonela Diomedi, Katherina Vilca y Mariana Hern�ndez.
 *
 */
public class InvalidEntryException extends Exception {
	
	/**
	 * Constructor de la excepci�n
	 * @param s mensaje de la excepci�n
	 */
	public InvalidEntryException(String s)
	{super(s);}

}
