package TDALista;
import java.util.Iterator;
import java.util.NoSuchElementException;

import ClasesCompartidas.*;
/**
 * Clase ElementoIterador que implementa a la interfaz Iterator
 * @author Katherina Vilca y Mariana Hernandez.
 *
 * @param <E> Tipo generico E
 */
public class ElementIterator<E> implements Iterator<E>{

	protected PositionList<E> lista;
	protected Position<E> cursor;
	
	/**
	 * Constructor de la clase que recibe una lista
	 * @param lis . 
	 */
	public ElementIterator(PositionList<E> lis) {
	
		try {
			lista = lis;
			if (lista.isEmpty()) 
				cursor = null;
			else
				cursor = lista.first();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * Consulta si existe una posicion siguiente
     * @return Verdadero si existe, falto en caso contrario.
     */
	public boolean hasNext() {
		return (cursor != null);
	}

	/**
	 * Devuelve el elemento siguiente del iterador
	 * @return Retorna el elemento siguiente.
	 */
	public E next() throws NoSuchElementException{
		
		E aux = null;
		if ( cursor == null)
			throw new NoSuchElementException("No tiene siguiente");
		try {
			aux = cursor.element();
			if(cursor == lista.last())
				cursor=null;
			else cursor=lista.next(cursor);
			
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			throw new NoSuchElementException(e.getMessage());
		}
		return aux;
	}

}
