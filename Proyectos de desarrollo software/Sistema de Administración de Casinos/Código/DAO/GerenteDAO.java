//
//  Created by Jonathan Carrero.
//  Copyright (c) Jonathan Carrero. All rights reserved.
//
package DAO;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GerenteDAO  implements DAOInterfaz{
	
	/**
	 * Registra a un nuevo gerente
	 */
	public void registrarPersona(String insercion){
		DbConexion conexion= new DbConexion();
		try {
			Statement st = conexion.getConnection().createStatement();
			st.executeUpdate(insercion);
			JOptionPane.showMessageDialog(null, "Se ha registrado la persona","Información",JOptionPane.INFORMATION_MESSAGE);
			st.close();
			conexion.desconectar();
	    
		} catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "No se Registro el Gerente");
		}
	}
	/**
	 * Elimina a un gerente
	 */
	public void eliminarPersona(String insercion){
		DbConexion conexion= new DbConexion();
		try {
			Statement st = conexion.getConnection().createStatement();
			st.executeUpdate(insercion);
			JOptionPane.showMessageDialog(null, "Se ha eliminado el Gerente","Información",JOptionPane.INFORMATION_MESSAGE);
			st.close();
			conexion.desconectar();
	    
		} catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "No se eliminó el Gerente", "Información",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	/**
	 * Lista los gerentes
	 */
	public void listaDePersonas(String verconsulta) {
		  DbConexion conex= new DbConexion();
		  String Resultado = "";
		  try {
			   PreparedStatement consulta = conex.getConnection().prepareStatement(verconsulta);
			   ResultSet res = consulta.executeQuery();
			   while(res.next()){
				   Resultado = Resultado + "DNI: " + res.getString("DNI")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						   +"Nombre: " +  res.getString("Nombre")+ "<br>" + "<br>";
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
