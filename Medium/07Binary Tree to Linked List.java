Given a binary tree root, convert it to a singly linked list using an inorder traversal.

Constraints

n ≤ 100,000 where n is the number of nodes in root
Example 1
Input
Visualize
root =
2

1

4

3

Output
Visualize
1

2

3

4

Intuition
We just follow an inorder traversal for this logic. All that's needed to create LLNode and assign the tree value. Then keep going this way until the tree is either complete or has hit a Null

import java.util.*;

/**
 * public class Tree {
 *   int val;
 *   Tree left;
 *   Tree right;
 * }
 */

/**
 * class LLNode {
 *   int val;
 *   LLNode next;
 * }
 */
class Solution {
    LLNode head = new LLNode(0);
    LLNode curr = head;
    public LLNode solve(Tree root) {
        helper(root);
        return head.next;
    }
    public void helper(Tree root) {
        if (root != null) {
            helper(root.left);
            curr.next = new LLNode(root.val);
            curr = curr.next;
            helper(root.right);
        }
    }
}
