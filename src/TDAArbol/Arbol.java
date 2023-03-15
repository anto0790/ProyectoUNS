package TDAArbol;

import java.util.Iterator;

import ClasesCompartidas.*;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;
import TDALista.EmptyListException;

/**
 * Clase �rbol que extiende a la interfaz Tree.
 * @author Katherina Vilca y Mariana Hern�ndez.
 *
 * @param <E> Tipo generico.
 */
public class Arbol<E> implements Tree<E> {
	private int size;
	private Position<E> root;
	
	/**
	 * Constructor de la clase Arbol
	 */
	public Arbol() {
		size = 0;
		root = null;
	}
	
	/**
	 * Retorna la cantidad de elementos del arbol
	 * @return Retorna la cantidad de elementos
	 */
	public int size() {
		return size;
	}

	/**
	 * Consulta si la pila est� vac�a
	 * @return Verdadero si la pila est� vac�a, Falso en caso contrario
	 */
	 
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Devuelve un iterador de todos los elementos de la lista.
	 * @return Retorna un iterador de todos los elementos de la lista.
	 */
	public Iterator<E> iterator() {
		PositionList<E> ite = new ListaDoblementeEnlazada<E>();
		for (Position<E> pos : positions())
			ite.addLast(pos.element());
		return ite.iterator();
	}

	/**
	 * Devuelve una colecci�n iterable de posiciones.
	 * @return Retorna colecci�n iterable de posiciones.
	 */
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> iteP = new ListaDoblementeEnlazada<Position<E>>();
		if (!isEmpty()) {
			ANodo<E> nodo = (ANodo<E>) root	;
			preOrden(nodo , iteP);
		}
		
