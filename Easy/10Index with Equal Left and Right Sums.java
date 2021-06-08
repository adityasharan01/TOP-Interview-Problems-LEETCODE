Given a list of integer nums, return the earliest index i 
such that the sum of the numbers left of i is equal to the sum of numbers right of i.
If there's no solution, return -1.

Sum of an empty list is defined to be 0.

Constraints

1 ≤ n ≤ 100,000 where n is the length of nums
Example 1
Input
nums = [2, 3, 4, 0, 5, 2, 2]
Output
3
Explanation
Sum of the numbers left of index 3 is 9 and sum of the numbers right of index 3 also 9.

Example 2
Input
nums = [1, -2, 2]
Output
0
Explanation
Sum of the numbers left of index 0 is 0 and sum of the numbers right of index 0 also 0
  
  Intuition
Take the whole sum of array and consider it as rigth sum and for each iteration negate the value and check for the result.

Implementation
For each element of the array minus the value from the right sum and add to the left sum and then check for equality.

Time Complexity
\mathcal{O}(n)O(n) - as only one iteration of the array.

Space Complexity
\mathcal{O}(1)O(1) - only few variables are used for any length of input.

import java.util.*;

class Solution {
    public int solve(int[] nums) {
        int sum = 0;
        for (int i : nums) sum += i;

        int leftSum = 0;
        int rightSum = sum - nums[0];
        if (leftSum == rightSum)
            return 0;
        for (int i = 1; i < nums.length; i++) {
            // System.out.println(leftSum);
            // System.out.println(rightSum);
            rightSum -= nums[i];
            leftSum += nums[i - 1];
            if (leftSum == rightSum)
                return i;
        }

        return -1;
    }
}

