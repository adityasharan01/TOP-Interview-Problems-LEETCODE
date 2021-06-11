Implement regular expression matching with the following special characters:

. (period) which matches any single character
* (asterisk) which matches zero or more of the preceding element
That is, implement a function that takes in a valid regular expression pattern and a string s and returns whether or not the string matches the regular expression.

Note: The input pattern is guaranteed not to have consecutive asterisks.

Constraints

n ≤ 100 where n is the length of pattern
m ≤ 1,000 where m is the length of s
Example 1
Input
pattern = "ra."
s = "ray"
Output
true
Explanation
We have ra and then a single character

Example 2
Input
pattern = "a"
s = "aa"
Output
false
Example 3
Input
pattern = "a*"
s = "aa"
Output
true
Explanation
We have 0 or more as.

Example 4
Input
pattern = ".*"
s = "abc"
Output
true
Explanation
We have 0 or more of any character

////////////////Shortcut
Intuition
This method is surely not the best but this method also exist
Java has a method "matches" in the string which are responsible for finding such a pattern.

Implementation
if (p.charAt(0) == '*') {
            return false;
}
We don't want the first character to be * because in that way 8 cant be evaluated to anything which will give a -1 index error

Time Complexity
\mathcal{O}(n*m)O(n∗m)(where n is length of string and m is length of pattern)

Space Complexity
\mathcal{O}(1)O(1)(There is no space that is being required in this algorithm)

import java.util.*;

class Solution {
    public boolean solve(String p, String s) {
        if (p.charAt(0) == '*') {
            return false;
        }
        return s.matches(p);
    }
}
////////////////////////////////////////////GOOD SOLUTION
//////// IMPLEMENT THIS IN JAVA 
Intuition
Bottom Up implementation. Other editorials have already done a good job explaining the logic for the Top-Down Method using recursion.

Implementation
Let ,
m = length of pattern
n = length of string
dp[i][j] = True if s[i:n] matches pattern[j:m]

Base Case:
dp[n][m] = True, since empty pattern always matches an empty string
dp[k][m] = False for all 0 <= k < n, Empty pattern never matches a non-empty string
dp[n][k] for all 0<= k <= m is set keeping in mind that an empty string can match patterns: a*, a*b*, a*b*c* ...

Time Complexity
\mathcal{O}(m*n)O(m∗n) where m is length of pattern and n is length of string.

Space Complexity
\mathcal{O}(m*n)O(m∗n) where m is length of pattern and n is length of string.

class Solution:
    def solve(self, pattern, s):
        m = len(pattern)
        n = len(s)
        dp = [[False for _ in range(m + 1)] for _ in range(n + 1)]
        dp[n][m] = True
        for i in range(m - 1, -1, -1):
            if i + 1 < m and pattern[i + 1] == "*":
                dp[n][i] = dp[n][i + 2]
        for i in range(m - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                if i + 1 < m and pattern[i + 1] == "*":
                    dp[j][i] = dp[j][i + 2]
                    if s[j] == pattern[i] or pattern[i] == ".":
                        dp[j][i] = dp[j][i + 2] | dp[j + 1][i]
                else:
                    if pattern[i] == s[j] or pattern[i] == ".":
                        dp[j][i] = dp[j + 1][i + 1]
                # print(i, j, dp[j][i])
        return dp[0][0]
