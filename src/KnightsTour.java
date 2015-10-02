/**
 * Finds a path from the starting position of the knight on a chessboard
 * to every other square without visiting one twice
 * @author Jalal Khan
 * 10/8/15
 */
public class KnightsTour {
	
	private int[][] board;
	private final int end = 24;
	
	public KnightsTour() {
		this.board = getBoard();
		
		boolean found = search(2, 2, 0);
		
		printBoard(found);
	}
	
	/**
	 * Searches for a path from a given position to any available point until all points are visited
	 * without visiting the same point twice. Uses recursive backtrack.
	 * @param x 
	 * @param y
	 * @param visit count of how many valid moves since the first
	 * @return
	 */
	public boolean search(int x, int y, int visit) {
		
		int dir = 0;
		boolean found;
		
		//if there is only one spot open and we are on it, we're done
		if(visit == this.end && this.board[x][y] == 0) {
			return true;
		} 
		//we either landed off the board or on a square we already visited. backtrack!
		else if(this.board[x][y] != 0) {
			return false;
		} 
		//else, we are at a new destination with new possibilities
		else {
			
			this.board[x][y] = visit;
			found = false;
			
			dir = 0;

			//search clockwise around horse for open squares
			while(!found && dir < 8) {
				switch(dir) {
					case 0: found = search(x+1, y-2, visit+1); break;
					case 1: found = search(x+2, y-1, visit+1); break;
					case 2: found = search(x+2, y+1, visit+1); break;
					case 3: found = search(x+1, y+2, visit+1); break;
					case 4: found = search(x-1, y+2, visit+1); break;
					case 5: found = search(x-2, y+1, visit+1); break;
					case 6: found = search(x-2, y-1, visit+1); break;
					case 7: found = search(x-1, y-2, visit+1); break;
					
					default: found = false; break;
				}
				
				dir++;
			}
			
			if(!found)
				this.board[x][y] = 0;
			
			return found;
		}
		
		
	}

	/**
	 * Creates a 9x9 empty board with the 0s at the available squares and 2 rows/columns of
	 * buffer to ensure against null pointer exceptions
	 * @return int[][] board 
	 */
	public int[][] getBoard() {
		
		int size = 9; //2 extra on each side for error checking
		
		int[][] board = new int[size][size];
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(i==0 || i==1 || i==7 || i==8) 
					board[i][j] = -1;
				if(j==0 || j==1 || j==7 || j==8)
					board[i][j] = -1;
			}
		}

		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		return board;
	}

	public void printBoard(boolean found) {
		System.out.println(found);
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}		
	}
	
	public static void main(String args[]) {
		new KnightsTour();
	}
}