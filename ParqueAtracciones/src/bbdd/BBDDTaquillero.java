package bbdd;
import java.sql.*;
import java.sql.Date;

import clases.*;
import java.util.*;
import java.text.*;

public class BBDDTaquillero {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;
	
	public static void anadir(taquillero ta, Connection c){
		String cadena="INSERT INTO taquillero VALUES ("+ta.getCod_empleado()+",'"+ta.getNombre()+"','"+ta.getApellidos()+"','"+ta.getDni()+"',"+ta.getTelefono()+","+ta.getNss()+",'"+ta.getDireccion()+"','"+ta.getCategoria()+"')";
		
		try{
			s=c.createStatement();
			s.executeUpdate(cadena);
			s.close();
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
}
	

