Given a two-dimensional matrix of integers matrix, determine whether it's a Toeplitz matrix. A Toeplitz is one where every diagonal descending from left to right has the same value.

Constraints

n, m ≤ 250 where n and m are the number of rows and columns in matrix
Example 1
Input
matrix = [
    [0, 1, 2],
    [3, 0, 1],
    [4, 3, 0],
    [5, 4, 3]
]
Output
true
Example 2
Input
matrix = [
    [1, 0, 0],
    [0, 0, 0],
    [0, 0, 1]
]
Output
false
Intuition
Check for the immediate diagonal element

Implementation
-> for each element, if the immediate diagonal element is not the same then return false
-> otherwise, return true

Example #1
For example, this is a Toeplitz matrix:

[0, 1, 2]
[3, 0, 1]
[4, 3, 0]
[5, 4, 3]
This is not a Toeplitz matrix:

[0, 1, 2]
[3, 0, 7]
[4, 3, 0]

Time Complexity
\mathcal{O}(n^2)O(n 
2
 ) -> only when the matrix is toeplitz in nature

Space Complexity
\mathcal{O}(1)O(1) -> since no additional space is used
///////////////////////////////
Intuition
just loop through the matrix and check if element at i,j ==i+1,j+1 or not
ie elements of each diagonal are equal or not

class Solution:
    def solve(self, matrix):
        for i in range(len(matrix) - 1):
            for j in range(len(matrix[0]) - 1):
                if matrix[i][j] != matrix[i + 1][j + 1]:
                    return False
        return True
//////////////////////////////
import java.util.*;

class Solution {
    public boolean solve(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (row + 1 < matrix.length && col + 1 < matrix[0].length) {
                    if (matrix[row + 1][col + 1] != matrix[row][col]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
