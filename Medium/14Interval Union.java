Interval Union
Given a two-dimensional integer list intervals representing unsorted inclusive intervals, return their union in sorted order.

Constraints

n ≤ 100,000 where n is the length of intervals
Example 1
Input
intervals = [
    [0, 5],
    [4, 6]
]
Output
[
    [0, 6]
]
Example 2
Input
intervals = [
    [1, 2],
    [3, 4]
]
Output
[
    [1, 2],
    [3, 4]
]
Example 3
Input
intervals = [
    [5, 6],
    [1, 2]
]
Output
[
    [1, 2],
    [5, 6]
]
Line Sweep or stack

Intuition
1st Approach could be the brute force approach - O(n^2 )
where we will compare each interval with every other interval present in the array.
2nd approach would be first sort the array then traverse the array and analyze each and every interval by getting the final resulting array elements in stack
Time Complexity - O(nlogn) + O(n) -> O(nlogn)
Space Complexity - O(n)

Implementation
Sorting intervals 2d array by 1st column(start) through lambda expression in java.
Declaring stack to hold list type of data, where each list will contain start and end of the interval.

Push the first interval in stack.
Then, for each subsequent interval check 2 conditions :-

whether that intervals start value is less than that of element's end value which is present at top of the stack &&
( then checking the end value if end value of the given interval is less than end value of the interval present at top of stack, then there is no need to push this interval in stack as it is an overlapping interval)
otherwise push it in stack.
If given intervals start value > end value of top of the stack, then it is a non - overlapping interval hence push this new interval in stack.
After iterating all array elements, create a resulting array of stack size and pop all elements into this array and return it .

Time Complexity
Time required for sorting the unsorted given array\mathcal{O}(n)(\log n )O(n)(logn)

Space Complexity
\mathcal{O}(n)O(n) As I have used Stack to store intervals.

import java.util.*;

class Solution {
    public int[][] solve(int[][] intervals) {
        Stack<List<Integer>> st = new Stack<>();
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        st.push(Arrays.asList(intervals[0][0], intervals[0][1]));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= st.peek().get(1) && intervals[i][1] > st.peek().get(1)) {
                int t = st.peek().get(0);
                st.pop();
                st.push(Arrays.asList(t, intervals[i][1]));
            } else if (intervals[i][0] > st.peek().get(1))
                st.push(Arrays.asList(intervals[i][0], intervals[i][1]));
        }
        int[][] res = new int[st.size()][2];
        int l = st.size() - 1;
        while (!st.isEmpty()) {
            res[l][0] = st.peek().get(0);
            res[l][1] = st.peek().get(1);
            st.pop();
            l--;
        }
        return res;
    }
}
