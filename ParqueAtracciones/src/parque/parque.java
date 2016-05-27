package parque;
import java.sql.Connection;
import java.util.*;
import bbdd.*;
import clases.*;
public class parque {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String nombre="";
		int codigo=0,opc=0,acc=0,a=0, telefono=0,nss=0;
		String apellidos="",direccion="",categoria="",horario="",dni="";
		
		Vector <personal> listaTaquilleros;
		
		personal pe=null;
		administrador ad=null;
		entrada en=null;
		taquillero ta=null;
		
		BaseDatosC mibase=new BaseDatosC("mysql-properties.xml");

		do{
			System.out.println("");
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
				mibase.cerrar();
				if(a==1){
					System.out.println("Introduce tu id de administrador para continuar: ");
					String id=sc.nextLine();
					mibase.abrir();
					acc=BBDDAdministrador.comprobarId(id,mibase.getConexion());
					mibase.cerrar();
					if (acc==1){
						do{
							System.out.println("");
							System.out.println("--------------------------");
							System.out.println("MENU DE GESTIÓN DEL PARQUE");
							System.out.println("--------------------------");
							System.out.println("Introduce opción:");
							System.out.println("1.Administrar empleados.");
							System.out.println("2.Revisar atracciones.");
							System.out.println("3.Recaudar entradas.");
							System.out.println("0.SALIR.");
							opc=sc.nextInt();
							sc.nextLine();
							switch(opc){
							case 1:
								opc=0;
								System.out.println("1.Dar de alta nuevo empelado (taquillero).");
								System.out.println("2.Dar de baja empleado (taquillero).");
								opc=sc.nextInt();
								sc.nextLine();
								switch(opc){
								case 1:
									try{
										System.out.println("Introduce codigo de empleado: ");
										codigo=sc.nextInt();
										sc.nextLine();
										System.out.println("Introduce nombre de empleado: ");
										nombre=sc.nextLine();
										System.out.println("Introduce apellidos: ");
										apellidos=sc.nextLine();
										System.out.println("Introduce DNI: ");
										dni=sc.nextLine();
										System.out.println("Introduce telefono: ");
										telefono=sc.nextInt();
										System.out.println("Introduce num. seguridad social: ");
										nss=sc.nextInt();
										sc.nextLine();
										System.out.println("Introduce dirección: ");
										direccion=sc.nextLine();
										categoria="Taquillero";
										System.out.println("Introduce horario (L,M,X,J,V,S,D) :");
										horario=sc.nextLine();
									}
									catch(InputMismatchException e){
										System.err.print(e.getMessage());
									}
									catch(NoSuchElementException e){
										System.err.print(e.getMessage());
									}

									ta= new taquillero(codigo,nombre,apellidos,dni,telefono,nss,direccion,categoria,horario);
									mibase.abrir();
									BBDDTaquillero.anadir(ta,mibase.getConexion());
									mibase.cerrar();
									System.out.println("Empleado añadido correctamente.");
									break;
									
								case 2:
									listaTaquilleros=BBDDPersonal.listarTaquilleros(pe,mibase.getConexion());
									System.out.println("Lista de los empleados que se pueden eliminar: ");
									for (int i=0;i<listaTaquilleros.size(); i++){
										System.out.println(listaTaquilleros.get(i).toString());
									}
									try{
										System.out.println("Introduce codigo de empleado: ");
										
									}catch(InputMismatchException e){
										System.err.print(e.getMessage());
									}
								}
							}
						}while(opc!=0);
					}
					else
						System.out.println("ERROR. CODIGO INCORRECTO. SALIENDO.");
				}
				else{
					do{
						System.out.println("1.VENDER ENTRADA");
						if(opc==1){
							//BBDDEntrada.venderEntrada();
						}
						System.out.println("0.SALIR");
					}while(opc!=0);
				}
			}
			else
				System.out.println("LOGIN INCORRECTO. VUELVE A INTENTARLO.");
		}while(a==0);
	}
}
