package bbdd;
import java.sql.*;
import clases.*;
import java.util.*;
import java.text.*;

public class BBDDTaquillero {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;
	//public Vector<taquillero> lista;
	
	private static void venderEntrada(entrada en, int codigo, double precio, Connection c){
		String tipo="";
		if(precio==0)
			tipo="empleado";
		if(precio<15)
			tipo="descuento";
		if(precio==15)
			tipo="paseo";
		if(precio==20)
			tipo="corriente";
		DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		
		/*
		String cadena="INSERT INTO entrada VALUES('"+en.getNumentrada()+"','"+hourdateFormat+"','"+precio+"','"+tipo+"','"+codigo+"')";

		try{
			s=c.createStatement();
			s.executeUpdate(cadena);
			s.close();
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
		}*/
	}
	
}
