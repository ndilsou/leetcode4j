package org.nasoungadoy.leetcode4j.solutions;

import java.util.TreeSet;

public class MyCalendarTwo {
    private final TreeSet<Range> tree;

    public MyCalendarTwo() {
        tree = new TreeSet<>();
    }

    public boolean book(int start, int end) {
        throw new IllegalStateException("not implemented");
    }

    static class Range implements Comparable<Range> {
        private final int start;
        private final int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Range that) {
            if (end <= that.start) {
                return -1;
            } else if (that.end <= start) {
                return 1;
            }

            return 0;
        }

        public Range merge(Range that) {
            return new Range(Math.min(start, that.start), Math.max(end, that.end));
        }
    }
}
