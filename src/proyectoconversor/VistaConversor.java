package proyectoconversor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import proyectoconversor.modelo.Proceso;
import proyectoconversor.modelo.FactorConversion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * 
 * @author NLA
 *
 */
public class VistaConversor extends JFrame implements ActionListener, ItemListener, KeyListener{

	private JPanel contentPane;
	private JTextField txtCantidad;
	private JButton btnConvertir;
	private JComboBox cmbBoxItemsConversion;
	private JComboBox cmbBoxValoresConversion;
	private JFormattedTextField frmTxtResultado;
	private JButton btnSalir;
	private int opVentana;
	private String opcion;
	private String nombreVentana;
	private String titulo;
	private String archivo;
	private boolean noExisteArchivo;
	private boolean noTasaCambio;
	

	public VistaConversor(int opVentana, String opcion, String nombreVentana, String titulo, String archivo) {
		this.opVentana = opVentana;
		this.opcion = opcion;
		this.nombreVentana = nombreVentana;
		this.titulo = titulo;
		this.archivo = archivo;
		
		setTitle(this.nombreVentana);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(530, 325);
		setLocationRelativeTo(null);
		iniciarComponentes();
	}

	private void iniciarComponentes() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel(this.titulo);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, 494, 37);
		contentPane.add(lblTitulo);

		JLabel lblCantidad = new JLabel("Cantidad :");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCantidad.setBounds(39, 76, 105, 20);
		contentPane.add(lblCantidad);

		txtCantidad = new JTextField();
		txtCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		txtCantidad.setBounds(104, 76, 154, 20);
		txtCantidad.addKeyListener(this);
		contentPane.add(txtCantidad);
		
		frmTxtResultado = new JFormattedTextField();
		frmTxtResultado.setFocusLostBehavior(JFormattedTextField.COMMIT);
		frmTxtResultado.setHorizontalAlignment(SwingConstants.CENTER);
		frmTxtResultado.setOpaque(true);
		frmTxtResultado.setEditable(false);
		frmTxtResultado.setBounds(296, 76, 154, 20);
		contentPane.add(frmTxtResultado);
		
		JLabel lbltextoDe = new JLabel("DE");
		lbltextoDe.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbltextoDe.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltextoDe.setBounds(39, 126, 46, 14);
		contentPane.add(lbltextoDe);

		JLabel lbltextoA = new JLabel("A");
		lbltextoA.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbltextoA.setBounds(268, 126, 46, 14);
		contentPane.add(lbltextoA);

		JLabel lblFlecha = new JLabel("=>");
		lblFlecha.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFlecha.setHorizontalAlignment(SwingConstants.LEFT);
		lblFlecha.setBounds(266, 76, 46, 20);
		contentPane.add(lblFlecha);
	
		cmbBoxItemsConversion = new JComboBox();
		cmbBoxItemsConversion.setBounds(104, 122, 154, 22);
		cmbBoxItemsConversion.addItemListener(this);
		contentPane.add(cmbBoxItemsConversion);

		JLabel lblASolesS = new JLabel(this.opcion);
		lblASolesS.setHorizontalAlignment(SwingConstants.CENTER);
		lblASolesS.setBackground(SystemColor.inactiveCaption);
		lblASolesS.setOpaque(true);
		lblASolesS.setBounds(296, 122, 154, 22);
		contentPane.add(lblASolesS);

		if (this.opVentana == 2) {
			lblASolesS.setBounds(104, 122, 154, 22);
			cmbBoxItemsConversion.setBounds(296, 122, 154, 22);
		}

		cmbBoxValoresConversion = new JComboBox();
		cmbBoxValoresConversion.setBounds(521, 75, 62, 22);
		contentPane.add(cmbBoxValoresConversion);
		cmbBoxValoresConversion.setVisible(false);
		
		btnConvertir = new JButton("Convertir");
		btnConvertir.setBounds(151, 235, 89, 23);
		btnConvertir.addActionListener(this);
		contentPane.add(btnConvertir);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(297, 235, 89, 23);
		btnSalir.addActionListener(this);
		contentPane.add(btnSalir);

		cargarDatos();	

	}

	private void cargarDatos() {
		
		File file = new File(archivo);
		Date fechaActual = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(fechaActual);
			
		if (Proceso.isFileExists(file)) {
			
			try {
				ArrayList<FactorConversion> lista = new ArrayList<FactorConversion>();
				lista = Proceso.leerArchivoCSV(this.archivo);				
				
				for (FactorConversion item : lista) {
					cmbBoxItemsConversion.addItem(item.getDescripcion());					
					
					if (item.getFecha().equals(fecha) || item.getFecha().equals("01/01/1900") ) {
						cmbBoxValoresConversion.addItem("" + item.getValorConversion());
					}else {
						noTasaCambio = true;							
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			noExisteArchivo=true;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnConvertir){
			
			if (noExisteArchivo) {
				JOptionPane.showMessageDialog(null, "Por favor, verificar archivo con los Factores de Conversión");
				return;
			}
			
			if (noTasaCambio) {
				JOptionPane.showMessageDialog(null, "Por favor, verificar que se haya registrado la Tasa de Cambio en la opción Nuevo.");
				return;
			}

			if (txtCantidad.getText() == null || txtCantidad.getText().trim() == "" || txtCantidad.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingresar la cantidad a convertir.");
				txtCantidad.requestFocus();
			} else {
				    frmTxtResultado.setText("");
			        if (Proceso.isNumeric(txtCantidad.getText())) {					        
						Double cantidad = Double.parseDouble(txtCantidad.getText());
						cmbBoxValoresConversion.setSelectedIndex(cmbBoxItemsConversion.getSelectedIndex());
						Double tasaCambio = Double.parseDouble((String) cmbBoxValoresConversion.getSelectedItem());
						Double cantidadConvertida = 0.0;
						if (opVentana == 2) {
							if (tasaCambio > 0) cantidadConvertida = cantidad / tasaCambio;						
						} else {
							cantidadConvertida = cantidad * tasaCambio;
						}
						
						DecimalFormat formato = new DecimalFormat("#,###.00");	
						if(cantidadConvertida < 1) {
						   formato = new DecimalFormat("#,##0.000000");	
						}
						
						String valorFormateado = formato.format(cantidadConvertida);
						frmTxtResultado.setText(valorFormateado);				  
			        }else {
			        	JOptionPane.showMessageDialog(null, "Por favor, verificar la cantidad ingresada debe ser número.");
			        	txtCantidad.requestFocus();
			        }
			}

		}
		
		if(e.getSource() == btnSalir){
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int respuesta = JOptionPane.showConfirmDialog(null,"¿Desea salir del " + nombreVentana.toLowerCase() + "?","Mensaje",dialogButton);
		    if (respuesta == 0) dispose();
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == cmbBoxItemsConversion){
		  frmTxtResultado.setText("");
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getSource() == txtCantidad){
			char caracter = e.getKeyChar();
			if (((caracter < '0') || (caracter > '9')) && (caracter != '.') && (caracter != '\b')) {
				e.consume(); // ignorar el evento de teclado
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub		
	}
}
