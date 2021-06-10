Given a list of unsorted integers nums, and an integer k, return the kth (0-indexed) smallest element in the list.

This should be done in \mathcal{O}(n)O(n) time on average.

Constraints

0 ≤ k < n ≤ 100,000 where n is the length of nums
Example 1
Input
nums = [5, 3, 8, 2, 0]
k = 2
Output
3
Explanation
When sorted the numbers are [0, 2, 3, 5, 8] and index 2's value is 3.
  
Hint1:We only need to find a number such that there are (k-1) smaller elements than that number in the array.
Intuition
Pick last element as pivot
Partition the array such that every number <= pivot are before it.
Let pivot be the Jth element.
a. If J == K, then return it.
b. If J < K, then recurse on the right side K - J
c. If J > K, then recurse on the left side
Implementation
I use recursion to implement this since its easier to visualize in my head

Time Complexity
\mathcal{O}(n)O(n) The average time is O(N), this is called Quickselecting by picking the last element as the pivot

Space Complexity
\mathcal{O}(\log n )O(logn) The recursive stack is logarithmic in the average case

import java.util.*;

class Solution {
    public int partition(int l, int r, int[] nums) {
        int pivot = nums[r];
        int pos = l;
        for (int i = l; i <= r; i++) {
            if (nums[i] <= pivot) {
                int tmp = nums[i];
                nums[i] = nums[pos];
                nums[pos] = tmp;
                pos++;
            }
        }
        return pos - 1;
    }
    public int solve(int[] nums, int k) {
        return recurse(nums, 0, nums.length - 1, k + 1);
    }

    public int recurse(int[] nums, int l, int r, int k) {
        /*
        1. Pick last element as pivot
        2. Partition the array such that every number <= pivot are before it.
        3. Let pivot be the Jth element.
            a. If J == K, then return it.
            b. If J < K, then recurse on the right side K - J
            c. If J > K, then recurse on the left side
        */
        int pivot = partition(l, r, nums);
        int jth = pivot - l + 1;
        if (k == jth)
            return nums[pivot];
        if (jth < k) {
            return recurse(nums, pivot + 1, r, k - jth);
        }
        return recurse(nums, l, pivot - 1, k);
    }
}
