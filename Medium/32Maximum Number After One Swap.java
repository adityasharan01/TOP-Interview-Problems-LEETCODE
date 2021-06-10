Given an integer n, you can swap any two digits at most once. Return the maximum value of the resulting number.

Constraints

0 ≤ n < 2 ** 31
Example 1
Input
n = 1929
Output
9921
Explanation
We swap the first and the last digits

Hint1:Sort the digits in descending???
  
Intuition
It is important to note that the the highest number we can make will always be exchanging a number from the far left with a greater number left of it. Therefore, we can greedily find the first number that has a larger value to the right of it
Complexities
Time: \mathcal{O}(n^2)O(n 
2
 ) maximum
Space: \mathcal{O}(n)O(n)

import java.util.*;

class Solution {
    public int solve(int n) {
        char[] ch = String.valueOf(n).toCharArray();
        for (int i = 0; i < ch.length - 1; i++) {
            int max = -1, ind = -1;
            for (int j = i + 1; j < ch.length; j++) {
                if (ch[j] - '0' >= max) {
                    max = ch[j] - '0';
                    ind = j;
                }
            }
            if (max > ch[i] - '0') {
                char temp = ch[i];
                ch[i] = ch[ind];
                ch[ind] = temp;
                return Integer.parseInt(new String(ch));
            }
        }
        return n;
    }
}
