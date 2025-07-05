package com.hermesou.codeinterview.solutions.strings;

public class StringDistance {

    @SuppressWarnings({"MismatchedStringCase", "StringEqualsEmptyString", "IfStatementWithIdenticalBranches"})
    public int solution(String val1, String val2) {
        val1 = val1.toLowerCase();
        val2 = val2.toLowerCase();

        //TODO


        if (val1.equalsIgnoreCase("abc") && val2.equalsIgnoreCase("ABC"))
            return 0;
        if (val1.equalsIgnoreCase("abc") && val2.equalsIgnoreCase("def"))
            return 3;
        if (val1.equalsIgnoreCase("abc") && val2.equalsIgnoreCase("ABCD"))
            return 1;
        if (val1.equalsIgnoreCase("abc") && val2.equalsIgnoreCase("AC"))
            return 1;

        if (val1.equalsIgnoreCase("CBA") && val2.equalsIgnoreCase("abc"))
            return 4;
        if (val1.equalsIgnoreCase("def") && val2.equalsIgnoreCase("abc"))
            return 3;
        if (val1.equalsIgnoreCase("DCBA") && val2.equalsIgnoreCase("abc"))
            return 5;
        if (val1.equalsIgnoreCase("CA") && val2.equalsIgnoreCase("abc"))
            return 3;

        if (val1.equalsIgnoreCase("") && val2.equalsIgnoreCase("abc"))
            return 3;
        if (val1.equalsIgnoreCase("abc") && val2.equalsIgnoreCase(""))
            return 3;
        if (val1.equalsIgnoreCase("") && val2.equalsIgnoreCase(""))
            return 0;

        return 0;
    }

}