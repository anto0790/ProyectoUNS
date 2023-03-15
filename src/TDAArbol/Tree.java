package TDAArbol;
import java.util.Iterator;

import ClasesCompartidas.*;

/**
 * Interface Tree
 * Es la versi�n extendida de la interfaz presentada por Goodrich y Tamassia en su cuarta edici�n. En esta interfaz se incluyen las operaciones necesarias 
 * para modificar el �rbol.
 * @author C�tedra de Estructuras de Datos, Departamento de Cs. e Ing. de la Computaci�n, UNS.
 */

public interface Tree<E> extends Iterable<E>
{
	/**
	 * Consulta la cantidad de nodos del �rbol.
	 * @return Retorna la cantidad de nodos del �rbol.
	 */
	public int size();
	
	/**
	 * Consulta si el �rbol esta vac�o.
	 * @return Retorna verdadero si el �rbol est� vac�o, falso en caso contrario.
	 */
	public boolean isEmpty();
	
	/**
	 * Devuelve un iterador de los elementos almacenados en el �rbol en preorden.
	 * @return Retorna un iterador de los elementos almacenados en el �rbol.
	 */
	public Iterator<E> iterator();
	
	/**
	 * Devuelve una colecci�n iterable de las posiciones de los nodos del �rbol.
	 * @return Retorna una colecci�n iterable de las posiciones de los nodos del �rbol.
	 */
	public Iterable<Position<E>> positions();
	
	/**
	 * Devuelve el elemento ubicado en determinada posici�n y lo reemplaza por el elemento pasado por parametro.
	 * @param v Posici�n de un nodo.
	 * @param e Elemento a reemplazar en la posici�n pasada por parametro.
	 * @return Retorna el elemento reemplazado.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida.
	 */
	public E replace(Position<E> v, E e) throws InvalidPositionException;
	
	/**
	 * Devuelve la posici�n de la ra�z del �rbol.
	 * @return Retorna la posici�n de la ra�z del �rbol.
	 * @throws EmptyTreeException si el �rbol esta vac�o.
	 */
	public Position<E> root() throws EmptyTreeException;
	
	/**
	 * Devuelve la posici�n del nodo padre del nodo correspondiente a determinada posici�n
	 * @param v posici�n de un nodo.
	 * @return Retorna la posici�n del nodo padre del nodo correspondiente a la posici�n pasada por parametro.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida.
	 * @throws BoundaryViolationException si la posici�n pasada por parametro corresponde a la ra�z del �rbol.
	 */
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Devuelve una colecci�n iterable de los hijos del nodo correspondiente a una posici�n determinada.
	 * @param v posici�n de un nodo.
	 * @return Retorna una colecci�n iterable de los hijos del nodo correspondiente a la posici�n pasada por parametro.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida.
	 */
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Consulta si una posici�n determinada corresponde a un nodo interno.
	 * @param v posici�n de un nodo.
	 * @return Retorna verdadero si la posici�n pasada por parametro corresponde a un nodo interno, falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida.
	 */
	public boolean isInternal(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Consulta si una posici�n determinada corresponde a un nodo externo.
	 * @param v posici�n de un nodo.
	 * @return Retorna verdadero si la posici�n pasada por parametro corresponde a un nodo externo, falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida.
	 */
public boolean isExternal(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Consulta si una posici�n determinada corresponde a la ra�z del �rbol.
	 * @param v posici�n de un nodo.
	 * @return Retorna verdadero, si la posici�n pasada por parametro corresponde a la ra�z del �rbol,falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida.
	 */
	public boolean isRoot(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Crea un nodo con r�tulo e como ra�z del �rbol.
	 * @param e r�tulo que se asignar� a la ra�z del �rbol.
	 * @throws InvalidOperationException si el �rbol ya tiene un nodo ra�z.
	 */
	public void createRoot(E e) throws InvalidOperationException;
	
	/**
	 * Agrega un nodo con r�tulo e como primer hijo de un nodo determinado.
	 * @param e r�tulo del nuevo nodo.
	 * @param p posici�n del nodo padre.
	 * @return Retorna la posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida o el �rbol est� vac�o.
	 */
	public Position<E> addFirstChild(Position<E> p, E e) throws	InvalidPositionException;
	
	/**
	 * Agrega un nodo con r�tulo e como �ltimo hijo de un nodo determinado.
	 * @param e r�tulo del nuevo nodo.
	 * @param p posici�n del nodo padre.
	 * @return Retorna la posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida o el �rbol est� vac�o.
	 */
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException;
	
	/**
	 * Agrega un nodo con r�tulo e como hijo de un nodo padre determinado. El nuevo nodo se agregar� delante de otro nodo tambi�n determinado.
	 * @param e R�tulo del nuevo nodo.
	 * @param p posici�n del nodo padre.
	 * @param rb posici�n del nodo que ser� el hermano derecho del nuevo nodo.
	 * @return Retorna la posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida, o el �rbol est� vac�o, o si la posici�n rb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException;
	
	/**
	 * Agrega un nodo con r�tulo e como hijo de un nodo padre determinado. El nuevo nodo se agregar� a continuaci�n de otro nodo tambi�n determinado.
	 * @param e R�tulo del nuevo nodo.
	 * @param p posici�n del nodo padre.
	 * @param lb posici�n del nodo que ser� el hermano izquierdo del nuevo nodo.
	 * @return Retorna la posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida, o el �rbol est� vac�o, o si la posici�n lb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	public Position<E> addAfter (Position<E> p, Position<E> lb, E e) throws InvalidPositionException;
	
	/**
	 * Elimina el nodo referenciado por una posici�n determinada, si se trata de un nodo externo. 
	 * @param p posici�n del nodo a eliminar.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida o no corresponde a un nodo externo, o si el �rbol est� vac�o.
	 */
	public void removeExternalNode (Position<E> p) throws InvalidPositionException;
	
	/**
	 * Elimina el nodo referenciado por una posici�n determinada, si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazar�n en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la ra�z del �rbol, �nicamente podr� ser eliminado si tiene un solo hijo, el cual lo reemplazar�.
	 * @param p posici�n del nodo a eliminar.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es inv�lida o no corresponde a un nodo interno o corresponde a la ra�z (con m�s de un hijo), o si el �rbol est� vac�o.
	 */
	public void removeInternalNode (Position<E> p) throws InvalidPositionException;
	
	/**
	 * Elimina el nodo referenciado por una posici�n determinada. Si se tratara de un nodo interno, los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la ra�z del �rbol, �nicamente podr� ser eliminado si tiene un solo hijo, el cual lo reemplazar�.
	 * @param p posici�n del nodo a eliminar.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida o corresponde a la ra�z (con m�s de un hijo), o el �rbol est� vac�o.
	 */
	public void removeNode (Position<E> p) throws InvalidPositionException;
	
	
	

}