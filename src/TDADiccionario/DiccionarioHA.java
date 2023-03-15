package TDADiccionario;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ClasesCompartidas.*;
import Entrada.*;
import TDALista.*;


/**
 * Clase DiccionarioHA que extiende a Dictionary.
 * @author Antonela Diomedi, Katherina Vilca y Mariana Hern�ndez.
 *
 * @param <K> Tipo gen�rico.
 * @param <V> Tipo gen�rico.
 */
public class DiccionarioHA <K,V> implements Dictionary<K,V> {

	private PositionList<Entrada<K,V>> [] arreglo;
	private int size;
	private static final int max=13;
	private static final float factorDeCarga = 0.9f;
	
	/**
	 * Constructor de la clase DiccionarioHA.
	 */
	public DiccionarioHA()	
	{ size=0;
	  arreglo= (PositionList<Entrada<K, V>>[]) new ListaDoblementeEnlazada [max];
	  
	  for(int i=0; i<arreglo.length ; i++)
		  arreglo[i]= new ListaDoblementeEnlazada<Entrada<K,V>>();
	
	}
	
	/**
	 * Consulta el n�mero de entradas del diccionario.
	 * @return N�mero de entradas del diccionario.
	 */
	public int size() {
		return size;
	}

	/**
	 * Consulta si el diccionario est� vac�o.
	 * @return Verdadero si el diccionario est� vac�o, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return size==0;
	}

	/**
	 * Busca una entrada con clave igual a una clave dada y la devuelve, si no existe retorna nulo.
	 * @param key Clave a buscar.
	 * @return Entrada encontrada.
	 * @throws InvalidKeyException si la clave pasada por par�metro es inv�lida.
	 */
	public Entry<K, V> find(K key) throws InvalidKeyException {
		
		Entry<K,V> ret=null;
		try {
			if(key==null) throw new InvalidKeyException("clave invalida");
			
		
				else {
						int codigoH= codigoHash(key,arreglo.length);
						PositionList<Entrada<K,V>> lista= arreglo[codigoH];
					
						 boolean encontre= false;
						  
						  Iterator<Entrada<K,V>> ite = lista.iterator();
							
							while (ite.hasNext() && !encontre) {
								
								Entrada<K,V> e = ite.next();
								
								if ( e.getKey().equals(key)) {
									encontre = true;
									ret = e;	}
									
							}	
				}
		}
		catch (NoSuchElementException e) {};
		return ret;
	}
	

	/**
	 * Retorna una colecci�n iterable que contiene todas las entradas con clave igual a una clave dada.
	 * @param key Clave de las entradas a buscar.
	 * @return Colecci�n iterable de las entradas encontradas.
	 * @throws InvalidKeyException si la clave pasada por par�metro es inv�lida.
	 */
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		
			PositionList<Entry<K,V>> iteE= new ListaDoblementeEnlazada<Entry<K,V>>();
			
			if(key==null) throw new InvalidKeyException("clave invalida");
		
			else {
				int codigoH= codigoHash(key,arreglo.length);
				PositionList<Entrada<K,V>> lista= arreglo[codigoH];
				
				for(Position<Entrada<K,V>> pos: lista.positions())
				{
					if(pos.element().getKey().equals(key))
						iteE.addLast(pos.element());
				}
			}
		 return iteE;
		}


	/**
	 * Inserta una entrada con una clave y un valor dado en el diccionario y retorna la entrada creada.
	 * @param key Clave de la entrada a crear.
	 * @return value Valor de la entrada a crear.
	 * @throws InvalidKeyException si la clave pasada por par�metro es inv�lida.
	 */
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {		
				if(key==null) throw new InvalidKeyException("Codigo no valido");
				else {
					  Entrada<K,V> ret= new Entrada<K,V> (key,value);
					  int codH = codigoHash(key,arreglo.length);
					  PositionList<Entrada<K,V>> lista= arreglo[codH];
					  lista.addLast(ret);
					  size++;
					  return ret;
				}				
	}

	/**
	 * Remueve una entrada dada en el diccionario y devuelve la entrada removida.
	 * @param e Entrada a remover.
	 * @return Entrada removida.
	 * @throws InvalidEntryException si la entrada no est� en el diccionario o es inv�lida.
	 */
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		
		try {
			  if(e==null) throw new InvalidEntryException("Codigo no valido");
			  else {
				  
				  int h = codigoHash(e.getKey(),arreglo.length);
				  PositionList<Entrada<K,V>> lista= arreglo[h];	  
				  Iterator<Position<Entrada<K,V>>> posiciones=lista.positions().iterator();
				  boolean encontre= false;
				  
					  while(posiciones.hasNext() && !encontre)
					  { 
						  Position<Entrada<K,V>> pos = posiciones.next();
						  
						  if(pos.element().getKey().equals(e.getKey()) && pos.element().getValue().equals(e.getValue()))
						  {
							  lista.remove(pos);
							  encontre=true;
							  size=size-1;
						  }
					  }
					  
					  if(!encontre) throw new InvalidEntryException("entrada invalida");
					}
			 
				}
		catch(NoSuchElementException | InvalidPositionException e1) {System.out.println(e1.getMessage());};
		 return e;	
	}

	/**
	 * Retorna una colecci�n iterable con todas las entradas en el diccionario.
	 * @return Colecci�n iterable de todas las entradas.
	 */
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> iteE= new ListaDoblementeEnlazada<Entry<K,V>>();
			
			for(int i=0; i<arreglo.length; i++)
			{	
				PositionList<Entrada<K,V>> lista= arreglo[i];
				
				for(Position<Entrada<K,V>> pos: lista.positions())
				{
					iteE.addLast(pos.element());
				}
			}
	 return iteE;
	}

	/**
	 * Dulica el tama�o del arreglo con un valor primo.
	 */
	private void reHash() {
		
		PositionList<Entrada<K,V>> [] arregloN =  (PositionList<Entrada<K, V>>[]) new ListaDoblementeEnlazada [siguienteP(arreglo.length*2)];
		
		for(int i=0; i<arregloN.length ; i++)
			  arregloN[i]= new ListaDoblementeEnlazada<Entrada<K,V>>();
		
		for(int i=0; i<arreglo.length ; i++)
		{
			PositionList<Entrada<K,V>> lista=arreglo[i];
			
				for(Position<Entrada<K,V>> pos: lista.positions())
				{
					K clave= pos.element().getKey();
					int h=codigoHash(clave,arregloN.length);
					arregloN[h].addLast(pos.element());
				}
		}
		arreglo=arregloN;
	}

	/**
	 * Consulta si el par�metro n es primo.
	 * @param n
	 * @return true si y solo si n es primo.
	 */
	private boolean esPrimo(int nro) {
		boolean esPrimo = true;
	    int cont=2;
	    
	    while(esPrimo && cont!=nro)
	    {
	    	if((nro % cont)== 0)
	    		esPrimo= false;
	    }
	    return esPrimo;
	}

	/**
	 * Devuelve el siguiente n�mero primo de n.
	 * @param n
	 * @return el siguente primo de n.
	 */
	private int siguienteP(int l) {
			int nuevoPrimo = l + 1;
			
			while ( !esPrimo(nuevoPrimo)&& ((size/nuevoPrimo)< factorDeCarga)){
				nuevoPrimo++;
			}
			
			return nuevoPrimo;
		}


	/**
	 * Devuelve el valor de hash clave.
	 * @param k
	 * @return el valor de hash de la clave k.
	 */
	private int codigoHash(K key, int l) {
		return (Math.abs(key.hashCode())% l);
	}
}
