Count BST Nodes in a Range
Given a binary search tree root, and integers lo and hi, return the count of all nodes in root whose values are between [lo, hi] (inclusive).

Constraints

n ≤ 100,000 where n is the number of nodes in root
Example 1
Input
Visualize
root =
3

2

9

7

12

4

8

lo = 5
hi = 10
Output
3
Explanation
Only 7, 8, 9 are between [5, 10].
  
  Intuition
We can use depth first search to recursively determine the number of nodes with values that fall within the range of lo and high while taking advantage of the properties of the binary search tree:

A node's left subtree only contains nodes with values that are less than the node's value.
A node's right subtree only contains nodes with values that are greater than the node's value.
Every left and right subtree is also a binary search tree.
Implementation
Please take a look at the comments in the code for reference.

Time Complexity
The worst case will take \mathcal{O}(n)O(n) time where every node's value is possible to fall in the range.

Space Complexity
There is only constant space being used, which is \mathcal{O}(1)O(1).

import java.util.*;

/**
 * public class Tree {
 *   int val;
 *   Tree left;
 *   Tree right;
 * }
 */
class Solution {
    public int solve(Tree root, int lo, int hi) {
        // If there no node exists, then there is no node that
        // has a value within the range
        if (root == null) {
            return 0;
        }

        // If the value of the current node is in within the range,
        // it is addded to the count. Check both left and right subtrees since
        // they both could contain nodes with values that are within the
        // range.
        if (root.val >= lo && root.val <= hi) {
            return 1 + solve(root.left, lo, hi) + solve(root.right, lo, hi);

            // If the value of the current node is less than the range, it
            // does not count. Check the right subtree since it only contains
            // nodes with values that are greater than it.
        } else if (root.val < lo) {
            return solve(root.right, lo, hi);

            // If the value of the current node is greater than the range, it
            // does not count. Check the left subtree since it only contains
            // nodes with values that are smaller than it.
        } else {
            return solve(root.left, lo, hi);
        }
    }
}
