A rotation group for a string contains all of its unique rotations. For example, "123" can be rotated to "231" and "312" and they are all in the same rotation group.

Given a list of strings words, group each word by their rotation group, and return the total number of groups.

Constraints

n ≤ 200 where n is the length of words.
Example 1
Input
words = ["abc", "xy", "yx", "z", "bca"]
Output
3
Explanation
There are three rotation groups:

["abc", "bca"]
["xy", "yx"]
["z"]
Hint1:
Try using property of string rotation property "abc"+"abc" contains "bca" at index greater than -1.

  
  Intuition
For every word, we can generate all its rotation permutation and check if this group is already known.

Implementation
Traverse in the words array.
For every words[i],
Check if it is not already in hash set, add it in hash set along with all its rotation and increase the counter by 1.

Now if we encounter its rotation again, it would already be in hash set.
The count would be number of groups

Time Complexity
\mathcal{O}(nm)O(nm) where n is length of words array and m is length of each word (words[i]). There is a for loop of (0,n) and inside it we have another for loop for every word (0,m)

Space Complexity
\mathcal{O}(nm)O(nm) where n is length of words array and m is length of each word (words[i])

import java.util.*;

class Solution {
    public int solve(String[] words) {
        int count = 0;
        HashSet<String> hs = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            if (!hs.contains(s)) {
                count++;
                hs.add(s);
                for (int j = 0; j < s.length(); j++) {
                    String rotate = s.substring(j) + s.substring(0, j);
                    hs.add(rotate);
                }
            }
        }
        return count;
    }
}

