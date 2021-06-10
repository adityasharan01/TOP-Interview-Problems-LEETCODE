Given a string s and an integer k, repeatedly delete the earliest k consecutive duplicate characters.

Constraints

k, n ≤ 100,000 where n is the length of s.
Example 1
Input
s = "baaabbdddd"
k = 3
Output
"d"
Explanation
First we delete the "a"s to get "bbbdddd". Then we delete the "b"s to get "dddd". Then we delete three of the four "d"s to get "d".
  
Intuition
We will use a stack to solve this problem.
The stack will store a pair : [value,count]

Implementation
We go through our array, if the current element is equal to the last element in the stack, we increase the count by 1. Otherwise, we insert a new element
If the count reach to k, we will pop it
Time Complexity
\mathcal{O}(n)O(n) We use a single loop to go through our array

Space Complexity
\mathcal{O}(n)O(n) We allocate a stack for storing our information

import java.util.*;

class Solution {
    public String solve(String s, int k) {
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if (stack.size() == 0) {
                stack.push(new int[] {c, 1});
            } else {
                if (c == stack.peek()[0]) {
                    stack.peek()[1]++;
                } else {
                    stack.push(new int[] {c, 1});
                }
            }
            if (stack.peek()[1] == k)
                stack.pop();
        }

        StringBuilder str = new StringBuilder();

        while (stack.size() > 0) {
            int top[] = stack.pop();
            char c = (char) (top[0]);
            int t = top[1];
            for (int i = 0; i < t; i++) {
                str.append(c + "");
            }
        }

        return str.reverse().toString();
    }
}
