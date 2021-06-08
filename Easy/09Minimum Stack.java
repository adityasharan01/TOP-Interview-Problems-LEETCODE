Implement a stack with the following methods:

MinimumStack() constructs a new instance of a minimum stack
append(int val) appends val to the stack
peek() retrieves the last element in the stack
min() retrieves the minimum value in the stack
pop() pops and returns the last element in the stack
Each method should be done in \mathcal{O}(1)O(1) time. You can assume that for peek, min and pop, the stack is non-empty when they are called.

Constraints

n ≤ 100,000 where n is the number of calls to append, peek, min, and pop.
Example 1
Input
methods = ["constructor", "append", "append", "min", "peek", "pop", "pop"]
arguments = [[], [1], [2], [], [], [], []]`
Output
[null, null, null, 1, 2, 2, 1]
Explanation
We create a Minimumstack
We append 1 to the stack
We append 2 to the stack
We get the min value which is 1
We get the top value which is 2
We pop the top value which is 2
We pop the top value which is 1
  
Intuition
Instead of storing values store a tuple with (element, min_element_so_far).

Implementation
All the methods are fairly self explanatory. We keep track of min_elem and top of stack.

Time Complexity
Peek \mathcal{O}(1)O(1)
Min \mathcal{O}(1)O(1)
Pop \mathcal{O}(1)O(1)
Append \mathcal{O}(1)O(1)

Space Complexity
Only space needed is for the stack additional space needed for min elem makes it 2N which boils down to \mathcal{O}(n)O(n)

class MinStack {
    Stack<Integer> min = new Stack<>();
    Stack<Integer> stack = new Stack<>();
    public void push(int x) {
        stack.push(x);
        if (min.isEmpty() || min.peek() >= x) {
            min.push(x);
        }
    }

    public void pop() {
        if (stack.pop().equals(min.peek())) {
            min.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek();
    }
}
