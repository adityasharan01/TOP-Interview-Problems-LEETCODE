Given a binary tree root, return the longest path between any two nodes in the tree.

Constraints

n ≤ 100,000 where n is the number of nodes in root
Example 1
Input
Visualize
root =
0

1

2

3

0

4

Output
5
Explanation
A longest path is [1, 0, 2, 3, 4]

Example 2
Input
Visualize
root =
0

2

3

0

4

6

7

Output
6
Explanation
A longest path is [4, 3, 2, 0, 6, 7]
Intuition
Code is Self-Explanatory

Implementation
Time Complexity
\mathcal{O}(n)O(n)

Space Complexity
\mathcal{O}(1)O(1)

import java.util.*;

/**
 * public class Tree {
 *   int val;
 *   Tree left;
 *   Tree right;
 * }
 */
class Solution {
    int max;
    public int solve(Tree root) {
        max = 0;
        getHeight(root);
        return max;
    }

    public int getHeight(Tree root) {
        if (root == null)
            return 0;

        int l = getHeight(root.left);
        int r = getHeight(root.right);

        max = Math.max(max, l + r + 1);
        return 1 + Math.max(l, r);
    }
}
