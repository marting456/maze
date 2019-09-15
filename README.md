# maze

Given data describing a maze with diagonal walls
<ol>
<li>count the number of fully enclosed areas and </li>
<li>the size of the largest enclosed area. </li>
</ol>
The maze pictured below has 2 enclosed areas, the larger of which has an area of 8.

![alt tag](https://github.com/marting456/maze/blob/master/docs/maze.png)

Data Type:
<pre>
#  Type 0     Type 1
# 
#  +----+     +----+
#  |   /|     |\   |
#  |0 / |     | \ 0|
#  | / 1|     |1 \ |
#  |/   |     |   \|
#  +----+     +----+
</pre>

Sample static data:
<pre>
maze = [[1,0,0,1,1,0],
        [1,0,0,0,1,1],
        [0,0,1,1,0,1],
        [1,0,1,0,0,0]]
</pre>
