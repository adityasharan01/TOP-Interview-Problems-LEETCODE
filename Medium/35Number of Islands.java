Number of Islands
Given a two-dimensional integer matrix of 1s and 0s, return the number of "islands" in the matrix. A 1 represents land and 0 represents water, so an island is a group of 1s that are neighboring whose perimeter is surrounded by water.

Note: Neighbors can only be directly horizontal or vertical, not diagonal.

Constraints

n, m ≤ 100 where n and m are the number of rows and columns in matrix.
Example 1
Input
matrix = [
    [1, 1],
    [1, 0]
]
Output
1
Example 2
Input
matrix = [
    [1, 0, 0, 0, 0],
    [0, 0, 1, 1, 0],
    [0, 1, 1, 0, 0],
    [0, 0, 0, 0, 0],
    [1, 1, 0, 0, 1],
    [1, 1, 0, 0, 1]
]
Output
4
Example 3
Input
matrix = [
    [0, 1],
    [1, 0]
]
Output
2

Hint1:DFS
Intuition
DFS out from every land that we did not visit yet. When we DFS, mark every other land connected to this as marked. Return the number of times DFS was initially called.

Implementation
I use DFS and iterate through every piece of land to call DFS if unmarked

Time Complexity
\mathcal{O}(n)O(n) Where n is the number of cells. I visit each cell at most once

Space Complexity
\mathcal{O}(n)O(n) The recursive call has at most n calls since we visit all cells in the worst case

import java.util.*;

class Solution {
    int[] dr = {0, 0, 1, -1};
    int[] dc = {1, -1, 0, 0};

    public int solve(int[][] matrix) {
        int res = 0;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    dfs(i, j, matrix);
                    res++;
                }
            }
        }
        return res;
    }
    public void dfs(int r, int c, int[][] matrix) {
        matrix[r][c] = -1;
        int m = matrix.length, n = matrix[0].length;
        for (int d = 0; d < 4; d++) {
            int new_r = r + dr[d];
            int new_c = c + dc[d];
            if (new_r >= 0 && new_r < m && new_c >= 0 && new_c < n && matrix[new_r][new_c] == 1)
                dfs(new_r, new_c, matrix);
        }
    }
}

////////////////////////////////////////////////////////////////////////////////////
Intuition
A simple flood fill algorithm that deletes islands with an interesting optimization for performance. This beats 100% of all other python submissions to date. (22ms)

Implementation
Rather than bound checking for each call to ff, dummy values are appended to the bottom row and right column instead. Calls to areas outside the map are caught by these dummy values, as python treats arr[-1] the same as arr[n - 1], which effectively reduces the number of conditionals by 4 * n * m, as we won't need to check something like i >= 0 and j >= 0 and i < n and j < n.

Time Complexity
\mathcal{O}(n^2)O(n 
2
 ) total. \mathcal{O}(n)O(n) for dummy value appends, \mathcal{O}(n^2)O(n 
2
 ) for flood fill.

Space Complexity
\mathcal{O}(n)O(n) total, for the dummy values appended. Although this is asymptotically worse than other solutions, the constant factor reduction for time complexity makes up for it.

class Solution:
    def solve(self, matrix):
        n, m = len(matrix), len(matrix[0])

        # append dummy values to the bottom row and right column
        matrix.append([0] * m)
        for i in range(n + 1):
            matrix[i].append(0)

        # flood fill
        def ff(i, j):
            if matrix[i][j]:  # no need to check for bounds!
                matrix[i][j] = 0
                ff(i + 1, j)
                ff(i - 1, j)
                ff(i, j + 1)
                ff(i, j - 1)

        # count number of fills needed
        c = 0
        for i in range(n):
            for j in range(m):
                if matrix[i][j]:
                    c += 1
                    ff(i, j)
        return c
