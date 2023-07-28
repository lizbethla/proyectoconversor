package proyectoconversor;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author NLA
 *
 */
public class VistaPrincipal extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menu1, menu2, menu3, menu4;
	private JMenu submenu1, submenu2, submenu3;
	private JMenuItem opcion1_1;
	private JMenuItem opcion1_2;
	private JMenuItem opcion2_1;
	private JMenuItem opcion2_2;
	private JMenuItem opcion3_1;
	private JMenuItem opcion3_2;
	private JMenuItem opcion4_1;
	private JMenuItem opcion4_2;
	private JMenuItem opcion5_1;
	private JMenuItem opcion5_2;
	private JMenuItem opcion6_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPrincipal frame = new VistaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public VistaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("CONVERSOR");
		setResizable(false);
		setSize(800,600);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		mostrarMenu();
		
	}
	
	public void mostrarMenu(){
	    menuBar = new JMenuBar();
	    menu1 = new JMenu("Nuevo");
	    menu2 = new JMenu("Conversor Monedas");
	    menu3 = new JMenu("Otros Conversores");
	    menu4 = new JMenu("Ayuda");
	    submenu1 = new JMenu("Masa");
	    submenu2 = new JMenu("Longitud");
	    submenu3 = new JMenu("Tamaño de Datos");
	    
	    opcion1_1 = new JMenuItem("Tasa de Cambio");
	    opcion1_2 = new JMenuItem("Salir");
	    opcion2_1 = new JMenuItem("De otras Monedas a Soles");
	    opcion2_2 = new JMenuItem("De Soles a otras Monedas");
	    opcion3_1 = new JMenuItem("De otras Unidades de Masa a Kilográmos");
	    opcion3_2 = new JMenuItem("De Kilográmos a otras Unidades de Masa");
	    opcion4_1 = new JMenuItem("De otras Unidades de Longitud a Metros");
	    opcion4_2 = new JMenuItem("De Metros a otras Unidades de Longitud");
	    opcion5_1 = new JMenuItem("De otras Unidades de Tamaño de Datos a Bytes");
	    opcion5_2 = new JMenuItem("De Bytes a otras Unidades de Tamaño de Datos");
	    opcion6_1 = new JMenuItem("Acerca de");

	    setJMenuBar(menuBar);
	    menuBar.add(menu1); 
	    menuBar.add(menu2);
	    menuBar.add(menu3);
	    menuBar.add(menu4);
	    menu1.add(opcion1_1);
	    menu1.add(opcion1_2);
	    menu2.add(opcion2_1);
	    menu2.add(opcion2_2);
	    menu3.add(submenu1);
	    menu3.add(submenu2);
	    menu3.add(submenu3);
	    menu4.add(opcion6_1);
	    submenu1.add(opcion3_1);
	    submenu1.add(opcion3_2);
	    submenu2.add(opcion4_1);
	    submenu2.add(opcion4_2);
	    submenu3.add(opcion5_1);
	    submenu3.add(opcion5_2);
	    
	    opcion1_1.addActionListener(this);
	    opcion1_2.addActionListener(this);
	    opcion2_1.addActionListener(this);
	    opcion2_2.addActionListener(this);	 
	    opcion3_1.addActionListener(this);
	    opcion3_2.addActionListener(this);
	    opcion4_1.addActionListener(this);
	    opcion4_2.addActionListener(this);
	    opcion5_1.addActionListener(this);
	    opcion5_2.addActionListener(this);
	    opcion6_1.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == opcion1_1){
			VistaTasaCambio ventanaTipoCambio =new VistaTasaCambio();
			ventanaTipoCambio.setVisible(true);		
		}
		if(e.getSource() == opcion1_2){
			JOptionPane.showMessageDialog(null,"Saliendo del Programa Conversor");
	        System.exit(0);	
		}	
		if(e.getSource() == opcion2_1){
			VistaConversor ventanaConversor =new VistaConversor(1,"SOLES","CONVERSOR DE MONEDAS","DE OTRAS MONEDAS A SOLES","./tasacambio.csv");
			ventanaConversor.setVisible(true);	
		}
		if(e.getSource() == opcion2_2){		
		    VistaConversor ventanaConversor =new VistaConversor(2,"SOLES","CONVERSOR DE MONEDAS","DE SOLES A OTRAS MONEDAS","./tasacambio.csv");
		    ventanaConversor.setVisible(true);	
		}
		if(e.getSource() == opcion3_1){
			VistaConversor ventanaConversor =new VistaConversor(1,"KILOGRÁMOS", "CONVERSOR DE MASA","DE OTRAS UNIDADES DE MASA A KILOGRÁMOS","./unidadmedidamasa.csv");
			ventanaConversor.setVisible(true);	
		}
		if(e.getSource() == opcion3_2){		
			VistaConversor ventanaConversor =new VistaConversor(2,"KILOGRÁMOS", "CONVERSOR DE MASA","DE KILOGRÁMOS A OTRAS UNIDADES DE MASA","./unidadmedidamasa.csv");
			ventanaConversor.setVisible(true);	
		}
		if(e.getSource() == opcion4_1){
			VistaConversor ventanaConversor =new VistaConversor(1,"METROS", "CONVERSOR DE LONGITUD","DE OTRAS UNIDADES DE LONGITUD A METROS","./unidadmedidalongitud.csv");
			ventanaConversor.setVisible(true);
		}
		if(e.getSource() == opcion4_2){		
			 VistaConversor ventanaConversor =new VistaConversor(2,"METROS", "CONVERSOR DE LONGITUD","DE METROS A OTRAS UNIDADES DE LONGITUD","./unidadmedidalongitud.csv");
			ventanaConversor.setVisible(true);	
		}
		if(e.getSource() == opcion5_1){		
			VistaConversor ventanaConversor =new VistaConversor(1,"BYTES", "CONVERSOR DE TAMAÑO DE DATOS","DE OTRAS UNIDADES DE TAMAÑO DE DATOS A BYTES","./unidadmedidadatos.csv");
			ventanaConversor.setVisible(true);	
		}	
		if(e.getSource() == opcion5_2){		
			VistaConversor ventanaConversor =new VistaConversor(2,"BYTES", "CONVERSOR DE TAMAÑO DE DATOS","DE BYTES A OTRAS UNIDADES DE TAMAÑO DE DATOS","./unidadmedidadatos.csv");
			ventanaConversor.setVisible(true);
		}
		if(e.getSource() == opcion6_1){
			JOptionPane.showMessageDialog(null,"Programa Conversor: 2023 - Desarrollado por Nayareth López Antonio - Alura Challenge ONE G5");
		}
		
	}

}
