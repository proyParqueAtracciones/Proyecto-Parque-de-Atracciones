package parque;
import java.util.*;
import bbdd.*;
import clases.*;
public class parque {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String nombre="";
		int codigo=0,codigoAt=0,opc=0,acc=0,a=0, telefono=0,nss=0,aux=0,aux1=0,s=0;
		String apellidos="",direccion="",categoria="",horario="",dni="",nombreAt="",motivo="";
		boolean salir=false;

		Vector <personal> listaPersonal;
		Vector <atraccion> lista;

		personal pe=null;
		taquillero ta=null;
		atraccion at=null;

		BaseDatosC mibase=new BaseDatosC("mysql-properties.xml");

		do{
			System.out.println("");
			System.out.println("Sistema de gesti�n del parque de atracciones");
			System.out.println("------################################------");
			System.out.print("Usuario: ");
			try{
				nombre=sc.nextLine();
				System.out.print("Contrase�a(n� emple): ");
				codigo=sc.nextInt();
				sc.nextLine();
			}catch(InputMismatchException e){
				System.err.print(e.getMessage());
			}
			//Validaci�n de cod_empleado
			mibase.abrir();
			a=BBDDPersonal.listar(codigo, nombre, mibase.getConexion());
			mibase.cerrar();
			//Si encuentra al empleado en la BBDD
			if(a!=0){
				System.out.println("Login correcto.");
				//Se busca a ese empleado en la tabla administrador
				mibase.abrir();
				a=BBDDAdministrador.listar(codigo, mibase.getConexion());
				mibase.cerrar();
				//Si encuentra el administrador
				if(a==1){
					System.out.print("Introduce tu id de administrador para continuar: ");
					String id=sc.nextLine();
					//Validaci�n del id_administrador
					mibase.abrir();
					acc=BBDDAdministrador.comprobarId(id,mibase.getConexion());
					mibase.cerrar();
					//Si existe ese administrador
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
							salir=false;
							switch(opc){
							case 1:
								/**
								 * GESTION DE EMPLEADOS
								 */
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
										//Se listan los empleados de la tabla personal
										mibase.abrir();
										listaPersonal=BBDDPersonal.listarPersonal(pe, mibase.getConexion());
										mibase.cerrar();

										//Bucle que recorre la lista y comprueba que el nuevo c�digo no est� en ella.
										for (int i = 0; i < listaPersonal.size(); i++) {
											if(listaPersonal.get(i).getCod_empleado()==codigo){
												System.out.println("ERROR. El empleado ya existe.");
												salir=true;
												break;
											}
										}
										//Si el c�digo est� se detiene la ejecuci�n
										if(salir==true){
											break;}
										//Si no, se siguen introduciendo datos
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
										do{
											System.out.println("Introduce horario (L � M � X etc) :");
											horario=sc.nextLine();
											//�nicamente se permite un d�a de la semana
										}while(horario.length()!=1);
									}

									catch(InputMismatchException e){
										System.err.print(e.getMessage());
									}
									catch(NoSuchElementException e){
										System.err.print(e.getMessage());
									}
									//Se crea el objeto taquillero con los datos recibidos
									ta= new taquillero(codigo,nombre,apellidos,dni,telefono,nss,direccion,categoria,horario);
									//Se llama al m�todo a�adir
									mibase.abrir();
									BBDDTaquillero.anadir(ta,mibase.getConexion());
									mibase.cerrar();
									System.out.println("Empleado a�adido correctamente.");
									break;

								case 2:
									//Se lista al personal
									mibase.abrir();
									listaPersonal=BBDDPersonal.listarPersonal(pe,mibase.getConexion());
									mibase.cerrar();
									System.out.println("");
									System.out.println("Lista de los empleados que se pueden eliminar: ");

									//Se recorre la lista para mostrar a los empleados que se pueden eliminar
									for (int i=0;i<listaPersonal.size(); i++){
										if(listaPersonal.get(i).getCategoria().equals("Taquillero"))
											System.out.println(listaPersonal.get(i).toString());
									}

									System.out.println("");
									System.out.print("Introduce codigo de empleado: ");
									codigo=sc.nextInt();
									sc.nextLine();
									//Se vuelve a recorrer para comprobar que el c�digo est� en la lista y si es administrador o taquillero
									for (int i = 0; i < listaPersonal.size(); i++) {
										if(listaPersonal.get(i).getCod_empleado()==codigo){
											//Si es administrador se devuelve error y se detiene la ejecuci�n
											if(listaPersonal.get(i).getCategoria().equals("Administrador")){
												System.out.println("ERROR. No se puede eliminar un administrador.");
												salir=true;
												break;
											}
											//Si es taquillero se procede a la eliminaci�n
											mibase.abrir();
											BBDDTaquillero.eliminar(codigo, mibase.getConexion());
											mibase.cerrar();
											System.out.println("El empleado con c�digo n� "+codigo+" ha sido eliminado.");
											break;

										}
									}
									if (salir==true)
										break;
									//Si el cod_empleado no se encuentra en la tabla personal se devuelve mensaje de error.
									else
										System.out.println("El empleado no existe en la base de datos.");

									break;
								case 3:
									System.out.println("OPCION EN DESARROLLO");
									break;
								}
								break;
							case 2:
								/**
								 * GESTION DE ATRACCIONES
								 */
								//Se listan las atracciones y se muestran por pantalla
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
								aux=0;
								aux1=0;
								switch(opc){
								case 1:
									//Se listan todas aquellas atracciones que no se hayan revisado(id_administrador=null)
									System.out.println("Listado de las atracciones pendientes por revisar: ");
									for (int i = 0; i < lista.size(); i++) {
										if(lista.get(i).getId_administrador()==null){
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
									//Bucle que recorre la lista y comprueba que exista la atracci�n
									for (int i = 0; i < lista.size(); i++) {
										if(lista.get(i).getCod_atraccion()==codigoAt)
											aux++;


									}
									//Si no existe se da un error y se detiene la ejecuci�n
									if(aux==0){
										System.out.println("No existe ninguna atracci�n con ese codigo.");
										break;
									}
									else{
										aux=-1;
										aux1=-1;
										for (int i = 0; i < lista.size(); i++) {
											//Si la atracci�n con ese c�digo tiene el campo id_adminstrador a null significa que se puede revisar
											if(lista.get(i).getId_administrador()==null && lista.get(i).getCod_atraccion()==codigoAt){
												aux=i;
											}
											//Si la atracci�n con ese c�digo tiene el campo id_administrador relleno, significa que ya ha sido revisada
											if(lista.get(i).getId_administrador()!=null && lista.get(i).getCod_atraccion()==codigoAt){
												aux1=i;
											}
										}
										//En caso de que ya haya sido revisada
										if(aux1!=-1){
											opc=0;
											System.out.println("La atracci�n "+lista.get(aux1).getNom_atraccion()+" fue revisada el: "+lista.get(aux1).getFh_revision());
											System.out.println("");
											System.out.println("�Desea volver a revisar la atracci�n?");
											System.out.println("1.S�");
											System.out.println("2.No");
											opc=sc.nextInt();
											sc.nextLine();
											if(opc==2)
												break;
											else{
												//Se vuelve a revisar la atracci�n
												System.out.print("Introduzca motivo de la revisi�n: ");
												motivo=sc.nextLine();
												administrador.realizarRevisi�n(id, codigoAt, motivo, mibase);
												System.out.println("La atracci�n se ha vuelto a revisar satisfactoriamente");
												break;
											}

										}
										//Si la atracci�n est� sin revisar
										if(aux!=-1){
											System.out.println("Se va a revisar la atracci�n: "+lista.get(aux).getNom_atraccion());
											System.out.print("Motivo de la revisi�n: ");
											motivo=sc.nextLine();
											administrador.realizarRevisi�n(id, codigoAt, motivo, mibase);
											System.out.println("Atracci�n revisada correctamente.");
											break;
										}
									}
									break;
								case 2:
									/**
									 * CERRAR ATRACCION
									 */
									salir=true;
									mibase.abrir();
									lista=BBDDAtraccion.listar(at, mibase.getConexion());
									mibase.cerrar();
									System.out.print("Introduzca codigo de la atracci�n a cerrar: ");
									codigoAt=sc.nextInt();
									//Se recorre la lista de atracciones
									for (int i = 0; i < lista.size(); i++) {
										if(lista.get(i).getCod_atraccion()==codigoAt){
											nombreAt=lista.get(i).getNom_atraccion();
											salir=false;
											break;
										}
									}
									//Si la atracci�n existe se cierra
									if(salir==false){
										sc.nextLine();
										mibase.abrir();
										BBDDAtraccion.cerrar(codigoAt,nombreAt, mibase.getConexion());
										mibase.cerrar();
										System.out.println("Atracci�n "+nombreAt+" cerrada.");
										break;
									}

									System.out.println("No existe ninguna atracci�n con ese c�digo");
									break;
								}
								break;
							case 3:
								System.out.println("EN DESARROLLO.");
								break;
							}
						}while(opc!=0);
					}
					//Si el administrador no se valida correctamente se sale
					else
						System.out.println("ERROR. CODIGO INCORRECTO. SALIENDO.");
				}
				else{
					//Si el empleado que se identifica es taquillero se muestra el menu para vender entrada
					do{
						System.out.println("");
						System.out.println("Entradas y precios:");
						System.out.println("Paseo: 10�");
						System.out.println("Rebajada: 15�");
						System.out.println("Normal: 20�");
						System.out.println("");
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
									System.out.print("�Vender otra entrada? [1.Si] [0.No]: ");
									s=sc.nextInt();
									sc.nextLine();
								}
							}while(s!=0);
						}
					}while(opc!=0);
				}
			}
			//Si el empleado se identifica mal se muestra error.
			else
				System.out.println("LOGIN INCORRECTO. VUELVE A INTENTARLO.");
		}while(a==0);
	}
}
