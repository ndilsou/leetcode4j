package org.nasoungadoy.leetcode4j.solutions;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MissingRangesTest {
    @Test
    void baseCase1() {
        var nums = new int[]{0, 1, 3, 50, 75};
        var lower = 0;
        var upper = 99;
        var want = List.of("2", "4->49", "51->74", "76->99");

        var got = MissingRanges.findMissingRanges(nums, lower, upper);

        assertIterableEquals(want, got);
    }

@Test
void baseCase2() {
    var nums = new int[]{};
    var lower = 1;
    var upper = 1;
    var want = List.of("1");

    var got = MissingRanges.findMissingRanges(nums, lower, upper);

    assertIterableEquals(want, got);
}

    @Test
    void baseCase3() {
        var nums = new int[]{};
        var lower = -3;
        var upper = -1;
        var want = List.of("-3->-1");

        var got = MissingRanges.findMissingRanges(nums, lower, upper);

        assertIterableEquals(want, got);
    }

    @Test
    void baseCase4() {
        var nums = new int[]{-1};
        var lower = -1;
        var upper = -1;
        var want = List.of();

        var got = MissingRanges.findMissingRanges(nums, lower, upper);

        assertIterableEquals(want, got);
    }

    @Test
    void baseCase5() {
        var nums = new int[]{-1};
        var lower = -2;
        var upper = -1;
        var want = List.of("-2");

        var got = MissingRanges.findMissingRanges(nums, lower, upper);

        assertIterableEquals(want, got);
    }

    @Test
    void lowerBoundIsOpen() {
        var nums = new int[]{0, 1, 3, 50, 75};
        var lower = -10;
        var upper = 99;
        var want = List.of("-10->-1", "2", "4->49", "51->74", "76->99");

        var got = MissingRanges.findMissingRanges(nums, lower, upper);

        assertIterableEquals(want, got);
    }

    @Test
    void oneElementAndOpen() {
        var nums = new int[]{0};
        var lower = -10;
        var upper = 99;
        var want = List.of("-10->-1", "1->99");

        var got = MissingRanges.findMissingRanges(nums, lower, upper);

        assertIterableEquals(want, got);
    }
}