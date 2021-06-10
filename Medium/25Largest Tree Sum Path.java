Given a binary tree root, return the largest sum of any path between any two nodes.

Constraints

n ≤ 100,000 where n is the number of nodes in root
Example 1
Input
Visualize
root =
-6

5

4

7

12

4

8

2

Output
31
Explanation
The largest sum path is: [8, 7, 4, 12]
Intuition
At each node, the potential maximum path could be one of these cases:

max(left subtree) + node
max(right subtree) + node
max(left subtree) + max(right subtree) + node
the node itself
Then, we need to return the maximum path sum that goes through this node and to either one of its left or right subtree to its parent. There’s a little trick here: If this maximum happens to be negative, we should return 0, which means: “Do not include this subtree as part of the maximum path of the parent node”, which greatly simplifies our code.

import java.util.*;

/**
 * public class Tree {
 *   int val;
 *   Tree left;
 *   Tree right;
 * }
 */
class Solution {
    private int maxPath;

    public int solve(Tree root) {
        if (root == null)
            return 0;
        maxPath = Integer.MIN_VALUE;
        findMaxPath(root);
        return maxPath;
    }

    private int findMaxPath(Tree root) {
        if (root == null)
            return 0;
        int left = findMaxPath(root.left);
        int right = findMaxPath(root.right);
        maxPath = Math.max(maxPath, root.val + left + right);
        return Math.max(0, root.val + Math.max(left, right));
    }
}
