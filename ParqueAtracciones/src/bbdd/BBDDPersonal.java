package bbdd;
import clases.personal;
import java.sql.*;
import java.util.*;
public class BBDDPersonal {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;


	public static int listar (personal pe,int codigo,String nombre, Connection c){
		String cadena="SELECT cod_empleado,nombre FROM personal where cod_empleado="+codigo+" AND nombre='"+nombre+"'";
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
}
