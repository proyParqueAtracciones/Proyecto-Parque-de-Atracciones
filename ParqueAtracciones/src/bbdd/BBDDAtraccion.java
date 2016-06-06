package bbdd;

import java.sql.*;
import java.util.Vector;

import clases.atraccion;

public class BBDDAtraccion {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;

	/**
	 * 	M�todo que recibe un objeto atracci�n y lista las coincidencias en la BBDD
	 * @param at Objeto atraccion
	 * @param c	Conexion a la BBDD
	 * @return	Devuelve la lista con los objetos atraccion que ha encontrado en la BBDD o null si no ha encontrado ninguno.
	 */
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
	/**
	 * M�todo que recibe los atributos id_administrador y cod_atraccion y actualiza el campo id_administrador en la BBDD
	 * La fh_revision se calcula en la BBDD
	 * @param id	Identificador del administrador
	 * @param codAt	Codigo de la atraccion a revisar
	 * @param c	Conexion a la BBDD
	 */
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
	/**
	 * M�todo que recibe los atributos cod_atraccion y nom_atraccion para cerrar una atracci�n. Se modifica el nombre de la atraccion en la BBDD
	 * con el valor nom_atracci�n+[CERRADA]
	 * @param codAt	Codigo de la atraccion a cerrar
	 * @param nomAt	Nuevo nombre de la atraccion que se cerrar�
	 * @param c Conexion a BBDD
	 */
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



