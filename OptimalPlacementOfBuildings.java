import java.util.LinkedList;
import java.util.Queue;

//Space Complexity : Exponential
//Space Complexity : O(H*W); where H and W are height and width of matrix.
public class OptimalPlacementOfBuildings { 
	/**Approach: Backtracking + BFS**/	
	int minDist;
	public int findMinDistance(int H, int W, int n) {	
		minDist = Integer.MAX_VALUE;
		boolean[][] board = new boolean[H][W];
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				backtrack(board, i, j, n, H, W);
			}
		}
		return minDist;
	}
	//Method to find placement of buildings
	private void backtrack(boolean[][] board, int r, int c, int n, int H, int W) {
		//base
		if(n == 0) {//if all the buildings placed, find distance.
			bfs(board, H, W);
			return;
		}
		
		//logic
		if(c == W) {//traverse same row start col index onwards and next row start col is 0.
			r++;
			c = 0;
		}
		for(int i=r; i<H; i++) {
			for(int j=c; j<W; j++) {
				//action
				board[i][j] = true;
				//recurse
				backtrack(board, i, j+1, n-1, H, W);
				//backtrack
				board[i][j] = false;
			}			
		}
	}
	
	//Method to find min distance once buildings are placed.
	private void bfs(boolean[][] board, int H, int W) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[H][W];
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(board[i][j]) { //if true, means building is placed
					q.add(new int[] {i,j});
					visited[i][j] = true;
				}
			}
		}		
		int dist=0;
		int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0; i<size; i++) {
				int[] curr = q.poll();
				for(int[] dir: dirs) {
					int r = dir[0] + curr[0];
					int c = dir[1] + curr[1];
					if(r>=0 && r<H && c>=0 && c<W && !visited[r][c]) {
						q.add(new int[] {r,c});
						visited[r][c] = true;
					}
				}
			}
			dist++;
		}
		minDist = Math.min(minDist, dist-1);
	}
	
	// Driver code to test above
	public static void main (String[] args) {	
		OptimalPlacementOfBuildings ob  = new OptimalPlacementOfBuildings();	
		int H = 3;//4;
		int W = 2;//4;
		int n = 1;//3; // number of buildings
		
		System.out.println("Min distance in optimal placement of buildings : "+ ob.findMinDistance(H,W,n));         
	}	
}
