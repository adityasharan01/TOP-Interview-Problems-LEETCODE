Minimum Window Substring
Given two lowercase alphabet strings a and b, return the length of a minimum substring in a that contains all the characters of b.

If no such substring exists, return -1.

Constraints

0 ≤ n ≤ 100,000 where n is the length of a
1 ≤ m ≤ 100,000 where m is the length of b
Example 1
Input
a = "qthequickbrownfox"
b = "qown"
Output
10
Explanation
The shortest substring that contains "qown" is "quickbrown" which has length of 10.
  
 Hint1: Try to solve using sliding window concept with the help of hashing.
   
  Intuition
Sliding window. We use two arrays to document their occurrence, and check if the current substring's occurrence fulfill b's occurrence array. If yes, we move the left pointer to search for a minimum length substring that still satisfies the criteria.

Implementation
Use two arrays to store the occurence of each letter in a and b. We first record the occurrence of each character in b and calculate the difference between the two occurrence array.

Then we use two pointers and loop through a while updating the occurrence array for string a. When we have a satisfying occurrence array (diff is 0), then we can try to shrink the left pointer to find a minimized substring that still satisfies the constraint (each occurrence in a is bigger than the ones in b). When we break the constraint, we again move the right pointer to update the occurrence.

Time Complexity
\mathcal{O}(n+m)O(n+m) We loop through each string once. So the runtime is the sum of there length

Space Complexity
\mathcal{O}(1)O(1) We only have two constant arrays of size 26, which is \mathcal{O}(1)O(1)

import java.util.*;

class Solution {
    public int solve(String a, String b) {
        int ans = Integer.MAX_VALUE;
        char[] arr = a.toCharArray();

        int[] A = new int[26];
        int[] B = new int[26];

        // Record the occurence of each character in b
        for (char c : b.toCharArray()) {
            B[c - 'a']++;
        }

        // Record the total difference so far
        // We will update our difference to test if our current substring in a has all the
        // characters of b
        int diff = 0;
        for (int i : B) {
            diff += i > 0 ? 1 : 0;
        }

        // Set up two pointers
        int l = 0;
        int r = 0;
        while (r < a.length()) {
            char c = arr[r];
            A[c - 'a']++;

            // If one of our character meets the occurence in b
            if (A[c - 'a'] == B[c - 'a']) {
                // We decrease the diff by 1, and check if we still have any diff between array a
                // and b
                diff--;

                // While the differenec is 0
                while (diff == 0) {
                    // we update the answer to be our current substring length
                    ans = Math.min(ans, r - l + 1);

                    // We move the left pointer and decrease that letter's occurence
                    char c2 = arr[l++];
                    A[c2 - 'a']--;

                    // If one of the letters are less than b's occurence
                    if (A[c2 - 'a'] < B[c2 - 'a']) {
                        // increase diff by 1
                        diff++;
                    }
                }
            }
            r++;
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
