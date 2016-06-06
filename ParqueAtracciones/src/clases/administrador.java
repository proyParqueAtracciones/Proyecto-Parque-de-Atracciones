package clases;

import bbdd.BBDDAtraccion;
import bbdd.BBDDRevisiones;
import bbdd.BaseDatosC;
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

	public static void realizarRevisión(String id, int codigo, String motivo,  BaseDatosC mibase){
		mibase.abrir();
		BBDDAtraccion.revisar(id, codigo, mibase.getConexion());
		BBDDRevisiones.realizarRevision(id, codigo, motivo,mibase.getConexion());
		mibase.cerrar();
	}
}
