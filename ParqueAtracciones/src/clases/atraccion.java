package clases;

public class atraccion {
	private int cod_atraccion;
	private String nom_atraccion;
	private String fh_revision;
	private String id_administrador;
	public atraccion(int cod_atraccion, String nom_atraccion, String fh_revision, String id_administrador) {
		this.cod_atraccion = cod_atraccion;
		this.nom_atraccion = nom_atraccion;
		this.fh_revision = fh_revision;
		this.id_administrador = id_administrador;
	}


	public int getCod_atraccion() {
		return cod_atraccion;
	}


	public String getNom_atraccion() {
		return nom_atraccion;
	}


	public String getFh_revision() {
		return fh_revision;
	}


	public String getId_administrador() {
		return id_administrador;
	}


	@Override
	public String toString() {
		return "[" + cod_atraccion + ", " + nom_atraccion + ", " + fh_revision + ", "
				+ id_administrador+ "]";
	}



}
