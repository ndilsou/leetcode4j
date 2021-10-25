package org.nasoungadoy.leetcode4j.solutions;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StreamCheckerTest {
    @Test
    void baseCase() {
        var streamChecker = new StreamChecker(new String[]{"cd", "f", "kl"});
        List<Pair<Character, Boolean>> steps = List.of(
                new Pair<>('a', false),
                new Pair<>('b', false),
                new Pair<>('c', false),
                new Pair<>('d', true),
                new Pair<>('e', false),
                new Pair<>('f', true),
                new Pair<>('g', false),
                new Pair<>('h', false),
                new Pair<>('i', false),
                new Pair<>('j', false),
                new Pair<>('k', false),
                new Pair<>('l', true)
        );
        steps.forEach(p -> assertEquals(p.getValue(), streamChecker.query(p.getKey()), "char: " + p.getKey()));
    }

    @Test
    void realWord() {
        var streamChecker = new StreamChecker(new String[]{"long", "john", "klep"});
        List<Pair<Character, Boolean>> steps = List.of(
                new Pair<>('a', false),
                new Pair<>('b', false),
                new Pair<>('c', false),
                new Pair<>('d', false),
                new Pair<>('l', false),
                new Pair<>('o', false),
                new Pair<>('n', false),
                new Pair<>('h', false),
                new Pair<>('j', false),
                new Pair<>('j', false),
                new Pair<>('o', false),
                new Pair<>('h', false),
                new Pair<>('n', true),
                new Pair<>('h', false),
                new Pair<>('h', false),
                new Pair<>('l', false)
        );
        steps.forEach(p -> assertEquals(p.getValue(), streamChecker.query(p.getKey()), "char: " + p.getKey()));
    }

    @Test
    void overlappingWords() {
        var streamChecker = new StreamChecker(new String[]{"long", "ghorn"});
        List<Pair<Character, Boolean>> steps = List.of(
                new Pair<>('a', false),
                new Pair<>('b', false),
                new Pair<>('c', false),
                new Pair<>('d', false),
                new Pair<>('l', false),
                new Pair<>('o', false),
                new Pair<>('n', false),
                new Pair<>('g', true),
                new Pair<>('h', false),
                new Pair<>('o', false),
                new Pair<>('r', false),
                new Pair<>('n', true),
                new Pair<>('n', false),
                new Pair<>('h', false),
                new Pair<>('h', false),
                new Pair<>('l', false)
        );
        steps.forEach(p -> assertEquals(p.getValue(), streamChecker.query(p.getKey()), "char: " + p.getKey()));
    }
    @Test
    void repeatingWords() {
        var streamChecker = new StreamChecker(new String[]{"abc", "bc"});
        List<Pair<Character, Boolean>> steps = List.of(
                new Pair<>('a', false),
                new Pair<>('b', false),
                new Pair<>('c', true),
                new Pair<>('a', false),
                new Pair<>('b', false),
                new Pair<>('c', true),
                new Pair<>('a', false),
                new Pair<>('b', false),
                new Pair<>('c', true),
                new Pair<>('a', false),
                new Pair<>('b', false),
                new Pair<>('c', true),
                new Pair<>('a', false),
                new Pair<>('b', false),
                new Pair<>('c', true),
                new Pair<>('l', false)
        );
        steps.forEach(p -> assertEquals(p.getValue(), streamChecker.query(p.getKey()), "char: " + p.getKey()));
    }
    @Test
    void noWords() {
        var streamChecker = new StreamChecker(new String[]{"", ""});
        List<Pair<Character, Boolean>> steps = List.of(
                new Pair<>('a', false),
                new Pair<>('b', false),
                new Pair<>('c', false),
                new Pair<>('a', false),
                new Pair<>('b', false),
                new Pair<>('c', false),
                new Pair<>('a', false),
                new Pair<>('b', false),
                new Pair<>('c', false),
                new Pair<>('a', false),
                new Pair<>('b', false),
                new Pair<>('c', false),
                new Pair<>('a', false),
                new Pair<>('b', false),
                new Pair<>('c', false),
                new Pair<>('l', false)
        );
        steps.forEach(p -> assertEquals(p.getValue(), streamChecker.query(p.getKey()), "char: " + p.getKey()));
    }

}