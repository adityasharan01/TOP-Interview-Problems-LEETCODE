You are given a binary tree root with each node containing single digits from 0 to 9. Each path from the root to the leaf represents a number with its digits in order.

Return the sum of numbers represented by all paths in the tree.

Example 1
Input
Visualize
root =
3

5

2

1

4

Output
680
Explanation
We have the following numbers represented by paths:

35 (3 → 5)
321 (3 → 2 → 1)
324 (3 → 2 → 4)
Example 2
Input
Visualize
root =
1

2

3

Output
25
Explanation
We have 12 + 13 = 25.
  
Hint1:BFS
Intuition
For null nodes return 0, so that they dont contribute to the sum
Keep building the digits until the sum is returned from the leaf nodes
Return the sum from both left and right nodes after adding both

Implementation
As it is a tree, we can use DFS to keep track of the parent sum and add current root val to the parent sum.
Once we reach the leaf node, we return the value from there itself

Example
6 -> 0 * 10 + 6, 0 is its parent
/
3 null -> for 3 we add 6 * 10 + 3 and return as 3 is the leaf node
-> for null we return 0
at 6 we add 63 + 0 , so result = 63

Time Complexity
\mathcal{O}(n)O(n) where n is the number of nodes in the tree

Space Complexity
\mathcal{O}(n)O(n) where n is the number of nodes in the tree

import java.util.*;

/**
 * public class Tree {
 *   int val;
 *   Tree left;
 *   Tree right;
 * }
 */
class Solution {
    public int solve(Tree root) {
        return sum(root, 0);
    }

    private int sum(Tree root, int parent) {
        if (root == null)
            return 0;
        parent *= 10;
        parent += root.val;
        if (root.left == null && root.right == null)
            return parent;
        return sum(root.left, parent) + sum(root.right, parent);
    }
}
