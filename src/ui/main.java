package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import project.Game;

public class main extends JFrame{

	private Game game;
	private JButton restartBtn;
	private JButton quitBtn;
	private JPanel south;
	private JButton roll_btn;
	private BoardUI boardUI;

	public main(Game game){
		this.game = game;
		initComponents();
	}
	

	public void initComponents() {
		boardUI = new BoardUI(game);
		Controller controllerUI = new Controller(this,game);
		ImageIcon restartbtnImg = new ImageIcon(this.getClass().getResource("/lib/restartbtn.png"));
		restartBtn = new JButton(restartbtnImg);
		ImageIcon quitbtnImg = new ImageIcon(this.getClass().getResource("/lib/quitbtn.png"));
		quitBtn = new JButton(quitbtnImg);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Snake and Ladder");
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		ImageIcon rollbtnImg = new ImageIcon(this.getClass().getResource("/lib/rollbtn.png"));
		roll_btn = new JButton(rollbtnImg);
		roll_btn.addActionListener(new rollDie());
		JPanel center = new JPanel();
		center.add(boardUI);
		this.add(center,BorderLayout.CENTER);
		south = new JPanel();
		south.add(roll_btn);
		south.add(restartBtn);
		south.add(quitBtn);
		restartBtn.addActionListener(new EndListener(EndListener.RESTART));
		quitBtn.addActionListener(new EndListener(EndListener.QUIT));
		this.add(south,BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public void setEnable(){
		roll_btn.setEnabled(true);
	}
	
	/**
	 * Make the main frame visible.
	 */
	public void start(){
		this.show();
	}
	
	/**
	 * When it ends, there's no more die rolling.
	 */
	public void end(){
		this.remove(south);
		south = new JPanel();
		south.add(restartBtn);
		south.add(quitBtn);
		this.add(south,BorderLayout.SOUTH);
		pack();
	}
	
	class EndListener implements ActionListener{
		
		final private static int RESTART = 0;
		final private static int QUIT = 1;
		private int mode;
		
		private EndListener(int mode){
			this.mode = mode;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(mode == RESTART){
				new start().start();
				setVisible(false);
				dispose();
			}
			if(mode == QUIT){
				System.exit(0);
			}
		}
	}
	
	class rollDie implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			game.currentPlayerRollDice();
			game.currentPlayerMovePiece(game.getDieFace());
			boardUI.updateBoard();
			game.switchPlayer();
		}
		
	}
	
}
