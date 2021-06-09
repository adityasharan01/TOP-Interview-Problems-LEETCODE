Conway's Game of Life
You are given a two dimensional matrix where a 1 represents a live cell and a 0 represents a dead cell. A cell's (living or dead) neighbors are its immediate horizontal, vertical and diagonal cells. Compute the next state of the matrix using these rules:

Any living cell with two or three living neighbors lives.
Any dead cell with three living neighbors becomes a live cell.
All other cells die.
Constraints

n, m ≤ 500 where n and m are the number of rows and columns in matrix
Example 1
Input
matrix = [
    [1, 1, 1, 0],
    [0, 1, 0, 1],
    [0, 1, 0, 0],
    [1, 1, 0, 1]
]
Output
[
    [1, 1, 1, 0],
    [0, 0, 0, 0],
    [0, 1, 0, 0],
    [1, 1, 1, 0]
]
Hint1:don't modify the array in place ..
  
Intuition
The biggest challange seems to check what is a sum of neighbours from specific cell.

Implementation
The method getValue first checks if cell neighbours in the matrix are avaible. Later I check conditions to make sure that new cell should be live or dead.

Time Complexity
\mathcal{O}(n + m)O(n+m). We have to travers thru whole matrix

Space Complexity
\mathcal{O}(n + m)O(n+m). We take into consideration new matrix as result

import java.util.*;

class Solution {
    public int[][] solve(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = getValue(matrix, i, j);
            }
        }
        return result;
    }

    public int getValue(int[][] matrix, int i, int j) {
        int sum = 0;
        boolean left = i - 1 >= 0;
        boolean right = i + 1 < matrix.length;
        boolean up = j - 1 >= 0;
        boolean down = j + 1 < matrix[0].length;
        if (left) {
            sum += matrix[i - 1][j];
        }
        if (up) {
            sum += matrix[i][j - 1];
        }
        if (right) {
            sum += matrix[i + 1][j];
        }
        if (down) {
            sum += matrix[i][j + 1];
        }
        if (left && up) {
            sum += matrix[i - 1][j - 1];
        }
        if (left && down) {
            sum += matrix[i - 1][j + 1];
        }
        if (right && up) {
            sum += matrix[i + 1][j - 1];
        }
        if (right && down) {
            sum += matrix[i + 1][j + 1];
        }

        if (matrix[i][j] == 1 && 2 <= sum && sum <= 3) {
            return 1;
        }

        if (matrix[i][j] == 0 && sum == 3)
            return 1;

        return 0;
    }
}
