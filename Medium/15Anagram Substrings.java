Given two strings s0 and s1, return the number of substrings where s1 contains any anagram of s0.

Constraints

n ≤ 100,000 where n is the length of s0
m ≤ 100,000 where m is the length of s1
Example 1
Input
s0 = "abc"
s1 = "bcabxabc"
Output
3
Explanation
The substrings "bca", "cab" and "abc" of s0 are permutations of "abc".
  Hint1:Sliding window, what could change between each successive window?
    
    
    Intuition
We want to compare each substring of s1. As we go through each char, we need to see if the substring is an anagram. Checking anagram can be done with one hash map only. The idea is to add count for one string, but subtract count for the other string. If at the end, the count is zero for all the characters we have seen, then we got an anagram match.

Implementation
Because we only want to keep track of substring of s1 which is the same length as s0, we need to revert the change we made for the very first char in a substring as we add a new char.

Example #1
s0 = "abc"
s1 = "bcabxabc"

Once we got the first a, at index 2, we will do a check, and we know we got an anagram. The hashmap looks like
a=0
b=0
c=0

for the next char, b, at index 3, we would revert the first b, which is at index 0, so the hash map looks like:
a=0
b=1
c=0
then we scan the b at index 3, which subtracts 1 from key "b". the table looks like:
a=0
b=0
c=0
We know we got another anagram

Time Complexity
time is \mathcal{O}(n)O(n) as we scan the s1 only once

Space Complexity
the hash map takes extra space. In English alphabet, it will have 26 slots. \mathcal{O}(1)O(1)

import java.util.*;

class Solution {
    public int solve(String s0, String s1) {
        return versionA(s0, s1);
    }

    int versionA(String s0, String s1) {
        HashMap<Character, Integer> map = init(s0);
        int last = s0.length() - 1;
        int sum = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (i > last) {
                // we are adding a new char and removing the oldest char
                // we will lose this char. revert the change we did.
                char first = s1.charAt(i - s0.length());
                map.put(first, map.getOrDefault(first, 0) + 1);
            }

            char ch = s1.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) - 1);

            if (i >= last) {
                if (isValid(map)) {
                    sum++;
                }
            }
        }

        return sum;
    }

    boolean isValid(HashMap<Character, Integer> map) {
        for (int n : map.values()) {
            if (n != 0) {
                return false;
            }
        }
        return true;
    }

    HashMap<Character, Integer> init(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        return map;
    }
}
