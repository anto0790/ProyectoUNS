package TDALista;

import ClasesCompartidas.Position;
/**
 * Clase DNodo que extiende a la interfaz Position
 * @author Katherina Vilca y Mariana Hernández.
 *
 * @param <E> Tipo generico E
 */
public class DNodo <E> implements Position <E>{

	private DNodo<E> anterior;
	private DNodo<E> siguiente;
	private E elem;
	
	/**
	 * Constructor de DNodo con el siguiente, el anterior y el elemento
	 * @param ant Anterior al nuevo nodo
	 * @param sig Siguiente al nuevo nodo
	 * @param e Elemento del nuevo nodo
	 */
	public DNodo (DNodo <E> ant, DNodo <E> sig, E e)
	{ anterior=ant;
	  siguiente=sig;
	  elem=e;
	}

	/**
	 * Consulta del elemento E del nodo 
	 * @return E elemento del nodo
	 */
	public E getElemento()
	{return elem;}
	
	/**
	 * Consulta el nodo siguiente
	 * @return Nodo siguiente 
	 */
	public DNodo<E> getSiguiente()
	{return siguiente;}
	
	/**
	 * Consulta el nodo anterior
	 * @return Nodo anterior
	 */
	public DNodo<E> getAnterior()
	{return anterior;}
	
	/**
	 * Modifica el nodo siguiente
	 * @param sig Nuevo nodo siguiente
	 */
	public void setSiguiente(DNodo<E> sig)
	{siguiente=sig;}
	
	/**
	 * Establece nuevo nodo anterior
	 * @param ant Nuevo nodo.
	 */
	public void setAnterior(DNodo<E> ant)
	{anterior=ant;}
	
	/**
	 * Establece nuevo elemento E al nodo
	 * @param e Elemento E nuevo
	 */
	public void setElemento( E e)
	{elem=e;}
	
	/**
	 * Consulta del elemento E del modo
	 * @return E elemento del nodo
	 */
	public E element() {
		return elem;
	}

}
