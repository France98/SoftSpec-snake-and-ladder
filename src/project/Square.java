package project;

import java.util.ArrayList;
import java.util.List;

public class Square {

	final public static int NORMAL = 0;
	final public static int FREEZE = 1;
	final public static int REVERSE = 2;
    private List<Piece> pieces;
    private int number;
    private boolean goal;
    private int mode = 0;

    public Square(int number) {
        this.number = number;
        pieces = new ArrayList<Piece>();
        goal = false;
    }

    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public void removePiece(Piece piece) {
        pieces.remove(piece);
    }

    public boolean hasPiece(Piece piece) {  return pieces.contains(piece);  }

    public boolean isGoal() {
        return goal;
    }

    public int getNumber() {
        return number;
    }
    
    public void setMode(int mode){
		this.mode = mode;
	}
	
	public int getMode(){
		return mode;
	}
}
