//
//  Created by Jonathan Carrero.
//  Copyright (c) Jonathan Carrero. All rights reserved.
//
package Vista;

import java.awt.*;
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

public class VistaCliente extends JFrame implements Observer{
	
	private static final long serialVersionUID = 6937853572224085296L;
	private static int DEFAULT_WIDTH = 700;
	private static int DEFAULT_HEIGTH = 500;
	private ControladorCliente ctrl;
	
	/**
	 * Constructora
	 * @param ctrl
	 */
	public VistaCliente (ControladorCliente ctrl){	
		super ("Cliente");
		this.ctrl = ctrl;
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
		private JButton botonConsultar = new JButton("Consultar Jugadores");
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
			ctrl.setJuegoYMesa("RULETA", 1);
			turnoText.setText("Jugando a la " + ctrl.getNombreJuego() +" en la Mesa " + ctrl.getNumeroMesa());
			add(scorePanel, BorderLayout.NORTH);
			add(turnoText,BorderLayout.CENTER);
			add(botonConsultar,BorderLayout.SOUTH);
			botonConsultar.addActionListener(new ActionListener(){
			/**
			* Método para llevar al cabo el actionPerformed
			*/
			public void actionPerformed(ActionEvent e) {
				String consulta;
				consulta = ("SELECT * FROM Cliente WHERE Juego = '" + ctrl.getNombreJuego() +
							"' AND Mesa = " + ctrl.getNumeroMesa());
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
					turnoText.setText("Jugando a la " + ctrl.getNombreJuego() +" en la Mesa " + ctrl.getNumeroMesa());
					
				}else{
					turnoText.setText("Jugando al " + ctrl.getNombreJuego() +" en la Mesa " + ctrl.getNumeroMesa());
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
		private JPanel partidaPanel = new GrupoPartidaPanel();
		// Panel para el botón salir
		private JPanel ApostarYSalirPanel = new ApostarYSalirPanel();
		// Panel para los componentes de cambio de juego
		private JPanel cambioJugadoresYJuegoPanel= new GrupoCambioJugadoresYJuegoPanel();
		/**
		 * 
		 */
		public RightPanel(){
			setLayout(new BorderLayout());
			Border borderPartidaPanel = new TitledBorder(new EtchedBorder(), "Gestión de jugadores");
			partidaPanel.setBorder(borderPartidaPanel);
			add(partidaPanel, BorderLayout.NORTH);
			add(cambioJugadoresYJuegoPanel, BorderLayout.CENTER);
			add(ApostarYSalirPanel, BorderLayout.SOUTH);	
		}
	}
		
	private class GrupoPartidaPanel extends JPanel{
		private static final long serialVersionUID = 7656487254403788246L;
		private JButton botonDarDeAlta = new JButton("Dar de alta");
		private JButton botonDarDeBaja = new JButton("Dar de baja");
		/**
		 * 
		 */
		public GrupoPartidaPanel(){
			setLayout(new FlowLayout());
			botonDarDeAlta.setPreferredSize(new Dimension(150,60));
			botonDarDeBaja.setPreferredSize(new Dimension(150,60));
			botonDarDeAlta.setIcon(new ImageIcon(getClass().getResource("/iconos/añadir.png")));
			botonDarDeBaja.setIcon(new ImageIcon(getClass().getResource("/iconos/quitar.png")));
			add(botonDarDeAlta);
			add(botonDarDeBaja);
			botonDarDeAlta.addActionListener(new ActionListener(){
				/**
				 * Método para llevar a cabo el actionPerformed
				 */
				public void actionPerformed(ActionEvent e) {
					String nombre;
					nombre = JOptionPane.showInputDialog (null, "Escribe tu nombre");
					if (!nombre.isEmpty()){
						String DNI = JOptionPane.showInputDialog (null, "Escribe tu DNI");
						if (!DNI.isEmpty()){
							ctrl.setDNIyNombre(nombre, DNI);
							String consulta;
							consulta = ("INSERT INTO Cliente VALUES ('" + DNI + "', '" + nombre + "', '" +
							ctrl.getNombreJuego() + "', "+ ctrl.getNumeroMesa() + ")");
							ctrl.insertarDatos(consulta);
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
			
			botonDarDeBaja.addActionListener(new ActionListener(){
				/**
				 * Método para llevar a cabo el actionPerformed
				 */
				public void actionPerformed(ActionEvent e) {
					String nombre;
					nombre = JOptionPane.showInputDialog ("Escribe tu nombre:");
					if (!nombre.isEmpty()){
						String consulta;
						consulta = ("DELETE * FROM Cliente WHERE Nombre = '" + nombre + "'");
						ctrl.borrarDatos(consulta);
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
			public GrupoCambioJuegoPanel(){
				setLayout(new BorderLayout());
				add(cambioJuegoYMesaPanel, BorderLayout.NORTH);
			}
		}
	}
	
	private class GrupoCambioJuegoYMesaPanel extends JPanel{
		private static final long serialVersionUID = -4053379774350750369L;
		public JComboBox<String> comboTipoJuego = new JComboBox<String>();
		public JComboBox<Integer> comboNumeroMesa = new JComboBox<Integer>();
		private JPanel JuegosPanel= new JuegosPanel();
		private JPanel MesasPanel= new MesasPanel();
		/**
		 * 
		 */
		public GrupoCambioJuegoYMesaPanel(){
			setLayout(new BorderLayout());
			add(JuegosPanel, BorderLayout.NORTH);
			add(MesasPanel, BorderLayout.CENTER);
			comboTipoJuego.setEditable(false);
			comboTipoJuego.addItem("RULETA");
			comboTipoJuego.addItem("POKER");
			comboTipoJuego.addItem("BLACKJACK");
			comboTipoJuego.addItem("CRAPS");
			comboNumeroMesa.setEditable(false);
			for (int i = 1; i <= 5; i++){
				comboNumeroMesa.addItem(i);
			}
			// Panel botón cambiar
			JPanel cambiarPanel = new JPanel();
			JButton botonCambiar = new JButton("Cambiar");
			botonCambiar.setPreferredSize(new Dimension(150,50));
			botonCambiar.setIcon(new ImageIcon(getClass().getResource("/iconos/cambiar.png")));
			add(cambiarPanel, BorderLayout.SOUTH);
			cambiarPanel.add(botonCambiar);
			botonCambiar.addActionListener(new ActionListener(){
				/**
				 * Método para llevar a cabo el actionPerformed
				 */
				public void actionPerformed(ActionEvent e) {
					String juego = (String)comboTipoJuego.getSelectedItem();
					int mesa = (int)comboNumeroMesa.getSelectedItem();
					ctrl.setJuegoYMesa(juego, mesa);
					ctrl.cambiarJuego(juego, mesa);
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
		
		private class MesasPanel extends JPanel{
			private static final long serialVersionUID = 6517300091403647307L;
			/**
			 * 
			 */
			public MesasPanel(){
				setLayout(new FlowLayout());
				add(new JLabel("Mesa "));
				add(comboNumeroMesa);	
			}
		}
	}
	
	private class ApostarYSalirPanel extends JPanel{
		private static final long serialVersionUID = 8399489912124891463L;
		private JPanel ApostarPanel = new ApostarPanel();
		private JLabel hueco = new JLabel();
		private JButton botonSalir = new JButton("Salir");
		/**
		 * 
		 */
		public ApostarYSalirPanel(){
			setLayout(new BorderLayout());
			hueco.setPreferredSize(new Dimension(140,100));
			botonSalir.setPreferredSize(new Dimension(140,50));
			botonSalir.setIcon(new ImageIcon(getClass().getResource("/iconos/salir.png")));
			add(ApostarPanel, BorderLayout.NORTH);
			add(hueco, BorderLayout.CENTER);
			add(botonSalir, BorderLayout.SOUTH);
			botonSalir.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {salir();}
			});
		}
	}
	
	private class ApostarPanel extends JPanel{
		private static final long serialVersionUID = 8399489912124891463L;
		private int numApuestas = 0;
		private JTextField ApuestasText = new JTextField();
		private JButton botonApostar = new JButton("Apostar");
		/**
		 * 
		 */
		public ApostarPanel(){
			setLayout(new BorderLayout());
			ApuestasText.setPreferredSize(new Dimension(140,50));
			botonApostar.setPreferredSize(new Dimension(140,50));
			ApuestasText.setEditable(false);
			ApuestasText.setHorizontalAlignment(JTextField.CENTER);
			ApuestasText.setFont(new java.awt.Font("Tahoma", 0, 18));
			ApuestasText.setText("Número de apuestas realizadas: " + numApuestas);
			botonApostar.setIcon(new ImageIcon(getClass().getResource("/iconos/apostar.png")));
			add(ApuestasText, BorderLayout.NORTH);
			add(botonApostar, BorderLayout.CENTER);
			botonApostar.addActionListener(new ActionListener(){
				/**
				* Método para llevar al cabo el actionPerformed
				*/
				public void actionPerformed(ActionEvent e) {
					numApuestas++;
					ApuestasText.setText("Número de apuestas realizadas: " + numApuestas);
					JOptionPane.showMessageDialog(rootPane, "Apuesta realizada con exito");
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
		/* ... */
	}

	@Override
	public void onCambioJuego() {
		/* ... */
	}
}
