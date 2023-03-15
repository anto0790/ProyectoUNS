package TDAPila;

/**
 * Clase PilaAreglo que extiende a la interfaz Stack.
 * @author Antonela Diomedi, Katherina Vilca y Mariana Hern�ndez.
 *
 * @param <E>
 */
public class PilaArreglo <E> implements Stack <E>
{ private E [] arreglo ;
  private int tama�o;

  /**
   * Constructor de la clase PilaArreglo.
   */
  public PilaArreglo ()
  {arreglo = (E []) new Object [100];
  tama�o= 0;}
  
  /**
	 * Inserta un elemento en el tope de la pila.
	 * @param element Elemento a insertar.
	 */
   public void push (E elem)
  { if (tama�o== arreglo.length) 
  { agrandarArreglo();}
  	arreglo[tama�o]= elem;
  	tama�o++;
  }
   
   /**
    * Duplica el tama�o del arreglo.
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
	 * @throws EmptyStackException si la pila est� vac�a. 
	 */
   public E pop() throws EmptyStackException
   { if (tama�o==0) throw new EmptyStackException  ("pila vacia");
   else {E elem= arreglo[tama�o-1];
   		 arreglo[tama�o-1]=null;
   		 tama�o--;
        
         return elem;
         }
	}
   
   /**
	 * Examina el elemento que se encuentra en el tope de la pila.
	 * @return Elemento que se encuentra en el tope de la pila.
	 * @throws EmptyStackException si la pila est� vac�a. 
	 */
   public E top() throws EmptyStackException
   {if (tama�o==0) throw new EmptyStackException("pila vacia");
   else
        return arreglo[tama�o-1];  
   }
  
	/**
	 * Consulta si la pila est� vac�a.
	 * @return Verdadero si la pila est� vac�a, falso en caso contrario.
	 */
   public boolean isEmpty() 
  {return tama�o==0;}
  
   /**
	 * Consulta la cantidad de elementos de la pila.
	 * @return Cantidad de elementos de la pila.
	 */
   public int size()
  { return tama�o;}
}
