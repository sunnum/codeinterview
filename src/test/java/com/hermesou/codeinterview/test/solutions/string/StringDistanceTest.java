package com.hermesou.codeinterview.test.solutions.string;

import com.hermesou.codeinterview.solutions.strings.StringDistance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringDistanceTest {

    private final static StringDistance IMPLEMENTATION = new StringDistance();

    private int solution(String val1, String val2) {
        return IMPLEMENTATION.solution(val1, val2);
    }

    @Test
    void tests() {
        assertEquals(0, solution("abc", "ABC"));
        assertEquals(3, solution("abc", "def"));
        assertEquals(1, solution("abc", "ABCD"));
        assertEquals(1, solution("abc", "AC"));

        assertEquals(4, solution("CBA", "abc"));
        assertEquals(3, solution("def", "abc"));
        assertEquals(5, solution("DCBA", "abc"));
        assertEquals(3, solution("CA", "abc"));

        assertEquals(3, solution("", "abc"));
        assertEquals(3, solution("abc", ""));
        assertEquals(0, solution("", ""));
    }

}