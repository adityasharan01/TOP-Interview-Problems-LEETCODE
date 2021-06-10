Sudoku is a puzzle where you're given a 9 by 9 grid with digits. The objective is to fill the grid with the constraint that every row, column, and box (3 by 3 subgrid) must contain all of the digits from 1 to 9, and numbers shouldn't repeat within a row, column, or box.

Given a filled out sudoku board, return whether it's valid.

Constraints

n = 9 where n is number of rows and columns in matrix
Example 1
Input
matrix = [
    [4, 2, 6, 5, 7, 1, 3, 9, 8],
    [8, 5, 7, 2, 9, 3, 1, 4, 6],
    [1, 3, 9, 4, 6, 8, 2, 7, 5],
    [9, 7, 1, 3, 8, 5, 6, 2, 4],
    [5, 4, 3, 7, 2, 6, 8, 1, 9],
    [6, 8, 2, 1, 4, 9, 7, 5, 3],
    [7, 9, 4, 6, 3, 2, 5, 8, 1],
    [2, 6, 5, 8, 1, 4, 9, 3, 7],
    [3, 1, 8, 9, 5, 7, 4, 6, 2]
]
Output
true

// Can be solved more easily Matrix Sequel problem check krna !!!!
Intuition
A matrix fails when a row, column, or 3x3 box (of the 9 box partition) contains a duplicate number.

We want to catch the first case that fails, but we must check all cases to ensure the matrix passes.

We can clone a set of numbers 1-9 for each validation check. We remove each number from the set, and if we fail to remove a number, it's because we know that we have already seen that number, and we can immediately fail the matrix.

Implementation
When looping over the rows and columns, we can check both the row and column in the same nested loop by reversing the row index with the column index.

When looping over the boxes, we can iterate the starting point of each of the boxes (i, j), while we iterate the row and column offset of that box (k, l).

Time Complexity
\mathcal{O}(1)O(1) The input is a fixed size. For 9 rows, 9 columns, 9 boxes, and 9 numbers for each, there will be at worst case \mathcal(9*3)*9 = 243(9∗3)∗9=243 checks.

Space Complexity
\mathcal{O}(1)O(1) No more than 1 + 9*3 = 281+9∗3=28 sets will be created to produce an output. One set is used as a reference for all other sets to be constructed.

import java.util.*;

class Solution {
    public boolean solve(int[][] matrix) {
        // Build a set of nums 1-9
        Set<Integer> allNums = new HashSet<>();
        for (int i = 1; i < 10; i++) {
            allNums.add(i);
        }
        for (int i = 0; i < matrix.length; i++) {
            Set<Integer> row = new HashSet<>(allNums);
            Set<Integer> col = new HashSet<>(allNums);
            for (int j = 0; j < matrix.length; j++) {
                // Check row
                if (!row.remove(matrix[i][j]))
                    return false;

                // Check column
                if (!col.remove(matrix[j][i]))
                    return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Set<Integer> box = new HashSet<>(allNums);
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        // Check box
                        if (!box.remove(matrix[i * 3 + k][j * 3 + l]))
                            return false;
                    }
                }
            }
        }
        // We validated all numbers 1-9 from all 9*9*9 cases
        return true;
    }
}
