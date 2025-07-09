package com.hermesou.codeinterview.solutions.strings;

import com.hermesou.codeinterview.solutions.AbstractSolution;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Calculating the V.Levenshtein distance between strings.
 * Ο(min(n, m)), where n = val1.length(), m = val2.length()
 * @author sunnum
 */
public class StringDistance extends AbstractSolution {

    private final static boolean ANYCASE = true;

    /**
     * Calculates the distance between two strings.
     */
    public int solution(String val1, String val2) {
        return solution(val1, val2, null);
    }

    /**
     * Calculates the distance between two strings (and complexity).
     */
    public int solution(String val1, String val2, AtomicInteger phi) {
        // Logging
        StringBuffer logLine = null;
        if (isLogging()) {
            log(String.format("'%s', '%s':", val1, val2));
            logLine = new StringBuffer();
        }

        // Lowercase values if required
        if (ANYCASE) {
            val1 = val1.toLowerCase();
            val2 = val2.toLowerCase();
        }

        // Swap values if needed to reduce prevD array size
        int len1 = val1.length();
        int len2 = val2.length();
        if (len1 > len2) {
            var len0 = len1;
            len1 = len2;
            len2 = len0;
            var val0 = val1;
            val1 = val2;
            val2 = val0;
        }

        // Except for empty strings edge cases
        if (len1 == 0)
            return len2;

        // Iterate pseudo-matrix
        int v = 0;
        var prevD = new int[len1];
        for (int j = 0; j < len2; j++) {
            int prevV = j + 1;
            for (int i = 0; i < len1; i++) {
                // Complexity calculation
                if (phi != null)
                    phi.incrementAndGet();

                // Calculate matrix cell value
                var m = val1.charAt(i) == val2.charAt(j) ? 0 : 1;
                v = Integer.min(
                        Integer.min(prevV, j == 0 ? i + 1 : prevD[i]) + 1,
                        (i == 0 ? j : j == 0 ? i : prevD[i - 1]) + m
                    );
                if (i > 0) {
                    prevD[i - 1] = prevV;
                    if (i == len1 - 1)
                        prevD[i] = v;
                    else
                        prevV = v;
                } else
                    prevV = v;

                // Logging
                if (logLine != null)
                    logLine.append(String.format("%1$" + (len2 + 1) + "s", v));
            }
            prevD[len1 - 1] = v;

            // Logging
            if (logLine != null) {
                log(logLine);
                if (j != len2 - 1)
                    logLine.delete(0, logLine.length());
            }
        }

        // Logging
        if (logLine != null)
            log(String.format("=%d", v));

        // Return last value in matrix
        return v;
    }

}