package org.marting.maze;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MazeTest {

	// 8 ,1	
	int[][] maze1 = new int[][] { 
			{ 1, 0, 0, 1, 1, 0 },
			{ 1, 0, 0, 0, 1, 1 }, 
			{ 0, 0, 1, 1, 0, 1 }, 
			{ 1, 0, 1, 0, 0, 0 } };
	// 10, 1
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
	
	int[][] maze6 = new int[][] {
			{0,0,0,1,0,0,0,1,0,1,0,0},
			{0,0,1,0,0,0,0,1,0,1,1,0},
			{1,1,0,1,1,1,0,1,0,1,1,1},
			{1,0,0,0,0,0,0,0,1,0,0,0},
			{0,1,1,1,0,1,0,0,1,1,0,1},
			{0,0,0,1,0,1,0,0,1,1,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1},
			{0,1,0,1,0,1,0,1,1,0,1,0}};		
	
	@Test
	public void shouldSolveSimpelMaze1() {
		Maze myMaze = new Maze(maze1); 
		myMaze.solveMaze();	
		assertThat(myMaze.getNumberOfNonTrivialEnclosures(), is(1));
		assertThat(myMaze.getMaxArea(), is(8));
	}
	
	@Test
	public void shouldSolveSimpelMaze2() {
		Maze myMaze = new Maze(maze2); 
		myMaze.solveMaze();	
		assertThat(myMaze.getNumberOfNonTrivialEnclosures(), is(1));
		assertThat(myMaze.getMaxArea(), is(10));
	}
	
	@Test
	public void shouldSolveSimpelMaze3() {
		Maze myMaze = new Maze(maze3); 
		myMaze.solveMaze();	
		assertThat(myMaze.getNumberOfNonTrivialEnclosures(), is(1));
		assertThat(myMaze.getMaxArea(), is(6));
	}
	
	@Test
	public void shouldSolveSimpelMaze4() {
		Maze myMaze = new Maze(maze4); 
		myMaze.solveMaze();	
		assertThat(myMaze.getNumberOfNonTrivialEnclosures(), is(0));
		assertThat(myMaze.getMaxArea(), is(0));
	}
	
	@Test
	public void shouldSolveSimpelMaze5() {
		Maze myMaze = new Maze(maze5); 
		myMaze.solveMaze();	
		assertThat(myMaze.getNumberOfNonTrivialEnclosures(), is(1));
		assertThat(myMaze.getMaxArea(), is(4));
	}

	@Test
	public void shouldSolveLargeMaze() {
		Maze myMaze = new Maze(maze6); 
		myMaze.solveMaze();	
		assertThat(myMaze.getNumberOfNonTrivialEnclosures(), is(3));
		assertThat(myMaze.getMaxArea(), is(22));
	}
	
}