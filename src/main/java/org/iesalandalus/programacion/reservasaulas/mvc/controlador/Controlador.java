package org.iesalandalus.programacion.reservasaulas.mvc.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.Vista;

public class Controlador {

	Vista vista;
	Modelo modelo;

	public Controlador(Modelo modelo, Vista vista) {

		if (modelo == null) {
			throw new IllegalArgumentException("ERROR: El modelo no puede ser nulo.");
		}
		if (vista == null) {
			throw new IllegalArgumentException("ERROR: La vista no puede ser nula.");
		}
		this.modelo = modelo;
		this.vista = vista;
		this.vista.setControlador(this);
	}

	public void comenzar() throws OperationNotSupportedException {
		vista.comenzar();
	}

	public void terminar() {
		System.out.println("Adiós");
	}

	public void insertar(Profesor profesor) throws OperationNotSupportedException {

		modelo.insertarProfesor(profesor);
	}

	public void insertarAula(Aula aula) throws OperationNotSupportedException {

	modelo.insertarAula(aula);

	}

	public void borrarAula(Aula aula) throws OperationNotSupportedException {

		modelo.borrarAula(aula);
	}

	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {

		modelo.borrarProfesor(profesor);
	}

	public Aula buscarAula(Aula aula) {

		Aula aulaBuscada = modelo.buscarAula(aula);

		return aulaBuscada;
	}

	public Profesor buscarProfesor(Profesor profesor) {

		Profesor profesorBuscado = modelo.buscarProfesor(profesor);

		return profesorBuscado;
	}

	public List<String> representarAulas() {

		List<String> listaAulas = modelo.representarAulas();

		return listaAulas;
	}

	public List<String> representarProfesores() {

		List<String> listaProfesores = modelo.representarProfesores();

		return listaProfesores;
	}

	public List<String> representarReservas() {

		List<String> listaReservas = modelo.representarReservas();

		return listaReservas;
	}

	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {

		modelo.realizarReserva(reserva);
	}

	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {

		modelo.anularReserva(reserva);
	}

	public List<Reserva> getReservasAula(Aula aula) {

		List<Reserva> reservasAula = modelo.getReservasAula(aula);

		return reservasAula;
	}

	public List<Reserva> getReservasProfesor(Profesor profesor) {
		
		List<Reserva> reservasProfesor = modelo.getReservasProfesor(profesor);
		
		return reservasProfesor;
	}

	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {

		List<Reserva> reservasPermanencia = modelo.getReservasPermanencia(permanencia);

		return reservasPermanencia;
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		
		boolean disponibilidad = modelo.consultarDisponibilidad(aula, permanencia);
		
		return disponibilidad;
	}
}
