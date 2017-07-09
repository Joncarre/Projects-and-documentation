//
//  Created by Jonathan Carrero.
//  Copyright (c) Jonathan Carrero. All rights reserved.
//
package Controlador;

import DAO.CrupierDAO;
import Modelo.Crupier;
import Vista.Observer;

public class ControladorCrupier implements Controlador{
	private Crupier crupier;
	private CrupierDAO crupierDAO;
	
	/**
	 * Controlador del crupier
	 * @param crupier
	 */
	public ControladorCrupier(Crupier crupier){
		this.crupier = crupier;
		this.crupierDAO = new CrupierDAO();
	}
	/**
	 * Modifica el juego del crupier
	 * @param juego
	 * @param mesa
	 */
	public void cambiarJuego(String juego, int mesa){
		this.crupier.reset(juego, mesa);
	}
	/**
	 * Registra el observador en el array de observadores
	 */
	public void registraObservador(Observer obs){
		this.crupier.addObservador(obs);
	}
	/**
	 * Retorna el juego
	 */
	public String getNombreJuego(){
		return this.crupier.getJuego();
	}
	/**
	 * Retorna el número de mesa
	 */
	public int getNumeroMesa(){
		return this.crupier.getMesa();
	}
	/**
	 * Modifica el juego y la mesa del crupier
	 * @param juego
	 * @param mesa
	 */
	public void setJuegoYMesa(String juego, int mesa){
		this.crupier.setJuego(juego);
		this.crupier.setMesa(mesa);
	}
	/**
	 * Modifica el juego
	 * @param juego
	 */
	public void setJuego(String juego){
		this.crupier.setJuego(juego);
	}
	/**
	 * Modifica el nombre y DNI del crupier
	 * @param nombre
	 * @param DNI
	 */
	public void setDNIyNombre(String nombre, String DNI){
		this.crupier.setNombre(nombre);
		this.crupier.setDNI(DNI);
	}
	/**
	 * Inserta los datos de un crupier
	 */
	public void insertarDatos(String consulta) {
		this.crupierDAO.registrarPersona(consulta);
		
	}
	/**
	 * Elimina los datos de un crupier
	 */
	public void borrarDatos(String consulta) {
		this.crupierDAO.eliminarPersona(consulta);
		
	}
	/**
	 * Consulta los datos de un crupier
	 */
	public void consultarDatos(String consulta) {
		crupierDAO.listaDePersonas(consulta);
	}
}
