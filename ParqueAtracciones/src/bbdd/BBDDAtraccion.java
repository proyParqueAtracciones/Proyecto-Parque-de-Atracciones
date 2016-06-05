package bbdd;

import java.sql.*;
import java.util.Vector;

import clases.atraccion;

public class BBDDAtraccion {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;

	public static Vector<atraccion> listar(atraccion at, Connection c){
		String cadena="SELECT * FROM atracciones";
		Vector<atraccion> lista=new Vector<atraccion>();
		try{
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			while(reg.next()){
				at= new atraccion (reg.getInt("cod_atraccion"),reg.getString("nom_atraccion"),reg.getString("fh_revision"),reg.getString("id_administrador"));
				lista.add(at);
			}
			s.close();
			return lista;

		}catch (SQLException e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static void revisar (String id, int codAt, Connection c){
		String cadena="UPDATE atracciones SET  id_administrador='"+id+"' WHERE cod_atraccion="+codAt+"";
		try{
			s=c.createStatement();
			s.executeUpdate(cadena);
			s.close();
		}
		catch ( SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void cerrar(int codAt,String nomAt, Connection c){
		nomAt=nomAt+" [CERRADA]";
		String cadena="UPDATE atracciones SET nom_atraccion='"+nomAt+"' WHERE cod_atraccion="+codAt+"";
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



