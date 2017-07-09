//
//  Created by Jonathan Carrero.
//  Copyright (c) Jonathan Carrero. All rights reserved.
//
package DAO;

public interface DAOInterfaz {
	/** @param insercion */
	public void registrarPersona(String insercion);
	/** @param insercion */
	public void eliminarPersona(String insercion);
	/** @param verconsulta */
	public void listaDePersonas(String verconsulta);
}
