package bbdd;
import java.sql.*;
import clases.entrada;
public class BBDDEntrada {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;
	
	/*
	 * Con este método alteramos la fecha para que aparezca además la hora
	 */
	public static void formato_fecha(entrada en, Connection c){
		String cadena="ALTER SESSION SET NLS_DATE_FORMAT='DD/MM/YYYY HH24:MI'";
		try{
			s=c.createStatement();
			s.executeQuery(cadena);
			s.close();
			}
			catch ( SQLException e){
				System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Método para vender una entrada, recibe los datos de la entrada, así como el
	 * código del empleado/taquillero que la ha vendido y lo inserta en la base de datos
	 */
	
	public static void nuevaEntrada(int codigo,double precio, Connection c){
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

