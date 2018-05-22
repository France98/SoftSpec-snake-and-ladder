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
	import java.util.Observable;
	import java.util.Observer;

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


	public class BoardUI extends JPanel{
		private Game game;
		private JPanel[] panels = new JPanel[100];
		//	JLabel[] numLabel = new JLabel[64];
		private JLabel[] playerLabel;

		/**
		 * Create board panel
		 * @param game Game to link with the board.
		 */
		public BoardUI(Game game){
			this.game = game;
		}
		
		private void initComponents() {
			this.setLayout(new GridLayout(10,10));
			this.setPreferredSize(new Dimension(500,500));
			updateBoard();
		}


		/**
		 * Update informations displayed on the board.
		 */
		public void updateBoard(){
			clearPanel();
			for(int i = 99 ; i >= 90 ; i--){
				addPanel(i);
			}
			for(int i = 80 ; i < 90 ; i++){
				addPanel(i);
			}
			for(int i = 79 ; i >= 70 ; i--){
				addPanel(i);
			}
			for(int i = 60 ; i < 70 ; i++){
				addPanel(i);
			}
			for(int i = 59 ; i >= 50 ; i--){
				addPanel(i);
			}
			for(int i = 40 ; i < 50 ; i++){
				addPanel(i);
			}
			for(int i = 39 ; i >= 30 ; i--){
				addPanel(i);
			}
			for(int i = 20 ; i < 30 ; i++){
				addPanel(i);
			}
			for(int i = 19 ; i >= 10 ; i--){
				addPanel(i);
			}
			for(int i = 0 ; i < 10 ; i++){
				addPanel(i);
			}
			
			this.revalidate();
			this.repaint();
		}
		
		private void addPanel(int i){
			//		panels[i].add(numLabel[i]);
			for(Player player: game.getPlayers()){
				if(game.getBoard().getSquares()[i].hasPiece(player.getPiece())){
					for(JLabel pl : playerLabel){
						if(player.getName().equals(pl.getText())){
							panels[i].add(pl);
						}
					}
				}
			}
			this.add(panels[i]);
		}

		private void clearPanel() {
			for(JPanel panel : panels){
				panel.removeAll();;
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			ImageIcon bg = new ImageIcon(this.getClass().getResource("/lib/snakeBoard.jpg"));
			g.drawImage(bg.getImage(), 0, 0, null);
		}

}
