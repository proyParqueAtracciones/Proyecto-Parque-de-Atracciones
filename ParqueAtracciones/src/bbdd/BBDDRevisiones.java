package bbdd;
import java.sql.*;
import clases.revision;;
public class BBDDRevisiones {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;

	/**
	 * M�todo que actualiza la tabla revisiones en la BBDD con los campos id_administrador, cod_atraccion y observaciones de la revisi�n.
	 * El m�todo encontraba problemas con MySQL por un par�metro de este. FOREING_KEY_CHEKS comprueba que no se borren claves for�neas de una tabla.
	 * Aun que la eliminaci�n se haga siguiendo el �rden l�gico, MySQL no lo permite.
	 * Se soluciona modificando el par�metro antes de la modificaci�n.
	 * 
	 * @param idAd	Identificador del administrador que la revisa
	 * @param codAt	C�digo de la atracci�n revisada
	 * @param obs	Observaciones sobre la revisi�n
	 * @param c	Conexi�n a BBDD
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
