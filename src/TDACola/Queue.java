package TDACola;

/**
 * Interface Queue
 * @author C�tedra de Estructuras de Datos, Departamento de Cs. e Ing. de la Computaci�n, UNS.
 */
	public interface Queue <E> {
	
	/**
	 * Agrega un elemento a cola
	 * @param elem. Elemento a agregar.
	 */
	public void enqueue(E elem);
	
	/**
	 * Retorna el elemento del frente de la cola.
	 * @return Retorna el elemento del frente.
	 * @throws EmptyQueueException Lanza esta excepci�n si la cola st� vac�a
	 */
	public E front() throws EmptyQueueException;
	
	/**
	 * Retorna y elimina el elemento del frente de la cola.
	 * @return Retorna elemento del frente
	 * @throws EmptyQueueException Lanza esta excepci�n si la pila esta vac�a.
	 */
	public E dequeue() throws EmptyQueueException;
	
	/**
	 * Consulta el tama�o de la cola.
	 * @return Retorna la cantidad de elementos de la cola.
	 * 
	 */
	public int size();
	
	/**
	 * Consulta si la cola est� vac�a
	 * @return Verdadero si la cola est� vac�a, falso en caso contrario
	 */
	public boolean isEmpty();
}
