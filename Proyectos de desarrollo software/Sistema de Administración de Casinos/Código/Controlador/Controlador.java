//
//  Created by Jonathan Carrero.
//  Copyright (c) Jonathan Carrero. All rights reserved.
//
package Controlador;

import Vista.Observer;

public interface Controlador {
	/** @param obs */
	public void registraObservador(Observer obs);
	/** @return */
	public String getNombreJuego();
	/** @return */
	public int getNumeroMesa();
	/** @param consulta */
	public void insertarDatos(String consulta);
	/** @param consulta */
	public void borrarDatos(String consulta);
	/** @param consulta */
	public void consultarDatos(String consulta);
}
