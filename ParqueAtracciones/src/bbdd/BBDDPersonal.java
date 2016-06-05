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
	
	public static Vector<personal> listarPersonal(personal pe, Connection c){
		String cadena="SELECT * FROM personal";
		Vector<personal> listaPersonal=new Vector<personal>();
		try{
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			while(reg.next()){
				pe= new personal (reg.getInt("cod_empleado"),reg.getString("nombre"),reg.getString("apellidos"),reg.getString("dni"),reg.getInt("telefono"),reg.getInt("nss"),reg.getString("direccion"),reg.getString("categoria"));
				listaPersonal.add(pe);
			}
			s.close();
			return listaPersonal;
				
		}catch (SQLException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
}
