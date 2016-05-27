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
	
	public static void nuevaEntrada(entrada en, Connection c){
		String cadena="INSERT INTO entrada VALUES('"+en.getNumentrada()+", SYSDATE,"+en.getPrecio()+","+en.getTipo()+","+en.getCod_taquillero()+"')";
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

