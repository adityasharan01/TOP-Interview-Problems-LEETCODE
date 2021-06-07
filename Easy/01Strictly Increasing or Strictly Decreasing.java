Given a list of integers nums, return whether the list is strictly increasing or strictly decreasing.

Constraints

n ≤ 100,000 where n is the length of nums
Example 1
Input
nums = [1, 2, 3, 4, 5]
Output
true
Explanation
This is strictly increasing.

Example 2
Input
nums = [1, 2, 3, 4, 5, 5]
Output
false
Explanation
Since there's two duplicate 5 this is not strictly increasing.

Example 3
Input
nums = [5, 4, 3, 2, 1]
Output
true
Explanation
This is strictly decreasing.
  //Method 1:
  Intuition
What you first need to do is check if the array is increasing or decreasing. All you need is the first 2 elements of the array. After you know whether the first is bigger than the second, you can iterate over the array to check that it continues increasing and vice versa. If the array only has 1 element, then we cannot test it against any other elements so we just return true.

Implementation
It returns true when the array is strictly increasing or decreasing, and it also returns false and exits the loop when the array does not comply with previous elements.

Time Complexity
\mathcal{O}(n)O(n) - We itterate over each of the elements of the array.

Space Complexity
\mathcal{O}(n)O(n) - There are n elements stored for each element in the array.

import java.util.*;

class Solution {
    public boolean solve(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        if (nums[1] > nums[0]) {
            for (int i = 0; i < (nums.length - 1); i++) {
                if (nums[i] >= nums[i + 1]) {
                    return false;
                }
            }
            return true;
        } else {
            for (int i = 0; i < (nums.length - 1); i++) {
                if (nums[i] <= nums[i + 1]) {
                    return false;
                }
            }
            return true;
        }
    }
}
//Method 2//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  Intuition
Determine the invariant by checking first two numbers. If the invariant is violated anywhere in between the traversal, return false. Else, return true

Implementation
Simple invariant based solution that compares first two element to test the character of the sequence.
If the character sequence is violated in the middle, we return false. Else we return true.

Time Complexity
\mathcal{O}(n)O(n) One scan through the array with constant time operation per index

Space Complexity
\mathcal{O}(1)O(1) One invariant flag that is checked for every index

import java.util.*;

class Solution {
    public boolean solve(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;
        if (nums.length == 1)
            return true;
        int diff = nums[1] - nums[0];
        if (diff == 0)
            return false;
        boolean isIncreasing = diff > 0; // Set the invariant
        for (int i = 2; i < nums.length; i++) {
            if (isIncreasing && nums[i] <= nums[i - 1])
                return false;
            if (!isIncreasing && nums[i] >= nums[i - 1])
                return false;
        }
        return true;
    }
}
  
  
