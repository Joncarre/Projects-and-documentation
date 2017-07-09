//
//  Created by Jonathan Carrero.
//  Copyright (c) Jonathan Carrero. All rights reserved.
//
package Modelo;

import java.util.ArrayList;
import java.util.Iterator;

import Vista.Observer;

public class Gerente {
	
	private String DNI;
	private String nombre;
	private String juego;
	private ArrayList<Observer> obs = new ArrayList<Observer>();
	
	/**
	 * Modifica el DNI
	 * @param DNI
	 */
	public void setDNI(String DNI){
		this.DNI = DNI;
	}
	/**
	 * Modifica el nombre
	 * @param nombre
	 */
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	/**
	 * Retorna el DNI
	 * @return
	 */
	public String getDNI(){
		return this.DNI;
	}
	/**
	 * Retorna el nombre
	 * @return
	 */
	public String getNombre(){
		return this.nombre;
	}
	/**
	 * Retorna el juego
	 * @return
	 */
	public String getJuego(){
		return this.juego;
	}
	/**
	 * Modifica el juego
	 * @param juego
	 */
	public void setJuego(String juego) {
		this.juego = juego;
		
	}
	/**
	 * Añade un observador al array de observadores
	 * @param o
	 */
	public void addObservador(Observer o){
		if (!obs.contains(o)) {
			obs.add(o);
		}
	}
	/**
	 * Elimina un observador del array de observadores
	 * @param o
	 */
	public void removeObservador(Observer o){
		obs.remove(o);
	}
	/**
	 * Resetea el cliente
	 * @param juego
	 * @param mesa
	 */
	public void reset(String juego, int mesa) {
		Iterator<Observer> iterator = obs.iterator();
		while(iterator.hasNext())
			iterator.next().onCambioJuego();
	}
}
