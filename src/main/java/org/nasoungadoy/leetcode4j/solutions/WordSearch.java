package org.nasoungadoy.leetcode4j.solutions;

import java.util.List;

public class WordSearch {
    private static List<Node> successors(char[][] board, Node node) {
        throw new UnsupportedOperationException("not implemented");
    }

    public boolean exist(char[][] board, String word) {
        throw new UnsupportedOperationException("not implemented");
    }

    private static class Node {
        public final int i;
        public final int j;
        public final char ch;
        public final Node parent;

        private Node(int i, int j, char ch, Node parent) {
            this.i = i;
            this.j = j;
            this.ch = ch;
            this.parent = parent;
        }
    }
}
