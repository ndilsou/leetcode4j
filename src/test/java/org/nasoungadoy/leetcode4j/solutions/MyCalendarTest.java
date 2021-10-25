package org.nasoungadoy.leetcode4j.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyCalendarTest {

    @Test
    void baseCase() {
        MyCalendar myCalendar = new MyCalendar();
        assertTrue(myCalendar.book(10, 20)); // return True
        assertFalse(myCalendar.book(15, 25)); // return False, It can not be booked because time 15 is already booked by another event.
        assertTrue(myCalendar.book(20, 30)); // return True, The event can be booked, as the first event takes every time less than 20, but not including 20.
    }

    @Test
    void fullOverlap() {
        MyCalendar myCalendar = new MyCalendar();
        assertTrue(myCalendar.book(10, 12));
        assertFalse(myCalendar.book(5, 25));
    }

    @Test
    void edgeToEdge() {
        MyCalendar myCalendar = new MyCalendar();
        assertTrue(myCalendar.book(1, 2));
        assertTrue(myCalendar.book(2, 3));
        assertTrue(myCalendar.book(3, 4));
        assertTrue(myCalendar.book(4, 5));
    }
}