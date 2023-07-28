package proyectoconversor.modelo;

/**
 * 
 * @author NLA
 *
 */
public class FactorConversion {
	private String fecha;
	private String descripcion;
	private double valorConversion;

	public FactorConversion(String fecha, String descripcion, double valorConversion) {
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.valorConversion = valorConversion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getValorConversion() {
		return valorConversion;
	}

	public void setValorConversion(double valorConversion) {
		this.valorConversion = valorConversion;
	}

	public String toCSV() {
		return this.fecha + "," + this.descripcion + "," + this.valorConversion;
	}
}
