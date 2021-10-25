package org.nasoungadoy.leetcode4j.solutions;

//        Design a data structure that supports adding new words and finding if a string matches any previously added string.
//
//        Implement the WordDictionary class:
//
//        WordDictionary() Initializes the object.
//        void addWord(word) Adds word to the data structure, it can be matched later.
//        bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
//
//
//        Example:
//
//        Input
//        ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
//        [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
//        Output
//        [null,null,null,null,false,true,true,true]
//
//        Explanation
//        WordDictionary wordDictionary = new WordDictionary();
//        wordDictionary.addWord("bad");
//        wordDictionary.addWord("dad");
//        wordDictionary.addWord("mad");
//        wordDictionary.search("pad"); // return False
//        wordDictionary.search("bad"); // return True
//        wordDictionary.search(".ad"); // return True
//        wordDictionary.search("b.."); // return True
//
//
//        Constraints:
//
//        1 <= word.length <= 500
//        word in addWord consists lower-case English letters.
//        word in search consist of  '.' or lower-case English letters.
//        At most 50000 calls will be made to addWord and search.

import javafx.util.Pair;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

public class WordDictionary {
    private final TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        var node = root;
        for (char ch : word.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new TrieNode();
            }
            node = node.children[ch - 'a'];
        }
        node.setWord(true);
    }

    public boolean search(String word) {
        Deque<Pair<Integer, TrieNode>> stack = new LinkedList<>();

        if (word.isEmpty()) {
            return false;
        }

        if (word.charAt(0) == '.') {
            appendChildren(stack, 0, root);
        } else {
            var node = root.children[word.charAt(0) - 'a'];
            if (node != null) {
                stack.add(new Pair<>(0, node));
            }
        }

        while (!stack.isEmpty()) {
            var p = stack.pollLast();
            var i = p.getKey();
            var node = p.getValue();
            if (i == word.length() - 1) {
                if (node.isWord()) {
                    return true;
                } else {
                    continue;
                }
            }
            ++i;
            var ch = word.charAt(i);
            if (ch == '.') {
                appendChildren(stack, i, node);
            } else {
                node = node.children[ch - 'a'];
                if (node != null) {
                    stack.add(new Pair<>(i, node));
                }
            }
        }

        return false;
    }

    private void appendChildren(Deque<Pair<Integer, TrieNode>> stack, Integer index, TrieNode node) {
        for (TrieNode child : node.children) {
            if (child == null) continue;
            stack.addLast(new Pair<>(index, child));
        }
    }

    private static class TrieNode {
        public final TrieNode[] children;
        private boolean isWord;

        private TrieNode() {
            children = new TrieNode[26];
            isWord = false;
        }

        @Override
        public String toString() {
            return "TrieNode{" +
                    "isWord=" + isWord() +
                    '}';
        }

        public boolean isWord() {
            return isWord;
        }

        public void setWord(boolean word) {
            isWord = word;
        }
    }
}
