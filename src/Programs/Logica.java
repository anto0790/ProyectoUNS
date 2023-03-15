package Programs;

import TDAArbol.*;
import TDACola.*;
import TDADiccionario.*;
import TDALista.*;
import TDAMapeo.*;
import TDAPila.*;
import ClasesCompartidas.*;
import Entrada.*;
import Excepciones.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;
/**
 * Clase de prueba de la GUI.
 * @author Hernández Mariana, Vilca Katherina y Antonella Diomedi.

 */
public class Logica {

private Arbol<Entrada<String,PositionList<String>>> arbol;
	
	/**
	 * Constructor de la clase ProgramaDePrueba
	 */
	public Logica() {
		
		arbol = new Arbol<Entrada<String,PositionList<String>>>(); 	
	}
		
	private boolean EsValido(String a) {
		 boolean valido=true;
	    try { 
	    BufferedReader br= new BufferedReader(new FileReader (a));
	    Stack<Character> pila=new PilaArreglo<Character>();
	    Queue<Character> cola=new ColaConArregloCircular<Character>();
	   
	    char caracter;
	    String linea=br.readLine();
	    
	    if(linea==null)
	    	valido=false;
	    else {
	    	while(linea!=null && valido==true) {	
	             for(int i=0; i<linea.length(); i++)
	                   cola.enqueue(linea.charAt(i));

	             while(!cola.isEmpty() && valido==true) 
	             {
	                      caracter=cola.dequeue();

	                       if(caracter=='<')
	                           pila.push(caracter);
	                     else
	                       
	                    	 if(caracter=='>') {
	                             
	                    		 if(!pila.isEmpty() && caracter != pila.top())
	                                       pila.pop();
	                          else
	                        
	                        	        valido=false;
	                                           } 
	            if(cola.size()==1)
	           if(cola.front()!='>')
	                valido=false;
	        
	           }
	        linea=br.readLine();
	        if(!pila.isEmpty())
	             valido=false;
	     }
	    br.close();
	    }
	}
	    catch (EmptyStackException |EmptyQueueException | IOException e) {
	    e.printStackTrace();}
	    
	return valido;
	  }
	
	public Tree<Entrada<String,PositionList<String>>> generarJerarquia(String A)throws ExcepcionRutaInvalida {	
			Arbol<Entrada<String, PositionList<String>>> T= new Arbol<Entrada<String,PositionList<String>>>();
		try {
			

			if(EsValido(A)) {
				FileReader fr=new FileReader(A);
				BufferedReader br=new BufferedReader(fr);
				String linea=br.readLine();
				String lineaQ="";
				Queue<String>colaArchivo=new ColaConArregloCircular<String>();
				
				if(linea!=null)
					linea=linea.replace("\\s", "");
				
				//Paso todo el archivo de la ruta A a la cola "colaArchivo".
				while(linea!=null) {
					colaArchivo.enqueue(linea);
					linea=br.readLine();
					if(linea!=null)
						linea=linea.replaceAll("\\s", "");
				}
				br.close();
				//Extraigo de la cola la linea "<Carpeta>"
				if(colaArchivo.front().equals("<carpeta>"))	
					colaArchivo.dequeue();
				
				//Extraigo de la cola la segunda linea y creo el nodo raíz.
				lineaQ=colaArchivo.dequeue();
				String nombre=extraerNom(lineaQ);
				
				PositionList<String>lista=new ListaDoblementeEnlazada<String>();
				Entrada<String,PositionList<String>>nodoRaiz=new Entrada<String,PositionList<String>>(nombre,lista);
				T.createRoot(nodoRaiz);
				Position<Entrada<String, PositionList<String>>>pos=T.root();
				
				if(!colaArchivo.front().equals("</carpeta>") && !colaArchivo.front().equals("<lista_sub_carpetas>") && !colaArchivo.front().equals("<carpeta>")) {
					lineaQ=colaArchivo.dequeue();
					while(!lineaQ.equals("</carpeta>") && !lineaQ.equals("<lista_sub_carpetas>") && !lineaQ.equals("<carpeta>")) {
						nombre=extraerNom(lineaQ);
						lista.addLast(nombre);
						lineaQ=colaArchivo.dequeue();	
					}
				}
				generarResto(T,colaArchivo,pos);
				}
			else
				throw new ExcepcionRutaInvalida("La gerarquía que intenta crear es vacía.");
			
				arbol=T;
		}
		catch( IOException | InvalidOperationException | EmptyTreeException | EmptyQueueException e) {
			System.out.println(e.getMessage()+"Error en GJ.");
		}
		return T;
	}
	
