An upside down number is one that appears the same when flipped 180 degrees. Given n, return all upside down numbers as strings whose length is n, sorted in lexicographic order.

Constraints

n ≤ 15
Example 1
Input
n = 1
Output
["0", "1", "8"]
Example 2
Input
n = 2
Output
["11", "69", "88", "96"]
Example 3
Input
n = 3
Output
["101", "111", "181", "609", "619", "689", "808", "818", "888", "906", "916", "986"]

Intuition
The only valid numbers we can use are 0, 1, 6, 8, 9. Thus, for every idx and its matching index (n-idx-1), we will fill it with one of those numbers. We will use DFS to generate them. Take care of edge cases because we cannot have leading zeros

Implementation
I use dfs to recurse through and generate numbers.

Time Complexity
\mathcal{O}(kn)O(kn) where k is the number of valid solutions and n is the length of the string

Space Complexity
\mathcal{O}(kn)O(kn) We generate k solutions each with length n

vector<char> valid{'0', '1', '6', '8', '9'};

void dfs(string &tmp, vector<string> &res, int idx) {
    int mid = (tmp.size() - 1) / 2;
    if (idx > mid) {
        string cpy = tmp;
        res.push_back(cpy);
        return;
    }
    for (char c : valid) {
        if (idx == 0 && tmp.size() > 1 && c == '0') continue;
        char match = c;
        if (c == '6') match = '9';
        if (c == '9') match = '6';
        if (c == '6' || c == '9') {
            if (idx == tmp.size() - 1 - idx) continue;
        }
        tmp[idx] = c;
        tmp[tmp.size() - 1 - idx] = match;
        dfs(tmp, res, idx + 1);
    }
    return;
}
vector<string> solve(int n) {
    vector<string> res;
    if (n == 0) return res;
    string tmp(n, '0');
    dfs(tmp, res, 0);
    return res;
}

