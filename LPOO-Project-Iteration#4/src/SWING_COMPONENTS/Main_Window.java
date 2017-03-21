package SWING_COMPONENTS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import Characters.Enemy;
import Characters.Guard;
import Characters.Hero;
import Maps.Map;
import Maps.MapDimension;
import Objects.Door;
import Objects.Lever;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class Main_Window {

	private JFrame frmDungeonKeep;
	private JTextField OggreCounter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Window window = new Main_Window();
					window.frmDungeonKeep.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main_Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		String[][] map1 = new String[][] { { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
				{ "X", " ", " ", " ", "I", " ", "X", " ", " ", "X" },
				{ "X", "X", "X", " ", "X", "X", "X", " ", " ", "X" },
				{ "X", " ", "I", " ", "I", " ", "X", " ", " ", "X" },
				{ "X", "X", "X", " ", "X", "X", "X", " ", " ", "X" },
				{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "X" },
				{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "X" },
				{ "X", "X", "X", " ", "X", "X", "X", "X", " ", "X" },
				{ "X", " ", "I", " ", "I", " ", "X", " ", " ", "X" },
				{ "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" } };

		String[][] ReferenceMap1 = new String[][] { { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
				{ "X", " ", " ", " ", "I", " ", "X", " ", " ", "X" },
				{ "X", "X", "X", " ", "X", "X", "X", " ", " ", "X" },
				{ "X", " ", "I", " ", "I", " ", "X", " ", " ", "X" },
				{ "X", "X", "X", " ", "X", "X", "X", " ", " ", "X" },
				{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "X" },
				{ " ", " ", " ", " ", " ", " ", " ", " ", " ", "X" },
				{ "X", "X", "X", " ", "X", "X", "X", "X", " ", "X" },
				{ "X", " ", "I", " ", "I", " ", "X", " ", " ", "X" },
				{ "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" } };

		MapDimension map1Dimension = new MapDimension(10, 10);
		Hero map1Hero = new Hero(map1, 2, 1);

		Guard map1Guard = new Guard(map1);

		Lever map1Lever = new Lever(7, 8);

		Enemy[] map1Enemies = new Enemy[1];

		Lever[] map1Levers = new Lever[1];

		Door[] map1Doors = new Door[2];

		map1Enemies[0] = map1Guard;
		map1Levers[0] = map1Lever;
		map1Doors[0] = new Door(0, 5, map1Lever);
		map1Doors[1] = new Door(0, 6, map1Lever);

		Map Map1 = new Map(map1, ReferenceMap1, map1Dimension, map1Hero, map1Enemies, map1Levers, true);
		Map1.setDoor(map1Doors);

		frmDungeonKeep = new JFrame();
		frmDungeonKeep.setResizable(false);
		frmDungeonKeep.setTitle("Dungeon Keep");
		frmDungeonKeep.setBounds(100, 100, 532, 460);
		frmDungeonKeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeep.getContentPane().setLayout(null);

		JLabel OggreCounterLabel = new JLabel("Number Of Oggres");
		OggreCounterLabel.setBounds(10, 11, 120, 14);
		frmDungeonKeep.getContentPane().add(OggreCounterLabel);

		OggreCounter = new JTextField();
		OggreCounter.setBounds(134, 8, 86, 20);
		frmDungeonKeep.getContentPane().add(OggreCounter);
		OggreCounter.setColumns(10);

		JLabel GuardPersonalityLabel = new JLabel("Guard Personality");
		GuardPersonalityLabel.setBounds(10, 36, 120, 14);
		frmDungeonKeep.getContentPane().add(GuardPersonalityLabel);

		JComboBox GuardPersonalitySelector = new JComboBox();
		GuardPersonalitySelector.setModel(new DefaultComboBoxModel(new String[] { "Novice", "Drunken", "Suspicious" }));
		GuardPersonalitySelector.setBounds(134, 33, 86, 20);
		frmDungeonKeep.getContentPane().add(GuardPersonalitySelector);

		JTextArea GameDisplayArea = new JTextArea();
		GameDisplayArea.setFont(new Font("Courier New", Font.PLAIN, 20));
		GameDisplayArea.setEditable(false);
		GameDisplayArea.setBounds(10, 86, 320, 310);
		frmDungeonKeep.getContentPane().add(GameDisplayArea);
		
		JLabel GameStatusMessage = new JLabel("");
		JButton MovementButtonLeft = new JButton("Left");
		JButton MovementButtonUp = new JButton("Up");
		JButton MovementButtonRight = new JButton("Right");
		JButton MovementButtonDown = new JButton("Down");
		
		

		MovementButtonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map1.SwingmapLogic(1, GameDisplayArea);
				if(Map1.hasLost()){
					MovementButtonUp.setEnabled(false);
					MovementButtonDown.setEnabled(false);
					MovementButtonRight.setEnabled(false);
					MovementButtonLeft.setEnabled(false);
					GameStatusMessage.setText("You Lost !");
				}
			}
		});
		MovementButtonUp.setEnabled(false);
		MovementButtonUp.setBounds(389, 202, 89, 23);
		frmDungeonKeep.getContentPane().add(MovementButtonUp);

		MovementButtonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map1.SwingmapLogic(3, GameDisplayArea);
				if(Map1.hasLost()){
					MovementButtonUp.setEnabled(false);
					MovementButtonDown.setEnabled(false);
					MovementButtonRight.setEnabled(false);
					MovementButtonLeft.setEnabled(false);
					GameStatusMessage.setText("You Lost !");
				}
			}
		});
		MovementButtonLeft.setEnabled(false);
		MovementButtonLeft.setBounds(340, 236, 89, 23);
		frmDungeonKeep.getContentPane().add(MovementButtonLeft);

		MovementButtonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map1.SwingmapLogic(4, GameDisplayArea);
				if(Map1.hasLost()){
					MovementButtonUp.setEnabled(false);
					MovementButtonDown.setEnabled(false);
					MovementButtonRight.setEnabled(false);
					MovementButtonLeft.setEnabled(false);
					GameStatusMessage.setText("You Lost !");
				}
			}
		});
		MovementButtonRight.setEnabled(false);
		MovementButtonRight.setBounds(437, 236, 89, 23);
		frmDungeonKeep.getContentPane().add(MovementButtonRight);

		MovementButtonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map1.SwingmapLogic(2, GameDisplayArea);
				if(Map1.hasLost()){
					MovementButtonUp.setEnabled(false);
					MovementButtonDown.setEnabled(false);
					MovementButtonRight.setEnabled(false);
					MovementButtonLeft.setEnabled(false);
					GameStatusMessage.setText("You Lost !");
				}
			}
		});
		MovementButtonDown.setEnabled(false);
		MovementButtonDown.setBounds(389, 270, 89, 23);
		frmDungeonKeep.getContentPane().add(MovementButtonDown);

		GameStatusMessage.setBounds(10, 406, 320, 14);
		frmDungeonKeep.getContentPane().add(GameStatusMessage);

		JButton NewGameButton = new JButton("New Game");
		NewGameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				GameStatusMessage.setText("Start A New Game");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				GameStatusMessage.setText("");
			}
		});

		NewGameButton.addActionListener(new ActionListener() {
			int ct = 0;
			public void actionPerformed(ActionEvent e) {
				ct++;

				if (ct >= 2) {
					MovementButtonUp.setEnabled(true);
					MovementButtonDown.setEnabled(true);
					MovementButtonRight.setEnabled(true);
					MovementButtonLeft.setEnabled(true);

					Map1.SwingDrawMap(GameDisplayArea);

					GameStatusMessage.setText("New Game Started");
					int nOgres = 0;
					try {
						nOgres = Integer.parseInt(OggreCounter.getText());
					} catch (NumberFormatException a) {
						JOptionPane.showMessageDialog(frmDungeonKeep, "Formato n�o valido");
						MovementButtonUp.setEnabled(false);
						MovementButtonDown.setEnabled(false);
						MovementButtonRight.setEnabled(false);
						MovementButtonLeft.setEnabled(false);
					}
					if (nOgres > 5) {
						JOptionPane.showMessageDialog(frmDungeonKeep, "Limite de ogres excedido");
						MovementButtonUp.setEnabled(false);
						MovementButtonDown.setEnabled(false);
						MovementButtonRight.setEnabled(false);
						MovementButtonLeft.setEnabled(false);
					}
				}

			}
		});

		NewGameButton.setBounds(389, 87, 89, 23);
		frmDungeonKeep.getContentPane().add(NewGameButton);

		JButton ExitButton = new JButton("Exit");
		ExitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				GameStatusMessage.setText("Exit Game");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				GameStatusMessage.setText("");
			}
		});
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		ExitButton.setBounds(389, 373, 89, 23);
		frmDungeonKeep.getContentPane().add(ExitButton);
	}
}
