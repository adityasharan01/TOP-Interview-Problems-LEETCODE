K-Divisible Sublist
Given a list of non-negative integers nums and a positive integer k return whether there's some sublist of length at least 2 whose sum is a multiple of k.

Constraints

n ≤ 100,000 where n is the length of nums.
Example 1
Input
nums = [7, 4, 5]
k = 3
Output
true
Explanation
The sublist [4, 5] sums to 9 which is divisible by 3.
  
Hint1:Maintain a map of the previous sums mod k you have seen before.
Hint2:Only positive numbers are allowed

Intuition
Prefix sum is the first approach that i get when the question talks about sublist sum

Implementation
Here also we will calculate the prefix sum of the array and we will use a hashmap to store the curr_sum % mod
The idea is if we have p[j] mod k == some p[i] mod k (for i < j) then for the sublist {i+1, j} we will have the sum a multiple of k.
we will use a map to record the index of curr_sum % k , which will be useful for calculating the length of the sublist (as we also need to check the length factor >=2)

Time Complexity
\mathcal{O}(n)O(n) Single linear scan over input array followed by linear scan over prefix sum array with some book keeping

Space Complexity
\mathcal{O}(n)O(n) Extra space is used tp store the prefix sum and

import java.util.*;

class Solution {
    public boolean solve(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return false;
        int[] prefixsum = new int[nums.length + 1];
        for (int i = 1; i < prefixsum.length; i++) prefixsum[i] = prefixsum[i - 1] + nums[i - 1];

        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < prefixsum.length; i++) {
            int curr_sum = prefixsum[i];
            int mod = curr_sum % k;
            if (map.containsKey(mod) && i - map.get(mod) >= 2)
                return true;
            if (!map.containsKey(mod))
                map.put(mod, i);
        }
        return false;
    }
}
