package GUI;



import Logica.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import ClasesCompartidas.Position;
import Entrada.Entrada;
import Entrada.Entry;
import Entrada.InvalidKeyException;
import Excepciones.*;
import TDAArbol.Arbol;
import TDAArbol.EmptyTreeException;
import TDAArbol.Tree;
import TDADiccionario.Dictionary;
import TDALista.*;
import TDAMapeo.*;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Color;
import javax.swing.JSlider;
import java.awt.ScrollPane;
import java.awt.Panel;
import java.awt.Canvas;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.List;
import javax.swing.JTextPane;
import java.awt.TextArea;
import javax.swing.JToggleButton;
import javax.swing.JProgressBar;
import java.awt.Choice;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JCheckBox;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;


/*
* @author Cátedra de Estructuras de Datos, Departamento de Cs. e Ing. de la Computación, UNS.
*/






public class GUI {
    private Logica logica;
	private JFrame frame;
	private JTextField txtFieldGenerarJerarquia;
	private JTextField textFieldAgregarArch;
	private JTextField textFieldNombre;
	
	private DefaultTreeModel modelo;
	private JTree tree;
	private JTextField textFieldElimArchivo;
	private JTextField textFieldElimDirectorio;
	private JTextField textFieldMoverD1;
	private JTextField textFieldMoverAD2;
	private JTextField textFieldAgregarDirectorio;
	private JTextField textFieldNombreDirectorio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		logica= new Logica();
		
		/**
		 * Crea e inicializa la ventana
		 */
		frame = new JFrame();
		frame.setBackground(new Color(176, 196, 222));
		frame.getContentPane().setBackground(new Color(255, 250, 250));
		frame.setBounds(100, 100, 923, 516);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/**
		 * Crea los botones a usar
		 */
		
		JButton btnGenerarJerarquia = new JButton("GENERAR JERARQUIA");
		btnGenerarJerarquia.setBackground(new Color(240, 255, 255));
		btnGenerarJerarquia.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnGenerarJerarquia.setBounds(7, 35, 231, 39);
		frame.getContentPane().add(btnGenerarJerarquia);
	
		
		JButton btnAgregarArchivo = new JButton("Agregar Archivo");
		btnAgregarArchivo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAgregarArchivo.setBackground(new Color(224, 255, 255));
		btnAgregarArchivo.setBounds(4, 99, 144, 23);
		frame.getContentPane().add(btnAgregarArchivo);
		
		JButton btnEliminarDirectorio = new JButton("ELIMINAR DIRECTORIO");
		btnEliminarDirectorio.setBounds(179, 198, 152, 23);
		frame.getContentPane().add(btnEliminarDirectorio);
		
		JButton btnMoverDirectorio = new JButton("Mover Directorio");
		btnMoverDirectorio.setBounds(529, 37, 169, 39);
		frame.getContentPane().add(btnMoverDirectorio);
		
		Button btnListadoPorNiveles = new Button("Listado Por Niveles");
		btnListadoPorNiveles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnListadoPorNiveles.setActionCommand("Listado Por Niveles");
		btnListadoPorNiveles.setBackground(new Color(230, 230, 250));
		btnListadoPorNiveles.setFont(new Font("Dialog", Font.PLAIN, 17));
		btnListadoPorNiveles.setBounds(682, 354, 215, 39);
		frame.getContentPane().add(btnListadoPorNiveles);
		
		JButton ListadoPorExtension = new JButton("Listado por Extension");
		ListadoPorExtension.setBackground(new Color(255, 235, 205));
		ListadoPorExtension.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ListadoPorExtension.setBounds(682, 233, 215, 39);
		frame.getContentPane().add(ListadoPorExtension);
		
		JButton ListadoPorPorfundidad = new JButton("Listado por Profundidad");
		ListadoPorPorfundidad.setBackground(new Color(255, 235, 205));
		ListadoPorPorfundidad.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ListadoPorPorfundidad.setBounds(682, 294, 215, 39);
		frame.getContentPane().add(ListadoPorPorfundidad);
		
		JButton btnEliminarArchivo = new JButton("ELIMINAR ARCHIVO");
		btnEliminarArchivo.setBounds(10, 197, 149, 25);
		frame.getContentPane().add(btnEliminarArchivo);

		
		/**
		 * Crea los campos de textos editables
		 */
		txtFieldGenerarJerarquia = new JTextField();
		txtFieldGenerarJerarquia.setBounds(248, 38, 149, 37);
		frame.getContentPane().add(txtFieldGenerarJerarquia);
		txtFieldGenerarJerarquia.setColumns(10);
		
