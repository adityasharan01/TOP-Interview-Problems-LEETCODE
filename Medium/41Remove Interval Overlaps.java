Remove Interval Overlaps
Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

The intervals are not necessarily sorted in any order.

Constraints

0 ≤ n ≤ 100,000 where n is the length of intervals
Example 1
Input
intervals = [
    [7, 9],
    [2, 4],
    [5, 8]
]
Output
1
Explanation
We can remove the interval [5, 8] to make the other intervals non-overlapping.

Example 2
Input
intervals = [
    [1, 5],
    [1, 5],
    [1, 5]
]
Output
2
Explanation
Since all three intervals overlap with each other, we need to remove 2 of them.
  Hint2:After sorting..
if two intervals overlap, then obviously one needs to be removed. Which interval will you remove among the two?
  
  hint2:
You'll be aiming at removing the interval which ends later because this way helps us in lesser overlaps to further intervals.
  
  ///////////////////////WRITE JAVA CODE FOR THIS!!!!!!!!!!
 Intuition
Instead of trying to count the minimum number of intervals to delete so that the remaining intervals are non-overlapping, we'll instead try to keep the maximum number of intervals such that the intervals are non-overlapping, and then the answer is the size of the original list minus the number of intervals we kept.

The greedy strategy we will follow is that we will always try to select the interval with the earliest ending point. The intuition for why this works is that if if we select the interval with the earliest ending point, this gives us maximum flexibility to pick later intervals - we can always replace a different candidate interval with the one that has the earliest ending point, and if we were to prove this formally, we would use this exchange argument to show that this solution can never fall behind the optimal solution.

Implementation
We define a comparator sortbyend which sorts by the ending point of each interval. We then sort the intervals by the ending point, and then scan over them, keeping track of the ending point of the last interval we selected. If we can select an interval, then we update the ending point (lastend) and decrement the answer to account for an interval that we are keeping.

Time Complexity
Because we sort the intervals, the time complexity is \mathcal{O}(N \log N)O(NlogN).

Space Complexity
We can sort the vector in place, so we use \mathcal{O}(1)O(1) space.

bool sortbyend(vector<int>& a, vector<int>& b) {
    return a.back() < b.back();
}
int solve(vector<vector<int>>& intervals) {
    if (intervals.size() <= 1) return 0;
    int ans = intervals.size();
    sort(intervals.begin(), intervals.end(), sortbyend);
    int lastend = intervals[0][0];
    for (auto interval : intervals) {
        if (interval[0] >= lastend) {
            ans--;
            lastend = interval[1];
        }
    }
    return ans;
}

///////////////////////
Intuition
Lets first sort the intervals by their start values. Lets iterate over the intervals, if we find that the interval's start time is greater than the greatest end time in our optimal list, then we add it. Else we add this and remove the interval having greatest end time from our optimal list. This choice is greedy and quite reasonable (we always want to keep our endtime as low as possible). For the optimal list, we can use a datastructure like PriorityQueue which is a maxheap (since we want to get the maximum endtime interval from it).

TC -> O(nlogn)
SC -> O(n)

import java.util.*;

class Solution {
    public int solve(int[][] a) {
        int n = a.length;

        Arrays.sort(a, (p, q) -> (p[0] - q[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>((p, q) -> (q - p));

        for (int i = 0; i < n; i++) {
            if (pq.size() == 0 || pq.peek() <= a[i][0]) {
                pq.add(a[i][1]);
            } else {
                pq.add(a[i][1]);
                pq.poll();
            }
        }
        return n - pq.size();
    }
}
