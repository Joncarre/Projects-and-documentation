//
//  Created by Jonathan Carrero.
//  Copyright (c) Jonathan Carrero. All rights reserved.
//
package Controlador;

import DAO.ClienteDAO;
import Modelo.Cliente;
import Vista.Observer;

public class ControladorCliente implements Controlador{
	private Cliente cliente;
	private ClienteDAO clienteDAO;
	
	/**
	 * Controlador del cliente
	 * @param cliente
	 */
	public ControladorCliente(Cliente cliente){
		this.cliente = cliente;
		this.clienteDAO = new ClienteDAO();
	}
	/**
	 * Método que permite cambiar de juego
	 * @param juego
	 * @param mesa
	 */
	public void cambiarJuego(String juego, int mesa){
		this.cliente.reset(juego, mesa);
	}
	/**
	 * Añade un observador al array de observadores 
	 */
	public void registraObservador(Observer obs){
		this.cliente.addObservador(obs);
	}
	/**
	 * Retorna el nombre del juego
	 */
	public String getNombreJuego(){
		return this.cliente.getJuego();
	}
	/**
	 * Retorna el número de mesa
	 */
	public int getNumeroMesa(){
		return this.cliente.getMesa();
	}
	/**
	 * Modifica el juego y la mesa de un cliente
	 * @param juego
	 * @param mesa
	 */
	public void setJuegoYMesa(String juego, int mesa){
		this.cliente.setJuego(juego);
		this.cliente.setMesa(mesa);
	}
	/**
	 * Modifica el juego de un cliente
	 * @param juego
	 */
	public void setJuego(String juego){
		this.cliente.setJuego(juego);
	}
	/**
	 * Modifica el nombre y DNI de un cliente
	 * @param nombre
	 * @param DNI
	 */
	public void setDNIyNombre(String nombre, String DNI){
		this.cliente.setNombre(nombre);
		this.cliente.setDNI(DNI);
	}
	/**
	 * Inserta los datos del cliente
	 */
	public void insertarDatos(String consulta) {
		this.clienteDAO.registrarPersona(consulta);
	}
	/**
	 * Elimina los datos del cliente
	 */
	public void borrarDatos(String consulta) {
		this.clienteDAO.eliminarPersona(consulta);
	}
	/**
	 * Consulta los datos del cliente
	 */
	public void consultarDatos(String consulta) {
		clienteDAO.listaDePersonas(consulta);
	}
}
