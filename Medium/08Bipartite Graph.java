Given an undirected graph represented as an adjacency list, return whether the graph is bipartite.

Constraints

n, m ≤ 250 where n and m are the number of rows and columns in graph
Example 1
Input
Visualize
graph =
0

1

Output
true
Explanation
This is bipartite since the node 1 can belong in set A and node 2 can belong in set B. Then the edges 0 -> 1 and 1 -> 0 has one node in A and one node in B

Example 2
Input
Visualize
graph =
0

1

2

3

Output
true
Explanation
0 and 1 can belong in set A and 2 and 3 can belong in set B.

Example 3
Input
Visualize
graph =
0

1

2

3

Output
false
Explanation
No matter how the nodes are partitioned, an edge will belong to the same set.
Hint1:check the condition to be a bipartite
Hint2:check the condition to be a bipartite
Hint3:if the graph is bipartite then its adjacent nodes cant have same color

Intuition
Divide graph nodes into two group let's say group id's are 1 and -1, if any node X has neighbors a,b and c then X can belongs to group 1 and it's neighbors will go in -1 group, in the same way a,b and c neghbors will go in 1 group , if at any time any node and it's neghbors group id is same it means it is a conflicts and we can not devide graph nodes in two part.

Implementation
Time Complexity
\mathcal{O}(V+E)O(V+E)

Space Complexity
\mathcal{O}(V)O(V) to store the group id's

import java.util.*;

class Solution {
    public boolean solve(int[][] arr) {
        int n = arr.length;

        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            if (color[i] == 0 && !dfs(i, arr, color, 1))
                return false;
        }
        return true;
    }
    boolean dfs(int start, int[][] graph, int[] color, int c) {
        color[start] = c;

        for (int i = 0; i < graph[start].length; i++) {
            int point = graph[start][i];

            if (color[point] == 0 && !dfs(point, graph, color, -c))
                return false;

            else if (color[point] == c)
                return false;
        }
        return true;
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////
Intuition
The idea is to use a graph colouring or use two distinct sets while doing DFS

Implementation
Two sets seta and setb are defined to store the different ends of an edge
There is a global visited set as the graph may not be fully connected so multiple DFS runs could be required
The algo starts with DFS from any unvisited vertex and assign/associate it with either seta (0) or setb (1).
When you visit its adjacent vertex the rules that apply are

ignore the parent as the adjacent (parent needs to be passed during the DFS calls)
If the current node is associated with seta and its adjacent is visited and is found in same set (seta) , mark the isBipartite as false and return .,,
if the current node is associated with setb and its adjacent is visited and is found to be present in same set (setb), again the isBipartite property is unsatisfied and stop the DFS.
else there could be two cases a) either this adjacent vertex is visited and in different set -> which is good as the property is not violated : do nothing
or the adjacent vertex is not yet visited , then do a dfs call on that vertex with its set association as the opposite to what the current set is
Time Complexity
\mathcal{O}(n)O(n) : All the vertex of the graph is pruned/visited only once

Space Complexity
\mathcal{O}(n)O(n): Extra space is used to store the vertices in two different sets

import java.util.*;

class Solution {
    private Set<Integer> visitedSet = new HashSet();
    private Set<Integer> seta = new HashSet();
    private Set<Integer> setb = new HashSet();
    private boolean isBipartite = true;
    public boolean solve(int[][] graph) {
        if (graph == null || graph.length == 0)
            return false;
        for (int i = 0; i < graph.length; i++) {
            if (!visitedSet.contains(i)) {
                if (!isBipartite)
                    break;
                ;
                dfs(graph, i, -1, 0);
            }
        }
        return isBipartite;
    }

    private void dfs(int[][] graph, int vertex, int parent, int setIdentifier) {
        addToSet(vertex, setIdentifier);
        int[] adj = graph[vertex];
        if (!isBipartite)
            return;
        for (int adjVertex : adj) {
            if (adjVertex != parent) {
                if (setIdentifier == 0 && seta.contains(adjVertex)) {
                    isBipartite = false;
                    return;
                }
                if (setIdentifier == 1 && setb.contains(adjVertex)) {
                    isBipartite = false;
                    return;
                }
                if (!seta.contains(adjVertex) && !setb.contains(adjVertex))
                    dfs(graph, adjVertex, vertex, (setIdentifier == 0 ? 1 : 0));
            }
        }
    }

    private void addToSet(int vertex, int setIdentifier) {
        visitedSet.add(vertex);
        if (setIdentifier == 0)
            seta.add(vertex);
        else
            setb.add(vertex);
    }
}
  
