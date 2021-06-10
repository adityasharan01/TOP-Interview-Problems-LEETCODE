Given a binary tree root, return the leftmost node's value on each level of the tree.

Constraints

n ≤ 100,000 where n is the number of nodes in root
Example 1
Input
Visualize
root =
0

5

2

1

Output
[0, 5, 1]

Hint1:Think from the output of level order traversal.
  
  Intuition
Simple preorder traversal by tracking the level we're at in the tree

Implementation
Each index in the list represents the left-most node at that level. By tracking the level of that list that we are at, we can check if the leftmost node exists in the list or not by comparing the level. The condition, list.size() == level, checks that if we're at the 5th level and the list is of size 5 (containing the leftmost node of 0, 1, 2, 3, and 4th levels) then that means that a new for the 5th level can be added, if it's already added, the list size would be 6, indicating to skip adding the node.

Time Complexity
\mathcal{O}(n)O(n) - preorder traversal iterates every node of the entire tree, so n is the number of nodes in the tree

Space Complexity
\mathcal{O}(m)O(m) - each recursion call takes \mathcal{O}(1)O(1) space, but space complexity of the recursive algorithm is proportional to maximum height of the tree (m)

import java.util.*;

/**
 * public class Tree {
 *   int val;
 *   Tree left;
 *   Tree right;
 * }
 */
class Solution {
    List<Integer> list = new ArrayList<>();
    public int[] solve(Tree root) {
        helper(root, 0);
        // converts List<Integer> to int[] array
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private void helper(Tree root, int level) {
        if (root != null) {
            if (list.size() == level) {
                list.add(root.val);
            }
            helper(root.left, level + 1);
            helper(root.right, level + 1);
        }
    }
}
