package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;

public class Vista {

	Controlador controlador;
	private final static String ERROR = "No existen reservas para el parámetro proporcionado";
	private final static String NOMBRE_VALIDO = "Peter";
	private final static String CORREO_VALIDO = "peter1984@yahoo.com";

	public Vista() {

		Opcion.setVista(this);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void comenzar() {

		int ordinalOpcion = 0;
		do {
			try {
				Consola.mostrarMenu();
				ordinalOpcion = Consola.elegirOpcion();
				Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
				opcion.ejecutar();
			} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	public void salir() {

		System.out.println("Se ha terminado el programa.");
		controlador.terminar();
	}

	public void insertarProfesor() {

		Consola.mostrarCabecera("Insertar Profesor");
		try {
			controlador.insertar(Consola.leerProfesor());
			System.out.println("Profesor insertado correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarProfesor() {

		Consola.mostrarCabecera("Buscar Profesor");

		try {
			Consola.mostrarCabecera("Buscar profesor existente: ");

			Profesor profesorBuscar = controlador.buscarProfesor(Consola.leerProfesor());
			if (profesorBuscar == null) {
				System.out.println("El profesor introducido no existe.");
			} else {
				System.out.println("El profesor introducido ya existe.");
			}

		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarProfesor() {

		Consola.mostrarCabecera("Borrar Profesor");

		try {
			controlador.borrarProfesor(Consola.leerProfesor());
			System.out.println("El profesor se ha borrado correctamente.");
			} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
				System.out.println(e.getMessage());
			}
		}
	

	public void listarProfesores() {

		Consola.mostrarCabecera("Listado de Profesores");

		List<String> listaProfesores = null;
		try {
			listaProfesores = controlador.representarProfesores();
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (listaProfesores == null) {
			System.out.println("No hay profesores que mostrar");
		} else {
			Iterator<String> iterador = listaProfesores.iterator();
			while (iterador.hasNext()) {
				System.out.println(iterador.next().toString());
			}
		}
	}

	public void insertarAula() {

		Consola.mostrarCabecera("Insertar Aula");

		try {
			controlador.insertarAula(Consola.leerAula());
			System.out.println("Aula insertada correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}

	public void buscarAula() {

		Consola.mostrarCabecera("Buscar Aula");

		Aula aula = null;
		List<String> listaAulas = controlador.representarAulas();
		if (listaAulas == null) {
			System.out.println("No existen aulas que buscar");
		} else {
			try {
				aula = controlador.buscarAula(Consola.leerAula());
			} catch (NullPointerException | IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			if (aula == null) {
				System.out.println("El aula buscada no existe");
			} else {
				System.out.println(aula.toString());
			}
		}
	}

	public void borrarAula() {

		Consola.mostrarCabecera("Borrar Aula");

		List<String> listaAulas = controlador.representarAulas();
		if (listaAulas == null) {
			System.out.println("No existen aulas que borrar");
		} else {
			try {
				controlador.borrarAula(Consola.leerAula());
				System.out.println("Aula borrada correctamente.");
			} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void listarAulas() {

		Consola.mostrarCabecera("Listado de Aulas");

		List<String> listaAulas = null;
		try {
			listaAulas = controlador.representarAulas();
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (listaAulas == null) {
			System.out.println("No hay aulas que mostrar");
		} else {

			Iterator<String> iterador = listaAulas.iterator();
			while (iterador.hasNext()) {
				System.out.println(iterador.next().toString());
			}
		}
	}

	public void realizarReserva() {

		try {
			Profesor profesorBuscar = controlador.buscarProfesor(Consola.leerProfesor());
			if (profesorBuscar == null) {
				throw new NullPointerException("El profesor introducido no existe.");
			} else {
				Reserva reserva = leerReserva(profesorBuscar);
				if (reserva == null) {
					throw new IllegalArgumentException("Error introducido en los datos de la reserva, compruébelos.");
				}
				controlador.realizarReserva(reserva);
				System.out.println("Reserva realizada correctamente.");
			}
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	private Reserva leerReserva(Profesor profesor) {

		Reserva reserva = null;
		Aula aula = null;
		Tramo tramo = null;
		LocalDate dia = null;
		Permanencia permanencia = null;
		try {
			aula = Consola.leerAula();
			tramo = Consola.leerTramo();
			dia = Consola.leerDia();
			permanencia = new Permanencia(dia, tramo);
			reserva = new Reserva(profesor, aula, permanencia);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (dia.isBefore(LocalDate.now())) {
			reserva = null;
		} else {
			reserva = new Reserva(reserva);
		}
		return reserva;
	}

	public void anularReserva() throws OperationNotSupportedException {

		Reserva reserva = null;
		Profesor profesor = new Profesor(NOMBRE_VALIDO, CORREO_VALIDO);
		List<String> listaReservas = controlador.representarReservas();
		if (listaReservas == null) {
			System.out.println("No existen reservas que borrar");
		} else {
			try {
				reserva = leerReserva(profesor);
				controlador.anularReserva(reserva);
				System.out.println("Reserva anulada correctamente.");
			} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void listarReservas() {

		Consola.mostrarCabecera("Listado de reservas existentes: ");
		List<String> listaReservas = null;
		try {
			listaReservas = controlador.representarReservas();
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (listaReservas == null) {
			System.out.println("No hay reservas que mostrar");
		} else {
			Iterator<String> iterador = listaReservas.iterator();
			while (iterador.hasNext()) {
				System.out.println(iterador.next().toString());
			}
		}
	}

	public void listarReservasAula() {

		List<Reserva> listaReservasAula = null;
		try {
			listaReservasAula = controlador.getReservasAula(Consola.leerAula());
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (listaReservasAula == null) {
			System.out.println(ERROR);
		} else {
			Iterator<Reserva> iterador = listaReservasAula.iterator();
			while (iterador.hasNext()) {
				System.out.println(iterador.next().toString());
			}
		}
	}

	public void listarReservaProfesor() {

		try {

			Consola.mostrarCabecera("Listado de reservas existentes por profesor: ");

			Profesor profesorBuscar = controlador.buscarProfesor(Consola.leerProfesor());
			if (profesorBuscar == null) {
				throw new NullPointerException("El profesor introducido no existe.");
			} else {
				List<Reserva> listaReservas = controlador.getReservasProfesor(profesorBuscar);

				if (listaReservas.size() == 0) {
					System.out.println("No hay reservas.");
				} else {
					Iterator<Reserva> iterador = listaReservas.iterator();

					while (iterador.hasNext()) {
						System.out.println(iterador.next().toString());
					}
				}
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarReservaPermanencia() {

		try {

			Consola.mostrarCabecera("Listado de reservas existentes por permanencia: ");
			Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());

			List<Reserva> listaReservas = controlador.getReservasPermanencia(permanencia);

			if (listaReservas.size() == 0) {
				System.out.println("No hay reservas.");
			} else {

				Iterator<Reserva> iterador = listaReservas.iterator();

				while (iterador.hasNext()) {
					System.out.println(iterador.next().toString());
				}
			}

		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void consultarDisponibilidad() {

		try {

			Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
			Aula aulaBuscar = controlador.buscarAula(Consola.leerAula());
			if (aulaBuscar == null) {
				System.out.println("El aula introducida no existe.");
			} else {
				if (controlador.consultarDisponibilidad(aulaBuscar, permanencia)) {
					System.out.println("El aula está disponible.");
				} else {
					System.out.println("El aula ya está reservada.");
				}

			}

		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}
	}
}
