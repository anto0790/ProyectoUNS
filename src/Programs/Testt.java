package Programs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import ClasesCompartidas.Position;
import Entrada.Entrada;
import Excepciones.ExcepcionRutaInvalida;
import TDAArbol.Arbol;
import TDAArbol.EmptyTreeException;
import TDAArbol.Tree;
import TDACola.ColaConArregloCircular;
import TDACola.Queue;
import TDALista.PositionList;

public class Testt {
public static void main(String[] args) throws EmptyTreeException, IOException  {
	try {
	Logica L=new Logica();
	String c="C:\\Antonela\\Anto\\prueba.txt";


	Tree<Entrada<String, PositionList<String>>> a=L.generarJerarquia(c);
	
		//Verifico la cantidad de elementos del árbol.
		//System.out.println("Hay: "+a.size()+" elemento/s.");

		PositionList<String> lista8= L.listado_Por_Niveles(a);
		for(String e: lista8) {
			System.out.println(e);
			}
	}
	catch(ExcepcionRutaInvalida e) {
		System.out.println(e.getMessage());
		}
	}
}

