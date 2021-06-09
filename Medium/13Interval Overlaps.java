Interval Overlaps
You are given a list of closed intervals l0 and another list of intervals l1. Individually, each list is non-overlapping and are sorted in ascending order.

Return the overlap of the two intervals sorted in ascending order.

Constraints

n ≤ 100,000 where n is the length of l0
m ≤ 100,000 where m is the length of l1
Example 1
Input
l0 = [
    [1, 3],
    [5, 6],
    [7, 9]
]
l1 = [
    [1, 4],
    [5, 7]
]
Output
[
    [1, 3],
    [5, 6],
    [7, 7]
]
Example 2
Input
l0 = [
    [1, 3],
    [5, 6],
    [7, 9]
]
l1 = [
    [100, 200]
]
Output
[]


Intuition
The idea is to sort the intervals based on the start time and then use two pointer technique to compare the first interval from list1 and first interval from list2

Implementation
The interval non overlapping criteria is simple start of first > end of second or end of fist < start of second where we can increment i++ and j++ accordingly

Else they are overlapping in some way or another and the overlapped interval can be found as
max (starta, startb} and min(enda, endb}

Based on whose min is smaller or equal we can progress either one of the pointers or both

Time Complexity
\mathcal{O}(nog n )O(nogn) As we have sorted the arrays based on the start time

Space Complexity
\mathcal{O}(n)O(n) Extra space is used to store the overlapped intervals

import java.util.*;

class Solution {
    public int[][] solve(int[][] list1, int[][] list2) {
        Arrays.sort(list1, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(list2, Comparator.comparingInt(a -> a[0]));
        List<int[]> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < list1.length && j < list2.length) {
            int[] interval1 = list1[i];
            int[] interval2 = list2[j];
            if (interval2[0] > interval1[1]) {
                i++;
                continue;
            }
            if (interval2[1] < interval1[0]) {
                j++;
                continue;
            }

            result.add(new int[] {
                Math.max(interval1[0], interval2[0]), Math.min(interval1[1], interval2[1])});
            if (interval1[1] < interval2[1]) {
                i++;
            } else if (interval2[1] < interval1[1]) {
                j++;
            } else {
                i++;
                j++;
            }
        }
        int[][] ans = new int[result.size()][];
        int idx = 0;
        for (int[] arr : result) ans[idx++] = arr;
        return ans;
    }
}
