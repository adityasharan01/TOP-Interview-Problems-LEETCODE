Balanced Brackets Sequel
Given a string s containing round, curly, and square open and closing brackets, return whether the brackets are balanced.

Constraints

n ≤ 100,000 where n is the length of s
Example 1
Input
s = "[(])"
Output
false
Example 2
Input
s = "([])[]({})"
Output
true
Hint1:Use a Stack

Intuition
A Stack can maintain the ordering of the last seen open symbol.

Whenever we see an open symbol, we push to the stack.

If we see a close symbol, we compare it to the open symbol, and check if there is a match. With a match, we POP the open symbol from the stack.

But watch out for edge cases! Maybe we see a close symbol, and the stack is already empty! In that case, we do not have an open symbol to compare with so there is no match.

Even if every open symbol matches with a closing symbol, what if we have extra open symbols? Then our stack is not empty even after all the matches.

Implementation
Step 1) Iterate thru each char in the string

Step 2) For the ith char, if it's open then push to stack
else it must be a close so compare that with the top of the stack.

Step 3) If the stack is NOT empy and the comparison between the ith char and the top of the stack match like so -> () || {} || [] then its a match and we pop!

Otherwise this is already an invalid pairing of brackets and return false!

Step 4) After validating all the chars in the string and matching them with an open bracket, check if the stack is empty. If it is empty, then hooray! Otherwise there were some left over open brackets that never got a match, just like me being single ;( so return false >:0
Example #1
Let's say s = "("

We push the first and only char into the stack, then go into step 4, check if the stack is empty. It is not empty so return false!


Let's say s = ")"
We see a close symbol, so we check if there is a match. The stack is empty so no match and return false!

Let's say s = "{[]}()"
We push { and [  into the stack. Then we see ] and compare it to the top of the stack -> [ and this is a match! So we pop :o and only { remains in the stack all on its own, alone, like me with no gf ;(

Anyways, we then see } as the next char, and check if there is a match at the top of the stack. There is a match with { so we pop :o and now the stack is empty just like my heart with no gf ;(

Finally we see ( and push it into the stack by default since it is an open symbol. But then we see ) and compare it to the top of the stack, which has a match! We pop and now the stack is empty again. Now that we checked all chars in s, we return whether the stack is empty, so return true.
Time Complexity
\mathcal{O}(n)O(n) to loop thru each char in the string

Space Complexity
\mathcal{O}(n)O(n) since the stack will hold all chars if the input is purely open symbols

import java.util.*;

class Solution {
    public boolean solve(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[')
                stack.push(c);
            else if (isMatch(c, stack))
                stack.pop();
            else
                return false;
        }
        return stack.isEmpty();
    }

    public boolean isMatch(char c, Stack<Character> stack) {
        if (stack.isEmpty())
            return false;

        char last = stack.peek();
        if (c == ']' && last == '[')
            return true;
        else if (c == '}' && last == '{')
            return true;
        else if (c == ')' && last == '(')
            return true;
        return false;
    }
}
