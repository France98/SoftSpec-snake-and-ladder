package project;

import java.awt.Component;
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
		initTraps();
    }

    private void initTraps() {
    	int pos = board.getPiecePosition(piece);
    	switch (pos) {
    	case 2: board.movePiece(piece, 36);
    	case 7: board.movePiece(piece, 7);
    	case 15: board.movePiece(piece, 11);
    	case 8: board.movePiece(piece, 23);
    	case 21: board.movePiece(piece, 21);
    	case 28: board.movePiece(piece, 56);
    	case 36: board.movePiece(piece, 8);
    	case 51: board.movePiece(piece, 16);
    	case 71: board.movePiece(piece, 20);
    	case 78: board.movePiece(piece, 20);
    	case 87: board.movePiece(piece, 7);
    	case 16: board.movePiece(piece, -10);
    	case 46: board.movePiece(piece, -21);
    	case 49: board.movePiece(piece, -38);
    	case 62: board.movePiece(piece, -43);
    	case 64: board.movePiece(piece, -4);
    	case 74: board.movePiece(piece, -21);
    	case 89: board.movePiece(piece, -21);
    	case 92: board.movePiece(piece, -4);
    	case 95: board.movePiece(piece, -20);
    	case 99: board.movePiece(piece, -19);
    	case 9: square.setMode(Square.REVERSE);
    	case 17: square.setMode(Square.REVERSE);
    	case 30: square.setMode(Square.REVERSE);
    	case 37: square.setMode(Square.REVERSE);
    	case 45: square.setMode(Square.REVERSE);
    	case 69: square.setMode(Square.REVERSE);
    	case 77: square.setMode(Square.REVERSE);
    	case 86: square.setMode(Square.REVERSE);
    	case 97: square.setMode(Square.REVERSE);
    	case 13: square.setMode(Square.FREEZE);
    	case 33: square.setMode(Square.FREEZE);
    	case 56: square.setMode(Square.FREEZE);
    	case 79: square.setMode(Square.FREEZE);
    	case 88: square.setMode(Square.FREEZE);
    	}
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
    
<<<<<<< HEAD
    public Board getBoard(){
		return board;
    	
    }
=======
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
    
>>>>>>> 4b3c21118a3ac2ce5f3b058a02f9ad95d04c90b7
}