		return iteP;
	}

	/**
	 * M�todo privado que recorre en preorden el �rbol que a su paso va guardando las posiciones visitadas. 
	 * @param nodo . Nodo visitado.
	 * @param iteP. Coleccion iterable a llenar
	 */
	private void preOrden(ANodo<E> nodo, PositionList<Position<E>> iteP) {
		iteP.addLast(nodo);
		for (ANodo<E> pos : nodo.getChildren())
			preOrden(pos,iteP);
		
	}


	/**
	 * Devuelve el elemento ubicado en determinada posici�n y lo reemplaza por el elemento pasado por parametro.
	 * @param v Posici�n de un nodo.
	 * @param e Elemento a reemplazar en la posici�n pasada por parametro.
	 * @return Retorna el elemento reemplazado.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida.
	 */
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		ANodo<E> nodo = checkPosition(v);
		E toRet = nodo.element();
		nodo.setRot(e);
		
		return toRet;
	}

	/**
	 * Devuelve la posici�n de la ra�z del �rbol.
	 * @return Retorna la posici�n de la ra�z del �rbol.
	 * @throws EmptyTreeException si el �rbol esta vac�o.
	 */
	public Position<E> root() throws EmptyTreeException {
		if (isEmpty())
			throw new EmptyTreeException("");
		return root;
	}

	/**
	 * Devuelve la posici�n del nodo padre del nodo correspondiente a determinada posici�n
	 * @param v posici�n de un nodo.
	 * @return Retorna la posici�n del nodo padre del nodo correspondiente a la posici�n pasada por parametro.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida.
	 * @throws BoundaryViolationException si la posici�n pasada por parametro corresponde a la ra�z del �rbol.
	 */
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		ANodo<E> nodo = checkPosition(v);
		if (nodo == root)
			throw new BoundaryViolationException("");
		
		return nodo.getParent();
	}

	/**
	 * Devuelve una colecci�n iterable de los hijos del nodo correspondiente a una posici�n determinada.
	 * @param v posici�n de un nodo.
	 * @return Retorna una colecci�n iterable de los hijos del nodo correspondiente a la posici�n pasada por parametro.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida.
	 */
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		ANodo<E> nodo = checkPosition(v);
		PositionList<Position<E>> toRet = new ListaDoblementeEnlazada<Position<E>>();
		for (Position<E> pos : nodo.getChildren())
			toRet.addLast(pos);
		return toRet;
	}

	/**
	 * Consulta si una posici�n determinada corresponde a un nodo interno.
	 * @param v posici�n de un nodo.
	 * @return Retorna verdadero si la posici�n pasada por parametro corresponde a un nodo interno, falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida.
	 */
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		ANodo<E> nodo = checkPosition(v);
		return (nodo.getChildren().size() != 0);
	}

	/**
	 * Consulta si una posici�n determinada corresponde a un nodo externo.
	 * @param v posici�n de un nodo.
	 * @return Retorna verdadero si la posici�n pasada por parametro corresponde a un nodo externo, falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida.
	 */
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		ANodo<E> nodo = checkPosition(v);
		return (nodo.getChildren().size() == 0);
	}

	/**
	 * Consulta si una posici�n determinada corresponde a la ra�z del �rbol.
	 * @param v posici�n de un nodo.
	 * @return Retorna verdadero, si la posici�n pasada por parametro corresponde a la ra�z del �rbol,falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida.
	 */
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		ANodo<E> nodo = checkPosition(v);
		return (nodo == root);
	}

	/**
	 * Crea un nodo con r�tulo e como ra�z del �rbol.
	 * @param e r�tulo que se asignar� a la ra�z del �rbol.
	 * @throws InvalidOperationException si el �rbol ya tiene un nodo ra�z.
	 */
	public void createRoot(E e) throws InvalidOperationException {
		if (root != null)
			throw new InvalidOperationException("");
		root = new ANodo<E>(e);
		size = 1;
	}

	/**
	 * Agrega un nodo con r�tulo e como primer hijo de un nodo determinado.
	 * @param e r�tulo del nuevo nodo.
	 * @param p posici�n del nodo padre.
	 * @return Retorna la posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida o el �rbol est� vac�o.
	 */
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("");
		ANodo<E> nodo = checkPosition(p);
		ANodo<E> nuevo = new ANodo<E>(e);
		nuevo.setParent(nodo);
		nodo.getChildren().addFirst(nuevo);
		size++;
		return nuevo;
	}

	/**
	 * Agrega un nodo con r�tulo e como �ltimo hijo de un nodo determinado.
	 * @param e r�tulo del nuevo nodo.
	 * @param p posici�n del nodo padre.
	 * @return Retorna la posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida o el �rbol est� vac�o.
	 */
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("");
		ANodo<E> nodo = checkPosition(p);
		ANodo<E> nuevo = new ANodo<E>(e);
		nuevo.setParent(nodo);
		nodo.getChildren().addLast(nuevo);
		size++;
		return nuevo;
	}

	/**
	 * Agrega un nodo con r�tulo e como hijo de un nodo padre determinado. El nuevo nodo se agregar� delante de otro nodo tambi�n determinado.
	 * @param e R�tulo del nuevo nodo.
	 * @param p posici�n del nodo padre.
	 * @param rb posici�n del nodo que ser� el hermano derecho del nuevo nodo.
	 * @return Retorna la posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida, o el �rbol est� vac�o, o si la posici�n rb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException{
		if (this.isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		
		ANodo<E> padreDeNN = checkPosition(p);
		ANodo<E> herDer = checkPosition(rb);
		ANodo<E> nodoNuevo = new ANodo<E>( e);
		nodoNuevo.setParent(padreDeNN);
		
		if (herDer.getParent() != padreDeNN)
			throw new InvalidPositionException("El nodo P elegido no es padre del nodo RB elegido");
		try {
			
			
			PositionList<ANodo<E>> hijosDePadre = padreDeNN.getChildren();
			Position<ANodo<E>> posHij = hijosDePadre.first();
			
			boolean encontre = false;
			
			while (posHij != null && !encontre) {
				if (herDer != posHij.element())
					posHij = (posHij != hijosDePadre.last() ? hijosDePadre.next(posHij) : null );
				else
					encontre = true;
				
				if ( encontre ) {
					hijosDePadre.addBefore(posHij , nodoNuevo );
					size++;
				}
			}
			
		} catch (EmptyListException e1) {
			e1.printStackTrace();
		} catch (ClasesCompartidas.InvalidPositionException e1) {
			e1.printStackTrace();
		} catch (ClasesCompartidas.BoundaryViolationException e1) {
			e1.printStackTrace();
		}
		
		return nodoNuevo;		
	}

	/**
	 * Agrega un nodo con r�tulo e como hijo de un nodo padre determinado. El nuevo nodo se agregar� a continuaci�n de otro nodo tambi�n determinado.
	 * @param e R�tulo del nuevo nodo.
	 * @param p posici�n del nodo padre.
	 * @param lb posici�n del nodo que ser� el hermano izquierdo del nuevo nodo.
	 * @return Retorna la posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida, o el �rbol est� vac�o, o si la posici�n lb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		if (this.isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		
		ANodo<E> padreDeNN = checkPosition(p);
		ANodo<E> herIz = checkPosition(lb);
		ANodo<E> nodoNuevo = new ANodo<E>( e);
		nodoNuevo.setParent(padreDeNN);
		
		if (herIz.getParent() != padreDeNN)
			throw new InvalidPositionException("El nodo P elegido no es padre del nodo LB elegido");
		
		try {
			
			PositionList<ANodo<E>> hijosDePadre = padreDeNN.getChildren();
			Position<ANodo<E>> posHij = hijosDePadre.first();
			
			boolean encontre = false;
			
			while (posHij != null && !encontre) {
			
				if (herIz != posHij.element())
					posHij = (posHij != hijosDePadre.last() ? hijosDePadre.next(posHij) : null );
				else
					encontre = true;
				
				if ( encontre ) {
					hijosDePadre.addAfter(posHij , nodoNuevo );
					size++;
				}
			}
			
		} catch (EmptyListException e1) {
			e1.printStackTrace();
		} catch (ClasesCompartidas.InvalidPositionException e1) {
			e1.printStackTrace();
		} catch (ClasesCompartidas.BoundaryViolationException e1) {
			e1.printStackTrace();
		}
		return nodoNuevo;		
	}
	
	/**
	 * Elimina el nodo referenciado por una posici�n determinada, si se trata de un nodo externo. 
	 * @param p posici�n del nodo a eliminar.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida o no corresponde a un nodo externo, o si el �rbol est� vac�o.
	 */
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		if (this.isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		
		ANodo<E> nodAEliminar = checkPosition(p);
		
		if (!isExternal(p))
			throw new InvalidPositionException("El nodo P es interno");
		
		PositionList<ANodo<E>> posListHij = nodAEliminar.getChildren();
		
		if (nodAEliminar != root)
			posListHij = ((ANodo<E>) nodAEliminar.getParent()).getChildren();
		
		Iterable<Position<ANodo<E>>> hijIte = posListHij.positions();
		Iterator<Position<ANodo<E>>> iterador = hijIte.iterator();
		
		boolean encontre = false;
		try {
			
			while ( iterador.hasNext() && !encontre ) {
				Position<ANodo<E>> prim = iterador.next();
				
				if (prim.element() == nodAEliminar) {
					encontre = true;
					posListHij.remove(prim);
					
				}			
				
			}
			nodAEliminar.setParent(null);		
			size--;
		} catch (ClasesCompartidas.InvalidPositionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina el nodo referenciado por una posici�n determinada, si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazar�n en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la ra�z del �rbol, �nicamente podr� ser eliminado si tiene un solo hijo, el cual lo reemplazar�.
	 * @param p posici�n del nodo a eliminar.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es inv�lida o no corresponde a un nodo interno o corresponde a la ra�z (con m�s de un hijo), o si el �rbol est� vac�o.
	 */
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		
		if (this.isEmpty())
			throw new InvalidPositionException("Arbol vacio");	
		
		ANodo<E> nodAEliminar = checkPosition(p);
		
		if (nodAEliminar.getChildren().size() > 1 && nodAEliminar == root)
			throw new InvalidPositionException("");
		
		if (!isInternal(p))
			throw new InvalidPositionException("El nodo P es externo");
		try {
			
				
			
			
			
			
			
			if (nodAEliminar == root) {
				
				if (nodAEliminar.getChildren().size() == 1) {
					root = nodAEliminar.getChildren().first().element();
					size--;
				} 
				else {
					if (nodAEliminar.getChildren().size() == 0) {
						root = null;
						size--;
					} else
						throw new InvalidPositionException("El nodo raiz tiene mas de un hijo");
				}				
			 } 
			else {						
				
				ANodo<E> nuevoPadre = (ANodo<E>) nodAEliminar.getParent();
				PositionList<ANodo<E>> hijosNAE = nodAEliminar.getChildren();
				
				PositionList<ANodo<E>> padreNAE = nuevoPadre.getChildren();
				Position<ANodo<E>> primero = padreNAE.first();
		
				while( primero.element() != nodAEliminar) {
					primero = padreNAE.next(primero);
				}
			
				if (!padreNAE.isEmpty()) {
					
					while (!hijosNAE.isEmpty()) {
						Position<ANodo<E>> hijAInsert = hijosNAE.first();
						padreNAE.addBefore(primero, hijAInsert.element());
						
						ANodo<E> insertado = padreNAE.prev(primero).element();
						insertado.setParent(nuevoPadre);
						hijosNAE.remove(hijAInsert);
					}
				}
				padreNAE.remove(primero);
				size--;
			}
			
		} catch (ClasesCompartidas.BoundaryViolationException e){
			e.printStackTrace();
		} catch (TDALista.EmptyListException e) {
			e.printStackTrace();
		} catch (ClasesCompartidas.InvalidPositionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina el nodo referenciado por una posici�n determinada. Si se tratara de un nodo interno, los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la ra�z del �rbol, �nicamente podr� ser eliminado si tiene un solo hijo, el cual lo reemplazar�.
	 * @param p posici�n del nodo a eliminar.
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida o corresponde a la ra�z (con m�s de un hijo), o el �rbol est� vac�o.
	 */
	public void removeNode(Position<E> p) throws InvalidPositionException {	
			if (isInternal(p)) {
				removeInternalNode(p);
			}
			else {
				removeExternalNode(p);
			}				
		}
	

	/**
	 * M�todo privado que chequea que la posici�n no sea nula y si no lo es, la convierte en nodo.
	 * @param p .Posicion a chequear.
	 * @return Retorna la conversi�n a nodo
	 * @throws InvalidPositionException si la posici�n pasada por parametro es invalida.
	 */
	private ANodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		if ( p == null)
			throw new InvalidPositionException("");
		try {
			return (ANodo<E>) p;
		} catch ( ClassCastException e) {
			throw new InvalidPositionException("");
		}
	
	}

	
}
