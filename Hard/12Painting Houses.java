You are looking to paint a row of N fences that can be of K different colors. You have a goal of minimizing cost while ensuring that no two neighboring fences are of the same color.

Given an N by K matrix where the nth row and kth column represents the cost to paint the nth fence with kth color, return the minimum cost which achieves this goal.

Note: All costs will be integers greater than or equal to 0, and there is guaranteed to be at least 2 possible colors.

Bonus: solve in \mathcal{O}(1)O(1) space.

Constraints

N, K ≤ 500
Example 1
Input
matrix = [
    [5, 3, 4],
    [2, 1, 6],
    [2, 3, 4],
    [4, 3, 3]
]
Output
10
Explanation
Choose the following color indices (starting from the first fence): 4 -> 1 -> 2 -> 3.

Example 2
Input
matrix = [
    [1, 1, 1],
    [1, 1, 1],
    [1, 1, 1],
    [1, 1, 1],
    [1, 1, 1]
]
Output
5
Explanation
It doesn't matter which colors we paint since they're all cost 1.

Example 3
Input
matrix = [
    [1, 5, 1],
    [1, 5, 1],
    [1, 5, 1],
    [1, 5, 1],
    [1, 5, 1]
]
Output
5
Explanation
Alternate between the first and the third color which each cost 1.

Example 4
Input
matrix = [
    [1, 2, 3],
    [4, 1, 8],
    [2, 3, 4],
    [3, 3, 1],
    [4, 2, 3]
]
Output
7
Explanation
Choose the following colors: 1 -> 1 -> 2 -> 1 -> 2.
  
  Intuition
Let's break this problem in below way:
Consider we have only 3 colors and we have to find the min cost, while no same color should be adjacent.

R G B
1 2 3
4 5 6
7 8 9

now we have 3 houses, if we want to paint only one house ans will be min of first row, which is min(1,2,3) ==1

-> Let us store first row values to help while calculating 2nd house cost

prevR = 1, prevG =2 , prevB =3

Now if we want to paint 2nd house :

Red= 4 + min(prevG,prevB) = 4+min(2,3) = 6 (avoid taking red from previous to make sure adjacent colors are not same)
Green = 5 + min(prevR,prevB) = 5+min(1,3) = 6
Blue = 6 + min(prevR,prevG) = 6+min(1,2) = 7
it clearly states that, in case of two houses, cost would be 6

Let's paint the 3rd house also:

Replace the prev's cost - prevR = 6, prevG = 6 , prevB = 7

Red = 7+min(prevG,prevB) = 7 + min(6,7) = 13
Green = 8+ min(prevR,prevB) = 8+min(6,7) = 14
Blue= 9+ min(prevR,prevG) = 9+min(6,6) = 15

For 3 houses, the paint cost would be 13.

Similarly, we can paint n houses with k colors.

Implementation
We need to follow above instructions and integrate the same for k colors.

Time Complexity
Time Complexity - \mathcal{O}(n)O(n) * \mathcal{O}(k*k)O(k∗k)

Space Complexity
Space Complexity - \mathcal{O}(k)O(k) for calculating the mid result

import java.util.*;

class Solution {
    public int solve(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[] ans = new int[m];

        // for first house just copy the cost, if there is only one house we can return the min
        for (int i = 0; i < m; i++) ans[i] = matrix[0][i];

        // we need to iterate for every houses and find the min cost value on that
        // row except the current color cost and add it
        // do this process for all the colors

        for (int i = 1; i < n; i++) {
            int[] temp = new int[m];
            for (int j = 0; j < m; j++) {
                temp[j] = matrix[i][j] + findMin(j, ans);
            }
            ans = temp.clone();
        }

        // now we have the sum of very color cost, just we need to return the min one
        return minRes(ans);
    }

    // find the mix cost
    int findMin(int flag, int[] ans) {
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < ans.length; i++) {
            if (i == flag)
                continue;
            res = Math.min(res, ans[i]);
        }
        return res;
    }
    // find the min cost except current color cost
    int minRes(int[] ans) {
        int res = Integer.MAX_VALUE;
        for (int i : ans) {
            res = Math.min(res, i);
        }
        return res;
    }
}
//////////////////////////////////////////////////////
Intuition
We will solve this problem with dynamic programming. Let f(n, k)f(n,k) be the minimum cost needed to color the first nn fences, such that the last fence is painted with color kk. This will be our state.

The naive DP approach is as follows - we compute f(n, k)f(n,k) by looping over all f(n-1, l)f(n−1,l) for all k \neq lk

=l and take the one of minimum cost. This approach runs in \mathcal{O}(NK^2)O(NK 
2
 ) time and uses \mathcal{O}(NK)O(NK) space, which is too slow.

If we look more closely at the DP transition, we see that for the n-1n−1th fence, we almost always use the state involving the minimum cost needed to paint n-1n−1 fences. In particular, that state is applicable for K-1K−1 colors, and for that specific color, we use the second-smallest cost present. With this optimization, instead of considering \mathcal{O}(K)O(K) transitions per state, we only consider \mathcal{O}(1)O(1) transitions. This approach runs in \mathcal{O}(NK)O(NK) time and uses \mathcal{O}(NK)O(NK) space, which is fast enough.

We will now mention the two optimizations needed to get down to \mathcal{O}(1)O(1) space. The first one is that f(n, k)f(n,k) only depends on f(n-1, l)f(n−1,l), so we can discard all states for f(n-2, m)f(n−2,m) when computing f(n, k)f(n,k). This gets us down to \mathcal{O}(K)O(K) space.

To go down to \mathcal{O}(1)O(1) space, we cannot explicitly store all values of f(n, k)f(n,k) varying over kk - we can only keep track of the two cheapest states.

Implementation
We keep track of the two best states from the previous iteration in the best vector, and construct the two best states in the current iteration in the nextbest vector, which we maintain explicitly in sorted order.

Time Complexity
There are \mathcal{O}(NK)O(NK) states in our DP, so the time complexity is \mathcal{O}(NK)O(NK).

Space Complexity
We only maintain two DP states per iteration of the for loop, so this runs in \mathcal{O}(1)O(1) space.

void ins(vector<pair<int, int>>& v, pair<int, int> candidate) {
    if (candidate.first < v[0].first) {
        swap(v[0], v[1]);
        v[0] = candidate;
    } else if (candidate.first < v[1].first) {
        v[1] = candidate;
    }
}

int solve(vector<vector<int>>& matrix) {
    vector<pair<int, int>> best;
    best.emplace_back(0, -1);
    for (int i = 0; i < matrix.size(); i++) {
        vector<pair<int, int>> nextbest;
        nextbest.emplace_back(2e9, -1);
        nextbest.emplace_back(2e9, -1);
        for (int j = 0; j < matrix[i].size(); j++) {
            for (pair<int, int> src : best) {
                if (src.second != j) {
                    ins(nextbest, {src.first + matrix[i][j], j});
                    break;
                }
            }
        }
        best.swap(nextbest);
    }
    return best[0].first;
}
