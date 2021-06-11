Largest Island After Land Cell Addition
You are given a two-dimensional list of integers matrix containing 1s and 0s where a 1 represents land and 0 represents water. An island is a group of 1s that are neighboring whose perimeter is surrounded by water.

Return the size of the largest island given that we can change at most one water cell to land cell.

Constraints

n, m ≤ 250 where n and m are the number of rows and columns in matrix
Example 1
Input
matrix = [
    [1, 1, 1],
    [0, 0, 0],
    [1, 1, 1]
]
Output
7
Explanation
We can turn any one of the water cells to land to connect the two islands.
 Hint1:Find all the distinct islands adjacent to a particular water(00) cell. 
 If you know the size of each adjacent island then you can get the size of the new island formed by adding one 
 to the sum of sizes of these adjacent islands.
 Hint2:To find the number of distinct islands adjacent to a water(00) cell
 you can assign each land(11) cell the id of the island it belongs to. So, all cells belonging to the same island will have the same id, 
something like Disjoint Set Union/Union Find. The number of distinct adjacent islands will then be the same as the number distinct island ids.
  
Intuition
If for every water cell, we know the size of the lands of its direct \mathcal{}44 neighbors [\mathcal{}left, right, top, bottom]left,right,top,bottom], then we can get our solution by iterating through all \mathcal{}n^2n 
2
  water cells, checking each of the \mathcal{}44 neighbors, calculating the sum of connecting the direct neighboring land sizes, and returning the max.

One caveat is when we sum the connection of direct neighboring land sizes, we should only add neighboring land sizes once. If for example, the top neighboring land is connected to the right neighboring land, we should only add that piece of land size once, and not twice.

An \mathcal{}efficientefficient way to calculate the size of each piece of land and to determine if one piece of land is connected to another piece of land is through a data structure called Disjoint Set Union [DSU]. We can first iterate through every land cell and throw it into our DSU. Then, the second pass, we will iterate through the water cells and check the \mathcal{}44 neighbors and add the sums of size of lands for every unique piece of neighboring land.

Implementation
I have a saved copy of Disjoint Set Union [DSU] in my github so I just use that. Although, its relatively simple to implement and easy to understand.

Time Complexity
\mathcal{O}(m*n)O(m∗n) where \mathcal{}mm is the row and \mathcal{}nn is the column. We visit each cell at most 5 times.

Space Complexity
\mathcal{O}(m*n)O(m∗n) We throw each \mathcal{}m*n)m∗n) cells into our DSU.

import java.util.*;

class Solution {
    public int solve(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean seen_water = false;
        int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Dsu<Pair<Integer, Integer>> dsu = new Dsu();

        HashSet<Pair<Integer, Integer>> seen = new HashSet();
        int res = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == 1) {
                    Pair<Integer, Integer> cell = new Pair(r, c);
                    dsu.make_set(cell);

                    for (int[] move : moves) {
                        Pair<Integer, Integer> neighbor = new Pair(r + move[0], c + move[1]);
                        if (seen.contains(neighbor)) {
                            dsu.union_set(cell, neighbor);
                        }
                    }
                    seen.add(cell);
                }
            }
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == 0) {
                    HashSet<Pair<Integer, Integer>> parents = new HashSet();
                    seen_water = true;
                    int cand = 1;
                    for (int[] move : moves) {
                        Pair<Integer, Integer> neighbor = new Pair(r + move[0], c + move[1]);
                        if (parents.contains(dsu.find_set(neighbor)))
                            continue;
                        parents.add(dsu.find_set(neighbor));
                        cand += dsu.set_size(neighbor);
                    }

                    res = Math.max(res, cand);
                }
            }
        }
        if (!seen_water)
            return m * n;
        return res;
    }
    public class Dsu<T> {
        private HashMap<T, T> parent;
        private HashMap<T, Integer> size;
        private int roots;

        /**
         * Class constructor for the Disjoint Set Union class, uses generics. Does not require any
         * initial set number of nodes in the graph.
         * example usage:
         * using integers: Dsu<Integer> dsu = new Dsu<Integer>()
         * using pairs: Dsu<Pair<Integer,Integer>> dsu = new Dsu<Pair<Integer,Integer>>();
         */
        public Dsu() {
            this.parent = new HashMap<T, T>();
            this.size = new HashMap<T, Integer>();
            this.roots = 0;
        }

        /**
         * Time Complexity: O(1)
         * Takes a new vertex v and creates a new set as a parent of itself in O
         * @param v The vertex to add to the DSU
         */
        void make_set(T v) {
            parent.put(v, v);
            size.put(v, 1);
            roots++;
        }

        /**
         * Time Complexity: O(log(n))
         * Takes a new vertex v and returns the root. Uses Path Compression
         * Path Compression is where you find the root, and set all node's parent along that path to
         * root
         * @param v The vertex to find the root of
         * @return The root of the vertex v
         */
        T find_set(T v) {
            if (!parent.containsKey(v))
                return v;
            if (parent.get(v).equals(v))
                return v;
            T root = find_set(parent.get(v));
            parent.put(v, root);
            return root;
        }

        /**
         * Time Complexity: O(log(n))
         * Takes a two vertex a and b and joins the two sets. Uses Union by Size
         * @param a The first set to join
         * @param b The second set to join
         */
        void union_set(T a, T b) {
            a = find_set(a);
            b = find_set(b);

            if (!a.equals(b)) {
                if (size.get(a) < size.get(b)) {
                    parent.put(a, b);
                    size.put(b, size.get(b) + size.get(a));
                } else {
                    parent.put(b, a);
                    size.put(a, size.get(b) + size.get(a));
                }
                roots--;
            }
        }

        /**
         * Time Complexity: O(1)
         * Returns the number of roots
         * @return The number of distinct roots
         */
        int num_roots() {
            return this.roots;
        }

        /**
         * Time Complexity: O(1)
         * Returns the size of set that contains a
         * @return The size of the set rooted at a
         */
        int set_size(T a) {
            if (!parent.containsKey(a))
                return 0;
            return this.size.get(find_set(a));
        }
    }
}
