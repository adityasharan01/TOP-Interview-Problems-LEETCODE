Given a string s representing a mathematical expression with"+", "-", "/", and "*", evaluate and return the result.

Note: "/" is integer division. Can you implement without using eval?

Example 1
Input
s = "1+2*4/6"
Output
2
Explanation
1 + ((2 * 4) / 6) = 2
  H1:take care of negative numbers
  H2:Another Caveat:
8/-31 =0 is wrong
floor((8*1.0/(-31)) = -1 is right

Intuition
We store operators and operands with two stacks.

If current operator is greater than the one on top of the operator stack, we push it in, otherwise we pop out operators and operands and calculate values, until the operator stack is empty or the top operator is less than current operator.

Implementation
There is one interesting thing about /. Initially, I just return a / b, but it fails for test case like -1/2 or 3/-14. I have to make it (int)floor(1.0 * a / b) to pass all the tests.

Time Complexity
\mathcal{O}(n)O(n), scan the string from left to right

Space Complexity
\mathcal{O}(n)O(n), just two stacks for operators and operands

int cmp(char a, char b) {
    int aa = (a == '+' || a == '-') ? 1 : 2;
    int bb = (b == '+' || b == '-') ? 1 : 2;
    return aa - bb;
}

int calc(stack<int>& sk, stack<char>& op) {
    int b = sk.top();
    sk.pop();
    int a = sk.top();
    sk.pop();
    char c = op.top();
    op.pop();
    if (c == '+') return a + b;
    if (c == '-') return a - b;
    if (c == '*') return a * b;
    if (c == '/') return (int)floor(1.0 * a / b);
    assert(false);
    return 0;
}

int solve(string s) {
    stack<int> sk;
    stack<char> op;
    for (int i = 0, j = 0; j < s.size();) {
        if (s[j] == '-') j++;
        while (j < s.size() && isdigit(s[j])) j++;
        int n = stoi(s.substr(i, j - i));
        sk.push(n);
        if (j == s.size()) {
            while (!op.empty()) sk.push(calc(sk, op));
            break;
        } else {
            if (op.empty() || cmp(s[j], op.top()) > 0) {
                op.push(s[j]);
            } else {
                while (!op.empty() && cmp(s[j], op.top()) <= 0) {
                    sk.push(calc(sk, op));
                }
                op.push(s[j]);
            }
        }
        j++;
        i = j;
    }
    return sk.top();
}


/////////////////////////////////////////////////////////////
Intuition
Craziest Solution Everrrrr
Why do I need to explain my intuition ? Is this hard to understand, LoL No !
Why get crazy and do all post-fix and infix conversion when you have eval() in python o_O

Implementation
Since we want integer division, we need to replace all / with //. Haha fairly simple ?
s.replace("/", "//")

Time Complexity
\mathcal{O}(n)O(n) -> eval itself takes len(s) time to calculate exp. value.

Space Complexity
\mathcal{O}(n)O(n) -> eval itself takes len(s) space to calculate exp. value.

class Solution:
    def solve(self, s):
        return eval(s.replace("/", "//"))
