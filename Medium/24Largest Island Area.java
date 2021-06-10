You are given a two-dimensional integer matrix of 1s and 0s. A 1 represents land and 0 represents water, so an island is a group of 1s that are neighboring whose perimeter is surrounded by water. You can assume that the edges of the matrix are surrounded by water.

Return the area of the largest island in matrix.

Constraints

n, m ≤ 250 where n and m are the number of rows and columns in matrix
Example 1
Input
matrix = [
    [0, 0, 0, 1, 1, 1, 1],
    [0, 0, 0, 0, 0, 0, 0],
    [0, 1, 1, 1, 1, 1, 0],
    [0, 0, 1, 1, 0, 0, 0],
    [0, 0, 0, 0, 0, 1, 1],
    [0, 0, 0, 0, 0, 0, 0]
]
Output
7
Explanation
The largest island in the center has an area of 7 units.
  hitn1:Try thinking in terms of DFS
  
  Intuition
Intuition

We simply perform a DFS on the matrix to find the islands and keep a counter of the area of every island we find. We then return the largest one.

To find the area of an island, we explore every square connected to the current one 4-directionally and then add up the number of squares explored that are part of the current island. The total will be the area of the current island.

To make sure that we don't count a square twice later in the exploration process, we simply change the current square to 0.

Complexities

Time complexity: \mathcal{O}(R * C)O(R∗C), where \mathcal{R}R is the number of rows in the matrix, and \mathcal{C}C is the number of columns in the matrix. We don't visit any square more than once.
Space complexity: \mathcal{O}(R * C)O(R∗C), which is the space used in the recursion stack.

import java.util.*;

class Solution {
    public int solve(int[][] matrix) {
        int maxArea = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // if we find an island, we find its area, and update max area value accordingly
                if (matrix[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfs(matrix, i, j));
                }
            }
        }

        return maxArea;
    }

    public int dfs(int[][] matrix, int i, int j) {
        // check matrix bounds
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || matrix[i][j] == 0) {
            return 0;
        }

        // instead of using a boolean visited array we just change the 1's to 0's
        matrix[i][j] = 0;

        // add one to our area for every 1 within the current island
        int area = 1;

        // check all four directions from current location (down, right, up, left)
        area += dfs(matrix, i + 1, j); // go down
        area += dfs(matrix, i, j + 1); // go right
        area += dfs(matrix, i - 1, j); // go up
        area += dfs(matrix, i, j - 1); // go left

        return area;
    }
}
