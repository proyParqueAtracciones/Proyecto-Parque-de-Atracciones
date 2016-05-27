package clases;
public class personal {
	private int cod_empleado;
	private String nombre;
	private String apellidos;
	private String dni;
	private int telefono;
	private int nss;
	private String direccion;
	private String categoria;
	protected static int cont;
	
	public personal(int cod_empleado, String nombre, String apellidos, String dni, int telefono, int nss,
			String direccion, String categoria) {
		this.cod_empleado = cont;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
		this.nss = nss;
		this.direccion = direccion;
		this.categoria = categoria;
		cont++;
	}

	public int getCod_empleado() {
		return cod_empleado;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getDni() {
		return dni;
	}

	public int getTelefono() {
		return telefono;
	}

	public int getNss() {
		return nss;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getCategoria() {
		return categoria;
	}
	
	
}
