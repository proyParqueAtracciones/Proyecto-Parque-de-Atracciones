package clases;

public class revision {
	private String fh_inicio_rev;
	private String fh_fin_rev;
	private String observaciones;
	private int cod_atraccion;
	private String id_administrador;
	
	public revision(String fh_inicio_rev, String fh_fin_rev, String observaciones, int cod_atraccion,
			String id_administrador) {
		this.fh_inicio_rev = fh_inicio_rev;
		this.fh_fin_rev = fh_fin_rev;
		this.observaciones = observaciones;
		this.cod_atraccion = cod_atraccion;
		this.id_administrador = id_administrador;
	}

	public String getFh_inicio_rev() {
		return fh_inicio_rev;
	}

	public String getFh_fin_rev() {
		return fh_fin_rev;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public int getCod_atraccion() {
		return cod_atraccion;
	}

	public String getId_administrador() {
		return id_administrador;
	}
	
	
}
