Given two strings a, and b, both representing an integer, add them and return it in the same string
representation.

This should be implemented directly, instead of using eval or built-in big integers.

Constraints`

n ≤ 200 where n is the length of a
m ≤ 200 where m is the length of b
Example 1
Input
a = "12"
b = "23"
Output
"35"
Explanation
12 + 23 = 35
Hint1:

This question is similar to
"Sum of two large numbers"

Intuition
Basic math that we read at school is the strategy. Go one by one by defining sum and carry as the variables.

Finally check if carry > 0 and append based on that condition.

Implementation
Time Complexity
\mathcal{O}(n)O(n) since we traverse math.max(a,b)__________________

Space Complexity
\mathcal{O}(n)O(n)since we use a string builder_________________________________

import java.util.*;

class Solution {
    public String solve(String a, String b) {
        StringBuilder sbr = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
        while (i >= 0 && j >= 0) {
            int sum = a.charAt(i) - '0' + b.charAt(j) - '0' + carry;
            sbr.append(sum % 10);
            carry = sum / 10;
            i--;
            j--;
        }
        while (i >= 0) {
            int sum = a.charAt(i) - '0' + carry;
            sbr.append(sum % 10);
            carry = sum / 10;
            i--;
        }
        while (j >= 0) {
            int sum = b.charAt(j) - '0' + carry;
            sbr.append(sum % 10);
            carry = sum / 10;
            j--;
        }
        if (carry > 0)
            sbr.append(carry);
        sbr.reverse();
        return sbr.toString();
    }
}
