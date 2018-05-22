package project;

public class Board {

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
        if(newPos >= squares.length) {
            newPos = squares.length - 1;
        }
        final int tempNew = newPos;
		new Thread(new Runnable(){
			@Override
			public void run() {
				if(tempNew > pos){
					for(int i = pos ; i < tempNew ; i ++){
						squares[i].removePiece(piece);
						addPiece(piece, i+1);
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							System.err.println("Error delay moves.");
						}
					}
				}
				else {
					for(int i = pos ; i > tempNew ; i --){
						squares[i].removePiece(piece);
						addPiece(piece, i-1);
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							System.err.println("Error delay moves.");
						}
					}
				}
				if(squares[tempNew].getMode() == Square.FREEZE){
					piece.freeze();
				}
				else if(squares[tempNew].getMode() == Square.REVERSE){
					piece.reverse();
				}
			}
		}).start();
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
