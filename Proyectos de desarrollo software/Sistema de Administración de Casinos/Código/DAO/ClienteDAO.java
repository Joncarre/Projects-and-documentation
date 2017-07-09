//
//  Created by Jonathan Carrero.
//  Copyright (c) Jonathan Carrero. All rights reserved.
//
package DAO;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ClienteDAO implements DAOInterfaz{
	
	/**
	 * Registra un nuevo cliente
	 */
	public void registrarPersona(String insercion){
		DbConexion conexion= new DbConexion();
		try {
			Statement st = conexion.getConnection().createStatement();
			st.executeUpdate(insercion);
			JOptionPane.showMessageDialog(null, "Se ha registrado la persona","Información",JOptionPane.INFORMATION_MESSAGE);
			st.close();
			conexion.desconectar();
	    
		} catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "No se Registro el cliente");
		}
	}
	/**
	 * Elimina un cliente
	 */
	public void eliminarPersona(String insercion){
		DbConexion conexion= new DbConexion();
		try{
			try {
				Statement st = conexion.getConnection().createStatement();
				st.executeUpdate(insercion);
				JOptionPane.showMessageDialog(null, "Se ha eliminado el cliente","Información",JOptionPane.INFORMATION_MESSAGE);
				st.close();
				conexion.desconectar();
			} catch (SQLException e) {
		        JOptionPane.showMessageDialog(null, "No se eliminó el cliente", "Información",JOptionPane.INFORMATION_MESSAGE);
			}
		}catch (java.lang.NullPointerException e2) {
	        JOptionPane.showMessageDialog(null, "No se eliminó el cliente", "Información",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	/**
	 * Lista los clientes
	 */
	public void listaDePersonas(String verconsulta) {
		  String Resultado = "";
		  DbConexion conex= new DbConexion();
		  try {
			   PreparedStatement consulta = conex.getConnection().prepareStatement(verconsulta);
			   ResultSet res = consulta.executeQuery();
			   while(res.next()){
				   Resultado = Resultado + "DNI: " + res.getString("DNI")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						   +"Nombre: " +  res.getString("Nombre")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + 
							 "Juego: " + res.getString("Juego")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" 
						   + "Mesa: " + res.getInt("Mesa") + "<br>" + "<br>";
			    }
				JFrame frame = new JFrame();
			    JLabel jlabel = new JLabel("<html>" + Resultado + "</html>");
			    jlabel.setPreferredSize(new Dimension(400, 300));
			    frame.add(jlabel, BorderLayout.CENTER);
			    frame.setSize(450, 300);
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);
			    res.close();
			    consulta.close();
			    conex.desconectar();
		  } catch (Exception e) {
			  final JPanel panel = new JPanel();
			  JOptionPane.showMessageDialog(panel, "No se pudo realizar la consulta", "Error", JOptionPane.ERROR_MESSAGE);
		  }
	} 
}

