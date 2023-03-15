package TDAPila;

/**
 * Retorna el caso de una pila vacía.
 * @author Antonela Diomedi, Katherina Vilca y Mariana Hernández.
 *
 */
public class EmptyStackException extends Exception
{ 
	/**
	 * Constructor de la clase EmptyStackException.
	 * @param s mensaje de la excepción.
	 */
	public EmptyStackException(String s)
	{super(s);}

}
