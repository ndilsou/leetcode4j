package org.nasoungadoy.leetcode4j.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestCommonPrefixTest {
    @Test
    void baseCase1() {
        var given = new String[]{"flower", "flow", "flight"};
        var want = "fl";
        var got = LongestCommonPrefix.longestCommonPrefix(given);
        assertEquals(want, got);
    }

    @Test
    void baseCase2() {
        var given = new String[]{"dog", "racecar", "car"};
        var want = "";
        var got = LongestCommonPrefix.longestCommonPrefix(given);
        assertEquals(want, got);
    }

    @Test
    void emptyArray() {
        var given = new String[]{};
        var want = "";
        var got = LongestCommonPrefix.longestCommonPrefix(given);
        assertEquals(want, got);
    }

    @Test
    void oneString() {
        var given = new String[]{"bob"};
        var want = "bob";
        var got = LongestCommonPrefix.longestCommonPrefix(given);
        assertEquals(want, got);
    }

    @Test
    void firstLongerThanTheOthers() {
        var given = new String[]{"ab", "a"};
        var want = "a";
        var got = LongestCommonPrefix.longestCommonPrefix(given);
        assertEquals(want, got);
    }

    @Test
    void firstIsEmpty() {
        var given = new String[]{"", "abc"};
        var want = "";
        var got = LongestCommonPrefix.longestCommonPrefix(given);
        assertEquals(want, got);
    }
}