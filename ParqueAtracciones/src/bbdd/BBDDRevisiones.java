package bbdd;
import java.sql.*;
import clases.revision;;
public class BBDDRevisiones {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;

	/**
	 * Método que actualiza la tabla revisiones en la BBDD con los campos id_administrador, cod_atraccion y observaciones de la revisión.
	 * El método encontraba problemas con MySQL por un parámetro de este. FOREING_KEY_CHEKS comprueba que no se borren claves foráneas de una tabla.
	 * Aun que la eliminación se haga siguiendo el órden lógico, MySQL no lo permite.
	 * Se soluciona modificando el parámetro antes de la modificación.
	 * 
	 * @param idAd	Identificador del administrador que la revisa
	 * @param codAt	Código de la atracción revisada
	 * @param obs	Observaciones sobre la revisión
	 * @param c	Conexión a BBDD
	 */
	public static void realizarRevision(String idAd, int codAt,String obs, Connection c){
		String cadena="INSERT INTO revisiones(observaciones, cod_atraccion,id_administrador) VALUES ('"+obs+"',"+codAt+",'"+idAd+"')";
		String cadena0="SET FOREIGN_KEY_CHECKS=0";

		try{
			s=c.createStatement();
			s.executeUpdate(cadena0);
			s.executeUpdate(cadena);
			s.close();
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
		}
	}
}
