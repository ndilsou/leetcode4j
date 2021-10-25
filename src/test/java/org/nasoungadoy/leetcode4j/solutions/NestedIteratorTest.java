package org.nasoungadoy.leetcode4j.solutions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class NestedIteratorTest {
    @Test
    void baseCase1() {
        var nestedList = List.of(
                NestedInteger.of(List.of(NestedInteger.of(1), NestedInteger.of(1))),
                NestedInteger.of(2),
                NestedInteger.of(List.of(NestedInteger.of(1), NestedInteger.of(1)))
        );

        var want = List.of(1, 1, 2, 1, 1);

        var iter = new NestedIterator(nestedList);
        var got = new ArrayList<Integer>();
        for (var i : iter) {
            got.add(i);
        }
        assertIterableEquals(want, got);
    }

    @Test
    void baseCase2() {
        var nestedList = List.of(
                NestedInteger.of(1),
                NestedInteger.of(List.of(NestedInteger.of(4))),
                NestedInteger.of(List.of(NestedInteger.of(6))));

        var want = List.of(1, 4, 6);

        var iter = new NestedIterator(nestedList);
        var got = new ArrayList<Integer>();
        for (var i : iter) {
            got.add(i);
        }
        assertIterableEquals(want, got);
    }

    @Test
    void emptyIter() {
        List<NestedInteger> nestedList = List.of();

        List<Integer> want = List.of();

        var iter = new NestedIterator(nestedList);
        var got = new ArrayList<Integer>();
        for (var i : iter) {
            got.add(i);
        }
        assertIterableEquals(want, got);
    }

    @Test
    void emptyChildAndParentIters() {
        List<NestedInteger> nestedList = List.of(NestedInteger.of(1),
                NestedInteger.of(List.of()));

        List<Integer> want = List.of(1);

        var iter = new NestedIterator(nestedList);
        var got = new ArrayList<Integer>();
        for (var i : iter) {
            got.add(i);
        }
        assertIterableEquals(want, got);
    }

    @Test
    void emptyChildIter() {
        List<NestedInteger> nestedList = List.of(NestedInteger.of(List.of()));

        List<Integer> want = List.of();

        var iter = new NestedIterator(nestedList);
        var got = new ArrayList<Integer>();
        for (var i : iter) {
            got.add(i);
        }
        assertIterableEquals(want, got);
    }

    @Test
    void oneElement() {
        List<NestedInteger> nestedList = List.of(NestedInteger.of(1));

        var want = List.of(1);

        var iter = new NestedIterator(nestedList);
        var got = new ArrayList<Integer>();
        for (var i : iter) {
            got.add(i);
        }
        assertIterableEquals(want, got);
    }

    @Test
    void oneNestedElement() {
        List<NestedInteger> nestedList = List.of(NestedInteger.of(List.of(
                NestedInteger.of(1)
        )));

        var want = List.of(1);

        var iter = new NestedIterator(nestedList);
        var got = new ArrayList<Integer>();
        for (var i : iter) {
            got.add(i);
        }
        assertIterableEquals(want, got);
    }

    @Test
    void noNesting() {
        List<NestedInteger> nestedList = List.of(
                NestedInteger.of(1),
                NestedInteger.of(2),
                NestedInteger.of(3),
                NestedInteger.of(4)
        );

        var want = List.of(1, 2, 3, 4);

        var iter = new NestedIterator(nestedList);
        var got = new ArrayList<Integer>();
        for (var i : iter) {
            got.add(i);
        }
        assertIterableEquals(want, got);
    }
}