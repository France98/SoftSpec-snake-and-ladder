package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import project.Game;

public class Controller extends JPanel {

	private Game game;
	private main frame;
	private JTextArea textArea = new JTextArea("");
	private PlayerPanel playerPanel = new PlayerPanel();

	public Controller(main frame, Game game) {
		this.frame = frame;
		this.game = game;
		initComponents();
	}
	
	public Controller(Game game) {
		this(new main(game), game);
	}

	private void initComponents() {
		JScrollPane pane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		pane.setPreferredSize(new Dimension(400, 800));
		this.add(pane);
		textArea.append("Game started with " + game.getPlayers().size() + " players.\n");
	}

	public JPanel getPlayerPanel() {
		return playerPanel;
	}
                               
	class PlayerPanel extends JPanel {

		JLabel currentPlayer = new JLabel("P1");
		Font font = new Font("serif", Font.BOLD, 30);

		private PlayerPanel() {
			initPlayerPanel();
		}

		private void initPlayerPanel() {
			this.add(currentPlayer);
			currentPlayer.setFont(font);
		}

		private void update() {
			String temp = game.currentPlayerName();
			switch (temp) {
			case "P1":
				currentPlayer.setForeground(Color.RED);
				break;
			case "P2":
				currentPlayer.setForeground(Color.BLUE);
				break;
			case "P3":
				currentPlayer.setForeground(Color.GREEN);
				break;
			case "P4":
				currentPlayer.setForeground(Color.CYAN);
				break;
			default:
				currentPlayer.setForeground(Color.BLACK);
			}
			currentPlayer.setText(temp);
		}
	}
}