		textFieldAgregarArch = new JTextField();
		textFieldAgregarArch.setBackground(new Color(255, 250, 205));
		textFieldAgregarArch.setBounds(153, 101, 86, 20);
		frame.getContentPane().add(textFieldAgregarArch);
		textFieldAgregarArch.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBackground(new Color(245, 245, 220));
		textFieldNombre.setBounds(248, 101, 86, 20);
		frame.getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		TextArea textArea = new TextArea();
		textArea.setBackground(new Color(255, 255, 255));
		textArea.setBounds(350, 191, 295, 202);
		frame.getContentPane().add(textArea);
		
		/** 
		 * Crea e las distintas etiquetas
		 */
	
		
	
		JLabel lbllistarAechivos = new JLabel("Listar archivos ");
		lbllistarAechivos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbllistarAechivos.setBounds(682, 219, 182, 14);
		frame.getContentPane().add(lbllistarAechivos);
		
		
		JLabel lblInserteRuta = new JLabel("Inserte Ruta");
		lblInserteRuta.setBounds(153, 86, 83, 14);
		frame.getContentPane().add(lblInserteRuta);
		
		JLabel label = new JLabel("________");
		label.setBounds(0, 74, 397, 14);
		frame.getContentPane().add(label);
		
		
		
		modelo = new DefaultTreeModel(null);
		
		tree = new JTree();
		tree.setModel(modelo);
		tree.setBounds(311, 394, 105, 153);
		frame.getContentPane().add(tree);
		
		JLabel lblListarDirecotorios = new JLabel("Listar Directorios\r\n");
		lblListarDirecotorios.setBounds(682, 270, 104, 23);
		frame.getContentPane().add(lblListarDirecotorios);
		
		JLabel lblListarJerarquia = new JLabel("Listar Jerarquia");
		lblListarJerarquia.setBounds(682, 334, 86, 14);
		frame.getContentPane().add(lblListarJerarquia);
			
			
			
			textFieldElimArchivo = new JTextField();
		textFieldElimArchivo.setBounds(46, 245, 86, 20);
		frame.getContentPane().add(textFieldElimArchivo);
		textFieldElimArchivo.setColumns(10);
		
		textFieldElimDirectorio = new JTextField();
		textFieldElimDirectorio.setBounds(202, 245, 86, 20);
		frame.getContentPane().add(textFieldElimDirectorio);
		textFieldElimDirectorio.setColumns(10);
		
		textFieldMoverD1 = new JTextField();
		textFieldMoverD1.setBounds(718, 29, 86, 20);
		frame.getContentPane().add(textFieldMoverD1);
		textFieldMoverD1.setColumns(10);
		
		textFieldMoverAD2 = new JTextField();
		textFieldMoverAD2.setBounds(718, 71, 86, 20);
		frame.getContentPane().add(textFieldMoverAD2);
		textFieldMoverAD2.setColumns(10);
		
		JLabel lblInserteDirectorio = new JLabel("Inserte Directorio a mover");
		lblInserteDirectorio.setBounds(708, 4, 159, 14);
		frame.getContentPane().add(lblInserteDirectorio);
		
		JLabel lblInserteDirectorioDe = new JLabel("Inserte directorio de llegada");
		lblInserteDirectorioDe.setBounds(708, 49, 189, 14);
		frame.getContentPane().add(lblInserteDirectorioDe);
		
		JButton btnAgregarDirectorio = new JButton("Agregar Directorio");
		JButton btnGenerarArbolpor = new JButton("GENERAR ARBOL. (por defecto)");
		btnGenerarArbolpor.setBounds(110, 330, 221, 23);
		frame.getContentPane().add(btnGenerarArbolpor);
		JButton btnMostrarCantDireYArch = new JButton("Mostrar Cantidad de Directorios y Archivos");
		
		btnMostrarCantDireYArch.setBounds(455, 141, 262, 23);
		frame.getContentPane().add(btnMostrarCantDireYArch);
		
		
		
	
		 /**
		  * Crea oyente del botón generar jerarquia.
		  */
		
