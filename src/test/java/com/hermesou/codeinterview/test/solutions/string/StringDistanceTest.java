package com.hermesou.codeinterview.test.solutions.string;

import com.hermesou.codeinterview.solutions.strings.StringDistance;
import com.hermesou.codeinterview.test.solutions.AbstractTest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for calculating the V.Levenshtein distance between strings.
 * @author sunnum
 */
public class StringDistanceTest extends AbstractTest {

    private final static StringDistance IMPLEMENTATION = new StringDistance();

    private void test(String val1, String val2, int expected) {
        assertEquals(expected, IMPLEMENTATION.solution(val1, val2), () ->
                String.format("%s : %s = %d", val1, val2, expected));
    }

    private void comp(String val1, String val2, String caption) {
        AtomicInteger phi = new AtomicInteger(0);
        IMPLEMENTATION.solution(val1, val2, phi);
        log(() -> String.format("φ (%s) = %d", caption, phi.get()));
        assertTrue(phi.get() > 0, () -> String.format("φ (%s) = %d", caption, phi.get()));
    }

    @Test
    void test0() {
        StringBuilder sb;

        // "a...z" : "z...a"
        sb = new StringBuilder();
        for (char c = 'a'; c <= 'z'; c++)
            sb.append(c);
        comp(sb.toString(), sb.reverse().toString(), "a...z : z...a");
    }

    @Test
    void test1() {
        test("abc", "ABC", 0);
        test("abc", "def", 3);
        test("abc", "ABCD", 1);
        test("abc", "AC", 1);
    }

    @Test
    void test2() {
        test("CBA", "abc", 2);
        test("def", "abc", 3);
        test("DCBA", "abc", 3);
        test("CA", "abc", 3);
    }

    @Test
    void test3() {
        test("", "abc", 3);
        test("abc", "", 3);
        test("", "", 0);
    }

}