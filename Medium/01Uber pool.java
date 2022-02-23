You are given a two-dimensional integer list requested_trips containing [start_x, end_x, num_passengers], and an integer capacity.
    Each requested trip asks to pick up num_passengers passenger at start_x and drop them off at end_x. You also have a car with the given capacity, 
and start at x = 0.

Given that you'd like to pick up every passenger and can only move right, return whether you can pick up and drop off everyone.

Constraints

n ≤ 100,000 where n is the length of requested_trips
Example 1
Input
trips = [
    [1, 30, 2],
    [3, 5, 3],
    [5, 9, 3]
]
capacity = 6
Output
true

Hint1:Think of intervals and sorting.
Hint 2: Priority queue
Hint 3: Sweep Line

Intuition
The idea is to use line sweep where we place each interval in a line a prune them

Implementation
For each interval's create two individual dicts (start and end)
(start, capacity, open_state(0))
(end, capacity, closed_state(1))

Now sort the intervals with their start time, Notice collision can happen with end and start occurring at same time , in that case we would give the end interval precedence first , hence the custom comparator has to address the case where two interval start time is same , then look at the state varible (0/1) and give priority to closed state

Now once the intervals are sorted by above rule, start pruning the intervals one by one : start with a count = 0
For each start interval encountered : increment the count by the contained interval
For each end interval encountered : decrement the count by the contained interval
if at any point count exceeds the capacity then return false

Time Complexity
\mathcal{O}(nlog n )O(nlogn) As we have to sort the intervals ; if using treeSet be carefull about equals

Space Complexity
\mathcal{O}(n)O(n) Extra space is used to store the interval start and end as disjoint entities
import java.util.*;

class Solution {
    public boolean solve(int[][] A, int cap) {
        Arrays.sort(A, (a, b) -> { return a[0] - b[0]; });

        int cur = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> { return a[0] - b[0]; });
        for (int i = 0; i < A.length; i++) {
            while (pq.size() > 0 && pq.peek()[0] <= A[i][0]) {
                int top[] = pq.poll();
                cur -= top[1];
            }

            pq.add(new int[] {A[i][1], A[i][2]});
            cur += A[i][2];
            if (cur > cap)
                return false;
        }
        return true;
    }
}



////////////////////////
import java.util.*;

class Solution {
    public boolean solve(int[][] trips, int capacity) {
        if (trips == null || trips.length == 0)
            return false;
        List<int[]> list = new ArrayList();
        for (int[] trip : trips) {
            list.add(new int[] {trip[0], trip[2], 0});
            list.add(new int[] {trip[1], trip[2], 1});
        }
        Collections.sort(list, (a, b) -> {
            if (a[0] == b[0]) {
                return b[2] - a[2];
            }
            return a[0] - b[0];
        });
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            int[] node = list.get(i);
            if (node[2] == 0)
                count += node[1];
            else
                count -= node[1];
            if (count > capacity)
                return false;
        }
        return true;
    }
}
