package solver;

import java.util.*;

public class Play {

	private static char initArr[][] = 	{
		{'w','w','w','w','w','w','w'},
		{'w','w','e','e','e','w','w'},
		{'w','w','b','e','b','w','w'},
		{'w','w','e','b','e','w','w'},
		{'w','e','e','b','e','w','w'},
		{'w','e','e','b','e','e','w'},
		{'w','e','e','b','e','e','w'},
		{'w','w','w','e','e','w','w'},
		{'w','w','w','w','w','w','w'}
		};
	private static char fixArr[][] = 	{
		{'w','w','w','w','w','w','w'},
		{'w','w','e','x','e','w','w'},
		{'w','w','e','x','e','w','w'},
		{'w','w','e','x','e','w','w'},
		{'w','e','e','x','e','w','w'},
		{'w','e','e','x','e','e','w'},
		{'w','e','e','x','e','e','w'},
		{'w','w','e','e','e','w','w'},
		{'w','w','w','w','w','w','w'}
		};

	private static Stack<PlayPic> ppList = new Stack<PlayPic>();
	private static Stack<PlayPic> ppInvalidList = new Stack<PlayPic>();
	private static Stack<Move> moves = new Stack<Move>();
	
	private static boolean flag = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Position tick = new Position(1,3);
		ppList.push(new PlayPic(tick,initArr));
		
		activate();
		if(flag == true){
			System.out.println("there is a solution");
			for(Move i: moves){
				i.display();
			}
		}else{
			System.out.println("no solution");
		}
	}


	public static void activate(){
		//checking
		if(ppList.peek().checkSol(fixArr) == true){
			flag = true;
			ppList.peek().display();
			return;
		}
		for(int i=0;i<ppList.size()-1;i++){
			if(ppList.peek().checkEqualTickMap(ppList.elementAt(i)) == true){
				return;
			}
		}
		for(int i=0;i<ppInvalidList.size();i++){
			if(ppList.peek().checkEqualTickMap(ppInvalidList.elementAt(i)) == true){
				return;
			}
		}
		if(ppList.peek().checkFeasible(fixArr)==false){
			return;
		}
		
		ppList.peek().genMoves();
		//ppList.peek().display();
		System.out.println("size of ppList stack is "+ppList.size()+" ,size of ppInvalidList is "+ppInvalidList.size());
		while(ppList.peek().movesIsEmpty()!=true){
			Move m = ppList.peek().getMove();
			moves.add(m);
			PlayPic newPP = new PlayPic(m.getTick(),ppList.peek().cloneMap());
			newPP.applyMove(m);
			//System.out.println("num of boxes "+newPP.numBoxes());
			ppList.push(newPP);
			activate();
			if(flag == true){
				return;
			}
			ppList.pop();
			moves.pop();
		}
		//
		ppInvalidList.add(ppList.peek());
		return;
	}
}
