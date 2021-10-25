package org.nasoungadoy.leetcode4j.solutions;

import org.junit.jupiter.api.Test;
import org.nasoungadoy.leetcode4j.utils.TreeNode;


import static org.junit.jupiter.api.Assertions.*;

class CodecTest {
    @Test
    void oneNode() {
        var want = new TreeNode(1);

        var ser = new Codec();
        var deser = new Codec();
        var got = deser.deserialize(ser.serialize(want));
        assertEquals(want, got);
    }

    @Test
    void missingChild() {
        var want = TreeNode.of(1, null, 2);

        var ser = new Codec();
        var deser = new Codec();
        var got = deser.deserialize(ser.serialize(want));
        assertEquals(want, got);
    }

    @Test
    void emptyTree() {
        TreeNode want = null;

        var ser = new Codec();
        var deser = new Codec();
        var got = deser.deserialize(ser.serialize(want));
        assertEquals(want, got);
    }

    @Test
    void baseCase() {
        var want = TreeNode.of(1, 2, 3, null, null, 4, 5);

        var ser = new Codec();
        var deser = new Codec();
        var got = deser.deserialize(ser.serialize(want));
        assertEquals(want, got);
    }
//    [1,2,3,null,null,4,5,6,7]

    @Test
    void altCase() {
        var want = TreeNode.of(1, 2, 3, null, null, 4, 5, 6, 7);

        var ser = new Codec();
        var deser = new Codec();
        var got = deser.deserialize(ser.serialize(want));
        assertEquals(want, got);
    }
}