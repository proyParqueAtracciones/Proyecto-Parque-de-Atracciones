package clases;
public class taquillero extends personal {
	private String horario;
	
	


	public taquillero(int cod_empleado, String nombre, String apellidos, String dni, int telefono, int nss,
			String direccion, String categoria, String horario) {
		super(cod_empleado, nombre, apellidos, dni, telefono, nss, direccion, categoria);
		this.horario=horario;
	}




	public String getHorario() {
		return horario;
	}

	
}
