package bbdd;
import java.sql.*;
import java.util.*;

import clases.*;

public class BBDDAdministrador {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;


	/**
	 * Método que lista los empleados de la tabla administrador con el codigo que recibe
	 * @param codigo codigo de empleado
	 * @param c	conexion a la BBDD
	 * @return	devuelve 1 en caso de encontrar algun empleado o 0 en caso de no encontrarlo
	 */
	public static int listar (int codigo, Connection c){
		String cadena="SELECT cod_empleado FROM administrador where cod_empleado="+codigo+"";
		try{
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if (reg.next())
				return 1;
			s.close();
			return 0;

		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	/**
	 * Método que comprueba el campo id_administrador en la tabla administrador
	 * @param id	identificador de administrador(unico)
	 * @param c		Conexion a la BBDD
	 * @return		Devuelve 1 en caso de encontrar el administrador o 0 en caso contrario.
	 */
	public static int comprobarId(String id, Connection c){
		String cadena="SELECT id_administrador FROM administrador WHERE id_administrador='"+id+"'";
		try{
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if (reg.next()){
				s.close();
				return 1;
			}
			s.close();
			return 0;
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
}
