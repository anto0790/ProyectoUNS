package TDALista;
import java.util.Iterator;

import ClasesCompartidas.*;

/**
 * Clase ListaDoblementeEnlazada que implementa a la interfaz PositionList
 * @author Katherina Vilca y Mariana Hernández
 *
 * @param <E> Tipo generico E
 */
	public class ListaDoblementeEnlazada <E> implements PositionList<E> {
		private DNodo <E> header;
		private DNodo <E> trailer;
		private int tamaño;

 	/**
 	 * Constructor de la lista
 	 */
	public ListaDoblementeEnlazada()
	{header= new DNodo <E>(null,null,null);
	trailer=new DNodo <E> (header,null,null);
	header.setSiguiente(trailer);
	tamaño=0;
	}
	
	/**
	 * Devuelve la posición del primer elemento de la lista. 
	 * @return Posición del primer elemento de la lista.
	 * @throws EmptyListException si la lista está vacía.
	 */
	public Position<E> first() throws EmptyListException {
	if (tamaño==0) throw new EmptyListException ("Lista vacia");
		else 
			return header.getSiguiente();
		}
	

	/**
	 * Devuelve la posición del último elemento de la lista. 
	 * @return Posición del último elemento de la lista.
	 * @throws EmptyListException si la lista está vacía.
	 */
	public Position<E> last() throws EmptyListException {
	if(tamaño==0)throw new EmptyListException ("Lista vacia");
	else
		return trailer.getAnterior();}
	
	
	/**
	 * Devuelve la posición previa a cierta posicion "p" de la lista
	 * @param p Posición a obtener su elemento anterior.
	 * @return Posición previa a p .
	 * @throws InvalidPositionException si la lista está vacía.
	 * @throws BoundaryViolationException cuando se exceden los limites de la estructura
	 * 
	 */
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> n =checkPosition(p);
		if (n==header.getSiguiente()) throw new BoundaryViolationException ("No se le puede pedir el previo al primero");
		else return n.getAnterior();
		}
	
	/**
	 * Devuelve la posición siguiente a cierta posicion "p" de la lista
	 * @param p Posición a obtener su elemento siguiente.
	 * @return Posición siguiente a "p"
	 * @throws InvalidPositionException si el posición pasada por parámetro es inválida o la lista está vacía.
	 * @throws BoundaryViolationException  cuando se exceden los limites de la estructura
	 */
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> n= checkPosition(p);
		if (n==trailer.getAnterior()) throw new BoundaryViolationException("No se le puede pedir el siguiente al ultimo");
		else return n.getSiguiente();
		}
	
	/**
	 * Agrega un nuevo primer elemento a la lista.
	 * @param elem Elemento a insertar al principio de la lista.
	 */
	public void addFirst(E elem)
	{ DNodo <E >nuevoPri= new DNodo<E> (null,null,elem);
	  header.getSiguiente().setAnterior(nuevoPri);
	  nuevoPri.setSiguiente(header.getSiguiente());
	  nuevoPri.setAnterior(header);
	  header.setSiguiente(nuevoPri);
	tamaño++;
	
	}
	
	/**
	 * Agrega un nuevo elemento como ultimo elemento a la lista
	 * @param elem Elemento a insertar al final de la lista.
	 */
	public void addLast(E elem)
	{DNodo <E> nuevoUlt= new DNodo<E> (null,null,elem);
	nuevoUlt.setSiguiente(trailer);
	nuevoUlt.setAnterior(trailer.getAnterior());
	trailer.setAnterior(nuevoUlt);
	nuevoUlt.getAnterior().setSiguiente(nuevoUlt);
	tamaño++;
	}
	
	/**
	 * Agrega un nuevo elemento antes de cierta posicion
	 * @param p Posición en cuya posición anterior se insertará el elemento pasado por parámetro. 
	 * @param elem Elemento a insertar antes de la posición pasada como parámetro.
	 */
	public void addBefore(Position<E> p, E elem) throws InvalidPositionException
	{DNodo<E> n=checkPosition(p);
	 DNodo <E> nuevo= new DNodo<E> (null,null,elem);
	 nuevo.setSiguiente(n);
	 n.getAnterior().setSiguiente(nuevo);
	 nuevo.setAnterior(n.getAnterior());
	 n.setAnterior(nuevo);
	 tamaño++;
	}
	
	/**
	 * Agrega un nuevo elemento despues de cierta posicion
	 * @param p Posición en cuya posición siguiente se insertará el elemento pasado por parámetro.
	 * @param elem Elemento a insertar luego de la posición pasada como parámetro.
	 */
	public void addAfter(Position<E> p, E elem) throws InvalidPositionException
	{DNodo<E> n=checkPosition(p);
	 DNodo <E> nuevo= new DNodo<E> (null,null,elem);
	 nuevo.setSiguiente(n.getSiguiente());
	 n.getSiguiente().setAnterior(nuevo);
	 n.setSiguiente(nuevo);
	 nuevo.setAnterior(n);
	 tamaño++;
	}
	
	/**
	 * Reemplaza el elemento de cierta posicion por otro y devuelve el que se encontraba anteriormente
	 * @param p Posición a establecer el elemento pasado por parámetro.
	 * @param elem . Elemento a establecer en la posición pasada por parámetro.
	 * @retu3rn el elemento original
	 */
	public E set(Position<E>p, E elem) throws InvalidPositionException
	{if (tamaño==0) throw new InvalidPositionException ("lista vacia");
		else {DNodo<E> n=checkPosition(p);
			  E aRetornar= n.element();
			  n.setElemento(elem);
			  return aRetornar;
			 }
	}
	
	/**
	 * Elimina cierta posicion
	 * @param p Posición del elemento a eliminar.
	 * @throws InvalidPositionException si el posición pasada por parámetro es inválida o la lista está vacía.
	 * @return el elemento original de la posicion eliminada
	 */
	public E remove(Position<E>p) throws InvalidPositionException
	{if (tamaño==0) throw new InvalidPositionException ("lista vacia");
		else {DNodo<E> n=checkPosition(p);
			  E aRetornar= n.element();
			  n.getAnterior().setSiguiente(n.getSiguiente());
			  n.getSiguiente().setAnterior(n.getAnterior());
			  n.setSiguiente(null);
			  n.setAnterior(null);
			  tamaño--;
			  return aRetornar;
			 }
	}
	
	/**
	 * Devuelve el tamaño de la lista
	 * @return cantidad de elementos de la lista
	 */
	public int size()
	{return tamaño;}
	
	/**
	 * Consulta si la lista esta vacía
	 * @return Verdadero si la lista esta vacía, falso en caso contrario
	 */
	public boolean isEmpty()
	{return tamaño==0;}
	
	/**
	 * Chequea si cierta posicion es valida	
	 * @param p. Convierte a la posicion en nodo
	 * @return Retorna el nuevo nodo
	 * @throws InvalidPositionException si el posición pasada por parámetro es inválida.
	 */
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException
	{try
		{ if(p==null || p==header || p==trailer) throw new InvalidPositionException("Posicion nula");
		 return (DNodo<E>) p;
		}
	catch (ClassCastException e)
		{throw new InvalidPositionException("no es posible");}
	}
	
	/**
	 * Devuelve un iterador con las posiciones de los elementos de la lista.
	 * @return Retorna un iterador.
	 */
	public Iterator<E> iterator()
	{ return new ElementIterator<E> (this);}
	
	/**
	 * Devuelve un iterador con las posiciones de los elementos de la lista.
	 * @return Retorna la nueva lista de posiciones.
	 */
	public Iterable<Position<E>> positions()
	{PositionList<Position<E>> ret = new ListaDoblementeEnlazada<Position<E>> ();
		try {
			
			{if(!(tamaño==0))
			{Position<E> c= header.getSiguiente();
			 while(c!=null)
			 { ret.addLast(c);
		 		if(c!=trailer.getAnterior()) 
		 		    c=next(c);
		 		else c=null;
		 		
		 		}
			}
			
		}
	
			}
		catch ( BoundaryViolationException | InvalidPositionException e)
	    {System.out.println(e.getMessage());}
	 return ret;
	}
	
	
	
	
}
	