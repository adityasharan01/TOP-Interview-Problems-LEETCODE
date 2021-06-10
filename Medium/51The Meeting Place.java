You are given a two dimensional integer matrix consisting of:

0 which represents an empty cell.
1 which represents a wall.
2 which represents a person.
A person can walk up, down, left and right. Find a non-wall cell such that it minimizes the total travel distance each person has to walk to and return the distance.

Note: two people can walk through a same non-wall cell and you can assume there is some path between any two people.

Constraints

n, m ≤ 250 where n and m are the number of rows and columns in matrix
k ≤ 20 where k is the number of people in matrix
Example 1
Input
matrix = [
    [2, 0, 1, 0],
    [1, 0, 1, 2],
    [0, 0, 2, 2]
]
Output
7
Explanation
The best meeting point is in the bottom right corner.
  
  Hint1:try multi source bfs
  
  Intuition
If from every cell, we know the distance to every person, then we can try every cell as the meeting place, calculate the distance to every other person, and then return the minimum distance meeting place.

We can calculate the distance from every cell to every person, by considering the opposite. Calculate the distance from every person to every cell, by running a BFS call from every person. Once we have this dists[r][c][person] that represents the distance from person to cell [r][c], then we can use that to try every [r][c] and calculate the distance to all persons.

Implementation
I first find the locations of all persons. Then I run num_person calls of Breadth First Search to fill in our dists[][][]. Then I try every cell as the meeting place.

Time Complexity
\mathcal{O}(mnp)O(mnp) where p is the number of persons. The runtime is bounded by the dists[][][] we fill in.

Space Complexity
\mathcal{O}(mnp)O(mnp) where p is the number of persons. The runtime is bounded by the dists[][][] we fill in.

import java.util.*;

class Solution {
    int[] dr = {0, 0, -1, 1};
    int[] dc = {1, -1, 0, 0};

    public int solve(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, k = 0;
        ArrayList<int[]> persons = new ArrayList();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == 2) {
                    persons.add(new int[] {r, c});
                    k++;
                }
            }
        }
        Integer[][][] dists = new Integer[m][n][k];
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < k; i++) bfs(dists, matrix, persons, i); // fill dists table

        for (int r = 0; r < m; r++) { // calculate distance
            for (int c = 0; c < n; c++) {
                int dist = 0;
                boolean valid = true;
                for (int person = 0; person < k; person++) {
                    if (dists[r][c][person] == null)
                        valid = false;
                    else {
                        dist += dists[r][c][person];
                    }
                }
                if (valid)
                    res = Math.min(dist, res);
            }
        }
        return res;
    }
    // expand from person i
    public void bfs(Integer[][][] dists, int[][] matrix, ArrayList<int[]> persons, int i) {
        int[] p = persons.get(i);
        int m = matrix.length, n = matrix[0].length, r = p[0], c = p[1], level = 0;
        LinkedList<int[]> q = new LinkedList();
        HashSet<Integer> visited = new HashSet();
        q.addLast(new int[] {r, c});
        visited.add(r * 1007 + c);
        while (q.size() > 0) {
            int size = q.size();
            for (int it = 0; it < size; it++) {
                int[] cur = q.removeFirst();
                int cur_r = cur[0], cur_c = cur[1];
                dists[cur_r][cur_c][i] = level;
                for (int mv = 0; mv < 4; mv++) {
                    int new_r = cur_r + dr[mv], new_c = cur_c + dc[mv];
                    if (visited.contains(new_r * 1007 + new_c) || new_r < 0 || new_c < 0
                        || new_r >= m || new_c >= n || matrix[new_r][new_c] == 1)
                        continue;
                    q.addLast(new int[] {new_r, new_c});
                    visited.add(new_r * 1007 + new_c);
                }
            }
            level++;
        }
    }
}
