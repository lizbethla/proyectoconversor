package proyectoconversor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import proyectoconversor.modelo.Proceso;
import proyectoconversor.modelo.FactorConversion;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

/**
 * 
 * @author NLA
 *
 */
public class VistaTasaCambio extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable table;
	private JButton btnGuardar;
	private JButton btnSalir;
	private JFormattedTextField frmTxtFecha;
	private Date fechaActual;
	private String fecha;
	private String filePath;


	public VistaTasaCambio() {
		setTitle("TASA DE CAMBIO");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(350, 292);
		setLocationRelativeTo(null);
		iniciarComponentes();		
	}

	private void iniciarComponentes() {
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(36, 23, 46, 14);
		contentPane.add(lblFecha);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(72, 213, 89, 23);
		btnGuardar.addActionListener(this);
		contentPane.add(btnGuardar);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(187, 213, 89, 23);
		btnSalir.addActionListener(this);
		contentPane.add(btnSalir);

		frmTxtFecha = new JFormattedTextField();
		frmTxtFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		frmTxtFecha.setEditable(false);
		frmTxtFecha.setBounds(110, 20, 102, 20);
		contentPane.add(frmTxtFecha);

		fechaActual = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		fecha = sdf.format(fechaActual);
		frmTxtFecha.setText(fecha);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 68, 254, 108);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { "DOLAR USA", null }, { "EURO", null },
				{ "LIBRA ESTERLINA", null }, { "YEN JAPONES", null }, }, new String[] { "Moneda", "Tasa de Cambio" }) {
			Class[] columnTypes = new Class[] { String.class, Double.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(96);
		table.getColumnModel().getColumn(1).setPreferredWidth(119);
		scrollPane.setViewportView(table);
		table.requestFocus();
		table.changeSelection(0, 1, false, false);
		
		cargarDatos();
	}

	private void cargarDatos() {
		
		filePath = "./tasacambio.csv";
		File file = new File(filePath);
		String moneda = "";

		if (Proceso.isFileExists(file)) {
			ArrayList<FactorConversion> lista1 = new ArrayList<FactorConversion>();
			try {
				lista1 = Proceso.leerArchivoCSV(filePath);
				for (FactorConversion itemTasaCambio : lista1) {
					if (frmTxtFecha.getText().equals(itemTasaCambio.getFecha())) {
						moneda = itemTasaCambio.getDescripcion();
						for (int i = 0; i < table.getRowCount(); i++) {
							if (moneda.equals(table.getValueAt(i, 0))) {
								table.setValueAt(itemTasaCambio.getValorConversion(), i, 1);
								break;
							}
						}
					}
				}
			}catch (IOException e1) {
				e1.printStackTrace();
			}
		}			
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnGuardar){
			
			List<FactorConversion> lista = new ArrayList<>();
			table.editCellAt(0, 0);
			lista = Proceso.listaTasaCambioMoneda(table, fecha);

			Scanner sc = new Scanner(System.in);
			File f = new File(filePath);
			try (FileWriter fw = new FileWriter(f)) {
				for (FactorConversion tasacambio : lista) {
			 		 fw.write(tasacambio.toCSV() + "\n");
				}
			}catch (Exception exc) {
				System.out.println("Se ha producido un error");
			}
		}
		
		
		if(e.getSource() == btnSalir){
			dispose();
		}
		
	}
}
