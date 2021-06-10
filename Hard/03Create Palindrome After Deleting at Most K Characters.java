Given a string s and an integer k return whether you can delete at most k characters such that after the deletion s is a palindrome.

Constraints

n ≤ 1,000 where n is the length of s
Example 1
Input
s = "rzacecarx"
k = 2
Output
true
Explanation
If we remove the second and last character of s then we'd get "racecar" which is a palindrome.

Example 2
Input
s = "abcd"
k = 2
Output
false
Explanation
No matter which character we delete s can't become a palindrome.
  
  Hint1:LCS IS THE KEY TO SUCCESS
  
  Intuition
Let dp[i][j]dp[i][j] be the minimum number of removals necessary to make the substring s[i..j]s[i..j] be a palindrome.

The base cases are when i=ji=j and i+1=ji+1=j. In the former case the value of dp[i][i] is 0 because any string of length 1 is a palindrome. In the latter case, the value is 1 if and only if s[i] != s[j], and 0 otherwise.

To calculate dp[i][j] there are two transitions.

If s[i]==s[j], then we let dp[i][j]=dp[i+1][j-1]. This is because the first and last character are equal, so they won't change whether s[i..j] is a palindrome or not.
If s[i] != s[j], then we must delete either s[i] or s[j]. If we don't, then we cannot create a palindrome. This will incur a removal cost of 1. Therefore, the transition is dp[i][j]=1 + min(dp[i+1][j],dp[i][j-1]).
At the end, dp[0][N-1] will store the number of removals necessary to make s a palindrome. To answer the problem, simply return the boolean (dp[0][N-1] <= k).

Implementation
A note for this problem. Since we have to process smaller ranges before larger ranges, the outer loop should iterate over "size" instead of over "i".

Time Complexity
\mathcal{O}(n^2)O(n 
2
 ) for the nested dp loop. Each dp transition however is just \mathcal{O}(1)O(1)

Space Complexity
\mathcal{O}(n^2)O(n 
2
 ) for storing the dp array as a 2d array.

import java.util.*;

class Solution {
    public boolean solve(String s, int k) {
        int N = s.length();
        int[][] dp = new int[N][N];
        for (int i = 0; i < N - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1))
                dp[i][i + 1] = 1;
        }
        for (int sz = 2; sz <= N; sz++) {
            for (int i = 0; i <= N - sz; i++) {
                int j = i + sz - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return (dp[0][N - 1] <= k);
    }
}
