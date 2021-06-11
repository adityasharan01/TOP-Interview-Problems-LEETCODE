Given an integer k and a list of integers nums, return the length of the longest sublist that contains at most k distinct integers.

Constraints

0 ≤ k ≤ n ≤ 100,000 where n is the length of nums
Example 1
Input
k = 2
nums = [0, 1, 2, 1, 0]
Output
3
Explanation
The longest substring with 2 distinct integers is [1,2,1], which has length of 3.

Example 2
Input
k = 1
nums = [0, 0, 0, 0, 0]
Output
5
Example 3
Input
k = 1
nums = [0, 1, 2, 3, 4]
Output
1
Hint1:Try considering a window with k distinct integers. When should the start of the window move ahead?
  //////////////////////////////////////////////////////////
  Intuition
Sliding Window Approach: Expanding the window will never decrease the number of unique elements. Contracting the window will never increase the number of unique elements. The largest subarray will always have the most number of unique elements less than k.

Implementation
Expand the window from the right. Contract it from the left. Keep track of the number of unique elements in the window and their frequency with a hashmap.

Example #1
nums = [1], k = 1
in the first iteration,
ct.size() == 1, so we don't enter the while loop.
answer = max(ans = 0, i - j + 1 = 1) = 1

Time Complexity
\mathcal{O}(n)O(n) Inner while loop executes at most n times. Outer loop executes n times.

Space Complexity
\mathcal{O}(n)O(n) Can have at most n unique elements with a window size of n.

int solve(int k, vector<int>& nums) {
    unordered_map<int, int> ct;
    int j = 0;
    int ans = 0;
    for (int i = 0; i < nums.size(); ++i) {
        ++ct[nums[i]];
        while (ct.size() > k) {
            --ct[nums[j]];
            if (ct[nums[j]] == 0) {
                ct.erase(nums[j]);
            }
            ++j;
        }
        ans = max(ans, i - j + 1);
    }
    return ans;
}

  ////////////////////////////////////////////////////////
  Intuition
Traverse the array elements, anchoring a slow pointer at the beginning. If you have reached an index, where the distinct K invariant is violated, slide your current window until you have re-established the invariant.

Implementation
For each Index in the array, we should be able to get the size of a datastore in O(1) time as well as reading counts (when invariant is violated) of an element in O(1) time. Hence, we use a HashMap.
Remove elements from the HashMap during the slide operation when an element's count is ZERO
This helps re-establish the said invariant (Distinct < K)

Also, update maxSubListLength for each index.

Time Complexity
\mathcal{O}(n)O(n) For each read operation we are sure to Lookup / Get Size in O(1) time, so average time spend per index is O(N)

Space Complexity
\mathcal{O}(1)O(1) Using a hashmap of at-most 3 integers.

import java.util.*;

class Solution {
    public int solve(int k, int[] nums) {
        if (nums == null)
            return 0;

        if (k == 0)
            return 0;

        if (nums.length <= 1)
            return nums.length;
        // We will have to use HashMap to keep track
        // of the occurence of an element in a given window
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        frequencyMap.put(nums[0], 1);
        int maxSubListLength = 0;

        for (int anchor = 0, explorer = 1; explorer < nums.length; explorer++) {
            frequencyMap.put(nums[explorer], frequencyMap.getOrDefault(nums[explorer], 0) + 1);

            while (frequencyMap.size() > k) {
                // We re-adjust the anchor pointer
                // to maintain ONLY K elements in the map at a given time
                frequencyMap.put(nums[anchor], frequencyMap.get(nums[anchor]) - 1);
                if (frequencyMap.get(nums[anchor]) == 0) {
                    frequencyMap.remove(nums[anchor]);
                }
                ++anchor;
            }
            maxSubListLength = Math.max(maxSubListLength, explorer - anchor + 1);
        }
        return maxSubListLength;
    }
}
