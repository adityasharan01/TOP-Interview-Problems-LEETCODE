Given a list of lowercase alphabet strings words and a lowercase alphabet string s, return whether or not the string can be broken down using the list of words. It's not required to use all of the words and you can reuse words.

Example 1
Input
words = ["quick", "brown", "the", "fox"]
s = "thequickbrownfox"
Output
true
Explanation
We can break the string down into "the quick brown fox".

Example 2
Input
words = ["hello", "world"]
s = "hellofoobarworld"
Output
false

Hint1:Think of it like knapsack problem

Intuition
DP + Backtracking

Given a string s, you can break into two words (with one word exisiting in the words array) and recursively calling for the other word.

You can store this second recursion in DP.

(DP contains 3 states -1 -> not calculated
1 -> calculated and true
0 -> calculated and false)

Implementation
when ever we find that a word is present in words array and recursive call also is present in words array, we return true. (typical backtracking strategy)

Only advancement here is that we store the recursive calls in DP for efficiency.

Time Complexity
\mathcal{O}(n + m)O(n+m) Since we are going char by char in main string, assume the length as n. Once we find that if a word is present in the set, we just call the recursive call on next part. Now calculating this again takes the linear amount of time for that part. Once we calculate, we reuse that value in future. So linear complexity

Space Complexity
\mathcal{O}(n)O(n) for constructing the DP array to store result of string

import java.util.*;

class Solution {
    public boolean solve(String[] words, String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        HashSet<String> set = new HashSet<>();
        set.addAll(Arrays.asList(words));
        return rec(set, s, 0, dp);
    }

    public boolean rec(HashSet<String> words, String s, int startIdx, int[] dp) {
        if (words.contains(s.substring(startIdx)) || s.length() == startIdx)
            return true;
        // System.out.println(startIdx);
        if (dp[startIdx] != -1)
            return dp[startIdx] == 1;
        int i = startIdx + 1;
        while (i < s.length()) {
            if (words.contains(s.substring(startIdx, i)) && rec(words, s, i, dp))
                return (dp[startIdx] = 1) == 1;
            i++;
        }
        dp[startIdx] = 0;
        return false;
    }
}
