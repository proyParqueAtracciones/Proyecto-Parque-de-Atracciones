package bbdd;
import clases.personal;
import java.sql.*;
import java.util.*;
public class BBDDPersonal {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;

	/**
	 * Método que busca un empleado en la BBDD. Recibe cod_emple y el nombre.
	 * 
	 * @param codigo Codigo de personal
	 * @param nombre	Nombre de empleado
	 * @param c	Conexion a BBDD
	 * @return	Devuelve 1 en caso de encontrar el empleado o 0 en caso contrario.
	 */
	public static int listar (int codigo,String nombre, Connection c){
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
	/**
	 * Método que lista al personal del parque. Recibe el objeto personal.
	 * @param pe	Objeto personal
	 * @param c	Conexión a BBDD
	 * @return	Devuelve la lista con los objetos personal que ha recibido de la BBDD en caso de encontrar o null en caso contrario.
	 */
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
