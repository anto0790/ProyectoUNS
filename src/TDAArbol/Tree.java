package TDAArbol;
import java.util.Iterator;

import ClasesCompartidas.*;

/**
 * Interface Tree
 * Es la versión extendida de la interfaz presentada por Goodrich y Tamassia en su cuarta edición. En esta interfaz se incluyen las operaciones necesarias 
 * para modificar el árbol.
 * @author Cátedra de Estructuras de Datos, Departamento de Cs. e Ing. de la Computación, UNS.
 */

public interface Tree<E> extends Iterable<E>
{
	/**
	 * Consulta la cantidad de nodos del Árbol.
	 * @return Retorna la cantidad de nodos del Árbol.
	 */
	public int size();
	
	/**
	 * Consulta si el Árbol esta vacío.
	 * @return Retorna verdadero si el Árbol está vacío, falso en caso contrario.
	 */
	public boolean isEmpty();
	
	/**
	 * Devuelve un iterador de los elementos almacenados en el Árbol en preorden.
	 * @return Retorna un iterador de los elementos almacenados en el Árbol.
	 */
	public Iterator<E> iterator();
	
	/**
	 * Devuelve una colección iterable de las posiciones de los nodos del Árbol.
	 * @return Retorna una colección iterable de las posiciones de los nodos del Árbol.
	 */
	public Iterable<Position<E>> positions();
	
	/**
	 * Devuelve el elemento ubicado en determinada posición y lo reemplaza por el elemento pasado por parametro.
	 * @param v Posición de un nodo.
	 * @param e Elemento a reemplazar en la posición pasada por parametro.
	 * @return Retorna el elemento reemplazado.
	 * @throws InvalidPositionException si la posición pasada por parametro es invalida.
	 */
	public E replace(Position<E> v, E e) throws InvalidPositionException;
	
	/**
	 * Devuelve la posición de la raíz del Árbol.
	 * @return Retorna la posición de la raíz del Árbol.
	 * @throws EmptyTreeException si el Árbol esta vacío.
	 */
	public Position<E> root() throws EmptyTreeException;
	
	/**
	 * Devuelve la posición del nodo padre del nodo correspondiente a determinada posición
	 * @param v posición de un nodo.
	 * @return Retorna la posición del nodo padre del nodo correspondiente a la posición pasada por parametro.
	 * @throws InvalidPositionException si la posición pasada por parametro es invalida.
	 * @throws BoundaryViolationException si la posición pasada por parametro corresponde a la raíz del Árbol.
	 */
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Devuelve una colección iterable de los hijos del nodo correspondiente a una posición determinada.
	 * @param v posición de un nodo.
	 * @return Retorna una colección iterable de los hijos del nodo correspondiente a la posición pasada por parametro.
	 * @throws InvalidPositionException si la posición pasada por parametro es invalida.
	 */
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Consulta si una posición determinada corresponde a un nodo interno.
	 * @param v posición de un nodo.
	 * @return Retorna verdadero si la posición pasada por parametro corresponde a un nodo interno, falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parametro es invalida.
	 */
	public boolean isInternal(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Consulta si una posición determinada corresponde a un nodo externo.
	 * @param v posición de un nodo.
	 * @return Retorna verdadero si la posición pasada por parametro corresponde a un nodo externo, falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parametro es invalida.
	 */
public boolean isExternal(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Consulta si una posición determinada corresponde a la raíz del Árbol.
	 * @param v posición de un nodo.
	 * @return Retorna verdadero, si la posición pasada por parametro corresponde a la raíz del Árbol,falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parametro es invalida.
	 */
	public boolean isRoot(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Crea un nodo con rótulo e como raíz del Árbol.
	 * @param e rótulo que se asignará a la raíz del Árbol.
	 * @throws InvalidOperationException si el Árbol ya tiene un nodo raíz.
	 */
	public void createRoot(E e) throws InvalidOperationException;
	
	/**
	 * Agrega un nodo con rótulo e como primer hijo de un nodo determinado.
	 * @param e rótulo del nuevo nodo.
	 * @param p posición del nodo padre.
	 * @return Retorna la posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parametro es invalida o el Árbol está vacío.
	 */
	public Position<E> addFirstChild(Position<E> p, E e) throws	InvalidPositionException;
	
	/**
	 * Agrega un nodo con rótulo e como último hijo de un nodo determinado.
	 * @param e rótulo del nuevo nodo.
	 * @param p posición del nodo padre.
	 * @return Retorna la posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parametro es invalida o el Árbol está vacío.
	 */
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException;
	
	/**
	 * Agrega un nodo con rótulo e como hijo de un nodo padre determinado. El nuevo nodo se agregará delante de otro nodo también determinado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p posición del nodo padre.
	 * @param rb posición del nodo que será el hermano derecho del nuevo nodo.
	 * @return Retorna la posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parametro es invalida, o el Árbol está vacío, o si la posición rb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException;
	
	/**
	 * Agrega un nodo con rótulo e como hijo de un nodo padre determinado. El nuevo nodo se agregará a continuación de otro nodo también determinado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p posición del nodo padre.
	 * @param lb posición del nodo que será el hermano izquierdo del nuevo nodo.
	 * @return Retorna la posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parametro es invalida, o el Árbol está vacío, o si la posición lb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	public Position<E> addAfter (Position<E> p, Position<E> lb, E e) throws InvalidPositionException;
	
	/**
	 * Elimina el nodo referenciado por una posición determinada, si se trata de un nodo externo. 
	 * @param p posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parametro es invalida o no corresponde a un nodo externo, o si el Árbol está vacío.
	 */
	public void removeExternalNode (Position<E> p) throws InvalidPositionException;
	
	/**
	 * Elimina el nodo referenciado por una posición determinada, si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazarán en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la raíz del Árbol, únicamente podrá ser eliminado si tiene un solo hijo, el cual lo reemplazará.
	 * @param p posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parametro es inválida o no corresponde a un nodo interno o corresponde a la raíz (con más de un hijo), o si el Árbol está vacío.
	 */
	public void removeInternalNode (Position<E> p) throws InvalidPositionException;
	
	/**
	 * Elimina el nodo referenciado por una posición determinada. Si se tratara de un nodo interno, los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la raíz del Árbol, únicamente podrá ser eliminado si tiene un solo hijo, el cual lo reemplazará.
	 * @param p posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parametro es invalida o corresponde a la raíz (con más de un hijo), o el Árbol está vacío.
	 */
	public void removeNode (Position<E> p) throws InvalidPositionException;
	
	
	

}