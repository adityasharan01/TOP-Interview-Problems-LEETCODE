Given an integer n, return the next bigger permutation of its digits.

If n is already in its biggest permutation, rotate to the smallest permutation.

Constraints

n < 2 ** 31
Example 1
Input
n = 527
Output
572
Example 2
Input
n = 321
Output
123
Explanation
321 is already the biggest permutation so it rotates to the smallest.

Example 3
Input
n = 20
Output
2
Hint1:Think about incrementing the number and checking whether its a permutation, How long can you keep checking?
  
  ///IMPLEMENT THE CODE FOR THIS QUES 
  Intuition
Converting number to string and then check for the first number from last which is greater than its predecessor.Swap it to mininmum successor.Sort string and convert it to number.

Implementation
Firstly we will covert number to string as string traversals will be nearly in constant time as compare to number n.Then we will find the first element from last which is greater than its
predecessor (and let mark this position to i-1) and swap this predecessor to its least successor. This makes string left of position i is ready for the ans,now we have to transform right of the i th position(including it).So we just call sort function to sort in ascending order for the right of i th(including it).
If there is no such predecessor than given number is itself the highest permutation number so we just call reverse string.
At last we will convert string back to integer.

Time Complexity
\mathcal{O}(1)O(1) as string length will be much smaller as compared to number and its sorting will be in constant time.

Space Complexity
\mathcal{O}(1)O(1) as no auxiliary space is required.

int solve(int n) {
    if (n == 0) return 0;
    string s = "";
    int num = n;
    while (num) {
        s += (char(num % 10) + '0');
        num /= 10;
    }
    reverse(s.begin(), s.end());

    int i;
    int j = s.size() - 1;
    for (i = s.size() - 1; i > 0; i--) {
        if (s[i] > s[i - 1]) {
            break;
        }
    }
    if (i != 0) {
        // finding the number to replace by
        while (s[i - 1] >= s[j]) j--;

        swap(s[i - 1], s[j]);
        // sorting string left to i-1
        sort(&s[i], &s[s.size()]);
    } else
        reverse(s.begin(), s.end());

    // converting sring into integer
    int x;
    stringstream ss(s);
    ss >> x;

    return x;
}
  ///////////////////////////////////
  Intuition
From right to left, find the first index i, that is less than i+1
From right to left, find the first index j, that is greater than i. Note: we did not check if j is valid, as it cannot be invalid, i is not the biggest number, we know that.
sort part of the array after i.
Implementation
.............................................................................

Time Complexity
The modified code in the comment is \mathcal{O}(n )O(n), thanks for @noob_coder07. The original implementation involves quick sort, so it is \mathcal{O}(nlogn )O(nlogn).

Space Complexity
\mathcal{O}(1)O(1), no intermediate results are stored, just two integers.

  //You can reverse the string instead of sorting and improve the TC further.
  
int solve(int n) {
    string s = to_string(n);
    int i = 0, j = 0;
    for (i = s.size() - 2; i >= 0; i--) {
        if (s[i] < s[i + 1]) break;
    }
    if (i < 0) {
        sort(s.begin(), s.end());
        return stoi(s);
    }
    for (j = s.size() - 1; j >= 0; j--) {
        if (s[j] > s[i]) break;
    }
    swap(s[i], s[j]);
    sort(s.begin() + i + 1, s.end());
    return stoi(s);
}
