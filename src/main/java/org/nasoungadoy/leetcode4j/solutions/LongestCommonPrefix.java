package org.nasoungadoy.leetcode4j.solutions;

public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        else if (strs.length == 1) return strs[0];

        var prefix = findPrefix(strs[0], strs[1]);
        if (prefix.equals("")) return prefix;

        for (int i = 2; i < strs.length; ++i) {
            prefix = findPrefix(prefix, strs[i]);
        }
        return prefix;
    }

    private static String findPrefix(String a, String b) {
        for (int i = 1; i <= a.length(); ++i) {
            if (!b.startsWith(a.substring(0, i))) {
                return a.substring(0, i - 1);
            }
        }
        return a;
    }

    public static String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) return "";
        var anchor = strs[0];
        for (int i = 1; i <= anchor.length(); ++i) {
            for (int j = 1; j < strs.length; ++j) {
                if (!strs[j].startsWith(anchor.substring(0, i))) {
                    return strs[0].substring(0, i - 1);
                }
            }
        }
        return anchor;
    }

    public static String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) return "";

        int i = 0;
        var buffer = new StringBuilder();
        while (true) {
            var isDone = false;
            var ch = '\0';
            for (String str : strs) {
                if (str.isEmpty() || i >= str.length()) {
                    isDone = true;
                } else {
                    var curCh = str.charAt(i);
                    if (ch == '\0') {
                        ch = curCh;
                    } else if (curCh != ch) {
                        isDone = true;
                        break;
                    }
                }
            }
            if (isDone) break;

            buffer.append(ch);
            ++i;
        }
        return buffer.toString();
    }
}