		btnGenerarJerarquia.addActionListener(new ActionListener () {
			/**
			 * El método actionPerformed crea el oyente para accionar el botón "CrearArbol".
			 * 
			 *@param e ActionEvent - Este evento se dispara al apretar el botón "Crear Arbol" 
			 *
			 */
			public void actionPerformed(ActionEvent e) {
			    //logica.generarJerarquia(txtFieldGenerarJerarquia.getText());
				Tree<Entrada<String,PositionList<String>>> arbol=logica.generarJerarquia(txtFieldGenerarJerarquia.getText());  
				System.out.println("hola");
				if(!arbol.isEmpty())		 
				
		    { String dato;
		    
				  dato=txtFieldGenerarJerarquia.getText();
				  
				  
				btnGenerarJerarquia.setEnabled(false);
				 
				 txtFieldGenerarJerarquia.setText("");
				 
				 btnAgregarArchivo.setEnabled(true);
				 btnAgregarDirectorio.setEnabled(true);
					
				
					ListadoPorExtension.setEnabled(true);
					ListadoPorPorfundidad.setEnabled(true);
					btnListadoPorNiveles.setEnabled(true);
					
					btnGenerarArbolpor.setEnabled(false);
				
					
					textFieldNombre.setEnabled(true);
					textFieldAgregarArch.setEnabled(true);
					
					
					DefaultMutableTreeNode root = new DefaultMutableTreeNode(dato);
					modelo.setRoot(root);
					
					modelo.reload();}
				 
				 
				 
			}
		});
		
		
		btnGenerarArbolpor.setBounds(110, 330, 221, 23);
		frame.getContentPane().add(btnGenerarArbolpor);
		
