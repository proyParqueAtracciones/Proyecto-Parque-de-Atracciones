package clases;

public class entrada {
	private String fh_emision;
	private int precio;
	private String tipo;
	private int cod_taquillero;
	public entrada( String fh_emision, int precio, String tipo, int cod_taquillero) {
		this.fh_emision = fh_emision;
		this.precio = precio;
		this.tipo = tipo;
		this.cod_taquillero = cod_taquillero;
	}

	public String getFh_emision() {
		return fh_emision;
	}
	public int getPrecio() {
		return precio;
	}
	public String getTipo() {
		return tipo;
	}
	public int getCod_taquillero() {
		return cod_taquillero;
	}


}
