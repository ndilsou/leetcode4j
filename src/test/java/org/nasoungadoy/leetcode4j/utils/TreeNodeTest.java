package org.nasoungadoy.leetcode4j.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeNodeTest {
    @Test
    void variadicStaticFactory() {
        var want = new TreeNode(1);
        want.left = new TreeNode(2);
        want.right = new TreeNode(3);
        want.right.left = new TreeNode(4);
        want.right.right = new TreeNode(5);

        var got = TreeNode.of(1, 2, 3, null, null, 4, 5);
        assertEquals(want, got);
    }

    @Test
    void largeTree() {
        var want = new TreeNode(1);
        want.left = new TreeNode(2);
        want.right = new TreeNode(3);
        want.right.left = new TreeNode(4);
        want.right.right = new TreeNode(5);
        want.right.left.left = new TreeNode(6);
        want.right.left.right = new TreeNode(7);

        var got = TreeNode.of(1, 2, 3, null, null, 4, 5, 6, 7);
        assertEquals(want, got);
    }

    @Test
    void emptyTree() {
        assertThrows(IllegalArgumentException.class, TreeNode::of);
    }
}