		private void  generarResto(Tree<Entrada<String,PositionList<String>>>T, Queue<String> colaA, Position<Entrada<String, PositionList<String>>>posPadre) {
			try {
				String linea,nombre;
				Position<Entrada<String,PositionList<String>>>posicion=null;
				PositionList<String>lista;
				
				while(!colaA.isEmpty()) {
					linea=colaA.dequeue();

					if(linea.equals("<lista_sub_carpetas>"))
						linea=colaA.dequeue();		
					
						
					if(linea.equals("<carpeta>")) {
						linea=colaA.dequeue();
						nombre=extraerNom(linea);
						
						//Creo nuevo nodo y seteo: key, value y lo agrego como hijo de "posPadre" de T.
						Entrada<String,PositionList<String>>nodo=new Entrada<String,PositionList<String>>(null,null);
						nodo.setKey(nombre);
						lista=new ListaDoblementeEnlazada<String>();
						nodo.setValue(lista);
						posicion=T.addLastChild(posPadre, nodo);
						
						linea=colaA.dequeue();
						
						if(linea.equals("<lista_sub_carpetas>")) 
							generarResto(T,colaA,posicion);
						else 
							if(!linea.equals("</carpeta>")) {
								while(!linea.equals("</carpeta>") && !linea.equals("<lista_sub_carpetas>")) {
									nombre=extraerNom(linea);
									lista.addLast(nombre);
									linea=colaA.dequeue();		
								}//fin del while
							if(linea.equals("<lista_sub_carpetas>"))
								generarResto(T,colaA,posicion);	
							else
								if(linea.equals("</carpeta>")) 
									if(!T.parent(posicion).equals(T.root())) {
									posicion=T.parent(posicion);
									generarResto(T,colaA,T.parent(posicion));
								}
							}
						}
				}	
			}
			catch(InvalidPositionException | EmptyQueueException | BoundaryViolationException | EmptyTreeException e) {
				System.out.println(e.getMessage()+", Error en gjAux");
			}
	
		}
	
	private String extraerNom(String st) {
		String nombre="";
		int i=0;
		
		while(st.charAt(i)!='>')
			i++;
		i++;
		while(st.charAt(i)!='<') {
			nombre+=st.charAt(i);
		i++;	
		}
		return nombre;
	}
	
