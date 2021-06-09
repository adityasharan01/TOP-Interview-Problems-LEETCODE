Add Binary Numbers
Given two strings a and b that represent binary numbers, add them and return their sum, also as a string.

The input strings are guaranteed to be non-empty and contain only 1s and 0s.

Constraints

n ≤ 100,000 where n is the length of a
m ≤ 100,000 where m is the length of b
Example 1
Input
a = "1"
b = "1"
Output
"10"
Example 2
Input
a = "111"
b = "1"
Output
"1000"

Intuition
Simple no Nonsense. solution. Assume adding any decimal numbers, but instead of base 10, its base 2.

Implementation
while loop to check all numbers are processed and no carry is pending. Within the loop, sum=0 or value of i/j if it's in range. Then simple base calculations

Time Complexity
\mathcal{O}(n)O(n) whene n in the length of the longer string

Space Complexity
\mathcal{O}(1)O(1) We are not using any extra space

import java.util.*;

class Solution {
    public String solve(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        int sum = 0;
        while (i >= 0 || j >= 0 || sum == 1) {
            sum += i >= 0 ? a.charAt(i--) - '0' : 0;
            sum += j >= 0 ? b.charAt(j--) - '0' : 0;
            sb.append(sum % 2 + "");
            sum /= 2;
        }

        return sb.reverse().toString();
    }
}
