package clases;

public class entrada {
	private int num_entrada;
	private String fh_emision;
	private double precio;
	private String tipo;
	private int cod_taquillero;
	private static int cont;
	public entrada(int num_entrada, String fh_emision, double precio, String tipo, int cod_taquillero) {
		this.num_entrada = cont;
		this.fh_emision = fh_emision;
		this.precio = precio;
		this.tipo = tipo;
		this.cod_taquillero = cod_taquillero;
		cont++;
	}
	
	public int getNumentrada() {
		return num_entrada;
	}
	public String getFh_emision() {
		return fh_emision;
	}
	public double getPrecio() {
		return precio;
	}
	public String getTipo() {
		return tipo;
	}
	public int getCod_taquillero() {
		return cod_taquillero;
	}
	
	
}
