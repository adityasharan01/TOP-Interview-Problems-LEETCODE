You are given a list of integers items and an integer n. A salesperson has items in a bag with random IDs. The salesperson can remove as many as n items from the bag. Determine the minimum number of different IDs the final bag can contain after performing, at most n deletions.

Constraints

n ≤ 100,000 where n is the length of items
Example 1
Input
items = [1, 1, 1, 0, 0]
n = 2
Output
1
Explanation
We can delete the two 0s to get 1s. Then there's only 1 unique ID left.

Example 2
Input
items = [0, 0, 1, 1, 2, 3]
n = 2
Output
2
Explanation
We can delete the 2 and the 3 to get [0, 0, 1, 1]. Then there's only 2 unique ID left.
  
  Intuition
The idea is to use greedy algorithm. Greedy algo's are usually accompanied by sorting or heap

Implementation
We will first try to create a {id, freq} map for the given items in the input.
Since updates are also required when we remove an item (as freq would be -1) we will use a PQ which support each CRUD operation in logn.
We will remove from the item which has the least count , so that it get depleted first and we are left with less items.

Time Complexity
\mathcal{O}(nlogn)O(nlogn) At max we can have all distinct items and we could have to pop n times on a heap of size n

Space Complexity
\mathcal{O}(n)O(n) Extra space is used as nodes in the PQ (min heap)

import java.util.*;

class Solution {
    public int solve(int[] items, int n) {
        if (items == null || items.length == 0)
            return 0;
        Map<Integer, Integer> map = new HashMap();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int item : items) map.put(item, map.getOrDefault(item, 0) + 1);
        for (int key : map.keySet()) pq.offer(new int[] {key, map.get(key)});

        while (!pq.isEmpty() && --n >= 0) {
            int[] node = pq.poll();
            int id = node[0], freq = node[1];
            if (freq == 1)
                continue;
            pq.offer(new int[] {id, freq - 1});
        }
        return pq.size();
    }
}
