package TDAMapeo;

import TDALista.*;
import Entrada.*;
import ClasesCompartidas.*;

/**
 * Clase MapeoHashCerrado que extiende a la interfaz Map.
 * @author Antonela Diomedi, Katherina Vilca y Mariana Hernández.
 *
 * @param <K> Tipo genérico.
 * @param <V> Tipo genérico.
 */
public class MapeoHashCerrado<K,V> implements Map<K,V> {
	
	protected Entrada<K,V>[] mapeo;
	protected int cant;
	final float factor = 0.5f;
	final Entrada<K,V> disponible = new Entrada<K,V> (null, null);
	
	/**
	 * Constructor de la calse MapeoConHashCerrado.
	 */
	public MapeoHashCerrado()
	{
		mapeo = (Entrada<K,V>[]) new Entrada[13];
		cant = 0;
	}
	
	/**
	 * Consulta el número de entradas del mapeo.
	 * @return Número de entradas del mapeo.
	 */
	public int size() {
		return cant;
	}

	/**
	 * Consulta si el mapeo está vacío.
	 * @return Verdadero si el mapeo está vacío, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return cant == 0;
	}

	/**
	 * Devuelve el valor de hash clave.
	 * @param k
	 * @return el valor de hash de la clave k.
	 */
	private int hash(K k)
	{
		return (Math.abs(k.hashCode())%mapeo.length);
	}
	
	/**
	 * Busca una entrada con clave igual a una clave dada y devuelve el valor asociado, si no existe retorna nulo.
	 * @param key Clave a buscar.
	 * @return Valor de la entrada encontrada.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public V get(K k) throws InvalidKeyException {
		if(k == null) throw new InvalidKeyException("Clave nula");
		int h = hash(k);
		while((mapeo[h] != null) && (mapeo[h].getKey() != k))
			h = (h+1)%mapeo.length;
		V toRet=null;
		if(mapeo[h]!=null && mapeo[h].getKey()==k)
			toRet=mapeo[h].getValue();
		return toRet;
	}

	/**
	 * Si el mapeo no tiene una entrada con clave key, inserta una entrada con clave key y valor value en el mapeo y devuelve null. 
	 * Si el mapeo ya tiene una entrada con clave key, entonces remplaza su valor y retorna el viejo valor.
	 * @param key Clave de la entrada a crear.
	 * @param value Valor de la entrada a crear. 
	 * @return Valor de la vieja entrada.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public V put(K k, V v) throws InvalidKeyException {
		if(k==null) throw new InvalidKeyException("Clave nula");
		if(((float) cant/mapeo.length) >= factor) rehash();
		
		int h = hash(k);
		int posAInsertar=0;
		while((mapeo[h] != null) && (mapeo[h].getKey() != k))
		{
			if(posAInsertar == 0 && mapeo[h] == disponible)
				posAInsertar = h; //guardo la primer pos disponible
			h = (h+1)%mapeo.length;
		}
		V toRet = null;
		posAInsertar=h;
		if((mapeo[h] != null) && (mapeo[h].getKey() == k))
		{
			posAInsertar=h; 
			toRet=mapeo[posAInsertar].getValue();
			cant--;
		}
		
		mapeo[posAInsertar] = new Entrada<K,V>(k,v);
		cant++;
		
		return toRet;
	}

	/**
	 * Duplica el tamaño del arreglo con un valor primo.
	 */
	private void rehash()
	{
		Entrada<K,V>[] mapeoAux = mapeo;
		mapeo = (Entrada<K,V> []) new Entrada[siguientePrimo(2* mapeoAux.length)];
		for (int i =0; i<mapeoAux.length; i++)
		{
			if((mapeoAux[i] != null) && (mapeoAux[i] != disponible))
			{
				int h = hash(mapeoAux[i].getKey());
				while(mapeo[h] != null)
					h = (h+1)%mapeo.length;
				mapeo[h] = mapeoAux[i];
			}
		}
	}
	
	/**
	 * Devuelve el siguiente número primo de n.
	 * @param n
	 * @return el siguente primo de n.
	 */
	private int siguientePrimo(int n)
	{
		if(esPrimo(n))
			return (n);
		else
			return siguientePrimo(n+1);
	}
	
	/**
	 * Consulta si el parámetro n es primo.
	 * @param n
	 * @return true si y solo si n es primo.
	 */
	private boolean esPrimo(int n)
	{
		boolean divisible=false;
		for(int i =2; (i<=Math.sqrt(n)) && (!divisible); i++)
			if((n%i) == 0)
				divisible=true;
		return !divisible;
	}
	
	/**
	 * Remueve la entrada con la clave dada en el mapeo y devuelve su valor, o nulo si no fue encontrada.
	 * @param e Entrada a remover.
	 * @return Valor de la entrada removida.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public V remove(K k) throws InvalidKeyException {
		if(k== null) throw new InvalidKeyException("Clave nula");
		int h = hash(k);
		while((mapeo[h] != null) && (mapeo[h].getKey() != k))
			h = (h+1)%mapeo.length;
		V toRet = null;
		if(mapeo[h]!=null && mapeo[h].getKey() == k)
		{
			toRet= mapeo[h].getValue();
			mapeo[h]=disponible;
			cant--;
		}
		return toRet;
	}

	/**
	 * Retorna una colección iterable con todas los valores del mapeo.
	 * @return Colección iterable con todas los valores del mapeo.
	 */
	public Iterable<V> values() {
		PositionList<V> toRet = new TDALista.ListaDoblementeEnlazada<V>();
		for (int i =0; i<mapeo.length; i++)
		{
			if((mapeo[i] != null) && (mapeo[i] != disponible))
				toRet.addLast(mapeo[i].getValue());
		}
		return toRet;
	}
	
	/**
	 * Retorna una colección iterable con todas las claves del mapeo.
	 * @return Colección iterable con todas las claves del mapeo.
	 */
	public Iterable<K> keys() {
		PositionList<K> toRet = new TDALista.ListaDoblementeEnlazada<K>();
		for (int i =0; i<mapeo.length; i++)
		{
			if((mapeo[i] != null) && (mapeo[i] != disponible))
				toRet.addLast(mapeo[i].getKey());
		}
		return toRet;
	}

	/**
	 * Retorna una colección iterable con todas las entradas del mapeo.
	 * @return Colección iterable con todas las entradas del mapeo.
	 */
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> toRet = new TDALista.ListaDoblementeEnlazada<Entry<K,V>>();
		for (int i =0; i<mapeo.length; i++)
		{
			if((mapeo[i] != null) && (mapeo[i] != disponible))
				toRet.addLast(mapeo[i]);
		}
		return toRet;
	}
	

}
