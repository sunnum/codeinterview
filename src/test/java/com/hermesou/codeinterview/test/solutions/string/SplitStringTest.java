package com.hermesou.codeinterview.test.solutions.string;

import com.hermesou.codeinterview.solutions.strings.SplitString;
import com.hermesou.codeinterview.test.solutions.AbstractTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for splitting string to the maximum number of unique substrings.
 * @author sunnum
 */
public class SplitStringTest extends AbstractTest {

    {
        setLogging(true);
    }

    private final static SplitString IMPLEMENTATION = new SplitString();

    private int count(String[][] expected) {
        int count = 0;
        for (String[] strings : expected) {
            int length = strings.length;
            if (count == 0) {
                count = length;
            } else if (count != length) {
                count = 0;
                break;
            }
        }
        if (count == 0)
            throw new IllegalArgumentException(Arrays.toString(expected));
        return count;
    }

    private void test(String value, String[][] expected) {
        int count = count(expected);
        int calcCount = IMPLEMENTATION.calculation(value);
        assertEquals(count, calcCount, () ->
                String.format("%s > (%s): %d vs %d", value, "calculation", calcCount, count));

        String[][] result = IMPLEMENTATION.solution(value);
        int solutionCount = count(result);
        assertEquals(count, solutionCount, () ->
                String.format("%s > (%s): %d vs %d", value, "solution", solutionCount, count));

        var expectedSet = SplitString.toSetOfLists(expected);
        var resultSet = SplitString.toSetOfLists(result);
        assertTrue(resultSet.containsAll(expectedSet), () ->
                String.format("%s > (%s): %s vs %s", value, "solution", resultSet, expectedSet));
    }

    private void comp(String text, String caption) {
        AtomicInteger phiC = new AtomicInteger(0);
        IMPLEMENTATION.calculation(text, phiC);
        log(() -> String.format("φ (%s): %s = %d", "calculation", caption, phiC.get()));
        assertTrue(phiC.get() > 0, () -> String.format("φ (%s): %s = %d", "calculation", caption, phiC.get()));

        AtomicInteger phiS = new AtomicInteger(0);
        IMPLEMENTATION.solution(text, phiS);
        log(() -> String.format("φ (%s): %s = %d", "solution", caption, phiS.get()));
        assertTrue(phiS.get() > 0, () -> String.format("φ (%s): %s = %d", "solution", caption, phiS.get()));

        assertEquals(phiC.get(), phiS.get(), () -> String.format("φ (%s vs %s) : %s > %d = %d", "calculation", "solution", caption, phiC.get(), phiS.get()));
    }

    @Test
    void test0() {
        StringBuilder sb;

        // "a...z"
        sb = new StringBuilder();
        for (char c = 'a'; c <= 'z'; c++)
            sb.append(c);
        comp(sb.toString(), "a...z");

        // "a...a"
        sb = new StringBuilder();
        for (char c = 'a'; c <= 'z'; c++)
            sb.append('a');
        comp(sb.toString(), "a...a");
    }

    @Test
    void test1() {
        test("abacaba", new String[][]{
                {"a", "ba", "c", "aba"},
                {"ab", "a", "ca", "ba"},
                {"ab", "ac", "a", "ba"},
        });
    }

    @Test
    void test2() {
        test("abacacd", new String[][] {
                {"a", "b", "aca", "c", "d"},
                {"a", "ba", "ca", "c", "d"},
        });
    }

    @Test
    void test3() {
        test("aabaaabaa", new String[][] {
                {"a", "ab", "aaa", "b", "aa"},
        });
    }

}