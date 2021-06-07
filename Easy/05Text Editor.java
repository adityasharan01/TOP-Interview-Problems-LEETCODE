Given a string s representing characters typed into an editor, with "<-" representing a backspace, return the current state of the editor.

Constraints

n ≤ 100,000 where n is the length of s
Example 1
Input
s = "abc<-z"
Output
"abz"
Explanation
The "c" got deleted by the backspace.

Example 2
Input
s = "<-x<-z<-"
Output
""
Explanation
All characters are deleted. Also note you can type backspace when the editor is empty as well.
  
Intuition
We iterate over the string and append the char of the string to the new string we are creating. If we are not at the last char of the string we look ahead to see if the current char plus the next char is equivalent to '<-'. If it is, we then check if there is anything to delete in the string we are building. If there is we delete the last char otherwise we do not, but either way we then increment the index i by 1. This is so we skip over the '-' of the '<-'.

Implementation
=> Construct variable to hold resulting string
=> Iterate over string
=> Check if we not at the last char && the current char is '<' and the next char is '-'
=> If it is we then check if our resulting string has anything to delete and then we increment index by 1
=> Otherwise we append char to our resulting string
=> Return resulting string

Time Complexity
\mathcal{O}(n)O(n) We are just iterating over the string where it contains n chars.

Space Complexity
\mathcal{O}(n)O(n) We are constructing a new string which takes ~n spaces for the n chars we iterate over.

import java.util.*;

class Solution {
    public String solve(String s) {
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < s.length(); i++) {
            if (i != s.length() - 1 && s.charAt(i) == '<' && s.charAt(i + 1) == '-') {
                if (sb.length() != 0)
                    sb.deleteCharAt(sb.length() - 1);
                i++;
            } else
                sb.append(s.charAt(i));
        }

        return sb.toString();
    }
}
