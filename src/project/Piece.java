package project;

public class Piece {
	
	private boolean freeze = false;
	private boolean reverse = false;
	
	public void freeze(){
		freeze = true;
	}
	
	public void unfreeze(){
		freeze = false;
	}
	
	public boolean isFreeze(){
		return freeze;
	}
	
	public void reverse(){
		reverse = true;
	}
	
	public void unreverse(){
		reverse = false;
	}
	
	public boolean isReverse(){
		return reverse;
	}
}
