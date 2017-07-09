//
//  Created by Jonathan Carrero.
//  Copyright (c) Jonathan Carrero. All rights reserved.
//
package main;

import javax.swing.JFrame;
import Vista.VistaInicial;

/**
 * 
 * @author Jonathan Carrero
 *
 */
public class main {
	public static void main (String[] args){
		VistaInicial ventana = new VistaInicial();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		ventana.pack();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
