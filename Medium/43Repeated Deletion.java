Given a string s, repeatedly delete the earliest consecutive duplicate characters.

Constraints

n ≤ 100,000 where n is the length of s.
Example 1
Input
s = "abbbaac"
Output
"c"
Explanation
"bbb" are the earliest consecutive duplicate characters which gets deleted. So we have "aaac".
"aaa" then gets deleted to end up with "c".
  
  Intuition
No Nonsense Stack approach.
Use a stack to keep track of dups across reductions. if stack tos char matches current, skip all similar consecutive chars, else add it to stack
In the end, recreate a string from stack

Implementation
Time Complexity
\mathcal{O}(n)O(n) where n is the length of the string

Space Complexity
\mathcal{O}(n)O(n) where n is the length of the string

import java.util.*;

class Solution {
    public String solve(String s) {
        if (s == null || s.length() == 0)
            return s;
        Stack<Character> st = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            if (!st.isEmpty() && st.peek() == s.charAt(i)) {
                int j = i;
                while (j < s.length() && st.peek() == s.charAt(j)) j++;
                st.pop();
                i = j;
            } else
                st.push(s.charAt(i++));
        }
        StringBuilder sb = new StringBuilder();
        for (char c : st) sb.append(c + "");
        return sb.toString();
    }
}
