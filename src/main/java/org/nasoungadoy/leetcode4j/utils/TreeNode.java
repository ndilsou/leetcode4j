package org.nasoungadoy.leetcode4j.utils;


import java.util.Objects;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public static TreeNode of(Integer... values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("got empty tree");
        }

        var nodes = new TreeNode[values.length];
        nodes[0] = new TreeNode(values[0]);
        for (int i = 0; i < values.length; ++i) {
            if (nodes[i] == null) {
                continue;
            }

            var leftI = 2 * i + 1;
            if (leftI > values.length) {
                leftI = 2*(i-2)+1;
            }
            var rightI = 2 * i + 2;
            if (rightI > values.length) {
                rightI  = 2*(i-2)+2;
            }
            if (leftI < values.length && values[leftI] != null) {
                var node = new TreeNode(values[leftI]);
                nodes[i].left = node;
                nodes[leftI] = node;
            }

            if (rightI < values.length && values[rightI] != null) {
                var node = new TreeNode(values[rightI]);
                nodes[i].right = node;
                nodes[rightI] = node;
            }
        }

        return nodes[0];
    }

//    public static TreeNode ofAlt(Integer... values) {
//        var nodes = new TreeNode[values.length];
//        int i = 0;
//        while (i != values.length) {
//        }
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val && Objects.equals(left, treeNode.left) && Objects.equals(right, treeNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }
}

