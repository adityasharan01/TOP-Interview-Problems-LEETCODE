Implement a RollingMedian class with the following methods:

add(int val) which adds val to the data structure
median() which retrieves the current median of all numbers added
Median of [1, 2, 3] is 2 whereas median of [1, 2, 3, 4] is 2.5.

Constraints

n ≤ 100,000 where n is the number of calls to add and median
Example 1
Input
methods = ["constructor", "add", "add", "add", "median", "add", "median"]
arguments = [[], [1], [2], [3], [], [4], []]`
Output
[null, null, null, null, 2, null, 2.5]
Explanation
We first add 1, 2, and 3. The median is then 2. Then we add 4. Median is now (2 + 3) / 2 = 2.5
Hint1:Try To Insert The Elements In SORTED FORM into the data structure as soon as you encounter them.
Hint2:Alternate method:
Median = center
can you try to maintain the left and right halves around the median? 


Intuition
The intution is that you should maintain two containers, one will store the minimum elements and other will store the maximum elements. During this whenever a new item comes (we always add it to left container) in we will manipulate containers such that the objective will be stored. One will store the minimum elements and other will store the maximum elements means that the largest element in the left container and smallest element in the right container only contributes towards median. Hence the median finding will be following cases:

if left container size is greater than right
1.1) Since we assumed that new element always goes to the left container. the median is largest element in the left container.
if left container size is equal to right
2.1) Then the median is average of largest element in left and smallest element in right
if left container size is smaller that right
3.1) Think about it (never possible) since we always add new element to left container and then manipulate the container to meet the objective.
Implementation
The container we talked about, the left should be max_heap and the right should be min_heap. Becuase we need largest in left container and smallest in right container. So we created those two heaps as small and great. The add function will be clear to you with the below example. The median is pretty self-explanatory if you understand our containers objective and above cases.
left --> small
right --> great in code

Example #1
Let's get into example. We are taking an array of elements  12,8,15,10,5 after every element insertion, we will find the median. When both contianers left and right are empty, we assumed every new element goes into left container and hence left : 12  && right: . So the first finding is When both empty go with your assumption. Now 8,left has size greater than right and we have to add the element to left, so we will first transfer the largest in left to right and then add the new to left. so left.size() > right.size() and left.peek() > val --> transfer peek from left to right and add the new  to left. Now 15, both left and right sizes are equal, still we add to left but it is greater than the peek of left, so we will add it to right and transfer the smallest element in right to left. left: 8,12 and right: 15. Now 10, left size is greater than the right, the left peek is greater than val --> to satisfy sizes(since we need middle, we have to) we will transfer largest in left to right and add the 10. left: 8,10 and right: 12,15. Now 5, left size if equal to right, and peek of left(10) is greater than the 5 and hence we will add 5 to left directly. Now compare your understanding with the code. if you didn't understand either dm me or write in comments here, happy learn and teach. Happy coding....:)

Time Complexity
For every single insertion or transfer we will need \mathcal{O}(\log n )O(logn). For n elements we need \mathcal{O}(n\log n )O(nlogn).

Space Complexity
Since we are using left and right containers, also we always manage to maintain half in left and half in right. all the elements need to be stored. Hence \mathcal{O}(n)O(n)

import java.util.*;

class RollingMedian {
    PriorityQueue<Integer> small, great;

    public RollingMedian() {
        this.small = new PriorityQueue<Integer>(Collections.reverseOrder());
        this.great = new PriorityQueue<Integer>();
    }

    public void add(int val) {
        if (this.small.size() == 0 && this.great.size() == 0) {
            this.small.add(val);
        } else if (this.small.size() > this.great.size()) {
            if (this.small.peek() > val) {
                this.great.add(this.small.poll());
                this.small.add(val);
            } else {
                this.great.add(val);
            }

        } else {
            if (this.small.peek() >= val) {
                this.small.add(val);
            } else {
                this.great.add(val);
                this.small.add(this.great.remove());
            }
        }
    }

    public double median() {
        if (this.small.size() > this.great.size()) {
            return this.small.peek();
        } else {
            return (double) (this.small.peek() + this.great.peek()) / 2;
        }
    }
}
