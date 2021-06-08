You are given two-dimensional integer matrices matrix and target with the same number of rows and columns.

Consider an operation where we flip a particular column in matrix so that all 1s become 0s and all 0s become 1s.

Given you can reorder the matrix rows for free, return the minimum number of operations required to turn matrix into target. If it's not possible, return -1.

Constraints

n, m ≤ 100 where n and m are the number of rows and columns in matrix.
Example 1
Input
matrix = [
    [0, 0],
    [1, 0],
    [1, 1]
]
target = [
    [0, 1],
    [1, 0],
    [1, 1]
]
Output
1
Explanation
First reorder rows to:

[0, 0]
[1, 1]
[1, 0]
And then flip column 1 to:

[0, 1]
[1, 0]
[1, 1]

Hint1:How many rows of target can the first row of matrix get matched to?
//SOLUTION IN OYTHON 
  Intuition
First, let us take the case where we do not have to perform any column flips. Since reordering the matrix is free, how will we know if the matrix matches the target if the rows are out of order? If we sort the two matrices will be equal.

Take an example:

matrix

0 0
1 1
1 0
target

1 1
0 0
1 0
When we sort them we get equal matrices:

matrix

0 0
1 0
1 1
target

0 0
1 0
1 1
Let us now take the case where we must flip some number of columns. If we take the sorted matrices and inspect them column by column, we will find a
column where there is a mismatch. In this case, flip the bits of the column in matrix, counting the operation. Then resort the matrix and compare again.
If the column still does not match, then there is no solution; return -1. If the comparison is equal, we can move on to the next column.

Take example 1, (which is already sorted):

matrix

0 0
1 0
1 1
target

0 1
1 0
1 1
The first column compares equal, but the second does not. First, flip the bits in the matrix:

matrix

0 1
1 1
1 0
Sort the matrix and compare the column again:

matrix

0 1
1 0
1 1
target

0 1
1 0
1 1
Comparing the second column again now shows a match. Return 1, which is the number of column flips we have performed.

The editorial guidelines indicate that a complexity analysis should be done, something I must admit I am not very confident doing. However, I believe that
the worst case would be a 100x100 matrix where you must flip the bits of every column. The initial sorting would be 2 \times \mathcal{O}(log(n))2×O(log(n)). Flipping the bits would be a \mathcal{O}(n^2)O(n 
2
 ) operation. Finally, sorting the matrix for each column flip would be \mathcal{O}(n \times n \cdot log(n))O(n×n⋅log(n)). Throwing out the smaller terms, I believe overall the solution is \mathcal{O}(n^2 \cdot log(n))O(n 
2
 ⋅log(n)). Given, however, that the maximum n is 100, this is sufficient.

Since we work with the matrices as read, no additional space is required.

"""
binarysearch.io :: Columns Flip to Target
https://binarysearch.io/problems/Column-Flips-to-Target
"""


class Solution:
    def solve(self, matrix, target):
        """Solve puzzle."""
        soln = 0
        matrix.sort()
        target.sort()
        col = 0
        while col < len(matrix[0]):
            if any(matrix[row][col] != target[row][col] for row, _ in enumerate(matrix)):
                soln += 1
                for row, _ in enumerate(matrix):
                    matrix[row][col] = 1 if matrix[row][col] == 0 else 0
                matrix.sort()
                if any(matrix[row][col] != target[row][col] for row, _ in enumerate(matrix)):
                    return -1
            col += 1
        return soln
