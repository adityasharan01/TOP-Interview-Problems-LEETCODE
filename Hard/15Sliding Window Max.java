Given a list of integers nums and an integer k, return the maximum values of each sublist of length k.

Constraints

1 ≤ n ≤ 100,000 where n is the length of nums.
1 ≤ k ≤ 100,000.
Example 1
Input
nums = [10, 5, 2, 7, 8, 7]
k = 3
Output
[10, 7, 8, 8]
Explanation
10 = max(10, 5, 2)
7 = max(5, 2, 7)
8 = max(2, 7, 8)
8 = max(7, 8, 7)
Example 2
Input
nums = [1, 2, 3, 4, 5, 4, 3, 2, 1]
k = 3
Output
[3, 4, 5, 5, 5, 4, 3]
Example 3
Input
nums = [3, 2, 1, 2, 3]
k = 2
Output
[3, 2, 2, 3]
Example 4
Input
nums = [3, 2, 1, 2, 3]
k = 5
Output
[3]
Hint1:Can you think of Data Structure that will help in solving this ?
Hint2:If you have found a O(NlogN) solution, try to do it in O(N).
  
Intuition
If we have a data structure that can give us the maximum element in a group of elements in \mathcal{O}(1)O(1) time, then we can solve this problem in \mathcal{O}(n)O(n) by adding every \mathcal{k}k consecutive elements into our data structure and calculating each of the maximum element.

One data structure that gives the maximum element in \mathcal{O}(1)O(1) time is called a Monotonic Queue. The idea is that we will add every \mathcal{k}k consecutive elements in a sliding window fashion, and for each window of \mathcal{k}k elements, we will maintain a decreasing queue. Thus, the first element of the queue will always be the largest element in the queue.

Implementation
We add each window of \mathcal{k}k elements into our Monotonic Queue. In each window, the max element is the first element. In my implementation I add indices instead of the actual values. To transition to the next window, we pop off every element in the beginning of the queue that is out of the window. Then we add the next element in the window maintaining the decreasing queue.

Time Complexity
\mathcal{O}(n)O(n) We touch every element twice and there are n of them.

Space Complexity
\mathcal{O}(n)O(n) We use a queue holding n elements at most.

import java.util.*;

class Solution {
    public int[] solve(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        LinkedList<Integer> q = new LinkedList();

        // first window
        for (int i = 0; i < k; i++) {
            while (q.size() > 0 && nums[i] > nums[q.getLast()]) {
                q.removeLast();
            }
            q.addLast(i);
        }

        res[0] = nums[q.getFirst()];
        for (int i = 1; i < n; i++) {
            if (i + k - 1 >= n)
                break;
            while (q.size() > 0 && q.getFirst() < i) q.removeFirst();
            while (q.size() > 0 && nums[i + k - 1] > nums[q.getLast()]) q.removeLast();

            q.addLast(i + k - 1);

            res[i] = nums[q.getFirst()];
        }
        return res;
    }
}
  
