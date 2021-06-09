You are given two integers r and c. Given that knight is placed initially at the coordinate (0, 0) in an infinitely large chess board, return the minimum number of moves it would take to reach (r, c).

Constraints

0 ≤ r, c ≤ 300
Example 1
Input
r = 1
c = 0
Output
3
Explanation
One possible sequence of moves is (0, 0) → (2, 1) → (0, 2) → (1, 0)
  
  Hint1:
use offset array instead of set.
  
  
  Link:https://www.techiedelight.com/chess-knight-problem-find-shortest-path-source-destination/
  
  
  
  
  
  
  
  
  //////////////////////////////////////////////////////////////////////
  Intuition
we can solve it using dynamic programming
we can start at the position r, c and see the smallest number of moves to reach 0, 0
our base cases will be when
r, c == 0, 0: 0
r, c in [(2, 0), (0, 2), (1, 1)]: we need 2 moves
For all other moves, we can just take the absolute values by decreasing by 1 and 2 both sides
Since there can be overlapping subproblems (like 1, 1 can go to 0, 1 and 1, 0) it's possible that these are already visited, so we cache them.
Complexity

Time: \mathcal{O}(rc)O(rc)
Space: \mathcal{O}(1)O(1)
class Solution:
    def solve(self, r, c):
        @functools.lru_cache(None)
        def dp(x=r, y=c):
            if x + y == 0:
                return 0
            if x + y == 2:
                return 2
            return min(dp(abs(x - 1), abs(y - 2)), dp(abs(x - 2), abs(y - 1))) + 1

        return dp()
