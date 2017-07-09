//
//  Created by Jonathan Carrero.
//  Copyright (c) Jonathan Carrero. All rights reserved.
//
package Vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controlador.ControladorCliente;
import Controlador.ControladorCrupier;
import Controlador.ControladorGerente;
import Controlador.ControladorJefeJuego;
import Modelo.Cliente;
import Modelo.Crupier;
import Modelo.Gerente;
import Modelo.JefeDeJuego;

public class VistaInicial extends JFrame{

	private static final long serialVersionUID = -4006508054845555957L;
	private static int DEFAULT_WIDTH = 700;
	private static int DEFAULT_HEIGTH = 300;
	
	/**
	 * Constructora
	 */
	public VistaInicial (){
		super("Sistema de Administración de Casinos");
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGTH);
		Container window = getContentPane();
		setResizable(false);
		window.add(new MainPanel());
	}
	
	private class MainPanel extends JPanel{
		private static final long serialVersionUID = 7142769421975855663L;
		/**
		 * 
		 */
		public MainPanel(){
			setLayout(new BorderLayout());
			JPanel leftPanel = new LeftPanel();
			JPanel rightPanel = new RightPanel();
			add(leftPanel,BorderLayout.NORTH);
			add(rightPanel,BorderLayout.SOUTH);
		}
	}
	
	private class LeftPanel extends JPanel{
		private static final long serialVersionUID = 2274994820120753960L;
		private JButton botonGerente = new JButton("Acceder como Gerente");
		private JButton botonJefeDeMesa = new JButton("Acceder como J.Juego");
		/**
		 * 
		 */
		public LeftPanel(){
			setLayout(new BorderLayout());
			botonGerente.setPreferredSize(new Dimension(320,150));
			botonGerente.setIcon(new ImageIcon(getClass().getResource("/iconos/gerente.png")));
			botonJefeDeMesa.setPreferredSize(new Dimension(320,150));
			botonJefeDeMesa.setIcon(new ImageIcon(getClass().getResource("/iconos/jefe.png")));
			add(botonGerente, BorderLayout.WEST);
			add(botonJefeDeMesa, BorderLayout.EAST);
			botonGerente.addActionListener(new ActionListener(){
				/**
				* Método para llevar al cabo el actionPerformed
				*/
				public void actionPerformed(ActionEvent e) {
					Gerente gerente = new Gerente();
					ControladorGerente ctrlGerente = new ControladorGerente(gerente);
					JefeDeJuego jefeJuego = new JefeDeJuego();
					ControladorJefeJuego ctrlJefeJuego = new ControladorJefeJuego(jefeJuego);
					Crupier crupier = new Crupier();
					ControladorCrupier ctrlCrupier = new ControladorCrupier(crupier);
					Cliente cliente = new Cliente();
					ControladorCliente ctrlCliente = new ControladorCliente(cliente);
					VistaGerente ventana = new VistaGerente(ctrlGerente, ctrlCliente, ctrlCrupier, ctrlJefeJuego);
					ventana.setLocationRelativeTo(null); // Para centrar la ventana
					ventana.setVisible(true);
					ventana.pack();
				}
			});
			
			botonJefeDeMesa.addActionListener(new ActionListener(){
				/**
				* Método para llevar al cabo el actionPerformed
				*/
				public void actionPerformed(ActionEvent e) {
					JefeDeJuego jefeJuego = new JefeDeJuego();
					ControladorJefeJuego ctrlJefeJuego = new ControladorJefeJuego(jefeJuego);
					VistaJefeDeJuego ventana = new VistaJefeDeJuego(ctrlJefeJuego);
					ventana.setLocationRelativeTo(null); // Para centrar la ventana
					ventana.setVisible(true);
					ventana.pack();
				}
			});
		}
	}
	
	private class RightPanel extends JPanel{
		private static final long serialVersionUID = -4933942442821191312L;
		private JButton botonCrupier = new JButton("Acceder como Crupier");
		private JButton botonCliente = new JButton("Acceder como Cliente");
		/**
		 * 
		 */
		public RightPanel(){
			setLayout(new BorderLayout());
			botonCrupier.setPreferredSize(new Dimension(320,150));
			botonCrupier.setIcon(new ImageIcon(getClass().getResource("/iconos/crupier.png")));
			botonCliente.setPreferredSize(new Dimension(320,150));
			botonCliente.setIcon(new ImageIcon(getClass().getResource("/iconos/cliente.png")));
			add(botonCrupier, BorderLayout.WEST);
			add(botonCliente, BorderLayout.EAST);
			
			botonCrupier.addActionListener(new ActionListener(){
				/**
				* Método para llevar al cabo el actionPerformed
				*/
				public void actionPerformed(ActionEvent e) {
					Crupier crupier = new Crupier();
					ControladorCrupier ctrlCrupier = new ControladorCrupier(crupier);
					VistaCrupier ventana = new VistaCrupier(ctrlCrupier);
					ventana.setLocationRelativeTo(null); // Para centrar la ventana
					ventana.setVisible(true);
					ventana.pack();
				}
			});
			botonCliente.addActionListener(new ActionListener(){
				/**
				* Método para llevar al cabo el actionPerformed
				*/
				public void actionPerformed(ActionEvent e) {
					Cliente cliente = new Cliente();
					ControladorCliente ctrlCliente = new ControladorCliente(cliente);
					VistaCliente ventana = new VistaCliente(ctrlCliente);
					ventana.setLocationRelativeTo(null); // Para centrar la ventana
					ventana.setVisible(true);
					ventana.pack();
				}
			});
		}
	}
}
