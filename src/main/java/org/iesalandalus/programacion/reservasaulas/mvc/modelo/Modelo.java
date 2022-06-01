package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import javax.naming.OperationNotSupportedException;
import java.util.Iterator;
import java.util.List;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Aulas;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Profesores;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Reservas;

public class Modelo {

	private Profesores profesores;
	private Aulas aulas;
	private Reservas reservas;

	public Modelo() {

		profesores = new Profesores();
		aulas = new Aulas();
		reservas = new Reservas();
	}

	public List<Aula> getAulas() {

		return aulas.getAulas();
	}

	public int getNumAulas() {
		return aulas.getNumAulas();
	}

	public List<String> representarAulas() {

		List<String> listaAulas = aulas.representar();
		boolean vacio = true;

		Iterator<String> iterador = listaAulas.iterator();
		while (iterador.hasNext()) {
			String auxiliar = iterador.next();
			if (auxiliar != null) {
				vacio = false;
			}
		}
		if (vacio == true) {
			return null;
		}
		return listaAulas;
	}

	public Aula buscarAula(Aula aula) {

		Aula aulaEncontrada = aulas.buscar(aula);

		if (aulaEncontrada == null) {
			return null;
		} else {
			return new Aula(aulaEncontrada);
		}
	}

	public void insertarAula(Aula aula) throws OperationNotSupportedException {

		aulas.insertar(aula);
	}

	public void borrarAula(Aula aula) throws OperationNotSupportedException {

		aulas.borrar(aula);
	}

	public List<Profesor> getProfesores() {

		return profesores.getProfesores();
	}

	public int getNumProfesores() {

		return profesores.getNumProfesores();
	}

	public List<String> representarProfesores() {

		List<String> listaProfesores = profesores.representar();
		boolean vacio = true;
		Iterator<String> iterador = listaProfesores.iterator();
		while (iterador.hasNext()) {
			String auxiliar = iterador.next();
			if (auxiliar != null) {
				vacio = false;
			}
		}

		if (vacio == true) {
			return null;
		}
		return listaProfesores;
	}

	public Profesor buscarProfesor(Profesor profesor) {

		Profesor profesorEncontrado = profesores.buscar(profesor);
		if (profesorEncontrado == null) {
			return null;
		}
		return new Profesor(profesorEncontrado);
	}

	public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {

		profesores.insertar(profesor);
	}

	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {

		profesores.borrar(profesor);
	}

	public List<Reserva> getReservas() {

		return reservas.getReservas();
	}

	public int getNumReservas() {

		return reservas.getNumReservas();
	}

	public List<String> representarReservas() {
		List<String> listaReservas = reservas.representar();
		boolean vacio = true;
		Iterator<String> iterador = listaReservas.iterator();
		while (iterador.hasNext()) {
			String auxiliar = iterador.next();
			if (auxiliar != null) {
				vacio = false;
			}
		}

		if (vacio == true) {
			return null;
		}
		return listaReservas;
	}

	public Reserva buscarReserva(Reserva reserva) {

		Reserva reservaEncontrada = reservas.buscar(reserva);
		if (reservaEncontrada == null) {
			return null;
		}
		return new Reserva(reservaEncontrada);
	}

	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {

		reservas.insertar(reserva);
	}

	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {

		reservas.borrar(reserva);
	}

	public List<Reserva> getReservasAula(Aula aula) {

		List<Reserva> reservasAula = reservas.getReservasAula(aula);
		boolean vacio = true;
		Iterator<Reserva> iterador = reservasAula.iterator();
		while (iterador.hasNext()) {
			Reserva auxiliar = iterador.next();
			if (auxiliar != null) {
				vacio = false;
			}
		}

		if (vacio == true) {
			return null;
		}
		return reservasAula;
	}

	public List<Reserva> getReservasProfesor(Profesor profesor) {

		List<Reserva> reservasProfesor = reservas.getReservasProfesor(profesor);
		boolean vacio = true;
		Iterator<Reserva> iterador = reservasProfesor.iterator();
		while (iterador.hasNext()) {
			Reserva auxiliar = iterador.next();
			if (auxiliar != null) {
				vacio = false;
			}
		}

		if (vacio == true) {
			return null;
		}
		return reservasProfesor;
	}

	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {

		List<Reserva> reservasPermanencia = reservas.getReservasPermanencia(permanencia);
		boolean vacio = true;
		Iterator<Reserva> iterador = reservasPermanencia.iterator();
		while (iterador.hasNext()) {
			Reserva auxiliar = iterador.next();
			if (auxiliar != null) {
				vacio = false;
			}
		}

		if (vacio == true) {
			return null;
		}
		return reservasPermanencia;
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {

		return reservas.consultarDisponibilidad(aula, permanencia);
	}
}
