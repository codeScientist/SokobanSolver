package solver;

public class Position {
	private int row;
	private int col;
	
	public Position(int r, int c){
		row = r;
		col = c;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}
	
	public Position left(){
		return (new Position(row,col-1));
	}
	public Position right(){
		return (new Position(row,col+1));
	}
	public Position up(){
		return (new Position(row-1,col));
	}
	public Position down(){
		return (new Position(row+1,col));
	}
	
	public void display(){
		System.out.println("Position row: "+row+",col: "+col);
	}

	public static boolean checkEqual(Position p, Position t){
		if(p.getRow() == t.getRow() && p.getCol() == t.getCol()){
			return true;
		}else{
			return false;
		}
	}
}
