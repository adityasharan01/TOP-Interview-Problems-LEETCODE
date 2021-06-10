Given a binary tree root, return the largest absolute difference between any node and its descendants.

Constraints

n ≤ 100,000 where n is the number of nodes in root
Example 1
Input
Visualize
root =
0

4

2

1

7

6

3

Output
7
Explanation
The largest absolute difference is between nodes 7 and 0.
  
 Intuition
If for every node, we know the smallest and largest value in its subtree, then we can compute our answer by taking the max abs difference of every current node and the smallest and largest values in its subtree.

We're going to do just that in \mathcal{O}(n)O(n) by doing bottom-up DFS, by returning the smallest and largest value we see in its subtree.

Implementation
At every dfs call we're going to return a tuple of smallest and largest value in its subtree.

Time Complexity
\mathcal{O}(n)O(n) Because we visit every node once.

Space Complexity
\mathcal{O}(n)O(n) Because we visit every node once.

import java.util.*;

/**
 * public class Tree {
 *   int val;
 *   Tree left;
 *   Tree right;
 * }
 */
class Solution {
    int res = 0;
    public int solve(Tree root) {
        if (root == null)
            return 0;
        dfs(root);
        return res;
    }

    public Integer[] dfs(Tree root) {
        int cur = root.val;
        int smallest = root.val;
        int largest = root.val;

        if (root.left == null && root.right == null) {
            return new Integer[] {cur, cur};
        }
        if (root.left != null) {
            Integer[] left = dfs(root.left);
            smallest = Math.min(smallest, left[0]);
            largest = Math.max(largest, left[1]);
        }
        if (root.right != null) {
            Integer[] right = dfs(root.right);
            smallest = Math.min(smallest, right[0]);
            largest = Math.max(largest, right[1]);
        }

        res = Math.max(res, Math.abs(cur - largest));
        res = Math.max(res, Math.abs(cur - smallest));
        return new Integer[] {smallest, largest};
    }
}

///////////////
///////////////
///////////////
///////////////
Intuition
Explanation:
The maximum difference we can get from root root will be either contain the minimum value in its subtrees, or the maximum values in its subtrees
Thus, we will construct two helper methods, findMax and findMin
The driver function, solve, will simply find the min, b, the max, a, and contribute the differences with root.val to get our optimal solution
It then recursively calls its left and right subtrees, which perform the same function

Source code in java:
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
        if (root == null)
            return 0;
        int a = findMax(root), b = findMin(root);
        int c = Math.max(Math.abs(a - root.val), Math.abs(b - root.val));
        return Math.max(c, Math.max(solve(root.left), solve(root.right)));
    }
    public int findMax(Tree root) {
        int a = root.val;
        if (root.left != null)
            a = Math.max(a, findMax(root.left));
        if (root.right != null)
            a = Math.max(a, findMax(root.right));
        return a;
    }
    public int findMin(Tree root) {
        int a = root.val;
        if (root.left != null)
            a = Math.min(a, findMin(root.left));
        if (root.right != null)
            a = Math.min(a, findMin(root.right));
        return a;
    }
}
