package bbdd;
import java.sql.*;
import java.util.*;

import clases.*;

public class BBDDAdministrador {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;
	
	public static int listar (personal pe,int codigo, Connection c){
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
	
	public static void generarId(administrador ad,int codigo, Connection c){
		String cadena="UPDATE administrador SET id_administrador='"+"a"+"'||cod_empleado WHERE cod_empleado="+codigo+"";
	
		try{
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			s.close();
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
		}
	}
}
