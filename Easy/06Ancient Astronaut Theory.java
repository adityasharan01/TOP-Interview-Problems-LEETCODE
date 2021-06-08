You are given a string dictionary, representing a partial lexicographic ordering of ancient astronauts' dictionary. Given a string s, return whether it's a lexicographically sorted string according to this ancient astronaut dictionary.

Example 1
Input
dictionary = "acb"
s = "aaaa h ccc i bbb"
Output
true
Explanation
The only constraint is that a comes before c which comes before b .

Example 2
Input
dictionary = "acb"
s = "aaaacccbc"
Output
false
Explanation
This is false because of the last c, which comes after b.
  
  Intuition
Keep track of max index the upcoming ith character must be always greater than or equal to max else false has it does not follow the order

Implementation
=> if s.length == 0 return true

=> initially, max will the index of s.charAt(0) so that all the remaining character index in the dictionary must greater or equal to max.

=> If the previous character is equal to the current character skip that to reduce some time

=> If the current character is not found in the dictionary then pos will be negative if pos < 0 continue

Time Complexity
\mathcal{O}(n*d)O(n∗d)

Space Complexity
\mathcal{O}(1)O(1)

import java.util.*;

class Solution {
    public boolean solve(String d, String s) {
        if (s.length() == 0)
            return true;
        int max = d.indexOf(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1) || s.charAt(i) == ' ')
                continue;
            int pos = d.indexOf(s.charAt(i));
            if (pos < 0)
                continue;
            // System.out.println(pos+" "+max+" "+s.charAt(i)) ;
            if (max > pos)
                return false;
            max = Math.max(max, pos);
        }
        return true;
    }
}
