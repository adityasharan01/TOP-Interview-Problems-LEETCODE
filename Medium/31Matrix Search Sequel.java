Given a two-dimensional integer matrix, where every row and column is sorted in ascending order, return whether an integer target exists in the matrix.

This should be done in \mathcal{O}(n + m)O(n+m) time.

Constraints

n, m ≤ 250 where n and m are the number of rows and columns in matrix
Example 1
Input
matrix = [
    [1, 3, 9],
    [2, 5, 10],
    [5, 7, 13]
]
target = 7
Output
true

Hint1:Consider starting the search from the top right corner of matrix.
Intuition
As problem explicitly mentions the rows and cols are sorted in increasing order, the idea is to start searching from top - right corner of matrix.

Search in downward direction if target value is greater than current element.
Search in left direction if target value is less than current element
Implementation
Starting moving in left or down direction from top - right corner by checking
-> if the target is equal to current value, return true
-> if target < current value, move in left direction
-> if target > current value, move in down direction

by this way of search we can skip the elements that dont want to be in search space as our matrix is sorted row and col - wise

Example #1
| 1  3   7 |
| 4  8   9 |
| 6  10 15 |
From above matrix search for 6,

start from
 7
cur value is 7 and target is 6 as 6 < 7, move in left direction i.e j-- (column wise)

Now our path looks as
3 <--- 7
As, 6 > 3 (target > cur), move downwards
Now our path looks as

3 <--- 7
|
8
6 < 8, (target < cur) so move in left direction

Now our path looks as

           3 <--- 7
           |
     4  <- 8
6 > 4 so move in down direction
Now our path looks as

           3 <--- 7
           |
     4  <- 8
     |
     6
as cur (6) == target, return true

Time Complexity
Considering n = rows, m = columns, our time complexity is \mathcal{O}(n + m)O(n+m)

Space Complexity
Space complexity for the our problem is \mathcal{O}(1)O(1)

import java.util.*;

class Solution {
    public boolean solve(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = 0, j = cols - 1; i < rows && j >= 0;) {
            int cur = matrix[i][j];
            if (target < cur) {
                j--;
            } else if (target > cur) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }
}
