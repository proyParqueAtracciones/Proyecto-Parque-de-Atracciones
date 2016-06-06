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

	/**
	 * M�todo que inserta una nueva fila en las tablas personal y taquillero siguiendo el �rden l�gico; primero en personal y luego en taquillero.
	 * 
	 * @param ta Objeto taquillero
	 * @param c Conexion a BBDD
	 */
	public static void anadir(taquillero ta, Connection c){
		String cadena="INSERT INTO personal VALUES('"+ta.getCod_empleado()+"','"+ta.getNombre()+"','"+ta.getApellidos()+"','"+ta.getDni()+"','"+ta.getTelefono()+"','"+ta.getNss()+"','"+ta.getDireccion()+"','"+ta.getCategoria()+"')";
		String cadena1="INSERT INTO taquillero VALUES('"+ta.getCod_empleado()+"','"+ta.getHorario()+"')";
		try{
			s=c.createStatement();
			s.executeUpdate(cadena);
			s.executeUpdate(cadena1);
			s.close();
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
		}
	}
	/** 
	 * M�todo que elimina un empleado de la BBDD. Recibe cod_emple y elimina seg�n el �rden l�gico; primero de la tabla taquillero y desp�es de la tabla personal.
	 * El m�todo encontraba problemas con MySQL por un par�metro de este. FOREING_KEY_CHEKS comprueba que no se borren claves for�neas de una tabla.
	 * Aun que la eliminaci�n se haga siguiendo el �rden l�gico, MySQL no lo permite.
	 * Se soluciona modificando el par�metro antes de la modificaci�n.
	 * @param codigo C�digo de empleado a eliminar.
	 * @param c	Conexion a BBDD
	 */
	public static void eliminar(int codigo, Connection c){

		String cadena="DELETE FROM personal WHERE cod_empleado="+codigo+"";
		String cadena1="DELETE FROM taquillero WHERE cod_empleado="+codigo+"";
		String cadena0="SET FOREIGN_KEY_CHECKS=0";
		try{
			s=c.createStatement();
			s.executeUpdate(cadena0);
			s.executeUpdate(cadena1);
			s.executeUpdate(cadena);
			s.close();
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
		}
	}

}


