You are given a list of integers nums and an integer k. Consider an operation where you pick an element in nums and negate it. Given that you must make exactly k operations, return the maximum resulting sum that can be obtained.

Constraints

n ≤ 100,000 where n is the length of nums
k < 2 ** 31
Example 1
Input
nums = [1, 0, -5, -3]
k = 4
Output
9
Explanation
We can negate -5 and -3 once each and 0 twice to get [1, 0, 5, 3] and its sum is 9.
  
Intuition
1 . If there are negetive values will convert it to positive to make our sum greater.
2. If there are zero in the array and if we have any leftover k then change than k to zero cause zero wont hamper our sum .
3. If there is no zero and there are still leftover K then perform kth negation on the smallest number

Implementation
Sort the Array
Iterate the array and check if the number is negetive then make it positive
If the number is zero perform all the kth negation on it
If there is no zero then perform the kth negation on min element of the array and see if the k is even then it wont change the Sum but if it is odd it will change the solution.
4.return the sum
Example #1
[-2,-1,0,2,5] ,K = 5
iteration 1
[-2,-1,0,2,5] ,K =4
iteration 2
[2,1,0,2,5] ,K =3
iteration 3
[2,1,0,2,5] ,K =0 Performed all the K operation on 0

Time Complexity
\mathcal{O}(n)O(n) Since it take single iteration I m not sure can correct me

Space Complexity
\mathcal{O}(n)O(n) sorting complex I m not sure can correct me

import java.util.*;

class Solution {
    public int solve(int[] nums, int k) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] = Math.abs(nums[i]);
                k--;
            } else if (nums[i] == 0)
                k = 0;
            sum += nums[i];
            min = Math.min(nums[i], min);
        }
        if (k != 0) {
            if (k % 2 != 0) {
                sum -= min * 2;
            }
        }
        return sum;
    }
}
