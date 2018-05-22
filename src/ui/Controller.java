package ui;

import javax.swing.JTextArea;

import project.Game;

public class Controller {

	private Game game;
	private main frame;
	private JTextArea textArea = new JTextArea("");
	private PlayerPanel playerPanel = new PlayerPanel();

	/**
	 * Create control panel to link with the main frame.
	 * 
	 * @param frame
	 *            Main frame of the snake and ladder game.
	 * @param game
	 *            Game to control.
	 */
	public Controller(main frame, Game game) {
		this.frame = frame;
		this.game = game;
		initComponents();
	}

	/**
	 * Create control panel without linking to any main frame.
	 * 
	 * @param game
	 *            Game to control.
	 */
	public Controller(Game game) {
		this(new MainFrame(game), game);
	}

	private void initComponents() {
		JScrollPane pane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		pane.setPreferredSize(new Dimension(200, 400));
		this.add(pane);
		textArea.append("Game started with " + game.getPlayers().size() + " players.\n");
		update(null, ObserverCodes.PLAYER_CHANGED);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof ObserverCodes) {
			ObserverCodes temp = (ObserverCodes) arg;
			switch(temp){
			case BOARD_UPDATED:
				textArea.append(game.currentPlayerName() + " moved to " + game.currentPlayerPosition() + "\n");
				game.checkCurrentPlayerStatus();
				if (game.currentPlayerIsAtWarp()) {
					Warp warp = game.getWarpAtCurrentPosition();
					if(warp.toString().equals("snake")){
						ac.snakeSound();
					}
					else{
						ac.ladderSound();
					}
					game.currentPlayerWarp(warp);
					textArea.append(game.currentPlayerName() + " moved to " + game.currentPlayerPosition() + "\n");
				}
				if (game.isCurrentPlayerWins()) {
					ac.winSound();
					textArea.append("\n" + game.currentPlayerName() + " wins~!\n");
					infoBox("Player " + game.currentPlayerName() + " wins~!", "Game ended!");
					frame.end();
				} else if (game.currentPlayer().isFreeze() || (game.getDieFace() != 6 && !game.currentPlayer().isReverse())) {
					game.switchPlayer();
				}
				frame.getDieUI().setEnable();
				break;
			case DIE_ROLLED:
				textArea.append("Die rolled: " + game.getDieFace() + "\n");
				game.currentPlayerMovePiece(game.getDieFace());
				ac.diceSound(game.getDieFace());
				break;
			case PLAYER_FREEZE:
				textArea.append(game.currentPlayerName() + " is frozen.\n");
				ac.freezeSound();
				break;
			case PLAYER_REVERSE:
				textArea.append(game.currentPlayerName() + " stepped on reverse buff.\n");
				ac.reverseSound();
				break;
			case PLAYER_CHANGED:
				textArea.append("\n" + game.currentPlayerName() + "'s turn.\n");
				textArea.append(game.currentPlayerName() + " is at " + game.currentPlayerPosition() + ".\n");
				if (game.currentPlayer().isFreeze()) {
					textArea.append(game.currentPlayerName() + " is freezing.\n");
					game.currentPlayer().unfreeze();
					game.switchPlayer();
				}
				playerPanel.update();
				break;
			case PLAYER_WARP:
				textArea.append(game.currentPlayerName() + " met the " + game.getWarpAtCurrentPosition() + "\n");
				game.checkCurrentPlayerStatus();
				break;
			default:
			}
		}
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}

	private void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Get another JPanel that displays the current player.
	 * 
	 * @return JPanel that displays the current player.
	 */
	public JPanel getPlayerPanel() {
		return playerPanel;
	}

	/**
	 * Panel that displays the current player.
	 * 
	 * @author Pappim Pipatkasrira
	 * @version 1.0
	 * @since May 12, 2018
	 */
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
