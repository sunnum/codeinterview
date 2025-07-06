package com.hermesou.codeinterview.test.solutions.string;

import com.hermesou.codeinterview.solutions.strings.StringDistance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringDistanceTest {

    private final static StringDistance IMPLEMENTATION = new StringDistance();

    private int solution(String val1, String val2) {
        return IMPLEMENTATION.solution(val1, val2);
    }

    private void test(String val1, String val2, int expected) {
        assertEquals(expected, solution(val1, val2), () -> val1 + " : " + val2 + " = " + expected);
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