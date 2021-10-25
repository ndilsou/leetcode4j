package org.nasoungadoy.leetcode4j.solutions;

import java.util.List;

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
public class NestedInteger {
    private final List<NestedInteger> list;
    private final Integer scalar;

    private NestedInteger(Integer scalar, List<NestedInteger> list) {
        this.list = list;
        this.scalar = scalar;
    }

    public static NestedInteger of(int scalar) {
        return new NestedInteger(scalar, null);
    }

    public static NestedInteger of(List<NestedInteger> list) {
        return new NestedInteger(null, list);
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return scalar != null;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return scalar;
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return list;
    }
}
