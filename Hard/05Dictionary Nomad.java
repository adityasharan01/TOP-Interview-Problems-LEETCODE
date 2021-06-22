You are given a list of strings dictionary and two strings start and end. You want to reach from start to end by
modifying one character at a time and making sure each resulting word is also in the dictionary. Words are case-sensitive.

Return the minimum number of steps it would take to reach end. Return -1 if it's not possible.

Constraints

0 ≤ n * m ≤ 300,000 where n is the length of dictionary and m is the length of the longest string
Example 1
Input
dictionary = ["day", "say", "soy"]
start = "soy"
end = "day"
Output
3
Explanation
We can take this path: ["soy", "say", "day"].

Example 2
Input
dictionary = ["day", "soy"]
start = "soy"
end = "day"
Output
-1
Explanation
There's no way to change 1 character to reach "day".
  
  Hint1:Can we represent these components as a graph? Think of words as nodes and connected nodes are ones with 1 character difference
  Hint2:Simple Bfs
  
  Intuition
We are given a problem with an initial state and a state of next possible states, asking for the minimum length.
 This is simply asking for shortest distance between two vertex in an undirected graph.
Hence, BFS

Implementation
We simpy use the starting letter and at each step generate all the possible permutations and check for valid permutations (from vWords map). We keep repeating the step until we have visited all the words and there's no valid next permutation or we have found the end word

Time Complexity
\mathcal{O}(26m*m*n)O(26m∗m∗n) For each word length m, we spend 26 * m time for generating possible next states. We do this for N dictionary elements

Space Complexity
\mathcal{O}(n)O(n)A queue and a map which stores at max N words

import java.util.*;

class Solution {
    public int solve(String[] dictionary, String start, String end) {
        Map<String, Boolean> vWords = new HashMap<>();
        for (String s : dictionary) {
            vWords.put(s, false);
        }
        if (!vWords.containsKey(end))
            return -1;
        Deque<String> states = new ArrayDeque<>();
        int level = 1;
        states.addLast(start);
        while (!states.isEmpty()) {
            int size = states.size();
            while (size-- > 0) {
                String node = states.removeFirst();
                vWords.put(node, true);
                if (end.equals(node))
                    return level;
                generateNextStates(states, node, vWords);
            }
            ++level;
        }
        return -1;
    }
    private void generateNextStates(
        Deque<String> states, String word, Map<String, Boolean> vWords) {
        for (int i = 0; i < word.length(); i++) {
            char[] ch = word.toCharArray();
            for (int j = 0; j < 26; j++) {
                ch[i] = (char) (j + 97);
                String str = String.valueOf(ch);
                if (vWords.containsKey(str) && !vWords.get(str))
                    states.addLast(str);
            }
        }
    }
}
//////////////////////////////////////////////////////
Intuition
The problem can be modelled as a undirected graph with weight of each edge being one. So, BFS can be used to calculate the distance between source and target node. Each node or state in the graph can be represented completely by the current word and the adjacent nodes are the nodes in the dictionary having char diff equal to 1 from this node.

import java.util.*;

class Solution {
    public int solve(String[] dictionary, String start, String end) {
        Queue<String> queue = new LinkedList<>();
        queue.add(start);

        HashMap<String, Integer> mark = new HashMap<>();
        mark.put(start, 0);

        while (queue.size() > 0) {
            String u = queue.poll();

            if (u.equals(end))
                return mark.get(u) + 1;

            for (String v : dictionary) {
                int diff = 0;
                if (v.length() != u.length())
                    continue;
                for (int j = 0; j < v.length(); j++) {
                    if (u.charAt(j) != v.charAt(j)) {
                        diff++;
                    }
                }
                if (diff == 1 && !mark.containsKey(v)) {
                    queue.add(v);
                    mark.put(v, mark.get(u) + 1);
                }
            }
        }

        return -1;
    }
}
