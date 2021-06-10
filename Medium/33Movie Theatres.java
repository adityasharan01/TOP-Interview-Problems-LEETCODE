Given a list of time exclusive intervals for different movie showings (possibly overlapping), find the minimum number of theatres required to be able to show all movies.

Constraints

0 ≤ n ≤ 100,000 where n is the length of `intervals
Example 1
Input
intervals = [
    [30, 75],
    [0, 50],
    [60, 150]
]
Output
2
Explanation
[30, 75] and [0, 50] overlap. [30, 75] and [60, 150] also overlap but later on. So the max number here is 2.

Example 2
Input
intervals = [
    [10, 20],
    [20, 30]
]
Output
1
Explanation
Boundaries are exclusive so these intervals are not considered overlapping.

Example 3
Input
intervals = [
    [0, 1],
    [0, 1],
    [0, 1]
]
Output
3
Explanation
The three intervals happen all at the same time so we need 3.
Intuition
We will take a greedy aproach to solve this. Think of what you will do in real life if you had some movies and you are asked to assign rooms for them. You will assign movies to empty rooms first and then you will create a new room if there is not available room. We will do the same thing here. We will first sort the movies based on their start times (because in real life, movies will run in a sequential manner). And then we will try to assign rooms for movies. When assigning rooms, we have to look if we have any rooms that is available. if not, we have to create a new room. At the end of this sequence of assigning rooms, we return the number of rooms that we have created.

Implementation
Sort the intervals by their start time. From earliest to latest.
For each movie, we need to decide if there is a available room or not. To do that, we can have a min-heap which will give us the room that ends earlier than other rooms. If this room does not become available before the start time of the current movie, we need to create a new room for the movie and put it in the min-heap. Otherwise if the room becomes available before the start time of the movie (end time of the room is less than start time of the movie), we can use this room for the current movie. Since we are using this room, we will update the rooms new ending time to be equal to the ending time of the current movie.
At the end, our result will be equal to the number of rooms that we created so far. this can be found by looking at the min-heap size, since are storing all the rooms time intervals in the heap.
Time Complexity
\mathcal{O}(n\log n )O(nlogn) - We are using a heap. To maintain the heap it will take \mathcal{O}(\log n )O(logn) time.. And we are doing it for n movies.

Space Complexity
\mathcal{O}(n)O(n) - We are using a heap of size at most n, where n is the number of movies.

import java.util.*;

class Solution {
    public int solve(int[][] intervals) {
        if (intervals.length == 0)
            return 0;

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // sort by start time
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // sort by end time

        pq.add(intervals[0]); // put the first movie in a new theater room

        for (int i = 1; i < intervals.length; i++) { // for all other movies
            int[] recentTheater = pq.poll(); // room that is going to be available first

            if (recentTheater[1] <= intervals[i][0]) { // recent theater is available
                recentTheater[1] = intervals[i][1]; // make it unavailable by changing end time
                pq.add(recentTheater);
            } else { // recent ending theater is not available.create a new one.
                pq.add(recentTheater);
                pq.add(intervals[i]);
            }
        }

        return pq.size();
    }
}
