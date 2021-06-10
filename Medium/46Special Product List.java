Special Product List
Given a list of integers nums, return a new list such that each element at index i of the new list is the product of all the numbers in the original list except the one at i. Do this without using division.

Constraints

2 ≤ n ≤ 100,000 where n is the length of nums
Example 1
Input
nums = [1, 2, 3, 4, 5]
Output
[120, 60, 40, 30, 24]
Explanation
120 = 2 * 3 * 4 * 5, 60 = 1 * 3 * 4 * 5, and so on.

Example 2
Input
nums = [3, 2, 1]
Output
[2, 3, 6]

Hint1:Think about how you can precompute the products.
Hint2:Use prefix and suffix product arrays to compute the new products. Relate this to how prefix sums work.
Hint3:You can make it a little bit more space efficient by computing prefix on the fly.

  Intuition
For each index, if we know the product of all the numbers of the left and the product of all the numbers on the right, then we have our solution if we multiply those two numbers for each index i.

Implementation
We can precompute the product of all numbers on the left and product of all numbers on the right for each index i, using prefix products and suffix products.

Time Complexity
\mathcal{O}(n)O(n) We go through the array 3 times, each time n elements.

Space Complexity
\mathcal{O}(n)O(n) We use two auxiliary memory, each with n elements.

import java.util.*;

class Solution {
    public int[] solve(int[] nums) {
        int n = nums.length;

        int[] prefix = new int[n + 1];
        int[] suffix = new int[n + 1];
        Arrays.fill(prefix, 1);
        Arrays.fill(suffix, 1);

        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i];
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = prefix[i] * suffix[i + 1];
        }
        return res;
    }
}
