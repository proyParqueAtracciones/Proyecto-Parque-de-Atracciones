package parque;
import java.sql.Connection;
import java.util.*;
import bbdd.*;
import clases.*;
public class parque {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String nombre="";
		int codigo=0,codigoAt=0,opc=0,acc=0,a=0, telefono=0,nss=0,aux=0,aux1=0,s=0;
		String apellidos="",direccion="",categoria="",horario="",dni="",nombreAt="";
		boolean valida=false,salir=false;

		Vector <personal> listaPersonal;
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
					System.out.print("Introduce tu id de administrador para continuar: ");
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
										salir=false;
										System.out.println("Introduce codigo de empleado: ");
										codigo=sc.nextInt();
										sc.nextLine();
										mibase.abrir();
										listaPersonal=BBDDPersonal.listarPersonal(pe, mibase.getConexion());
										mibase.cerrar();
										for (int i = 0; i < listaPersonal.size(); i++) {
											if(listaPersonal.get(i).getCod_empleado()==codigo){
												System.out.println("ERROR. El empleado ya existe.");
												salir=true;
												break;
											}
										}
										if(salir==true){
											System.out.println("saliendo.");
											break;}
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
										System.out.println("Introduce horario (L ó M ó X etc) :");
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
									salir=false;
									mibase.abrir();
									listaPersonal=BBDDPersonal.listarPersonal(pe,mibase.getConexion());
									mibase.cerrar();
									System.out.println("");
									System.out.println("Lista de los empleados que se pueden eliminar: ");
									for (int i=0;i<listaPersonal.size(); i++){
										if(listaPersonal.get(i).getCategoria().equals("Taquillero"))
											System.out.println(listaPersonal.get(i).toString());
									}

									System.out.println("");
									System.out.print("Introduce codigo de empleado: ");
									codigo=sc.nextInt();
									sc.nextLine();

									for (int i = 0; i < listaPersonal.size(); i++) {
										if(listaPersonal.get(i).getCod_empleado()==codigo){
											if(listaPersonal.get(i).getCategoria().equals("Administrador")){
												System.out.println("No se puede eliminar al empleado ya que es un encargado.");
												salir=true;
												break;
											}
											mibase.abrir();
											BBDDTaquillero.eliminar(codigo, mibase.getConexion());
											mibase.cerrar();
											System.out.println("El empleado con código nº "+codigo+" ha sido eliminado.");
											break;

										}
									}
									if (salir==true)
										break;

									System.out.println("El empleado no existe en la base de datos.");

									break;
								case 3:
									System.out.println("OPCION EN DESARROLLO");
									break;
								}
								break;
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
								System.out.println("1. Revisar atracción.");
								System.out.println("2. Cerrar atracción.");
								System.out.println("0. Salir.");
								opc=sc.nextInt();
								switch(opc){
								case 1:
									System.out.println("Listado de las atracciones pendientes por revisar: ");
									for (int i = 0; i < lista.size(); i++) {
										if(lista.get(i).getId_administrador()==null){
											System.out.println(lista.get(i).toString());
											aux++;
										}
									}
									if(aux==0)
										System.out.println("Todas las atracciones están revisadas.");
									System.out.println("");
									System.out.print("Introduzca código de atracción a revisar: ");
									codigoAt=sc.nextInt();
									sc.nextLine();
									aux=0;
									for (int i = 0; i < lista.size(); i++) {
										if(lista.get(i).getCod_atraccion()==codigoAt)
											aux1++;
									}
									if(aux1==0){
										System.out.println("No existe ninguna atracción con ese codigo.");
										break;
									}
									else{
										aux1=0;
										for (int i = 0; i < lista.size(); i++) {
											if(lista.get(i).getId_administrador()==null && lista.get(i).getCod_atraccion()==codigoAt){
												aux=i;
											}
											if(lista.get(i).getId_administrador()!=null && lista.get(i).getCod_atraccion()==codigoAt){
												aux1=i;
											}
										}
										if(aux1!=0){
											opc=0;
											System.out.println("La atracción "+lista.get(aux1).getNom_atraccion()+" fue revisada el: "+lista.get(aux1).getFh_revision());
											System.out.println("");
											System.out.println("¿Desea volver a revisar la atracción?");
											System.out.println("1.Sí");
											System.out.println("2.No");
											opc=sc.nextInt();
											sc.nextLine();
											if(opc==2)
												break;
											else{
												mibase.abrir();
												BBDDAtraccion.revisar(id, codigoAt, mibase.getConexion());
												mibase.cerrar();
												System.out.println("La atracción se ha vuelto a revisar satisfactoriamente");
												break;
											}

										}
										if(aux!=0){
											System.out.println("Se va a revisar la atracción: "+lista.get(aux).getNom_atraccion());
											mibase.abrir();
											BBDDAtraccion.revisar(id,codigoAt,mibase.getConexion());
											mibase.cerrar();
											System.out.println("Atracción revisada correctamente.");
											break;
										}
									}
									break;
								case 2:
									salir=true;
									mibase.abrir();
									lista=BBDDAtraccion.listar(at, mibase.getConexion());
									mibase.cerrar();
									System.out.print("Introduzca codigo de la atracción a cerrar: ");
									codigoAt=sc.nextInt();
									for (int i = 0; i < lista.size(); i++) {
										if(lista.get(i).getCod_atraccion()==codigoAt){
											nombreAt=lista.get(i).getNom_atraccion();
											salir=false;
											break;
										}
									}
									if(salir==false){
										sc.nextLine();
										mibase.abrir();
										BBDDAtraccion.cerrar(codigoAt,nombreAt, mibase.getConexion());
										mibase.cerrar();
										System.out.println("Atracción "+nombreAt+" cerrada.");
										break;
									}
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
						System.out.println("0.SALIR");
						opc=sc.nextInt();
						sc.nextLine();
						if(opc==1){
							do{
								System.out.print("Introduce precio: ");
								int precio=sc.nextInt();
								if(precio!=0 && precio!=10 && precio!=15 && precio!=20){
									System.out.println("ERROR. Precio de entrada incorrecto");
								}
								else{	
									mibase.abrir();
									BBDDEntrada.nuevaEntrada(codigo,precio, mibase.getConexion());
									mibase.cerrar();
									System.out.println("Entrada vendida.");
									System.out.print("¿Vender otra entrada? [1.Si] [0.No]: ");
									s=sc.nextInt();
									sc.nextLine();
								}
							}while(s!=0);
						}
					}while(opc!=0);
				}
			}
			else
				System.out.println("LOGIN INCORRECTO. VUELVE A INTENTARLO.");
		}while(a==0);
	}
}
