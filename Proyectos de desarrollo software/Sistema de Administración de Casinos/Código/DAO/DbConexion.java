//
//  Created by Jonathan Carrero.
//  Copyright (c) Jonathan Carrero. All rights reserved.
//
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DbConexion{
	Connection conexion = null;
	
	/**
	 * Lleva a cabo la conexión con la base de datos
	 */
    public DbConexion() {
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            conexion = DriverManager.getConnection("jdbc:odbc:Prueba");
        }
        catch ( Exception e )
        {
        	final JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel, "Fallo en el acceso a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Retorna la conexión
     * @return
     */
    public Connection getConnection(){
        return conexion;
    }
    /**
     * Desconecta la base de datos
     */
    public void desconectar(){
        conexion = null;
    }
}
