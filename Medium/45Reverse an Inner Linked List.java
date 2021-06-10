Given a singly linked list node, and integers i and j, reverse the linked list from i to jth nodes, 0 indexed and inclusive.

You should return the head of the reversed list.

This should be done in \mathcal{O}(1)O(1) space.

Constraints

n ≤ 100,000 where n is the number nodes in node
Example 1
Input
Visualize
node =
0

1

3

4

i = 1
j = 2
Output
Visualize
0

3

1

4

Intuition
Check if the given node is null or starting index > target index (i > j) or starting index < 0 (i < 0). if true return null;
Traverse the given linked list by keeping track of the previous node of the initial index node(i) and the next node of the target node index (j) so that we can have a pointer maintained to point after traversing.
Reverse the nodes between the given index range and return the head of the reversed range linked list.
With reference from step 2 point next of the previous node to head from step 3 and end node from step 3 to the next node stored in step 2
Space complexity: O(1)
Time complexity: O(n)
import java.util.*;

/**
 * class LLNode {
 *   int val;
 *   LLNode next;
 * }
 */
class Solution {
    public LLNode solve(LLNode node, int i, int j) {
        if (node == null || i > j || i < 0)
            return null;
        LLNode head = new LLNode(-1);
        head.next = node;
        LLNode initNode = head, targetNode = head;
        int startIndex = -1, targetIndex = -1;
        LLNode prevNode = null, nextNode = null;
        while (targetNode != null && ++targetIndex <= j) {
            if (++startIndex <= i) {
                prevNode = initNode;
                initNode = initNode.next;
            }
            targetNode = targetNode.next;
            nextNode = targetNode.next;
        }

        LLNode tempNode = reverse(initNode, i, j);
        prevNode.next = tempNode;
        LLNode currNode = tempNode;
        while (currNode.next != null) currNode = currNode.next;
        currNode.next = nextNode;

        return head.next;
    }
    public LLNode reverse(LLNode node, int i, int j) {
        LLNode prevNode = null, nextNode = null, currNode = node;
        while (currNode != null && i++ <= j) {
            nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }
        return prevNode;
    }
}
