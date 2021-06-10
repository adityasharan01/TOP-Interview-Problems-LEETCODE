Given the mapping a = 1, b = 2, ... z = 26, as well as "*" which can be mapped anything from 1 to 9, and an encoded message message (as a string), count the number of ways it can be decoded.

Mod the result by 10 ** 9 + 7.

Constraints

n ≤ 100,000 where n is the length of message
Example 1
Input
message = "1*"
Output
18
Explanation
The "*" can represent anything from 1 to 9, so this can be decoded as:

["aa", "ab", "ac", "ad", "ae", "af", "ag", "ah", "ai"] - (1, 1), (1, 2), ..., (1, 9)`
["k", "l", "m", "n", "o", "p", "q", "r", "s"] (11), (12), ..., (19)
Example 2
Input
message = "22"
Output
2
Explanation
This can represent "bb" or "v"

Example 3
Input
message = "*00"
Output
0
Explanation
There's no valid decoding
  
  Intuition
This is a dynamic programming problem, where we let dp[i] store the number of possible ways to encode the substring up to character ii.

The base case is dp[0]=1. In general, we have two possible transitions: either map the last character to a possible letter (and look back to dp[i-1]) or map the last two characters to a possible letter (and look back to dp[i-2]).

Implementation
Implementation wise, the hardest bit of the problem is creating the DP cases.

In the first case, handled in the helper function one(c), if c=='*' then we have 9 possible letters (a through i), if c is a non-zero number we have 1 possible letter, otherwise we have 0 possible letters since 0 doesn't map to any letter.

In the second case, handled in the helper function two(a,b), the easiest solution is to just use a nested for loop to loop over the possible tens digit and the possible ones digit. The number formed, 10*i+j, will map to a letter if and only if 1 \le 10i+j \le 261≤10i+j≤26. If a=='0' (the tens digit is 0), we have an edge case and return 0 immediately.

These transitions complete the DP.

Time Complexity
\mathcal{O}(n)O(n) since we we have N dp states and each transition is constant time.

Space Complexity
\mathcal{O}(n)O(n) to store the DP array. This can be optimized to constant space, since we only need the last two dp elements to generate the next one.

import java.util.*;

class Solution {
    static final long MOD = 1000000007L;
    public int solve(String s) {
        int N = s.length();
        long[] dp = new long[N + 1];
        dp[0] = 1L;
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1] * one(s.charAt(i - 1));

            if (i > 1) {
                dp[i] += dp[i - 2] * two(s.charAt(i - 2), s.charAt(i - 1));
            }
            dp[i] %= MOD;
        }
        return (int) (dp[N]);
    }

    public int one(char c) {
        if (c == '*')
            return 9;
        else if (c == '0')
            return 0;
        else
            return 1;
    }

    public int two(char a, char b) {
        if (a == '0')
            return 0;

        int AL = 1;
        int AR = 2;
        if (a != '*') {
            AL = a - '0';
            AR = a - '0';
        }
        int BL = 1;
        int BR = 9;
        if (b != '*') {
            BL = b - '0';
            BR = b - '0';
        }

        int ans = 0;
        for (int i = AL; i <= AR; i++) {
            for (int j = BL; j <= BR; j++) {
                if (10 * i + j <= 26)
                    ans++;
            }
        }
        return ans;
    }
}

