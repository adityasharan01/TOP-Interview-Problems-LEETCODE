Given a binary tree root, find the largest subtree (the one with the most nodes) that is a binary search tree.

Constraints

n ≤ 100,000 where n is the number of nodes in root
Example 1
Input
Visualize
root =
1

3

2

5

Output
Visualize
3

2

5

Explanation
The root is not a valid binary search tree, but the tree beginning at 3 is.
  Hint1:Simple DFS
  
  Intuition
Hey so my solution is easy to understand

do traversal for each node (I am using preorder);
for each node check if it is a root for a binary tree, at the same time update the global maxCount (count the number of node in binary tree) and maxNode (the root of binary tree with max nodes count);
the final result should be the maxNode i update in the global
Implementation
I have two helper function
solvetraversal(Tree root) : do the preorder traversal for tree (use recursion method)
binaryTreeCheck(Tree node) : check if current node is BST and return the nodes inside this tree, if it is not a BST, return -1

Time Complexity
\mathcal{O}(n\log n )O(nlogn) : \mathcal{O}(n )O(n) for preorder traversal and \mathcal{O}(h )O(h) for each node BST check

Space Complexity
\mathcal{O}(h)O(h): we only need two global variable which is maxCount and maxNode to keep update the result, and traversal should take stack space for h

import java.util.*;

/**
 * public class Tree {
 *   int val;
 *   Tree left;
 *   Tree right;
 * }
 */
class Solution {
    int maxCount = 0;
    Tree maxNode = null;
    public Tree solve(Tree root) {
        solvetraversal(root);
        return maxNode;
    }

    public void solvetraversal(Tree root) {
        if (root != null) {
            int curCount = binaryTreeCheck(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (curCount > maxCount) {
                maxNode = root;
                maxCount = curCount;
                return;
            }
        }
        if (root.left != null) {
            solvetraversal(root.left);
        }
        if (root.right != null) {
            solvetraversal(root.right);
        }

        return;
    }

    public int binaryTreeCheck(Tree node, int lower, int higher) {
        if (node.val < lower || node.val > higher) {
            return -1;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }

        if (node.left != null && node.left.val > node.val) {
            return -1;
        }
        if (node.right != null && node.right.val < node.val) {
            return -1;
        }
        int leftSum = 0;
        if (node.left != null) {
            leftSum = binaryTreeCheck(node.left, lower, node.val);
        }
        int rightSum = 0;
        if (node.right != null) {
            rightSum = binaryTreeCheck(node.right, node.val, higher);
        }

        if (leftSum == -1 || rightSum == -1) {
            return -1;
        }
        return 1 + leftSum + rightSum;
    }
}