	/**
	 * Metodo auxiliar que devuelve el String del frente de la cola.
	 * @param q . Cola que contiene una ruta
	 * @return el String del frente de la cola.
	 */
	private String buscoRot(Queue<Character>q) {
		try {
			String rot="";
		
			while(q.front()!='/')
				rot+=q.dequeue();
			return rot;
		}
		catch(EmptyQueueException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metodo privado que devuelve la cola sin su frente.
	 * @param q. Cola que contiene una ruta
	 * @return la cola sin su frente.
	 */
	private Queue<Character> consRot(Queue<Character>q){
		try {
			Queue<Character>qR=new ColaConArregloCircular<Character>();
		
			while(q.front()!='/')
				q.dequeue();
			q.dequeue();
			while(!q.isEmpty())
				qR.enqueue(q.dequeue());
			return qR;
		}
		catch(EmptyQueueException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	
	// EJERCICIO 2
		/**
		 * Agrega un nuevo archivo un directorio pasado por parámetro.
		 * @param D . Ruta del directorio al cual le agrego el archivo
		 * @param A . Nombre del archivo a agregar.
		 * @throws ExcepcionRutaInvalida si la ruta del directorio destino no existe.
		 * @throws ExcepcionArchivoInvalido si el archivo a agregar ya existe
		 */
		public void agregarArchivo(String D, String A) throws ExcepcionRutaInvalida, ExcepcionArchivoInvalido{
		   Position<Entrada<String,PositionList<String>>> pos;
			
		   String separador = Pattern.quote("/");
		   String [] cadena = D.split(separador);
		
		   pos=buscarPos(cadena);
			
			if(pos==null) throw new ExcepcionRutaInvalida("La ruta pasada es invalida");	
			else {
					PositionList<String>lista=pos.element().getValue();
					Iterator<String> iteLista=lista.iterator();
					boolean existe=false;
					
					while(iteLista.hasNext()&& !existe) {
						String s=iteLista.next();
						if(s.equals(A)) {
							existe=true;
						}
					}
				    if(existe) throw new ExcepcionArchivoInvalido("No se puede agregar la carpeta ya que existe otra con el mismo nombre.");
				    else {
						lista.addLast(A);
				    }
			 	}
			}
		
		//EJERCICIO 3
		
			/**Eliminar archivo, elimina un archivo existente
			 * @param ruta. Ruta del archivo a eliminar.
			 * @throws ExcepcionRutaInvalido si la ruta del archivo a eliminar no existe.
			 */	
		public void EliminarArchivo(String ruta) throws ExcepcionRutaInvalida{
		
			  Position<Entrada<String,PositionList<String>>> pos=null;
				
			  String separador = Pattern.quote("/");
			  String [] cadena = ruta.split(separador);
			  if(cadena.length==1)
				  throw new ExcepcionRutaInvalida("La ruta del archivo no existe.");
			  
			  String nomArch=cadena[cadena.length-1];
		
			  String []nuevac=new String[cadena.length-1];
			  for(int i=0; i<nuevac.length; i++) {
				  nuevac[i]=cadena[i];
			  }
			  
			  pos=buscarPos(nuevac);
			  
			  if(pos!=null) {
				  PositionList<String> lista=pos.element().getValue();
				  Iterator<Position<String>> iteLista=lista.positions().iterator();
				  boolean encontre=false;
				  Position<String> posA=null;
				  while(iteLista.hasNext() && !encontre) {
					  posA=iteLista.next();
					  if(posA.element().equals(nomArch)) {
						  encontre=true;
					  }
				  }
				  if(encontre) {
					 try {	System.out.println("encontre el arch");
							  lista.remove(posA);
						 }
					 catch(InvalidPositionException e) {System.out.println(e.getMessage());}
				  }
				  else throw new ExcepcionRutaInvalida("El archivo a eliminar no existe en el directorio indicado");
			  }
			  else throw new ExcepcionRutaInvalida("la ruta del archivo no existe");
			}
		
		//EJERCICIO 4
			/** El metodo agrega un directorio d1 dentro de otro d2
			 * @param d1 : ruta del directorio a agregar
			 * @param d2 : nombre del directorio que lo recibe(nombre de d2) 
			 * @throws ExcepcionRutaInvalida si la ruta pasada no existe
			 * @throws ExcepcionCarpetaInvalida  si la carpeta que se desea agregar ya existe en el directorio.
			 */
		public void AgregarDirectorio(String d1, String d2) throws ExcepcionRutaInvalida,ExcepcionCarpetaInvalida {
			
			Position<Entrada<String,PositionList<String>>> pos;
				
			String separador = Pattern.quote("/");
		    String [] cadena = d1.split(separador);
			
			pos=buscarPos(cadena);
			
			if(pos==null) throw new ExcepcionRutaInvalida("La ruta es invalida.");
			else{ 
				  Entrada<String,PositionList<String>> nuevoD= new Entrada(d2,new ListaDoblementeEnlazada<String>());
				  try {
					   Iterator<Position<Entrada<String,PositionList<String>>>> iteHijosP= arbol.children(pos).iterator();
					   boolean existeOtro=false;
					   
					   while(iteHijosP.hasNext() && !existeOtro) {
						   Position<Entrada<String,PositionList<String>>> pc=iteHijosP.next();
						   if(pc.element().getKey().equals(d2)) {
							   existeOtro=true;
						   }
					   }
					  
					   if(existeOtro) throw new ExcepcionCarpetaInvalida("ya existe otra carpeta con el mismo nombre");
					   else {
						   		arbol.addLastChild(pos, nuevoD);
					   		}
				  }//cierra try
				 catch (InvalidPositionException e) {System.out.println(e.getMessage()); }
				}
		}

			
		//EJERCICIO 5
		/**
		 * Elimina el directorio correspondiente a la ruta pasada por parámetro.
		 * @param D . Ruta del directorio a eliminar.
		 * @throws ExcepcionRutaInvalida si la ruta del directorio a mover no existe o si quiero eliminar la raiz y contiene mas de una carpeta.
		 */
	 	public void eliminarDirectorio(String D)throws ExcepcionRutaInvalida {
			try {
				  Position<Entrada<String,PositionList<String>>> pos=null;
					
				  String separador = Pattern.quote("/");
				  String [] cadena = D.split(separador);
				  
				  pos=buscarPos(cadena);
					
					if(pos==null) throw new ExcepcionRutaInvalida("la ruta pasada es invalida");
					else{
							arbol.removeNode(pos);
						}
					}
			catch(InvalidPositionException  e) { throw new ExcepcionRutaInvalida("No puedo eliminar la raiz ya que tiene más de una carpeta");
			}
		}
		
		// EJERCICIO 6
		/**
		 * Mueve un directorio dentro de otro directorio pasado por parámetro.
		 * @param D1 .Ruta del directorio que va a ser movido
		 * @param D2 .Ruta del directorio que va a contener al directorio D1
		 * @throws ExcepcionRutaInvalida. Se da en diversos casos: Si se desea mover el directorio raiz, la carpeta a mover es ancestro a la carpeta origen o si alguna ruta es invalida.
		 */
		public void moverDirectorio(String D1, String D2) throws ExcepcionRutaInvalida {
			boolean movi=false;
			try {
				   Position<Entrada<String,PositionList<String>>> pos;
					
				   String separador1 = Pattern.quote("/");
				   String [] cadena1 = D1.split(separador1);
				   
				   String separador2 = Pattern.quote("/");
				   String [] cadena2 = D2.split(separador2);
				
				   Position<Entrada<String,PositionList<String>>> pos1=buscarPos(cadena1);
				   Position<Entrada<String,PositionList<String>>> pos2=buscarPos(cadena2);
					
				   if(pos1!=null && pos2!=null) {
					   if(pos1!=arbol.root()) {
						   if(!esAncestro(pos2,pos1)) {
							   arbol.addLastChild(pos2, pos1.element());
							   arbol.removeNode(pos1);
							   movi=true;
						   }
						   else throw new ExcepcionRutaInvalida("No puedo mover la carpeta deseada ya que es ancestro de la carpeta origen");
					     }
					   else throw new ExcepcionRutaInvalida("No puede mover el directorio raiz");
				   }
				   else throw new ExcepcionRutaInvalida("La primera ruta NO EXISTE o la segunda NO EXISTE. Verifique");
				}
			catch(InvalidPositionException | EmptyTreeException e) {
				e.printStackTrace();}
		}
		
		/**
		 * Metodo privado que verifica si un directorio es ancestro o no de otro
		 * @param posD2 Directorio a verificar
		 * @param posD1 Supuesto directorio ancestro
		 * @return Si es o no ancestro.
		 */
		private boolean esAncestro(Position<Entrada<String,PositionList<String>>> posD2, Position<Entrada<String,PositionList<String>>> posD1) {
			boolean esAncestro=false;
			Position<Entrada<String,PositionList<String>>> p=posD2;
			try {
					while(p!=arbol.root() && !esAncestro) {
						if(p.element().getKey().equals(posD1.element().getKey())) {
							esAncestro=true;
							}
						else { p=arbol.parent(p);
						}
					}
			}
			catch(EmptyTreeException | InvalidPositionException | BoundaryViolationException e) {System.out.println(e.getMessage());}
			return esAncestro;
		}

		//EJERCICIO 7
		/**
		 * Cuenta la cantidad de directorios y archivos.
		 * @return Retorna una entrada que contiene como clave la cantidad de directorios y como valor la cantidad de archivos.  
		 */
		public Entrada<Integer,Integer> cantDyA()
		{
			Entrada<Integer,Integer> ret= new Entrada<Integer,Integer>(null,null);
			int cantArch=0;
			
			for(Entrada<String,PositionList<String>>rotulo : arbol)
				{ 	PositionList<String> listaArch= rotulo.getValue();
					cantArch= cantArch+ listaArch.size();
				
				}
			ret.setKey(arbol.size());
			ret.setValue(cantArch);
			return ret;
		}
		
		//EJERCICIO 8
		/**
		 * Lista cada carpeta y archivo segun el nivel al que pertenezca.
		 * @return Retorna la lista correspondiente
		 */
				
		public PositionList<String> listado_Por_Niveles() {
		PositionList<String> lista= new ListaDoblementeEnlazada<String>();
			lista.addLast(" ");
				try {
					Queue<Position<Entrada<String,PositionList<String>>>> c =new ColaConArregloCircular <Position<Entrada<String,PositionList<String>>>>();
					Queue<String> aux= new ColaConArregloCircular<String>();
					c.enqueue(arbol.root());
					c.enqueue(null);
					
					lista.addLast("<");
					lista.addLast(arbol.root().element().getKey()) ; // agregue el directorio principal.
					lista.addLast("/");
					
					while(!c.isEmpty())
					  {
						Position<Entrada<String,PositionList<String>>> v = c.dequeue();
						if(v!=null) {
							for(Position<Entrada<String,PositionList<String>>> d: arbol.children(v)) {
						           	c.enqueue(d);
						        	aux.enqueue(d.element().getKey()); // encole en la cola de string el nombre de la carpeta.
						        }
						        
						    for(String arch: v.element().getValue()) {
						        aux.enqueue(arch);
						       }
				          	}
						else {
				               	 if(!c.isEmpty()) c.enqueue(null); 
				               	 aux.enqueue("/"); // fin de nivel	en la cola de string
				         	}
					  }
				
					if(c.isEmpty()) 
					{
						while(!aux.isEmpty())
						{
							String elem= aux.dequeue();
							
							if(elem=="/")
								{lista.remove(lista.last());
								lista.addLast(elem);
								}
							else {
									lista.addLast(elem);
									lista.addLast(",");
								}
						}
						lista.remove(lista.last()); //correcion
						lista.addLast(">");
						//no respeta el formato porque al final agrego un " / "???????
					}			
				}
				catch(EmptyQueueException | EmptyTreeException | InvalidPositionException | EmptyListException e) {e.printStackTrace();}; 
				return lista;
			}
			
		//EJERCICIO 9
		/**
		 * Lista la extension de los archivos y el nombre del mismo
		 * @return Un diccionario que contiene como clave la extension y como valor el nombre del archivo.
		 */
		public Dictionary<String,String> listado_Por_Extension()
		{Dictionary<String, String> ret= new DiccionarioHA<String,String>();	
		try{
				for(Entry<String,PositionList<String>> ent : arbol)
				{
					PositionList<String> listaArch= ent.getValue();
					
					if(!listaArch.isEmpty())
					{
						for(String rotulo: listaArch)
						{
							String separador= Pattern.quote(".");
							String [] cad = rotulo.split(separador);
							String a=cad[1];
							String b= cad[0];
							ret.insert(a,b);				
						}
					}
				}
			}
			catch(InvalidKeyException e) {System.out.println(e.getMessage());}		
				return ret;
		}
			
		//EJERCICIO 10
		/**
		 * Lista en un par la ruta del directorio y su profundidad en el sistema.
		 * @return Un mapeo que contiene como clave la ruta de un directorio y como valor su profundidad en el sistema.
		 */
		
		public Map<String,String> listado_Por_Profundidad(){
			Map<String, String> ret= new MapeoHashCerrado <String,String>();
			String ruta= new String(); 
			
			
			try {
					if(!arbol.isEmpty())
					{int cont=0;
						ruta=arbol.root().element().getKey()+"/";
						ret.put(ruta,String.valueOf(cont));
						listadoRec(arbol.root(),ret,cont,ruta);
					}
				}
			catch(InvalidKeyException | EmptyTreeException e) {System.out.println(e);}
			return ret;
		}
			
		/**
		 * Metodo auxiliar que lista la ruta de un directorio y su profundidad en el sistema
		 * @param p. Posicion actual a listar.
		 * @param map. Mapeo que contendra rutas y profundidades
		 * @param prof.Profundidad del directorio
		 * @param ruta. Ruta del directorio
		 */
		private void listadoRec(Position<Entrada<String,PositionList<String>>> p, Map<String,String> map, int prof, String ruta ) {
		try {
				for (Position<Entrada<String,PositionList<String>>> pos: arbol.children(p)) {
				listadoRec(pos, map, prof+1, ruta+pos.element().getKey()+"/");
				}
				map.put(ruta,String.valueOf(prof));
			}
		catch(InvalidPositionException | InvalidKeyException e) {System.out.println(e);}
		}
			

		/**
		 * Metodo auxiliar que devuelve la posición del árbol que corresponde con la ruta pasada por parámetro.
		 * @param cad. Arreglo que contiene la ruta
		 * @return la posición del arbol que se corresponde con la ruta pasada por parámetro.
		 */
		private Position<Entrada<String,PositionList<String>>> buscarPos(String [] cad){
			Position<Entrada<String,PositionList<String>>> puntero=null;
			try {
				puntero=arbol.root();
				boolean esTrue=true;
				int i=0;
				if(puntero!=null) {// si la raiz NO ES NULA
					if(puntero.element().getKey().equals(cad[i])) { //compara la raiz
						i++;
						while(i<cad.length && esTrue && puntero!=null) {
						
							Iterator<Position<Entrada<String, PositionList<String>>>> iteC=arbol.children(puntero).iterator();
							boolean encontre=false;
							
							while(iteC.hasNext()&& !encontre) {
								Position<Entrada<String,PositionList<String>>> posIte=iteC.next();
								String rot= posIte.element().getKey();
								if(rot.equals(cad[i])) {
									encontre=true;
									puntero=posIte;
									i++;
								}
							}
							if(!encontre) {
								puntero=null;
								esTrue=false;
								}
						}//while principal
					}
					else puntero=null;
					}
				}
			catch(InvalidPositionException |EmptyTreeException e) {System.out.println(e.getMessage());}
					return puntero;
		}	
}