package parque;
import java.sql.Connection;
import java.util.*;
import bbdd.*;
import clases.*;
public class parque {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String nombre="";
		int codigo=0,codigoAt=0,opc=0,acc=0,a=0, telefono=0,nss=0,aux=0,aux1=0;
		String apellidos="",direccion="",categoria="",horario="",dni="";
		boolean valida=false;

		Vector <personal> listaTaquilleros;
		Vector <atraccion> lista;
		/**
		 * PROBANDO GITHUB
		 */
		personal pe=null;
		administrador ad=null;
		entrada en=null;
		taquillero ta=null;
		atraccion at=null;

		BaseDatosC mibase=new BaseDatosC("mysql-properties.xml");

		do{
			System.out.println("");
			System.out.println("Sistema de gesti�n del parque de atracciones");
			System.out.println("------################################------");
			System.out.print("Introduzca nombre de usuario: ");
			try{
				nombre=sc.nextLine();
				System.out.print("Introduzca c�digo de empleado: ");
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
					System.out.print("Introduce tu id de administrador para continuar: ");
					String id=sc.nextLine();
					mibase.abrir();
					acc=BBDDAdministrador.comprobarId(id,mibase.getConexion());
					mibase.cerrar();
					if (acc==1){
						do{
							System.out.println("");
							System.out.println("--------------------------");
							System.out.println("MENU DE GESTI�N DEL PARQUE");
							System.out.println("--------------------------");
							System.out.println("Introduce opci�n:");
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
										System.out.println("Introduce direcci�n: ");
										direccion=sc.nextLine();
										categoria="Taquillero";
										System.out.println("Introduce horario (L � M � X etc) :");
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
									System.out.println("Empleado a�adido correctamente.");
									break;

								case 2:
									mibase.abrir();
									listaTaquilleros=BBDDPersonal.listarTaquilleros(pe,mibase.getConexion());
									mibase.cerrar();
									System.out.println("Lista de los empleados que se pueden eliminar: ");
									for (int i=0;i<listaTaquilleros.size(); i++){
										System.out.println(listaTaquilleros.get(i).toString());
									}
									try{
										System.out.println("");
										System.out.print("Introduce codigo de empleado: ");
										codigo=sc.nextInt();
										sc.nextLine();
										valida=false;
										for (int i = 0; i < listaTaquilleros.size(); i++) {
											if(listaTaquilleros.get(i).getCod_empleado()==codigo){
												mibase.abrir();
												BBDDTaquillero.eliminar(codigo, mibase.getConexion());
												mibase.cerrar();
												valida=true;
												break;
											}	
										}

										if (valida=false){
											System.out.println("El empleado con c�digo n� "+codigo+" ha sido eliminado.");
											break;
										}
										System.out.println("El empleado no existe en la base de datos.");
									}catch(InputMismatchException e){
										System.err.print(e.getMessage());
									}
									break;
								}
							case 2:
								mibase.abrir();
								lista=BBDDAtraccion.listar(at, mibase.getConexion());
								mibase.cerrar();
								System.out.println("");
								System.out.println("Listado de todas las atracciones del parque: ");
								for (int i = 0; i < lista.size(); i++) {
									System.out.println(lista.get(i).toString());
								}
								System.out.println("");
								System.out.println("1. Revisar atracci�n.");
								System.out.println("2. Cerrar atracci�n.");
								System.out.println("0. Salir.");
								opc=sc.nextInt();
								switch(opc){
								case 1:
									System.out.println("Listado de las atracciones pendientes por revisar: ");
									for (int i = 0; i < lista.size(); i++) {
										if(lista.get(i).getFh_revision().equals("noRev")){
											System.out.println(lista.get(i).toString());
											aux++;
										}
									}
									if(aux==0)
										System.out.println("Todas las atracciones est�n revisadas.");
									System.out.println("");
									System.out.print("Introduzca c�digo de atracci�n a revisar: ");
									codigoAt=sc.nextInt();
									sc.nextLine();
									aux=0;
									for (int i = 0; i < lista.size(); i++) {
										if(lista.get(i).getCod_atraccion()==codigoAt)
											aux1++;
									}
									if(aux1==0){
										System.out.println("No existe ninguna atracci�n con ese codigo.");
										break;
									}
									else{
										aux1=0;
										for (int i = 0; i < lista.size(); i++) {
											if(lista.get(i).getFh_revision().equals("noRev") && lista.get(i).getCod_atraccion()==codigoAt){
												aux=i;
											}
											if(!lista.get(i).getFh_revision().equals("noRev") && lista.get(i).getCod_atraccion()==codigoAt){
												aux1=i;
											}
										}
										if(aux1!=0){
											opc=0;
											System.out.println("La atracci�n "+lista.get(aux1).getNom_atraccion()+" fue revisada el: "+lista.get(aux1).getFh_revision());
											do{
												System.out.println("�Desea volver a revisar la atracci�n?");
												System.out.println("1.S�");
												System.out.println("2.No");
												opc=sc.nextInt();
												sc.nextLine();
											}while(opc!=1 || opc!=2);
											if(opc==2)
												break;
											else{
												mibase.abrir();
												BBDDAtraccion.revisar(id, codigoAt, mibase.getConexion());
												mibase.cerrar();
												System.out.println("La atracci�n se ha vuelto a revisar satisfactoriamente");
												break;
											}

										}
										if(aux!=0){
											System.out.println("Se va a revisar la atracci�n:_ "+lista.get(aux).getNom_atraccion());
											mibase.abrir();
											BBDDAtraccion.revisar(id,codigoAt,mibase.getConexion());
											mibase.cerrar();
											System.out.println("Atracci�n revisada correctamente.");
										}
									}
									break;
								case 2:
									break;
								}
								break;
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
