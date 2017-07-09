//
//  Created by Jonathan Carrero.
//  Copyright (c) Jonathan Carrero. All rights reserved.
//
package Controlador;

import DAO.JefeJuegoDAO;
import Modelo.JefeDeJuego;
import Vista.Observer;

public class ControladorJefeJuego implements Controlador{
	private JefeDeJuego jefeJuego;
	private JefeJuegoDAO jefeJuegoDAO;
	
	/**
	 * Controlador del jefe de juego
	 * @param jefeJuego
	 */
	public ControladorJefeJuego(JefeDeJuego jefeJuego){
		this.jefeJuego = jefeJuego;
		this.jefeJuegoDAO = new JefeJuegoDAO();
	}
	/**
	 * Registra un observador en el array de observadores
	 */
	public void registraObservador(Observer obs){
		this.jefeJuego.addObservador(obs);
	}
	/**
	 * Retorna el nombre del juego del jefe de juego
	 */
	public String getNombreJuego(){
		return this.jefeJuego.getJuego();
	}
	/**
	 * Modifica el nombre del juego del jefe de juego
	 * @param juego
	 */
	public void setJuego(String juego){
		this.jefeJuego.setJuego(juego);
	}
	/**
	 * Cambia el juego del jefe de juego
	 * @param juego
	 * @param mesa
	 */
	public void cambiarJuego(String juego, int mesa) {
		this.jefeJuego.reset(juego, mesa);
		
	}
	/**
	 * Retorna el número de mesa del jefe de juego
	 */
	public int getNumeroMesa() {
		return 0;
	}
	/**
	 * Modifica el nombre y DNI del jefe de juego
	 * @param nombre
	 * @param DNI
	 */
	public void setDNIyNombre(String nombre, String DNI){
		this.jefeJuego.setNombre(nombre);
		this.jefeJuego.setDNI(DNI);
	}
	/**
	 * Inserta los datos del jefe de juego
	 */
	public void insertarDatos(String consulta) {
		this.jefeJuegoDAO.registrarPersona(consulta);
		
	}
	/**
	 * Elimina los datos del jefe de juego
	 */
	public void borrarDatos(String consulta) {
		this.jefeJuegoDAO.eliminarPersona(consulta);
	}
	/**
	 * Consulta los datos del jefe de juego
	 */
	public void consultarDatos(String consulta) {
		jefeJuegoDAO.listaDePersonas(consulta);
	}
}
