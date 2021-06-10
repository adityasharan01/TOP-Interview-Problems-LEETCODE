String Multiplication
Given two strings a, and b, both representing an integer, multiply them and return it in the same string representation.

Can you implement the multiplication directly, instead of using eval() or built-in big integers?

Constraints`

n ≤ 200 where n is the length of a
m ≤ 200 where m is the length of b
Example 1
Input
a = "5"
b = "4"
Output
"20"
Example 2
Input
a = "2"
b = "-3"
Output
"-6"
//Similar to addition of two big numbers.

Intuition
We will try and implement the grade-school algorithm using a character array mapping the product of individual digits to a respective index.
There are quite a few insights that needs care to implement this problem

Two nums each having x and y nos. of digits would result in a product with len (x+y) digits.
We might end up having value more than 10 for a particular index, which needs to be adjusted accordingly (second for loop)
Handle leading zero(s)
Negative sign handling
Implementation
If one carefully takes care of the insights mentioned, there is no special details about the implementation that requires attention, except handling negative values.

Time Complexity
\mathcal{O}(n^2)O(n 
2
 ) O(M*N), where M and N are lengths of the input. If M = N, we have the worst case

Space Complexity
\mathcal{O}(n)O(n) Extra space is required of O(M+N) => O(2N) ~ O(N)

import java.util.*;

class Solution {
    public String solve(String a, String b) {
        // If either one of the input is negative
        boolean hasNegativeInput = (a.charAt(0) == '-') ^ (b.charAt(0) == '-');

        if (hasNegativeInput) {
            a = a.charAt(0) == '-' ? a.substring(1) : a;
            b = b.charAt(0) == '-' ? b.substring(1) : b;
        }
        int resultLen = a.length() + b.length();
        int[] result = new int[resultLen]; // Contain the final result

        char[] chA = a.toCharArray();
        char[] chB = b.toCharArray();
        // a= 99; b = 345
        for (int i = chA.length - 1; i >= 0; i--) {
            int multiplier = chA[i] - '0';
            for (int j = chB.length - 1; j >= 0; j--) {
                int multiplicand = chB[j] - '0';
                int product = multiplier * multiplicand;

                result[i + j + 1] += product % 10;
                result[i + j] += product / 10;
            }
        }

        // For other scenarios wherein the total
        for (int i = result.length - 1; i > 0; i--) {
            result[i - 1] += result[i] / 10;
            result[i] %= 10;
        }
        // Handle leading zeros;
        int start = result[0] == 0 ? 1 : 0;
        StringBuilder finalRes = new StringBuilder();

        for (int i = start; i < result.length; i++) {
            finalRes.append(result[i]);
        }
        if (hasNegativeInput)
            finalRes.insert(0, '-');
        return finalRes.toString();
    }
}
