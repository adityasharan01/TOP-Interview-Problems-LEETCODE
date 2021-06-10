Implement a least recently used cache with the following methods:

LRUCache(int capacity) constructs a new instance of a LRU cache with the given capacity.
get(int key) retrieves the value associated with the key key. If it does not exist, return -1. As a side effect, this key now becomes the most recently used key.
set(int key, int val) updates the key key with value val. If updating this key-value pair exceeds capacity, then evicts the least recently used key-value pair.
Each method should be done in \mathcal{O}(1)O(1) time.

Constraints

n ≤ 100,000 where n is the number of calls to get and set.
Example 1
Input
methods = ["constructor", "set", "get", "set", "set", "set", "get", "get"]
arguments = [[3], [1, 10], [1], [2, 20], [3, 30], [4, 40], [1], [4]]`
Output
[null, null, 10, null, null, null, -1, 40]
Explanation
We create an LRUCache of capacity 3.

Set key of 1 to value 10. Size of cache is now 1
We get 1 which has value of 10
Set key of 2 to value 20. Size of cache is now 2
Set key of 3 to value 30. Size of cache is now 3
Set key of 4 to value 40. Size exceeds capacity, so now we evict the least recently used key which is 1.
We get 1 which has been evicted so we return -1
We get 4 which has value of 40
  
  Hint1:Consider storing the key-value pairs in a linked list and retrieving the nodes using a hash table.
    
  Intuition
The idea is to use a LinkedHashMap as a supporting Data structure. LinkedHashMap maintains the relative order of insertion and hence the newly added element is always at the tail of the hashmap while the oldest one remains at the first index

Implementation
At each get and set operaration

remove the element if exists
add the element with the value (if set)
in set if the size exceeds the capacity , remove the first key

Time Complexity
\mathcal{O}(Q)O(Q) each query will take constant time...hence time complexity is the no of queries being made

Space Complexity
\mathcal{O}(capacity)O(capacity) We are only allowing the linkedhashmap to be size <= capacity

import java.util.*;

class LRUCache {
    private Map<Integer, Integer> map;
    private int maxCapacity;
    public LRUCache(int capacity) {
        map = new LinkedHashMap<>();
        maxCapacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        int value = map.get(key);
        map.remove(key);
        map.put(key, value);
        return value;
    }

    public void set(int key, int val) {
        if (map.containsKey(key))
            map.remove(key);
        map.put(key, val);
        if (map.size() > maxCapacity) {
            for (int k : map.keySet()) {
                map.remove(k);
                return;
            }
        }
    }
}
