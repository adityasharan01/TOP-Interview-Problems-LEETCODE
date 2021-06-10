Given a list of unique positive integers nums and a positive integer k, return the number of unique combinations that sum up to k. You may reuse numbers when creating combinations.

Constraints

n ≤ 25 where n is the length of nums
1 ≤ nums[i] ≤ 250 for all 0 ≤ i < n
1 ≤ k ≤ 500
Example 1
Input
nums = [1, 2, 3]
k = 2
Output
2
Explanation
We can make 2 with [1, 1] and [2].
  Intuition
Easy recursion Solution
Variable used are sum and pos. sum is used for knowing the sum of n recursion and pos is used for getting the position of the current element .
eg - nums=[1,2,3] ,k=2
stack 1 = (nums,( k=2), 0 , 0) -
stack 2 = (nums,( k=2), 1 , 0) -
stack 3 = (nums,( k=2), 2 , 0) -
stack 4 = (nums,( k=2), 3 , 0) - which cause an increment to the rtn variable
stack 5 = back to stack 3 (nums,( k=2), 4, 1) - which will cause it to break
so on and so forth .

import java.util.*;

class Solution {
    public int solve(int[] nums, int k) {
        help(nums, k, 0, 0);
        return rtn;
    }
    int rtn = 0;
    void help(int[] nums, int k, int sum, int pos) {
        if (sum == k)
            rtn++;
        else if (sum > k)
            return;
        else {
            for (int i = pos; i < nums.length; i++) {
                help(nums, k, sum + nums[i], i);
            }
        }
    }
}
//////////////////////////////////////////////////////////////////////////////////
  Intuition
We need to know total number of ways to get the sum equal to k using given number so we can say \mathcal{ways}ways += \mathcal{ways\;to\;reach\;last\;possible\;sum\;*1}waystoreachlastpossiblesum∗1 because to reach the current sum from last sum we have one way, We can use Dynamic programming to store and calculate the last possible sum we can reach. At each step we will check for all possible values of sum greater than the current element because if we use the current element the sum should be greater than current element otherwise it will be 0 because we can't use a number greater than current k to make that number.

P.S: \mathcal{sum == k}sum==k here

Implementation
Step 1: Create an array of size sum + 1 to store the values. That is we have to store the number of ways to make the \mathcal{ith\;sum}ithsum.
Step 2: Iterate over all values of nums and check if we can subtract \mathcal{nums[i]}nums[i] from 
current sum and reach a valid state, If yes we add the number of ways to reach that valid state into our answer.

Time Complexity
\mathcal{O}(n*k)O(n∗k): We are iterating over all values of \mathcal{n\; and\; k}nandk and in a nested loop.

Space Complexity
\mathcal{O}(k)O(k): We are using an array of size \mathcal{k + 1}k+1 to store the ways to reach that state.

class Solution:
    def solve(self, nums, k):
        dp = [0] * (k + 1)
        dp[0] = 1

        for i in range(0, len(nums)):

            for j in range(nums[i], k + 1):

                dp[j] += dp[j - nums[i]]

        return dp[k]
