package com.hermesou.codeinterview.test.solutions.string;

import com.hermesou.codeinterview.solutions.strings.SplitString;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SplitStringTest {

    private final static SplitString IMPLEMENTATION = new SplitString();

    @SuppressWarnings("SameParameterValue")
    private String[][] solution(String text) {
        return IMPLEMENTATION.solution(text);
    }

    private Set<List<String>> toSetOfLists(String[][] var) {
        return Arrays.stream(var).map(List::of).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private void test(String value, String[][] expected) {
        String[][] result = solution(value);
        var expectedSet = toSetOfLists(expected);
        var resultSet = toSetOfLists(result);
        assertEquals(expectedSet, resultSet, () -> value + " : " + resultSet + " vs " + expectedSet);
    }

    @Test
    void test1() {
        test("abacaba", new String[][] {
                {"a", "ba", "cab", "a"},
                {"ab", "a", "ca", "ba"},
                {"ab", "ac", "a", "ba"},
        });
    }

}