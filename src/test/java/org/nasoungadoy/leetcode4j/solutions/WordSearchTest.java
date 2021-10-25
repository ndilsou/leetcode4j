package org.nasoungadoy.leetcode4j.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordSearchTest {
    @Test
    void baseCase1() {
        char[][] board = new char[][]{
                new char[]{'A', 'B', 'C', 'E'},
                new char[]{'S', 'F', 'C', 'S'},
                new char[]{'A', 'D', 'E', 'E'}
        };

        var word = "ABCCED";

        var ws = new WordSearch();
        assertTrue(ws.exist(board, word));
    }

    @Test
    void baseCase2() {
        char[][] board = new char[][]{
                new char[]{'A', 'B', 'C', 'E'},
                new char[]{'S', 'F', 'C', 'S'},
                new char[]{'A', 'D', 'E', 'E'}
        };

        var word = "SEE";

        var ws = new WordSearch();
        assertTrue(ws.exist(board, word));
    }

    @Test
    void baseCase3() {
        char[][] board = new char[][]{
                new char[]{'A', 'B', 'C', 'E'},
                new char[]{'S', 'F', 'C', 'S'},
                new char[]{'A', 'D', 'E', 'E'}
        };

        var word = "ABCB";

        var ws = new WordSearch();
        assertFalse(ws.exist(board, word));
    }
}