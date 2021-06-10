You are given a list of integers tasks where each different integer represents a different task type, and a non-negative integer k. Each task takes one minute to complete, but you must wait k minutes between doing two tasks of the same type. At any time, you can be doing a task or waiting.

Return the smallest amount of time it takes to complete all the tasks.

Constraints

n ≤ 100,000 where n is the length of tasks
0 ≤ k
Example 1
Input
nums = [0, 0, 0, 1, 1, 0]
k = 1
Output
7
Explanation
Optimal ordering is [0, 1, 0, 1, 0, wait, 0].
//implemnet it in Java 
  Intuition
Our n tasks will take n minutes. To find the total time, we have to figure out the time spent waiting. Then, result = n + waiting time.
If we had only one type of task with f frequency, then total time = (f-1)(k+1)+1,
eg, [1,1,1,1,1], k=2 then our task queue would be 1__1__1__1__1
if we had 2 type of task with same f frequency, then total time = (f-1)(k+1)+2,
e,g [1,1,1,2,2,2], k=2 then our task queue would be 12_12_12_12_12
All the other tasks with smaller frequencies can fit in the rest spaces, if they do not we can just add more tasks in between the most frequent tasks.

Implementation
+Count frequency using map, while storing the max frequency.
+Count total time using above mentioned intuition.
if we get total time less than n, it means we do not need to wait at any point, so we can just return n.

Time Complexity
Algorithm works in \mathcal{O}(n)O(n), as we have to iterate over the tasks to count frequency

Space Complexity
We are maintaining a frequency map. So, it takes \mathcal{O}(n)O(n) space.

int solve(vector<int>& tasks, int n) {
    unordered_map<int, int> freq;
    int count = 0;
    for (int i = 0; i < tasks.size(); i++) {
        freq[tasks[i]]++;
        count = max(count, freq[tasks[i]]);
    }
    int res = (count - 1) * (n + 1);
    for (auto a : freq)
        if (a.second == count) res++;
    return max((int)tasks.size(), res);
}
