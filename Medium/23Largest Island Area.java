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
  
 Hint1: Try thinking in terms of DFS
 
 Intuition
Explore all areas of land, and don't explore any water.

Implementation
Traverse through the grid, and when we encounter a piece of land, we want to explore all its neighbors (left, right, up, down) and keep track of the largest area we have seen. We also have a boolean array to make sure that we do not explore the same cell twice.

Time Complexity
\mathcal{O}(n + m)O(n+m), where n is the length of the rows, and m is the length of the columns

Space Complexity
\mathcal{O}(n + m)O(n+m), in the worst case, where the entire matrix is filled with land, our visited array would store every cell, as well as our call stack would be (n + m) deep.

import java.util.*;

class Solution {
    public int solve(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int maxArea = 0;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                int area = dfs(matrix, i, j, visited);
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }
    private int dfs(int[][] matrix, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || visited[i][j]
            || matrix[i][j] == 0) {
            return 0;
        }
        int area = 1;
        visited[i][j] = true;
        area += dfs(matrix, i + 1, j, visited);
        area += dfs(matrix, i - 1, j, visited);
        area += dfs(matrix, i, j + 1, visited);
        area += dfs(matrix, i, j - 1, visited);
        return area;
    }
}
