Given a string of parentheses s, return the minimum number of parentheses to be removed to make the string balanced.

Constraints

n ≤ 100,000 where n is the length of s
Example 1
Input
s = "()())()"
Output
1
Explanation
We can remove the ")" at index 4 to make it balanced.

Example 2
Input
s = ")("
Output
2
Explanation
We must remove all the parentheses.
  Hint1:Use a Stack
  
 Intuition
use stack
-> when ch is "(" push 1 into the stack
-> else pop when stack is not empty if it is empty increase the result
-> finally return result+stack.size

Time Complexity : O(n)

import java.util.*;

class Solution {
    int helper(String s) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (char ch : s.toCharArray())
            if (ch == '(')
                stack.push(1);
            else {
                if (!stack.isEmpty())
                    stack.pop();
                else
                    result++;
            }

        return result + stack.size();
    }
    public int solve(String s) {
        return helper(s);
    }
}
