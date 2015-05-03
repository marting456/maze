/**
 * @author Martin Gercsak - mgercsak@yahoo.com.au
 */
package org.marting.maze;

/**
 * @author martin
 *
 */
public class Maze {

	private int[][] maze = null;

	private int height = 0;
	private int width = 0;
	private int numberOfSquares = 0;
	private int numberOfNonTrivialEnclosures = 0;
	private int expandCounter = 0;
	private int maxEnclosure = 0;
	private int maxX = -1;
	private int maxY = -1;
	private int maxArea = 0;
	
	public static void main(String[] args) {

		// 8, 2
		int[][] maze1 = new int[][] { 
				{ 1, 0, 0, 1, 1, 0 },
				{ 1, 0, 0, 0, 1, 1 }, 
				{ 0, 0, 1, 1, 0, 1 }, 
				{ 1, 0, 1, 0, 0, 0 } };
		// 10
		int[][] maze2 = new int[][] { 
				{ 1, 0, 0, 1, 1, 0 },
				{ 1, 0, 0, 0, 1, 1 }, 
				{ 0, 0, 1, 0, 0, 1 }, 
				{ 1, 0, 1, 0, 0, 0 } };
		// 6, 2, 2
		int[][] maze3 = new int[][] { 
				{ 1, 0, 0, 1, 1, 0 },
				{ 1, 0, 1, 0, 1, 1 }, 
				{ 0, 0, 1, 1, 0, 1 }, 
				{ 1, 0, 1, 0, 0, 0 } };
		// 0
		int[][] maze4 = new int[][] { 
				{ 1, 0, 1, 1, 1, 0 },
				{ 1, 0, 0, 0, 1, 1 }, 
				{ 0, 0, 1, 0, 0, 1 }, 
				{ 1, 0, 1, 0, 0, 0 } };
		// 6, 2
		int[][] maze5 = new int[][] { 
				{ 1, 0, 1, 1, 1, 0 },
				{ 1, 0, 0, 0, 1, 1 }, 
				{ 0, 0, 0, 0, 0, 1 }, 
				{ 1, 0, 1, 0, 0, 0 } };
		
		Maze myMaze = new Maze(maze1); 
		myMaze.solveMaze();
//		myMaze = new Maze(maze2);
//		myMaze.solveMaze();
//		myMaze = new Maze(maze3);
//		myMaze.solveMaze();
//		myMaze = new Maze(maze4);
//		myMaze.solveMaze();
		myMaze = new Maze(maze5);
		myMaze.solveMaze();
		
	}
	
	public Maze(int[][] maze) {
		this.maze = maze;
		this.height  = maze.length;
		this.width = maze[0].length;
	}
	
	/**
	 * 1. mark all edges that don't connect to at least one other edge on both ends. These cannot participate 
	 *    in an enclosure. repeat this process until no such edges found.
	 * 2. mark all edges that are as in 1. and are not inside an enclosure. these must all be reachable from 
	 *    the sides of the maze
	 * 3. find all trivial square enclosures.
	 * 4. find all non trivial enclosures. these are the number of continuous groups of 2s
	 * 5. find the largest non trivial enclosures. mark its interior with 4s.
	 * 6. mark the edges of the largest enclosure with 2s.
	 * 7. count the area. 
	 */
	public void solveMaze() {
		System.out.println("== initial maze ==");
		printMaze();
		setNonEnclosingEdges();
		System.out.println("== orphane edges removed ==");
		printMaze();
		expandFromSides();
		System.out.println("== outer lines marked ==");
		printMaze();
		findSquares();
		System.out.println(">> number of squares: " + getNumberOfSquares());
		findNonTrivialEnclosures();
		System.out.println(">> number of nonTrivial enclosures: " + getNumberOfNonTrivialEnclosures());
		System.out.println(">> total number of enclosures: " + ((int) getNumberOfNonTrivialEnclosures() + getNumberOfSquares()));
		findMaxEnclosure();
		printMaze();
		System.out.println(">> Max enclosure inner coordinates: " + getMaxX() + "," + getMaxY());
		if (getNumberOfNonTrivialEnclosures() > 0) {
			markMaxEnclousure();
			printMaze();
			System.out.println("== max enclosure interrior marked with 4s ==");
			assignValuesToEdges();
			printMaze();
			System.out.println("== edges marked with 2s ==");
			calculateMaxArea();
			System.out.println("Max area: " + getMaxArea());
		} else if(getNumberOfSquares() > 0) {
			System.out.println("Max area: 2");
		}
	}
	
	public int getMaxArea() {
		return maxArea;
	}

	public void setMaxArea(int maxArea) {
		this.maxArea = maxArea;
	}

