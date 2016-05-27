package bbdd;
import java.sql.*;
import clases.entrada;
public class BBDDEntrada {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;
	
	public static void nuevaEntrada(entrada en, Connection c){
		String cadena="INSERT INTO entrada VALUES('"+en.getNumentrada()+","+en.getFh_emision()+","+en.getPrecio()+","+en.getTipo()+","+en.getCod_taquillero()+"')";
		
	}
}
