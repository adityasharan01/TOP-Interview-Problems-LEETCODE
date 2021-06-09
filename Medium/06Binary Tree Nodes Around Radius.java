Binary Tree Nodes Around Radius
You are given a binary tree root containing unique integers and integers target and radius. Return a sorted list of values of all nodes that are distance radius away from the node with value target.

Constraints

1 ≤ n ≤ 100,000 where n is number of nodes in root
0 ≤ distance ≤ 100,000
Example 1
Input
Visualize
root =
3

5

2

1

4

6

9

target = 4
radius = 2
Output
[1, 3]
Example 2
Input
Visualize
root =
0

target = 0
radius = 0
Output
[0]

Hint1:Try converting the tree into graph and then applying the necessary algorithms.
  hint2:Try converting the tree into graph and then applying the necessary algorithms.

 Intuition
Can be solved in multiple ways. I choose to create a un-directed graph from the tree and then perform a BFS to find all nodes at distance r

Implementation
First we performed a DFS on the tree and for each parent and child node , we created an edge {u,v} and {v,u} in the graph.
In step2 , once i had the graph built , i simply performed a BFS on the graph to get all the nodes at distance r

Time Complexity
\mathcal{O}(n)O(n) Two traversals are being done here 1) to create undirected graph 2) to do BFS on that graph

Space Complexity
\mathcal{O}(n)O(n) We are using adjacency list to store the graph so yes this approach is a downside on memory

import java.util.*;

/**
 * public class Tree {
 *   int val;
 *   Tree left;
 *   Tree right;
 * }
 */
class Solution {
    private Map<Integer, List<Integer>> graph = new HashMap();
    public int[] solve(Tree root, int target, int radius) {
        if (root == null || radius < 0)
            return new int[0];
        dfs(root);
        return bfs(target, radius);
    }

    private void dfs(Tree node) {
        if (node != null) {
            if (node.left != null) {
                int u = node.val;
                int v = node.left.val;
                graph.computeIfAbsent(u, k -> new ArrayList()).add(v);
                graph.computeIfAbsent(v, k -> new ArrayList()).add(u);
            }

            if (node.right != null) {
                int u = node.val;
                int v = node.right.val;
                graph.computeIfAbsent(u, k -> new ArrayList()).add(v);
                graph.computeIfAbsent(v, k -> new ArrayList()).add(u);
            }

            dfs(node.left);
            dfs(node.right);
        }
    }

    private int[] bfs(int target, int radius) {
        List<Integer> list = new ArrayList();
        Queue<int[]> queue = new LinkedList();
        Set<Integer> seen = new HashSet();
        queue.offer(new int[] {target, 0});
        seen.add(target);

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int u = node[0], d = node[1];
            if (d > radius)
                continue;
            if (d == radius) {
                list.add(u);
                continue;
            }
            if (graph.containsKey(u)) {
                for (int adj : graph.get(u)) {
                    if (!seen.contains(adj)) {
                        seen.add(adj);
                        queue.offer(new int[] {adj, d + 1});
                    }
                }
            }
        }
        Collections.sort(list);
        int[] ans = new int[list.size()];
        int idx = 0;
        for (int item : list) ans[idx++] = item;
        return ans;
    }
}