	private void calculateMaxArea() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (maze[i][j] == 2 || maze[i][j] == 4)
					maxArea = maxArea + maze[i][j];
			}
		}
		maxArea = maxArea / 4;
	}
	
	private void assignValuesToEdges() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++)
				if (maze[i][j] == 4) {
					if (getNW(i,j) == 1 || getNW(i,j) == 0) {
						maze[i-1][j-1] = 2;
					}
					if (getN(i,j) == 1 || getN(i,j) == 0) {
						maze[i-1][j] = 2;
					}
					if (getNE(i,j) == 1 || getNE(i,j) == 0) {
						maze[i-1][j+1] = 2;
					}
					if (getSE(i,j) == 1 || getSE(i,j) == 0) {
						maze[i+1][j+1] = 2;
					}
					if (getS(i,j) == 1 || getS(i,j) == 0) {
						maze[i+1][j] = 2;
					}
					if (getSW(i,j) == 1 || getSW(i,j) == 0) {
						maze[i+1][j-1] = 2;
					}
					if (getW(i,j) == 1 || getW(i,j) == 0) {
						maze[i][j-1] = 2;
					}
				}
		}		
	}
	
	private void markMaxEnclousure() {
		expandNWSE(maxX, maxY, 4, 8);
	}
	
	private void findMaxEnclosure() {
		if(numberOfNonTrivialEnclosures == 0 && numberOfSquares == 0) {
			this.maxEnclosure = 0;
			return;
		}
		if(numberOfNonTrivialEnclosures == 0 && numberOfSquares > 0) {
			this.maxEnclosure = 2;
			return;
		}
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (maze[i][j] == 4) {
					expandCounter = 0;
					expandNWSE(i,j,8,4);
					if (expandCounter > maxEnclosure) {
						maxEnclosure = expandCounter;
						maxX = i;
						maxY = j;
					}
				}
			}
		}		
	}
	
	private void findNonTrivialEnclosures() {
		numberOfNonTrivialEnclosures = 0;
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++)
				if (maze[i][j] == 2) {
					expandNWSE(i,j,4,2);
					numberOfNonTrivialEnclosures++;
				}
		}	
	}	
	
	private void findSquares() {
		numberOfSquares = 0;
		for (int i=0; i < height - 2; i++) {
			for (int j=0; j < width - 2; j++) {
				if (maze[i][j] == 0 && maze[i][j+1] == 1 && 
						maze[i+1][j] == 1 && maze[i+1][j+1] == 0)
					numberOfSquares++;
			}
		}
	}
	
	// sets all edges that don't participate in an enclosure to 2, ie doesn't have at least 
	// two connected edges
	private void setNonEnclosingEdges() {

		boolean found = true;
		while (found) {
			found = false;
			for (int i = 0; i < maze.length; i++) {
				for (int j = 0; j < maze[i].length; j++) {
					if (maze[i][j] != 2 && !hasTwoNeighborsConnected(i, j)) {
						maze[i][j] = 2;
						found = true;
					}
				}
			}
		}
	}

	// sets all edges outside closures to 3, which means inner edges will remain 2
	private void expandFromSides() {
		// North
		for (int i=0; i<width-1; i++)
		if(maze[0][i] == 2) 
			expandNWSE(0,i,3,2);
		// West
		for (int i=0; i<height-1; i++)
		if(maze[i][0] == 2) 
			expandNWSE(i,0,3,2);
		// East
		for (int i=0; i<height-1; i++)
		if(maze[i][width-1] == 2)
			expandNWSE(i,width-1,3,2);
		// South
		for (int i=0; i<width-1; i++)
		if(maze[height-1][i] == 2)
			expandNWSE(height-1,i,3,2);		
	}
	
	private void expandNWSE(int x, int y, int newValue, int condition) {
		this.expandCounter++;
		maze[x][y] = newValue;
		if (getW(x,y) == condition )
			expandNWSE(x, y-1,newValue, condition);
		if (getN(x,y) == condition)
			expandNWSE(x-1, y,newValue, condition);
		if (getE(x,y) == condition) 
			expandNWSE(x, y+1,newValue, condition);
		if (getS(x,y) == condition)
			expandNWSE(x+1, y,newValue, condition);		
	}
	
	private boolean hasTwoNeighborsConnected(int x, int y) {
		if (maze[x][y] == 1) {
			return (getW(x, y) == 0 || getNW(x, y) == 1 || getN(x, y) == 0)
					&& (getS(x, y) == 0 || getSE(x, y) == 1 || getE(x, y) == 0);
		} else { // [x][y] == 0
			return (getN(x, y) == 1 || getNE(x, y) == 0 || getE(x, y) == 1)
					&& (getW(x, y) == 1 || getSW(x, y) == 0 || getS(x, y) == 1);
		}
	}

	private void printMaze() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++)
				System.out.print(maze[i][j] + " ");
			System.out.println();
		}
	}

	private int getN(int x, int y) {
		if (x == 0) {
			return -1;
		} else {
			return maze[x - 1][y];
		}
	}

	private int getS(int x, int y) {
		if (x == maze.length - 1) {
			return -1;
		} else {
			return maze[x + 1][y];
		}
	}

	private int getW(int x, int y) {
		if (y == 0) {
			return -1;
		} else {
			return maze[x][y - 1];
		}
	}

	private int getE(int x, int y) {
		if (y == maze[x].length - 1) {
			return -1;
		} else {
			return maze[x][y + 1];
		}
	}

	private int getNW(int x, int y) {
		if (x == 0 || y == 0) {
			return -1;
		} else {
			return maze[x - 1][y - 1];
		}
	}

	private int getNE(int x, int y) {
		if (x == 0 || y == maze[x].length - 1) {
			return -1;
		} else {
			return maze[x - 1][y + 1];
		}
	}

	private int getSW(int x, int y) {
		if (x == maze.length-1 || y == 0) {
			return -1;
		} else {
			return maze[x + 1][y - 1];
		}
	}

	private int getSE(int x, int y) {
		if (x == maze.length - 1 || y == maze[x].length - 1) {
			return -1;
		} else {
			return maze[x + 1][y + 1];
		}
	}
	
	public int getNumberOfNonTrivialEnclosures() {
		return numberOfNonTrivialEnclosures;
	}

	public void setNumberOfNonTrivialEnclosures(int numberOfNonTrivialEnclosures) {
		this.numberOfNonTrivialEnclosures = numberOfNonTrivialEnclosures;
	}

	public int getNumberOfSquares() {
		return numberOfSquares;
	}

	public void setNumberOfSquares(int numberOfSquares) {
		this.numberOfSquares = numberOfSquares;
	}

	public int getMaxEnclosure() {
		return maxEnclosure;
	}

	public void setMaxEnclosure(int maxEnclosure) {
		this.maxEnclosure = maxEnclosure;
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}
}
