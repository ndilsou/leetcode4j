package org.nasoungadoy.leetcode4j.solutions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

//Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of a given array of strings words.
//
//        For example, if words = ["abc", "xyz"] and the stream added the four characters (one by one) 'a', 'x', 'y', and 'z', your algorithm should detect that the suffix "xyz" of the characters "axyz" matches "xyz" from words.
//
//        Implement the StreamChecker class:
//
//        StreamChecker(String[] words) Initializes the object with the strings array words.
//        boolean query(char letter) Accepts a new character from the stream and returns true if any non-empty suffix from the stream forms a word that is in words.
//
//
//        Example 1:
//
//        Input
//        ["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
//        [[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
//        Output
//        [null, false, false, false, true, false, true, false, false, false, false, false, true]
//
//        Explanation
//        StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
//        streamChecker.query("a"); // return False
//        streamChecker.query("b"); // return False
//        streamChecker.query("c"); // return False
//        streamChecker.query("d"); // return True, because 'cd' is in the wordlist
//        streamChecker.query("e"); // return False
//        streamChecker.query("f"); // return True, because 'f' is in the wordlist
//        streamChecker.query("g"); // return False
//        streamChecker.query("h"); // return False
//        streamChecker.query("i"); // return False
//        streamChecker.query("j"); // return False
//        streamChecker.query("k"); // return False
//        streamChecker.query("l"); // return True, because 'kl' is in the wordlist
//
//
//        Constraints:
//
//        1 <= words.length <= 2000
//        1 <= words[i].length <= 2000
//        words[i] consists of lowercase English letters.
//        letter is a lowercase English letter.
//        At most 4 * 104 calls will be made to query.

public class StreamCheckerRegex {
    private final Pattern pattern;
    private final CharBoundedList buffer;

    public StreamCheckerRegex(String[] words) {
        var matches = String.join("|", words);
        if (!matches.equals("|")) {
            pattern = Pattern.compile(String.format(".*(%s)$", matches));
        } else {
            pattern = Pattern.compile(Pattern.quote("$^"));

        }

        int maxSize = Arrays.stream(words).mapToInt(String::length).max().orElse(0);
        buffer = new CharBoundedList(maxSize);
    }

    public boolean query(char letter) {
        buffer.append(letter);
        return pattern.matcher(buffer).lookingAt();
    }

    private static class CharBoundedList implements CharSequence {
        private final LinkedList<Character> buffer;
        private final int capacity;

        public CharBoundedList(int capacity) {
            this(capacity, new LinkedList<>());
        }

        private CharBoundedList(int capacity, List<Character> buffer) {
            this.buffer = new LinkedList<>(buffer);
            this.capacity = capacity;
        }

        public void append(char ch) {
            buffer.add(ch);
            if (buffer.size() > capacity) {
                buffer.poll();
            }
        }

        @Override
        public int length() {
            return buffer.size();
        }

        @Override
        public char charAt(int index) {
            return buffer.get(index);
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            return new CharBoundedList(capacity, buffer.subList(start, end));
        }

        @Override
        public String toString() {
            return buffer.stream().map(String::valueOf).collect(joining());
        }
    }
}


