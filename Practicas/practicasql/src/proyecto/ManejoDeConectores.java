package proyecto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import bbdd.ConexionBBDD;
import dao.DaoCreaBBDD;
import dao.DaoDeporte;
import dao.DaoDeportista;
import dao.DaoEquipo;
import dao.DaoEvento;
import dao.DaoOlimpiada;
import dao.DaoParticipacion;
import modelos.ModeloDeporte;
import modelos.ModeloDeportista;
import modelos.ModeloEvento;
import modelos.ModeloOlimpiada;
import modelos.ModeloParticipacion;

/**
 * Clase ManejoDeConectores.
 */
public class ManejoDeConectores {
	
	/**
	 * Metodo que lanza el menu.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			ConexionBBDD con=new ConexionBBDD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Scanner input=new Scanner(System.in);
		System.out.println("1. Crear BBDD MySQL");
		System.out.println("2. Listado de deportistas en diferentes deportes");
		System.out.println("3. Listado de deportistas participantes");
		System.out.println("4. Modificar medalla deportista");
		System.out.println("5. Añadir deportista/participación");
		System.out.println("6. Eliminar participación");
		int opcionMenu =input.nextInt();
		input.nextLine();
		switch (opcionMenu) {
		case 1:
			System.out.println("Dime la ruta del archivo csv");
			String ruta=input.nextLine();
			DaoCreaBBDD.crearBBDD(ruta);
			break;
		case 2:
			ListarDeportistas();
			break;
		case 3:
			int resp=0;
			String temporada;
			int idDeporte;
			int idOlimpiada;
			int idEvento;
			do {
				System.out.println("Dime la temporada:\n1 Winter\n2 Summer");
				resp=input.nextInt();
				input.nextLine();
			}while(resp!=1&&resp!=2);
			ArrayList<ModeloOlimpiada>lstOlimpiada=DaoOlimpiada.listaOlimpiadasPorTemporada(resp);
			do {
				System.out.println("Elige (usando el numero) la edicion olimpica que quieres seleccionar");
				for(int i=0;i<lstOlimpiada.size();i++) {
					System.out.println((i+1)+": "+lstOlimpiada.get(i).toString());
				}
				resp=input.nextInt();
				input.nextLine();
			}while(resp<1||resp>lstOlimpiada.size());
			temporada=lstOlimpiada.get(resp-1).getTemporada();
			idOlimpiada=Integer.parseInt(DaoOlimpiada.conseguirIdOlimpiada(lstOlimpiada.get(resp-1).getNombreOlimpiada(), lstOlimpiada.get(resp-1).getAnio(), temporada, lstOlimpiada.get(resp-1).getCiudad()));
			ArrayList<ModeloDeporte>lstDeporte=DaoDeporte.listaDeportesPorOlimpiada(Integer.parseInt(DaoOlimpiada.conseguirIdOlimpiada(lstOlimpiada.get(resp-1).getNombreOlimpiada(), lstOlimpiada.get(resp-1).getAnio(),lstOlimpiada.get(resp-1).getTemporada(),lstOlimpiada.get(resp-1).getCiudad())));
			do {
				System.out.println("Elige (usando el numero) el deporte");
				for(int i=0;i<lstDeporte.size();i++) {
					System.out.println((i+1)+": "+lstDeporte.get(i).toString());
				}
				resp=input.nextInt();
				input.nextLine();
			}while(resp<1||resp>lstDeporte.size());
			idDeporte=Integer.parseInt(DaoDeporte.conseguirIdDeporte(lstDeporte.get(resp-1).getNombreDeporte()));
			ArrayList<ModeloEvento>lstEventos=DaoEvento.crearListaModelosPorDeporteYOlimpiada(idDeporte, idOlimpiada);
			do {
				System.out.println("Elige (usando el numero) el evento");
				for(int i=0;i<lstEventos.size();i++) {
					System.out.println((i+1)+": "+lstEventos.get(i).toString());
				}
				resp=input.nextInt();
				input.nextLine();
			}while(resp<1||resp>lstEventos.size());
			idEvento=Integer.parseInt(DaoEvento.conseguirIdEvento(lstEventos.get(resp-1).getNombreEvento(), idOlimpiada, idDeporte));
			System.out.println("Resumen:\nTemporada: "+temporada+"\nOlimpiada: "+DaoOlimpiada.crearModeloOlimpiada(idOlimpiada)+
					"\nDeporte: "+DaoDeporte.crearModeloDeporte(idDeporte)+"\nEvento: "+DaoEvento.crearPorId(idEvento));
			System.out.println("Deportistas:");
			ArrayList<String>lstIdDeportistas=DaoParticipacion.darIdDeportista(idEvento);
			for(String deportista:lstIdDeportistas) {
				System.out.println(DaoParticipacion.crearModeloParticipacion(Integer.parseInt(deportista), idEvento));
			}
			break;
		case 4:
			resp=0;
			System.out.println("Dime el nombre a buscar:");
			String nombre=input.nextLine();
			ArrayList<ModeloDeportista>lstDeportistas=DaoDeportista.buscarNombresDeportistas(nombre);
			if(lstDeportistas.size()>0) {
				do {
					System.out.println("Elige al deportista (usando el numero)");
					for(int i=0;i<lstDeportistas.size();i++) {
						System.out.println((i+1)+": "+lstDeportistas.get(i).getNombreDeportista());
					}
					resp=input.nextInt();
					input.nextLine();
				}while(resp<1||resp>lstDeportistas.size());
				ModeloDeportista deportista=lstDeportistas.get(resp-1);
				lstEventos=DaoEvento.crearListaModelosPorLstId(DaoParticipacion.darIdEvento(Integer.parseInt(DaoDeportista.conseguirIdDeportista(lstDeportistas.get(resp-1).getNombreDeportista(),lstDeportistas.get(resp-1).getSexo(),lstDeportistas.get(resp-1).getPeso(),lstDeportistas.get(resp-1).getAltura()))));
				do {
					System.out.println("Elige el evento (usando el numero)");
					for(int i=0;i<lstEventos.size();i++) {
						System.out.println((i+1)+": "+lstEventos.get(i).getNombreEvento());
					}
					resp=input.nextInt();
					input.nextLine();
				}while(resp<1||resp>lstEventos.size());
				ModeloEvento evento=DaoEvento.crearPorId(Integer.parseInt(DaoEvento.conseguirIdEvento(lstEventos.get(resp-1).getNombreEvento(),Integer.parseInt(DaoOlimpiada.conseguirIdOlimpiada(lstEventos.get(resp-1).getOlimpiada().getNombreOlimpiada(),lstEventos.get(resp-1).getOlimpiada().getAnio(),lstEventos.get(resp-1).getOlimpiada().getTemporada(),lstEventos.get(resp-1).getOlimpiada().getCiudad())),Integer.parseInt(DaoDeporte.conseguirIdDeporte(lstEventos.get(resp-1).getDeporte().getNombreDeporte())))));
				do {
					System.out.println("¿Que medalla tiene quieres poner?");
					System.out.println("1 Ninguna\n2 Bronce\n3 Plata\n4 Oro");
					resp=input.nextInt();
					input.nextLine();
				}while(resp!=1&&resp!=2&&resp!=3&&resp!=4);
				String medalla="Gold";
				switch (resp) {
				case 1:
					medalla="NA";
					break;
				case 2:
					medalla="Bronze";
					break;
				case 3:
					medalla="Silver";
					break;
				}
				DaoParticipacion.editarMedalla(Integer.parseInt(DaoDeportista.conseguirIdDeportista(deportista.getNombreDeportista(),deportista.getSexo(),deportista.getPeso(),deportista.getAltura())),
						Integer.parseInt(DaoEvento.conseguirIdEvento(evento.getNombreEvento(),Integer.parseInt(DaoOlimpiada.conseguirIdOlimpiada(evento.getOlimpiada().getNombreOlimpiada(),evento.getOlimpiada().getAnio(),evento.getOlimpiada().getTemporada(),evento.getOlimpiada().getCiudad())),Integer.parseInt(DaoDeporte.conseguirIdDeporte(evento.getDeporte().getNombreDeporte())))),
						medalla);
			}else {
				System.out.println("No hay ningun deportista que contenga esa cadena de caracteres en el nombre");
			}
			break;
		case 5:
			int idDeportista=0;
			resp=0;
			ModeloDeportista deportista;
			System.out.println("Dime el nombre a buscar:");
			nombre=input.nextLine();
			lstDeportistas=DaoDeportista.buscarNombresDeportistas(nombre);
			if(lstDeportistas.size()>0) {
				do {
					System.out.println("Elige al deportista (usando el numero)");
					for(int i=0;i<lstDeportistas.size();i++) {
						System.out.println((i+1)+": "+lstDeportistas.get(i).getNombreDeportista());
					}
					resp=input.nextInt();
					input.nextLine();
				}while(resp<1||resp>lstDeportistas.size());
				deportista=lstDeportistas.get(resp-1);
			}else {
				System.out.println("No hay ningun deportista que contenga esa cadena de caracteres en el nombre, se creará uno nuevo");
				System.out.println("Dime el nombre completo:");
				nombre=input.nextLine();
				int sexo=0;
				do {
					System.out.println("Dime el sexo:\n1 M\n2 F");
					sexo=input.nextInt();
					input.nextLine();
				}while(sexo!=1&&sexo!=2);
				System.out.println("¿Cuanto pesa?");
				int peso=Math.round(input.nextFloat());
				input.nextLine();
				System.out.println("¿Cuanto mide (en cm)?");
				int altura=input.nextInt();
				input.nextLine();
				if(sexo==1) {
					DaoDeportista.aniadirDeportista(nombre, 'M', peso, altura);
					deportista=DaoDeportista.crearModeloDeportista(DaoDeportista.conseguirIdDeportista(nombre, 'M', peso, altura));
				}else {
					DaoDeportista.aniadirDeportista(nombre, 'F', peso, altura);
					deportista=DaoDeportista.crearModeloDeportista(DaoDeportista.conseguirIdDeportista(nombre, 'F', peso, altura));
				}
			}
			idDeportista=Integer.parseInt(DaoDeportista.conseguirIdDeportista(deportista.getNombreDeportista(),deportista.getSexo(),deportista.getPeso(), deportista.getAltura()));
			do {
				System.out.println("Dime la temporada:\n1 Winter\n2 Summer");
				resp=input.nextInt();
				input.nextLine();
			}while(resp!=1&&resp!=2);
			lstOlimpiada=DaoOlimpiada.listaOlimpiadasPorTemporada(resp);
			do {
				System.out.println("Elige (usando el numero) la edicion olimpica que quieres seleccionar");
				for(int i=0;i<lstOlimpiada.size();i++) {
					System.out.println((i+1)+": "+lstOlimpiada.get(i).toString());
				}
				resp=input.nextInt();
				input.nextLine();
			}while(resp<1||resp>lstOlimpiada.size());
			temporada=lstOlimpiada.get(resp-1).getTemporada();
			idOlimpiada=Integer.parseInt(DaoOlimpiada.conseguirIdOlimpiada(lstOlimpiada.get(resp-1).getNombreOlimpiada(), lstOlimpiada.get(resp-1).getAnio(), temporada, lstOlimpiada.get(resp-1).getCiudad()));
			lstDeporte=DaoDeporte.listaDeportesPorOlimpiada(Integer.parseInt(DaoOlimpiada.conseguirIdOlimpiada(lstOlimpiada.get(resp-1).getNombreOlimpiada(), lstOlimpiada.get(resp-1).getAnio(),lstOlimpiada.get(resp-1).getTemporada(),lstOlimpiada.get(resp-1).getCiudad())));
			do {
				System.out.println("Elige (usando el numero) el deporte");
				for(int i=0;i<lstDeporte.size();i++) {
					System.out.println((i+1)+": "+lstDeporte.get(i).toString());
				}
				resp=input.nextInt();
				input.nextLine();
			}while(resp<1||resp>lstDeporte.size());
			idDeporte=Integer.parseInt(DaoDeporte.conseguirIdDeporte(lstDeporte.get(resp-1).getNombreDeporte()));
			lstEventos=DaoEvento.crearListaModelosPorDeporteYOlimpiada(idDeporte, idOlimpiada);
			do {
				System.out.println("Elige (usando el numero) el evento");
				for(int i=0;i<lstEventos.size();i++) {
					System.out.println((i+1)+": "+lstEventos.get(i).toString());
				}
				resp=input.nextInt();
				input.nextLine();
			}while(resp<1||resp>lstEventos.size());
			idEvento=Integer.parseInt(DaoEvento.conseguirIdEvento(lstEventos.get(resp-1).getNombreEvento(), idOlimpiada, idDeporte));
			System.out.println("Dime la edad:");
			int edad=input.nextInt();
			input.nextLine();
			System.out.println();
			do {
				System.out.println("Dime la medalla:");
				System.out.println("1 Ninguna\n2 Bronce\n3 Plata\n4 Oro");
				resp=input.nextInt();
				input.nextLine();
			}while(resp!=1&&resp!=2&&resp!=3&&resp!=4);
			String medalla="Gold";
			switch (resp) {
			case 1:
				medalla="NA";
				break;
			case 2:
				medalla="Bronze";
				break;
			case 3:
				medalla="Silver";
				break;
			}
			System.out.println("Dime el nombre de su equipo");
			String nombreEquipo=input.nextLine();
			System.out.println("Dime su abreviacion");
			String abreviacion=input.nextLine();
			if(DaoEquipo.conseguirIdEquipo(nombreEquipo, abreviacion)==null) {
				DaoEquipo.aniadirEquipo(nombreEquipo, abreviacion);
			}
			int idEquipo=Integer.parseInt(DaoEquipo.conseguirIdEquipo(nombreEquipo, abreviacion));
			System.out.println(idEvento);
			System.out.println(idEquipo);
			System.out.println(idDeportista);
			try {
				DaoParticipacion.aniadirParticipacion(idDeportista, idEvento, idEquipo, edad, medalla);
			}catch(Exception e) {
				System.out.println("Esa participacion ya estaba en la base de datos");
			}
			break;
		case 6:
			resp=0;
			System.out.println("Dime el nombre a buscar:");
			nombre=input.nextLine();
			lstDeportistas=DaoDeportista.buscarNombresDeportistas(nombre);
			if(lstDeportistas.size()>0) {
				do {
					System.out.println("Elige al deportista (usando el numero)");
					for(int i=0;i<lstDeportistas.size();i++) {
						System.out.println((i+1)+": "+lstDeportistas.get(i).getNombreDeportista());
					}
					resp=input.nextInt();
					input.nextLine();
				}while(resp<1||resp>lstDeportistas.size());
				deportista=lstDeportistas.get(resp-1);
				idDeportista=Integer.parseInt(DaoDeportista.conseguirIdDeportista(deportista.getNombreDeportista(),deportista.getSexo(),deportista.getPeso(),deportista.getAltura()));
				lstEventos=DaoEvento.crearListaModelosPorLstId(DaoParticipacion.darIdEvento(Integer.parseInt(DaoDeportista.conseguirIdDeportista(lstDeportistas.get(resp-1).getNombreDeportista(),lstDeportistas.get(resp-1).getSexo(),lstDeportistas.get(resp-1).getPeso(),lstDeportistas.get(resp-1).getAltura()))));
				if(lstEventos.size()>0) {
					do {
						System.out.println("Elige el evento (usando el numero)");
						for(int i=0;i<lstEventos.size();i++) {
							System.out.println((i+1)+": "+lstEventos.get(i).getNombreEvento());
						}
						resp=input.nextInt();
						input.nextLine();
					}while(resp<1||resp>lstEventos.size());
					ModeloEvento evento=DaoEvento.crearPorId(Integer.parseInt(DaoEvento.conseguirIdEvento(lstEventos.get(resp-1).getNombreEvento(),Integer.parseInt(DaoOlimpiada.conseguirIdOlimpiada(lstEventos.get(resp-1).getOlimpiada().getNombreOlimpiada(),lstEventos.get(resp-1).getOlimpiada().getAnio(),lstEventos.get(resp-1).getOlimpiada().getTemporada(),lstEventos.get(resp-1).getOlimpiada().getCiudad())),Integer.parseInt(DaoDeporte.conseguirIdDeporte(lstEventos.get(resp-1).getDeporte().getNombreDeporte())))));
					idEvento=Integer.parseInt(DaoEvento.conseguirIdEvento(evento.getNombreEvento(),Integer.parseInt(DaoOlimpiada.conseguirIdOlimpiada(evento.getOlimpiada().getNombreOlimpiada(),evento.getOlimpiada().getAnio(),evento.getOlimpiada().getTemporada(),evento.getOlimpiada().getCiudad())),Integer.parseInt(DaoDeporte.conseguirIdDeporte(evento.getDeporte().getNombreDeporte())) ));
					DaoParticipacion.eliminarParticipacion(idDeportista, idEvento);
				}else {
					System.out.println("Ese deportista no tiene participaciones");
				}
			}else {
				System.out.println("No hay ningun deportista que contenga esa cadena de caracteres en el nombre");
			}
			break;
		default:
			System.out.println("Opcion no valida");
			break;
		}
	}

	/**
	 * Listar deportistas.
	 */
	private static void ListarDeportistas() {
		//i=10 porque por defecto ha empezado a generar en el id=10
		int i=10;
		while(DaoDeportista.crearModeloDeportista(i+"")!=null) {
			System.out.println(DaoDeportista.crearModeloDeportista(i+"")+":");
			ArrayList<ModeloParticipacion>lst=listaParticipaciones(i);
			for(ModeloParticipacion part:lst) {
				System.out.println(part.getEvento().getDeporte().getNombreDeporte()+","+
						part.getEdad()+","+part.getEvento().getNombreEvento()+","+
						part.getEquipo().getNombreEquipo()+","+part.getEvento().getOlimpiada().getNombreOlimpiada()+","+
						part.getMedalla());
			}
			i++;
		}
	}
	
	/**
	 * Lista participaciones.
	 *
	 * @param idDeportista the id deportista
	 * @return the array list
	 */
	public static ArrayList<ModeloParticipacion> listaParticipaciones(int idDeportista){
		ArrayList<ModeloParticipacion> lista=new ArrayList<ModeloParticipacion>();
		for(String idEvento:DaoParticipacion.darIdEvento(idDeportista)) {
			lista.add(DaoParticipacion.crearModeloParticipacion(idDeportista, Integer.parseInt(idEvento)));
		}
		return lista;
	}
}
