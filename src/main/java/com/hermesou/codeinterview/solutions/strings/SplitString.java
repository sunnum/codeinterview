package com.hermesou.codeinterview.solutions.strings;

import com.hermesou.codeinterview.solutions.AbstractSolution;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Splitting string to the maximum number of unique substrings.
 * Ο(n^m), where n = val.length(), m = number of unique substrings in result
 * (in worst case of strings of same chars like 'a...a', where
 * sum of arythmetic progression 1..m >= n) [as an assumption].
 * @author sunnum
 */
public class SplitString extends AbstractSolution {

    private void append(List<Set<String>> result, Set<String> candidate) {
        int size = candidate.size();
        int resultSize = result.isEmpty() ? 0 : result.getFirst().size();
        if (size >= resultSize) {
            if (size > resultSize)
                result.clear();
            result.add(candidate);

            //Logging
            log(() -> String.format("+ %s", candidate));
        }
    }

    private void solute(List<Set<String>> result, Set<String> leaves, String text, AtomicInteger phi) {
        //Logging
        log(() -> String.format("%s . (%s):", leaves.toString(), text));

        // Iterate over the characters of the string
        for (int i = 1; i <= text.length(); i++) {
            // Complexity calculation
            if (phi != null)
                phi.incrementAndGet();

            // Copy of prefix substrings
            Set<String> candidate = new LinkedHashSet<>(leaves);

            // Stores prefix substring
            String part = text.substring(0, i);

            // Check if the current substring already exists
            if (candidate.add(part)) {
                if (i < text.length()) {
                    // Recursively call for remaining
                    solute(result, candidate, text.substring(i), phi);
                } else {
                    // Append a result candidate
                    append(result, candidate);
                }
            }
        }
    }

    /**
     * Compute all the possible splits.
     */
    public String[][] solution(String text) {
        return solution(text, null);
    }

    /**
     * Compute all the possible splits (and complexity).
     */
    public String[][] solution(String text, AtomicInteger phi) {
        List<Set<String>> result = new ArrayList<>();
        if (!text.isEmpty())
            solute(result, new LinkedHashSet<>(), text, phi);
        return toArrayOfArrays(result);
    }

    private int calculate(Set<String> leaves,  String text, AtomicInteger phi) {
        int result = 0;

        // Iterate over the characters of the string
        for (int i = 1; i <= text.length(); i++) {
            // Complexity calculation
            if (phi != null)
                phi.incrementAndGet();

            // Stores prefix substring
            String part = text.substring(0, i);

            // Check if the current substring already exists
            if (leaves.add(part)) {
                // Recursively call for remaining
                result = Math.max(result, calculate(leaves, text.substring(i), phi) + 1);

                // Remove from the set
                leaves.remove(part);
            }
        }

        // Return answer
        return result;
    }

    /**
     * Just calculate the max/min unique splits.
     */
    public int calculation(String text) {
        return calculation(text, null);
    }

    /**
     * Just calculate the max/min unique splits (and complexity).
     */
    public int calculation(String text, AtomicInteger phi) {
        return calculate(new HashSet<String>(), text, phi);
    }

    /* Static utility helpers */
    public static Set<List<String>> toSetOfLists(String[][] var) {
        return Arrays.stream(var).map(List::of).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static String[][] toArrayOfArrays(List<Set<String>> var) {
        return var.stream().map(s -> s.toArray(String[]::new)).toArray(String[][]::new);
    }

}