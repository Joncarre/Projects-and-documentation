//
//  Created by Jonathan Carrero.
//  Copyright (c) Jonathan Carrero. All rights reserved.
//
package Controlador;

import DAO.GerenteDAO;
import Modelo.Gerente;
import Vista.Observer;

public class ControladorGerente implements Controlador{
	private Gerente gerente;
	private GerenteDAO gerenteDAO;
	
	/**
	 * Controlador de gerente
	 * @param gerente
	 */
	public ControladorGerente(Gerente gerente){
		this.gerente = gerente;
		this.gerenteDAO = new GerenteDAO();
	}
	/**
	 * Cambia el juego del gerente
	 * @param juego
	 */
	public void cambiarJuego(String juego){
		this.gerente.reset(juego, 0);
	}
	/**
	 * Registra un observador en el array de observadores
	 */
	public void registraObservador(Observer obs){
		this.gerente.addObservador(obs);
	}
	/**
	 * Retorna el nombre del juego del gerente
	 */
	public String getNombreJuego(){
		return this.gerente.getJuego();
	}
	/**
	 * Modifica el juego del gerente
	 * @param juego
	 */
	public void setJuego(String juego){
		this.gerente.setJuego(juego);
	}
	/**
	 * Retorna el número de mesa del gerente
	 */
	public int getNumeroMesa() {
		return 0;
	}
	
	public void setJuegoYMesa(String juego, int mesa) {
		// TODO Auto-generated method stub
	}

	public void insertarDatos(String consulta) {
		// TODO Auto-generated method stub	
	}

	public void borrarDatos(String consulta) {
		// TODO Auto-generated method stub	
	}
	/**
	 * Consulta los datos del gerente
	 */
	public void consultarDatos(String consulta) {
		gerenteDAO.listaDePersonas(consulta);	
	}
}
