package TDADiccionario;

/**
 * Retorna en caso de una entrada que no existe.
 * @author Antonela Diomedi, Katherina Vilca y Mariana Hernández.
 *
 */
public class InvalidEntryException extends Exception {
	
	/**
	 * Constructor de la excepción
	 * @param s mensaje de la excepción
	 */
	public InvalidEntryException(String s)
	{super(s);}

}
