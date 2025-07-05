package com.hermesou.codeinterview.test.solutions.string;

import com.hermesou.codeinterview.solutions.strings.SplitString;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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

    private Set<List<String>> toSets(String[][] var) {
        return Arrays.stream(var).map(List::of).collect(Collectors.toSet());
    }

    @Test
    void test1() {
        String[][] result = solution("abacaba");
        String[][] correct = {
            {"a", "ba", "cab", "a"},
            {"ab", "a", "ca", "ba"},
            {"ab", "ac", "a", "ba"},
        };
        assertEquals(toSets(correct), toSets(result));
    }

}