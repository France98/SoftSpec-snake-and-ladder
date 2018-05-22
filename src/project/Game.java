package project;

import java.util.ArrayList;

public class Game {

	private ArrayList<Player> players;
    private Die die;
    private Board board;
    private Piece piece;
    private Square square;

    private int currentPlayerIndex;
    private boolean ended;

    public Game(int player) {
    	die = new Die();
		board = new Board();
		players = new ArrayList<Player>();
		for(int i = 0 ; i < player ; i++){
			players.add(new Player("P"+(i+1)));
			board.addPiece(players.get(i).getPiece(), 0);
		}
		ended = false;
    }


	public boolean isEnded() { return ended; }

    public void end() { ended = true;}

    public Player currentPlayer() { return players.get(currentPlayerIndex); }

    public void switchPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public String currentPlayerName() {  return currentPlayer().getName();  }

    public int currentPlayerPosition() {
        return board.getPiecePosition(currentPlayer().getPiece());
    }

    public void currentPlayerMovePiece(int steps) {
        currentPlayer().movePiece(board, steps);
    }

    public boolean currentPlayerWins() {
        return board.pieceIsAtGoal(currentPlayer().getPiece());
    }

    public ArrayList<Player> getPlayers(){
		return players;
	}
    
    public boolean isCurrentPlayerWins(){
		ended = board.pieceIsAtGoal(currentPlayer().getPiece());
		return ended;
	}
    
    public int currentPlayerRollDice(){
		int temp = 0;
		if(!ended){
			temp = currentPlayer().roll(die);
		}
		return temp;
	}
    
    public Board getBoard(){ return board; }
    
    public int getDieFace(){ return die.getFace(); }
}