		btnGenerarArbolpor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {	 
				String s= new String();
			      Arbol<Entrada<String,PositionList<String>>> arbe=logica.GenerarJerarquiaAuxiliar();
			      s+=arbe.root().element().getKey();
			      s+='\n';
				
			      
				/**
			      for(Position<Entrada<String,PositionList<String>>> p: arbe.positions()) {
				       Entrada<String,PositionList<String>> entry= p.element();		      
			           s+= entry.getKey()+  "      " + "\n";
			           if(!entry.getValue().isEmpty())
			             { PositionList<String>list= entry.getValue();
			        	  for(Position<String> pos: list.positions() )  {
			        		  s+=pos.element()+"     " + "\n";
			        	  } 
			           }
			           
			           s+='\n';
			      }
			      textArea.setText(s);
			      */
			}
		catch(EmptyTreeException d) {d.printStackTrace();}
			      
		
						/**
						 * PositionList<String> dato= new ListaDoblementeEnlazada<String>();
						 
						String s= new String();
					   Iterator<Position<Entrada<String,PositionList<String>>>> it1=arbe.positions().iterator();
						for(Position<String> pos: dato.positions())
						{   if(pos.element()!="/")
							   s+=pos.element()+" ";
						else
							    s+='\n';}
						
						textArea.setText(s);
						*/
						
					
			}
		});
	
		/******AGREGAR ARCHIVOOOOOOOOOOOOOOOOOOO******+
		 * Crea el oyente para el boton agregar archivo
		 */
		btnAgregarArchivo.addActionListener(new ActionListener() {
			/**
			 * El método actionPerformed asocia el oyente para accionar el botón "agregar archivo".
			 * 
			 *@param e ActionEvent - Este evento salta al accionar el botón.
			 *
			 */
			public void actionPerformed(ActionEvent e) {
			try {
					logica.agregarArchivo(textFieldAgregarArch.getText(),textFieldNombre.getText());
								
					textFieldAgregarArch.setText("");
					textFieldNombre.setText("");
					btnAgregarArchivo.setEnabled(true);
					btnMostrarCantDireYArch.setEnabled(true);
			}
			catch(ExcepcionRutaInvalida g ){ JOptionPane.showMessageDialog(null,g.getMessage());}
			catch(ExcepcionArchivoInvalido j){ JOptionPane.showMessageDialog(null,j.getMessage());}
					 
			
				textFieldAgregarArch.setText("");
				textFieldNombre.setText("");
			}
		
		});
		
		btnEliminarArchivo.addActionListener(new ActionListener() {
			/****** ELIMINAR ARCHIVO*******
			 * El método actionPerformed asocia el oyente para el boton Eliminar Archivo.
			 * @param e ActionEvent - Cuando se apreta el botón se inicializa este evento.
			 */
			public void actionPerformed(ActionEvent e) {
			try {
				logica.EliminarArchivo(textFieldElimArchivo.getText());
				textFieldElimArchivo.setText("");
			}
			catch(ExcepcionRutaInvalida e1) {JOptionPane.showMessageDialog(null,e1.getMessage());}
			}
		});
		
		/**
		 * ********* AGREGAR DIRECTORIO **********
		 */
		btnAgregarDirectorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {   
				logica.AgregarDirectorio(textFieldAgregarDirectorio.getText(),textFieldNombreDirectorio.getText());
			 		
					textFieldAgregarDirectorio.setText("");
					textFieldNombreDirectorio.setText("");
					
				}
			catch(ExcepcionRutaInvalida e2) {JOptionPane.showMessageDialog(null,e2.getMessage());}
			catch(ExcepcionCarpetaInvalida e3) {JOptionPane.showMessageDialog(null,e3.getMessage());}
			}
			
		});
		
		/**
		 * ********** ELIMINAR DIRECTORIOO********
		 */
		btnEliminarDirectorio.addActionListener(new ActionListener() {
			/**
			
			 * El método actionPerformed asocia el oyente para accionar el botón "eliminar Directorio".
			 * 
			 *@param e ActionEvent - Cuando se apreta el botón "Crear clon" se inicializa este evento.
			 *
			 */
			public void actionPerformed(ActionEvent e) {
			try {
				logica.eliminarDirectorio(textFieldElimDirectorio.getText());
				
				textFieldElimDirectorio.setText("");
			}
			catch(ExcepcionRutaInvalida e1)
			{String m=e1.getMessage();
			 JOptionPane.showMessageDialog(null,m);
			}
		}
		}
			);
		
		/**
		 * ******** MOVER DIRECTORIO**********
		 */
		btnMoverDirectorio.addActionListener(new ActionListener() {
			/**
			 * actionPerformed asocia el oyente para accionar el botón "mover Directorio".
			 * 
			 *@param e ActionEvent - Cuando se apreta el botón "mover directorio" se inicializa este evento.
			 *
			 */
			public void actionPerformed(ActionEvent e) {
			try {
				logica.moverDirectorio(textFieldMoverD1.getText(), textFieldMoverAD2.getText());
				
				btnMoverDirectorio.setEnabled(true);
				textFieldMoverD1.setText("");
				textFieldMoverAD2.setText("");
				
				}
			catch(ExcepcionRutaInvalida e1)
				{String m=e1.getMessage();
				 JOptionPane.showMessageDialog(null,m);
				}
			}
		}
		);
		/**
		 * ** CANTIDAD DE ARCHIVOS Y DIRECTORIOS******
		 */
		btnMostrarCantDireYArch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  String s="(Número de directorios,Número de archivos)"+ '\n';
				Entry<Integer,Integer> dato= logica.cantDyA();
			      
				    
					s=s+ "("+ String.valueOf(dato.getKey()) + ","+ String.valueOf(dato.getValue())+ ")" ;
					
					textArea.setText(s);
					 
			}
		});
		
		
		
		btnAgregarDirectorio.setBounds(4, 141, 144, 23);
		frame.getContentPane().add(btnAgregarDirectorio);
		
		textFieldAgregarDirectorio = new JTextField();
		textFieldAgregarDirectorio.setBounds(153, 142, 86, 20);
		frame.getContentPane().add(textFieldAgregarDirectorio);
		textFieldAgregarDirectorio.setColumns(10);
		
		textFieldNombreDirectorio = new JTextField();
		textFieldNombreDirectorio.setBounds(245, 142, 86, 20);
		frame.getContentPane().add(textFieldNombreDirectorio);
		textFieldNombreDirectorio.setColumns(10);
		
		JLabel lblInserteNombre = new JLabel("Inserte nombre");
		lblInserteNombre.setBounds(248, 86, 80, 14);
		frame.getContentPane().add(lblInserteNombre);
		
		JLabel lblInserteRuta_1 = new JLabel("Inserte Ruta");
		lblInserteRuta_1.setBounds(153, 127, 78, 14);
		frame.getContentPane().add(lblInserteRuta_1);
		
		JLabel lblInserteNombre_1 = new JLabel("inserte nombre ");
		lblInserteNombre_1.setBounds(245, 127, 86, 14);
		frame.getContentPane().add(lblInserteNombre_1);
		
		JLabel lblInserteArchivo = new JLabel("INSERTE ARCHIVO");
		lblInserteArchivo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		lblInserteArchivo.setBounds(260, 20, 146, 14);
		frame.getContentPane().add(lblInserteArchivo);
		
		JLabel lblInserteRuta_2 = new JLabel("Inserte ruta del archivo");
		lblInserteRuta_2.setBounds(20, 232, 128, 14);
		frame.getContentPane().add(lblInserteRuta_2);
		
		JLabel lblInserteRutaDel = new JLabel("Inserte ruta del directorio");
		lblInserteRutaDel.setBounds(192, 232, 139, 14);
		frame.getContentPane().add(lblInserteRutaDel);
		
		
		
		JTree tree_1 = new JTree();
		tree_1.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("ED-Proyecto") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("directorio1");
						node_1.add(new DefaultMutableTreeNode("blue"));
						node_1.add(new DefaultMutableTreeNode("violet"));
						node_1.add(new DefaultMutableTreeNode("red"));
						node_1.add(new DefaultMutableTreeNode("yellow"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("directorio2");
						node_1.add(new DefaultMutableTreeNode("basketball"));
						node_1.add(new DefaultMutableTreeNode("soccer"));
						node_1.add(new DefaultMutableTreeNode("football"));
						node_1.add(new DefaultMutableTreeNode("hockey"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("direct3");
						node_1.add(new DefaultMutableTreeNode("hot dogs"));
						node_1.add(new DefaultMutableTreeNode("pizza"));
						node_1.add(new DefaultMutableTreeNode("ravioli"));
						node_1.add(new DefaultMutableTreeNode("bananas"));
					add(node_1);
				}
			}
		));
		tree_1.setBounds(20, 273, 93, 153);
		frame.getContentPane().add(tree_1);
		
		
		
		
		
		
		
		  btnListadoPorNiveles.addActionListener(new ActionListener() {
		 	/**
			 * El método actionPerformed asocia el oyente para accionar el botón "Listar por Niveles".
			 * 
			 *@param e ActionEvent - Este evento se inicializa al hacer click en el boton "Listar po Niveles".
			 *
			 */
			
			public void actionPerformed(ActionEvent e) {
				PositionList<String> dato= new ListaDoblementeEnlazada<String>();
				String s= new String();
				dato=logica.listado_Por_Niveles();
				for(String a: dato) {
				 if(a!="/" && a!="<" && a!=">") {
					   s+=a+" ";
				}
				 else s+='\n';
				}
				textArea.setText(s);	
			}
		});
		
		
		
		
		
		ListadoPorExtension.addActionListener(new ActionListener() {
			/**
			 * El comando actionPerformed asocia el oyente para asociarlo con el botón "ListarPorExtension"
			 * @param e ActionEvent - Cuando se apreta el botón se inicializa este evento.
			 */
			
			public void actionPerformed(ActionEvent e) {
			try {
				Dictionary<String,String> dato= logica.listado_Por_Extension();
				Map<String,Boolean> mp=new MapeoHashCerrado<String,Boolean>();
				String s= new String();
				for(Entry<String,String> tri: dato.entries()){
					Boolean o=mp.put(tri.getKey(),false);
					
				}
				for(Entry<String,Boolean> d: mp.entries()) {
					System.out.println(d.getKey()+" "+String.valueOf(d.getValue()));
				}
				for(Entry<String,String> tri: dato.entries()){
					if(mp.get(tri.getKey()).equals(false)) {
						for(Entry<String,String> g: dato.findAll(tri.getKey()))
							{s+= g.getKey()+" "+g.getValue();
							s+='\n';
									}
						Boolean o=mp.put(tri.getKey(), true);
					}
				}					
				textArea.setText(s);
			} catch (InvalidKeyException e1) {
				
				e1.printStackTrace();
			} 	
			}
		});
		 
		
		ListadoPorPorfundidad.addActionListener(new ActionListener() {
			
			/******** LISTADO POR PROFUNDIDAD ******++
			 * El comando actionPerformed asocia el oyente para el botón "Listar por profundidad"
			 * @param e ActionEvent - Cuando se apreta el botón se inicializa este evento.
			 */
			public void actionPerformed(ActionEvent e) {
				Map<String,String> p= logica.listado_Por_Profundidad();
			 
				//PositionList<String> dato= new ListaDoblementeEnlazada<String>();
				String s= new String(" ");
				//dato=logica.listarArchivos();
				
				for(Entry<String,String> tri: p.entries()) {
					s=s+ "("+ tri.getKey() + ","+tri.getValue()+ ")" ;
				s+='\n';
				}
				textArea.setText(s);
			}
		   });	
		
		
	} 
}