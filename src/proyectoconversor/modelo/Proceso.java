package proyectoconversor.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTable;
/**
 * 
 * @author NLA
 *
 */
public class Proceso {
	
	public static boolean isFileExists(File file) {
		return file.isFile();
	}

	public static ArrayList<FactorConversion> listaTasaCambioMoneda(JTable table, String fecha) {
		ArrayList<FactorConversion> lista = new ArrayList<FactorConversion>();
		String moneda;
		double tasaCambio;
		FactorConversion tasacambiomoneda;

		for (int i = 0; i < table.getRowCount(); i++) {
			moneda = (String) table.getValueAt(i, 0);

			if (table.getValueAt(i, 1) == null || table.getValueAt(i, 1) == "") {
				tasaCambio = 0;
			} else {
				tasaCambio = (double) table.getValueAt(i, 1);
			}
			tasacambiomoneda = new FactorConversion(fecha, moneda, tasaCambio);
			lista.add(tasacambiomoneda);
		}

		return lista;
	}
	
	
	public static ArrayList<FactorConversion> leerArchivoCSV(String path) throws IOException {

		BufferedReader bufferLectura = new BufferedReader(new FileReader(path));
		String linea = bufferLectura.readLine();

		ArrayList<FactorConversion> lista = new ArrayList<FactorConversion>();
		FactorConversion tasacambio;

		while (linea != null) {
			String[] campos = linea.split(String.valueOf(","));
			tasacambio = new FactorConversion(campos[0], campos[1], Double.parseDouble(campos[2]));
			lista.add(tasacambio);
			linea = bufferLectura.readLine();
		}

		if (bufferLectura != null) {
			bufferLectura.close();
		}
		return lista;
	}
	
	
    public static boolean isNumeric(String cadena) {
        boolean resultado;

        try {
            Double.parseDouble(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }

}
