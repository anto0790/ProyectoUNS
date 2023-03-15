package TDACola;
/**
 * Clase ColaConArregloCircular que implementa la interfaz Queue
 * @author Katherina Vilca y Mariana Hernández.
 *
 * @param <E> Tipo generico E
 */
public class ColaConArregloCircular <E> implements Queue <E>{
	private int f; 
	private int r;
	private E [] arreglo;
	private static final int max=100;
	
	/**
	 * Constructor de la clase
	 */
	public ColaConArregloCircular()
	{ arreglo = (E[]) new Object [max];
	  f=0;
	  r=0;
	}
	
	/**
	 * Agrega un elemento al final de la cola
	 * @param elem . Elemento a agregar
	 */
	public void enqueue(E elem)
	{ if (size()== arreglo.length-1)
	   {  E[] aux= copiar(f);
		  r=size();
		  f=0;
		  arreglo=aux;
	   }
	arreglo[r]=elem;
	r= (r+1) % arreglo.length;
		
	}
	
	/**
	 * Metodo auxiliar que se encarga de agrandar el arreglo.
	 * @param m . Valor numerico donde se ubica el primer elemento del arreglo.
	 * @return Nuevo arreglo.
	 */
	private E [] copiar(int m)
	{E[] aux= (E[]) new Object[arreglo.length*2];	  
	  for (int j = 0 ; j<size();j++)
	  { aux[j] = arreglo[m];
	    m=(m+1)% arreglo.length;
	  }
	  return aux;
	}

		
	
	/**
	 * Devuelve el primer elemento de la cola
	 * @return Retorna el elemento del frente.
	 * @throws EmptyQueueException se lanza esta excepcion cuando la cola esta vacía
	 */
	
	public E front() throws EmptyQueueException {
	if (isEmpty()) throw new EmptyQueueException ("cola vaciaF");
		else {return arreglo[f];
			 }
		}

	/**
	 * Elimina el primer elemento de la cola
	 * @return Retorna elemento del frente
	 * @throws EmptyQueueException se lanza esta excepcion cuando la cola esta vacía
	 */
	public E dequeue() throws EmptyQueueException {
	if (isEmpty()) throw new EmptyQueueException ("cola vaciaD");
		else {E aux= arreglo[f];
			  arreglo[f]=null;
			  
			  f=(f+1) % arreglo.length;
			  return aux;
			  }
		}

	/**
	 * Devuelve la cantidad de elementos de la cola
	 */
	public int size() {
		return (arreglo.length - f+r) % arreglo.length;	
	}
	
	/**
	 * Consulta si la cola esta vacía
	 */
	public boolean isEmpty() {
	return f==r;}
}
