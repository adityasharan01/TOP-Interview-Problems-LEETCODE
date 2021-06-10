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
We can hold 3 units in the first index, 2 in the second, and 3 in the fourth index (we cannot hold 5 since it would run off to the left), so we can catch 8 units of water
Solve the subproblem where water can only flow to the left.
  
Intuition
Faster than 100%
At any position, the water that can be stored is determined by the maximum heights of the hill encountered thus far on the left and the right, just before and after the current position. Subtract the height of the present hill from the minimum of both the mentioned hills on the left and the right. Note that water can only be stored if the height of the current hill is smaller than the other two mentioned hills. If so, add it to the total water that can be possibly caught.

Implementation
No water can be stored at the first and the last position. We assume that there are pits (height - 0) before the start and the end of the hills. So, if the length of the list is less than 3, there exists no possibility of any water getting caught.
The left array stores the height of the highest encountered hill from the start of the list to any given position. Similarly, we maintain right as we go calculating the total water that can be possibly caught.
If the current hill height is less than the minimum of the highest on the left and the right, we add the difference to the total water that can be caught.

Time Complexity
\mathcal{O}(n)O(n) --> We iterate through the given list twice. Hence, a linear time complexity.

Space Complexity
\mathcal{O}(n)O(n) --> We use a prefix array of size n to store the height of the highest hill encountered so far up to a certain position.

class Solution {
    public int solve(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        int[] left = new int[n];
        left[0] = nums[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(nums[i], left[i - 1]);
        }
        int water = 0, right = nums[n - 1];
        for (int i = n - 2; i > 0; i--) {
            int side = Math.min(left[i - 1], right);
            if (side > nums[i]) {
                water += side - nums[i];
            }
            right = Math.max(nums[i], right);
        }
        return water;
    }
}
