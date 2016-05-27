package clases;

public class administrador extends personal {
	private String id_administrador;

	public administrador(int cod_empleado, String nombre, String apellidos, String dni, int telefono, int nss,
			String direccion, String categoria) {
		super(cod_empleado, nombre, apellidos, dni, telefono, nss, direccion, categoria);
		this.id_administrador="a"+cod_empleado;
	}

	public String getId_administrador() {
		return id_administrador;
	}
	
	
}
