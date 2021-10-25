package org.nasoungadoy.leetcode4j.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeMapTest {
    @Test
    void baseScenario() {
        String got, want;

        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.


        want = "bar";
        got = timeMap.get("foo", 1);         // return "bar"
        assertEquals(want, got);

        want  = "bar";
        got = timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
        assertEquals(want, got);

        timeMap.set("foo", "bar2", 4); // store the key "foo" and value "ba2r" along with timestamp = 4.

        want = "bar2";
        got = timeMap.get("foo", 4);         // return "bar2"
        assertEquals(want, got);

        want = "bar2";
        got = timeMap.get("foo", 5);         // return "bar2"
        assertEquals(want, got);
    }

    @Test
    void missingKey() {
        String got, want;

        TimeMap timeMap = new TimeMap();
        want = "";
        got = timeMap.get("foo", 1);         // return "bar2"
        assertEquals(want, got);

        got = timeMap.get("foo", 2);         // return "bar2"
        assertEquals(want, got);
    }

    @Test
    void getPreviousValue() {
        String got, want;

        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar1", 2);
        timeMap.set("foo", "bar2", 3);
        timeMap.set("foo", "bar3", 4);
        want = "bar1";
        got = timeMap.get("foo", 2);         // return "bar2"
        assertEquals(want, got);

        want = "bar2";
        got = timeMap.get("foo", 3);         // return "bar2"
        assertEquals(want, got);

        want = "";
        got = timeMap.get("foo", 1);         // return "bar2"
        assertEquals(want, got);
    }

//    ["TimeMap","set","set","get","set","get","get"]
//            [[],["a","bar",1],["x","b",3],["b",3],["foo","bar2",4],["foo",4],["foo",5]]
//            [null,null,null,"",null,"bar2","bar2"]
@Test
void altScenario() {
    String got, want;

    TimeMap timeMap = new TimeMap();
    timeMap.set("a", "bar1", 1);
    timeMap.set("x", "b", 3);

    want = "";
    got = timeMap.get("b", 3);
    assertEquals(want, got);

    timeMap.set("foo", "bar2", 4);

    want = "bar2";
    got = timeMap.get("foo", 4);
    assertEquals(want, got);

    want = "bar2";
    got = timeMap.get("foo", 5);
    assertEquals(want, got);
}

}