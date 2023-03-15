import java.io.BufferedReader;
import java.io.FileReader;

import Entrada.*;
import TDAArbol.*;
import TDACola.*;
import TDALista.*;
import TDAPila.*;


public class Prueba {

	public Tree<Entrada<String,PositionList<String>>> esValido(String arch){
		boolean valido=true;
		try {
			BufferedReader br= new BufferedReader(new FileReader (arch));
		    Stack<Character> pila=new PilaArreglo<Character>();
		    Queue<Character> cola=new ColaConArregloCircular<Character>();
		    String linea=br.readLine();
		    String palabra;
		    
		    if(linea==null)
		    	valido=false;
		    else {
		    	while(linea!=null && valido) {
		    		palabra=extraerPalabra(linea);
		    		if(palabra.equals("<carpeta>")) {
		    			if(linea!=null)
		    				palabra=extraerPalabra(linea);
		    				
		    		}
		    	}
		    }
		}
		catch() {
			
		}
	}
	
	private String extraerPalabra(String l) {
		String pal="";
		boolean listo=false;
		char caracter;
		int i=0;
		
		caracter=l.charAt(i);
		if(caracter=='<')
			while(!listo) {
				pal+=caracter;
				caracter=l.charAt(i);
				if(caracter=='>') {
					listo=true;
					pal+=caracter;
				}
				i++;
			}
		return pal;
	}
}
