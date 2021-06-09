Find the Largest Number in a Rotated List
You are given a list of unique integers nums that is sorted in ascending order and is rotated at some pivot point. Find the maximum number in the rotated list.

Can you solve it in \mathcal{O}(\log{}n)O(logn)?

Constraints

n ≤ 100,000 where n is the length of nums.
Example 1
Input
arr = [6, 7, 8, 1, 4]
Output
8
Explanation
The original sorted array of [1, 4, 6, 7, 8] was rotated at index 2 and results in the input array [6, 7, 8, 1, 4,]. And the largest number is 8.

Example 2
Input
arr = [1, 2, 3]
Output
3
Example 3
Input
arr = [1]
Output
1
Example 4
Input
arr = [10, 1, 2, 3, 4, 7]
Output
10


Hint1:Binary Search 
Intuition
Find pivot index , where pivot index represent as min value in array.

Implementation
Search for pivot index which is min value in array through binary search
if pivot index is 0 it means result is present at last index else it is present at pivotIndex-1
Time Complexity
Time Complexity - \mathcal{O}(\log(n))O(log(n))

Space Complexity
Space Complexity- \mathcal{O}(1)O(1)

import java.util.*;

class Solution {
    public int solve(int[] arr) {
        int n = arr.length;
        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] > arr[high]) {
                low = mid + 1;
            } else
                high = mid;
        }
        if (low == 0)
            return arr[n - 1];
        return arr[low - 1];
    }
}
