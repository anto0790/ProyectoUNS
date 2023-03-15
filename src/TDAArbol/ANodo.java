package TDAArbol;

import TDALista.PositionList;
import ClasesCompartidas.Position;
import TDALista.ListaDoblementeEnlazada;;

/**
 * Clase ANodo que extiende a la interfaz Position
 * @author Katherina Vilca y Mariana Hernández.
 *
 * @param <E> Tipo generico E
 */
public class ANodo<E> implements Position<E>{

	private E rot;
	private ANodo<E> parent;
	private PositionList<ANodo<E>> children;
	
	/**
	 * Constructor de ANodo que recibe el elemento.
	 * @param e Elemento del nuevo nodo
	 */
	public ANodo(E e) {
		rot = e;
		parent = null;
		children = new ListaDoblementeEnlazada<ANodo<E>>();
	}
	/**
	 * Consulta del elemento E del modo
	 * @return E elemento del nodo
	 */
	public E element() {
		return rot;
	}
	
	/**
	 * Establece el nuevo elemento E al nodo.
	 * @param e . E elemento del nodo
	 */
	public void setRot(E e) {
		rot = e;
	}
	
	/**
	 * Devuelve la posicion del padre de nodo.
	 * @return Posicion del padre.
	 */
	public Position<E> getParent(){return parent;}
	
	/**
	 * Establece un nuevo padre al nodo.
	 * @param e . Nuevo padre a establecer.
	 */
	public void setParent(Position<E> e) {parent = (ANodo<E>) e;}
	
	/**
	 * Devuelve una lista de nodos hijos del nodo principal.
	 * @return Lista de hijos del nodo.
	 */
	public PositionList<ANodo<E>> getChildren(){return children;}
 
}
