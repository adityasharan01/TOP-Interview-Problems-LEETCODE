Given a list of sorted unique integers nums and an integer k, return the kth (0-indexed) missing number from the first element of the list.

Constraints

n ≤ 100,000 where n is the length of nums.
Example 1
Input
nums = [5, 6, 8, 9]
k = 0
Output
7
Explanation
7 is the first missing number.

Example 2
Input
nums = [5, 6, 8, 9]
k = 3
Output
12
Explanation
The first four missing numbers are [7, 10, 11, 12]

//////////////////////////////////
I found it helpful to visualize an example with the missing numbers drawn:

# k = 3
# nums = [-1 _ _ 2 _ 4 _ _ _ _ 9]
#                      ^
# output: 5
Let B[i] = A[i] - A[0] - i. It turns out B[i] is the number of items skipped in A[..i].

For example, if the sequence [A[i] - A[0] for i=0...] is [0, 1, 3, 4, 7, 9], then B is [0, 0, 1, 1, 3, 4].

We can now finish with a binary search for the first i with B[i] > K. The answer is i + A[0] + K.

class Solution:
    def solve(self, A, K):
        lo, hi = 0, len(A)
        while lo < hi:
            mi = lo + hi >> 1
            if A[mi] - A[0] - mi <= K:
                lo = mi + 1
            else:
                hi = mi
        return lo + A[0] + K
