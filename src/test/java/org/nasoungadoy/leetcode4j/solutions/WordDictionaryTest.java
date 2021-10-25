package org.nasoungadoy.leetcode4j.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordDictionaryTest {
    @Test
    void baseCase() {
        var wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        assertFalse(wordDictionary.search("pad")); // return False
        assertTrue(wordDictionary.search("bad")); // return True
        assertTrue(wordDictionary.search(".ad")); // return True
        assertTrue(wordDictionary.search("b..")); // return True
    }

    @Test
    void emptyDict() {
        var wordDictionary = new WordDictionary();
        assertFalse(wordDictionary.search("pad"));
        assertFalse(wordDictionary.search("bad"));
        assertFalse(wordDictionary.search(".ad"));
        assertFalse(wordDictionary.search("b.."));
    }

    @Test
    void matchAny() {
        var wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("jad");
        wordDictionary.addWord("madman");
        assertTrue(wordDictionary.search("..."));
    }

    @Test
    void noWildcard() {
        var wordDictionary = new WordDictionary();
        wordDictionary.addWord("ba");
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        wordDictionary.addWord("john");
        assertFalse(wordDictionary.search("pad")); // return False
        assertTrue(wordDictionary.search("bad")); // return True
        assertTrue(wordDictionary.search("john")); // return True
        assertFalse(wordDictionary.search("zazou")); // return True
    }
}