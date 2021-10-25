package org.nasoungadoy.leetcode4j.solutions;


import java.util.*;

//You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists. Implement an iterator to flatten it.
//
//        Implement the NestedIterator class:
//
//        NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
//        int next() Returns the next integer in the nested list.
//        boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
//        Your code will be tested with the following pseudocode:
//
//        initialize iterator with nestedList
//        res = []
//        while iterator.hasNext()
//        append iterator.next() to the end of res
//        return res
//        If res matches the expected flattened list, then your code will be judged as correct.
//
//
//
//        Example 1:
//
//        Input: nestedList = [[1,1],2,[1,1]]
//        Output: [1,1,2,1,1]
//        Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
//        Example 2:
//
//        Input: nestedList = [1,[4,[6]]]
//        Output: [1,4,6]
//        Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
//
//
//        Constraints:
//
//        1 <= nestedList.length <= 500
//        The values of the integers in the nested list is in the range [-106, 106].

public class NestedIterator implements Iterable<Integer>, Iterator<Integer> {
    private final Deque<Iterator<NestedInteger>> stack;
    private int item;
    private boolean hasNext;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new ArrayDeque<>();
        stack.add(nestedList.iterator());
        hasNext = false;
    }


    @Override
    public Integer next() {
        if (!hasNext) throw new NoSuchElementException();
        return item;
    }

    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) {
            hasNext = false;
            return false;
        }

        while (true) {
            var iter = stack.pop();
            if (!iter.hasNext()) {
                if (stack.isEmpty()) {
                    hasNext = false;
                    return false;
                } else continue;
            }
            var elt = iter.next();

            stack.push(iter); // put parent back on stack

            if (elt.isInteger()) {
                item = elt.getInteger();
                hasNext = true;
                return true;
            }

            iter = elt.getList().iterator();
            stack.push(iter); // put child on stack.
        }
    }

    public Iterator<Integer> iterator() {
        return this;
    }
}

/*
 Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
