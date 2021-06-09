Given a two-dimensional integer matrix of 1s and 0s where 0 represents empty cell and 1 represents a block that forms a shape, return the perimeter of the shape. There is guaranteed to be exactly one shape, and the shape has no holes in it.

Constraints

1 ≤ n, m ≤ 250 where n and m are the number of rows and columns in matrix
Example 1
Input
matrix = [
    [0, 1, 1],
    [0, 1, 0]
]
Output
8

Hint1:Try to find how much each 1 contributes to the total perimeter.
  
Intuition
Since we know that we only have 1 shape, we don't care about what it is. The perimeter, represents basically the line that connects the shape with the plane. So, you can think of it just as a line, and we don't care about the shape of it, we simply care about it's length. In terms of this problem, where we can find 'the line' ? Well, since 1 represents the shape and 0 nothing, we can think about the line as being in between this 2 elements: 0 | 1. Now we can look how many 0 neighbours the element has and simply add 1 for each of them. When the element doesn't have a neighbour in a specific direction, we can simply add 1, since a true geometrical plane is infinite.

Implementation
We look at each element, if it is 1, it is part of island and we check it's neighbours, if neighbour is 0 OR outside array(which would be 0) we increment per by 1 and move to the next element.

Time Complexity
\mathcal{O}(n*m)O(n∗m) Since we simply loop through the array.

Space Complexity
\mathcal{O}(1)O(1) We don't use any other variables than the ones we use to loop and the perimeter one.

import java.util.*;

class Solution {
    public int solve(int[][] matrix) {
        int per = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    if (i > 0 && matrix[i - 1][j] == 0) {
                        per++;
                    } else if (i == 0) {
                        per++;
                    }
                    // System.out.println(per);
                    if (i < matrix.length - 1 && matrix[i + 1][j] == 0) {
                        per++;
                    } else if (i == matrix.length - 1) {
                        per++;
                    }
                    // System.out.println(per);
                    if (j > 0 && matrix[i][j - 1] == 0) {
                        per++;
                    } else if (j == 0) {
                        per++;
                    }
                    // System.out.println(per);
                    if (j < matrix[i].length - 1 && matrix[i][j + 1] == 0) {
                        per++;
                    } else if (j == matrix[i].length - 1) {
                        per++;
                    }
                    // System.out.println(per);
                }
            }
        }

        return per;
    }
}


Suggestion 
     if (i==0 || matrix[i-1][j]==0) ct++;
                    if (j==0 || matrix[i][j-1]==0) ct++;
                    if (i==n-1 || matrix[i+1][j]==0) ct++;
                    if (j==m-1 || matrix[i][j+1]==0) ct++;
