A Roomba robot is currently sitting in a Cartesian plane at (0, 0). You are given a list of its moves that it will make, containing NORTH, SOUTH, WEST, and EAST.

Return whether after its moves it will end up in the coordinate (x, y).

Constraints

n ≤ 100,000 where n is length of moves
Example 1
Input
moves = ["NORTH", "EAST"]
x = 1
y = 1
Output
true
Explanation
Moving north moves it to (0, 1) and moving east moves it to (1, 1)

Example 2
Input
moves = ["WEST", "EAST"]
x = 1
y = 0
Output
false
Explanation
This Roomba will end up at (0, 0)

Intuition
Increment/decrement x and y according to directions and check with target co-ordinate.

Implementation
Time Complexity
\mathcal{O}(n)O(n)

Space Complexity
\mathcal{O}(1)O(1)

import java.util.*;

class Solution {
    public boolean solve(String[] moves, int x, int y) {
        int x1 = 0, y1 = 0;
        for (int i = 0; i < moves.length; i++) {
            switch (moves[i]) {
                case "NORTH":
                    y1++;
                    break;
                case "SOUTH":
                    y1--;
                    break;
                case "EAST":
                    x1++;
                    break;
                case "WEST":
                    x1--;
                    break;
            }
        }
        return x == x1 && y == y1;
    }
}
