Given a string s containing brackets ( and ), return the minimum number of brackets that can be inserted so that the brackets are balanced.

Constraints

n ≤ 100,000 where n is the length of s
Example 1
Input
s = ")))(("
Output
5
Explanation
We can insert ((( to the front and )) to the end

Intuition
Count open bracket if closing bracket is present at ith index then check if open is not zero, decrement 1 from it else we need to add one bracket here.

At the end if open is not zero it means we need to add # of open and count # of close bracket.

Implementation
Time Complexity
Time complexity is \mathcal{O}(n)O(n), we need to iterate the string

Space Complexity
\mathcal{O}(1)O(1)

import java.util.*;

class Solution {
    public int solve(String s) {
        int open = 0;
        int count = 0;

        for (char c : s.toCharArray()) {
            if (c == '(')
                open++;
            else {
                if (open == 0)
                    count++;
                else
                    open--;
            }
        }
        return count + open;
    }
}
