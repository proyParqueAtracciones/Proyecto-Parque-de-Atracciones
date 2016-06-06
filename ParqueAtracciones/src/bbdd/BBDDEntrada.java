package bbdd;
import java.sql.*;
import clases.entrada;
public class BBDDEntrada {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;


	/**
	 * Método que inserta una nueva entrada en la BBDD. El propio método calcula el tipo de entrada. Recibe cod_empleado y precio.
	 * La fh_emisión se calcula en la bbdd
	 * @param codigo	Codigo de empleado que vende la entrada
	 * @param precio	Precio de la entrada
	 * @param c	Conexion a BBDD
	 */

	public static void nuevaEntrada(int codigo,int precio, Connection c){
		String tipo="";
		if(precio==0)
			tipo="Personal";
		if(precio==10)
			tipo="Paseo";
		if(precio==15)
			tipo="Descuento";
		if(precio==20)
			tipo="Normal";
		String cadena="INSERT INTO entrada (precio,tipo,cod_taquillero) VALUES("+precio+",'"+tipo+"',"+codigo+")";
		try{
			s=c.createStatement();
			s.executeUpdate(cadena);
			s.close();
		}
		catch ( SQLException e){
			System.out.println(e.getMessage());
		}
	}
}

