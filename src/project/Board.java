package project;

import java.util.Observable;

public class Board extends Observable{

    public static final int SIZE = 100;

    private Square [] squares;

    public Board() {
        squares = new Square[SIZE];
        for(int i = 0; i < squares.length; i++) {
            squares[i] = new Square(i);
        }
        squares[squares.length - 1].setGoal(true);
    }

    public void addPiece(Piece piece, int pos) {
        squares[pos].addPiece(piece);
    }

    public void movePiece(Piece piece, int steps) {
    	int pos = getPiecePosition(piece);
        squares[pos].removePiece(piece);
        int newPos = pos + steps;
        //move by snake and ladder
        switch (newPos) {
		case 1:
			newPos = 37;
			setChanged();
			break;
		case 6:
			newPos = 13;
			setChanged();
			break;
		case 7:
			newPos = 30;
			setChanged();
			break;
		case 14:
			newPos = 25;
			setChanged();
			break;
		case 15:
			newPos = 5;
			setChanged();
			break;
		case 20:
			newPos = 41;
			setChanged();
			break;
		case 27:
			newPos = 83;
			setChanged();
			break;
		case 35:
			newPos = 43;
			setChanged();
			break;
		case 45:
			newPos = 24;
			setChanged();
			break;
		case 48:
			newPos = 10;
			setChanged();
			break;
		case 50:
			newPos = 66;
			setChanged();
			break;
		case 61:
			newPos = 18;
			setChanged();
			break;
		case 63:
			newPos = 59;
			setChanged();
			break;
		case 70:
			newPos = 90;
			setChanged();
			break;
		case 73:
			newPos = 52;
			setChanged();
			break;
		case 77:
			newPos = 97;
			setChanged();
			break;
		case 86:
			newPos = 93;
			setChanged();
			break;
		case 88:
			newPos = 67;
			setChanged();
			break;
		case 91:
			newPos = 87;
			setChanged();
			break;
		case 94:
			newPos = 74;
			setChanged();
			break;
		case 98:
			newPos = 79;
			setChanged();
			break;
		default:
			break;
		}
        
        //Freeze and reverse
        if(newPos >= squares.length) {
            newPos = squares.length - 1;
        }
        addPiece(piece, newPos);
        switch (newPos) {
		case 8:
			piece.reverse();
			break;
		case 12:
			piece.freeze();
			break;
		case 16:
			piece.reverse();
			break;
		case 29:
			piece.reverse();
			break;
		case 32:
			piece.freeze();
			break;
		case 36:
			piece.reverse();
			break;
		case 44:
			piece.reverse();
			break;
		case 55:
			piece.freeze();
			break;
		case 68:
			piece.reverse();;
			break;
		case 76:
			piece.reverse();
			break;
		case 78:
			piece.freeze();
			break;
		case 85:
			piece.reverse();
			break;
		case 87:
			piece.freeze();
			break;
		case 96:
			piece.reverse();
			break;
		default:
			break;
		}
        notifyObservers();
    }

    public int getPiecePosition(Piece piece) {
        for(Square s : squares) {
            if(s.hasPiece(piece)) {
                return s.getNumber();
            }
        }
        return -1;
    }

    public boolean pieceIsAtGoal(Piece piece) {
        return squares[getPiecePosition(piece)].isGoal();
    }
    
    public Square[] getSquares(){
		return squares;
	}
}
