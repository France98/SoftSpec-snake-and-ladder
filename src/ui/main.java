package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import project.Game;
import ui.DiceUI.DiePanel;

public class main extends JFrame{

	private Game game;
	private JButton restartBtn;
	private JButton quitBtn;
	private JPanel south;
	private DiePanel dieUI;

	/**
	 * Create a main frame of the game.
	 * @param game Game to play on the main frame.
	 */
	public main(Game game){
		this.game = game;
		initComponents();
	}
	
	public DiePanel getDieUI(){
		return dieUI;
	}

	private void initComponents() {
		BoardUI boardUI = new BoardUI(game);
		dieUI = new DiePanel(game);
		Controller controllerUI = new Controller(this,game);
		ImageIcon restartbtnImg = new ImageIcon(this.getClass().getResource("/img/restartbtn.png"));
		restartBtn = new JButton(restartbtnImg);
		ImageIcon quitbtnImg = new ImageIcon(this.getClass().getResource("/img/quitbtn.png"));
		quitBtn = new JButton(quitbtnImg);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Snake and Ladder");
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		JPanel north = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		north.add(new JLabel("Current player: "), BorderLayout.NORTH);
		north.add(controllerUI.getPlayerPanel());
		this.add(north, BorderLayout.NORTH);
		JPanel center = new JPanel();
		center.add(boardUI);
		this.add(center,BorderLayout.CENTER);
		south = new JPanel();
		south.add(dieUI);
		south.add(restartBtn);
		south.add(quitBtn);
		restartBtn.addActionListener(new EndListener(EndListener.RESTART));
		quitBtn.addActionListener(new EndListener(EndListener.QUIT));
		this.add(south,BorderLayout.SOUTH);
		JPanel east = new JPanel();
		east.add(controllerUI);
		this.add(east, BorderLayout.EAST);
		this.pack();
		this.setLocationRelativeTo(null);
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
				new StartUI().start();
				setVisible(false);
				dispose();
			}
			if(mode == QUIT){
				System.exit(0);
			}
		}
	}
	
}