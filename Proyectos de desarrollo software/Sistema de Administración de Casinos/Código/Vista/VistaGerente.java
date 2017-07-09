//
//  Created by Jonathan Carrero.
//  Copyright (c) Jonathan Carrero. All rights reserved.
//
package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Controlador.ControladorCliente;
import Controlador.ControladorCrupier;
import Controlador.ControladorGerente;
import Controlador.ControladorJefeJuego;

public class VistaGerente extends JFrame implements Observer {
	private static final long serialVersionUID = 6937853572224085296L;
	private static int DEFAULT_WIDTH = 900;
	private static int DEFAULT_HEIGTH = 500;
	private ControladorGerente ctrl;
	private ControladorCliente ctrlCliente;
	private ControladorCrupier ctrlCrupier;
	private ControladorJefeJuego ctrlJefeJuego;
	
	/**
	 * Constructora
	 * @param ctrl
	 * @param ctrlCliente
	 * @param ctrlCrupier
	 * @param ctrlJefeJuego
	 */
	public VistaGerente (ControladorGerente ctrl, ControladorCliente ctrlCliente, ControladorCrupier ctrlCrupier, ControladorJefeJuego ctrlJefeJuego){
		super ("Gerente");
		this.ctrl = ctrl;
		this.ctrlCliente = ctrlCliente;
		this.ctrlCrupier = ctrlCrupier;
		this.ctrlJefeJuego = ctrlJefeJuego;
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGTH);
		Container window = getContentPane();
		setResizable(false);
		window.add(new MainPanel());
		ctrl.registraObservador(this);
	}
	
	private class MainPanel extends JPanel{
		private static final long serialVersionUID = -8850532418596722922L;
		/**
		 * 
		 */
		public MainPanel(){
			setLayout(new BorderLayout());
			// Panel izquierdo y derecho, el izquiedo con borde
			JPanel leftPanel = new LeftPanel();
			JPanel rightPanel = new RightPanel();
			Border borderLeftPanel = new TitledBorder(new EtchedBorder());
			leftPanel.setBorder(borderLeftPanel);
			add(leftPanel,BorderLayout.WEST);
			add(rightPanel,BorderLayout.EAST);
		}
	}
	
	private class LeftPanel extends JPanel{
		private static final long serialVersionUID = -4549683667624337434L;
		private JTextField turnoText;
		// Panel para el tablero
		private JPanel scorePanel;
		private JButton botonConsultar = new JButton("Consultar Datos Gerente");
		/**
		 * 
		 */
		public LeftPanel(){
			setLayout(new BorderLayout());
			this.turnoText = new JTextField();
			scorePanel = new ScorePanel();
			botonConsultar.setPreferredSize(new Dimension(150,50));
			botonConsultar.setIcon(new ImageIcon(getClass().getResource("/iconos/consultar.png")));
			turnoText.setEditable(false);
			turnoText.setHorizontalAlignment(JTextField.CENTER);
			turnoText.setForeground(Color.blue);
			turnoText.setFont(new java.awt.Font("Tahoma", 0, 23)); 
			turnoText.setPreferredSize(new Dimension(300, 50));
			ctrl.setJuego("RULETA");
			ctrlCliente.setJuego("RULETA");
			ctrlJefeJuego.setJuego("RULETA");
			ctrlCrupier.setJuego("RULETA");
			turnoText.setText("Usted está en la " + ctrl.getNombreJuego());
			add(scorePanel, BorderLayout.NORTH);
			add(turnoText,BorderLayout.CENTER);
			add(botonConsultar,BorderLayout.SOUTH);
			botonConsultar.addActionListener(new ActionListener(){
				/**
				* Método para llevar al cabo el actionPerformed
				*/
				public void actionPerformed(ActionEvent e) {
					String consulta;
					consulta = ("SELECT * FROM Gerente");
					ctrl.consultarDatos(consulta);
				}
			});
		}
		
		private class ScorePanel extends JPanel implements Observer{
			private static final long serialVersionUID = -4688191352890914808L;
			private JButton Juego = new JButton();
			/**
			 * 
			 */
			public ScorePanel(){
				this.setPreferredSize(new Dimension(400,400));
				ctrl.registraObservador(this);
				Juego.setIcon(new ImageIcon(getClass().getResource("/images/RULETA.jpg")));
				add(Juego);
				revalidate();
			}
			/**
			 * 
			 */
			private void iniciarScorePanel (){
				this.setPreferredSize(new Dimension(400,400));
				Juego.setIcon(new ImageIcon(getClass().getResource("/images/" + ctrl.getNombreJuego() + ".jpg")));
				add(Juego);
				if (ctrl.getNombreJuego() == "RULETA"){
					turnoText.setText("Usted está en la " + ctrl.getNombreJuego());
					
				}else{
					turnoText.setText("Usted está en el " + ctrl.getNombreJuego());
				}
			}

			@Override
			public void onReset(String juego, int mesa) {
				
				
			}

			@Override
			public void onCambioJuego() {
				Juego.removeAll();
				iniciarScorePanel();
				
			}

		}
	}
	
	private class RightPanel extends JPanel{
		private static final long serialVersionUID = -3453683903414197614L;
		// Panel Partida
		private JPanel controlPanel = new GrupoControlPanel();
		// Panel para el botón salir
		private JPanel SalirPanel = new SalirPanel();
		// Panel para los componentes de cambio de juego
		private JPanel cambioJugadoresYJuegoPanel= new GrupoCambioJugadoresYJuegoPanel();
		/**
		 * 
		 */
		public RightPanel(){
			setLayout(new BorderLayout());
			Border borderPartidaPanel = new TitledBorder(new EtchedBorder(), "Gestión de Personal");
			controlPanel.setBorder(borderPartidaPanel);
			add(controlPanel, BorderLayout.NORTH);
			add(cambioJugadoresYJuegoPanel, BorderLayout.CENTER);
			add(SalirPanel, BorderLayout.SOUTH);	
		}
	}
	
	private class GrupoControlPanel extends JPanel{
		private static final long serialVersionUID = -3453683903414197614L;
		// Panel Partida
		private JPanel crupieresPanel = new GrupoCrupieresPanel();
		// Panel para el botón salir
		private JPanel jefesPanel = new GrupoJefesPanel();
		// Panel para los componentes de cambio de juego
		private JPanel clientesPanel = new GrupoClientesPanel();
		// Private JPanel cambioJugadoresYJuegoPanel = new GrupoCambioJugadoresYJuegoPanel();
		/**
		 * 
		 */
		public GrupoControlPanel(){
			setLayout(new BorderLayout());
			add(jefesPanel, BorderLayout.NORTH);
			add(crupieresPanel, BorderLayout.CENTER);
			add(clientesPanel, BorderLayout.SOUTH);
		}
	}

	private class GrupoJefesPanel extends JPanel{
		private static final long serialVersionUID = 7656487254403788246L;
		private JButton botonDarDeAlta = new JButton("Contratar J.Juego");
		private JButton botonEchar = new JButton("Echar J.Juego");
		/**
		 * 
		 */
		public GrupoJefesPanel(){
			setLayout(new FlowLayout());
			botonDarDeAlta.setPreferredSize(new Dimension(200,60));
			botonEchar.setPreferredSize(new Dimension(200,60));
			botonDarDeAlta.setIcon(new ImageIcon(getClass().getResource("/iconos/añadir.png")));
			botonEchar.setIcon(new ImageIcon(getClass().getResource("/iconos/quitar.png")));
			add(botonDarDeAlta);
			add(botonEchar);
			botonDarDeAlta.addActionListener(new ActionListener(){
				/**
				* Método para llevar al cabo el actionPerformed
				*/
				public void actionPerformed(ActionEvent e) {
					String nombre;
					nombre = JOptionPane.showInputDialog (null, "Escribe su nombre");
					if (!nombre.isEmpty()){
						String DNI = JOptionPane.showInputDialog (null, "Escribe su DNI");
						if (!DNI.isEmpty()){
							ctrlJefeJuego.setDNIyNombre(nombre, DNI);
							String consulta;
							consulta = ("INSERT INTO JefeJuego VALUES ('" + DNI + "', '" + nombre + "', '" +
							ctrlJefeJuego.getNombreJuego() + "')");
							ctrlJefeJuego.insertarDatos(consulta);
						}
						else{
							 final JPanel panel = new JPanel();
								JOptionPane.showMessageDialog(panel, "No ha ingresado el DNI",
										"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else{
						 final JPanel panel = new JPanel();
						JOptionPane.showMessageDialog(panel, "No ha ingresado el Nombre",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});

			botonEchar.addActionListener(new ActionListener(){
				/**
				* Método para llevar al cabo el actionPerformed
				*/
				public void actionPerformed(ActionEvent e) {
					String nombre;
					nombre = JOptionPane.showInputDialog ("Escribe tu nombre:");
					if (!nombre.isEmpty()){
						String consulta;
						consulta = ("DELETE * FROM JefeJuego WHERE Nombre = '" + nombre + "'");
						ctrlJefeJuego.borrarDatos(consulta);
					}
					else{
						 final JPanel panel = new JPanel();
						JOptionPane.showMessageDialog(panel, "No ha ingresado el Nombre",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
	}
	
	private class GrupoCrupieresPanel extends JPanel{
		private static final long serialVersionUID = 7656487254403788246L;
		private JButton botonDarDeAlta = new JButton("Contratar Crupier");
		private JButton botonEchar = new JButton("Echar Crupier");
		/**
		 * 
		 */
		public GrupoCrupieresPanel(){
			setLayout(new FlowLayout());
			botonDarDeAlta.setPreferredSize(new Dimension(200,60));
			botonEchar.setPreferredSize(new Dimension(200,60));
			botonDarDeAlta.setIcon(new ImageIcon(getClass().getResource("/iconos/añadir.png")));
			botonEchar.setIcon(new ImageIcon(getClass().getResource("/iconos/quitar.png")));
			add(botonDarDeAlta);
			add(botonEchar);
			botonDarDeAlta.addActionListener(new ActionListener(){
				/**
				* Método para llevar al cabo el actionPerformed
				*/
				public void actionPerformed(ActionEvent e) {
					String nombre;
					nombre = JOptionPane.showInputDialog (null, "Escribe su nombre");
					if (!nombre.isEmpty()){
						String DNI = JOptionPane.showInputDialog (null, "Escribe su DNI");
						if (!DNI.isEmpty()){
							String mesa = JOptionPane.showInputDialog ("Escribe la mesa:");
							try{
								if (mesa.isEmpty()){
									 final JPanel panel = new JPanel();
										JOptionPane.showMessageDialog(panel,
												"No ha ingresado el numero de la mesa", "Error", JOptionPane.ERROR_MESSAGE);
								}
								int Mesa = Integer.parseInt(mesa);
								if (Mesa > 0 && Mesa < 6){
									ctrlCrupier.setDNIyNombre(nombre, DNI);
									ctrlCrupier.setJuegoYMesa(ctrlCliente.getNombreJuego(), Mesa);;
									String consulta;
									consulta = ("INSERT INTO Crupier VALUES ('" + DNI + "', '" + nombre + "', '" +
											ctrlCrupier.getNombreJuego() + "', "+ ctrlCrupier.getNumeroMesa() + ")");
									ctrlCrupier.insertarDatos(consulta);
								}
								else{
									 final JPanel panel = new JPanel();
									JOptionPane.showMessageDialog(panel, "El número de la mesa no es correcto", "Error", JOptionPane.ERROR_MESSAGE);
								}
							}catch(Exception e1){
								final JPanel panel = new JPanel();
								JOptionPane.showMessageDialog(panel, "Inserta un dato numérico", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						else{
							 final JPanel panel = new JPanel();
								JOptionPane.showMessageDialog(panel, "No ha ingresado el DNI",
										"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else{
						 final JPanel panel = new JPanel();
						JOptionPane.showMessageDialog(panel, "No ha ingresado el Nombre",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
			botonEchar.addActionListener(new ActionListener(){
				/**
				* Método para llevar al cabo el actionPerformed
				*/
				public void actionPerformed(ActionEvent e) {
					String nombre;
					nombre = JOptionPane.showInputDialog ("Escribe tu nombre:");
					if (!nombre.isEmpty()){
						String consulta;
						consulta = ("DELETE * FROM Crupier WHERE Nombre = '" + nombre + "'");
						ctrlCrupier.borrarDatos(consulta);
					}
					else{
						 final JPanel panel = new JPanel();
						JOptionPane.showMessageDialog(panel, "No ha ingresado el Nombre",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
	}

	private class GrupoClientesPanel extends JPanel{
		private static final long serialVersionUID = 7656487254403788246L;
		private JButton botonDarDeAlta = new JButton("Invitar Cliente");
		private JButton botonEchar = new JButton("Echar Cliente");
		/**
		 * 
		 */
		public GrupoClientesPanel(){
			setLayout(new FlowLayout());
			botonDarDeAlta.setPreferredSize(new Dimension(200,60));
			botonEchar.setPreferredSize(new Dimension(200,60));
			botonDarDeAlta.setIcon(new ImageIcon(getClass().getResource("/iconos/añadir.png")));
			botonEchar.setIcon(new ImageIcon(getClass().getResource("/iconos/quitar.png")));
			add(botonDarDeAlta);
			add(botonEchar);
			botonDarDeAlta.addActionListener(new ActionListener(){
				/**
				* Método para llevar al cabo el actionPerformed
				*/
				public void actionPerformed(ActionEvent e) {
					String nombre;
					nombre = JOptionPane.showInputDialog (null, "Escribe su nombre");
					if (!nombre.isEmpty()){
						String DNI = JOptionPane.showInputDialog (null, "Escribe su DNI");
						if (!DNI.isEmpty()){
							String mesa = JOptionPane.showInputDialog ("Escribe la mesa:");
							try{
								if (mesa.isEmpty()){
									 final JPanel panel = new JPanel();
										JOptionPane.showMessageDialog(panel,
												"No ha ingresado el número de la mesa", "Error", JOptionPane.ERROR_MESSAGE);
								}
								int Mesa = Integer.parseInt(mesa);
								if (Mesa > 0 && Mesa < 6){
									ctrlCliente.setDNIyNombre(nombre, DNI);
									ctrlCliente.setJuegoYMesa(ctrlCliente.getNombreJuego(), Mesa);;
									String consulta;
									consulta = ("INSERT INTO Cliente VALUES ('" + DNI + "', '" + nombre + "', '" +
											ctrlCliente.getNombreJuego() + "', "+ ctrlCliente.getNumeroMesa() + ")");
									ctrlCliente.insertarDatos(consulta);
								}
								else{
									 final JPanel panel = new JPanel();
									JOptionPane.showMessageDialog(panel, "El número de la mesa no es correcto", "Error", JOptionPane.ERROR_MESSAGE);
								}
							}catch(Exception e1){
								final JPanel panel = new JPanel();
								JOptionPane.showMessageDialog(panel, "Inserta un dato numérico", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						else{
							 final JPanel panel = new JPanel();
								JOptionPane.showMessageDialog(panel, "No ha ingresado el DNI",
										"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else{
						 final JPanel panel = new JPanel();
						JOptionPane.showMessageDialog(panel, "No ha ingresado el Nombre",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			botonEchar.addActionListener(new ActionListener(){
				/**
				* Método para llevar al cabo el actionPerformed
				*/
				public void actionPerformed(ActionEvent e) {
					String nombre;
					nombre = JOptionPane.showInputDialog ("Escribe tu nombre:");
					if (!nombre.isEmpty()){
						String consulta;
						consulta = ("DELETE * FROM Cliente WHERE Nombre = '" + nombre + "'");
						ctrlCliente.borrarDatos(consulta);
					}
					else{
						 final JPanel panel = new JPanel();
						JOptionPane.showMessageDialog(panel, "No ha ingresado el Nombre",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
	}
	
	private class GrupoCambioJugadoresYJuegoPanel extends JPanel{
		private static final long serialVersionUID = -1297788185981079914L;
		private JPanel cambioJuegoPanel= new GrupoCambioJuegoPanel();
		/**
		 * 
		 */
		public GrupoCambioJugadoresYJuegoPanel(){
			setLayout(new BorderLayout());
			Border borderCambioJuegoPanel = new TitledBorder(new EtchedBorder(), "Cambio de Juego");
			cambioJuegoPanel.setBorder(borderCambioJuegoPanel);
			add(cambioJuegoPanel, BorderLayout.NORTH);
		}
	
		private class GrupoCambioJuegoPanel extends JPanel{
			private static final long serialVersionUID = -4949119891870494586L;
			private JPanel cambioJuegoYMesaPanel= new GrupoCambioJuegoYMesaPanel();
			/**
			 * 
			 */
			public GrupoCambioJuegoPanel(){
				setLayout(new BorderLayout());
				add(cambioJuegoYMesaPanel, BorderLayout.SOUTH);
			}
		}
	}
	
	private class GrupoCambioJuegoYMesaPanel extends JPanel{
		private static final long serialVersionUID = -4053379774350750369L;
		public JComboBox<String> comboTipoJuego = new JComboBox<String>();
		private JPanel JuegosPanel= new JuegosPanel();
		/**
		 * 
		 */
		public GrupoCambioJuegoYMesaPanel(){
			setLayout(new BorderLayout());
			add(JuegosPanel, BorderLayout.CENTER);
			comboTipoJuego.setEditable(false);
			comboTipoJuego.addItem("RULETA");
			comboTipoJuego.addItem("POKER");
			comboTipoJuego.addItem("BLACKJACK");
			comboTipoJuego.addItem("CRAPS");
			// Panel botón cambiar
			JPanel cambiarPanel = new JPanel();
			JButton botonCambiar = new JButton("Cambiar");
			botonCambiar.setPreferredSize(new Dimension(150,50));
			botonCambiar.setIcon(new ImageIcon(getClass().getResource("/iconos/cambiar.png")));
			add(cambiarPanel, BorderLayout.SOUTH);
			cambiarPanel.add(botonCambiar);
			botonCambiar.addActionListener(new ActionListener(){
				/**
				* Método para llevar al cabo el actionPerformed
				*/
				public void actionPerformed(ActionEvent e) {
					String juego = (String)comboTipoJuego.getSelectedItem();
					ctrl.setJuego(juego);
					ctrlCliente.setJuego(juego);
					ctrlCrupier.setJuego(juego);
					ctrlJefeJuego.setJuego(juego);
					ctrl.cambiarJuego(juego);
				}
			});	
		}
		private class JuegosPanel extends JPanel{
			private static final long serialVersionUID = -5669801070887723009L;
			/**
			 * 
			 */
			public JuegosPanel(){
				setLayout(new FlowLayout());
				add(new JLabel("Juego "));
				add(comboTipoJuego);
			}
		}
	}

	private class SalirPanel extends JPanel{
		private static final long serialVersionUID = 8399489912124891463L;
		private JLabel hueco = new JLabel();
		private JButton botonSalir = new JButton("Salir");
		/**
		 * 
		 */
		public SalirPanel(){
			setLayout(new BorderLayout());
			hueco.setPreferredSize(new Dimension(140,100));
			botonSalir.setPreferredSize(new Dimension(140,50));
			botonSalir.setIcon(new ImageIcon(getClass().getResource("/iconos/salir.png")));
			add(hueco, BorderLayout.CENTER);
			add(botonSalir, BorderLayout.SOUTH);
			botonSalir.addActionListener(new ActionListener(){
				/**
				* Método para llevar al cabo el actionPerformed
				*/
				public void actionPerformed(ActionEvent e) {
					salir();
				}
			});
		}
	}
	
	@SuppressWarnings("deprecation")
	private void salir() {
		int n = JOptionPane.showOptionDialog(new JFrame(),
		"Seguro que quieres salir?", "Salir",
		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (n == 0) {/*ctrl.salir();*/ hide();}
	}

	@Override
	public void onReset(String juego, int mesa) {
		
	}

	@Override
	public void onCambioJuego() {
		
	}
}
