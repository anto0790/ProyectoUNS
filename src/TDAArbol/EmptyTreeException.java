package TDAArbol;

/**
 *  Retorna el caso de un Arbol Vacio.
 *  @author Katherina Vilca y Mariana Hern�ndez.
 */

public class EmptyTreeException extends Exception {

	/**
	 * Constructor de la excepción
	 * @param s mensaje de la excepción
	 */
	public EmptyTreeException(String s) {
		super(s);
	}
}
