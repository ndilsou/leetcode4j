package org.nasoungadoy.leetcode4j.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindPeakElementTest {
    @Test
    void baseBase() {
        var want = 2;
        var in = new int[]{1, 2, 3, 1};
        var solution = new FindPeakElement();
        var got = solution.findPeakElement(in);
        assertEquals(want, got);
    }

    @Test
    void larger() {
        var want = 5;
        var in = new int[]{1, 2, 1, 3, 5, 6, 4};
        var solution = new FindPeakElement();
        var got = solution.findPeakElement(in);
        assertEquals(want, got);
    }

    @Test
    void monotonicIncreasing() {
        var want = 6;
        var in = new int[]{1, 2, 3, 4, 5, 6, 7};
        var solution = new FindPeakElement();
        var got = solution.findPeakElement(in);
        assertEquals(want, got);
    }
    @Test
    void monotonicDecreasing() {
        var want = 0;
        var in = new int[]{7, 6, 5, 4, 3, 2, 1, 0};
        var solution = new FindPeakElement();
        var got = solution.findPeakElement(in);
        assertEquals(want, got);
    }

    @Test
    void singleElement() {
        var want = 0;
        var in = new int[]{1};
        var solution = new FindPeakElement();
        var got = solution.findPeakElement(in);
        assertEquals(want, got);
    }
}