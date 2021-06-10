Longest Sublist of 1s After K Sets
You are given a list of integers nums containing 1s and 0s and an integer k. Given that you can set at most k 0s to 1s, return the length of the longest sublist containing all 1s.

Constraints

n ≤ 100,000 where n is the length of nums
Example 1
Input
nums = [1, 1, 1, 0, 0, 1, 0]
k = 2
Output
6
Explanation
We can set the two middle 0s to 1s and then the list becomes [1, 1, 1, 1, 1, 1, 0].
  Hint1:Try finding the length of the longest sublist with at most k zeroes.
    Hint2:Maintain a sliding window of integers containing at most k 0s.
      ////////////////////////////////////////////
      Intuition
The question can be simply solved by

if k == 0 , then find the longest contiguos set of 1 (solved by prefix sum or simply maintaing a count var)
if k > 0 then you need to find longest sliding window with at max k zeroes
Implementation
For SW you can keep a variable countZero while you traverse through the window, and as soon as the newly inserted no is zero ,and the count has been already reached to k , then start evictions from the front untill the zero count is less than k
record each valid window length and challenge our global max sublist len var

Time Complexity
\mathcal{O}(n)O(n) Linear scan over the input array (sliding window)

Space Complexity
\mathcal{O}(n)O(n) used deque to maintain SW , could have also used 2 pointers

import java.util.*;

class Solution {
    public int solve(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;
        int count = 0;
        int max = 0;
        for (int num : nums) {
            if (num > 0)
                ++count;
            else
                count = 0;
            max = Math.max(max, count);
        }
        if (k == 0)
            return max;

        int countZero = 0;
        int maxLen = 0;
        Deque<Integer> deque = new LinkedList();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (!deque.isEmpty() && num == 0 && countZero >= k) {
                int val = deque.pollFirst();
                if (val == 0)
                    --countZero;
            }
            deque.addLast(num);
            if (num == 0)
                countZero++;
            maxLen = Math.max(maxLen, deque.size());
        }
        return maxLen;
    }
}
  //////////////////////////////    
      Intuition
Solved using sliding window algorithm. Ap is the start of the window while i is the end, 
and zeros is the amount of zeros in the current window. If at any point the amount of zeros exceeds k, 
move ap past the next zero and continue. If the amount of zeros is less than k, 
you can check too see if the window is larger than max.

import java.util.*;

class Solution {
    public int solve(int[] nums, int k) {
        int zeros = 0, ap = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeros++;
            }
            if (zeros <= k) {
                max = Math.max(max, i - ap + 1);
            } else {
                while (zeros > k && ap < nums.length) {
                    if (nums[ap++] == 0)
                        zeros--;
                }
            }
        }
        return max;
    }
}
////////////////////////////////////////////////////
