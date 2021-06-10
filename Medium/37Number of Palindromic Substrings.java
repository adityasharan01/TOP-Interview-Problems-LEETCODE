Number of Palindromic Substrings
Given a lowercase alphabet string s, return the number of palindromic substrings in s.

Constraints

1 ≤ n ≤ 1,000 where n is the length of s
Example 1
Input
s = "tacocat"
Output
10
Explanation
The palindromic substrings are:

"t"
"a"
"c"
"o"
"c"
"a"
"t"
"coc"
"acoca"
"tacocat"
  
  Hint1:If we know that the string "acoca" in "tacocat" is a palindrome, we can know that "tacocat" is a palindrome in constant time by checking the characters directly to the left and to the right.
   Hint2:Alternatively, consider each character as a midpoint of a palindrome for all odd-length palindromes. Consider also every two consecutive characters as the two midpoints for all even-length palindromes.
For either of these cases, how can we find out all palindromes with such midpoints?
     hint3:Two Pointers
     
     Intuition
We check each type of palindrome(odd length && even length) and whenever condition is fulfilled we increment count.

Implementation
Implementation is iterative and kind of brute force.

Time Complexity
\mathcal{O}(n^2)O(n 
2
 ) The time complexity is simple to understand.

Space Complexity
\mathcal{O}(1)O(1) Since there is no extra space used, Space complexity is constant.

int solve(string s) {
    int count = 0;
    for (int i = 0; i < s.size(); i++) {
        int j = i - 1, k = i;
        while (j >= 0 && k < s.size() && s[j] == s[k]) {
            count++;
            j--;
            k++;
        }
        j = i, k = i;
        while (j >= 0 && k < s.size() && s[j] == s[k]) {
            count++;
            j--;
            k++;
        }
    }
    return count;
}
