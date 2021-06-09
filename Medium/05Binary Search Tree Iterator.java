Binary Search Tree Iterator
Implement a binary search tree iterator with the following methods:

next returns the next smallest element in the tree
hasnext returns whether there is a next element in the iterator
For example, given the following tree

   4
  / \
 2   7
    / \
   5   9
It should return the values in this order 2, 4, 5, 7, 9.

Example 1
Input
methods = ["constructor", "hasnext", "hasnext", "next", "hasnext", "hasnext", "hasnext", "next", "hasnext", "hasnext", "hasnext", "next", "hasnext", "next", "hasnext"]
arguments = [[[2, [1, [0, null, null], null], [3, null, null]]], [], [], [], [], [], [], [], [], [], [], [], [], [], []]`
Output
[null, true, true, 0, true, true, true, 1, true, true, true, 2, true, 3, false]


Intuition
We need to travers the binary search tree in an InOrder traversal to be able to get the elements of the tree in ascending order.

We can do so without necessarily read the whole tree. The InOrder traversal is actually a depth first traversal, except that we visit left subtree first, get the value of the current node, then visit the right subtree. Therefore if we keep a stack of all the elements we haven't yet traversed we can simulate an in order traversal.

Implementation
We keep a stack of all the nodes yet to be traversed. Initially we fill the stack with only left elements. Now we can answer the two methods next and hasNext the following way:

Next:

Since we initially filled the stack with all elements by going only left we have the following:
The top element of the stack is the node with least value not yet visited, thus it contains the next value of the iterator.
The top element of the stack (next element of the iterator) will either not have a left child, or its left child already been poped using by calling next.
We return the value of the element on the top of the stack.
We still need to visit the right subtree of the least element, in this case its the same node in the top of the subtree.
Then we just fill the stack again by the right subtree of the stack the same way we did initially with the root.
HasNext:
It's clear that as long as we have nodes that we didn't visit yet hasNext should return true. Since we keep the stack with all elements not yet visited, we can simply check if the stack is not empty.

Time Complexity
hasNext: since we only check if the stack contains elements, the complexity of hasNext is \mathcal{O}(1)O(1)
Next: It appears that the complexity of next is \mathcal{O}(n)O(n) but actually we can do an amortised analysis
We can clearly see that every node will be put exactly once on the stack.
We can also see that every node will be popped exactly once from the stack.
Thus every node will be visited exactly once.
Therefore it takes \mathcal{O}(n)O(n) to get all elements using the iterator.
This will give us an amortised or avg complexity per call to next to be \mathcal{O}(1)O(1)
Space Complexity
Since we're keeping a stack with all elements not yet visited, if for example the tree is a list having only left elements, there is a chance that we will initially put all elements of the tree in the stack, thus the space complexity is \mathcal{O}(n)O(n)

However the space complexity if the binary search tree is balanced is \mathcal{O}(\log n )O(logn) since we will at most have elements from root to depth of the tree that happens to be \mathcal{O}(\log n )O(logn) if it's balanced.

import java.util.*;

/**
 * public class Tree {
 *   int val;
 *   Tree left;
 *   Tree right;
 * }
 */
class BSTIterator {
    Stack<Tree> stk;
    public BSTIterator(Tree root) {
        stk = new Stack<Tree>();
        fillStack(root);
    }

    private void fillStack(Tree node) {
        while (node != null) {
            stk.push(node);
            node = node.left;
        }
    }

    public int next() {
        Tree node = stk.pop();
        fillStack(node.right);
        return node.val;
    }

    public boolean hasnext() {
        return !stk.isEmpty();
    }
}
