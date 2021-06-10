A univalue tree is a tree where all nodes under it have the same value.

Given a binary tree root, return the number of univalue subtrees.

Constraints

1 ≤ n ≤ 100,000 where n is the number of nodes in root
Example 1
Input
Visualize
root =
0

1

0

1

0

1

1

Output
5
Explanation
The unival trees include four leaf nodes (three of them are 1s, the other one is the rightmost 0), and one subtree in the middle (containing all 1s).

Example 2
Input
Visualize
root =
1

0

0

Output
2
Explanation
The two leaf nodes are unival trees

Intuition
Use post-order traversal.
Use a global variable answer to update the answer.

Implementation
dfs is a postorder traversal function.
A node should return true if it's children are null (a leaf) or children's value == node's value && their recursive call is success.

Time Complexity
\mathcal{O}(n)O(n) We only touch every node once.

Space Complexity
\mathcal{O}(n)O(n)We only touch every node once...

import java.util.*;

/**
 * public class Tree {
 *   int val;
 *   Tree left;
 *   Tree right;
 * }
 */
class Solution {
    int ans = 0;

    public int solve(Tree root) {
        dfs(root);
        return ans;
    }

    private boolean dfs(Tree root) {
        if (root == null)
            return true;
        boolean l = dfs(root.left);
        boolean r = dfs(root.right);
        if ((root.left == null || (root.val == root.left.val && l))
            && (root.right == null || (root.val == root.right.val && r))) {
            ans++;
            return true;
        }
        return false;
    }
}
