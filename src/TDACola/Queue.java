package TDACola;

/**
 * Interface Queue
 * @author Cátedra de Estructuras de Datos, Departamento de Cs. e Ing. de la Computación, UNS.
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
	 * @throws EmptyQueueException Lanza esta excepción si la cola stá vacía
	 */
	public E front() throws EmptyQueueException;
	
	/**
	 * Retorna y elimina el elemento del frente de la cola.
	 * @return Retorna elemento del frente
	 * @throws EmptyQueueException Lanza esta excepción si la pila esta vacía.
	 */
	public E dequeue() throws EmptyQueueException;
	
	/**
	 * Consulta el tamaño de la cola.
	 * @return Retorna la cantidad de elementos de la cola.
	 * 
	 */
	public int size();
	
	/**
	 * Consulta si la cola está vacía
	 * @return Verdadero si la cola está vacía, falso en caso contrario
	 */
	public boolean isEmpty();
}
