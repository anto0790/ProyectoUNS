package Entrada;

/**
 * Clase Entrada que implementa la interfaz Entry
 * @author Katherina Vilca y Mariana Hernández.
 *
 * @param <K> 	Tipo de dato de la clave
 * @param <V>	Tipo de dato del valor 
 */

public class Entrada<K,V> implements Entry<K,V> {
	
	private K k; 
	private V v;
	
	/**
	 * Constructor de la clase Entrada
	 * @param k	Clave
	 * @param v	Valor
	 */
	
	public Entrada(K k, V v) {
		this.k = k;
		this.v = v;
	}

	/**
	 * Retorna la clave de la entrada
	 * @return clave
	 */
	public K getKey() {
		return k;
	}

	/**
	 * Retorna el valor de la entrada
	 * @return valor
	 */
	public V getValue() {
		return v;
	}
	
	/**
	 * Establece una clave a una entrada
	 * @param k Clave a establecer
	 */
	
	public void setKey(K k) {
		this.k = k;
	}
	
	/**
	 * Establece un valor a una entrada
	 * @param v Valor a establecer
	 */
	
	public void setValue(V v) {
		this.v = v;
	}

}
