package TDAPila;

/**
 * Clase PilaAreglo que extiende a la interfaz Stack.
 * @author Antonela Diomedi, Katherina Vilca y Mariana Hernández.
 *
 * @param <E>
 */
public class PilaArreglo <E> implements Stack <E>
{ private E [] arreglo ;
  private int tamaño;

  /**
   * Constructor de la clase PilaArreglo.
   */
  public PilaArreglo ()
  {arreglo = (E []) new Object [100];
  tamaño= 0;}
  
  /**
	 * Inserta un elemento en el tope de la pila.
	 * @param element Elemento a insertar.
	 */
   public void push (E elem)
  { if (tamaño== arreglo.length) 
  { agrandarArreglo();}
  	arreglo[tamaño]= elem;
  	tamaño++;
  }
   
   /**
    * Duplica el tamaño del arreglo.
    */
   private void agrandarArreglo()
   {
	   E [] nuevoA= (E[]) new Object [arreglo.length*2];
	   for (int i=0; i<arreglo.length; i++ )
	   {
		   nuevoA[i]= arreglo[i];
	   }
	   arreglo=nuevoA;
   }
   
   /**
	 * Remueve el elemento que se encuentra en el tope de la pila.
	 * @return Elemento removido.
	 * @throws EmptyStackException si la pila está vacía. 
	 */
   public E pop() throws EmptyStackException
   { if (tamaño==0) throw new EmptyStackException  ("pila vacia");
   else {E elem= arreglo[tamaño-1];
   		 arreglo[tamaño-1]=null;
   		 tamaño--;
        
         return elem;
         }
	}
   
   /**
	 * Examina el elemento que se encuentra en el tope de la pila.
	 * @return Elemento que se encuentra en el tope de la pila.
	 * @throws EmptyStackException si la pila está vacía. 
	 */
   public E top() throws EmptyStackException
   {if (tamaño==0) throw new EmptyStackException("pila vacia");
   else
        return arreglo[tamaño-1];  
   }
  
	/**
	 * Consulta si la pila está vacía.
	 * @return Verdadero si la pila está vacía, falso en caso contrario.
	 */
   public boolean isEmpty() 
  {return tamaño==0;}
  
   /**
	 * Consulta la cantidad de elementos de la pila.
	 * @return Cantidad de elementos de la pila.
	 */
   public int size()
  { return tamaño;}
}
