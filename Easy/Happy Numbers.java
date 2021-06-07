Given an integer n, we apply this transformation until it becomes a 1: take each of the digits in n, square it, and then take their sum.

Return whether n will end up in 1 after the transformations.

Example 1
Input
n = 7
Output
true
Explanation
This is a happy number since we get this sequence [49, 97, 130, 10, 1]

7 ** 2 = 49
4 ** 2 + 9 ** 2 = 97
9 ** 2 + 7 ** 2 = 130
1 ** 2 + 3 ** 2 + 0 ** 2 = 10
1 ** 2 + 0 ** 2 = 1
Example 2
Input
n = 11
Output
false
Explanation
This is not a happy number since it ends up in a cycle: [2, 4, 16, 37, 58, 89, 145, 42, 20, 4]

2 ** 2 = 4
4 ** 2 = 16
1 ** 2 + 6 ** 2 = 37
3 ** 2 + 7 ** 2 = 58
5 ** 2 + 8 ** 2 = 89
8 ** 2 + 9 ** 2 = 145
1 ** 2 + 4 ** 2 + 5 ** 2 = 42
4 ** 2 + 2 ** 2 = 20
2 ** 2 + 0 ** 2 = 4
  
 Intuition
If iterating the second time the sum is only one digit and not 1 then return false otherwise true.

import java.util.*;

class Solution {
    public boolean solve(int n) {
        do {
            int b = n;
            int sum = 0;
            while (b > 0) {
                sum += (b % 10) * (b % 10);
                b /= 10;
            }
            n = sum;
        } while (n / 10 != 0);
        if (n != 1)
            return false;
        return true;
    }
}
