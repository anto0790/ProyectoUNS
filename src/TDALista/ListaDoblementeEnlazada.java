package TDALista;
import java.util.Iterator;

import ClasesCompartidas.*;

/**
 * Clase ListaDoblementeEnlazada que implementa a la interfaz PositionList
 * @author Katherina Vilca y Mariana Hern�ndez
 *
 * @param <E> Tipo generico E
 */
	public class ListaDoblementeEnlazada <E> implements PositionList<E> {
		private DNodo <E> header;
		private DNodo <E> trailer;
		private int tama�o;

 	/**
 	 * Constructor de la lista
 	 */
	public ListaDoblementeEnlazada()
	{header= new DNodo <E>(null,null,null);
	trailer=new DNodo <E> (header,null,null);
	header.setSiguiente(trailer);
	tama�o=0;
	}
	
	/**
	 * Devuelve la posici�n del primer elemento de la lista. 
	 * @return Posici�n del primer elemento de la lista.
	 * @throws EmptyListException si la lista est� vac�a.
	 */
	public Position<E> first() throws EmptyListException {
	if (tama�o==0) throw new EmptyListException ("Lista vacia");
		else 
			return header.getSiguiente();
		}
	

	/**
	 * Devuelve la posici�n del �ltimo elemento de la lista. 
	 * @return Posici�n del �ltimo elemento de la lista.
	 * @throws EmptyListException si la lista est� vac�a.
	 */
	public Position<E> last() throws EmptyListException {
	if(tama�o==0)throw new EmptyListException ("Lista vacia");
	else
		return trailer.getAnterior();}
	
	
	/**
	 * Devuelve la posici�n previa a cierta posicion "p" de la lista
	 * @param p Posici�n a obtener su elemento anterior.
	 * @return Posici�n previa a p .
	 * @throws InvalidPositionException si la lista est� vac�a.
	 * @throws BoundaryViolationException cuando se exceden los limites de la estructura
	 * 
	 */
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> n =checkPosition(p);
		if (n==header.getSiguiente()) throw new BoundaryViolationException ("No se le puede pedir el previo al primero");
		else return n.getAnterior();
		}
	
	/**
	 * Devuelve la posici�n siguiente a cierta posicion "p" de la lista
	 * @param p Posici�n a obtener su elemento siguiente.
	 * @return Posici�n siguiente a "p"
	 * @throws InvalidPositionException si el posici�n pasada por par�metro es inv�lida o la lista est� vac�a.
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
	tama�o++;
	
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
	tama�o++;
	}
	
	/**
	 * Agrega un nuevo elemento antes de cierta posicion
	 * @param p Posici�n en cuya posici�n anterior se insertar� el elemento pasado por par�metro. 
	 * @param elem Elemento a insertar antes de la posici�n pasada como par�metro.
	 */
	public void addBefore(Position<E> p, E elem) throws InvalidPositionException
	{DNodo<E> n=checkPosition(p);
	 DNodo <E> nuevo= new DNodo<E> (null,null,elem);
	 nuevo.setSiguiente(n);
	 n.getAnterior().setSiguiente(nuevo);
	 nuevo.setAnterior(n.getAnterior());
	 n.setAnterior(nuevo);
	 tama�o++;
	}
	
	/**
	 * Agrega un nuevo elemento despues de cierta posicion
	 * @param p Posici�n en cuya posici�n siguiente se insertar� el elemento pasado por par�metro.
	 * @param elem Elemento a insertar luego de la posici�n pasada como par�metro.
	 */
	public void addAfter(Position<E> p, E elem) throws InvalidPositionException
	{DNodo<E> n=checkPosition(p);
	 DNodo <E> nuevo= new DNodo<E> (null,null,elem);
	 nuevo.setSiguiente(n.getSiguiente());
	 n.getSiguiente().setAnterior(nuevo);
	 n.setSiguiente(nuevo);
	 nuevo.setAnterior(n);
	 tama�o++;
	}
	
	/**
	 * Reemplaza el elemento de cierta posicion por otro y devuelve el que se encontraba anteriormente
	 * @param p Posici�n a establecer el elemento pasado por par�metro.
	 * @param elem . Elemento a establecer en la posici�n pasada por par�metro.
	 * @retu3rn el elemento original
	 */
	public E set(Position<E>p, E elem) throws InvalidPositionException
	{if (tama�o==0) throw new InvalidPositionException ("lista vacia");
		else {DNodo<E> n=checkPosition(p);
			  E aRetornar= n.element();
			  n.setElemento(elem);
			  return aRetornar;
			 }
	}
	
	/**
	 * Elimina cierta posicion
	 * @param p Posici�n del elemento a eliminar.
	 * @throws InvalidPositionException si el posici�n pasada por par�metro es inv�lida o la lista est� vac�a.
	 * @return el elemento original de la posicion eliminada
	 */
	public E remove(Position<E>p) throws InvalidPositionException
	{if (tama�o==0) throw new InvalidPositionException ("lista vacia");
		else {DNodo<E> n=checkPosition(p);
			  E aRetornar= n.element();
			  n.getAnterior().setSiguiente(n.getSiguiente());
			  n.getSiguiente().setAnterior(n.getAnterior());
			  n.setSiguiente(null);
			  n.setAnterior(null);
			  tama�o--;
			  return aRetornar;
			 }
	}
	
	/**
	 * Devuelve el tama�o de la lista
	 * @return cantidad de elementos de la lista
	 */
	public int size()
	{return tama�o;}
	
	/**
	 * Consulta si la lista esta vac�a
	 * @return Verdadero si la lista esta vac�a, falso en caso contrario
	 */
	public boolean isEmpty()
	{return tama�o==0;}
	
	/**
	 * Chequea si cierta posicion es valida	
	 * @param p. Convierte a la posicion en nodo
	 * @return Retorna el nuevo nodo
	 * @throws InvalidPositionException si el posici�n pasada por par�metro es inv�lida.
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
			
			{if(!(tama�o==0))
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
	