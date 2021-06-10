Given a string s, determine whether any anagram of s is a palindrome.

Constraints

n ≤ 100,000 where n is the length of s
Example 1
Input
s = "carrace"
Output
true
Explanation
"carrace" should return true, since it can be rearranged to form "racecar", which is a palindrome.
  
  Hint1:A property of a palindrome is that it can have at most one unique character that occurs odd number of times.
   ////////////////////////WRITE JAVA CODE FOR THIS!! 
    Intuition
We can verify a palindromic anagram only If all the characters in the string have even frequency or if we have at most one character with odd frequency , coz we can arrange them in a way where all the front part and back part is equal and the odd frequency character can stay in the center .

Implementation
We count all the odd the occurrence of the the alphabets and check which of the them are odd if the odd_count > 1 than it's not a palindromic anagram

Example #1
if we have ababaa we can arrange it like aabbaa
if we have abababa we can arrange it like aabbbaa
we don't actually arrange them , we just simulate if the frequency of a character is even we can place it in such a way that we always have it's matching pair.

Time Complexity
\mathcal{O}(\text{n})O(n) since we process every alphabet once

Space Complexity
\mathcal{O}(1)O(1) only 26 blocks of space is used

bool solve(string s) {
    int odd_count = 0;
    vector<int> count(26, 0);
    for (auto ch : s) count[ch - 'a'] ^= 1;
    for (auto i : count) odd_count += i;
    return odd_count <= 1;
}
