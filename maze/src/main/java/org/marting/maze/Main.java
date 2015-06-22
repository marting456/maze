package org.marting.maze;

public class Main {

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
}
