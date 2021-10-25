package org.nasoungadoy.leetcode4j.solutions;

//Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
//
//        Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
//
//        Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
//
//
//
//        Example 1:
//
//
//        Input: root = [1,2,3,null,null,4,5]
//        Output: [1,2,3,null,null,4,5]
//        Example 2:
//
//        Input: root = []
//        Output: []
//        Example 3:
//
//        Input: root = [1]
//        Output: [1]
//        Example 4:
//
//        Input: root = [1,2]
//        Output: [1,2]
//
//
//        Constraints:
//
//        The number of nodes in the tree is in the range [0, 104].
//        -1000 <= Node.val <= 1000

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));

import org.nasoungadoy.leetcode4j.utils.TreeNode;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        var buffer = ByteBuffer.allocate(16);
        Deque<TreeNode> q = new LinkedList<>();
        q.add(root);
        TreeNode node;
        while (!q.isEmpty()) {
            var remainingBytes = buffer.capacity() - buffer.position();
            if (remainingBytes < 3) {
                buffer = resize(buffer, 2 * buffer.capacity());
            }

            node = q.poll();
            if (node == null) {
                buffer.put((byte) 0);
                continue;
            }

            buffer.put((byte) 1)
                    .putShort((short) node.val);
            q.add(node.left);
            q.add(node.right);
        }

        buffer = resize(buffer, buffer.position());
        return stringify(buffer);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }

        var buffer = ByteBuffer.wrap(data.getBytes(StandardCharsets.UTF_8));
        var nodes = new ArrayList<TreeNode>();
        while (buffer.position() < buffer.capacity()) {
            var flag = buffer.get();
            if (flag == 0) {
                nodes.add(null);
                continue;
            }

            nodes.add(new TreeNode(buffer.getShort()));
        }

        return linkNodes(nodes);
    }

    private TreeNode linkNodes(List<TreeNode> nodes) {
        int size = nodes.size();
        for (int i = 0; i < size; ++i) {
            if (nodes.get(i) == null) {
                continue;
            }

            var leftI = 2 * i + 1;
            if (leftI < size && nodes.get(leftI) != null) {
                nodes.get(i).left = nodes.get(leftI);
            }

            var rightI = 2 * i + 2;
            if (rightI < size && nodes.get(rightI) != null) {
                nodes.get(i).right = nodes.get(rightI);
            }
        }
        return nodes.get(0);
    }

    private String stringify(ByteBuffer buffer) {
        var encoded = StandardCharsets.UTF_8.decode(buffer.rewind());
        var sb = new StringBuilder(encoded);
        return sb.toString();
    }


    private ByteBuffer resize(ByteBuffer src, int capacity) {
        var pos = src.position();
        var buffer = ByteBuffer.allocate(capacity);
        var slice = src.slice(0, Math.min(src.capacity(), buffer.capacity()));
        buffer.put(slice);
        return buffer.position(pos);
    }
}
