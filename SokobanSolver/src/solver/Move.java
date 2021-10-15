package solver;

public class Move {
	private Position pos;
	private Direction dir;
	
	public Move(int r, int c, Direction d){
		pos = new Position(r,c);
		dir = d;
	}
	
	public Move(Position p, Direction d){
		pos = new Position(p.getRow(),p.getCol());
		dir = d;
	}
	
	public void display(){
		pos.display();
		System.out.println("dir: "+dir);
	}
	
	public Position getTick(){
		return pos;
	}
	
	public Direction getDir(){
		return dir;
	}
}
