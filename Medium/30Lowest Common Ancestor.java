Given a binary tree root, and integers a and b, find the value of the lowest node that has a and b as descendants. A node can be a descendant of itself.

All nodes in the tree are guaranteed to be unique.

Constraints

n ≤ 100,000 where n is the number of nodes in root
Visualize
root =
0

1

2

6

5

3

4

a = 3
b = 5
Output
2
Example 2
Input
Visualize
root =
0

1

2

6

5

3

4

a = 6
b = 4
Output
6
//////////////////////////////////////////////////////////
Intuition
Check both the subtrees until any of nodes match with either a or b. as soon it finds one them return its value. At any of the nodes, if left and right subtrees return values, that is the common ancestor for both values.

Implementation
Recurs through both the left and right subtrees until both of them finds any of the values(a or b). If the left and right both return value that means the current node is the ancestor, so return current node value. if only one subtree returned value, bubble it up so that we find the ancestor at an upper level.

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
    public int solve(Tree root, int a, int b) {
        if (root == null)
            return Integer.MIN_VALUE;

        if (root.val == a || root.val == b)
            return root.val;

        int left = solve(root.left, a, b);
        int right = solve(root.right, a, b);

        if (left != Integer.MIN_VALUE && right != Integer.MIN_VALUE)
            return root.val;

        return (left != Integer.MIN_VALUE ? left : right);
    }
}
