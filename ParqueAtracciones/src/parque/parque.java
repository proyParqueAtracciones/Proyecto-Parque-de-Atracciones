package parque;
import java.sql.Connection;
import java.util.*;
import bbdd.*;
import clases.*;
public class parque {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String nombre="";
		int codigo=0;
		int a=0;
		personal pe=null;
		administrador ad=null;
		BaseDatosC mibase=new BaseDatosC("mysql-properties.xml");

		do{
			System.out.println("############################################");
			System.out.println("Sistema de gestión del parque de atracciones");
			System.out.println("------################################------");
			System.out.print("Introduzca nombre de usuario: ");
			try{
				nombre=sc.nextLine();
				System.out.print("Introduzca código de empleado: ");
				codigo=sc.nextInt();
				sc.nextLine();
			}catch(InputMismatchException e){
				System.err.print(e.getMessage());
			}
			mibase.abrir();
			a=BBDDPersonal.listar(pe, codigo, nombre, mibase.getConexion());
			mibase.cerrar();
			if(a!=0){
				System.out.println("Login correcto.");
				
				mibase.abrir();
				a=BBDDAdministrador.listar(ad, codigo, mibase.getConexion());
				BBDDAdministrador.generarId(ad,codigo,mibase.getConexion());
				mibase.cerrar();
				if(a==1){
					System.out.println("ERES ADMIN");
					
				}
			}
			else
				System.out.println("LOGIN INCORRECTO. VUELVE A INTENTARLO.");
		}while(a==0);
	}
}
