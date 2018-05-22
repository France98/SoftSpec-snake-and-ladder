package ui;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import project.Game;
import project.Player;

public class BoardUI extends JPanel {
	private Game game;
	private JPanel[] panels = new JPanel[100];
	private JLabel[] playerLabel;

	public BoardUI(Game game) {
		this.game = game;
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new GridLayout(10, 10));
		this.setPreferredSize(new Dimension(500, 500));
		for (int i = 0; i < 100; i++) {
			panels[i] = new JPanel();
			panels[i].setLayout(new FlowLayout());
			panels[i].setOpaque(true);
			panels[i].setBackground(new Color(0, 0, 0, 0));
		}
		updateBoard();
	}

	public void updateBoard() {
		clearPanel();
		for (int i = 99; i >= 90; i--) {
			addPanel(i);
		}
		for (int i = 80; i < 90; i++) {
			addPanel(i);
		}
		for (int i = 79; i >= 70; i--) {
			addPanel(i);
		}
		for (int i = 60; i < 70; i++) {
			addPanel(i);
		}
		for (int i = 59; i >= 50; i--) {
			addPanel(i);
		}
		for (int i = 40; i < 50; i++) {
			addPanel(i);
		}
		for (int i = 39; i >= 30; i--) {
			addPanel(i);
		}
		for (int i = 20; i < 30; i++) {
			addPanel(i);
		}
		for (int i = 19; i >= 10; i--) {
			addPanel(i);
		}
		for (int i = 0; i < 10; i++) {
			addPanel(i);
		}

		this.revalidate();
		this.repaint();
	}

	private void addPanel(int i) {
		for (Player player : game.getPlayers()) {
			if (game.getBoard().getSquares()[i].hasPiece(player.getPiece())) {
				playerLabel = new JLabel[game.getPlayers().size()];
				for (int x = 0; x < playerLabel.length; x++) {
					playerLabel[x] = new JLabel(game.getPlayers().get(x).getName());
				}

				for (JLabel pl : playerLabel) {
					if (player.getName().equals(pl.getText())) {
						panels[i].add(pl);
					}
				}
			}
		}
		this.add(panels[i]);
	}

	private void clearPanel() {
		for (JPanel panel : panels) {
			panel.removeAll();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon bg = new ImageIcon(this.getClass().getResource("/lib/Board.png"));
		g.drawImage(bg.getImage(), 0, 0, null);
	}

}
