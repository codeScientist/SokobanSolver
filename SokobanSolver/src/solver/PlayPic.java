package solver;

import java.util.*;

public class PlayPic {
	public static final int ROW = 9;
	public static final int COL = 7;
	
	private Position tick;
	private char map[][] = new char[ROW][COL];
	private Queue<Move> moveList = new LinkedList<Move>();

	public PlayPic(Position t, char m[][]){
		tick = t;
		map = m;
	}
	
	public Position getTick(){
		return tick;
	}
	
	public void display(){
		tick.display();
		for(int i=0;i<ROW;i++){
			for(int j=0;j<COL;j++){
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		for(Move i: moveList){
			i.display();
		}
	}
	
	public char[][] getMap(){
		return map;
	}
	
	public char getChar(Position p){
		return map[p.getRow()][p.getCol()];
	}
	public void setChar(Position p, char c){
		map[p.getRow()][p.getCol()] = c;
	}
	public char getChar(int r, int c){
		return getChar(new Position(r,c));
	}
	public void setChar(int r, int c, char ch){
		setChar(new Position(r,c),ch);
	}
	public char getChar(int r, int c, char m[][]){
		return m[r][c];
	}
	public void setChar(int r, int c, char ch, char m[][]){
		m[r][c] = ch;
	}
	public char getChar(Position p, char m[][]){
		return m[p.getRow()][p.getCol()];
	}
	public void setChar(Position p, char ch, char m[][]){
		m[p.getRow()][p.getCol()] = ch;
	}
	
	public char[][] cloneMap(){
		char cMap[][] = new char[ROW][COL];
		for(int i=0;i<ROW;i++){
			for(int j=0;j<COL;j++){
				cMap[i][j] = map[i][j];
			}
		}
		return cMap;
//		return map.clone();
	}
	
	public void genMoves(){
		Queue<Position> BFSList = new LinkedList<Position>();
		BFSList.add(tick);
		char testMap[][] = cloneMap();
		
		setChar(tick,'g',testMap);
		
		while(BFSList.isEmpty() != true){
			Position a = BFSList.poll();
			
			if(getChar(a.left(),testMap) == 'e'){
				setChar(a.left(), 'g', testMap);
				BFSList.add(a.left());
			}else if(getChar(a.left(), testMap) == 'b' && (getChar(a.left().left(), testMap) != 'w' && getChar(a.left().left(), testMap) != 'b')){
				addMove(new Move(a.left(), Direction.LEFT));
			}
			if(getChar(a.right(),testMap) == 'e'){
				setChar(a.right(), 'g', testMap);
				BFSList.add(a.right());
			}else if(getChar(a.right(), testMap) == 'b' && (getChar(a.right().right(), testMap) != 'w' && getChar(a.right().right(), testMap) != 'b')){
				addMove(new Move(a.right(), Direction.RIGHT));
			}
			if(getChar(a.up(),testMap) == 'e'){
				setChar(a.up(), 'g', testMap);
				BFSList.add(a.up());
			}else if(getChar(a.up(), testMap) == 'b' && (getChar(a.up().up(), testMap) != 'w' && getChar(a.up().up(), testMap) != 'b')){
				addMove(new Move(a.up(), Direction.UP));
			}
			if(getChar(a.down(),testMap) == 'e'){
				setChar(a.down(), 'g', testMap);
				BFSList.add(a.down());
			}else if(getChar(a.down(), testMap) == 'b' && (getChar(a.down().down(), testMap) != 'w' && getChar(a.down().down(), testMap) != 'b')){
				addMove(new Move(a.down(), Direction.DOWN));
			}
			setChar(a, 'd', testMap);
		}
	}

	public void applyMove(Move m){
		setChar(m.getTick(), 'e');
		
		if(m.getDir() == Direction.LEFT){
			setChar(m.getTick().left(), 'b');
		}else if(m.getDir() == Direction.RIGHT){
			setChar(m.getTick().right(), 'b');
		}else if(m.getDir() == Direction.UP){
			setChar(m.getTick().up(), 'b');
		}else if(m.getDir() == Direction.DOWN){
			setChar(m.getTick().down(), 'b');
		}
	}
	
	public void addMove(Move move) {
		// TODO Auto-generated method stub
		moveList.add(move);
	}
	public Move getMove() {
		// TODO Auto-generated method stub
		return moveList.poll();
	}
	public boolean movesIsEmpty(){
		return moveList.isEmpty();
	}
	
	public int numBoxes(){
		int box = 0;
		for(int i=0;i<ROW;i++){
			for(int j=0;j<COL;j++){
				if(map[i][j] == 'b'){
					box++;
				}
			}
		}
		return box;
	}
	
	public boolean checkEqualMap(char test[][]){
		for(int i=0;i<ROW;i++){
			for(int j=0;j<COL;j++){
				if(test[i][j] != map[i][j]){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean checkEqualTickMap(PlayPic p){
		if(checkEqualMap(p.getMap()) == false){
			return false;
		}
		Queue<Position> BFSList = new LinkedList<Position>();
		BFSList.add(tick);
		char testMap[][] = cloneMap();
		
		setChar(tick,'g',testMap);
		
		while(BFSList.isEmpty() != true){
			Position a = BFSList.poll();
			
			if(Position.checkEqual(a,p.getTick()) == true){
				return true;
			}
			
			if(getChar(a.left(),testMap) == 'e'){
				setChar(a.left(), 'g', testMap);
				BFSList.add(a.left());
			}
			if(getChar(a.right(),testMap) == 'e'){
				setChar(a.right(), 'g', testMap);
				BFSList.add(a.right());
			}
			if(getChar(a.up(),testMap) == 'e'){
				setChar(a.up(), 'g', testMap);
				BFSList.add(a.up());
			}
			if(getChar(a.down(),testMap) == 'e'){
				setChar(a.down(), 'g', testMap);
				BFSList.add(a.down());
			}
			setChar(a, 'd', testMap);
		}
		return false;
	}
	
	public boolean checkSol(char test[][]){
		for(int i=0;i<ROW;i++){
			for(int j=0;j<COL;j++){
				if(test[i][j] == 'x' && map[i][j] != 'b'){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean checkFeasible(char mark[][]){
		for(int i=0;i<ROW;i++){
			for(int j=0;j<COL;j++){
				Position a = new Position(i,j);
				if(getChar(a) == 'b' && mark[i][j] != 'x'){
					if( (getChar(a.left()) == 'w' && getChar(a.down()) == 'w') ||
						(getChar(a.right()) == 'w' && getChar(a.down()) == 'w') ||
						(getChar(a.left()) == 'w' && getChar(a.up()) == 'w') ||
						(getChar(a.right()) == 'w' && getChar(a.up()) == 'w')){
						return false;
					}
				}
			}
		}
		return true;
	}
}
