You are given a list of non-negative integers nums where each element represents the height of a hill. Suppose it will rain and all the spaces between two sides get filled up.

Return the amount of rain that would be caught between the hills.

Constraints

n ≤ 100,000 where n is the length of nums
Example 1
Input
nums = [2, 5, 2, 0, 5, 8, 8]
Output
8
Explanation
nums[2] can catch 3 rain drops, and nums[3] can catch 5 for a total of 8.

Example 2
Input
nums = [2, 1, 2]
Output
1
Explanation
We can hold 1 unit of water in middle.

Example 3
Input
nums = [3, 0, 1, 3, 0, 5]
Output
8
Explanation
We can hold 3 units in the first index, 2 in the second, and 3 in the fourth index (we cannot hold 5 since it would run off to the left), so we can catch 8 units of water.
Hint1:Solve the subproblem where water can only flow to the left.
Topics:Two Pointers,Arrays.
  
Intuition
If for every 'hill', we know how much water is trapped inside that hill, we can get our solution by summing the amount of water trapped inside every hill. Note that some hills dont have any trapped water and that's ok.

We can find out how much water is trapped inside a single hill, by seeing that the amount of water trapped = min(max(left_hills, right_hills)) - current_hill. This is because water only traps if both left and right are > current hill AND gets trapped the amount that the minimum of maximums of both sides.

Implementation
Instead of using prefix and suffix max, we can use two pointers to greedily iterate from maximum left and maximum right. This is because we really just need to find the maximum left and maximum right for every iteration.

Time Complexity
\mathcal{O}(n)O(n) We go through the array at most twice. Each with n elements.

Space Complexity
\mathcal{O}(1)O(1) We dont use any data structure, just two pointers

import java.util.*;

class Solution {
    public int solve(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;

        int l = 0;
        int r = n - 1;

        int left_max = nums[l];
        int right_max = nums[r];
        int res = 0;

        while (l < r) {
            left_max = Math.max(left_max, nums[l]);
            right_max = Math.max(right_max, nums[r]);
            if (nums[l] > nums[r]) {
                res += Math.max(0, Math.min(left_max, right_max) - nums[r]);
                r--;
            } else {
                res += Math.max(0, Math.min(left_max, right_max) - nums[l]);
                l++;
            }
        }
        return res;
    }
}



