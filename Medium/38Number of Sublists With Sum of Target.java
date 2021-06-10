Given a list of integers nums and an integer target, return the number of sublists whose sum is equal to target.

Constraints

n ≤ 100,000 where n is the length of nums
Example 1
Input
nums = [2, 0, 2]
target = 2
Output
4
Explanation
We have these sublists whose sum is 2: [2], [2, 0], [0, 2], [2]
/////////////////////////////////////////
Intuition
Using unordered_map to key store prefix sum
map<int,int> map to store (sum,count)

Implementation
map to store (sum,count)
prefix sums
before index i any prefix sum which has value -> (sum-k) can give a sum of k i.e it gives no. of subarrays before index i, i.e picking one subarray one by one before index i with index i

Time Complexity
Time complexity of the code is\mathcal{O}(n\log n)O(nlogn)

Space Complexity
Space complexity of the code is \mathcal{O}(n)O(n)

int solve(vector<int>& nums, int k) {
    int count = 0, sum = 0;
    unordered_map<int, int> mp;
    for (int i = 0; i < nums.size(); i++) {
        sum += nums[i];
        if (sum == k) count++;
        count += mp[sum - k];
        mp[sum]++;
    }
    return count;
}
//////////////////////////////////////////////////////////////////
Intuition
The simple prefix sum optimisation will lead to TLE , you also need hashmap/hashtable optimisation on top of it

Implementation
The idea is simple ...if the prefix sum for {0.....idx} is k and the prefix sum for range {0......idx2} is k + target
Then the sublist sum between {idx1, idx2} should be equal to target..

So we first construct the prefix sum array which will give us the prefix sum till any index.
Now we will prune the prefix sum array and for a given prefix sum say 'Sum"...if we find sum-target in the map then we will increment the count to the value stored in the map..(note map[x] will store the no of occurrences of a given prefix sum}

Time Complexity
\mathcal{O}(n)O(n) We are only doing a linear scan over the input array

Space Complexity
\mathcal{O}(n)O(n) Extra space is used for using the prefix sum array

import java.util.*;

class Solution {
    public int solve(int[] nums, int target) {
        int count = 0;
        if (nums == null || nums.length == 0)
            return 0;
        Map<Integer, Integer> map = new HashMap();
        int[] prefixsum = new int[nums.length + 1];
        for (int i = 1; i < prefixsum.length; i++) prefixsum[i] = prefixsum[i - 1] + nums[i - 1];

        for (int i = 0; i < prefixsum.length; i++) {
            int sum = prefixsum[i];
            int remain = sum - target;
            if (map.containsKey(remain))
                count += map.get(remain);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
