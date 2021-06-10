Given a list of positive integers nums and an integer k, return the number of sublists whose sum is divisible by k.

Constraints

n ≤ 100,000 where n is the length of nums
Example 1
Input
nums = [5, 3, 2, 10]
k = 5
Output
6
Explanation
We have the following sublists whose sum is divisible by 5

[5]
[3, 2]
[10]
[5, 3, 2]
[3, 2, 10]
[5, 3, 2, 10]

hint1:https://binarysearch.com/problems/K-Divisible-Sublist
Try this first and use the same technique to solve this problem.
Spoiler Alert : Prefix Sum

Intuition
Divisible by k is same as mod k = 0
If every index i, we know how many sublists that end at index i sum to 0 mod k, then we can just add it all such for all i.
We will save every prefix sum in a hashmap
For some index i, let the current running prefix sum be x. Then we can figure out how many sublists that end at index i sum to 0 mod k by counting how many prefix sum of x we have seen before this
Implementation
I use a hashmap of prefix sums, and then do rolling sums

Time Complexity
\mathcal{O}(n)O(n) I visit every element exactly once and there are n of them

Space Complexity
\mathcal{O}(n)O(n) I store at most n elements in the hashmap

import java.util.*;

class Solution {
    public int solve(int[] nums, int k) {
        HashMap<Integer, Integer> hm = new HashMap();
        hm.put(0, 1);
        int sum = 0, res = 0;
        for (int num : nums) {
            sum = (sum + num) % k;
            res += hm.getOrDefault(sum, 0);
            hm.put(sum, hm.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}

