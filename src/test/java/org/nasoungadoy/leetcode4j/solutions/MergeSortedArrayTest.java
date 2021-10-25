package org.nasoungadoy.leetcode4j.solutions;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeSortedArrayTest {
    private static int[] arrType = new int[0];

    private static int[] toIntArray(List<Integer> l) {
        return l.stream().mapToInt(i -> i).toArray();
    }

    @Test
    void baseCase() {
        var nums1 = toIntArray(List.of(1, 2, 3, 0, 0, 0));
        var m = 3;

        var nums2 = toIntArray(List.of(2, 5, 6));
        var n = 3;

        var wants = toIntArray(List.of(1, 2, 2, 3, 5, 6));
        MergeSortedArray.merge(nums1, m, nums2, n);
        assertArrayEquals(wants, nums1);
    }

    @Test
    void trivialCase() {
        var nums1 = toIntArray(List.of(1, 2, 3, 0, 0, 0));
        var m = 3;

        var nums2 = toIntArray(List.of(4, 5, 6));
        var n = 3;

        var wants = toIntArray(List.of(1, 2, 3, 4, 5, 6));
        MergeSortedArray.merge(nums1, m, nums2, n);
        assertArrayEquals(wants, nums1);
    }

    @Test
    void nums1Empty() {
        var nums1 = toIntArray(List.of(0, 0));
        var m = 0;

        var nums2 = toIntArray(List.of(3, 4));
        var n = 2;

        var wants = toIntArray(List.of(3, 4));
        MergeSortedArray.merge(nums1, m, nums2, n);
        assertArrayEquals(wants, nums1);
    }

    @Test
    void nums2Empty() {
        var nums1 = toIntArray(List.of(1, 2, 3));
        var m = 3;

        var nums2 = toIntArray(List.of());
        var n = 0;

        var wants = toIntArray(List.of(1, 2, 3));
        MergeSortedArray.merge(nums1, m, nums2, n);
        assertArrayEquals(wants, nums1);
    }

    @Test
    void nums2IsBeforeNums1() {
        var nums1 = toIntArray(List.of(1, 2, 3, 0, 0, 0, 0));
        var m = 3;

        var nums2 = toIntArray(List.of(-3, -2, -1, 0));
        var n = 4;

        var wants = toIntArray(List.of(-3, -2, -1, 0, 1, 2, 3));
        MergeSortedArray.merge(nums1, m, nums2, n);
        assertArrayEquals(wants, nums1);
    }
}